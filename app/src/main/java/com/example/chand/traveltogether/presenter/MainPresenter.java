package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.model.Activity;
import com.example.chand.traveltogether.model.User;
import com.example.chand.traveltogether.presenter.presenternterface.IMainPresenter;
import com.example.chand.traveltogether.view.viewinterface.IMainView;

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
                        System.out.println(user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onComplete() {
                        view.get().writeUserInfo(muser.getData().getContent());
                    }
                });
    }

    @Override
    public void getCurrentActivity(final int activity_id) {
        manager.getCurrentActivity(activity_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Activity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Activity activity) {
                        System.out.println(activity.getData().getContent().size());
                        if(activity.getResCode() == 0 ){
                            if (activity.getData().getContent().size() > 0){
                                view.get().setCurrentActivity(activity.getData().getContent().get(0));
                            }else {
                                view.get().showError("没有正在进行的活动");
                            }
                        }else if(activity.getResCode() == 1002){
                            view.get().showError("没有正在进行的活动");
                        }else {
                            view.get().showError("没有正在进行的活动");
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
