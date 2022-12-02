package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;
import com.revature.util.LoginTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //since this class depends on the DAO implementation class, we will need an instance of DAO here
    private static UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
    }

    @Override
    public boolean register_user(User user) {
        //1. log event
        logger.info("UserServiceImpl::register_user() called. Creating new user...");
        //2. get the new id number (do dao method call here)
        int id = userDAO.create_user(user);
;        //3. return true if id exists else return false
        logger.info("Received from DAO. New ID# " + id);

        return (id != 0) ? true : false;
    }

    @Override
    public boolean login_user(String username, String password) {

        logger.info("UserServiceImpl::login_user() called. Logging in user...");

        //1. find user that matches username given
        User target = userDAO.get_user_by_username(username, password);

        //2. check if password/username matches records
        //true = user found, login ok
        if (target == null || target.getErs_username() == null) {
            logger.error("Could not find user.");
        } else if (target.getErs_username().equalsIgnoreCase(username) && target.getErs_password().equalsIgnoreCase(password)) {
            logger.info("User found.");
            return true;
        }

        //false = no user found, error in login
        return false;
    }

    @Override
    public boolean logout_user(String username) {
        logger.info("UserService::logout_user() called. Logging out...");
        return false;
    }

    @Override
    public User get_user_by_id(int id) {
        logger.info("UserService::get_user_by_id() called. Trying to find user ID# "+ id +"...");
        return userDAO.get_user_by_id(id);
    }

    @Override
    public boolean update_user(User user) {
        logger.info("UserService::update_user() called. Updating user ID# "+ user.getErs_user_id() +"...");
        return userDAO.update_user(user);
    }

    @Override
    public boolean delete_user(int id) {
        logger.info("UserService::delete_user() called. Deleting user ID# "+ id +"...");
        return userDAO.delete_user_by_id(id);
    }
}
