package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.model.ReqAddActivity;
import com.example.chand.traveltogether.model.ReqUpload;
import com.example.chand.traveltogether.presenter.presenternterface.ICreatePresenter;
import com.example.chand.traveltogether.view.viewinterface.ICreateView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class CreatePresenter implements ICreatePresenter {
    private RequestManager manager;
    private WeakReference<ICreateView> view;

    public CreatePresenter(ICreateView view) {
        manager = new RequestManager();
        this.view = new WeakReference<>(view);
    }

    @Override
    public void reqCreateActivity(final String account, String city, String location, String title, String details, String time_start, String time_end, String type, int price, final MultipartBody.Part file) {
        manager.requestAddActivity(account, city, location, title, details, time_start, time_end, type, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReqAddActivity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReqAddActivity reqAddActivity) {
                        String aid = reqAddActivity.getData().getContent().get(0).getAid() + "";
                        MultipartBody.Part f = file;
                        if (reqAddActivity.getResCode() == 0) {
                            manager.requestUpload(aid, 3, f)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<ReqUpload>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(ReqUpload reqUpload) {
                                            if (reqUpload.getResCode() == 0) {
                                                view.get().showSuccess("申请成功");
                                            } else {
                                                view.get().showError(reqUpload.getResMsg());
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
                        } else {
                            view.get().showError(reqAddActivity.getResMsg());
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
