<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>Add Worklog</title>
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
	
	
    <form action="UserTask" method="post">
        <fieldset>
            <div>
                <label for="userId"></label> <input type="hidden"
                    name="userId" value="<c:out value="${userID}" />"
                    readonly="readonly" placeholder="User ID" />
            </div>
            <div>
                <label for="taskId"></label> <input type="hidden"
                    name="taskId" value="<c:out value="${task.taskId}" />"
                    readonly="readonly" placeholder="Task ID" />
            </div>
            <div>
                <label for="logStartTime">Log Start Time</label> <input type="datetime-local"
                    name="logStartTime" value="<c:out value="${usertask.logStartTime}" />"
                    placeholder="Log Start Time" />
            </div>
            <div>
                <label for="logEndTime">Log End Time</label> <input type="datetime-local" name="logEndTime"
                    value="<c:out value="${usertask.logEndTime}" />" placeholder="Log End Time" />
            </div>
            <div>
                <label for="logDescription">Log Description</label> <input type="text" name="logDescription"
                    value="<c:out value="${usertask.logDescription}" />" placeholder="Log Description" />
            </div>
            <div>
                <input type="submit" value="Submit" />
            </div>
        </fieldset>
    </form>
</body>
</html>