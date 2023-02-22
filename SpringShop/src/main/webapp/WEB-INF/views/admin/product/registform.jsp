<%@page import="com.edu.springshop.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Category> categoryList = (List)request.getAttribute("categoryList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- admin lte -->
<script
	src="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/js/adminlte.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/admin-lte@3.2/dist/css/adminlte.min.css">

<!-- jquery, bootstrap -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<style type="text/css">
.box-style {
	width: 90px;
	height: 95px;
	margin: 5px;
	border: 1px solid #ccc;
	display: inline-block;
}
.box-style div span{
	cursor: pointer;
	
}
.box-style div span:hover{
	color: blue;
	background: red;
}

.box-style img {
	width: 87px;
	height: 73px;
	
}
</style>
</head>

<script type="text/javascript">
	let app1;
	
	const imageBox = {
			template:`
				<div class="box-style">
					<div><span @click="delItem(image)">X</span></div>
					<img :src="image.data">
				</div>
			`,
			props:["image"],
			methods:{
				delItem:(image)=>{
					let filteredList = app1.imageList.filter((item)=>{
						if(item.filename == image.filename){
							return false;
						}else{
							return true;
						}
					});
					
					app1.imageList = filteredList;
				}
			}
	};

	//서머노트 적용하기
	$(()=>{
		init();
		console.log(1);
		$("#file").change(function(e){
			preview(this.files);
		});
		
		$("#detail").summernote({
			minHeight:150
		});
		
		
		$("#bt_regist").click(()=>{
			regist();
		});
		
		$("#bt_list").click(()=>{
			location.href="#";
		});
		
	});
	
	//등록
	function regist() {
		//파일 업로드를 커스터마이징 시켰기 때문에 FormData 사용
		let formData = new FormData();
		formData.append("category.category_idx", $("#category_idx").val());
		formData.append("product_name", $("#product_name").val());
		formData.append("brand", $("#brand").val());
		formData.append("price", $("#price").val());
		formData.append("discount", $("#discount").val());
		formData.append("detail", $("#detail").val());
		
		app1.imageList.forEach((item)=>{
			formData.append("files", item.file);
		});
		
		$.ajax({
			url:"/admin/rest/product",
			type:"POST",
			data:formData,
			contentType:false, //application/x-www-form-urlencode
			processData:false, //쿼리스트링 사용 여부
			enctype:"multipart/form-data",
			success:(result, status, xhr)=>{
				console.log(result);
			}
		});
	}
	
	//vue 초기화
	function init() {
		app1 = new Vue({
			el:"#app1",
			data:{
				count:5,
				imageList:[]
			},
			components:{
				imagebox:imageBox
			}
		});
	}
	
	//미리보기
	function preview(files) {
		console.log(files);
		
		for(let i =0;i<files.length;i++){
			let reader = new FileReader();
			let file = files[i];
			reader.readAsDataURL(file);
			
			reader.onload = (e)=>{
				
				for(let j = 0;j<app1.imageList.length;j++){
					if(app1.imageList[j].filename == file.name) return;
				}
				
				
				app1.imageList.push({
					filename:file.name,
					data:e.target.result,
					file
				});
			};
		}
	}
</script>
<body>
	<div class="container mt-3" id="app1">
		<div class="row">
			<div class="col">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">상품 등록</h3>
					</div>
					<!-- /.card-header -->
					<!-- form start -->
					<form>
						<div class="card-body">
							<div class="form-group">
								<label for="category">상위 카테고리 분류</label> <select
									class="custom-select rounded-0" id="category_idx" name="category_idx">
									<% for(int i =0;i<categoryList.size();i++) {%>
									<% Category category = categoryList.get(i); %>
									<option value="<%= category.getCategory_idx() %>"><%= category.getCategory_name() %></option>
									<% } %>
								</select>
							</div>
							<div class="form-group">
								<label for="product_name">상품명</label> <input type="text"
									class="form-control" id="product_name"
									placeholder="상품명을 입력하세요..">
							</div>
							<div class="form-group">
								<label for="brand">브랜드명</label> <input type="text"
									class="form-control" id="brand"
									placeholder="브랜드명을 입력하세요..">
							</div>
							<div class="form-group">
								<label for="price">가격</label> <input type="number"
									class="form-control" id="price" placeholder="가격을 입력하세요..">
							</div>
							<div class="form-group">
								<label for="discount">할인된 가격</label> <input type="number"
									class="form-control" id="discount"
									placeholder="할인된 가격을 입력하세요..">
							</div>
							<div class="form-group">
								<label for="discount">세부 사항</label>
								<textarea id="detail" name="editordata"></textarea>
							</div>
							<div class="form-group">
								<label for="file">이미지 등록</label> <input type="file"
									class="form-control-file" id="file" multiple>
							</div>
							<div class="form-group">
								<template v-for="(item, i) in imageList">
									<imagebox :key="i" :image="item" />
								</template>
							</div>


						</div>
						<!-- /.card-body -->

						<div class="card-footer">
							<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
							<button type="button" class="btn btn-primary" id="bt_list">목록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>