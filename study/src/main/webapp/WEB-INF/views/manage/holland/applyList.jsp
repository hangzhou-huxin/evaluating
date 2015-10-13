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
    	var s = "" ;
    	if( record.data['applyId'] ){
    		if( record.data['isProcessed'] == 0)
    		  s = "<a  href='javascript:showProcessWin(\""+ record.data['applyId']+"\")' onclick=''>处理</a>";
    	}	
        return s;
    }; 
    
    
    
  

  
    
   
   	var cm = new Ext.grid.ColumnModel([ //new Ext.grid.RowNumberer(),
   	      										{header:'序号',dataIndex:'id',sortable:false},
   	      										{header:'评测编号',dataIndex:'evaluationId',sortable:false},
   	      										{header:'姓名',dataIndex:'name',sortable:false },
   	      										{header:'qq',dataIndex:'qq',sortable:false},
   	      										{header:'ip地址',dataIndex:'ip',sortable:false},
   	      										{header:'评测时间',dataIndex:'createDate',sortable:false},
   	      										{header:'评测报告',dataIndex:'id',renderer:renderReport,sortable:false},
   	      										{header:'评测明细',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'处理意见',dataIndex:'id',renderer:renderProcess,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/holland/queryForApply.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			//totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'evaluationId'},
          		{name:'name'},
          		{name:'qq'},
          		{name:'createDate'},
          		{name:'ip'},
          		{name:'applyId'},
          		{name:'isProcessed'}
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
		            ]
      })  ;
      
      
      
    

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
			    			    	store.reload();	
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
					grid
   				]
    });
    store.load();
  
});   					
  </script>
</head>
<body>

</body>
</html>