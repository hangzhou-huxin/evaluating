<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta content="text/html;charset=UTF-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/testpaper2.js" charset="UTF-8"></script>
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
		   
	       $('#navi-button2').click(function(e) {
	    	    window.location.href = "index3.html" ;
		    }) ;
	       
	       $('#navi-button1').click(function(e) {
	    	    window.location.href = "index1.html" ;
		    })
	  
	  
	    
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   
  	<div id="test" class="container-fluid">
  		<h1 class="page-header" style="text-align:center;">二.	您所擅长获胜的活动</h1>
		<div id="part1" class="container">
		 	<div class="row">
		 	   下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请在“是”栏里打√，反之，在“否”栏里打“×”请按顺序回答全部问题。
		 	</div>
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem1" class="bg-primary">R：实际型活动</p>
			   <div id="questions1" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem2" class="bg-primary">A：艺术型能力</p>
			   <div id="questions2" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem3" class="bg-primary">I：调研型能力</p>
			   <div id="questions3" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem4" class="bg-primary">S：社会型能力</p>
			   <div id="questions4" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem5" class="bg-primary">E：事业型能力</p>
			   <div id="questions5" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem6" class="bg-primary">C常规型能力</p>
			   <div id="questions6" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 </div>
	 	
	 
	 <div class="container">
	 	<button type="button" class="btn btn-default btn-lg active"  id="navi-button1">上一步</button>
	    <button type="button" class="btn btn-primary btn-lg active"  id="navi-button2">下一步</button>
	 </div>
	 	 
	
	 
	
  
<script src="bootstrap/js/bootstrap.js"></script>

</body>
</html>
