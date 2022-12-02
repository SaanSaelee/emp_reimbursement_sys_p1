package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.util.JDBCConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReimbDAOImpl implements ReimbDAO{

    private static Logger logger = LoggerFactory.getLogger(ReimbDAOImpl.class);

    //create connection
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ReimbDAOImpl() {
        conn = JDBCConnectionUtil.getConnection();
    }


    @Override
    public int create_ticket(Reimbursement ticket) {

        try {
            logger.info("ReimbDAOImpl::create_ticket() - creating reimbursement ticket");

            String sql = "INSERT INTO reimbursement (reimb_amount, reimb_submitted, " +
                    "reimb_description, reimb_author, reimb_status_id, reimb_type_id, reimb_status_type) VALUES(?,?," +
                    "?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, ticket.getReimb_amount());
            pstmt.setTimestamp(2, java.sql.Timestamp.from(Instant.now()));
            pstmt.setString(3, ticket.getReimb_description());
            pstmt.setInt(4, ticket.getReimb_author());
            pstmt.setInt(5, 3);
            pstmt.setInt(6, 5);
            pstmt.setString(7, "pending");
//            pstmt.setBytes(4, ticket.getReimb_receipt());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            rs.next();

            logger.info("ReimbDAOImpl::create_ticket() - new ticket ID# is " + rs.getInt(1));

            return rs.getInt("reimb_id");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public List<Reimbursement> view_all_tickets() {

        List<Reimbursement> ticket_list = new ArrayList<>();

        try {
            logger.info("ReimbDAOImpl::view_all_tickets() - fetching all submitted tickets");

            String sql = "SELECT * FROM reimbursement;";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                Reimbursement reimb = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getInt("reimb_amount"),
                        rs.getTimestamp("reimb_submitted"),
                        rs.getTimestamp("reimb_resolved"),
                        rs.getString("reimb_description"),
                        rs.getBytes("reimb_receipt"),
                        rs.getInt("reimb_author"),
                        rs.getInt("reimb_resolver"),
                        rs.getInt("reimb_status_id"),
                        rs.getString("reimb_status_type"),
                        rs.getInt("reimb_type_id")
                        );

                ticket_list.add(reimb);
            }

            logger.info("ReimbDAOImpl::view_all_tickets() - fetched all submitted tickets");

            return ticket_list;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Reimbursement view_ticket_by_id(int id) {

        try {
            logger.info("ReimbDAOImpl::view_ticket_by_id() - fetching ticket by ID...");

            String sql = "SELECT * FROM reimbursement WHERE reimb_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            Reimbursement target = new Reimbursement();

            while(rs.next()) {
                target.setReimb_id(rs.getInt("reimb_id"));
                target.setReimb_amount(rs.getInt("reimb_amount"));
                target.setReimb_submitted(rs.getTimestamp("reimb_submitted"));
                target.setReimb_resolved(rs.getTimestamp("reimb_resolved"));
                target.setReimb_description(rs.getString("reimb_description"));
                target.setReimb_receipt(rs.getBytes("reimb_receipt"));
                target.setReimb_author(rs.getInt("reimb_author"));
                target.setReimb_resolver(rs.getInt("reimb_resolver"));
                target.setReimb_status_id(rs.getInt("reimb_status_id"));
                target.setReimb_status_type(rs.getString("reimb_status_type"));
                target.setReimb_type_id(rs.getInt("reimb_type_id"));
            }

            logger.info("ReimbDAOImpl::view_ticket_by_id() - fetched ticket by ID.");

            return target;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reimbursement> view_tickets_by_author(int id) {

        List<Reimbursement> ticket_list = new ArrayList<>();

        try {
            logger.info("ReimbDAOImpl::view_tickets_by_author() - fetching all tickets by author...");

            String sql = "SELECT * FROM reimbursement WHERE reimb_author=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                Reimbursement reimb = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getInt("reimb_amount"),
                        rs.getTimestamp("reimb_submitted"),
                        rs.getTimestamp("reimb_resolved"),
                        rs.getString("reimb_description"),
                        rs.getBytes("reimb_receipt"),
                        rs.getInt("reimb_author"),
                        rs.getInt("reimb_resolver"),
                        rs.getInt("reimb_status_id"),
                        rs.getString("reimb_status_type"),
                        rs.getInt("reimb_type_id")
                );

                ticket_list.add(reimb);
            }

            logger.info("ReimbDAOImpl::view_tickets_by_author() - fetched all tickets by author.");

            return ticket_list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reimbursement> view_tickets_by_status(int id) {

        List<Reimbursement> ticket_list = new ArrayList<>();

        try {
            logger.info("ReimbDAOImpl::view_tickets_by_status() - fetching tickets by status by user ID...");

            String sql = "SELECT * FROM reimbursement WHERE reimb_status_id=?";
//            String sql = "SELECT * FROM reimbursement WHERE reimb_status_type=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {

                Reimbursement reimb = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getInt("reimb_amount"),
                        rs.getTimestamp("reimb_submitted"),
                        rs.getTimestamp("reimb_resolved"),
                        rs.getString("reimb_description"),
                        rs.getBytes("reimb_receipt"),
                        rs.getInt("reimb_author"),
                        rs.getInt("reimb_resolver"),
                        rs.getInt("reimb_status_id"),
                        rs.getString("reimb_status_type"),
                        rs.getInt("reimb_type_id")
                );

                ticket_list.add(reimb);
            }

            logger.info("ReimbDAOImpl::view_tickets_by_status() - fetched all tickets by status by user ID.");

            return ticket_list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Reimbursement> view_all_tickets_by_status(int id) {

            List<Reimbursement> ticket_list = new ArrayList<>();

            try {
                logger.info("ReimbDAOImpl::view_tickets_by_status() - fetching all tickets by status...");

                String sql = "SELECT * FROM reimbursement WHERE reimb_status_id=?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                while(rs.next()) {

                    Reimbursement reimb = new Reimbursement(
                            rs.getInt("reimb_id"),
                            rs.getInt("reimb_amount"),
                            rs.getTimestamp("reimb_submitted"),
                            rs.getTimestamp("reimb_resolved"),
                            rs.getString("reimb_description"),
                            rs.getBytes("reimb_receipt"),
                            rs.getInt("reimb_author"),
                            rs.getInt("reimb_resolver"),
                            rs.getInt("reimb_status_id"),
                            rs.getString("reimb_status_type"),
                            rs.getInt("reimb_type_id")
                    );

                    ticket_list.add(reimb);
                }

                logger.info("ReimbDAOImpl::view_tickets_by_status() - fetched all tickets by status");

                return ticket_list;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return null;
        }


    @Override
    public List<Reimbursement> view_tickets_by_type(int id) {
        return null;
    }

    @Override
    public boolean delete_ticket_by_id(int id) {
        try {
            logger.info("ReimbDAOImpl::delete_ticket_by_id() - deleting ticket by ID...");

            String sql = "DELETE FROM reimbursement WHERE reimb_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            if (pstmt.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            logger.error("UserDAOImpl::delete_ticket_by_id() exception thrown! Message: " + e.getMessage());
        }

        return true;
        }

    @Override
    public boolean update_ticket_status(Reimbursement ticket) {

//        try {
//            logger.info("ReimbDAOImpl::update_ticket_status() - checking ticket status for approval or denial...");
//
//            String sql = "SELECT reimb_status_type FROM reimbursement WHERE reimb_status_id=?";
//
//            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            pstmt.setInt(1, ticket.getReimb_id());
//
//            rs = pstmt.executeQuery();
//
//            rs.next();
//
//            logger.info("ReimbDAOImpl::update_ticket_status() - ticket status is...");
//
//            if (rs.getString("reimb_status_type").equals("approved") || rs.getString("reimb_status_type").equals(
//                    "denied")) {
//                return false;
//            }
//        } catch (SQLException e) {
//            logger.info("ReimbDAOImpl::update_ticket_status() - ticket status check failed.");
//
//            return false;
//        }

        try {
            logger.info("ReimbDAOImpl::create_ticket() - creating reimbursement ticket");

            String sql = "UPDATE reimbursement SET reimb_resolver=?, reimb_status_id=?, reimb_status_type=? WHERE " +
                    "reimb_id=?";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, 1);
            pstmt.setInt(2, ticket.getReimb_status_id());
            pstmt.setString(3, ticket.getReimb_status_type());
            pstmt.setInt(4, ticket.getReimb_id());

            if (pstmt.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            logger.error("UserDAOImpl::update_user() " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean process_ticket(int id) {
        return false;
    }
}
