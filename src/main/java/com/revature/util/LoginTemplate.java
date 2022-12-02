package com.revature.util;

public class LoginTemplate {
    //this class is being used to convert our HTTP requests into java objects
    //assists with Jackson Databind

    String ers_username;
    String ers_password;

    public LoginTemplate() {
        super();
    }

    public LoginTemplate(String username, String password) {
        this.ers_username = username;
        this.ers_password = password;
    }

    public String getErs_username() {
        return ers_username;
    }

    public void setErs_username(String ers_username) {
        this.ers_username = ers_username;
    }

    public String getErs_password() {
        return ers_password;
    }

    public void setErs_password(String ers_password) {
        this.ers_password = ers_password;
    }

    @Override
    public String toString() {
        return "LoginTemplate{" +
                "username='" + ers_username + '\'' +
                ", password='" + ers_password + '\'' +
                '}';
    }
}
