package com.example.areact.group.detail.server;

import com.google.gson.annotations.SerializedName;

public class GroupNoticeDel {
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
