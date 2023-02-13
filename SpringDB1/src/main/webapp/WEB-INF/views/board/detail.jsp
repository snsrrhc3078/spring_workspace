<%@page import="com.edu.db.domain.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Board board = (Board)request.getAttribute("board");
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
		
		$("#bt_edit").click(()=>{
			edit();
		});
		$("#bt_del").click(()=>{
			del();
		});
		$("#bt_menu").click(()=>{
			location.href="/board/list"
		});
	});
	
	function edit() {
		$("#form1").attr({
			action:"/board/edit",
			method:"POST"
		});
		
		$("#form1").submit();
	}
	function del() {
		$("#form1").attr({
			action:"/board/del",
			method:"POST"
		});
		
		$("#form1").submit();
	}
	
	

</script>
<body>
	<div class="container mt-3">
		<form id="form1">

			<div class="card card-primary">
				<div class="card-header">
					<h1 class="card-title">게시글 상세보기</h1>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
					<div class="card-body">
						<input type="hidden" name="board_idx" value="<%= board.getBoard_idx() %>">
						<div class="form-group">
							<input type="text" class="form-control" name="title"
								placeholder="Title.." value="<%= board.getTitle() %>">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer"
								placeholder="Writer.." value="<%= board.getWriter() %>">
						</div>
						<div class="form-group">
							<textarea class="form-control" name="content"><%= board.getContent() %></textarea>
						</div>

					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<button type="button" class="btn btn-success" id="bt_edit">수정</button>
						<button type="button" class="btn btn-danger" id="bt_del">삭제</button>
						<button type="button" class="btn btn-primary" id="bt_menu">목록</button>
					</div>
			</div>
			
		</form>
	</div>
</body>
</html>