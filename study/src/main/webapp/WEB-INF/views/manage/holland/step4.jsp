<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>逆袭</title>
<meta content="text/html;charset=UTF-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step4.js" charset="UTF-8"></script>
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
	        
		    $('#finisth-button,#navi-previous-button').click(function(){
		    	if(!validateQuestions(questions1)){
		    		showSubitem(1) ;
		    		return  ;
		    	}
		    	if(!validateQuestions(questions2)){
		    		showSubitem(2) ;
		    		return  ;
		    	}if(!validateQuestions(questions3)){
		    		showSubitem(3) ;
		    		return  ;
		    	}if(!validateQuestions(questions4)){
		    		showSubitem(4) ;
		    		return  ;
		    	}if(!validateQuestions(questions5)){
		    		showSubitem(5) ;
		    		return  ;
		    	}if(!validateQuestions(questions6)){
		    		showSubitem(6) ;
		    		return  ;
		    	}
		    }) ;
	       

	      
	  
	    
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   <form action="<%=request.getContextPath()%>/manage/holland/gotoStep.do?step=3" method="post">
    <input type="hidden" name="evId" value="${evId}"/>
  	<div id="test" class="container-fluid">
  		<h1 class="page-header" style="text-align:center;">四.	 以下这些描述有多少符合你的想法：</h1>
		<div id="part1" class="container">
		 	
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem1" class="bg-primary">R：实际型</p>
			   <div id="questions1" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem2" class="bg-primary">E：事业型</p>
			   <div id="questions2" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem3" class="bg-primary">C：常规型</p>
			   <div id="questions3" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem4" class="bg-primary">S：社会型</p>
			   <div id="questions4" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem5" class="bg-primary">I：调研型</p>
			   <div id="questions5" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem6" class="bg-primary">A：艺术型</p>
			   <div id="questions6" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 </div>
	 	
	 
	 <div class="row">
	    <input type="submit" value="上一步" id="navi-previous-button" name="previous" class="btn btn-default btn-lg active"  id="navi-button1"/>
	 </div>
	</form> 	 
	
	 

</body>
</html>
