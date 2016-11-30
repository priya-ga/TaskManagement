package com.todo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.util.Constant;
import com.todo.util.DBUtil;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Connection conn;

    public UserController() {
        conn = DBUtil.getConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String action = (String) request.getParameter("action");
        response.setContentType("text/html;charset=UTF-8");

        if (action.equals(Constant.LOGIN)) {
            PrintWriter out = response.getWriter();
            String username = request.getParameter("uname");
            String pwd = request.getParameter("pass");
            boolean valid = (username != null) && username.matches("[A-Za-z0-9_]+");
          
            if (username == "admin" && pwd == "admin") {
                out.print("Welcome admin");
                RequestDispatcher rs = request.getRequestDispatcher("success.jsp");
                rs.include(request, response);
            }
            if (valid == true && Validate.checkUser(username, pwd)) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username", username);
                RequestDispatcher rs = request.getRequestDispatcher("success.jsp");
                rs.forward(request, response);
            } else {
                out.println("Username or Password incorrect");
                RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
                rs.include(request, response);
            }
        }
    }
}