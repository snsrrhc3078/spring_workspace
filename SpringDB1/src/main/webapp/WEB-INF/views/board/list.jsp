<%@page import="com.edu.db.domain.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Board> boardList = (List) request.getAttribute("boardList");
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
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">
</head>
<script type="text/javascript">
	$(()=>{
		
		$("#bt_regist").click(()=>{
			location.href="/board/registform";
		});
	});

</script>
<body>
	<div class="container mt-3">

		<div class="card card-primary">
			<div class="card-header">
				<h3 class="card-title">게시판</h3>
			</div>
			<!-- /.card-header -->
			<div class="card-body">
				<table class="table table-bordered table-hover">
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
							<td>	<%= board.getRegdate() %></td>
							<td><%= board.getHit() %></td>
						</tr>
						<% } %>

					</tbody>
				</table>
			</div>
			<!-- /.card-body -->
			<div class="card-footer clearfix">
				<button class="btn btn-primary" id="bt_regist">글쓰기</button>
				<ul class="pagination pagination-sm m-0 float-right">
					<li class="page-item"><a class="page-link" href="#">«</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">»</a></li>
				</ul>
			</div>
		</div>

	</div>
</body>
</html>