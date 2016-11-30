<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Tasks</title>
<body>
 <%@include file="header.jsp" %>
	<%
	    if (session != null) {
	        if (session.getAttribute("username") != null) {
	            String name = (String) session.getAttribute("uname");
	          
	        } else {
	            response.sendRedirect("index.jsp");
	        }
	    }
	%>

		<div align="center">
			<table border="1" cellpadding="5">
				<caption>
					<h2>List of Tasks</h2>
				</caption>

				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Creation Date</th>

				</tr>
				<c:forEach var="task" items="${task}">
					<tr>
						<td><c:out value="${task.taskId}" /></td>
						<td><c:out value="${task.taskName}" /></td>
						<td><c:out value="${task.taskDescription}" /></td>
						<td><c:out value="${task.taskCreationDate}" /></td>
						<td><a
							href="home?action=edit&taskId=<c:out value="${task.taskId }"/>">Update</a></td>
						<td><a
							href="home?action=delete&taskId=<c:out value="${task.taskId }"/>">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

			<center>
				<a href="home?action=insert">Add Task</a>
			</center>
			
		</tr>

	</form>
</body>



</html>