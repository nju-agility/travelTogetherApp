package com.example.chand.traveltogether.model;

import java.util.ArrayList;
import java.util.List;

public class UserContent {
    private UserEntity content;

    public UserEntity getContent() {
        return content;
    }

    public void setContent(UserEntity content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserContent{" +
                "content=" + content +
                '}';
    }
}
