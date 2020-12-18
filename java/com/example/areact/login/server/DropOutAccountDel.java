package com.example.areact.login.server;

import com.google.gson.annotations.SerializedName;

public class DropOutAccountDel {
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
