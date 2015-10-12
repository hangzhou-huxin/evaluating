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
		<form action="<%=request.getContextPath()%>/common/apply/save.do" method="post">
			<input type="hidden" name="evId" value="${evId}"/>
		  <div class="form-group">
		    <div class="highlight">
				<p> 提交基本信息，稍后会有老师为您一对一深入解读（请与测试时填写的信息保持一致） </p>
			</div>
		    <label>真实姓名：</label>
		    <input type="text" class="form-control" id="exampleInputEmail1" name="name" value="${name}" readonly="true">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">QQ</label>
		    <input type="text" class="form-control" id="exampleInputPassword1" name="qq" value="${qq}" readonly="readonly">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">专业</label>
		    <input type="text" class="form-control" value="" name="profession" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">入学年份</label>
		    <input type="text" class="form-control" value="" name="schoolYear" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">职业</label>
		    <input type="text" class="form-control" name="career" value="" id="exampleInputFile">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">学历</label>
		    <select style="width:200px;" name="educationId">
		    	<option value="0">请选择...</option>
		    	<option value="1">初中</option>
		    	<option value="2">高中</option>
		    	<option value="3">中专</option>
		    	<option value="4">大专</option>
		    	<option value="5">本科</option>
		    	<option value="6">硕士</option>
		    	<option value="7">博士</option>
		    </select>
		  </div>
		  <div class="form-group">
		  	<label>备注</label>
		  	<textarea class="form-control" rows="3" name="memo"></textarea>
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		  <button type="reset" class="btn btn-default">重置</button>
		</form>
	</div>
</body>
</html>