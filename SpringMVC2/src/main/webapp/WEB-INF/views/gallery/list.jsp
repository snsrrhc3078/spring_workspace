<%@page import="com.edu.mvc2.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Board> boardList = (List)request.getAttribute("boardList");
	//out.print(boardList.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<script type="text/javascript">
	$(()=>{
		$("#bt_regist").click((e)=>{
			regist(e);
		});
	});
	
	function regist(e) {
		location.href="/board/registform";
	}
</script>
<body>
	<div class="container mt-3">
		<table class="table table-hover table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Title</th>
					<th>Writer</th>
					<th>Regdate</th>
					<th>Hit</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i =0;i<boardList.size();i++){ %>
				<% Board board = boardList.get(i); %>
				<tr>
					<td><a href="/board/detail?board_idx=<%= board.getBoard_idx() %>"><%= board.getTitle() %></a></td>
					<td><%= board.getWriter() %></td>
					<td><%= board.getRegdate() %></td>
					<td><%= board.getHit() %></td>
				</tr>
				<% } %>
				<tr>
					<td colspan="4">
						<button type="button" class="btn btn-primary" id="bt_regist">글등록</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>