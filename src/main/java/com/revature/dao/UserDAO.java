package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {

    int create_user(User user);
    User get_user_by_id(int id);
    User get_user_by_username(String username, String password);
    boolean update_user(User user);
    boolean delete_user_by_id(int id);
}


