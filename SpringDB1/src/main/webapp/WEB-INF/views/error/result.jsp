<%@page import="com.edu.db.exception.BoardException"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: yellow">
	이용에 불편을 드려 죄송합니다.
	<p>
	<%
		RuntimeException e = (RuntimeException)request.getAttribute("e");
		out.print(e);
	%>
	</p>
	<a href="/board/list">홈페이지 메인으로</a>
</body>
</html>