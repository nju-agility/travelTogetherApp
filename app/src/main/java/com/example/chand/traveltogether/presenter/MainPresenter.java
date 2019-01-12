package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.RequestManager;
import com.example.chand.traveltogether.model.User;
import com.example.chand.traveltogether.presenter.Interface.IMainPresenter;
import com.example.chand.traveltogether.view.Interface.IMainView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements IMainPresenter {
    private WeakReference<IMainView> view;
    private RequestManager manager;
    private User muser;

    public MainPresenter(IMainView context) {
        view = new WeakReference<>(context);
        manager = new RequestManager();
    }

    @Override
    public void getUserInfo(String account) {
        manager.getUserInfo(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        muser = user;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.get().writeUserInfo(muser.getData().getContent());
                    }
                });
    }
}
