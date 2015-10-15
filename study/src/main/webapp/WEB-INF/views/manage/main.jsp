<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逆袭后台管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){
	
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
		
		 var pwdSetFormPanel = new Ext.FormPanel({
	            id:'adviser-form',
	            width:200,
	            height:70,
	            frame:true,
	            labelWidth: 100,
	            items:[
	    				     {
	    				    	 xtype:'textfield',
	    				    	 name:'pwd',
	    				    	 fieldLabel:'新密码',
	    				    	 allowBlank:false
	    				     },
	    				     {
	    				    	 xtype:'textfield',
	    				    	 name:'repwd',
	    				    	 fieldLabel:'重复密码',
	    				    	 allowBlank:false
	    				     }
	    			],
	    			buttonAlign:'center',
	    	    	buttons:[
	    	    		{
	    	    			text:'提交',
	    	    			id:'btn_save_edit',
	    	    			handler:function(){
	    	    				 pwdSetFormPanel.getForm().submit({
	    			    			    clientValidation: true,
	    			    			    url: '<%=request.getContextPath()%>/system/alertPwd.do',
	    			    			    params :{ },
	    			    			    success: function(form, action) {
	    			    			    	Ext.Msg.alert('', action.result.msg);
	    			    			    	pwdSetupWin.hide() ;
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
	    	    		},
	    	    		{
	    	    			text:'重置',
	    	    			id:'btn_reset',
	    	    			handler:function(){
	    	    				pwdSetFormPanel.getForm().reset();
	    	    			}
	    	    		}
	    	    	]
	        });
	        
	        
	      //密码设置窗口
	     	  var pwdSetupWin ; 
	     	  showPwdSetupWin=function(){
	        	if (!pwdSetupWin) {
	        		pwdSetupWin = new Ext.Window({
	    				id:'pwdSetup-win',
	    		        title: '修改密码',
	    		        width: 250,
	    		        height:300,
	    		        layout: 'fit',
	    		        bodyStyle:'padding:5px;',
	    		        buttonAlign:'center',
	    		        items: [
	    		                pwdSetFormPanel
	    		                ],
	    		        closable: true,
	    		        closeAction:'hide',
	                  plain: true
	    		    }) ;
	    		}
	        	pwdSetupWin.show();
	      }
		var tb = new Ext.Toolbar({
    	 	width: Ext.getBody().getWidth(),
    	 	id:'tools',
    	    height: 30,
    	    region: 'north',
    	    items: [
    	       '->',
    	        
    	        {
    	        	xtype:'button',
    	        	text:'修改密码',
    	        	pressed:true,
    	        	handler:function(){
    	        		showPwdSetupWin() ;
    	        	}
    	        },{
    	        	xtype:'button',
    	        	text:'退出登录',
    	        	pressed:true,
    	        	handler:function(){
    	        		 window.location.href = "<%=request.getContextPath()%>/j_spring_security_logout" ;
    	        	}
    	        }
    	       
    	    ]
    });
		
		tree = new Ext.tree.TreePanel({
		        region: 'west',
		        collapsible: true,
		        title: '后台管理',
		        xtype: 'treepanel',
		        width: 200,
		        autoScroll: true,
		        split: true,
		        extend:true,
		        loader: new Ext.tree.TreeLoader(),
		        root: new Ext.tree.AsyncTreeNode({
		            expanded: true,
		            children: [{
			            text: '霍兰德', 
			            cls: 'folder',
			            expanded:true,
			            children: [
			                       {
						                text: '测试结果查询',
						                leaf: true,
						                url: '<%=request.getContextPath()%>/manage/holland/list.do'
			                       },
			                       {
						                text: '深度报告申请处理',
						                leaf: true,
						                url: '<%=request.getContextPath()%>/manage/holland/applyList.do'
			                       }
			            ]
		            },{
			            text: '逆袭指数', 
			            cls: 'folder',
			            expanded:true,
			            children: [
									{
									    text: '测试配置',
									    leaf: true,
									    url:'<%=request.getContextPath()%>/manage/escape/config.do'
									    
									},
									{
									    text: '测试结果查询',
									    leaf: true
									},
									{
									    text: '深度报告申请处理',
									    leaf: true
									}
			            ]
		            }]
		        }),
		        rootVisible: false,
		        listeners: {
		            click: function(n) {
		            	document.getElementById('frame_work').src = n.attributes.url;
		            }
		        }
		    }) ;
		new Ext.Viewport({
		    layout: 'border',
		    items: [tb,tree, {
		        region: 'center',
		        xtype: 'tabpanel',
		        html:'<iframe id="frame_work"  name="frame_work" allowtransparency="true" src="" scrolling="yes" frameborder="0" marginwidth="0" marginheight="0" width="100%" height="100%" onload=""></iframe>'
		    }]
		});
	});
	
</script>
</head>
<body>
sdf
</body>
</html>