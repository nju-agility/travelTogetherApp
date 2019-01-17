package com.example.chand.traveltogether.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chand.traveltogether.application.TravelApplication;

public class SharedHelper {
    private static SharedHelper sharedHelper = new SharedHelper();
    private static SharedPreferences sharedPreferences = TravelApplication.getAppContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sharedPreferences.edit();


    public static SharedHelper getSharedHelper() {
        synchronized (SharedHelper.class) {
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

    public void setStr(String a, String b) {
        editor.putString(a, b);
        editor.apply();
    }

    public void setInt(String a, int b) {
        editor.putInt(a, b);
        editor.apply();
    }

    public void setFloat(String a, Float b) {
        editor.putFloat(a, b);
        editor.apply();
    }

    public void setBool(String a, boolean b) {
        editor.putBoolean(a, b);
        editor.apply();
    }

    public void cleanAll() {
        editor.clear();
        editor.apply();
    }

    public int getAge() {
        return sharedPreferences.getInt("age", 0);
    }

    public String getName() {
        return sharedPreferences.getString("name", "");
    }

    public int getGender() {
        return sharedPreferences.getInt("gender", 0);
    }

    public String getCity() {
        return sharedPreferences.getString("city", "");
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    public String getAccount() {
        return sharedPreferences.getString("account", "");
    }

    public String getHeadURL() {
        return sharedPreferences.getString("userPic", "");
    }

    public String getCode() {
        return sharedPreferences.getString("code", "");
    }

    public String getSchool() {
        return sharedPreferences.getString("school", "");
    }
}
