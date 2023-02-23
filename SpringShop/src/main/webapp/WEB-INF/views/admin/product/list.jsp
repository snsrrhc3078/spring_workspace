<%@page import="com.edu.springshop.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Product> productList = (List) request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<%@ include file="../inc/header_link.jsp"%>
<style type="text/css">
	tr{
		cursor: pointer;
	}

</style>
</head>
<body>
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
							<h1 class="m-0">상품목록</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">상품관리</a></li>
								<li class="breadcrumb-item active">상품목록</li>
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
				<div class="container-fluid" id="app1">
					<div class="row">
						<div class="col">
						
							<!-- table card-->
							<div class="card card-secondary">
								<!-- table card header -->
								<div class="card-header">
									<h3 class="card-title">카테고리 목록</h3>
									<div class="card-tools">
										<div class="input-group input-group-sm" style="width: 150px;">
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
												<th>No</th>
												<th>상품명</th>
												<th>브랜드</th>
												<th>가격</th>
												<th>할인가</th>
											</tr>
										</thead>
										<tbody>
											<% for(int i =0;i<productList.size();i++){ %>
											<% Product product = productList.get(i); %>
											<tr onclick="getDetail(<%= product.getProduct_idx() %>)">
												<td><%= i+1 %></td>
												<td><%= product.getProduct_name() %></td>
												<td><%= product.getBrand() %></td>
												<td><%= product.getPrice() %></td>
												<td><%= product.getDiscount() %></td>
											</tr>
											<% } %>
										</tbody>
									</table>
								</div>

								<div class="card-footer">
									<button class="btn btn-primary" id="bt_regist">등록</button>
								</div>
							</div>
							<!-- end of table card -->
							
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
	function getDetail(product_idx) {
		location.href = "/admin/product/detail?product_idx="+product_idx;
	}
	
	$(()=>{
		$("#bt_regist").click(()=>{
			location.href="/admin/product/registform";
		});
	});
</script>
</html>