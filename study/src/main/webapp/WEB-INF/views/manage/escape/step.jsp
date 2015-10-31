<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/escape/question.js" charset="UTF-8"></script>
<script type="text/javascript">

   
	$(document).ready(function(){
	   var pageNo = 1; 
	   var pageSize = 10; 
	   var questions = ${questions} ;
	   var size = questions.length  ;
	   var totalPage = Math.floor(size/pageSize) ;
	   if( (size%pageSize) > 0){
		   totalPage = totalPage + 1 ;
		   if(totalPage ==1){
			   $('#navi-next-button').hide() ;
			   $('#navi-previous-button').hide() ;
		   }else{
			   $('#navi-previous-button').hide() ;
			  
		   }
	   }
	   var cache = ${cache} ;
	   if( !cache){
		   cache = {} ;
	   }
	   createQuestion($('#questions') , questions ,cache);
	   showQuestions(pageNo , pageSize ,questions);
	   
	   $('#navi-previous-button').click(function(){
		     
		      if( pageNo > 1){
		    	  pageNo = pageNo -1 ;
		      }
	    	  if(pageNo == 1){
	    		 $('#navi-previous-button').hide();
	    	  }
	    	  
	    	  $('#navi-next-button').show() ; 
	    	  showQuestions(pageNo , pageSize ,questions);
	    }) ;
      
	   $('#navi-next-button').click(function(){
		      
		      if( pageNo < totalPage){
		    	  pageNo = pageNo + 1 ;
		      }
		      
		      if( pageNo ==  totalPage ){
		    	  $('#navi-next-button').hide() ;
		    	  
		      }
		      $('#navi-previous-button').show() ;
		      showQuestions(pageNo , pageSize ,questions) ;
	    }) ;
	   
	   
	   
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 80%;">
    <h3>${category.name}(用户名:${username} ,  qq:${qq})</h3>
  	<div id="test" class="container-fluid">
  		<form id="form" action="<%=request.getContextPath()%>/manage/escape/result/viewReport.do" method="post" onsubmit="">
			<input type="hidden" name="evId" value="${evId}"/>
			<input type="hidden" name="qq" value="${qq}"/>
			<input type="hidden" name="username" value="${username}"/>
			<input type="hidden" name="categoryId" value="${category.id}"/>
			<div id="questions" class="container">
	 	    </div>
	 		<div class="container">
	    		<input type="button" value="上一步" id="navi-previous-button" name="previous" class="btn btn-default btn-lg active"  />
	    		<input type="button" name="next"   id="navi-next-button" class="btn btn-primary btn-lg active"  value="下一步"/>
	    		<input id="finish-button" type="submit"  class="btn btn-primary btn-lg active" value="查看报表"/>
			</div>
	 	</form>
	 </div>
</body>
</html>