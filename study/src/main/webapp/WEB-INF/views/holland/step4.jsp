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
	    	    window.location.href = "step3.do?evId=${evId}" ;
		    }) ;
	       
	      
	  
	    
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   <form action="finish.do" method="post">
    <input type="hidden" name="evId" value="${evId}"/>
  	<div id="test" class="container-fluid">
  		<h1 class="page-header" style="text-align:center;">四.	 以下这些描述有多少符合你的想法：</h1>
		<div id="part1" class="container">
		 	
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem1" class="bg-primary">实际型</p>
			   <div id="questions1" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem2" class="bg-primary">事业型</p>
			   <div id="questions2" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem3" class="bg-primary">常规型</p>
			   <div id="questions3" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem4" class="bg-primary">社会型</p>
			   <div id="questions4" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem5" class="bg-primary">调研型</p>
			   <div id="questions5" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem6" class="bg-primary">艺术型</p>
			   <div id="questions6" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 </div>
	 	
	 
	 <div class="row">
	    <button type="button" class="btn btn-default btn-lg active"  id="navi-button1">上一步</button>
	    
	    <input  type="submit"  value="完成" class="btn btn-primary btn-lg active"/>
	 </div>
	</form> 	 
	
	 

</body>
</html>
