<%@page import="com.edu.springboard.domain.ReBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script type="text/javascript">

let app1;

const row = {
		template:`
			<tr>
				<td>{{p_notice.notice_idx}}</td>
				<td @click="getDetail(p_notice.notice_idx)"><a href="#">{{p_notice.title}}</a></td>
				<td>{{p_notice.writer}}</td>
				<td>{{p_notice.regdate}}</td>
				<td>{{p_notice.hit}}</td>
			</tr>
		`,
		props:["notice"],
		data(){
			return {
				p_notice:this.notice
			};
		},
		methods:{
			getDetail:function(notice_idx){
				location.href = "/notice/detail?notice_idx="+notice_idx;
			}
		}
};

$(function(){
	getList();
	
	init();
	
	$("#bt_regist").click(function(){
		location.href="/notice/registform";
	});
});

function init() {
	app1 = new Vue({
		el:"#app1",
		data:{
			noticeList:[]
		},
		components:{
			row
		}
	});
}

function getList() {
	$.ajax({
		url:"/rest/notices",
		type:"GET",
		success:(result, status, xhr)=>{
			app1.noticeList = result;
		}
	});
}
</script>
</head>

<body>
	<div class="container" id="app1">
		<h2 style="text-align: center;">게시판 만들기</h2>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
					<template v-for="item in noticeList">
						<row :key="item['notice_idx']" :notice="item" />
					</template>
				<tr>
					<td colspan="5"><!-- 까먹지말자 -->
						<button type="button" class="btn btn-info" id="bt_regist">글등록</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>