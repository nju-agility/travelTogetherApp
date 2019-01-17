package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.presenternterface.IHistoryPresenter;
import com.example.chand.traveltogether.view.viewinterface.IHistoryView;

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
    public void requestHistoryRecord(final String account) {
        manager.getHistoryActivities(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Activity activity) {
                        if (activity.getResCode() == 0) {
                            ArrayList<ActivityEntity> activityEntities = new ArrayList<>();
                            for (ActivityEntity a : activity.getData().getContent()) {
                                if (a.getStatus() == 3 || a.getStatus() == 5) {
                                    activityEntities.add(a);
                                }
                            }
                            if (activityEntities.size() > 0) {
                                view.get().setPerformanceData(activityEntities);
                            } else {
                                view.get().showError("暂时没有历史数据");
                            }
                        } else if (activity.getResCode() == 1002) {
                            view.get().showError("暂时没有历史数据");
                        } else {
                            view.get().showError(activity.getResMsg());
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
