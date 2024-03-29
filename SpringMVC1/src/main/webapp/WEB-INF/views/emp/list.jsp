<%@page import="com.edu.SpringMVC1.domain.Emp"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Emp> empList = (List)request.getAttribute("empList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(()=>{
		$("#bt_regist").click(()=>{
			location.href="/emp/regist.jsp";
		});
		$("#bt_del_selected").click(()=>{
			deleteSelectedItem();
		});
	});
	
	function deleteSelectedItem() {
		let checkedItems = $("input[type='checkbox']:checked");
		$.each(checkedItems, (i, item)=>{
			$(item).attr("name", "empno[]");
		});
		
		$("#form1").attr({
			action:"/emp/deleteSelected.do",
			method:"POST"
		});
		
		$("#form1").submit();
	}
	
</script>
<body>
	<div class="container mt-3">
		<form id="form1">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th></th>
						<th>Dname</th>
						<th>Ename</th>
						<th>Sal</th>
					</tr>
				</thead>
				<tbody>
					<% for(int i =0;i<empList.size();i++){ %>
					<% Emp emp = empList.get(i); %>
					<tr>
						<td>
							<input type="checkbox" value="<%=emp.getEmpno()%>">
						</td>
						<td><%= emp.getDept().getDname() %></td>
						<td><%= emp.getEname() %></td>
						<td><%= emp.getSal() %></td>
					</tr>
					<% } %>
					<tr>
						<td colspan="4">
							<button type="button" class="btn btn-primary" id="bt_regist">글쓰기</button>
							<button type="button" class="btn btn-danger" id="bt_del_selected">선택삭제</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>