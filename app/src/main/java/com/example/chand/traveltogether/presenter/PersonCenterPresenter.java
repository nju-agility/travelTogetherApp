package com.example.chand.traveltogether.presenter;

import com.example.chand.traveltogether.Utils.RequestManager;
import com.example.chand.traveltogether.model.UpdateUserTextInfoReq;
import com.example.chand.traveltogether.presenter.Interface.IPersonCenterPresenter;
import com.example.chand.traveltogether.view.Interface.IPersonCenterView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PersonCenterPresenter implements IPersonCenterPresenter {
    private RequestManager manager;
    private WeakReference<IPersonCenterView> view;

    public PersonCenterPresenter(IPersonCenterView context) {
        manager = new RequestManager();
        view = new WeakReference<>(context);
    }

    @Override
    public void reqUpdateUserTextInfo(final String name, final int gender, final int age, final String city, final String code, final String passwd, final String account, final String school) {
        manager.updateUserTextInfoReqObservable(name, gender, age, city, code, passwd, account, school)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateUserTextInfoReq>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateUserTextInfoReq updateUserTextInfoReq) {
                        if (updateUserTextInfoReq.getResCode() == 0) {
                            updateSharedPreference(name, gender, age, city, code, passwd, school);
                            view.get().showReqResult("更新成功");
                        } else {
                            view.get().showError("更新出现错误，请检查输入信息");
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

    private void updateSharedPreference(String name, int gender, int age, String city, String code, String passwd, String school) {
        view.get().updateSharedPreference("name", name);
        view.get().updateSharedPreference("gender", gender);
        view.get().updateSharedPreference("age", age);
        view.get().updateSharedPreference("code", code);
        view.get().updateSharedPreference("city", city);
        view.get().updateSharedPreference("password", passwd);
        view.get().updateSharedPreference("school", school);
    }
}
