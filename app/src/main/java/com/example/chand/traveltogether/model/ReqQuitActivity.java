package com.example.chand.traveltogether.model;

public class ReqQuitActivity extends BaseModel {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString() +
                "ReqQuitActivity{" +
                "data='" + data + '\'' +
                '}';
    }
}
