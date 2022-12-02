package com.revature.controllers;

import com.revature.dao.UserDAO;
import com.revature.util.LoginTemplate;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Handler;


public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    //since this class is dependent on the service layer, we will need an instance of it here
    private static UserService user_serv = new UserServiceImpl();


    //here we will need to set up a HTTP handler that deals solely for registration requests
    public static Handler register_user = ctx -> {
        //here we will be performing actions against our HTTP request
        //1. log event
        logger.info("User is making a registration request...");

        //2. get the user info from the request body
        String body = ctx.body();

        //here we will convert the body into a User object
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule()); //you need a jackson.datatype dependency

        //convert JSON from http request into java objects, use ObjectMapper.readValue
        User target = om.readValue(body, User.class);
        logger.info("New User: " + target);

        //3. do service call
        boolean isCreated = user_serv.register_user(target);

        //4. render the response
        if(isCreated) {
            ctx.html("New user has been registered successfully.");
            ctx.status(HttpStatus.ACCEPTED_202);
        } else {
            ctx.html("Username is taken. Try again.");
            ctx.status(HttpStatus.NOT_ACCEPTABLE_406);
        }
    };

    public static Handler login_user = ctx -> {
        //1. get user info from request body
        String body = ctx.body();

        //here we will need to convert the body into a User object
        ObjectMapper om = new ObjectMapper();
        LoginTemplate target = om.readValue(body, LoginTemplate.class);

        //2. do service call
        boolean isAuthenticated = user_serv.login_user(target.getErs_username(), target.getErs_password());

        //3. render response
        if (isAuthenticated) {
            ctx.html("You have successfully logged in. Welcome " + target.getErs_username() + "!");
            ctx.status(HttpStatus.ACCEPTED_202);
        }else {
            ctx.html("Invalid username and/or password. Please try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler get_user_by_id = ctx -> {
        //to retrieve info from the url, we can use our ContextHandler from Javalin
        int id = Integer.parseInt(ctx.pathParam("id"));

        User target = user_serv.get_user_by_id(id);

        if(target != null && target.getErs_username() != null) {
            ctx.json(target);
        }else {
            ctx.html("User with that ID# could not be found. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler update_user = ctx -> {
        //1. get user info from request body
        int id = Integer.parseInt(ctx.pathParam("id"));
        String body = ctx.body();

        //here we will need to convert the body into a User object
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        User target = om.readValue(body, User.class);
        target.setErs_user_id(id);

        //2. do service call
        boolean isUpdated = user_serv.update_user(target);

        //3. render response
        if(isUpdated) {
            ctx.html("Your user information has been updated successfully.");
//            ctx.json(target);
        }else {
            ctx.html("Error during update. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler delete_user = ctx -> {

        //1. get user id from path
        int id = Integer.parseInt(ctx.pathParam("id"));

        //2. do service call
        boolean isDeleted = user_serv.delete_user(id);

        //3. render response

        if(isDeleted) {
            ctx.html("User ID# "+ id +" has been successfully removed from the system.");
            ctx.status(HttpStatus.ACCEPTED_202);
        }else {
            ctx.html("Error during deletion. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }

    };
}