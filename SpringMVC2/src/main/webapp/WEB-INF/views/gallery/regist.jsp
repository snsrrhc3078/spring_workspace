<%@ page contentType="text/html; charset=UTF-8"%>
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
		$("#bt_regist").click(()=>{
			regist();
		});
	});
	
	function regist() {
		$("#form1").attr({
			action:"/board/regist",
			method:"POST"
		});
		
		$("#form1").submit();
	}
</script>
<body>
	<div class="container mt-3">
		<h1>갤러리 글 등록</h1>
		<form id="form1">
			<div class="form-group">
				<input type="text" name="title" class="form-control"
					placeholder="title..">
			</div>
			<div class="form-group">
				<input type="text" name="writer" class="form-control"
					placeholder="writer..">
			</div>
			<div class="form-group">
				<textarea name="content" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<input type="file" name="file" class="form-control-file"
					placeholder="writer..">
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary" id="bt_regist">글등록</button>
			</div>
		</form>
	</div>
</body>
</html>