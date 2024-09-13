<%@page import="model.entity.AttendancesBean"%>
<%@page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	AttendancesBean attendances = (AttendancesBean) request.getAttribute("attendances");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠編集</title>
</head>
<body>
	<h1>勤怠編集</h1>
	<form action="attendances-edit" method="post">
		<div>
			<p>従業員ID：<%=attendances.getEmployee_id()%></p>
			<p>日付：<%=attendances.getDate()%></p>
		</div>
		<table>
			<thead>			
				<tr>
					<th><label for="start_time">始業時間</label></th>
					<th><label for="end_time">終業時間</label></th>
					<th><label for="break_time">休憩時間</label></th>
					<th><label for="work_time">就業時間</label></th>
					<th><label for="over_time">残業時間</label></th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td><input type="time" name="start_time" id="start_time" value="<%=attendances.getStart_time()%>"></td>
					<td><input type="time" name="end_time" id="end_time" value="<%=attendances.getEnd_time()%>"></td>
					<td><input type="time" name="break_time" id="break_time" value="<%=attendances.getBreak_time()%>"></td>
					<td><input type="time" name="work_time" id="work_time" value="<%=attendances.getWork_time()%>"></td>
					<td><input type="time" name="over_time" id="over_time" value="<%=attendances.getOver_time()%>"></td>
				</tr>
			</tbody>
		</table>
		<div>
			<button type="submit">更新</button>
			<input type="hidden" name="id" value="<%=attendances.getId()%>">
			<a href="attendances-list">戻る</a>
		</div>
	</form>
</body>
</html>