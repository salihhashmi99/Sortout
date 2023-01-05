package com.metalinko.fyp.Model;

public class SecurityLoginModel {

    private String user_id , password;


    public SecurityLoginModel()
    {

        // Empty
    }

    public SecurityLoginModel(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
