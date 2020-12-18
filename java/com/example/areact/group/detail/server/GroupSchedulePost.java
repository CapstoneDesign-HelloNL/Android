package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

public class GroupSchedulePost {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("author")
    private String author;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
