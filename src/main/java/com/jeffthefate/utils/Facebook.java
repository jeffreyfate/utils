package com.jeffthefate.utils;

import com.restfb.FacebookClient;

public class Facebook {

    private FacebookClient facebookClient;
    private String facebookPageId;

    public Facebook(FacebookClient facebookClient, String facebookPageId) {
        this.facebookClient = facebookClient;
        this.facebookPageId = facebookPageId;
    }

    public String getFacebookPageId() {
        return facebookPageId;
    }

    public FacebookClient getFacebookClient() {
        return facebookClient;
    }
}
