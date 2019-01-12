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
import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.presenter.HistoryPresenter;
import com.example.chand.traveltogether.presenter.Interface.IHistoryPresenter;
import com.example.chand.traveltogether.view.Interface.IHistoryView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryActivity extends BaseActivity implements IHistoryView {
    private IHistoryPresenter presenter;
    private HistoryAdapter adapter;
    private ArrayList<ActivityEntity> entities;
    Unbinder unbinder;

    @BindView(R.id.history_list)
    RecyclerView recyclerView;

    @Override
    protected void initialData() {
        setContentView(R.layout.activity_history);
        unbinder = ButterKnife.bind(this);
        presenter = new HistoryPresenter(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        adapter = new HistoryAdapter(new ArrayList<ActivityEntity>(), this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CardDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                ActivityEntity obj = entities.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("activity", obj);
                bundle.putInt("type", 2);
                Intent intent = new Intent(HistoryActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        presenter.requestHistoryRecord(SharedHelper.getSharedHelper().getAccount());
    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public void setPerformanceData(ArrayList<ActivityEntity> entities) {
        this.entities = entities;
        adapter.updateSourceData(this.entities);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
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
