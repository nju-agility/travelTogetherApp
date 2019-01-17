package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.presenter.presenternterface.ISearchResultPresenter;
import com.example.chand.traveltogether.view.viewinterface.ISearchResultView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchResultPresenter implements ISearchResultPresenter {

    private RequestManager manager;
    private WeakReference<ISearchResultView> view;
    public SearchResultPresenter(ISearchResultView context) {
        view = new WeakReference<>(context);
        manager = new RequestManager();
    }

    @Override
    public void searchTypeActivities(String type) {
        manager.getTypeActivities(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Activity activity) {
                        if(activity.getResCode() == 0){
                            if(activity.getData().getContent().size()>0){
                                view.get().setPerformanceData(activity.getData().getContent());
                            }else {
                                view.get().showError("暂无更多数据");
                            }
                        }else if(activity.getResCode() == 1002){
                            view.get().showError("暂无更多数据");
                        }else {
                            view.get().showError("暂无更多数据");
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
