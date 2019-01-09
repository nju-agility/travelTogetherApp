package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.presenter.Interface.IRegisterPresenter;
import com.example.chand.traveltogether.view.Interface.IRegisterView;

import java.lang.ref.WeakReference;

public class RegisterPresenter implements IRegisterPresenter {
    private WeakReference<IRegisterView> view;

    public RegisterPresenter(IRegisterView view){
        this.view = new WeakReference<>(view);
    }
    @Override
    public void submitRegister(String account, String name, String pwd) {
        //判断是否注册成功
        if(true){
            SharedHelper.getSharedHelper().setStr("account","123");
            SharedHelper.getSharedHelper().setStr("password","123");
            SharedHelper.getSharedHelper().setStr("name","123");
            SharedHelper.getSharedHelper().setBool("isTokenEmpty",false);
            view.get().setActivityResult(1);
        }else {
            view.get().showResult("账户已被注册");
        }
    }

    @Override
    public void unRegister() {
        view.clear();
    }
}
