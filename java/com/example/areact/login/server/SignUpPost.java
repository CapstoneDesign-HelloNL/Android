package com.example.areact.login.server;

import com.google.gson.annotations.SerializedName;

public class SignUpPost {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("pwd")
    private String pwd;
    @SerializedName("school")
    private String school;
    @SerializedName("stdNum")
    private String stdNum;
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;


    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getStdNum() {
        return stdNum;
    }

    public String getSchool() {
        return school;
    }

    public String getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setStuNum(String stdNum) {
        this.stdNum = stdNum;
    }
}
