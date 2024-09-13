<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>
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
	<form action="login" method="post">
		<label for="employee_id">従業員ID：</label> <input type="text" name="employee_id" id="employee_id" value="<%=request.getParameter("employee_id") != null ? request.getParameter("employee_id") : ""%>"required><br>
		<label for="password">パスワード：</label>
		<input type="password" name="password" id="password" value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>" required><br>
		<br> 
		<input type="submit" value="ログイン"><br>
	</form>
	<br>
	<a href="employees-register.jsp"><button>新規登録</button></a>
</body>
</html>