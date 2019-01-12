package com.example.chand.traveltogether.model;

public class LoginReqContent {
    private String account;
    private String name;
    private String headURL;
    private String token;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    @Override
    public String toString() {
        return "LoginReqContent{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", headURL='" + headURL + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
