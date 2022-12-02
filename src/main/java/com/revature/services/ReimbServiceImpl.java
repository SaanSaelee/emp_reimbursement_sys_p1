package com.revature.services;

import com.revature.dao.ReimbDAO;
import com.revature.dao.ReimbDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbServiceImpl implements ReimbService{

    private static Logger logger = LoggerFactory.getLogger(ReimbServiceImpl.class);

    private static ReimbDAO reimb_DAO;

    public ReimbServiceImpl(){
        reimb_DAO = new ReimbDAOImpl();
    }

    @Override
    public boolean submit_ticket(Reimbursement ticket) {
        logger.info("ReimbServiceImpl::submit_ticket() called. Creating new ticket...");

        int id = reimb_DAO.create_ticket(ticket);

        logger.info("Received from DAO. New Ticket ID: " + id);
        return (id != 0) ? true : false;
    }

    @Override
    public List<Reimbursement> view_all_tickets() {
        logger.info("ReimbService::view_all_tickets() called. Attempting to fetch all tickets...");
        return reimb_DAO.view_all_tickets();
    }

    @Override
    public Reimbursement view_ticket_by_id(int id) {
        logger.info("ReimbService::view_ticket_by_id() called. Attempting to find ticket ID# "+ id +"...");
        return reimb_DAO.view_ticket_by_id(id);
    }

    @Override
    public boolean delete_ticket_by_id(int id) {
        logger.info("ReimbService::delete_ticket_by_id() called. Attempting to find ticket ID# "+ id +"...");
        return reimb_DAO.delete_ticket_by_id(id);
    }

    @Override
    public boolean update_ticket_status(Reimbursement status) {
        logger.info("ReimbService::update_ticket_status() called. Updating ticket...");
        return reimb_DAO.update_ticket_status(status);
    }

    @Override
    public boolean process_ticket(int id, boolean approval) {
        logger.info("ReimbService::process_ticket() called. Processing ticket ID# "+ id +"...");
        return reimb_DAO.process_ticket(id);
    }

    @Override
    public List<Reimbursement> view_tickets_by_author(int id) {
        logger.info("ReimbService::view_tickets_by_author() called. Attempting to fetch ticket by author ID.");
        return reimb_DAO.view_tickets_by_author(id);
    }

    @Override
    public List<Reimbursement> view_tickets_by_status(int id) {
        logger.info("ReimbService::filter_tickets_by_status() called. Attempting to fetch tickets by status by user " +
                "ID" +
                "." +
                "..");
        return reimb_DAO.view_tickets_by_status(id);
    }

    @Override
    public List<Reimbursement> view_all_tickets_by_status(int id) {
        logger.info("ReimbService::view_all_tickets_by_status() called. Attempting to fetch all tickets by status");
        return reimb_DAO.view_all_tickets_by_status(id);
    }
}
