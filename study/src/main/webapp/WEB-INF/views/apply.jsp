<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="finish.do" method="post">
		  <div class="form-group">
		    <div class="highlight">
				<p> 提交基本信息，稍后会有老师为您一对一深入解读（请与测试时填写的信息保持一致） </p>
			</div>
		    <label>真实姓名：</label>
		    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">QQ</label>
		    <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Password">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">专业</label>
		    <input type="text" class="form-control" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">入学年份</label>
		    <input type="text" class="form-control" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">职业</label>
		    <input type="text" class="form-control" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">学历</label>
		    <select style="width:200px;"></select>
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		  <button type="reset" class="btn btn-default">重置</button>
		</form>
	</div>
</body>
</html>