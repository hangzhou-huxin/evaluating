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
		var questions = ${questions} ;
		var cache = ${cache} ;
	   if( !cache){
		   cache = {} ;
	   }
	   createQuestion($('#questions') , questions ,cache);
	   
	   
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 80%;">
   
  	<div id="test" class="container-fluid">
  		<form id="form" action="<%=request.getContextPath()%>/holland/step2.do" method="post" onsubmit="return validateForm() ;">
			<input type="hidden" name="evId" value="${evId}"/>
			<input type="hidden" name="step" value="${step}"/>
			<input type="hidden" name="stepCount" value="${stepCoutn}"/>
			<input type="hidden" name="categoryId" value="${categoryId}"/>
			<div class="container">
	 	       <div id="questions" class="row" style=""></div>
	 	    </div>
	 		<div class="container">
	    		<input id="navi-next-button" type="submit" class="btn btn-primary btn-lg active" value="完成"/>
	 		</div>
	 	</form>
	 </div>
</body>
</html>