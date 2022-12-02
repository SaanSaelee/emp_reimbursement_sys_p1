package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;
import com.revature.services.ReimbServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ReimbController {

    private static Logger logger = LoggerFactory.getLogger(ReimbController.class);

    private static ReimbService reimb_serv = new ReimbServiceImpl();
//    private static UserService user_serv = new UserServiceImpl();

    public static Handler submit_ticket = ctx -> {
        logger.info("User is submitting a ticket...");

        String body = ctx.body();
        int id = Integer.parseInt(ctx.pathParam("id"));

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        Reimbursement target = om.readValue(body, Reimbursement.class);
        logger.info("New Ticket: " + target);

//        String cookie = ctx.cookieStore().get("Auth-Cookie");
//        int author_id = user_serv.get_user_by_id(cookie);
        target.setReimb_author(id);

        boolean isSubmitted = reimb_serv.submit_ticket(target);

        if(isSubmitted) {
            ctx.html("New ticket has been submitted successfully.");
        } else {
            ctx.html("Error during submission. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler view_all_tickets = ctx -> {
        logger.info("Fetching all submitted tickets...");

        String body = ctx.body();

        List<Reimbursement> all_tickets = reimb_serv.view_all_tickets();

        if(all_tickets != null) {
            ctx.json(all_tickets);
        } else {
            ctx.html("Error during fetch. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler view_ticket_by_id = ctx -> {
        logger.info("Fetching ticket by id...");

        int id = Integer.parseInt(ctx.pathParam("id"));

        Reimbursement target = reimb_serv.view_ticket_by_id(id);

        if (target != null || target.getReimb_author() != 0) {
            ctx.json(target);
        } else {
            ctx.html("Error fetching ticket by id. Try again.");
            ctx.status(HttpStatus.NOT_FOUND_404);
        }

    };

    public static Handler view_tickets_by_author = ctx -> {
        logger.info("Fetching tickets by author...");

        String body = ctx.body();
        int id = Integer.parseInt(ctx.pathParam("id"));

        List<Reimbursement> all_tickets = reimb_serv.view_tickets_by_author(id);

        if(all_tickets != null) {
            ctx.json(all_tickets);
        } else {
            ctx.html("Error during fetch. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler view_tickets_by_status = ctx -> {
        logger.info("Fetching tickets by status...");

        String body = ctx.body();
        int id = Integer.parseInt(ctx.pathParam("id"));
        int status = Integer.parseInt(ctx.pathParam("status"));

        List<Reimbursement> all_tickets = reimb_serv.view_tickets_by_status(status);

        if(all_tickets != null) {
            ctx.json(all_tickets);
        } else {
            ctx.html("Error during fetch. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler view_all_tickets_by_status = ctx -> {
        logger.info("Fetching tickets by status...");

        String body = ctx.body();
        int status = Integer.parseInt(ctx.pathParam("status"));

        List<Reimbursement> all_tickets = reimb_serv.view_all_tickets_by_status(status);

        if(all_tickets != null) {
            ctx.json(all_tickets);
        } else {
            ctx.html("Error during fetch. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    };

    public static Handler delete_ticket_by_id = ctx -> {

        //1. get user id from path
        int id = Integer.parseInt(ctx.pathParam("id"));

        //2. do service call
        boolean isDeleted = reimb_serv.delete_ticket_by_id(id);

        //3. render response

        if(isDeleted) {
            ctx.html("Ticket ID# "+ id +" has been successfully removed from the system.");
        }else {
            ctx.html("Error during deletion. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }

    };

    public static Handler update_ticket_status = ctx -> {
        logger.info("Manager is updating a ticket status...");

        String body = ctx.body();
//        int id = Integer.parseInt(ctx.pathParam("status"));

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        Reimbursement target = om.readValue(body, Reimbursement.class);

        boolean isUpdated = reimb_serv.update_ticket_status(target);

        if(isUpdated) {
            ctx.html("Ticket status has been updated successfully.");
//            ctx.json(reimb_serv.update_ticket_status(target));
        } else {
            ctx.html("Error occurred during ticket status update. Try again.");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }

    };


}
