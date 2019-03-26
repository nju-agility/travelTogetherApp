package com.example.chand.traveltogether.model;

public class Artical extends BaseModel {
    private ArticalContent data;

    public ArticalContent getData() {
        return data;
    }

    public void setData(ArticalContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "data=" + data +
                '}';
    }
}
