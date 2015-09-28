<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>霍兰德评估结果查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">
  Ext.onReady(function(){
		
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
   		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   	
   
   
    var renderReport = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='<%=request.getContextPath()%>/manage/holland/viewReport.do?evId=" + record.data['evaluationId'] +"' target='_blank' onclick=''>查看</a>";
        return s;
    }; 
    
    var renderDetail = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='<%=request.getContextPath()%>/manage/holland/gotoStep.do?step=1&evId=" + record.data['evaluationId'] +"' target='_blank' onclick=''>查看</a>";
         return s;
    }; 
    
    var renderProcess = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='javascript:showSelStrategyWin(\""+rowIndex+"\")' onclick=''>查看</a>";
         return s;
    }; 
    
  
		
  
   
   	var cm = new Ext.grid.ColumnModel([ //new Ext.grid.RowNumberer(),
   	      										{header:'序号',dataIndex:'id',sortable:false},
   	      										{header:'评测编号',dataIndex:'evaluationId',sortable:false},
   	      										{header:'姓名',dataIndex:'name',sortable:false },
   	      										{header:'qq',dataIndex:'qq',sortable:false},
   	      										{header:'评测时间',dataIndex:'createDate',sortable:false},
   	      										{header:'评测报告',dataIndex:'id',renderer:renderReport,sortable:false},
   	      										{header:'评测明细',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'处理时间',dataIndex:'createDate',sortable:false},
   	      										{header:'处理意见',dataIndex:'id',renderer:renderDetail,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/holland/query.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'evaluationId'},
          		{name:'name'},
          		{name:'qq'},
          		{name:'createDate'}
          	  ]),
      	  remoteSort:true
      });

      
     
      var grid = new Ext.grid.GridPanel({
      	title:'霍兰德评测列表',
      	store:store,
      	region:'center',
      	width:300,
      	cm:cm,
      	sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
          bbar:[
		            new Ext.PagingToolbar(
	            	{
	            		pageSize:20,
	            		store:store,
	            		displayInfo:true,
	            		displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
	            		emptyMsg:"没有记录"
	            	})]
      })  ;
      
      var applyCombo = new Ext.form.ComboBox({ 
    	    id:'id' ,
     	    typeAhead: true,
     	    triggerAction: 'all',
     	    lazyRender:false,
     	    fieldLabel:'是否申请',
     	    mode: 'local',
     	    width:50,
     	    store: new Ext.data.ArrayStore({
     	        id: 0,
     	        fields: [
     	            'id',
     	            'name'
     	        ],
     	        data: [[1, '是'], [0, '否']]
     	    }),
     	    valueField: 'id',
     	    displayField: 'name',
     	 	hiddenName:'status'
     	});
       	
      
    //查询窗口
    var form_query = new Ext.form.FormPanel({
    	id:'query',
    	title:'查询',
	    region: 'north',
	    height:100,
	    labelWidth:55,
    	collapsible:false,
    	collapsed:false,
    	frame:true,
    	url:'',
    	items:[
    		{
    			layout:'column',
    			items:[
    			  
    			 {
    			   columnWidth:.3,
    			   layout:'form',
    			   items:[
    			    {
					    xtype:'textfield',
					    id:'name1',
					    fieldLabel: '起始时间',
					    labelWidth:65,
					    name: 'name',
					    width:50,
					    value:'',
					    allowBlank:true,
					    anchor:'95%'
					},
					{
  					    xtype:'textfield',
  					    id:'name2',
  					    fieldLabel: '结束时间',
  					    name: 'name',
  					    value:'',
  					    allowBlank:true,
  					    anchor:'95%'
  					}]
    			 },
    			 {
      			   columnWidth:.2,
      			   layout:'form',
      			   items:[

      					{
      					    xtype:'textfield',
      					    id:'name',
      					    fieldLabel: '姓名',
      					    name: 'name',
      					    value:'',
      					    allowBlank:true,
      					    anchor:'95%'
      					}
      			    ,
  					{
					    xtype:'textfield',
					    id:'qq',
					    fieldLabel: 'qq',
					    name: 'name',
					    value:'',
					    allowBlank:true,
					    anchor:'95%'
					}]
      			 },
	    		 {
	    			 columnWidth:.2,
	    			   layout:'form',
	    			   items:[
							applyCombo,
							{
							    xtype:'textfield',
							    id:'eval_id',
							    fieldLabel: '评测编号',
							    name: 'name',
							    value:'',
							    allowBlank:true,
							    anchor:'95%'
							}
					    ] 
	    		 },
    			 {
    			   columnWidth:.2,
    			   layout:'form',   
    			   border:true,
    			   items:[
    			   			{		    			   		
    			   				xtype:'button',
    			   				text:' 查 询  ',
    			   				width:70,
    			   				handler:function(){
    			   					var adviserName = Ext.getCmp('name').getValue()  ;
    			   					var disciplineId = discipline_combo.getValue() ;
    			   					//adviserGrid.getStore().load({params:{'name': adviserName,'disciplineId':disciplineId}}) ;
    			   					squadStore.reload({params:{'name': adviserName,'disciplineId':disciplineId,'start':0,'limit':20}}) ;
    			   				}
    			   			},
    			   			{		    			   		
    			   				xtype:'button',
    			   				text:' 重 置  ',
    			   				width:70,
    			   				handler:function(){
    			   					
    			   				}
    			   			}
    			   	]
    			 }
    		  ]
    		}
    	]
    }) ;

    
    
	
    var viewport = new Ext.Viewport({
    		layout:'border',
    		items: [
					form_query,
					grid
   				]
    });
    store.load({params:{'start':0,'limit':20}});
  
});   					
  </script>
</head>
<body>

</body>
</html>