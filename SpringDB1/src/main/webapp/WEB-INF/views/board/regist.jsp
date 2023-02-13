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
			regist();
		});
		$("#bt_menu").click(()=>{
			location.href="/board/list"
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
		<form id="form1">

			<div class="card card-primary">
				<div class="card-header">
					<h3 class="card-title">게시글 등록하기</h3>
				</div>
				<!-- /.card-header -->
					<div class="card-body">

						<div class="form-group">
							<input type="text" class="form-control" name="title"
								placeholder="Title..">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="writer"
								placeholder="Writer..">
						</div>
						<div class="form-group">
							<textarea class="form-control" name="content"></textarea>
						</div>

					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
						<button type="button" class="btn btn-primary" id="bt_menu">목록</button>
					</div>
			</div>
			
		</form>
	</div>
</body>
</html>