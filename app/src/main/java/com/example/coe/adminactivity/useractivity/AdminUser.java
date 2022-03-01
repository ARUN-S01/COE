package com.example.coe.adminactivity.useractivity;

public class AdminUser {

    private String userName;
    private String userEmail;

    public AdminUser(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public AdminUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
