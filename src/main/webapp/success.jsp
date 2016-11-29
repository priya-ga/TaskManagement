<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<%
		if ((session.getAttribute("username") == null)
				|| (session.getAttribute("pwd") == "")) {
	%>
	You are not logged in
	<br />
	<a href="index.jsp">Please Login</a>
	<%
		} else {
	%>
	Welcome
	<%-- <%=session.getAttribute(name) %> --%>
	<%=session.getAttribute("username")%>

	<form method="post" action="HomeController">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2"><a
							href="${contextPath}/TaskManagement/listTask.jsp">Create
								Task</a></th>
					</tr>
				</thead>


			</table>
		</center>
	</form>
	<form method="post" action="HomeController">
		<center>
			<table>
			<thead>
					<tr>
						<th colspan="2"><a
							href="${contextPath}/TaskManagement/viewTask.jsp">View
								Task</a></th>
					</tr>
				</thead>
			</table>
		</center>

	</form>

	<a href="UserController?action=logout">Log Out</a>
	<%} %>
</body>
</html>