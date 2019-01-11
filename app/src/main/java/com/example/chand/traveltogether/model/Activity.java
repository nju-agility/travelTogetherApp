package com.example.chand.traveltogether.model;


public class Activity extends BaseModel{
    private ActivityContent data;

    public ActivityContent getData() {
        return data;
    }

    public void setData(ActivityContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "data=" + data +
                '}';
    }
}