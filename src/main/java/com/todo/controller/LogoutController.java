package com.todo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
//	private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = -2865518663790143943L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LOGOUT called");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("thanq you!!, Your session was destroyed successfully!!");
		HttpSession session = request.getSession(false);
		// session.setAttribute("user", null);
		session.removeAttribute("username");
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}
}