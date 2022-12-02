package com.revature.services;

import com.revature.models.User;

public interface UserService {
    //here we will provide the method calls that will handle our "business logic" for our app
    //To do so, the service layer will communicate and pass the info from the dao layer to our controller layer
    //These layers of abstraction are considered good practices for decoupling your application

    boolean register_user(User user);
    boolean login_user(String username, String password);
    boolean logout_user(String username);
    User get_user_by_id(int id);
    boolean update_user(User user);
    boolean delete_user(int id);
}
