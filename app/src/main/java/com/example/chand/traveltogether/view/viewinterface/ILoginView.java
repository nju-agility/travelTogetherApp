package com.example.chand.traveltogether.view.viewinterface;

import android.content.Context;

//提供给presenter进行回调的接口
public interface ILoginView {
    void showResult(String s);
    void callStartActivity(String s);
    Context getContext();

}
