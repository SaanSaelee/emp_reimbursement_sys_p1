package com.revature.dao;

import java.sql.*;

import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAOImpl implements UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //establish a connection here so JDBC can start preparing statements
    public UserDAOImpl() {
        conn = JDBCConnectionUtil.getConnection();
    }

    @Override
    public int create_user(User user) {
        // this will be how we insert new users into db

        try {
            //preparing our sql statement
            String sql = "INSERT INTO users (ers_username, ers_password, user_first_name, " +
                    "user_last_name, user_email, user_role_id) VALUES(?,?,?,?,?,?)";

            //we are using generated keys to allow us to get that new id number for the new user
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //now we are filling in the ? with the user data
            pstmt.setString(1, user.getErs_username());
            pstmt.setString(2, user.getErs_password());
            pstmt.setString(3, user.getUser_first_name());
            pstmt.setString(4, user.getUser_last_name());
            pstmt.setString(5, user.getUser_email());
            pstmt.setInt(6, user.getUser_role_id());

            //note that when we are inserting, we will get back our new ID number from the ResultSet
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            //this result set works off of a cursor/pointer that starts before the first record
            //pointer keeps moving down until there are no more records
            //reads the result or information

            rs.next();

            logger.info("UserDAOImpl - create() - new user id is " + rs.getInt(1));

            return rs.getInt("ers_user_id");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //else if no user creation is done, return 0
        return 0;
    }

    @Override
    public User get_user_by_id(int id) {
        try {
            String sql = "SELECT * FROM users WHERE ers_user_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            User target = new User();

            while(rs.next()) {
                target.setErs_user_id(rs.getInt("ers_user_id"));
                target.setErs_username(rs.getString("ers_username"));
                target.setErs_password(rs.getString("ers_password"));
                target.setUser_first_name(rs.getString("user_first_name"));
                target.setUser_last_name(rs.getString("user_last_name"));
                target.setUser_email(rs.getString("user_email"));
                target.setUser_role_id(rs.getInt("user_role_id"));
            }

            return target;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public User get_user_by_username(String username, String password) {

        try {
            String sql = "SELECT ers_username, ers_password FROM users WHERE ers_username=? AND ers_password=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // by using executeQuery() we get a result set back
            rs = pstmt.executeQuery();

            //we are creating an instance of our User b/c we will have to return a User
            User user = new User();

            // this ResultSet that we get back from our query is what were iterating through in order to make out our
            // User object
            while (rs.next()) {
                user.setErs_username(rs.getString("ers_username"));
                user.setErs_password(rs.getString("ers_password"));
            }

            //now that our object's fields have been set, it can be returned that found user
            return user;

        } catch (SQLException e) {
            logger.error("UserDAOImpl::get_user_by_username() exception - Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update_user(User user) {
        // here we are going to update the database for a user

        try {
            String sql = "UPDATE users SET ers_username=?, ers_password=?, user_first_name=?, user_last_name=?, user_email=? WHERE " +
                    "ers_user_id=?";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getErs_username());
            pstmt.setString(2, user.getErs_password());
            pstmt.setString(3, user.getUser_first_name());
            pstmt.setString(4, user.getUser_last_name());
            pstmt.setString(5, user.getUser_email());
            pstmt.setInt(6, user.getErs_user_id());

            if (pstmt.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            logger.error("UserDAOImpl::update_user() " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete_user_by_id(int id) {

        try {
            String sql = "DELETE FROM users WHERE ers_user_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            // it will return false if the first result is an update count
            //			TRUE indicates that query returned a ResultSet object
            //			FALSE indicate returned an int value or returned nothing.
            // we want it to return False for our delete method because technically we are
            // not returning
            // any result we are just removing it from the db

            if (pstmt.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException e) {
            // we can use Exception even though these methods specifically throw
            // SQLExceptions
            // because Exception is the parent class of all Exceptions
            logger.error("UserDAOImpl::delete_user_by_id() exception thrown! Message: " + e.getMessage());
        }

        return true;
    }
}
