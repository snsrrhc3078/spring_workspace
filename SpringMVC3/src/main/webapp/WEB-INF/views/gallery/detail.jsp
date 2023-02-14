<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//Board board = (Board)request.getAttribute("board");
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
		$("#bt_menu").click(()=>{
			location.href="/board/list";
		});
		$("#bt_edit").click(()=>{
			edit();
		});
		$("#bt_del").click(()=>{
			del();
		});
	});
	
	function del() {
		if(confirm("삭제하시겠습니까?")){
			$("#form1").attr({
				action:"/board/delete",
				method:"POST"
			});
			
			$("#form1").submit();
		}
	}
	
	function edit() {
		if(confirm("수정하시겠습니까?")){
			$("#form1").attr({
				action:"/board/edit",
				method:"POST"
			});
			
			$("#form1").submit();
		}
		
	}
</script>
<body>
	<div class="container mt-3">
		<form id="form1">
		<input type="hidden" name="board_idx" value="<%//=board.getBoard_idx()%>">
			<div class="form-group">
				<input type="text" name="title" class="form-control"
					placeholder="title.." value="<%//=board.getTitle()%>">
			</div>
			<div class="form-group">
				<input type="text" name="writer" class="form-control"
					placeholder="writer.." value="<%//=board.getWriter()%>">
			</div>
			<div class="form-group">
				<textarea name="content" class="form-control"><%//=board.getContent()%></textarea>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-danger" id="bt_edit">수정</button>
				<button type="button" class="btn btn-danger" id="bt_del">삭제</button>
				<button type="button" class="btn btn-primary" id="bt_menu">목록</button>
			</div>
		</form>
	</div>
</body>
</html>