package com.example.areact.feed;

public class FeedPost {
    private String name;
    private String date;
    private String body;

    FeedPost(String name, String date, String body) {
        this.name = name;
        this.date = date;
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }
}