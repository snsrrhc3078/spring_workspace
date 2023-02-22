<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../inc/header_link.jsp"%>
<style type="text/css">
tbody tr {
	cursor: pointer;
}
</style>
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

		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">카테고리 관리</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">상품관리</a></li>
								<li class="breadcrumb-item active">카테고리 관리</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<section class="content">
				<div class="container-fluid mt-3">
					<div class="row">
						<div class="col-md-6">
							<div class="row">
								<div class="col">
									<!-- regist form card-->
									<div class="card card-primary">
										<div class="card-header">
											<h3 class="card-title">카테고리 등록</h3>
										</div>

										<form id="form1">
											<div class="card-body">
												<div class="form-group">
													<label for="category_name">카테고리 이름</label> <input
														type="text" class="form-control" placeholder="카테고리 이름.."
														id="category_name">
												</div>
											</div>
											<div class="card-footer">
												<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
											</div>
										</form>
									</div>
									<!-- end of regist form card -->

									<!-- table card-->
									<div class="card card-secondary">
										<!-- table card header -->
										<div class="card-header">
											<h3 class="card-title">카테고리 목록</h3>
											<div class="card-tools">
												<div class="input-group input-group-sm"
													style="width: 150px;">
													<input type="text" name="table_search"
														class="form-control float-right" placeholder="Search">

													<div class="input-group-append" onclick="alert()">
														<button type="submit" class="btn btn-default">
															<i class="fas fa-search"></i>
														</button>
													</div>
												</div>
											</div>
										</div>

										<!-- table carad body -->
										<div class="card-body table-responsive p-0">
											<table class="table table-hover text-nowrap" id="table1">
												<thead>
													<tr>
														<th>카테고리 번호</th>
														<th>카테고리 이름</th>
													</tr>
												</thead>
												<tbody>
													<template v-for="(category, i) in categoryList">
														<row :key="i" :category="category" />
													</template>
												</tbody>
											</table>
										</div>

									</div>
									<!-- end of table card -->
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<!-- detail form card-->
							<div class="card card-success">
								<div class="card-header">
									<h3 class="card-title">상세보기</h3>
								</div>

								<form id="form1">
									<div class="card-body">
										<input type="hidden" id="category_idx">
										<div class="form-group">
											<label for="category_name2">카테고리 이름</label> <input
												type="text" class="form-control" placeholder="카테고리 이름.."
												id="category_name2">
										</div>
									</div>
									<div class="card-footer">
										<button type="button" class="btn btn-success">수정</button>
										<button type="button" class="btn btn-danger">삭제</button>
									</div>
								</form>
							</div>
							<!-- end of detail form card -->
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>

	<%@ include file="../inc/footer.jsp"%>
	<!-- Control Sidebar -->
	<%@ include file="../inc/sidebar_right.jsp"%>
</body>
<%@ include file="../inc/footer_link.jsp"%>

<script type="text/javascript">
	let table1;
	const row = {
			template:`
				<tr @click="getDetail(category)">
					<td>{{category.category_idx}}</td>
					<td>{{category.category_name}}</td>
				</tr>
			`,
			props:["category"],
			methods:{
				getDetail:(category)=>{
					$("#category_idx").val(category.category_idx);
					$("#category_name2").val(category.category_name);
				}
			}
	}


	$(()=>{
		init();
		
		$("#bt_regist").click(()=>{
			regist();
		});
		
		getList();
	});
	
	function init() {
		table1 = new Vue({
			el:"#table1",
			data:{
				categoryList:[]
			},
			components:{
				row
			}
		});
	}
	
	function regist() {
		$.ajax({
			url:"/admin/rest/category",
			type:"POST",
			data:{
				category_name:$("#category_name").val()
			},
			//서버로부터 전송된 HTTP 응답 헤더 정보가 성공일 때 반응
			success:(result, status, xhr)=>{
				console.log(result);
				getList();
			},
			//서버로부터 전송된 HTTP 응답 헤더 정보가 실패일 때 반응
			error:(xhr, status, err)=>{
				console.log("에러났습니다", err);
			}
		});
	}
	
	function getList() {
		$.ajax({
			url:"/admin/rest/category",
			type:"GET",
			success:(result, status, xhr)=>{
				table1.categoryList = result;
			},
			error:(xhr, status, err)=>{
				alert("에러났음", err);
			}
		});
	}
</script>
</html>