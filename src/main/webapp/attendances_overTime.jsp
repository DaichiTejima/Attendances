<%@ page import="java.util.List"%>
<%@ page import="java.sql.Time"%>
<%@ page import="model.entity.AttendancesBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<AttendancesBean> attendancesList = (List<AttendancesBean>) request.getAttribute("attendancesList");

int totalHours = 0;
int totalMinutes = 0;
int totalSeconds = 0;

if (attendancesList != null) {
	for (AttendancesBean attendances : attendancesList) {
		Time overTime = attendances.getOver_time();
		if (overTime != null) {
			
			totalHours += overTime.getHours();
			totalMinutes += overTime.getMinutes();
			totalSeconds += overTime.getSeconds();
		}
	}
}

totalHours += totalMinutes / 60; 
totalMinutes = totalMinutes % 60;
totalMinutes += totalSeconds / 60;
totalSeconds = totalSeconds % 60;

String formattedTotalOverTime = String.format("%02d:%02d", totalHours, totalMinutes);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>残業管理</title>
</head>
<body>
	<h1>残業管理</h1>
	<form action="attendances-overTime" method="post">
		<label for="employee_id">従業員ID：</label>
		<input type="text" name="employee_id" id="employee_id" value="<%=request.getParameter("employee_id") != null ? request.getParameter("employee_id") : ""%>">
		<button type="submit">検索</button>
		<%
		if (attendancesList != null && !attendancesList.isEmpty()) {
		%>
		<table>
			<thead>
				<tr>
					<th><label for="date">日付</label></th>
					<th><label for="over_time">残業時間</label></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (AttendancesBean attendances : attendancesList) {
				%>

				<tr>
					<td><%=attendances.getDate()%></td>
					<td><%=attendances.getOver_time()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<h4>
			残業時間の合計：<%=formattedTotalOverTime%>
		<h4>
		<%
		}
		%>
		<div>
			<a href="menu.jsp">戻る</a>
		</div>
	</form>
</body>
</html>