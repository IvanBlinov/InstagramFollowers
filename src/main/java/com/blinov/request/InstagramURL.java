package com.blinov.request;

public class InstagramURL {

    public static String getFolowersByIdURL(String userId) {
        return "https://www.instagram.com/graphql/query/?query_id=17851374694183129&id=" + userId +
                "&first=50&after=";
    }

    public static String getFolowersByIdURL(String userId, String after) {
        return "https://www.instagram.com/graphql/query/?query_id=17851374694183129&id=" + userId +
                "&first=50&after=" + after;
    }

    public static String getInstagramURL() {
        return "https://www.instagram.com";
    }

    public static String getInstagramLoginURL() {
        return "https://www.instagram.com/accounts/login/ajax/";
    }
}
