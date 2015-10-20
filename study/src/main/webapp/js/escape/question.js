var prefix = "es_" ;
function createQuestion( containerObj , questionList  , cacheData){
	var len = questionList.length ; 
	for( var i=0 ; i<len ;i++){
		 
		 var q = questionList[i] ;
		 var questionObj=$('<div id="question_id_' + i +  '" class="row" style="display:none;"></div>');
		  //newObj.addclass('container'); 
		 var contentObj=$('<div class="row"><div class="col-xs-12"><span style="font-size:14px;">'+ q.index + '&nbsp;'+ q.content +'</span></div></div>') ;
		 questionObj.append(contentObj) ;
		 
		 var options = q.options ;
		 for(var j=0 ; j<options.length ;j++){
			 var option = options[j] ;
			 var inputStr = "" ;
			 var cacheValue = cacheData[q.id] ;
		     if( cacheValue && cacheValue == option.value	){
		    	 inputStr = '<div class="row"><div class="col-xs-12" >&nbsp;&nbsp;<input type="radio"  checked required="required" value="' + option.value + '" name="' + prefix + q.id +"_"+q.dimensionKey +'" id="' + q.id + '"/>&nbsp;&nbsp;'+option.content+'</div></div>';
		     }else{
		    	 inputStr = '<div class="row"><div class="col-xs-12" >&nbsp;&nbsp;<input type="radio"   required="required" value="' + option.value + '" name="' + prefix + q.id +"_"+q.dimensionKey +'" id="' + q.id + '"/>&nbsp;&nbsp;'+option.content+'</div></div>';
		     }
		     var optionObj=$(inputStr) ;
			 questionObj.append( optionObj) ;
		 }
		 
		 containerObj.append(questionObj) ;  
		// containerObj.append($('<br/>')) ; 
	 }
	 
}


function showQuestions(pageNo , pageSize ,questions){
	var start = (pageNo-1) * pageSize; 
	var end = start + pageSize -1 ; 
	var len = questions.length ;
	for( var i=0 ; i<len ;i++){
		 var q = questions[i] ;
		 var obj = $("#question_id_" +i + "") ;
		 if(i>=start && i<=end){
			 obj.show() ;
		 }else{
			 obj.css("display", "none");  ;
		 }
	}
}

function validateForm( ){
	
}

function validateQuestions(pageNo , pageSize ,questions ){
	var start = (pageNo-1) * pageSize; 
	var end = start + pageSize -1 ; 
	var len = questions.length ;
	 var flag = true ;
	 for( var i=0 ; i<len ;i++){
		 if(i>=start && i<=end){
			 var q = questions[i] ;
			 var obj = $("input[name='" +prefix+q.id +"_"+q.dimensionKey + "']:checked") ;
			 if(!obj.val()){
				flag = false ; 
				break ;
			 }
		 }
	 }
	// alert(flag) ;
	 return flag ;
}


