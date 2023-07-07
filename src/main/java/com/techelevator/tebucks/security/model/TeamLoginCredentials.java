package com.techelevator.tebucks.security.model;
//Store our teams login
public class TeamLoginCredentials {
    private String username = "team02";
    private String password = "password";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
