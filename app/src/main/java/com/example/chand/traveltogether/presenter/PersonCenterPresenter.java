package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.model.User;
import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.model.ReqUpload;
import com.example.chand.traveltogether.model.UpdateUserTextInfoReq;
import com.example.chand.traveltogether.presenter.presenternterface.IPersonCenterPresenter;
import com.example.chand.traveltogether.view.viewinterface.IPersonCenterView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class PersonCenterPresenter implements IPersonCenterPresenter {
    private RequestManager manager;
    private WeakReference<IPersonCenterView> view;

    public PersonCenterPresenter(IPersonCenterView context) {
        manager = new RequestManager();
        view = new WeakReference<>(context);
    }

    @Override
    public void reqUpdateUserTextInfo(final String name, final int gender, final int age, final String city, final String code, final String passwd, final String account, final String school) {
        manager.updateUserTextInfoReqObservable(name, gender, age, city, code, passwd, account, school)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateUserTextInfoReq>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateUserTextInfoReq updateUserTextInfoReq) {
                        if (updateUserTextInfoReq.getResCode() == 0) {
                            updateSharedPreference(name, gender, age, city, code, passwd, school);
                            view.get().showReqResult("更新成功");
                        } else {
                            view.get().showError("更新出现错误，请检查输入信息");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void requestUpdateIcon(final String account, final MultipartBody.Part File) {
        manager.requestUpload(account, 0, File)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReqUpload>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReqUpload reqUpload) {
                        if (reqUpload.getResCode() == 0) {
                            view.get().showReqResult("修改成功");
                            requestUserInfo(account);
                        } else {
                            view.get().showReqResult("修改失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void requestUserInfo(String account) {
        manager.getUserInfo(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        if (user.getResCode() == 0) {
                            updateSharedPreference(user.getData().getContent().getHeadURL());
                            updateSharedPreference(user.getData().getContent().getStatus());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void requestUpdateStatus(final String account, MultipartBody.Part File) {
        manager.requestUpload(account, 1, File)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReqUpload>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReqUpload reqUpload) {
                        if (reqUpload.getResCode() == 0) {
                            view.get().showReqResult("上传成功");
                            requestUserInfo(account);
                        } else {
                            view.get().showReqResult("上传失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void updateSharedPreference(String name, int gender, int age, String city, String code, String passwd, String school) {
        view.get().updateSharedPreference("name", name);
        view.get().updateSharedPreference("gender", gender);
        view.get().updateSharedPreference("age", age);
        view.get().updateSharedPreference("code", code);
        view.get().updateSharedPreference("city", city);
        view.get().updateSharedPreference("password", passwd);
        view.get().updateSharedPreference("school", school);
    }

    private void updateSharedPreference(String url) {
        view.get().updateSharedPreference("userPic", url);
    }

    private void updateSharedPreference(int studentCard){
        view.get().updateSharedPreference("status", studentCard);
    }
}
