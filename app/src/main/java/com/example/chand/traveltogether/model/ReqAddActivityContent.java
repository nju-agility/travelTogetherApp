package com.example.chand.traveltogether.model;

import java.util.ArrayList;

public class ReqAddActivityContent {
    private ArrayList<ActivityEntity> content;

    public ArrayList<ActivityEntity> getContent() {
        return content;
    }

    public void setContent(ArrayList<ActivityEntity> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReqAddActivityContent{" +
                "content=" + content +
                '}';
    }
}
