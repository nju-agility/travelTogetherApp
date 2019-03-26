package com.example.chand.traveltogether.presenter.presenternterface;

import okhttp3.MultipartBody;

public interface ICreateArticalPresenter {
    void requestCreateArtical(String account, String city, String location, String title, String detail, String submission_date, MultipartBody.Part file);
}
