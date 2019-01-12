package com.example.chand.traveltogether.model;

public class User extends BaseModel{
    private UserContent data;

    public UserContent getData() {
        return data;
    }

    public void setData(UserContent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "User{" +
                "data=" + data +
                '}';
    }
}