package com.example.chand.traveltogether.view.Fragment;

import android.graphics.Color;

import com.example.chand.traveltogether.R;

import butterknife.BindView;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class RecommendFragment extends BaseFragment{
    @BindView(R.id.recommend_swipe)
    WaveSwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void initalData() {
        setLayout(R.layout.fragment_recommend);
    }

    @Override
    public void destoryData() {

    }

    @Override
    public void inflateView() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLACK);
        swipeRefreshLayout.setWaveColor(Color.rgb(140,187,177));
    }
}
