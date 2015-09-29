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
    
  

    var renderEdit = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s =  "  <a  href='javascript:genResultDelete(\""+record.data['id'] +"\")' onclick=''>删除</a>";
         return s;
    }; 
    
    genResultDelete = function(id){
   		Ext.Msg.confirm('','确定删除吗?',function(button){
  			if(button == "yes"){
  				Ext.Ajax.request({
  				   url: '<%=request.getContextPath()%>/manage/holland/delete.do',
  				   success: function(response,options){
			 			var msg = Ext.decode(response.responseText) ;
			   			Ext.Msg.alert('',msg.msg) ;
			   			grid.getStore().reload(); ;
					},
				   failure: function(){
					 	Ext.Msg.alert('','通信错误') ;
				   },
  				   headers: {
  				   },
  				   params: { id: id }
  				});
  			}
  			else{
  				
  			}
  		});
    }	
  
   
   	var cm = new Ext.grid.ColumnModel([ //new Ext.grid.RowNumberer(),
   	      										{header:'序号',dataIndex:'id',sortable:false},
   	      										{header:'评测编号',dataIndex:'evaluationId',sortable:false},
   	      										{header:'姓名',dataIndex:'name',sortable:false },
   	      										{header:'qq',dataIndex:'qq',sortable:false},
   	      										{header:'ip地址',dataIndex:'ip',sortable:false},
   	      										{header:'评测时间',dataIndex:'createDate',sortable:false},
   	      										{header:'评测报告',dataIndex:'id',renderer:renderReport,sortable:false},
   	      										{header:'评测明细',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'处理意见',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'常规操作',dataIndex:'id',renderer:renderEdit,sortable:false}
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
          		{name:'createDate'},
          		{name:'ip'}
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
    	    id:'apply' ,
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
    			    	name: 'startdt',
    			        id: 'startdt',
    			        xtype: 'textfield',
    			        fieldLabel: '起始时间',
    			        blankText:'yyyy-mm-dd',
    			        maxLengthText:10,
					    labelWidth:65,
					    allowBlank:true,
					    anchor:'95%'
					},
					{
						name: 'enddt',
				        id: 'enddt',
				        xtype: 'textfield',
				        fieldLabel: '结束时间',
				        blankText:'yyyy-mm-dd',
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
					    name: 'qq',
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
							    name: 'evalId',
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
    			   					var name = Ext.getCmp('name').getValue()  ;
    			   					var startdt = Ext.getCmp('startdt').getValue()  ;
    			   					var enddt = Ext.getCmp('enddt').getValue()  ;
    			   					var qq = Ext.getCmp('qq').getValue() ;
    			   					var apply = applyCombo.getValue() ;
    			   					var evalId = Ext.getCmp('eval_id').getValue() ;
    			   					store.reload({params:{'evalId':evalId,'name': name,'startdt':startdt,'enddt':enddt,'qq':qq,'apply':apply,'start':0,'limit':20}}) ;
    			   				}
    			   			},
    			   			{		    			   		
    			   				xtype:'button',
    			   				text:' 重 置  ',
    			   				width:70,
    			   				handler:function(){
    			   					form_query.getForm().reset()
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