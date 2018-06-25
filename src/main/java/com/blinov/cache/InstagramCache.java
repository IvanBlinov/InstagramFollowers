package com.blinov.cache;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;

public class InstagramCache {

    private String csrftoken;
    private String sessionid;
    private String mid;
    private String ds_user_id ;

    public InstagramCache() {
    }

    public InstagramCache(String csrftoken, String sessionid, String mid, String ds_user_id) {
        this.csrftoken = csrftoken;
        this.sessionid = sessionid;
        this.mid = mid;
        this.ds_user_id = ds_user_id;
    }

    public InstagramCache(HttpResponse response) {
        setParameters(response);
    }

    public void setCachedParameters(HttpResponse response) {
        setParameters(response);
    }

    private void setParameters(HttpResponse response) {
        for (Header header : response.getAllHeaders()) {
            for (HeaderElement element : header.getElements()) {
                if (!"".equals(element.getValue())) {
                    switch (element.getName()) {
                        case "csrftoken" : {
                            this.csrftoken = element.getValue();
                            break;
                        }
                        case "sessionid" : {
                            this.sessionid = element.getValue();
                            break;
                        }
                        case "mid" : {
                            this.mid = element.getValue();
                            break;
                        }
                        case "ds_user_id" : {
                            this.ds_user_id = element.getValue();
                            break;
                        }
                    }
                }
            }
        }
    }

    public String getCsrftoken() {
        return csrftoken;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getMid() {
        return mid;
    }

    public String getDs_user_id() {
        return ds_user_id;
    }
}
