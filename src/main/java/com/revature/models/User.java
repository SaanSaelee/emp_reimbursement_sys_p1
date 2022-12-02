package com.revature.models;

import java.util.Objects;

public class User {

    private int ers_user_id;
    private String ers_username;
    private String ers_password;
    private String user_first_name;
    private String user_last_name;
    private String user_email;
    public int user_role_id = 2;

    //constructors
    public User(){
        super();
    }

    public User(int ers_user_id, String ers_username, String ers_password, String user_first_name, String user_last_name, String user_email, int user_role_id) {
        this.ers_user_id = ers_user_id;
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_email = user_email;
        this.user_role_id = user_role_id;
    }

    public User(String ers_username, String ers_password, String user_first_name, String user_last_name, String user_email, int user_role_id) {
        this.ers_username = ers_username;
        this.ers_password = ers_password;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.user_email = user_email;
        this.user_role_id = user_role_id;
    }

    //getters and setters
    // * once everything checks out, use Lombok for easier compilation
    public int getErs_user_id() {
        return ers_user_id;
    }

    public void setErs_user_id(int ers_user_id) {
        this.ers_user_id = ers_user_id;
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

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    //boilerplate methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ers_user_id == user.ers_user_id && user_role_id == user.user_role_id && Objects.equals(ers_username, user.ers_username) && Objects.equals(ers_password, user.ers_password) && Objects.equals(user_first_name, user.user_first_name) && Objects.equals(user_last_name, user.user_last_name) && Objects.equals(user_email, user.user_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ers_user_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + ers_user_id +
                ", username='" + ers_username + '\'' +
                ", password='" + ers_password + '\'' +
                ", firstName='" + user_first_name + '\'' +
                ", lastName='" + user_last_name + '\'' +
                ", email='" + user_email + '\'' +
                ", userRoleId=" + user_role_id +
                '}';
    }
}
