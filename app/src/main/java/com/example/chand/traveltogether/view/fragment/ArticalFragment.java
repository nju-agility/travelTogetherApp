package com.example.chand.traveltogether.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.adapter.ArticalAdapter;
import com.example.chand.traveltogether.adapter.CardDecoration;
import com.example.chand.traveltogether.adapter.RecyclerAdapter;
import com.example.chand.traveltogether.model.ArticalEntity;
import com.example.chand.traveltogether.presenter.ArticalPresenter;
import com.example.chand.traveltogether.presenter.presenternterface.IArticalPresenter;
import com.example.chand.traveltogether.view.activity.ArticalDetailActivity;
import com.example.chand.traveltogether.view.fragment.Interface.IArticalView;

import java.util.ArrayList;

import butterknife.BindView;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ArticalFragment extends BaseFragment implements IArticalView {
    IArticalPresenter presenter;
    ArticalAdapter adapter;
    ArrayList<ArticalEntity> entities;
    @BindView(R.id.artical_swipe)
    WaveSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.artical_list)
    RecyclerView recyclerView;

    @Override
    public void initalData() {
        setLayout(R.layout.fragment_artical);
        presenter = new ArticalPresenter(this);
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
                presenter.doGetArtical();
            }
        });


        adapter = new ArticalAdapter(new ArrayList<ArticalEntity>(), getContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CardDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.setOnItemClickListener(new ArticalAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                ArticalEntity obj = entities.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("artical", obj);
                //TODO 打开游记详情
                Intent intent = new Intent(getActivity(), ArticalDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
//        swipeRefreshLayout.setRefreshing(true);
//        presenter.doGetRecommendActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        presenter.doGetArtical();
    }

    @Override
    public void setPerformanceData(ArrayList<ArticalEntity> entities) {
        this.entities = entities;
        adapter.updateSourceData(this.entities);
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
