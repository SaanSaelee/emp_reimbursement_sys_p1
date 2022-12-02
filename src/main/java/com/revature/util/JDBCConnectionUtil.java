package com.revature.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionUtil {
    //this utility class will establish a database connection between this Java program and our SQL database

    public static Logger logger = LoggerFactory.getLogger(JDBCConnectionUtil.class);

    //make our connection
    public static Connection getConnection(){

        //always set this to null
        Connection conn = null;

        try {
            //1. establish our DB credentials (URL for the host, username, & password)
            //2. establish our connection's DriverManager to make a new connection based on provided credentials

            logger.info(String.format("Making a DB connection with credentials: \nURL: %s \nUsername: %s \nPassword: " +
                            "%s",
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD")));

            conn = DriverManager.getConnection(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USERNAME"),
                    System.getenv("DB_PASSWORD"));

            logger.info("You have successfully connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
