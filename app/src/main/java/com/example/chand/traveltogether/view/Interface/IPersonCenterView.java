package com.example.chand.traveltogether.view.Interface;

public interface IPersonCenterView {
    void showReqResult(String s);
    void showError(String s);
    void updateSharedPreference(String a,String b);
    void updateSharedPreference(String a,boolean b);
    void updateSharedPreference(String a,int b);
}
