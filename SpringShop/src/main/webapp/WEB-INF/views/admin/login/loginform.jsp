<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>
<%@ include file="../inc/header_link.jsp"%>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%@ include file="../inc/preloader.jsp"%>

		<!-- Navbar -->
		<%@ include file="../inc/navbar.jsp"%>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="../inc/sidebar_left.jsp"%>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Login</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item active">Login</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->
			<!-- Main content -->
			<section class="content">
				<div class="container">
					<div class="row">
						<div class="col">
							<form id="form1">
								<div class="card card-primary">
									<div class="card-header">
										<h3 class="card-title">로그인</h3>
									</div>
									<div class="card-body">
										<div class="form-group">
											<label for="id">아이디</label> <input
												type="text" class="form-control" id="id"
												placeholder="Enter ID" name="admin_id">
										</div>
										<div class="form-group">
											<label for="pass">비밀번호</label> <input
												type="password" class="form-control" id="pass"
												placeholder="Enter password" name="admin_pass">
										</div>
									</div>
									<div class="card-footer">
										<button type="button" class="btn btn-success" id="bt_login">
											로그인
										</button>
										<button type="button" class="btn btn-default" id="bt_regist">
											회원가입
										</button>
										<button type="button" class="btn btn-danger float-right" id="bt_cancle">
											취소
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../inc/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@ include file="../inc/sidebar_right.jsp"%>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp"%>
</body>
<script type="text/javascript">
	$(()=>{
		$("#bt_login").click(()=>{
			login();
		});
		$("#bt_regist").click(()=>{});
	});
	
	function login() {
		let formData = $("#form1").serialize();
		
		$.ajax({
			url:"/admin/rest/login/admin",
			type:"POST",
			data:formData,
			success:(result, status, xhr)=>{
				alert(result.msg);
				console.log(result);
				console.log(status);
				location.href="/admin/main"; //관리자 메인 요청
			},
			error:(xhr, status, err)=>{
				alert(xhr.responseJSON.msg);
				console.log(xhr.responseJSON);
			}
		});
	}
</script>
</html>









