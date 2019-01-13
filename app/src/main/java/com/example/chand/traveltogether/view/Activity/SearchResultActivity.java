package com.example.chand.traveltogether.view.Activity;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.adapter.CardDecoration;
import com.example.chand.traveltogether.adapter.HistoryAdapter;
import com.example.chand.traveltogether.adapter.ResultAdapter;
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.Interface.ISearchResultPresenter;
import com.example.chand.traveltogether.presenter.SearchResultPresenter;
import com.example.chand.traveltogether.view.Interface.IPersonCenterView;
import com.example.chand.traveltogether.view.Interface.ISearchResultView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchResultActivity extends BaseActivity implements ISearchResultView {
    private ISearchResultPresenter presenter;
    private ResultAdapter adapter;
    private ArrayList<ActivityEntity> entities;

    @BindView(R.id.search_result_list)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @Override
    protected void initialData() {
        setContentView(R.layout.activity_searchresult);
        unbinder = ButterKnife.bind(this);
        presenter = new SearchResultPresenter(this);

        String theme = getIntent().getStringExtra("theme");

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        adapter = new ResultAdapter(new ArrayList<ActivityEntity>(), this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CardDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new ResultAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                ActivityEntity obj = entities.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("activity", obj);
                boolean type = SharedHelper.getSharedHelper().getBool("doing", false);
                if(type){
                    bundle.putInt("type", 4);
                }else {
                    bundle.putInt("type", 1);
                }
                Intent intent = new Intent(SearchResultActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        presenter.searchTypeActivities(theme);

    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {

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
    public void setPerformanceData(ArrayList<ActivityEntity> activityEntities) {
        this.entities = activityEntities;
        adapter.updateSourceData(this.entities);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}