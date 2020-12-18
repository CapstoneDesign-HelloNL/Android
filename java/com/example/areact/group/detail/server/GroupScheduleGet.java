package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupScheduleGet {
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;

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
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("content")
        private String content;
        @SerializedName("author")
        private String author;
        @SerializedName("groupName")
        private String groupName;
        @SerializedName("startDate")
        private String startDate;

        public void setStartDate(String startDate) {
            this.startDate = startDate;
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

        public void setId(String id) {
            this.id = id;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getStartDate() {
            return startDate;
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

        public String getGroupName() {
            return groupName;
        }

        public String getId() {
            return id;
        }
    }
}

