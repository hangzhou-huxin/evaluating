<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逆袭指数申请结果查询</title>
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
   	      										{header:'评测编号',dataIndex:'evId',sortable:false},
   	      										{header:'姓名',dataIndex:'name',sortable:false },
   	      										{header:'专业',dataIndex:'profession',sortable:false},
   	      										{header:'入学年份',dataIndex:'schoolYear',sortable:false},
   	      										{header:'qq',dataIndex:'qq',sortable:false},
   	      										{header:'备注',dataIndex:'memo',sortable:false},
   	      										{header:'ip地址',dataIndex:'ip',sortable:false},
   	      										{header:'职业',dataIndex:'career',sortable:false},
   	      										{header:'查看',dataIndex:'id',renderer:renderReport,sortable:false},
   	      										{header:'处理',dataIndex:'id',renderer:renderProcess,sortable:false}
   	      										]);
   	
      
      
      var store = new Ext.data.Store({
      	  proxy:new Ext.data.HttpProxy({url:'<%=request.getContextPath()%>/manage/escape/apply/list.do?time=' + (new Date).getTime()}),
      	  reader:new Ext.data.JsonReader({
      			//totalProperty:'totalCount',
      			root:'data'
          },[
             	{name:'id'},
          		{name:'evId'},
          		{name:'name'},
          		{name:'profession'},
          		{name:'schoolYear'},
          		{name:'qq'},
          		{name:'createDate'},
          		{name:'ip'},
          		{name:'applyId'},
          		{name:'isProcessed'},
          		{name:'memo'},
          		{name:'career'}
          	  ]),
      	  remoteSort:true
      });

      
     
      var grid = new Ext.grid.GridPanel({
      	title:'逆袭指数申请列表',
      	store:store,
      	region:'center',
      	width:300,
      	cm:cm,
      	sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
          bbar:[
		            ]
      })  ;
      
      
      
    

  //---------------------------查看或修改处理信息---begin-------------------------------------
    
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
     	    width:150,
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
    		
    			categoryCombo ,
 			   			{		    			   		
 			   				xtype:'button',
 			   				text:' 查 询  ',
 			   				width:70,
 			   				handler:function(){
 			   					var categoryId = categoryCombo.getValue() ;
 			   					//var process = Ext.getCmp('process').getValue() ;
 			   					store.baseParams = {'categoryId':categoryId } ;
 			   					store.reload({params:{'start':0,'limit':20}}) ;
 			   				}
 			   			}
    			   	
    			 
    	]
    }) ;
 	 //---------------------------查看或修改处理信息---end-------------------------------------   
	
    var viewport = new Ext.Viewport({
    		layout:'border',
    		items: [
					form_query,
					grid
   				]
    });
    //store.load();
  
});   					
  </script>
</head>
<body>

</body>
</html>