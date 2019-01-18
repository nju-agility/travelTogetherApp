package com.example.chand.traveltogether.model;

import java.util.ArrayList;

public class ReqAddActivity extends BaseModel {
    private ReqAddActivityContent data;

    public ReqAddActivityContent getData() {
        return data;
    }

    public void setData(ReqAddActivityContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReqAddActivity{" +
                "data=" + data +
                '}';
    }
}
