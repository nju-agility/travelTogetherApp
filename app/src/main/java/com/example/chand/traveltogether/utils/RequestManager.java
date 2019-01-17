package com.example.chand.traveltogether.utils;


import com.example.chand.traveltogether.application.TravelApplication;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.model.RegisterReq;
import com.example.chand.traveltogether.model.ReqAddActivity;
import com.example.chand.traveltogether.model.ReqJoinActivity;
import com.example.chand.traveltogether.model.ReqQuitActivity;
import com.example.chand.traveltogether.model.ReqUpload;
import com.example.chand.traveltogether.model.UpdateUserTextInfoReq;
import com.example.chand.traveltogether.model.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


public class RequestManager {
    private RetrofitService mRetrofitService;

    public RequestManager() {
        this.mRetrofitService = RetrofitHelper.getInstance(TravelApplication.getAppContext()).getServer();
    }

    public Observable<LoginReq> getUserLogin(String account, String passwd) {
        return mRetrofitService.getUserLogin(account, passwd);
    }

    public Observable<RegisterReq> getUserRegister(String account, String name, String passwd) {
        return mRetrofitService.getUserRegister(account, name, passwd);
    }

    public void unRegisterService() {
        mRetrofitService = null;
    }

    public Observable<Activity> getAllActivities() {
        return mRetrofitService.getAllActivities();
    }

    public Observable<User> getUserInfo(String account) {
        return mRetrofitService.getUserInfo(account);
    }

    public Observable<UpdateUserTextInfoReq> updateUserTextInfoReqObservable(String name, int gender, int age, String city, String code,
                                                                             String passwd, String account, String school) {
        return mRetrofitService.updateUserTextInfo(name, gender, age, city, code, passwd, account, school);
    }

    public Observable<Activity> getCurrentActivity(int activity_id) {
        return mRetrofitService.getCurrentActivity(activity_id);
    }

    public Observable<Activity> getHistoryActivities(String account) {
        return mRetrofitService.getHistoryActivities(account);
    }

    public Observable<ReqJoinActivity> requestJoinActivity(String account, int activity_id) {
        return mRetrofitService.requestJoinActivity(account, activity_id);
    }

    public Observable<Activity> getTypeActivities(String type) {
        return mRetrofitService.getTypeActivities(type);
    }

    public Observable<ReqQuitActivity> requestQuitActivity(String account) {
        return mRetrofitService.requestQuitActivity(account);
    }

    public Observable<ReqUpload> requestUpload(String account, int item, MultipartBody.Part file) {
        return mRetrofitService.requestUpload(account, item, file);
    }

    public Observable<ReqAddActivity> requestAddActivity(String account, String city, String location, String title,
                                                         String details, String time_start, String time_end, String type, int price) {
        return mRetrofitService.requestAddActivity(account, city, location, title, details, time_start, time_end, type, price);
    }
}
