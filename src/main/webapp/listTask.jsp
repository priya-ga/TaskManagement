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

<%
		if (session != null) {
			if (session.getAttribute("username") != null) {
				String name = (String) session.getAttribute("username");
				out.print("Hello, " + name + "  Welcome to ur Profile");
			} else {
				response.sendRedirect("index.jsp");
			}
		}
	%>


	<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/TaskManagement" user="root"
		password="root" />

	<sql:query var="listTasks" dataSource="${myDS}">
        SELECT * FROM task;
    </sql:query>
	<form method="post" ,value="viewAllUsersLog.jsp">
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
				<c:forEach var="task" items="${listTasks.rows}">
					<tr>
						<td><c:out value="${task.taskId}" /></td>
						<td><c:out value="${task.taskName}" /></td>
						<td><c:out value="${task.taskDescription}" /></td>
						<td><c:out value="${task.taskCreationDate}" /></td>
						<td><a
							href="HomeController?action=edit&taskId=<c:out value="${task.taskId }"/>">Update</a></td>
						<td><a
							href="HomeController?action=delete&taskId=<c:out value="${task.taskId }"/>">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<tr>

			<center>
				<a href="HomeController?action=insert">Add Task</a>
			</center>
			<center>
				<a href="HomeController?action=HomePage">Home Page</a>
			</center>
		</tr>

	</form>
</body>


<%-- </head>
<body>
    <table>
        <thead>
            <tr>
                <th>Task Id</th>
                <th>Task Name</th>
                
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
        
       
            <c:forEach items="${task}" var="task">
                <tr>
                    <td><c:out value="${task.taskId}" /></td>
                    <td><c:out value="${task.taskName}" /></td>
                   
                    <td><a
                        href="HomeController?action=edit&taskId=<c:out value="${task.taskId }"/>">Update</a></td>
                    <td><a
                        href="HomeController?action=delete&taskId=<c:out value="${task.taskId }"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="HomeController?action=insert">Add Task</a>
    </p>
</body> --%>
</html>