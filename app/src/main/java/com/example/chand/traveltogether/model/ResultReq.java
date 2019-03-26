package com.example.chand.traveltogether.model;

public class ResultReq extends BaseModel{
    private String data;

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResultReq{" +
                "data='" + data + '\'' +
                '}';
    }
}
