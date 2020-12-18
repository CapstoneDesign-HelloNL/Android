package com.example.areact.group.server;

import com.google.gson.annotations.SerializedName;

public class GroupSearchOneGet {
    @SerializedName("data")
    private Data data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("name")
        private String name;

        @SerializedName("univName")
        private String univName;

        @SerializedName("createdAt")
        private String createAt;

        public String getName() {
            return name;
        }

        public String getUnivName() {
            return univName;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnivName(String univName) {
            this.univName = univName;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }
    }
}
