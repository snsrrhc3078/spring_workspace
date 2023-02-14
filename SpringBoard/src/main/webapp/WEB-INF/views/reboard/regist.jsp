<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
  function regist(){
	  $("#form1").attr({
		  action:"/reboard/regist",
		  method:"POST"
	  });
	  $("#form1").submit();
  }
  
	  
  
  $(function(){
	  $("#bt_regist").click(function(){
		  regist();
	  });
	  $("#bt_list").click(function(){
		  location.href="/reboard/list"
	  });
  })
  </script>
</head>
<body>
	<h2 class="d-flex justify-content-center mt-3">내용 등록폼</h2>
	<div class="container">
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
					<button type="button" class="btn btn-primary" id="bt_regist">등록</button>
					<button type="button" class="btn btn-primary" id="bt_list">목록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>