package com.example.areact.login.server;

import com.google.gson.annotations.SerializedName;

public class SignInPost {
    @SerializedName("email")
    private String email;
    @SerializedName("pwd")
    private String pwd;
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private TokenData data;

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public TokenData getData() {
        return data;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setData(TokenData data) {
        this.data = data;
    }

    //token data
    public static class TokenData {
        @SerializedName("accessToken")
        private String accessToken;

        @SerializedName("refreshToken")
        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
