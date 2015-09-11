<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>逆袭</title>
<meta content="text/html;charset=UTF-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../jquery/jquery-2.1.4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('form :input').blur(function(){
            var $parent = $(this).parent();
            $parent.find(".formtips").remove();
            //验证用户名
            if( $(this).is('#username') ){
                    if(this.value.length > 10){
                	   alert('用户名输入过长') ;
                   }
            }else if( $(this).is('#qq')){
	            	if(this.value.length > 10){
	             	   alert('QQ号输入过长') ;
	                }
            }
		}) ;
       
		$('#submit').click(function(){
			//return false ;
		}) ;
	  
	  
	});
</script>
</head>
<body style="margin:0 auto;width: 75%;">
 <h1 class="page-header" style="text-align:center;">霍兰德职业倾向测试量表</h1>
  <p>${errMsg}</p>
  <div id="test" class="container-fluid">
	 <form class="form-horizontal" action="step1.do" method="post">
	 <input type="hidden" name="evId" value="${evId}"/>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="username" name="username" value="" required="required" placeholder="请填入真实姓名"/>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">QQ</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="qq" name="qq" placeholder="QQ" value="" required="required"/>
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="submit" type="submit" class="btn btn-default">开始</button>
    </div>
  </div>
</form>
	 
	
  </div>


</body>
</html>
