package com.example.chand.traveltogether.Utils;

import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.model.RegisterReq;
import com.example.chand.traveltogether.model.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/api/login")
    Observable<LoginReq> getUserLogin(@Query("account")String account, @Query("passwd")String pwd);

    @FormUrlEncoded
    @POST("/api/register")
    Observable<RegisterReq> getUserRegister(@Field("account") String account, @Field("name")String name, @Field("passwd")String pwd);

    @GET("/api/publishedActivities")
    Observable<Activity> getAllActivities();



}
