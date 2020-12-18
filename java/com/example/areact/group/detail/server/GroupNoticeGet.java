package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupNoticeGet {
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("author")
        private String author;
        @SerializedName("photo")
        private String photo;
        @SerializedName("createdAt")
        private String createdAt;

        public String getPhoto() {
            return photo;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getTitle() {
            return title;
        }

        public Integer getId() {
            return id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
