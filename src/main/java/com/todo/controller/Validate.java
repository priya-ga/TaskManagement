package com.todo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        
        
       
     }catch(Exception e)
     {
         e.printStackTrace();
     }
        return st;                 
 }

	
	
	
	

	

	

}