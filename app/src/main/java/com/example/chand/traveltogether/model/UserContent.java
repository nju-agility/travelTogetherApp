package com.example.chand.traveltogether.model;

import java.util.ArrayList;
import java.util.List;

public class UserContent {
    private ArrayList<UserEntity> content;

    public ArrayList<UserEntity> getContent() {
        return content;
    }

    public void setContent(ArrayList<UserEntity> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserContent{" +
                "content=" + content +
                '}';
    }
}
