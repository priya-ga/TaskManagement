package com.todo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.todo.model.Task;
import com.todo.model.User;
import com.todo.util.DBUtil;

public class Validate
{
    public static boolean checkUser(String uname,String pass) 
    {
    	
    	boolean st =false;
     try{

        Connection con=DBUtil.getConnection();
                       
        PreparedStatement ps =con.prepareStatement
                            ("select * from user where uname=? and pass=?");
        ps.setString(1, uname);
        ps.setString(2, pass);
        ResultSet rs =ps.executeQuery();
        st = rs.next();
        
        System.out.println("I AM ST , : " +st);
       
     }catch(Exception e)
     {
         e.printStackTrace();
     }
        return st;                 
 }

	public static User getUserByuserName(String userName)
	{
		System.out.println("in getUser by Username");
		boolean st =false;
		User dbUser = new User();
		 try{
		Connection con=DBUtil.getConnection();
        
        PreparedStatement ps =con.prepareStatement
                            ("select * from user where uname=?");
        ps.setString(1, userName);
        ResultSet rs =ps.executeQuery();
        st = rs.next();
        
        	
        	dbUser.setUserId(rs.getInt("userId"));
        	dbUser.setEmail(rs.getString("email"));
        	dbUser.setUname(rs.getString("uname"));
		 }catch(Exception e)
	     {
	         e.printStackTrace();
	     }
        	System.out.println("dbUser ::"+dbUser);
		return dbUser;
	}   
	
	
	

	public User getUserByUserId(int userId) {
		
		System.out.println("in getUser by UserId");
		boolean st =false;
		User dbUser = new User();
		 try{
			 Connection con=DBUtil.getConnection();
        
        PreparedStatement ps =con.prepareStatement("select * from user where userid=?");
        ps.setInt(1, userId);
        ResultSet rs =ps.executeQuery();
        st = rs.next();
        
        	dbUser.setEmail(rs.getString("email"));
        	dbUser.setUname(rs.getString("uname"));
		 }catch(Exception e)
	     {
	         e.printStackTrace();
	     }
        	System.out.println("dbUser ::"+dbUser);
		return dbUser;
	}

	

}