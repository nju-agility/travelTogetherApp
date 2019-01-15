package com.example.chand.traveltogether.presenter.Interface;

import okhttp3.MultipartBody;

public interface ICreatePresenter {
    void reqCreateActivity(String account,String city,String location,String title,String details,String time_start,String time_end,String type ,int price,MultipartBody.Part file);
}
