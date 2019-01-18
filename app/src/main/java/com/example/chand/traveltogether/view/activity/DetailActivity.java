package com.example.chand.traveltogether.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.utils.CodeHelper;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.DetailPresenter;
import com.example.chand.traveltogether.presenter.presenternterface.IDetailPresenter;
import com.example.chand.traveltogether.view.viewinterface.IDetailView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity implements ObservableScrollViewCallbacks, IDetailView {
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
    @BindView(R.id.join_btn)
    Button button;
    @BindView(R.id.cost_number)
    TextView detail_cost;
    Unbinder unbinder;
    ActivityEntity activityEntity;
    private int type;
    private IDetailPresenter presenter;

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
        type = bundle.getInt("type");
        presenter = new DetailPresenter(this);
        initialContent(type);
    }


    public void initialContent(int type) {
        CodeHelper.clear(this);
        if (null != activityEntity) {
            Glide.with(this).load(getString(R.string.base_url) + activityEntity.getActivityURL()).into(detail_img);
            detail_city.setText(activityEntity.getCity() + " " + activityEntity.getLocation());
            detail_theme.setText(activityEntity.getType());
            detail_intro.setText(activityEntity.getDetails());
            detail_owner.setText(activityEntity.getOwner());
            detail_title.setText(activityEntity.getTitle());
            detail_time.setText(activityEntity.getTime_start() + " " + activityEntity.getTime_end());
            detail_cost.setText(activityEntity.getPrice() + " 元");
        }

        if (type == 1) {
            button.setClickable(true);
        } else if (type == 2) {
            button.setText("活动不可用");
            button.setBackgroundColor(getColor(R.color.unable));
            button.setClickable(false);
        } else if (type == 3) {
            button.setText("取消活动");
            button.setBackground(getDrawable(R.drawable.ripple_cancel_btn_style));
            button.setClickable(true);
        } else {
            button.setText("有活动进行中");
            button.setBackgroundColor(getColor(R.color.unable));
            button.setClickable(false);
        }
    }

    @OnClick(R.id.join_btn)
    public void requestJoin(View view) {
        if (type == 1) {
            Toast.makeText(this, "申请加入活动", Toast.LENGTH_SHORT).show();
            presenter.reqJoinActivity(SharedHelper.getSharedHelper().getAccount(), activityEntity.getAid());
        } else if (type == 3) {
            presenter.reqCancelActivity(SharedHelper.getSharedHelper().getAccount());
            Toast.makeText(this, "申请取消活动", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void joinSuccess() {
        button.setText("已经加入");
        button.setClickable(false);
        button.setBackgroundColor(getColor(R.color.unable));
        SharedHelper.getSharedHelper().setBool("doing", true);
    }

    @Override
    public void cancelSuccess() {
        button.setText("已经取消");
        button.setClickable(false);
        button.setBackgroundColor(getColor(R.color.unable));
        SharedHelper.getSharedHelper().setBool("doing", false);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
