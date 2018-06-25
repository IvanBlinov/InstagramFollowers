package com.blinov.generator;

import com.blinov.cache.InstagramCache;
import com.blinov.entity.Instagram;
import com.blinov.request.InstagramURL;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class InstagramRequestGenerator {

    public static HttpGet generateFirstRequest() {

        String url = InstagramURL.getInstagramURL();
        HttpGet request = new HttpGet(url);
        return request;
    }

    public static HttpPost generateLoginRequest(Instagram instagram, InstagramCache cache) throws UnsupportedEncodingException {

        String url = InstagramURL.getInstagramLoginURL();
        HttpPost request = new HttpPost(url);
        request.addHeader("x-csrftoken", cache.getCsrftoken());
        request.addHeader("refer", "https://www.instagram.com/accounts/login/");
        request.addHeader("cookie", "csrftoken=" + cache.getCsrftoken() +
                "; mid=" + cache.getMid());
        request.setEntity(new StringEntity("username=" + instagram.getUsername()
                + "&password=" + instagram.getPassword()
                + "&queryParams=%7B%7D"));
        return request;
    }

    public static HttpGet generateGetFollowersRequest(String userId, InstagramCache cache) {

        return generateGetFollowersRequest(userId, cache, "");
    }

    public static HttpGet generateGetFollowersRequest(String userId, InstagramCache cache, String after) {

        String url = InstagramURL.getFolowersByIdURL(userId, after);
        HttpGet request = new HttpGet(url);
        request.addHeader("x-csrftoken", cache.getCsrftoken());
        request.addHeader("refer", "https://www.instagram.com/accounts/login/");
        request.addHeader("cookie", "csrftoken=" + cache.getCsrftoken() +
                "; mid=" + cache.getMid() +
                "; ds_user_id=" + cache.getDs_user_id() +
                "; sessionid= " + cache.getSessionid());
        return request;
    }
}
