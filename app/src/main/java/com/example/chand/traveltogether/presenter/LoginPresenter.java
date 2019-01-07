package com.example.chand.traveltogether.presenter;


import com.example.chand.traveltogether.presenter.Interface.ILoginPresenter;
import com.example.chand.traveltogether.view.Interface.ILoginView;

import java.lang.ref.WeakReference;

public class LoginPresenter implements ILoginPresenter {
    private WeakReference<ILoginView>  view;
    public LoginPresenter(ILoginView context){
        view = new WeakReference<>(context);
    }
    @Override
    public String submitLogin(String account, String password) {
        return null;
    }

    @Override
    public void unRegistt() {
        view.clear();
    }
}
