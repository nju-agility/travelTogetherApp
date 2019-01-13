package com.example.chand.traveltogether.Utils;

import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.model.RegisterReq;
import com.example.chand.traveltogether.model.ReqJoinActivity;
import com.example.chand.traveltogether.model.ReqQuitActivity;
import com.example.chand.traveltogether.model.ReqUpload;
import com.example.chand.traveltogether.model.UpdateUserTextInfoReq;
import com.example.chand.traveltogether.model.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/api/login")
    Observable<LoginReq> getUserLogin(@Query("account") String account, @Query("passwd") String pwd);

    @FormUrlEncoded
    @POST("/api/register")
    Observable<RegisterReq> getUserRegister(@Field("account") String account, @Field("name") String name, @Field("passwd") String pwd);

    @GET("/api/publishedActivities")
    Observable<Activity> getAllActivities();

    @GET("/api/userInfo")
    Observable<User> getUserInfo(@Query("account") String account);

    @FormUrlEncoded
    @POST("/api/updateUserInfo")
    Observable<UpdateUserTextInfoReq> updateUserTextInfo(@Field("name") String name, @Field("gender") int gender,
                                                         @Field("age") int age, @Field("city") String city, @Field("code") String code,
                                                         @Field("passwd") String passwd, @Field("account") String account, @Field("school") String school);

    @GET("/api/userAttendActivity")
    Observable<Activity> getCurrentActivity(@Query("activity_id")int activity_id);

    @GET("/api/getRecord")
    Observable<Activity> getHistoryActivities(@Query("account")String account);

    @GET("/api/userApplyActivity")
    Observable<ReqJoinActivity> requestJoinActivity(@Query("account")String account,@Query("activity_id")int activity_id);

    @GET("/api/userQuitActivity")
    Observable<ReqQuitActivity> requestQuitActivity(@Query("account") String account);

    @GET("/api/typeActivities")
    Observable<Activity> getTypeActivities(@Query("type")String type);

    @Multipart
    @POST("/api/uploadImg")
    Observable<ReqUpload> requestUpload(@Part("account")String account, @Part("item")int item, @Part MultipartBody.Part file);

}
