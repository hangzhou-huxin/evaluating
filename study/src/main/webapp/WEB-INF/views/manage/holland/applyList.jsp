<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>霍兰德深度报告申请处理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
<script type="text/javascript">
  Ext.onReady(function(){
		
		Ext.BLANK_IMAGE_URL='<%=request.getContextPath()%>/ext/resources/images/default/s.gif';
		Ext.QuickTips.init();
   		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
   	
   
   
    var renderReport = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='<%=request.getContextPath()%>/estimate/teaching/viewResult.do?deployId=" + record.data['id'] +"' target='_blank' onclick=''>查看</a>";
        return s;
    }; 
    
    var renderDetail = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	//alert(Ext.util.Format.date(new Date(parseInt(record.data.deployDate)),'Y-m-d')) ;
        var s = "<a  href='javascript:showSelStrategyWin(\""+rowIndex+"\")' onclick=''>查看</a>";
         return s;
    }; 
    
    var renderProcess = function(value,cellmeta,record,rowIndex,columnIndex,store){
    	var s = "<a  href='<%=request.getContextPath()%>/estimate/teaching/viewResult.do?deployId=" + record.data['id'] +"' target='_blank' onclick=''>处理</a>";
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
   	      										{header:'操作',dataIndex:'id',renderer:renderProcess,sortable:false}
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
      	title:'霍兰德深度报告申请列表',
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
      
      
      
    
    
    
	
    var viewport = new Ext.Viewport({
    		layout:'border',
    		items: [
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