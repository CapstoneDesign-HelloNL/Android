package com.example.areact;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("stuNum")
    private String stuNum;
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

    public String getPassword() {
        return password;
    }

    public String getStuNum() {
        return stuNum;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }
}
