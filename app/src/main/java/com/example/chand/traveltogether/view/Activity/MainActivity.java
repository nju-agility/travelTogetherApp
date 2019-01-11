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

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.adapter.FragmentAdapter;
import com.example.chand.traveltogether.view.Fragment.ManagementFragment;
import com.example.chand.traveltogether.view.Fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends FragmentActivity {
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
        Fragment recommendFragment = new RecommendFragment();
        Fragment managementFragment = new ManagementFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(managementFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(fragmentAdapter);
        pager.setCurrentItem(0);
        recommend_img.setImageResource(R.drawable.recommend_selected);
        boolean isNew = SharedHelper.getSharedHelper().getBool("isNewAccount", false);
        if (isNew) {
            Intent intent = new Intent(MainActivity.this, PersonCenterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        accountStr = SharedHelper.getSharedHelper().getStr("account","账号");
        nameStr = SharedHelper.getSharedHelper().getStr("name","用户名");
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


    @OnClick({R.id.recommend_tab,R.id.management_tab})
    public void changeTab(View view){
        switch (view.getId()){
            case R.id.recommend_tab:{
                pager.setCurrentItem(0);
                recommend_img.setImageResource(R.drawable.recommend_selected);
                management_img.setImageResource(R.drawable.management_unselected);
                break;
            }
            case R.id.management_tab:{
                pager.setCurrentItem(1);
                recommend_img.setImageResource(R.drawable.recommend_unselected);
                management_img.setImageResource(R.drawable.management_selected);
                break;
            }
        }
    }

    @OnClick({R.id.user_info,R.id.user_current,R.id.user_history,R.id.user_exit})
    public void menuEvent(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.user_info:{
                intent = new Intent(MainActivity.this, PersonCenterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.user_current:{
                break;
            }
            case R.id.user_history:{
                break;
            }
            case R.id.user_exit:{
                SharedHelper.getSharedHelper().cleanAll();
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

}