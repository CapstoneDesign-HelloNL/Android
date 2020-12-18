package com.example.areact.feed;

public class FeedPostComment {
    private String name;
    private String comment;
    private String date;

    FeedPostComment(String name, String comment, String date) {
        this.name = name;
        this.comment = comment;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
