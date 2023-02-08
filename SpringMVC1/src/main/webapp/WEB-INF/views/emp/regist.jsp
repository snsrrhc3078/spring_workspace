<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(()=>{
		$("#bt_regist").click(()=>{
			regist();
		});
	});
	
	
	function regist() {
		$("#form1").attr({
			action:"/emp/regist.do",
			method:"POST"
		});
		
		$("#form1").submit();
	}
</script>
<body>
	<div class="container mt-3">
		<form id="form1">
			<div class="row mb-3">
				<div class="col">
					<input type="text" class="form-control" name="dname" placeholder="부서명">
				</div>
			</div>
			<div class="row mb-3">
				<div class="col">
					<input type="text" class="form-control" name="ename" placeholder="사원명">
				</div>
			</div>
			<div class="row mb-3">
				<div class="col">
					<input type="number" class="form-control" name="sal" placeholder="급여">
				</div>
			</div>
			<div class="row mb-3">
				<div class="col">
					<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>