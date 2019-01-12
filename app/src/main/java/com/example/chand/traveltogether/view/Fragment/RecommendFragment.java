package com.example.chand.traveltogether.view.Fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.adapter.CardDecoration;
import com.example.chand.traveltogether.adapter.RecyclerAdapter;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.Interface.IRecommendPresenter;
import com.example.chand.traveltogether.presenter.RecommendPresenter;
import com.example.chand.traveltogether.view.Fragment.Interface.IRecommendView;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class RecommendFragment extends BaseFragment implements IRecommendView {
    IRecommendPresenter presenter;
    RecyclerAdapter adapter;
    ArrayList<ActivityEntity> entities;
    @BindView(R.id.recommend_swipe)
    WaveSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recommend_list)
    RecyclerView recyclerView;
//    @BindView(R.id.recommend_fab)
//    FloatingActionButton floatingActionButton;

    @Override
    public void initalData() {
        setLayout(R.layout.fragment_recommend);
        presenter = new RecommendPresenter(this);

    }

    @Override
    public void destoryData() {

    }

    @Override
    public void inflateView() {
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.BLACK);
        swipeRefreshLayout.setWaveColor(Color.rgb(140, 187, 177));
//        floatingActionButton.attachToRecyclerView(recyclerView);
//        floatingActionButton.show();

        swipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.doGetRecommendActivity();
            }
        });


        adapter = new RecyclerAdapter(new ArrayList<ActivityEntity>(), getContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CardDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Toast.makeText(getActivity(), entities.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);
        presenter.doGetRecommendActivity();

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void setPerformanceData(ArrayList<ActivityEntity> entities) {
        this.entities = entities;
        adapter.updateSourceData(this.entities);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showCardView() {

    }

    @Override
    public void stopRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
