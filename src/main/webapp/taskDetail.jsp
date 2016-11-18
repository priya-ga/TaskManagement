<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Tasks</title>
</head>
<body>
	<table>
		<thead>
			<%-- <%
				HttpSession httpSession = request.getSession();

				System.out.println("username"
						+ httpSession.getAttribute("username"));
				String userName = (String) httpSession.getAttribute("username");
			%> --%>
			<tr>
				Task Name:
				<c:out value="${task.taskName}" />
			</tr>
			</br>
			<tr>
				Task Description:
				<c:out value="${task.taskDescription}" />
			</tr>

			<tr>
				Task ID:
				<c:out value="${task.taskId}" />
			</tr>

			<%-- <sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/TaskManagement" user="root"
				password="root" />

			<sql:query var="listUserTasks" dataSource="${myDS}">
        SELECT * FROM usertask;
    </sql:query> --%>
			<form method="post" ,value="viewAllUsersLog.jsp">
				<div align="center">
					<tr>
						<center>
							<td>WorkLog</td>
						</center>
					</tr>
					<table border="1" cellpadding="5">

						<caption>
							<h2>List of UserTasks</h2>
						</caption>
						<thread>
						<tr>
							<th>User Name</th>
							<th>Log Start Time</th>
							<th>Log End Time</th>
							<th>Log Description</th>

						</tr>

						</thread>
						<tbody>
							<c:forEach var="usertask" items="${userTaskDTOList}">
								<tr>
									<td><c:out value="${usertask.userName}" /></td>
									<td><c:out value="${usertask.logStartTime}" /></td>
									<td><c:out value="${usertask.logEndTime}" /></td>
									<td><c:out value="${usertask.logDescription}" /></td>

								</tr>
							</c:forEach>
						</tbody>
						</thread>

					</table>

					<table>
						<tr>
							<td><a
								href="HomeController?action=addWorklog&taskId=<c:out value="${task.taskId}"/>">Add
									Worklog</a></td>
						</tr>

					</table>


				</div>
			</form>

			<p>
				<a href="HomeController?action=HomePage">Home Page</a>
			</p>
</body>
</html>