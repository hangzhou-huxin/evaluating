<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step1.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step2.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/holland/step3.js" charset="UTF-8"></script>
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
	   createTrueFalseQuestion($('#questions7') , questions7 );
	   createTrueFalseQuestion($('#questions8') , questions8 ) ;
	   createTrueFalseQuestion($('#questions9') , questions9 ) ;
	   createTrueFalseQuestion($('#questions10') , questions10 ) ;
	   createTrueFalseQuestion($('#questions11') , questions11 ) ;
	   createTrueFalseQuestion($('#questions12') , questions12 ) ;
	   createTrueFalseQuestion($('#questions13') , questions13 );
	   createTrueFalseQuestion($('#questions14') , questions14 ) ;
	   createTrueFalseQuestion($('#questions15') , questions15 ) ;
	   createTrueFalseQuestion($('#questions16') , questions16 ) ;
	   createTrueFalseQuestion($('#questions17') , questions17 ) ;
	   createTrueFalseQuestion($('#questions18') , questions18 ) ;
	   createTrueFalseQuestion($('#questions19') , questions19 );
	   createTrueFalseQuestion($('#questions20') , questions20 ) ;
	   createTrueFalseQuestion($('#questions21') , questions21 ) ;
	   createTrueFalseQuestion($('#questions22') , questions22 ) ;
	   createTrueFalseQuestion($('#questions23') , questions23 ) ;
	   createTrueFalseQuestion($('#questions24') , questions24 ) ;
	       
	   for(var i=1; i<=24 ;i++){
	    	   $('#subitem'+i).click(function(e) {
	    		   for(var j=1;j<=24;j++){
	    			   $("#questions" + j).hide() ;
	    		   }
	    		   var id = "#questions" + $(this).attr('id').replace("subitem","") ;
	    		   $(id).show();    //如果元素为隐藏,则将它显现
		    	}) ;
	   }
	   
	    currentStep = 1 ;
		   
	    $('#navi-next-button').click(function(){
	    	if(currentStep == 4) {
	    		return ;
	    	}else if(currentStep == 3){
	    		$('#submit-button').show();
	    		$('#navi-next-button').hide(); 
	    	}
	    	currentStep = currentStep + 1 ;
	    	if(currentStep == 3){
	    	}
	    	$('#part1').hide() ;
	    	$('#part2').hide() ;
	    	$('#part3').hide() ;
	    	$('#part4').hide() ;
	    	$('#part' + currentStep).show(); 
	    		
	    }) ;
	    
	    $('#navi-last-button').click(function(){
	    	if(currentStep == 1){
	    		return ;
	    	}else if(currentStep == 4){
	    		$('#submit-button').hide();
	    		$('#navi-next-button').show(); 
	    	}
    			
	    	currentStep = currentStep - 1 ;
	    	//for(var i=1;i<=4;i++){
	    		$('#part1').hide() ;
	    		$('#part2').hide() ;
	    		$('#part3').hide() ;
	    		$('#part4').hide() ;
	    		$('#part' + currentStep).show(); 
	    		
	    }) ;
	    
	});
	
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
   
  	<div id="test" class="container-fluid">
  		
  		<form action="<%=request.getContextPath()%>/holland/finish.do" method="post" onsubmit="return validateFirstForm(this) ;">
			<input type="hidden" name="evId" value="${evId}"/>
		<div id="part1" class="container">
			<h1 class="page-header" style="text-align:center;">一.	您所感兴趣的活动</h1>
		 	<div class="row">
		 	   下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请在“是”栏里打√，反之，在“否”栏里打“×”请按顺序回答全部问题。
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
	 	       <p id="subitem6" class="bg-primary">C：常规型（传统型）活动</p>
			   <div id="questions6" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 	
	 	<div id="part2" class="container" style="display:none;">
	 		<h1 class="page-header" style="text-align:center;">二.	您所擅长获胜的活动</h1>
		 	<div class="row">
		 	   下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请在“是”栏里打√，反之，在“否”栏里打“×”请按顺序回答全部问题。
		 	</div>
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem7" class="bg-primary">R：实际型活动</p>
			   <div id="questions7" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem8" class="bg-primary">A：艺术型活动</p>
			   <div id="questions8" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem9" class="bg-primary">I：调查型活动</p>
			   <div id="questions9" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem10" class="bg-primary">S：社会型活动</p>
			   <div id="questions10" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem11" class="bg-primary">E：事业型活动</p>
			   <div id="questions11" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem12" class="bg-primary">C：常规型（传统型）活动</p>
			   <div id="questions12" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 	
	 	<div id="part3" class="container" style="display:none;">
	 	    <h1 class="page-header" style="text-align:center;">三.	你所喜欢的职业</h1>
		 	<div class="row">
		 	   下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请在“是”栏里打√，反之，在“否”栏里打“×”请按顺序回答全部问题。
		 	</div>
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem13" class="bg-primary">R：实际型活动</p>
			   <div id="questions13" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem14" class="bg-primary">A：艺术型活动</p>
			   <div id="questions14" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem15" class="bg-primary">I：调查型活动</p>
			   <div id="questions15" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem16" class="bg-primary">S：社会型活动</p>
			   <div id="questions16" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem17" class="bg-primary">E：事业型活动</p>
			   <div id="questions17" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem18" class="bg-primary">C：常规型（传统型）活动</p>
			   <div id="questions18" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 	
	 	<div id="part4" class="container" style="display:none;">
	 	    <h1 class="page-header" style="text-align:center;">四.	 以下这些描述有多少符合你的想法：</h1>
		 	<div class="row">
		 	   下面列举了若干种活动，请就这些活动判断你的好恶。喜欢的，请在“是”栏里打√，反之，在“否”栏里打“×”请按顺序回答全部问题。
		 	</div>
	 		<br/>
	 	    <div class="container">
	 	       <p id="subitem19" class="bg-primary">R：实际型活动</p>
			   <div id="questions19" class="row" style=""></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem20" class="bg-primary">A：艺术型活动</p>
			   <div id="questions20" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem21" class="bg-primary">I：调查型活动</p>
			   <div id="questions21" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem22" class="bg-primary">S：社会型活动</p>
			   <div id="questions22" class="row" style="display:none;"></div>
	 	    </div>
	 	    <div class="container">
	 	       <p id="subitem23" class="bg-primary">E：事业型活动</p>
			   <div id="questions23" class="row" style="display:none;"></div>
	 	    </div>
	 		<div class="container">
	 	       <p id="subitem24" class="bg-primary">C：常规型（传统型）活动</p>
			   <div id="questions24" class="row" style="display:none;"></div>
	 	    </div>
	 	</div>
	 	
	 	<div class="container">
	 	    <button id="navi-last-button" type="button" class="btn btn-default btn-lg active"  >上一步</button>
	    	<input id="navi-next-button" type="button" class="btn btn-primary btn-lg active" value="下一步" />
	    	<input  id="submit-button" type="submit"  value="完成" class="btn btn-primary btn-lg active" style="display:none;"/>
	 	</div>
	 	</form>
	 </div>
	 	
	 
	 
	 	 
	
	 
	


</body>
</html>