package com.example.areact.group.server;

import com.google.gson.annotations.SerializedName;

public class GroupDelete {
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
}
