package com.example.areact.login.server;

import com.google.gson.annotations.SerializedName;

public class LogoutAccountPost {
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
}
