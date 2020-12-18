package com.example.areact.group.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupSearchAllGet {
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("name")
        private String name;

        @SerializedName("univName")
        private String school;

        @SerializedName("joinCode")
        private String joinCode;

        public String getName() {
            return name;
        }

        public String getSchool() {
            return school;
        }

        public String getJoinCode() {
            return joinCode;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public void setJoinCode(String joinCode) {
            this.joinCode = joinCode;
        }
    }
}