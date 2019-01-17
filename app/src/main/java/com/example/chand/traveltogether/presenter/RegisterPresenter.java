package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.utils.Validator;
import com.example.chand.traveltogether.model.RegisterReq;
import com.example.chand.traveltogether.presenter.presenternterface.IRegisterPresenter;
import com.example.chand.traveltogether.view.viewinterface.IRegisterView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements IRegisterPresenter {
    private WeakReference<IRegisterView> view;
    private RequestManager manager;
    private RegisterReq mregisterReq;

    public RegisterPresenter(IRegisterView view) {
        this.view = new WeakReference<>(view);
        manager = new RequestManager();
    }

    @Override
    public void submitRegister(final String account, final String name, final String pwd) {
        if (!Validator.isEmail(account)) {
            view.get().showResult("请输入正确的邮箱账号");
        }else if (!Validator.isUserPass(pwd)) {
            view.get().showResult("密码长度应为8位至20位");
        }  else if (!Validator.isUsername(name)) {
            view.get().showResult("用户名长度为1到15位之间");
        } else {
            manager.getUserRegister(account,name,pwd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RegisterReq>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RegisterReq registerReq) {
                            mregisterReq = registerReq;
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            System.out.println(mregisterReq.getResCode());
                            if (mregisterReq.getResCode() == 1003) {
                                view.get().showResult("账户已被注册");
                            } else if (mregisterReq.getResCode() == 0) {
                                SharedHelper.getSharedHelper().setStr("account", account);
                                SharedHelper.getSharedHelper().setStr("name", name);
                                SharedHelper.getSharedHelper().setStr("password", pwd);
                                SharedHelper.getSharedHelper().setBool("isTokenEmpty", false);
                                SharedHelper.getSharedHelper().setBool("isNewAccount", true);
                                view.get().setActivityResult(1);
                            }
                        }
                    });
        }
    }

    @Override
    public void unRegister() {
        view.clear();
        manager.unRegisterService();
    }
}
