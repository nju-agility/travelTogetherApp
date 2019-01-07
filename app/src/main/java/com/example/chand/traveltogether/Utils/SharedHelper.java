package com.example.chand.traveltogether.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chand.traveltogether.application.TravelApplication;

public class SharedHelper {
    private static volatile SharedHelper sharedHelper = new SharedHelper();
    private static SharedPreferences sharedPreferences = TravelApplication.getAppContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);


    public static SharedHelper getSharedHelper() {
        synchronized (SharedHelper.class){
            return sharedHelper;
        }
    }

    public boolean getBool(String a, boolean b) {
        return sharedPreferences.getBoolean(a, b);
    }

    public String getStr(String a, String b) {
        return sharedPreferences.getString(a, b);
    }

    public int getInt(String a, int b) {
        return sharedPreferences.getInt(a, b);
    }
}
