package com.example.chand.traveltogether.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {
    @BindView(R.id.detail_listview)
    ObservableScrollView scrollView;
    @BindView(R.id.activity_detail_img)
    ImageView detail_img;
    @BindView(R.id.activity_detail_city)
    TextView detail_city;
    @BindView(R.id.activity_detail_introduction)
    TextView detail_intro;
    @BindView(R.id.activity_detail_owner)
    TextView detail_owner;
    @BindView(R.id.activity_detail_theme)
    TextView detail_theme;
    @BindView(R.id.activity_detail_time)
    TextView detail_time;
    @BindView(R.id.activity_detail_title)
    TextView detail_title;
    Unbinder unbinder;
    ActivityEntity activityEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        scrollView.setScrollViewCallbacks(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        activityEntity = (ActivityEntity) bundle.getSerializable("activity");
        initialContent();
    }

    public void initialContent() {
        if (null != activityEntity) {
            Glide.with(this).load(R.drawable.testactivitypic).into(detail_img);
            detail_city.setText(activityEntity.getCity() + " " + activityEntity.getLocation());
            detail_theme.setText(activityEntity.getType());
            detail_intro.setText(activityEntity.getDetails());
            detail_owner.setText(activityEntity.getOwner());
            detail_title.setText(activityEntity.getTitle());
            detail_time.setText(activityEntity.getTime_start() + " " + activityEntity.getTime_end());
        }
    }

    @OnClick(R.id.join_btn)
    public void requestJoin(View view) {
        Toast.makeText(this, "申请加入活动", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            default: {
                return true;
            }
        }
    }
}
