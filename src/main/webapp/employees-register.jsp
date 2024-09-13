<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	%>
	<%
	if (errorMessage != null) {
	%>
	<p><%=errorMessage%></p>
	<%
	}
	%>
	<div>
		<h1>新規登録</h1>
		<form action="employees-register" method="post">
			<div>
				<label for="employee_id">従業員ID</label><br>
				<input type="text" name="employee_id" id="employee_id" >
			</div>
			<div>
				<label for="password">パスワード</label><br>
				<input type="password" name="password" id="password" >
			</div>
			<div>
				<label for="name">名前</label><br>
				<input type="text" name="name" id="name" >
			</div>
			<div>
				<label for="age">年齢</label><br>
				<input type="text" name="age" id="age" >
			</div>
			<div>
				<button type="submit">登録</button>
				<a href="login.jsp">戻る</a>
			</div>
		</form>
	</div>

</body>
</html>