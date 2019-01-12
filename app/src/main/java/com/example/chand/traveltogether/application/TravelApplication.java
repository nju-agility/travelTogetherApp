package com.example.chand.traveltogether.application;

import android.app.Application;
import android.content.Context;

import com.example.chand.traveltogether.model.User;
import com.example.chand.traveltogether.model.UserEntity;

public class TravelApplication extends Application {
    private volatile static Context globalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return globalContext;
    }


}
