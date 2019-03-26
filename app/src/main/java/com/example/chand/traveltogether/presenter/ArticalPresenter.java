package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.model.Artical;
import com.example.chand.traveltogether.model.ArticalEntity;
import com.example.chand.traveltogether.presenter.presenternterface.IArticalPresenter;
import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.view.fragment.Interface.IArticalView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArticalPresenter implements IArticalPresenter {
    private RequestManager manager;
    private WeakReference<IArticalView> view;
    private ArrayList<ArticalEntity> list;

    public ArticalPresenter(IArticalView view) {
        this.view = new WeakReference<>(view);
        manager = new RequestManager();
    }

    @Override
    public void doGetArtical() {
        manager.requestAllArtical()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Artical>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Artical artical) {
                        if (artical.getResCode() == 0) {
                            if(artical.getData().getContent().size()>0){
                                list = artical.getData().getContent();
                                view.get().setPerformanceData(list);
                            }else {
                                view.get().showError("没有更多的结果了");
                            }
                        }else {
                            view.get().showError("没有更多的结果了");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {
                        view.get().stopRefreshing();
                    }
                });
    }
}
