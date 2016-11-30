package com.todo.dao;

import com.todo.model.User;

public interface IUserDAO {
    
     User getUserByUserId(int userId);
     
     User getUserByuserName(String userName);

}
