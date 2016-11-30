package com.todo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.dao.IUserDAO;
import com.todo.model.User;
import com.todo.util.DBUtil;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User getUserByUserId(int userId) {

        System.out.println("in getUser by UserId");
        boolean st = false;
        User dbUser = new User();
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement ps = con.prepareStatement("select * from user where userid=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

            dbUser.setEmail(rs.getString("email"));
            dbUser.setUname(rs.getString("uname"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dbUser ::" + dbUser);
        return dbUser;
    }

    @Override
    public User getUserByuserName(String userName) {
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

}
