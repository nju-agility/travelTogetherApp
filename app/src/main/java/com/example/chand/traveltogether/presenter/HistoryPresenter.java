package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.RequestManager;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.Interface.IHistoryPresenter;
import com.example.chand.traveltogether.view.Interface.IHistoryView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryPresenter implements IHistoryPresenter {

    private RequestManager manager;
    private WeakReference<IHistoryView> view;
    private ArrayList<ActivityEntity> list;

    public HistoryPresenter(IHistoryView context) {
        manager = new RequestManager();
        view = new WeakReference<>(context);
    }

    @Override
    public void requestHistoryRecord(String account) {
        manager.getHistoryActivities(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Activity activity) {
                        if (activity.getResCode() == 0 ) {
                            view.get().setPerformanceData(activity.getData().getContent());
                        } else if(activity.getResCode() == 1002 ){
                            view.get().showError("暂时没有历史数据");
                        } else {
                            view.get().showError(activity.getResMsg());
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
