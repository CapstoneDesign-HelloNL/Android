package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

public class GroupNoticePost {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("author")
    private String author;
    @SerializedName("photo")
    private String photo;
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

    public String getPhoto() {
        return photo;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
