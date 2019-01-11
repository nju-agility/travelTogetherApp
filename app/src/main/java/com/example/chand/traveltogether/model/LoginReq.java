package com.example.chand.traveltogether.model;


public class LoginReq extends BaseModel {
    private LoginReqContent data;

    public LoginReqContent getData() {
        return data;
    }

    public void setData(LoginReqContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString() +
                "LoginReq{" +
                "data=" + data.toString() +
                '}';
    }
}
