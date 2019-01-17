package com.example.chand.traveltogether.presenter;


import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.utils.Validator;
import com.example.chand.traveltogether.model.LoginReq;
import com.example.chand.traveltogether.presenter.presenternterface.ILoginPresenter;
import com.example.chand.traveltogether.view.viewinterface.ILoginView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements ILoginPresenter {
    private RequestManager manager;
    private WeakReference<ILoginView> view;
    private LoginReq mlogin;

    public LoginPresenter(ILoginView context) {
        view = new WeakReference<>(context);
        manager = new RequestManager();

    }

    @Override
    public void submitLogin(String account, String password) {


        if (!Validator.isEmail(account)) {
            view.get().showResult("请输入正确的邮箱账号");
        } else if (!Validator.isUserPass(password)) {
            view.get().showResult("密码长度应为8位至20位");
        } else {
            manager.getUserLogin(account, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginReq>() {
                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onComplete() {
                            if (mlogin.getResCode() == 1002) {
                                view.get().showResult("账号密码错误");
                            } else if (mlogin.getResCode() == 0) {
                                SharedHelper.getSharedHelper().setStr("account", mlogin.getData().getAccount());
                                SharedHelper.getSharedHelper().setStr("name", mlogin.getData().getName());
                                SharedHelper.getSharedHelper().setStr("token", mlogin.getData().getToken());
                                SharedHelper.getSharedHelper().setStr("userPic", mlogin.getData().getHeadURL());
                                SharedHelper.getSharedHelper().setBool("isTokenEmpty", false);
                                view.get().callStartActivity("Main");
                            }
                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginReq loginReq) {
                            mlogin = loginReq;
                        }
                    });
        }
    }

    @Override
    public void submitRegister() {
        view.get().callStartActivity("Register");
    }


    @Override
    public void unRegister() {
        view.clear();
        manager.unRegisterService();
    }
}
