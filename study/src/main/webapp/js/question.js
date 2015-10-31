function createTrueFalseQuestion(subitemObj , questions  , cacheData){
	 var len = questions.length ;
	 
	 for( var i=0 ; i<len ;i++){
		 var q = questions[i] ;
		 var questionObj=$('<div class="container"></div>');
		  //newObj.addclass('container'); 
		 var contentObj=$('<div class="row"><div class="col-xs-12">'+ q.index + q.context +'</div></div>') ;
		 questionObj.append(contentObj) ;
		 
		 var options = q.options ;
		 var optionStr = '<div class="row">' ;
		 for(var j=0 ; j<options.length ;j++){
			 var option = options[j] ;
			 var inputStr = "" ;
			 var cacheValue = cacheData[q.qid] ;
		     if( cacheValue && cacheValue == option.value	){
		    	 inputStr = '<input type="radio"  checked required="required" value="'  + option.value + '" name="' + q.qid +'" id="' + q.id + '"/>';
		     }else{
		    	 inputStr = '<input type="radio"   required="required" value="' + option.value + '" name="' + q.qid +'" id="' + q.id + '"/>';
		     }
		     optionStr +='<div class="col-xs-1">' + inputStr + option.text +'</div>' ;
		 }
		 optionStr += '</div>' ;
		 var optionObj=$(optionStr) ;
		 questionObj.append( optionObj) ;
		 subitemObj.append(questionObj) ;  
		 subitemObj.append($('<br/>')) ; 
	 }
	 
}

function validateForm( ){
	
}

function validateQuestions(questions ){
	var len = questions.length ;
	 var flag = true ;
	 for( var i=0 ; i<len ;i++){
		 var q = questions[i] ;
		 var obj = $("input[name='" +q.qid + "']:checked") ;
		 if(!obj.val()){
			flag = false ; 
			break ;
		 }
	 }
	// alert(flag) ;
	 return flag ;
}

function setSubitemClickEvent(  ){
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
 		   
 	   }) ;
 	   
    } 
}

function showSubitem(  index ){
	$('#questions1').hide() ;
	$('#questions2').hide() ;
 	$('#questions3').hide() ;
 	$('#questions4').hide() ;
 	$('#questions5').hide() ;
 	$('#questions6').hide() ;
 	$('#questions' + index).show(); 
}

