<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <%
		if (session != null) {
			if (session.getAttribute("username") != null) {
				String name = (String) session.getAttribute("username");
				out.print("Hello, " + name + "  Welcome to ur Profile");
			} else {
				response.sendRedirect("index.jsp");
				return;
			}
		}
	%> --%>

<%@ page import ="java.sql.*" %>
<%
	

    String user = request.getParameter("uname");    
    String pwd = request.getParameter("pass");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String email = request.getParameter("email");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManagement",
            "root", "root");
    Statement st = con.createStatement();
    //ResultSet rs;
    
    
	boolean validEmail = (email != null) && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	boolean validPass = (pwd != null);
     	
    if (validEmail== true && validPass == true)
    {
    int i = st.executeUpdate("insert into user(first_name, last_name, email, uname, pass, regdate) values ('" + fname + "','" + lname + "','" + email + "','" + user + "','" + pwd + "', CURDATE())");
    out.print("VALUE OF I " +i);
    if (i > 0 ) {
    	/* session.setAttribute("userId",userId); */
        session.setAttribute("username", user);
        response.sendRedirect("welcome.jsp");
       // out.print("Registration Successfull!"+"<a href='index.jsp'>Go to Login</a>");
    } else {
        response.sendRedirect("index.jsp");
    }
    }
    else  
    {
    	out.print("Enter correct Email address or password");
    	response.sendRedirect("reg.jsp");
    }
    
        
%>
</body>
</html>