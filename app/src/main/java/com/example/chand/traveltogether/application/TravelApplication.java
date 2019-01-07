package com.example.chand.traveltogether.application;

import android.app.Application;
import android.content.Context;

public class TravelApplication extends Application {
    private static Context globalContext;
    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this.getApplicationContext();
    }

    public static Context getAppContext(){
        return globalContext;
    }


}
