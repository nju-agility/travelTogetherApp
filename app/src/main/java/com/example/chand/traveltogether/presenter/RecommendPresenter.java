package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.RequestManager;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.Interface.IRecommendPresenter;
import com.example.chand.traveltogether.view.Fragment.Interface.IRecommendView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendPresenter implements IRecommendPresenter {
    private RequestManager manager;
    private WeakReference<IRecommendView> view;
    private ArrayList<ActivityEntity> list;

    public RecommendPresenter(IRecommendView view){
        this.view = new WeakReference<>(view);
        manager = new RequestManager();
    }

    @Override
    public void doGetRecommendActivity() {
        manager.getAllActivities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Activity activity) {
                        list = activity.getData().getContent();
                        view.get().setPerformanceData(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.get().stopRefreshing();
                    }
                });
    }
}
