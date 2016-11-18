<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All usertasks</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>User Name</th>
				<th>Log Start Time</th>
				<th>Log End Time</th>
				<th>Log Description</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usertask}" var="usertask">
				<tr>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${usertask.logStartTime}" /></td>
					<td><c:out value="${usertask.logEndTime}" /></td>
					<td><c:out value="${usertask.logDescription}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="WorklogController?action=insert">Add Student</a>
	</p>
</body>
</html>