package com.example.chand.traveltogether.model;

public class ArticalReqContent {
    private ArticalEntity content;

    @Override
    public String toString() {
        return "ArticalReqContent{" +
                "content=" + content +
                '}';
    }

    public ArticalEntity getContent() {
        return content;
    }

    public void setContent(ArticalEntity content) {
        this.content = content;
    }
}
