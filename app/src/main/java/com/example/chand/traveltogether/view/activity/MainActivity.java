package com.example.chand.traveltogether.view.activity;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.utils.CodeHelper;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.adapter.FragmentAdapter;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.model.UserEntity;
import com.example.chand.traveltogether.presenter.presenternterface.IMainPresenter;
import com.example.chand.traveltogether.presenter.MainPresenter;
import com.example.chand.traveltogether.view.fragment.SearchFragment;
import com.example.chand.traveltogether.view.fragment.RecommendFragment;
import com.example.chand.traveltogether.view.viewinterface.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    private ActivityEntity current;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

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
        for (String s : permissions) {
            int i = ContextCompat.checkSelfPermission(this, s);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, 999);
            }
        }
        presenter = new MainPresenter(this);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.travel);
        actionBar.setDisplayUseLogoEnabled(true);
        Fragment recommendFragment = new RecommendFragment();
        Fragment searchResultFragment = new SearchFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(searchResultFragment);
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
        SharedHelper.getSharedHelper().setStr("school", "南京大学");
        SharedHelper.getSharedHelper().setInt("activity_id", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        CodeHelper.clear(this);
        accountStr = SharedHelper.getSharedHelper().getStr("account", "账号");
        nameStr = SharedHelper.getSharedHelper().getStr("name", "用户名");
        String url = SharedHelper.getSharedHelper().getStr("userPic", "");
        if (url.equals("/image/Dont't find image!") || url.equals("")) {
            Glide.with(this).load(R.drawable.testpic).into(picIV);
        } else {
            Glide.with(this).load(getString(R.string.base_url) + url).apply(CodeHelper.getOptions()).into(picIV);
        }
        accountTv.setText(accountStr);
        nameTv.setText(nameStr);
        presenter.getUserInfo(SharedHelper.getSharedHelper().getAccount());
//        CodeHelper.clear(this);
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

    @OnClick(R.id.user_create_activity)
    public void goCreateActivity(View view) {
        if (SharedHelper.getSharedHelper().getBool("doing", true)) {
            Toast.makeText(this, "已经有活动正在进行", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        }
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
                if (null != this.current) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("activity", this.current);
                    bundle.putInt("type", 3);
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "没有正在进行中的活动", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.user_history: {
                intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
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
    public void writeUserInfo(UserEntity userEntity) {
        UserEntity entity = userEntity;
        SharedHelper.getSharedHelper().setStr("name", entity.getName());
        SharedHelper.getSharedHelper().setStr("city", entity.getCity().equals("") ? "地球村" : entity.getCity());
        SharedHelper.getSharedHelper().setInt("age", entity.getAge());
        SharedHelper.getSharedHelper().setInt("gender", entity.getGender());
        SharedHelper.getSharedHelper().setInt("score", entity.getScore());
        SharedHelper.getSharedHelper().setStr("school", entity.getSchool());
        SharedHelper.getSharedHelper().setStr("password", entity.getPasswd());
        SharedHelper.getSharedHelper().setStr("userPic", entity.getHeadURL());
        SharedHelper.getSharedHelper().setInt("activity_id", entity.getActivity_id());
        SharedHelper.getSharedHelper().setBool("doing", false);
        presenter.getCurrentActivity(SharedHelper.getSharedHelper().getInt("activity_id", 0));
    }

    @Override
    public void setCurrentActivity(ActivityEntity activity) {
        this.current = activity;
        SharedHelper.getSharedHelper().setBool("doing", true);
    }

    @Override
    public void showError(String s) {
        System.out.println(s);
        if (s.equals("没有正在进行的活动")) {
            this.current = null;
            SharedHelper.getSharedHelper().setBool("doing", false);
        }
    }
}