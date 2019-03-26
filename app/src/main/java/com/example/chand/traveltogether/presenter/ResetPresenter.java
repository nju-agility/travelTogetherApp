package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.model.ResultReq;
import com.example.chand.traveltogether.presenter.presenternterface.IResetPresenter;
import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.view.viewinterface.IResetView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ResetPresenter implements IResetPresenter {
    private WeakReference<IResetView> view;
    private RequestManager manager;

    public ResetPresenter(IResetView view) {
        this.view = new WeakReference<>(view);
        manager = new RequestManager();
    }

    @Override
    public void doReset(String account, String name) {
        manager.requestReset(account, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultReq>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultReq resultReq) {
                        if (resultReq.getResCode() == 0) {
                            view.get().resetOK();
                        } else {
                            view.get().resetError();
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
}
