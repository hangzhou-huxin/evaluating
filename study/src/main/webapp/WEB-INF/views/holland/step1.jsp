<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step1.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/question.js" charset="UTF-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	   var cache = ${cache} ;
	   if( !cache){
		   cache = {} ;
		   
	   }
	   createTrueFalseQuestion($('#questions1') , questions1 ,cache);
	   createTrueFalseQuestion($('#questions2') , questions2 ,cache) ;
	   createTrueFalseQuestion($('#questions3') , questions3 ,cache) ;
	   createTrueFalseQuestion($('#questions4') , questions4 ,cache) ;
	   createTrueFalseQuestion($('#questions5') , questions5 ,cache) ;
	   createTrueFalseQuestion($('#questions6') , questions6 ,cache) ;
	   
	   setSubitemClickEvent(); 
	        
	    $('#navi-next-button').click(function(){
	    	if(!validateQuestions(questions1)){
	    		showSubitem(1) ;
	    		alert("请输入完整") ;
	    		return  false ;
	    	}
	    	if(!validateQuestions(questions2)){
	    		showSubitem(2) ;
	    		alert("请输入完整") ;
	    		return  false;
	    	}if(!validateQuestions(questions3)){
	    		showSubitem(3) ;
	    		alert("请输入完整") ;
	    		return  false ;
	    	}if(!validateQuestions(questions4)){
	    		showSubitem(4) ;
	    		alert("请输入完整") ;
	    		return  false;
	    	}if(!validateQuestions(questions5)){
	    		showSubitem(5) ;
	    		alert("请输入完整") ;
	    		return  false;
	    	}if(!validateQuestions(questions6)){
	    		showSubitem(6) ;
	    		alert("请输入完整") ;
	    		return  false ;
	    	}
	    }) ;
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   
  	<div id="test" class="container-fluid">
  		<h1 class="page-header" style="text-align:center;">一.	您所感兴趣的活动</h1>
  		<form id="form" action="<%=request.getContextPath()%>/holland/step2.do" method="post" onsubmit="return validateForm() ;">
			<input type="hidden" name="evId" value="${evId}"/>
		<div id="part1" class="container">
		 	<div class="row">
		 	  下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请选“是”，反之，请选“否”，请按顺序回答全部问题。
		 	</div>
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem1" class="bg-primary">R：实际型活动</p>
			   <div id="questions1" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem2" class="bg-primary">A：艺术型活动</p>
			   <div id="questions2" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem3" class="bg-primary">I：调查型活动</p>
			   <div id="questions3" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem4" class="bg-primary">S：社会型活动</p>
			   <div id="questions4" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem5" class="bg-primary">E：事业型活动</p>
			   <div id="questions5" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem6" class="bg-primary">C：常规型活动</p>
			   <div id="questions6" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 	<div class="container">
	    	<input id="navi-next-button" type="submit" class="btn btn-primary btn-lg active" value="下一步"/>
	 	</div>
	 	</form>
	 </div>
</body>
</html>