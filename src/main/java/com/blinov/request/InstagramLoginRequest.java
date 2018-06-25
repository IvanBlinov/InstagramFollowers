package com.blinov.request;

public class InstagramLoginRequest {

    private String username;
    private String password;
    private String queryParams;

    public InstagramLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        queryParams = "{}";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", queryParams=" + queryParams +
                '}';
    }
}
