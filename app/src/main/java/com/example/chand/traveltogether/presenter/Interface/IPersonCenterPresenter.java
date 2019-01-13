package com.example.chand.traveltogether.presenter.Interface;

import okhttp3.MultipartBody;

public interface IPersonCenterPresenter {
    void reqUpdateUserTextInfo(String name, int gender, int age, String city, String code,
                               String passwd, String account, String school);
    void requestUpdateIcon(String account, MultipartBody.Part File);
}
