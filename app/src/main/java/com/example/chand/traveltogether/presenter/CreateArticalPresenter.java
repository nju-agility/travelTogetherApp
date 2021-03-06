package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.model.Artical;
import com.example.chand.traveltogether.model.ArticalReq;
import com.example.chand.traveltogether.model.ReqUpload;
import com.example.chand.traveltogether.model.ResultReq;
import com.example.chand.traveltogether.presenter.presenternterface.ICreateArticalPresenter;
import com.example.chand.traveltogether.utils.RequestManager;
import com.example.chand.traveltogether.view.viewinterface.ICreateArticalView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class CreateArticalPresenter implements ICreateArticalPresenter {

    private WeakReference<ICreateArticalView> view;
    private RequestManager manager;

    public CreateArticalPresenter(ICreateArticalView view) {
        this.view = new WeakReference<>(view);
        manager = new RequestManager();
    }

    @Override
    public void requestCreateArtical(final String account, final String city, final String location, final String title, final String detail, final String submission_date, final MultipartBody.Part file) {
        manager.requestCreateArtical(account, city, location, title, detail, submission_date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticalReq>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticalReq artical) {
                        if (artical.getResCode() == 0) {
                            manager.requestUpload(artical.getData().getContent().getId() + "", 2, file)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<ReqUpload>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(ReqUpload reqUpload) {
                                            System.out.println("file: " + reqUpload.getResCode());
                                            if (reqUpload.getResCode() == 0) {
                                                view.get().requestSuccess();
                                            } else {
                                                view.get().requestError();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        } else {
                            view.get().requestError();
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
