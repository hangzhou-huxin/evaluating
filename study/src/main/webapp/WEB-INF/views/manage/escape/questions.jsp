<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逆袭指数题配置</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">

  questionId = 0 ;  //当前选中的题
  Ext.onReady(function(){
		
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
   		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   	
   		var dimension_combo = new Ext.form.ComboBox({
   	  	  id:'dimension-combo',
   	  	  fieldLabel:'维度',
   	  	  typeAhead:true,
   	  	  triggerAction:'all',
   	  	  lazyRender:true,
   	  	  mode:'remote',
   	  	  store:new Ext.data.Store({
   	  		  proxy:new Ext.data.HttpProxy({
   	  			  url:'<%=request.getContextPath()%>/manage/escape/dimension/list.do?categoryId=${category.id}'
   	  		  }),
   	  		  reader:new Ext.data.JsonReader({
   	  			  root:'data'
   	  		  },[{name:'id'},{name:'name'}])
   	  	  }),
   	  	  valueField:'id',
   	  	  hiddenName:'dimensionId',
   	  	  displayField:'name',
   	  	  allowBlank:false
   	    }) ;
   		
   		dimension_combo_store = new Ext.data.Store({
  		  proxy:new Ext.data.HttpProxy({
  			  url:'<%=request.getContextPath()%>/manage/escape/dimension/list.do?categoryId=${category.id}'
  		  }),
  		  reader:new Ext.data.JsonReader({
  			  root:'data'
  		  },[{name:'id'},{name:'name'}])
  	  });
   
    var renderOptions = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='javascript:showOptionsWin(\""+ record.data['id']+"\")' onclick=''>查看或编辑</a>";
        return s;
    }; 
    
    var renderDetail = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='<%=request.getContextPath()%>/manage/holland/gotoStep.do?step=1&evId=" + record.data['id'] +"' target='_blank' onclick=''>查看或编辑</a>";
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
    
    
    
    var renderResult = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='#' onclick='genResultUpdate(\""+ rowIndex +"\")'>修改</a>" ;
            s+= "     <a  href='javascript:genResultDelete(\""+record.data['id'] +"\")' onclick=''>删除</a>" ;
        //alert(s) ;
            return s;
    }; 
  

    var renderEdit = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='#' onclick='genResultUpdate(\""+ rowIndex +"\")'>修改</a>" ;
        s +=  "  <a  href='javascript:genResultDelete(\"question\",\""+record.data['id'] +"\")' onclick=''>删除</a>";
         return s;
    }; 
    
    var renderOptionEdit = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	 var s = "<a  href='#' onclick='genOptionResultUpdate(\""+ rowIndex +"\")'>修改</a>" ;
         s +=  "  <a  href='javascript:genResultDelete(\"option\",\""+record.data['id'] +"\")' onclick=''>删除</a>";
         return s;
    }; 
    
    //更新选项
    genOptionResultUpdate = function(rowIndex){
    	var record = optionStore.getAt(rowIndex) ;
    	showAddWin("更新" , record.data.id) ;
		 if(record){
        	var values = [{id:'id',value:record.data.id},
    				 	  {id:'index',value:record.data.index},
    				 	  {id:'title',value:record.data.title},
    				 	  {id:'content',value:record.data.content},
    				 	  {id:'value',value:record.data.value}] ;
        	optionFormPanel.getForm().setValues(values) ;
        }else{
        	optionFormPanel.getForm().reset();
        }
    	
    }
    
    //更新题
    genResultUpdate = function(rowIndex){
    	var record = store.getAt(rowIndex) ;
    	showWin("更新" , record.data.id) ;
		 if(record){
        	var values = [{id:'id',value:record.data.id},
    				 	  {id:'index',value:record.data.index},
    				 	  {id:'content',value:record.data.content},
    				 	  {id:'dimensionId',value:record.data['dimensionId']}
    				 	 ] ;
        	formPanel.getForm().setValues(values) ;
        }else{
        	formPanel.getForm().reset();
        }
    	
    }
    
    genResultDelete = function(type,id){
   		Ext.Msg.confirm('','确定删除吗?',function(button){
  			if(button == "yes"){
  				var url = "" ;
  				if( type == "question"){
  					url = '<%=request.getContextPath()%>/manage/escape/questions/delete.do' ;
  				}else{
  					url = '<%=request.getContextPath()%>/manage/escape/options/delete.do' ;
  				}
  				Ext.Ajax.request({
  				   url: url,
  				   success: function(response,options){
			 			var msg = Ext.decode(response.responseText) ;
			   			Ext.Msg.alert('',msg.msg) ;
			   			if(type == "question")
			   				grid.getStore().reload(); 
			   			else
			   				optionStore.load({params:{'questionId':questionId}}) ;
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
   	      										{header:'题索引',dataIndex:'index',sortable:false},
   	      										{header:'题内容',dataIndex:'content',sortable:false},
   	      										{header:'所属维度',dataIndex:'dimensionName',sortable:false},
   	      										{header:'题选项维护',dataIndex:'questionId',renderer:renderOptions,sortable:false},
   	      										{header:'查看和编辑',dataIndex:'id',renderer:renderDetail,sortable:false},
   	      										{header:'删除',dataIndex:'id',renderer:renderEdit,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/escape/questions/list.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'title'},
          		{name:'content'},
          		{name:'index'},
          		{name:'categoryId'},
          		{name:'dimensionId'},
          		{name:'dimensionName'}
          	  ]),
         
      	  remoteSort:true
      });
      
    
      
     
      var grid = new Ext.grid.GridPanel({
      	title:'${category.name}',
      	store:store,
      	region:'center',
      	width:300,
      	cm:cm,
      	sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
        tbar:[
				{
					text:'新增题>>>',
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
    var categoryId = ${category.id}	 ;
    var win ;
    var formPanel = new Ext.FormPanel({
      id:'form',
      width:300,
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
						 xtype:'hidden',
						 id:'categoryId',
						 name:'categoryId',
						 value:'${category.id}'
					},
					{
						xtype:'textfield',
						name:'index',
						value:'',
						fieldLabel :'题索引',
						width:300,
						allowBlank:false
					},
					{
						xtype:'textarea',
						name:'content',
						value:'',
						fieldLabel :'题内容',
						width:300,
						height:100,
						allowBlank:false
					},
					dimension_combo
			],
			buttonAlign:'center',
	    	buttons:[
	    		{
	    			text:'保存',
	    			id:'btn_save_edit',
	    			handler:function(){
	    				var categoryId = Ext.getCmp('categoryId').getValue()  ;
	    				if(!categoryId){
	    					Ext.Msg.alert('','未绑定类别!') ;
	    					return ;
	    				}
	    				formPanel.getForm().submit({
			    			    clientValidation: true,
			    			    url: '<%=request.getContextPath()%>/manage/escape/questions/save.do',
			    			    params :{ },
			    			    success: function(form, action) {
			    			    	store.reload() ;
			    			    	Ext.Msg.alert('', action.result.msg);
			    			    	win.hide();
			    			    },
			    			    failure: function(form, action) {
			    			        switch (action.failureType) {
			    			            case Ext.form.Action.CLIENT_INVALID:
			    			                Ext.Msg.alert('', '请输入完整');
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
    	win.setTitle(title) ;
    	win.show();
  }  
 	 //---------------------------查看或修改处理信息---end------------------------------------- 
 	
 	 
 	 //---------------------------题选项维护---begin-------------------------------------
 	var optionWin ;
	var optionCm = new Ext.grid.ColumnModel([ //new Ext.grid.RowNumberer(),
     										{header:'选项索引',width:10,dataIndex:'index',sortable:false},
     										{header:'选项内容',dataIndex:'content',sortable:false},
     										{header:'选项分值',dataIndex:'value',sortable:false},
     										{header:'删除',dataIndex:'id',renderer:renderOptionEdit,sortable:false}
     										]);



	var optionStore = new Ext.data.Store({
		  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/escape/options/list.do?time=' + (new Date).getTime()}),
		  reader:new Ext.data.JsonReader({
				totalProperty:'totalCount',
				root:'data'
	  },[
	     	{name:'id'},
	  		{name:'content'},
	  		{name:'index'},
	  		{name:'questionId'},
	  		{name:'value'}
	  	  ]),
	  //baseParams :[{questionId:'${questionId}'}],
		  remoteSort:true
	});
	
    var optionPanel = new Ext.grid.GridPanel({
      	title:'"选项列表',
      	store:optionStore,
      	region:'center',
      	width:300,
      	cm:optionCm,
      	sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
        tbar:[
				{
					text:'新增选项',
					tooltip:'',
					iconCls:'add',
					handler:function(){
						showAddWin('新增');
						//formPanel.getForm().reset();
					}
				}
		],
       	viewConfig: {
        	 forceFit: true
       	}
      })  ;
    
    var optionFormPanel = new Ext.FormPanel({
        id:'optionForm',
        width:300,
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
  						name:'index',
  						value:'',
  						fieldLabel :'选项索引',
  						width:300,
  						allowBlank:false
  					},
  					{
  						xtype:'textarea',
  						name:'content',
  						value:'',
  						fieldLabel :'选项内容',
  						width:300,
  						height:100,
  						allowBlank:false
  					},
  					{
  						xtype:'textfield',
  						name:'value',
  						value:'',
  						fieldLabel :'分值',
  						width:300,
  						allowBlank:false
  					}
  			],
  			buttonAlign:'center',
  	    	buttons:[
  	    		{
  	    			text:'保存选项',
  	    			id:'btn_save_option_edit',
  	    			handler:function(){
  	    				if(!questionId){
	    					Ext.Msg.alert('','未绑定题!') ;
	    					return ;
	    				}
  	    				optionFormPanel.getForm().submit({
			    			    clientValidation: true,
			    			    url: '<%=request.getContextPath()%>/manage/escape/options/save.do',
			    			    params :{'questionId':questionId },
			    			    success: function(form, action) {
			    			    	optionStore.load({params:{'questionId':questionId}}) ;
			    			    	Ext.Msg.alert('', action.result.msg);
			    			    	addWin.hide();
			    			    },
			    			    failure: function(form, action) {
			    			        switch (action.failureType) {
			    			            case Ext.form.Action.CLIENT_INVALID:
			    			                Ext.Msg.alert('', '请输入完整');
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
    
    var addWin ;
    showAddWin=function(title,id){
	    if(!id){
	    	optionFormPanel.getForm().reset(); 
	    }
    	if (!addWin) {
    		addWin = new Ext.Window({
				id:'addWin',
		        title: '',
		        width: 350,
		        height:300,
		        layout: 'fit',
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items: [
							optionFormPanel
		                ],
		        closable: true,
		        closeAction:'hide',
              	plain: true
		    }) ;
		}
    	addWin.setTitle(title) ;
    	addWin.show();
  	}  
 	
    showOptionsWin=function(id){
    	questionId = id ;
    	//Ext.getCmp('questionId').setValue(questionId) ;
    	if (!optionWin) {
    		optionWin = new Ext.Window({
				id:'optionWin',
		        title: '',
		        width: 450,
		        height:300,
		        layout: 'fit',
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items: [
							optionPanel,
		                ],
		        closable: true,
		        closeAction:'hide',
              	plain: true
		    }) ;
		}
    	optionStore.load({params:{'questionId':questionId}}) ;
    	optionWin.show();
  }  
 	 
 	 //---------------------------题选项维护---end-------------------------------------
	
    var viewport = new Ext.Viewport({
    		layout:'border',
    		items: [
    		        grid
   				]
    });
    store.load({params:{'categoryId':categoryId}}) ;
    dimension_combo_store.load();
	
  
});   					
  </script>
</head>
<body>

</body>
</html>