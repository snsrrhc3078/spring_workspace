<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.boxStyle{
	width: 70px;
	height: 85px;
	border: 2px solid #ccc;
	display: inline-block; /*inline:다른요소와 공전, block: 크기설정*/
	margin: 5px;
}
.boxStyle img{
	width: 65px;
	height: 57px;
}
.boxStyle div{
	text-align: right;
	margin-right: 5px;
	font-weight: bold;
}
</style>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- development version, includes helpful console warnings -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
let app1;
let key=0; //유저가 프로그램을 사용하는 동안 그 값을 계속 증가시킴. 유일성 확보
//사용자정의 UI 컴포넌트 등록하기
//등록한 이후엔 마치 태그처럼 사용이 가능...
const imagebox={
	template: `
	<div class="boxStyle">
		<div @click="delImg(p_json)"><a href="#">X</a></div>
		<img :src="p_json['data']">
	</div>
	`,
	/* 이 컴포넌트를 태그로 호출하는 자가 넘긴 속성을 받으려면 props로 받는다 */
	props:["json"],
	data:function(){
		return {
			/* p_src의 용도: 내부 템플릿에서 접근하기 위한 변수
				props의 용도: 외부에서 전달될 속성값을 보괗나기 위한 변수*/
			p_json:this.json
		};
	},
	methods:{
		delImg:function(p_json){
			//console.log(p_json);
			app1.imageList = app1.imageList.filter((item)=>{
				if(item.filename==p_json.filename){
					return false;
				}else{
					return true;
				}
			});
		}
	}
};
function init(){
	app1=new Vue({
		el:"#app1",
		components:{
			imagebox
		},
		data:{
			count:10,
			imageList:[]
		}
	});	
}

function preview(files) {
	for(let i =0;i<files.length;i++){
		let file = files[i];
		
		
		if(checkDuplicate(file)) continue;
		
		let reader = new FileReader();
		reader.onload= function(e){
			console.log(e);
			key++;
			
			app1.imageList.push({
				filename:file.name,
				key:key,
				data:e.target.result,
				file:file
			});
		}
		reader.readAsDataURL(file);
	}
}

//사용자가 선택한 이미지가 이미 app1.imageList에 들어있는지 여부 판단하기
function checkDuplicate(file) {
	let flag = false;
	for(let j = 0;j<app1.imageList.length;j++){
		if(app1.imageList[j].filename==file.name){
			flag=true;
		}
	}
	return flag;
}

function regist() {
	/*
		기존 html의 폼을 이용하여 전송할경우 제일 마지막에 일으킨 이벤트에 의해
		등록된 이미지들만 정송하므로, 누적된 이미지는 전송할 수 없다
		해결책) form을 대체하는 formdata 객체를 이용하여 개발자가 주도하여
		폼을 구성하여 전송하면 됨 html6 FormData + jquery ajax 사용시 쉽다
	*/
	
	let formData = new FormData(); //비어있는 폼을 하나 생성한다
	
	//개발자가 직접 파라미터를 구성할 수 있다
	formData.append("title", $("input[name='title']").val());
	formData.append("writer", $("input[name='writer']").val());
	formData.append("content", $("textarea[name='content']").val());
	
	//파일 파라미터 채우기 (2개 이상임)
	for(let i =0;i<app1.imageList.length;i++){
		let file = app1.imageList[i].file;
		formData.append("photo", file); // 전송할 파일 끄집어내기
	}
	
	$.ajax({
		url:"/gallery/regist",
		type:"POST",
		processData:false, //쿼리스트링화 방지
		contentType:false, //application/x-www 방지
		data:formData,
		enctype:"multipart/form-data",
		success:(result, status, xhr)=>{
			alert("성공");
		}
	});
	
	/* $("#form1").attr({
		action:"/gallery/regist",
		method:"POST",
		enctype:"multipart/form-data"
	});
	
	$("#form1").submit(); */
}

$(function(){
	init();
	
	$("input[name='photo']").change(function(){
		//files 배열을 read only 라서 조작이 불가능하다
		console.log(this.files);	
		preview(this.files);
	});
	
	//등록버튼
	$("#bt_regist").click(()=>{
		regist();
	});
});
</script>
</head>
<body>
	<h2 class="d-flex justify-content-center mt-3">갤러리 내용 등록폼</h2>
	<div class="container" id="app1">
		<div class="row">
			<div class="col">
				<form id="form1">
					<div class="form-group">
						<label for="email">Title:</label> 
						<input type="text" class="form-control" placeholder="Enter title.." name="title">
					</div>
					<div class="form-group">
						<label for="pwd">writer:</label> 
						<input type="text" class="form-control" placeholder="Enter writer.." name="writer">
					</div>
					<div class="form-group">
						<label for="comment">content:</label>
						<textarea class="form-control" rows="5" name="content"></textarea>
					</div>
					<div class="form-group">
						<input type="file" name="photo" multiple>
					</div>
					<div class="form-group" id="previewArea">
						<template v-for="json in imageList">
							<imagebox :key="json['key']" :json="json"/>
						</template>
					</div>
					<button type="button" class="btn btn-success" id="bt_regist">등록</button>
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>