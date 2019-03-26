package com.example.chand.traveltogether.model;

import java.util.ArrayList;

public class ArticalContent {
    private ArrayList<ArticalEntity> content;

    public ArrayList<ArticalEntity> getContent() {
        return content;
    }

    public void setContent(ArrayList<ArticalEntity> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticalContent{" +
                "content=" + content +
                '}';
    }
}
