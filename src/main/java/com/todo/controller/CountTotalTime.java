package com.todo.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class CountTotalTime 
{
	public static void main(String[] argv) throws Exception {
	    int sum = 0;
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManagement",
	        "root", "root");
	    Statement st = con.createStatement();
	    ResultSet res = st.executeQuery("SELECT logStartTime FROM usertask");
	    while (res.next()) {
	      int c = res.getInt(1);
	      sum = sum + c;
	    }
	    System.out.println("Sum of column = " + sum);
	  }
}