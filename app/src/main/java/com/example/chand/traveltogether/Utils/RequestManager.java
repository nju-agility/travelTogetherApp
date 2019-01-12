package com.example.chand.traveltogether.Utils;


import com.example.chand.traveltogether.application.TravelApplication;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.model.RegisterReq;
import com.example.chand.traveltogether.model.ReqJoinActivity;
import com.example.chand.traveltogether.model.UpdateUserTextInfoReq;
import com.example.chand.traveltogether.model.User;

import io.reactivex.Observable;


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
}
