package com.revature;

import com.revature.controllers.ReimbController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    public static Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        //here is the main entry point for our app
        //here we will first start the server using Javalin
        //from there, we can expose our endpoints

        Javalin app = Javalin.create().start(9000);

        //BEFORE & AFTER HANDLERS FOR EACH ENDPOINT CALL
        //these methods will be called every time the endpoints are used and when they finish
        app.before(ctx -> {
            logger.info("Request at URL " + ctx.url() + " has started.");
        });

        app.after(ctx -> {
            logger.info("Request at URL " + ctx.url() + " is now complete.");
        });


        // **** HTTP REQUESTS

        //POST METHODS
        app.post("/users/register", UserController.register_user);
        app.post("/users/login", UserController.login_user);
        app.post("/users/{id}/newticket", ReimbController.submit_ticket);

        //GET METHODS
        app.get("/users/{id}", UserController.get_user_by_id);
        app.get("/users/{id}/viewtickets", ReimbController.view_tickets_by_author);
        app.get("/users/{id}/{status}", ReimbController.view_tickets_by_status);
        app.get("/ticket/all/{status}", ReimbController.view_all_tickets_by_status);
        app.get("/ticket/all", ReimbController.view_all_tickets);
        app.get("/ticket/{id}", ReimbController.view_ticket_by_id);

        //PUT METHODS
        app.put("/users/{id}", UserController.update_user);
        app.put( "/users/1/status", ReimbController.update_ticket_status);

        //DELETE METHODS
        app.delete("/users/{id}", UserController.delete_user);
        app.delete( "ticket/{id}", ReimbController.delete_ticket_by_id);

    }
}
