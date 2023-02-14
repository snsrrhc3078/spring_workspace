<%@page import="com.edu.springboard.domain.ReBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	ReBoard reBoard=(ReBoard)request.getAttribute("reBoard");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript">
  function edit(){
	  $("#form1").attr({
		  action:"/reboard/edit",
		  method:"POST"
	  });
	  $("#form1").submit();
  }

  function del(){
	  $("#form1").attr({
		  action:"/reboard/delete",
		  method:"POST"
	  });
	  $("#form1").submit();
  }
  
  $(function(){
	  $("#bt_edit").click(function(){
		  edit();
	  });
	  $("#bt_del").click(function(){
		  del();
	  });
	  $("#bt_list").click(function(){
		  location.href="/reboard/list"
	  });
	  $("#bt_replyform").click(function(){
		  //숨겨져있던 답변등록 폼 등장
		  $("#reply_section").show();
	  });
	  
	  
	  $("#reply_section").hide();
  })
  
  </script>
</head>
<body>
	<h2 class="d-flex justify-content-center mt-3">상세 보기</h2>
	<div class="container">
		<div class="row mb-3">
			<div class="col">
				<form id="form1">
					<input type="hidden" name="reboard_idx" value="<%=reBoard.getReboard_idx()%>">
					<div class="form-group">
						<label for="email">Title:</label> 
						<input type="text" class="form-control" placeholder="Enter title.." name="title" value="<%=reBoard.getTitle()%>">
					</div>
					<div class="form-group">
						<label for="pwd">writer:</label> 
						<input type="text" class="form-control" placeholder="Enter writer.." name="writer" value="<%=reBoard.getWriter()%>">
					</div>
					<div class="form-group">
						<label for="comment">content:</label>
						<textarea class="form-control" rows="5" name="content"><%=reBoard.getContent() %></textarea>
					</div>
					<button type="button" class="btn btn-warning" id="bt_edit">수정</button>
					<button type="button" class="btn btn-warning" id="bt_del">삭제</button>
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
					<button type="button" class="btn btn-primary" id="bt_replyform">답변하기</button>
				</form>
			</div>
		</div>
		
		<div class="row mb-3" id ="reply_section">
			<div class="col">
				<form id="form2">
					<div class="form-group">
						<input type="text" name="" class="form-control" placeholder="제목">
					</div>
					<div class="form-group">
						<input type="text" name="" class="form-control" placeholder="작성자">
					</div>
					<div class="form-group">
						<textarea class="form-control" name=""></textarea>
					</div>

					<div class="form-group">
						<button type="button" class="btn btn-primary">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>