<%@ page import="java.util.List"%>
<%@ page import="model.entity.AttendancesBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

List<AttendancesBean> attendancesList = (List<AttendancesBean>) request.getAttribute("attendancesList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠検索</title>
</head>
<body>
	<h1>勤怠検索</h1>
	<form action="attendances-search" method="get">
		<div>
			<p>日付：<%= (attendancesList != null && !attendancesList.isEmpty()) ? attendancesList.get(0).getDate() : "" %></p>
		</div>
		<table>
			<thead>
				<tr>
					<th>従業員ID</th>
					<th>始業時間</th>
					<th>終業時間</th>
					<th>休憩時間</th>
					<th>就業時間</th>
					<th>残業時間</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (attendancesList != null && !attendancesList.isEmpty()) {
				%>
				<%
				for (AttendancesBean attendances : attendancesList) {
				%>
				<tr>
					<td><%=attendances.getEmployee_id()%></td>
					<td><%=attendances.getStart_time()%></td>
					<td><%=attendances.getEnd_time()%></td>
					<td><%=attendances.getBreak_time()%></td>
					<td><%=attendances.getWork_time()%></td>
					<td><%=attendances.getOver_time()%></td>
				</tr>
				<%
				}
				%>
				<%
				} else {
				%>
				<tr>
					<td>該当するデータがありません</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<div>
			<a href="attendances-list">戻る</a>
		</div>
	</form>
</body>
</html>