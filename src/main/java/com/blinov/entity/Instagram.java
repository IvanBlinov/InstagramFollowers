package com.blinov.entity;

public class Instagram {

    private String username;
    private String password;

    public Instagram(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
