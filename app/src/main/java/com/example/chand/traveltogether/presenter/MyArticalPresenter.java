package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.model.Artical;
import com.example.chand.traveltogether.model.ArticalEntity;
import com.example.chand.traveltogether.presenter.presenternterface.IMyArticalPresenter;
import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.view.viewinterface.IHistoryView;
import com.example.chand.traveltogether.view.viewinterface.IMyArticalView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyArticalPresenter implements IMyArticalPresenter {

    private RequestManager manager;
    private WeakReference<IMyArticalView> view;
    private ArrayList<ArticalEntity> list;

    public MyArticalPresenter(IMyArticalView context) {
        manager = new RequestManager();
        view = new WeakReference<>(context);
    }

    @Override
    public void requestMyArtical(String account) {
        manager.requestUserArtical(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artical>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Artical artical) {
                        if (artical.getResCode() == 0) {
                            ArrayList<ArticalEntity> articalEntities = new ArrayList<>();
                            for (ArticalEntity a : artical.getData().getContent()) {
                                articalEntities.add(a);
                            }
                            if (articalEntities.size() > 0) {
                                view.get().setPerformanceData(articalEntities);
                            } else {
                                view.get().showError("暂时没有历史数据");
                            }
                        } else if (artical.getResCode() == 1002) {
                            view.get().showError("暂时没有历史数据");
                        } else {
                            view.get().showError(artical.getResMsg());
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
