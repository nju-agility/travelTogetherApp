package com.example.chand.traveltogether.view.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.adapter.FragmentAdapter;
import com.example.chand.traveltogether.application.TravelApplication;
import com.example.chand.traveltogether.model.UserEntity;
import com.example.chand.traveltogether.presenter.Interface.IMainPresenter;
import com.example.chand.traveltogether.presenter.MainPresenter;
import com.example.chand.traveltogether.view.Fragment.ManagementFragment;
import com.example.chand.traveltogether.view.Fragment.RecommendFragment;
import com.example.chand.traveltogether.view.Interface.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends FragmentActivity implements IMainView {
    List<Fragment> fragments;
    Unbinder unbinder;
    @BindView(R.id.main_viewpager)
    ViewPager pager;
    @BindView(R.id.recommend_tab)
    LinearLayout recommend;
    @BindView(R.id.management_tab)
    LinearLayout management;
    @BindView(R.id.recommend_img)
    ImageView recommend_img;
    @BindView(R.id.management_img)
    ImageView management_img;

    @BindView(R.id.user_info)
    LinearLayout user_info;
    @BindView(R.id.user_history)
    LinearLayout user_history;
    @BindView(R.id.user_current)
    LinearLayout user_current;
    @BindView(R.id.user_exit)
    LinearLayout user_exit;

    @BindView(R.id.usr_account)
    TextView accountTv;
    @BindView(R.id.usr_name)
    TextView nameTv;
    @BindView(R.id.usr_img)
    ImageView picIV;

    String accountStr;
    String nameStr;
    String picUrl;

    private IMainPresenter presenter;

//        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
//        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
//        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
//            @Override
//            public void onDrawerStateChange(int oldState, int newState) {
//                if (newState == ElasticDrawer.STATE_CLOSED) {
//                    Log.i("MainActivity", "Drawer STATE_CLOSED");
//                }
//            }
//
//            @Override
//            public void onDrawerSlide(float openRatio, int offsetPixels) {
//                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
//            }
//        });
    //加一个floatButton
    //再用一个下拉刷新
    //viewpager + fragment


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        Fragment recommendFragment = new RecommendFragment();
        Fragment managementFragment = new ManagementFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(managementFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentAdapter);
        pager.setCurrentItem(0);
        recommend_img.setImageResource(R.drawable.recommend_selected);
        boolean isNew = SharedHelper.getSharedHelper().getBool("isNewAccount", false);
        if (isNew) {
            Intent intent = new Intent(MainActivity.this, PersonCenterActivity.class);
            initialInfo();
            startActivity(intent);
        }
        requestUserInfo();
    }

    public void initialInfo() {
        SharedHelper.getSharedHelper().setInt("gender", 0);
        SharedHelper.getSharedHelper().setInt("age", 18);
        SharedHelper.getSharedHelper().setStr("city", "地球村");
        SharedHelper.getSharedHelper().setStr("school","南京大学");
    }

    @Override
    protected void onResume() {
        super.onResume();
        accountStr = SharedHelper.getSharedHelper().getStr("account", "账号");
        nameStr = SharedHelper.getSharedHelper().getStr("name", "用户名");
        String url = SharedHelper.getSharedHelper().getStr("userPic", "");
//        System.out.println("URL ----------->" + url);
//        System.out.println(url.equals(""));
        if (url.equals("/image/Dont't find image!")) {
            Glide.with(this).load(R.drawable.testpic).into(picIV);
        } else {
            Glide.with(this).load(url).into(picIV);
        }
        accountTv.setText(accountStr);
        nameTv.setText(nameStr);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R.id.recommend_tab, R.id.management_tab})
    public void changeTab(View view) {
        switch (view.getId()) {
            case R.id.recommend_tab: {
                pager.setCurrentItem(0);
                recommend_img.setImageResource(R.drawable.recommend_selected);
                management_img.setImageResource(R.drawable.management_unselected);
                break;
            }
            case R.id.management_tab: {
                pager.setCurrentItem(1);
                recommend_img.setImageResource(R.drawable.recommend_unselected);
                management_img.setImageResource(R.drawable.management_selected);
                break;
            }
        }
    }

    @OnClick({R.id.user_info, R.id.user_current, R.id.user_history, R.id.user_exit})
    public void menuEvent(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.user_info: {
                intent = new Intent(MainActivity.this, PersonCenterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.user_current: {
                break;
            }
            case R.id.user_history: {
                break;
            }
            case R.id.user_exit: {
                SharedHelper.getSharedHelper().cleanAll();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    public void requestUserInfo() {
        presenter.getUserInfo(SharedHelper.getSharedHelper().getStr("account", "账号"));
    }

    @Override
    public void writeUserInfo(ArrayList<UserEntity> userEntity) {
        UserEntity entity = userEntity.get(0);
        SharedHelper.getSharedHelper().setStr("name", entity.getName());
        SharedHelper.getSharedHelper().setStr("city", entity.getCity().equals("")?"地球村":entity.getCity());
        SharedHelper.getSharedHelper().setInt("age", entity.getAge());
        SharedHelper.getSharedHelper().setInt("gender", entity.getGender());
        SharedHelper.getSharedHelper().setInt("score", entity.getScore());
        SharedHelper.getSharedHelper().setStr("school", entity.getSchool());
        SharedHelper.getSharedHelper().setStr("userPic", entity.getHeadURL());
    }
}