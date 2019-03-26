package com.example.chand.traveltogether.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.model.ArticalEntity;
import com.example.chand.traveltogether.utils.CodeHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ArticalDetailActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {
    Unbinder unbinder;
    @BindView(R.id.artical_detail_listview)
    ObservableScrollView scrollView;
    @BindView(R.id.artical_detail_img)
    ImageView detail_img;
    @BindView(R.id.artical_detail_city)
    TextView detail_city;
    @BindView(R.id.artical_detail_introduction)
    TextView detail_intro;
    @BindView(R.id.artical_detail_owner)
    TextView detail_owner;
    @BindView(R.id.artical_detail_time)
    TextView detail_time;
    @BindView(R.id.artical_detail_title)
    TextView detail_title;

    private ArticalEntity articalEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical_detail);
        unbinder = ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        scrollView.setScrollViewCallbacks(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        articalEntity = (ArticalEntity) bundle.getSerializable("artical");
        initialContent();
    }


    public void initialContent() {
        CodeHelper.clear(this);
        if (null != articalEntity) {
            Glide.with(this).load(getString(R.string.base_url) + articalEntity.getImgPath()).into(detail_img);
            detail_city.setText(articalEntity.getCity() + " " + articalEntity.getLocation());
            detail_intro.setText(articalEntity.getDetails());
            detail_owner.setText(articalEntity.getAccount());
            detail_title.setText(articalEntity.getTitle());
            detail_time.setText(articalEntity.getSubmission_date());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
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
