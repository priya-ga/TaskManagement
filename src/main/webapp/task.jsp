<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%@include file="header.jsp" %>
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
	<form action="home" method="post">
		<fieldset>

			 <div>

				<label for="taskId"></label> <input type="hidden"  name="taskId"
					value="<c:out value="${task.taskId}" />" readonly="readonly"
					placeholder="Task ID" />
			</div> 
			
			<%-- <input type="hidden"  name="userId" value="${task.userId}">  --%>
			
			<div>
				<label for="taskName">Task Name</label> <input type="text"
					name="taskName" value="<c:out value="${task.taskName}" />"
					placeholder="Task Name" />
			</div>
			<div>
				<label for="taskDescription">Task Description</label> <input type="text"
					name="taskDescription" value="<c:out value="${task.taskDescription}" />"
					placeholder="Task Description" />
			</div>
			
			<div>
				<input type="submit" value="Submit" />
			</div>
		</fieldset>
	</form>
</body>
</html>