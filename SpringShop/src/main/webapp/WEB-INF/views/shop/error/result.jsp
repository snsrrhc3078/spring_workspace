<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="orange">
	이용에 불편을 드려 죄송합니다<br>
	
	<% RuntimeException exception = (RuntimeException)request.getAttribute("e"); %>
	<%= exception.getMessage() %>	
</body>
</html>