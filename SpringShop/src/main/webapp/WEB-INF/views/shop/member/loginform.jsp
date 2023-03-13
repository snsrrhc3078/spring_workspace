<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%@ include file="../inc/header.jsp" %>
</head>

<body>
    <!-- Page Preloder -->
	<%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <!-- 
    	jsp자체에서 지원하는 태그 
     	왜 써야 하나? 사실 jsp는 디자인 영역이므로, 개발자만 사용하는 것이
     	아니라 퍼블리셔, 웹디자이너와 공유를 한다..이때  java 에 대한 
     	非전문가들은 java 코드를 이해할 수 없기 때문에, 그들이 좀더 쉽게
     	다가갈 수 있도록 태그를 지원해준다 ( 협업 때문에 )
     -->
	<%@ include file="../inc/main_navi.jsp"%>    
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="../inc/header_section.jsp"%>
    <!-- Header Section End -->
    <section>
    	<div class="container">
    		<div class="row m-5">
    			<div class="col">
				    <form id="form1">
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="Enter id" name="id">
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="Enter pass" name="pass">
				        </div>
				       
				        <button type="button" class="btn btn-success" id="bt_google">Google 로그인</button>
				        <button type="button" class="btn btn-success" id="bt_googleauth">Google 인증</button>
				        <button type="button" class="btn btn-success" id="bt_naver">Naver 로그인</button>
				        <button type="button" class="btn btn-success" id="bt_kakao">Kakao 로그인</button>
				        <button type="button" class="btn btn-success" id="bt_login">Login</button>
				        <button type="button" class="btn btn-success" id="bt_regist">신규가입</button>
				        
				    </form>
    			
    			</div>
    		</div>
    	</div>
    </section>
    
    
    
    
    
	<!-- Instagram Begin -->
	<%@ include file="../inc/insta.jsp" %>
	<!-- Instagram End -->
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Search Begin -->
	<%@ include file="../inc/search.jsp" %>
	<!-- Search End -->

<!-- Js Plugins -->
<%@ include file="../inc/footer_link.jsp" %>
<script type="text/javascript">
function regist(){
	$("#form1").attr({
		action:"/member/join.jsp",
		method:"post"
	});	
	$("#form1").submit();
}

$(function(){
	
	$("#bt_googleauth").click(()=>{
		location.href = "<%//= request.getAttribute("url") %>";
	});
	$("#bt_google").click(()=>{
		//SNS사업자가 미리 만들어놓은 인증화면 주소를 요청행 ㅑ한다
		//따라서 주소 및 파라미터명이 이미 정해져있다
		//(어디서 조사해야하나?구글 개발자 사이트에 공시되어있다)
		gotoAuthForm("google");
	});
	$("#bt_kakao").click(()=>{
		gotoAuthForm("kakao");
	});
	$("#bt_naver").click(()=>{
		gotoAuthForm("naver");
	});
	
	$("#bt_login").click(function(){
		$("#form1").attr({
			action:"/member/login.jsp",
			method:"post"
		});
		$("#form1").submit();
	});
	
	$("#bt_regist").click(function(){
		//location.href="";
		$(location).attr("href", "/member/joinform.jsp");
	});
	
});

function gotoAuthForm(sns) {
	$.ajax({
		url:"/rest/member/authform/"+sns,
		type:"GET",
		success:(result, status, xhr)=>{
			console.log("인증 주소는", result.msg);
			location.href=result.msg;
		}
	});
}
</script>
</body>

</html>






