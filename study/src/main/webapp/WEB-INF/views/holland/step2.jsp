<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta content="text/html;charset=UTF-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step2.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/question.js" charset="UTF-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	   createTrueFalseQuestion($('#questions1') , questions1 );
	   createTrueFalseQuestion($('#questions2') , questions2 ) ;
	   createTrueFalseQuestion($('#questions3') , questions3 ) ;
	   createTrueFalseQuestion($('#questions4') , questions4 ) ;
	   createTrueFalseQuestion($('#questions5') , questions5 ) ;
	   createTrueFalseQuestion($('#questions6') , questions6 ) ;
	   
	   
	   
	  
	
	       for(var i=1; i<7 ;i++){
	    	   $('#subitem'+i).click(function(e) {
	    		   var id = $(this).attr('id').replace("subitem","") ;
	    		   $('#questions1').hide() ;
	    		   $('#questions2').hide() ;
	    		   $('#questions3').hide() ;
	    		   $('#questions4').hide() ;
	    		   $('#questions5').hide() ;
	    		   $('#questions6').hide() ;
	    		   $('#questions' +id).show(); 
	    		   $('#questions' +id).animate({scrollTop:$('#questions' +id).offset().top}, 500);
	    	       
	    	   }) ;
	    	   
	       }
		   
	      $('#navi-button1').click(function(e) {
	    	    window.location.href = "main.do?evId=${evId}" ;
		    })
	  
	  
	    
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   
  	<div id="test" class="container-fluid">
  		<h1 class="page-header" style="text-align:center;">二.	您所擅长获胜的活动</h1>
  		<form action="<%=request.getContextPath()%>/holland/step3.do" method="post" onsubmit="">
		<input type="hidden" name="evId" value="${evId}"/>
		<div id="part1" class="container">
		 	<div class="row">
		 	   下面列举了若干种活动，其中你能做或大概能做的事，请选“是”；反之，请选“否”。请按顺序回答全部问题。
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
	 	       <p id="subitem3" class="bg-primary">I：调研型活动</p>
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
	 </div>
	 	
	 
	 <div class="container">
	 	<button type="button" class="btn btn-default btn-lg active"  id="navi-button1">上一步</button>
	    <input type="submit" class="btn btn-primary btn-lg active"  id="navi-button2" value="下一步"/>
	 </div>
	 </form>	 
	


</body>
</html>
