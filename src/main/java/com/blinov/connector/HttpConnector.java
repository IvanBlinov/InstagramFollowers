package com.blinov.connector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpConnector {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36";
    private HttpClient client = HttpClientBuilder.create().build();

    public HttpResponse sendRequest(HttpRequestBase request) throws Exception {
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Accept-Language", "en-US,en;q=0.5");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse response = client.execute(request);
        return response;
    }
}
