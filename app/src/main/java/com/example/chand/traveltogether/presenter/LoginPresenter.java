package com.example.chand.traveltogether.presenter;


import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.presenter.Interface.ILoginPresenter;
import com.example.chand.traveltogether.view.Interface.ILoginView;

import java.lang.ref.WeakReference;

public class LoginPresenter implements ILoginPresenter {
    private WeakReference<ILoginView>  view;
    public LoginPresenter(ILoginView context){
        view = new WeakReference<>(context);
    }
    @Override
    public void submitLogin(String account, String password) {
        //判断账号和密码
        if(true){
            SharedHelper.getSharedHelper().setStr("account","123");
            SharedHelper.getSharedHelper().setStr("password","123");
            SharedHelper.getSharedHelper().setStr("name","123");
            SharedHelper.getSharedHelper().setBool("isTokenEmpty",false);
            view.get().callStartActivity("Main");
        }else {
            view.get().showResult("账号密码错误");
        }
    }

    @Override
    public void submitRegister() {
        view.get().callStartActivity("Register");
    }

    @Override
    public void submitForgetPwd() {
        view.get().callStartActivity("Forget");
    }

    @Override
    public void unRegister() {
        view.clear();
    }
}
