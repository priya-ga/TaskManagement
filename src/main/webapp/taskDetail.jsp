<%@page import="com.todo.util.DBUtil"%>
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
 <%@include file="header.jsp" %>
	
	
			<%
				if (session != null) {
						if (session.getAttribute("username") != null) {
							String name = (String) session.getAttribute("username");

						} else {
							response.sendRedirect("index.jsp");
						}
					}
			%>
			<table>
			<tr>Task Name:<c:out value="${task.taskName}" /></tr>
			</br>
			<tr>Task Description:<c:out value="${task.taskDescription}" /></tr>
			</table>

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
							<th>Log Start Time(In hour)</th>
							<th>Log End Time(In hour)</th>
							<th>Log Description</th>
							<th>Total Duration(In hour)</th>

						</tr>

						</thread>
						<tbody>
							<c:forEach var="usertask" items="${userTaskDTOList}">
								<tr>
									<td><c:out value="${usertask.userName}" /></td>
									<td><c:out value="${usertask.logStartTime}" /></td>
									<td><c:out value="${usertask.logEndTime}" /></td>
									<td><c:out value="${usertask.logDescription}" /></td>
									<td><c:out value="${usertask.totalDuration}" /></td>

								</tr>
							</c:forEach>
						</tbody>
						

					</table>

					
									<table>
										<tr>
											<td><a
												href="home?action=addWorklog&taskId=<c:out value="${task.taskId}"/>">Add
													Worklog</a></td>
										</tr>

									</table>


								</div>
							</form>

							
						</body>
						</html>
						