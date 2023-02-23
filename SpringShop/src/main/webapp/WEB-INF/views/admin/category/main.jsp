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

								<form id="form2">
									<div class="card-body">
										<input type="hidden" name="_method"> 
										<input type="hidden" name="category_idx">
										<div class="form-group">
											<label for="category_name2">카테고리 이름</label> <input
												type="text" class="form-control" placeholder="카테고리 이름.."
												name="category_name">
										</div>
									</div>
									<div class="card-footer">
										<button type="button" class="btn btn-success" id="bt_edit">수정</button>
										<button type="button" class="btn btn-danger" id="bt_del">삭제</button>
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
					$("#form2 input[name='category_idx']").val(category.category_idx);
					$("#form2 input[name='category_name']").val(category.category_name);
				}
			}
	}


	$(()=>{
		init();
		
		$("#bt_regist").click(()=>{
			regist();
		});
		$("#bt_edit").click(()=>{
			editAsync();
		});
		$("#bt_del").click(()=>{
			del();
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
	
	function edit() {
		//동기방식 put
		$("#form2 input[name='_method']").val("PUT");
		
		$("#form2").attr({
			action:"/admin/rest/category",
			method:"POST",
		});
		
		$("#form2").submit();
		
		
	}
	
	function editAsync() {
		/* $("#form2 input[name='_method']").val("PUT");
		let formData = $("#form2").serialize();
		console.log(formData); */
		
		if(!confirm("수정하시겠습니까?")){
			return;
		}
		
		//json 방식으로 전송하기
		//웹상의 데이터 교환시 데이터형식은 무조건 문자열이 되어야 한다
		//따라서 자바스크립트 내장객체인 json 자체는 전송대상이 될 수 없다
		//해결책: 문자열화 시키되 개발자가 일일이 수작업으로 하지 말고 JSON.stringify()활용
		let json = {
			_method:"PUT",
			category_idx:$("#form2 input[name='category_idx']").val(),
			category_name:$("#form2 input[name='category_name']").val()
		};
				
		 //비동기방식 put
		$.ajax({
			url:"/admin/rest/category",
			type:"PUT",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(json),
			processData:false, //Query string 화 여부
			success:(result, status, xhr)=>{
				console.log(result);
				getList();
			},
			error:(xhr, status , err)=>{
				console.log(err);
			}
			
		});
	}
	
	function del() {
		if(!confirm("삭제하시겠습니까?")){
			return;
		}
		
		$.ajax({
			url:"/admin/rest/category/"+$("#form2 input[name='category_idx']").val(),
			type:"DELETE",
			success:(result, status, xhr)=>{
				console.log(result);
				getList();
			},
			error:(xhr, status, err)=>{
				console.log(err);
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