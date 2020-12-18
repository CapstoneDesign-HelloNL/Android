package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupHamPersonGet {
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<MyData> data;

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public List<MyData> getData() {
        return data;
    }

    public void setData(List<MyData> data) {
        this.data = data;
    }

    public static class MyData {
        @SerializedName("memberEmail")
        private String memberEmail;
        @SerializedName("memberRank")
        private String memberRank;

        public String getMemberRank() {
            return memberRank;
        }

        public String getMemberEmail() {
            return memberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public void setMemberRank(String memberRank) {
            this.memberRank = memberRank;
        }
    }
}

