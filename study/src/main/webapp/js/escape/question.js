function createQuestion( containerObj , questionList  , cacheData){
	 var len = questionList.length ;
	 
	 for( var i=0 ; i<len ;i++){
		 var q = questionList[i] ;
		 var questionObj=$('<div class="container"></div>');
		  //newObj.addclass('container'); 
		 var contentObj=$('<div class="row"><div class="col-xs-12"><span style="font-size:14px;">'+ q.index + '&nbsp;'+ q.content +'</span></div></div>') ;
		 questionObj.append(contentObj) ;
		 
		 var options = q.options ;
		 for(var j=0 ; j<options.length ;j++){
			 var option = options[j] ;
			 var inputStr = "" ;
			 var cacheValue = cacheData[q.id] ;
		     if( cacheValue && cacheValue == option.value	){
		    	 inputStr = '<div class="row"><div class="col-xs-12" >&nbsp;&nbsp;<input type="radio"  checked required="required" value="' + option.value + '" name="' + q.id +'" id="' + q.id + '"/>&nbsp;&nbsp;'+option.content+'</div></div>';
		     }else{
		    	 inputStr = '<div class="row"><div class="col-xs-12" >&nbsp;&nbsp;<input type="radio"   required="required" value="' + option.value + '" name="' + q.id +'" id="' + q.id + '"/>&nbsp;&nbsp;'+option.content+'</div></div>';
		     }
		     var optionObj=$(inputStr) ;
			 questionObj.append( optionObj) ;
		 }
		 
		 containerObj.append(questionObj) ;  
		 containerObj.append($('<br/>')) ; 
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

