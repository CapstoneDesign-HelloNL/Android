package com.example.areact.group.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupListGet {
    @SerializedName("data")
    private List<mData> data;
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;

    public List<mData> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


    public void setData(List<mData> data) {
        this.data = data;
    }

    public static class mData {
        @SerializedName("name")
        private String name;
        @SerializedName("univName")
        private String univName;
        @SerializedName("joinCount")
        private String join_count;

        public void setName(String name) {
            this.name = name;
        }

        public void setJoin_count(String join_count) {
            this.join_count = join_count;
        }

        public void setUnivName(String univName) {
            this.univName = univName;
        }

        public String getName() {
            return name;
        }

        public String getJoin_count() {
            return join_count;
        }

        public String getUnivName() {
            return univName;
        }
    }
}

