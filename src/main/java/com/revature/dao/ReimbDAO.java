package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbDAO {

    int create_ticket(Reimbursement ticket);
    List<Reimbursement> view_all_tickets();
    List<Reimbursement> view_tickets_by_author(int id); //users can view a list of submitted tickets
    List<Reimbursement> view_tickets_by_status(int id);
    List<Reimbursement> view_all_tickets_by_status(int id);
    List<Reimbursement> view_tickets_by_type(int id);
    Reimbursement view_ticket_by_id(int id);
    boolean delete_ticket_by_id(int id);
    boolean update_ticket_status(Reimbursement ticket);
    boolean process_ticket(int id);
}
