package com.example.chand.traveltogether.view.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.model.UserEntity;


public interface IMainView {
    void writeUserInfo(UserEntity userEntity);
    void setCurrentActivity(ActivityEntity activity);
    void showError(String s);
}
