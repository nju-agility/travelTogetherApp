package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.RequestManager;
import com.example.chand.traveltogether.model.ReqJoinActivity;
import com.example.chand.traveltogether.model.ReqQuitActivity;
import com.example.chand.traveltogether.presenter.Interface.IDetailPresenter;
import com.example.chand.traveltogether.view.Interface.IDetailView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements IDetailPresenter {
    private RequestManager manager;
    private WeakReference<IDetailView> view;

    public DetailPresenter(IDetailView context) {
        manager = new RequestManager();
        view = new WeakReference<>(context);
    }

    @Override
    public void reqJoinActivity(String account, int activity_id) {
        manager.requestJoinActivity(account, activity_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReqJoinActivity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReqJoinActivity reqJoinActivity) {
                        if (reqJoinActivity.getResCode() == 0) {
                            view.get().joinSuccess();
                        } else {
                            view.get().showError(reqJoinActivity.getResMsg());
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
    public void reqCancelActivity(String account) {
        manager.requestQuitActivity(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReqQuitActivity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReqQuitActivity reqQuitActivity) {
                        System.out.println(reqQuitActivity);
                        if (reqQuitActivity.getResCode() == 0) {
                            view.get().cancelSuccess();
                        } else {
                            view.get().showError(reqQuitActivity.getResMsg());
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
}
