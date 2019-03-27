package com.example.chand.traveltogether.model;

public class ArticalReq extends BaseModel {
    private ArticalReqContent data;

    @Override
    public String toString() {
        return "ArticalReq{" +
                "data=" + data +
                '}';
    }

    public ArticalReqContent getData() {
        return data;
    }

    public void setData(ArticalReqContent data) {
        this.data = data;
    }
}
