<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>逆袭</title>
<meta content="text/html;charset=UTF-8" http-equiv="Content-Type" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/testpaper.js" charset="UTF-8"></script>
<script type="text/javascript">
	
</script>
</head>
<body style="margin:0 auto;width: 75%;">
 
  <div id="test" class="container-fluid" style="margin-top:50px;">
  		<ul>
  		    <c:forEach var="category" items="${categoryList}" >
  		    	<li><a href="<%=request.getContextPath()%>/escape/evaluation/main.do?id=${category.id}"><c:out value="${category.name}" /></a></li>
  		    </c:forEach>
  		</ul>
  </div>

</body>
</html>
