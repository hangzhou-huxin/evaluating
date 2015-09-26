<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评估策略列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">
  Ext.onReady(function(){
		
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
   		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   	
   		var top = new Ext.FormPanel({
   	        labelAlign: 'top',
   	        frame:true,
   	        title: 'Multi Column, Nested Layouts and Anchoring',
   	        bodyStyle:'padding:5px 5px 0',
   	        width: 600,
   	        items: [
	   	          	{
		   	            xtype:'htmleditor',
		   	            id:'introduction',
		   	            name:'introduction',
		   	            fieldLabel:'前言',
		   	            height:100,
		   	            anchor:'98%'
	   	        	},
	   	        	{
	   	        		xtype:'htmleditor',
		   	            id:'specialty',
		   	            name:'specialty',
		   	            fieldLabel:'特点',
		   	            height:100,
		   	            anchor:'98%'
	   	        	},
	   	        	{
	   	        		xtype:'htmleditor',
		   	            id:'suggest',
		   	            name:'suggest',
		   	            fieldLabel:'职业建议',
		   	            height:100,
		   	            anchor:'98%'
	   	        	},
	   	        	{
	   	        		xtype:'htmleditor',
		   	            id:'depth_profile',
		   	            name:'depthProfile',
		   	            fieldLabel:'深度剖析',
		   	            height:100,
		   	            anchor:'98%'
	   	        	}
   	        ],

   	        buttons: [{
   	            text: 'Save'
   	        },{
   	            text: 'Cancel'
   	        }]
   	    });

	
	    var viewport = new Ext.Viewport({
	    		layout:'fit',
	    		items: [
					top
	   			]
	    });
});   					
  </script>
</head>
<body>

</body>
</html>