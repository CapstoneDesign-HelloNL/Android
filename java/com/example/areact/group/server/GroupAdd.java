package com.example.areact.group.server;

import com.google.gson.annotations.SerializedName;

public class GroupAdd {
    @SerializedName("name")
    private String name;

    @SerializedName("univName")
    private String univ_name;

    @SerializedName("joinCode")
    private String join_code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("status")
    private String status;

    public String getName() {
        return name;
    }

    public String getUniv_name() {
        return univ_name;
    }

    public String getJoin_code() {
        return join_code;
    }

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniv_name(String univ_name) {
        this.univ_name = univ_name;
    }

    public void setJoin_code(String join_code) {
        this.join_code = join_code;
    }
}
