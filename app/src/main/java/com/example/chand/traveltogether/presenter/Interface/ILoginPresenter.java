package com.example.chand.traveltogether.presenter.Interface;

public interface ILoginPresenter {
    void submitLogin(String account,String password);
    void submitRegister();
    void submitForgetPwd();
    void unRegister();
}
