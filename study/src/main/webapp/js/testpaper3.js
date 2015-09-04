
questions1 = [{index:"1.",context:" 飞机机械师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"2.",context:"野生动物专家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"3.",context:"设备维修专家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"4.",context:"测量工程师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"5.",context:"无线电报务员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"6.",context:"园艺师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"7.",context:"网络管理员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
                  {index:"8.",context:"电气工程师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]}
                  ] ;

questions2 = [{index:"1.",context:"街道、工会干部",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"2.",context:"小学、中学教师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"3.",context:"精神病医生",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"4.",context:"体育教练",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"5.",context:"福利机构负责人",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"6.",context:"心理咨询员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"7.",context:"导游",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"8.",context:"国家机关工作人员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]} 
			 ];

questions3 = [{index:"1.",context:"气象学或天文学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"2.",context:"生物学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"3.",context:"医学实验室的技术人员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"4.",context:"动物学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"5.",context:"数学学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"6.",context:"科学杂志的编辑或作家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"7.",context:"地质学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"8.",context:"物理学者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]} 
			 ];

questions4 = [{index:"1.",context:"厂长",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"2.",context:"电视片编制人",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"3.",context:"公司经理",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"4.",context:"广告部长",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"5.",context:"体育活动主办者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"6.",context:"销售部长",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"7.",context:"个体工商业者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"8.",context:"企业管理咨询人员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]} 
			 ];

questions5 = [{index:"1.",context:"乐队指挥",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"2.",context:"演奏家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"3.",context:"作家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"4.",context:"摄影家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"5.",context:"记者",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"6.",context:"画家、书法家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"7.",context:"歌唱家",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"8.",context:"电影电视演员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]} 
			 ];

questions6 = [{index:"1.",context:"会计师",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"2.",context:"银行出纳员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"3.",context:"税收管理员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"4.",context:"计算机操作员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"5.",context:"成本核算员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"6.",context:"文书档案管理员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"7.",context:"打字员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]},
              {index:"8.",context:"法庭书记员",options:[{text:"是",value:"true"},{text:"否",value:"false"}]} 
			 ];


function createTrueFalseQuestion(subitemObj , questions ){
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
			 optionStr +='<div class="col-xs-1"><input type="radio" value="' + option.value + '"/>'+ option.text +'</div>' ;
		     
		 }
		 optionStr += '</div>' ;
		 var optionObj=$(optionStr) ;
		 questionObj.append( optionObj) ;
		 subitemObj.append(questionObj) ;  
		 subitemObj.append($('<br/>')) ; 
	 }
	 
}