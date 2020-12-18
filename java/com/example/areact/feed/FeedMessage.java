package com.example.areact.feed;

public class FeedMessage {
    private String name;
    private String content;

    FeedMessage(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
