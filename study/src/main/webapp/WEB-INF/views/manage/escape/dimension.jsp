<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维度配置</title>
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
        var s = "<a  href='<%=request.getContextPath()%>/manage/escape/category/editQuestions.do?id=" + record.data['id'] +"' target='_blank' onclick=''>查看或编辑</a>";
         return s;
    }; 
    
    
    
  

    var renderEdit = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='#' onclick='genResultUpdate(\""+ rowIndex +"\")'>修改</a>" ;
        
    	s +=  "  <a  href='javascript:genResultDelete(\""+record.data['id'] +"\")' onclick=''>删除</a>";
         return s;
    }; 
    
  //更新类别
    genResultUpdate = function(rowIndex){
    	var record = grid.getStore().getAt(rowIndex) ;
    	showWin("更新" , record.data.id) ;
		 if(record){
        	var values = [{id:'id',value:record.data.id},
    				 	  {id:'name',value:record.data.name}] ;
        	formPanel.getForm().setValues(values) ;
        }else{
        	formPanel.getForm().reset();
        }
    	
    }
  
    
    genResultDelete = function(id){
   		Ext.Msg.confirm('','确定删除吗?',function(button){
  			if(button == "yes"){
  				Ext.Ajax.request({
  				   url: '<%=request.getContextPath()%>/manage/escape/dimension/delete.do',
  				   success: function(response,options){
			 			var msg = Ext.decode(response.responseText) ;
			   			Ext.Msg.alert('',msg.msg) ;
			   			grid.getStore().load({params:{'categoryId':'${category.id}'}}); ;
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
   	      										{header:'维度名称',dataIndex:'name',sortable:false},
   	      										{header:'维护',dataIndex:'id',renderer:renderEdit,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/escape/dimension/list.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'name'}
          	  ]),
      	  remoteSort:true
      });

      
     
      var grid = new Ext.grid.GridPanel({
      	title:'${category.name}-维度配置',
      	store:store,
      	region:'center',
      	width:300,
      	cm:cm,
      	sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
        tbar:[
				{
					text:'新增>>>',
					tooltip:'',
					iconCls:'add',
					handler:function(){
						showWin("新增");
						formPanel.getForm().reset();
					}
				}
		],
       	viewConfig: {
        	 forceFit: true
       	}
      })  ;
      

  //---------------------------查看或修改处理信息---begin-------------------------------------
    var win ;
    var formPanel = new Ext.FormPanel({
      id:'form',
      width:200,
      height:70,
      frame:true,
      labelWidth: 100,
      items:[
					{ 
						 xtype:'hidden',
						 name:'id',
						 value:''
					},
					{ 
						 xtype:'hidden',
						 name:'categoryId',
						 value:'${category.id}'
					},
					{
						xtype:'textfield',
						name:'name',
						value:'',
						fieldLabel :'维度名称',
						width:200
					}
			],
			buttonAlign:'center',
	    	buttons:[
	    		{
	    			text:'保存',
	    			id:'btn_save_edit',
	    			handler:function(){
	    				formPanel.getForm().submit({
			    			    clientValidation: true,
			    			    url: '<%=request.getContextPath()%>/manage/escape/dimension/save.do',
			    			    params :{ },
			    			    success: function(form, action) {
			    			    	store.load({params:{'categoryId':'${category.id}'}}) ;
			    			    	Ext.Msg.alert('', action.result.msg);
			    			    	win.hide();
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
    
 	showWin=function(title, id){
 		if(!id){
 			formPanel.getForm().reset();
 		}
 		
    	if (!win) {
    		win = new Ext.Window({
				id:'win',
		        title: title,
		        width: 350,
		        height:300,
		        layout: 'fit',
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items: [
							formPanel
		                ],
		        closable: true,
		        closeAction:'hide',
              	plain: true
		    }) ;
		}
    	
    	win.show();
  }  
 	 //---------------------------查看或修改处理信息---end-------------------------------------   
	
    var viewport = new Ext.Viewport({
    		layout:'border',
    		items: [
					grid
   				]
    });
    store.load({params:{'categoryId':'${category.id}'}});
  
});   					
  </script>
</head>
<body>

</body>
</html>