package com.example.chand.traveltogether.Utils;


import com.example.chand.traveltogether.application.TravelApplication;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.model.RegisterReq;

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

    public Observable<Activity> getAllActivities(){
        return mRetrofitService.getAllActivities();
    }

}
