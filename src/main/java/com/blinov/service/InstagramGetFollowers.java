package com.blinov.service;

import com.blinov.cache.InstagramCache;
import com.blinov.connector.HttpConnector;
import com.blinov.entity.Instagram;
import com.blinov.generator.InstagramRequestGenerator;
import com.blinov.response.InstagramFollowersResponse;
import com.blinov.response.InstagramFollowersResponse.Edge;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InstagramGetFollowers {

    private Instagram instagram;
    private HttpConnector connector;
    private InstagramCache cache;

    public InstagramGetFollowers(Instagram instagram) {
        this.instagram = instagram;
        this.connector = new HttpConnector();
        this.cache = new InstagramCache();
    }

    public List<String> getFollowersId(String userId) throws Exception{

        HttpGet firstRequest = InstagramRequestGenerator.generateFirstRequest();
        HttpResponse response = connector.sendRequest(firstRequest);
        cache.setCachedParameters(response);
        firstRequest.releaseConnection();
        logIntoInstagram();
        return getFollowers(userId);
    }

    private void logIntoInstagram() throws Exception {

        HttpPost loginRequest = InstagramRequestGenerator.generateLoginRequest(instagram, cache);
        HttpResponse loginResponse = connector.sendRequest(loginRequest);
        cache.setCachedParameters(loginResponse);
        loginRequest.releaseConnection();
    }

    private List<String> getFollowers(String userId) throws Exception {

        List<String> ids = new ArrayList<>();
        boolean hasNextPage = true;
        String endCursor = "";
        while (hasNextPage) {
            HttpGet getFollowersRequest = InstagramRequestGenerator.generateGetFollowersRequest(userId, cache, endCursor);
            HttpResponse httpFollowersResponse = connector.sendRequest(getFollowersRequest);
            InstagramFollowersResponse getFollowersResponse = getFollowersResponse(httpFollowersResponse);
            if (getFollowersResponse == null || getFollowersResponse.getData() == null) {
                break;
            }
            addIds(ids, getFollowersResponse);
            getFollowersRequest.releaseConnection();
            endCursor = getFollowersResponse.getData().getUser().getEdge_followed_by().getPage_info().getEnd_cursor();
            hasNextPage = getFollowersResponse.getData().getUser().getEdge_followed_by().getPage_info().has_next_page();
        }
        return ids;
    }

    private void addIds(List<String> ids, InstagramFollowersResponse followersResponse) {

        for (Edge edge : followersResponse.getData().getUser().getEdge_followed_by().getEdges()) {
            ids.add(edge.getNode().getId());
        }
    }

    private String printResponse(HttpResponse response) throws IOException {

        print(response.getStatusLine().getStatusCode());
        print(response.getStatusLine().getReasonPhrase());
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            for (HeaderElement element : header.getElements()) {
                print(element.getName() + " : " + element.getValue());
            }
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        print(result.toString());
        return result.toString();
    }

    private InstagramFollowersResponse getFollowersResponse(HttpResponse response) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        Gson g = new Gson();
        InstagramFollowersResponse followersResponse = g.fromJson(result.toString(), InstagramFollowersResponse.class);
        return followersResponse;
    }

    private void print(String name, String value) {
        System.out.println(name + " : " + value);
    }

    private void print(String text) {
        System.out.println(text);
    }

    private void print(int text) {
        System.out.println(text);
    }
}
