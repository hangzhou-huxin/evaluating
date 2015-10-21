<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逆袭指数-评估结果查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">
  Ext.onReady(function(){
		
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
   		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   	
   
   
    var renderReport = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='<%=request.getContextPath()%>/manage/escape/viewReport.do?evId=" + record.data['evalId'] +"' target='_blank' onclick=''>查看</a>";
        return s;
    }; 
    
    var renderDetail = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='<%=request.getContextPath()%>/manage/escape/step.do?evId=" + record.data['evalId'] +"' target='_blank' onclick=''>查看</a>";
         return s;
    }; 
    
    var renderProcess = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "" ;
    	if( record.data['applyId'] ){
    		if( record.data['isProcessed'] == 1)
    		  s = "<a  href='javascript:showProcessWin(\""+ record.data['applyId']+"\")' onclick=''>查看</a>";
    		else
    		  s = "未处理" ;
    	}else{
    		s = "<font color='red'>无申请</font>"
    	}
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
  				   url: '<%=request.getContextPath()%>/manage/escape/result/delete.do',
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
   	      										{header:'评测编号',dataIndex:'evalId',sortable:false},
   	      										{header:'姓名',dataIndex:'userName',sortable:false },
   	      										{header:'qq',dataIndex:'qq',sortable:false},
   	      										{header:'总得分',dataIndex:'totalScore',sortable:false},
   	      										{header:'ip地址',dataIndex:'ip',sortable:false},
   	      										{header:'评测时间',dataIndex:'createDate',sortable:false},
   	      										{header:'评测报告',dataIndex:'id',renderer:renderReport,sortable:false},
   	      										{header:'评测明细',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'处理意见',dataIndex:'id',renderer:renderProcess,sortable:false},
   	      										{header:'常规操作',dataIndex:'id',renderer:renderEdit,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/escape/query.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'evalId'},
          		{name:'userName'},
          		{name:'qq'},
          		{name:'createDate'},
          		{name:'ip'},
          		{name:'applyId'},
          		{name:'isProcessed'},
          		{name:'totalScore'}
          	  ]),
      	  remoteSort:true
      });

      
     
      var grid = new Ext.grid.GridPanel({
      	title:'逆袭指数-评测列表',
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
      
      var processCombo = new Ext.form.ComboBox({ 
  	    id:'process' ,
   	    typeAhead: true,
   	    triggerAction: 'all',
   	    lazyRender:false,
   	    fieldLabel:'是否处理',
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
   	 	hiddenName:'isProcess'
   	});
      
      category_combo_store = new Ext.data.Store({
  		  proxy:new Ext.data.HttpProxy({
  			  url:'<%=request.getContextPath()%>/manage/escape/category/list.do'
  		  }),
  		  reader:new Ext.data.JsonReader({
  			  root:'data'
  		  },[{name:'id'},{name:'name'}])
  	  });
      
      var categoryCombo = new Ext.form.ComboBox({ 
    	    id:'process' ,
     	    fieldLabel:'类别',
     	    typeAhead:true,
    	  	triggerAction:'all',
    	  	lazyRender:true,
    	  	mode:'remote',
     	    width:50,
     	    store: category_combo_store,
     	    valueField: 'id',
     	    displayField: 'name',
     	 	hiddenName:'categoryId'
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
    			   columnWidth:.2,
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
      					    name: 'userName',
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
    			   columnWidth:.3,
    			   layout:'form',   
    			   border:true,
    			   items:[
							categoryCombo ,
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
    			   					var categoryId = categoryCombo.getValue() ;
    			   					//var process = Ext.getCmp('process').getValue() ;
    			   					store.baseParams = {'categoryId':categoryId,'evalId':evalId,'userName': name,'startdt':startdt,'enddt':enddt,'qq':qq,'apply':apply } ;
    			   					store.reload({params:{'start':0,'limit':20}}) ;
    			   				}
    			   			}
    			   	]
    			 }
    		  ]
    		}
    	]
    }) ;

  //---------------------------查看或修改处理信息---begin-------------------------------------
    var processWin ;
    var processFormPanel = new Ext.FormPanel({
      id:'process-form',
      width:200,
      height:370,
      frame:true,
      labelWidth: 100,
      items:[
					{ 
						 xtype:'hidden',
						 name:'id',
						 value:''
					},
					{
						xtype:'textfield',
						name:'name',
						value:'',
						disabled :true,
						fieldLabel :'姓名',
						width:200
					},
					{
						xtype:'textfield',
						name:'qq',
						value:'',
						disabled :true,
						fieldLabel :'qq',
						width:200
					},
					{
						xtype:'textfield',
						name:'profession',
						value:'',
						disabled :true,
						fieldLabel :'专业',
						width:200
					},
					{
						xtype:'textfield',
						name:'schoolYear',
						value:'',
						disabled :true,
						fieldLabel :'入学年份',
						width:200
					},
					{
						xtype:'textfield',
						name:'career',
						value:'',
						disabled :true,
						fieldLabel :'职业',
						width:200
					},
					{
						xtype:'textfield',
						name:'educationName',
						value:'',
						disabled :true,
						fieldLabel :'学历',
						width:200
					},
					{
						xtype:'textarea',
						name:'memo',
						value:'',
						disabled :true,
						fieldLabel :'备注',
						width:200,
						height:100
					},
					{
						xtype:'textarea',
						name:'processInfo',
						value:'',
						fieldLabel :'处理意见',
						width:200,
						height:100
					}
			],
			buttonAlign:'center',
	    	buttons:[
	    		{
	    			text:'处理或修改',
	    			id:'btn_save_edit',
	    			handler:function(){
	    				processFormPanel.getForm().submit({
			    			    clientValidation: true,
			    			    url: '<%=request.getContextPath()%>/common/apply/saveProcess.do',
			    			    params :{ },
			    			    success: function(form, action) {
			    			    	Ext.Msg.alert('', action.result.msg);
			    			    },
			    			    failure: function(form, action) {
			    			        switch (action.failureType) {
			    			            case Ext.form.Action.CLIENT_INVALID:
			    			                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
			    			                break;
			    			            case Ext.form.Action.CONNECT_FAILURE:
			    			                Ext.Msg.alert('处理失败', '发生通信错误');
			    			                break;
			    			            case Ext.form.Action.SERVER_INVALID:
			    			               Ext.Msg.alert('Failure', action.result.msg);
			    			       }
			    			    }
			    		}); 
		    				
	    			}				    			
	    		}
	    	]
  });
    
 	showProcessWin=function( applyId){
 		//查询申请信息
 		Ext.Ajax.request({
  				   url: '<%=request.getContextPath()%>/common/apply/view.do',
  				   success: function(response,options){
			 			var result = Ext.decode(response.responseText) ;
			 			processFormPanel.getForm().setValues(result.data) ;
			   			//Ext.Msg.alert('',result.data.name) ;
					},
				   failure: function(){
					 	Ext.Msg.alert('','通信错误') ;
				   },
  				   headers: {
  				   },
  				   params: { applyId: applyId }
  		});
    	if (!processWin) {
    		processWin = new Ext.Window({
				id:'processWin',
		        title: '',
		        width: 350,
		        height:300,
		        layout: 'fit',
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items: [
							processFormPanel
		                ],
		        closable: true,
		        closeAction:'hide',
              plain: true
		    }) ;
		}
    	
    	processWin.show();
  }  
 	 //---------------------------查看或修改处理信息---end-------------------------------------   
	
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