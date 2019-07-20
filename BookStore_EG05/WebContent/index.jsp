<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
</head>
<body>
	<!-- 转发到BookClientServlet查询分页数据 -->
	<jsp:forward page="/BookClientServlet?type=findPage&pageNumber=1"></jsp:forward>
	
	
</body>
</html>