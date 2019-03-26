package com.example.chand.traveltogether.view.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chand.traveltogether.adapter.CardDecoration;
import com.example.chand.traveltogether.adapter.MyArticalAdapter;
import com.example.chand.traveltogether.model.ArticalEntity;
import com.example.chand.traveltogether.presenter.MyArticalPresenter;
import com.example.chand.traveltogether.presenter.presenternterface.IMyArticalPresenter;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.view.viewinterface.IMyArticalView;
import com.example.chand.traveltogether.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyArticalActivity extends BaseActivity implements IMyArticalView {

    Unbinder unbinder;

    private MyArticalAdapter adapter;
    private ArrayList<ArticalEntity> entities;
    private IMyArticalPresenter presenter;

    @BindView(R.id.my_artical_list)
    RecyclerView recyclerView;

    @Override
    protected void initialData() {
        setContentView(R.layout.activity_my_artical);
        unbinder = ButterKnife.bind(this);
        presenter = new MyArticalPresenter(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        adapter = new MyArticalAdapter(new ArrayList<ArticalEntity>(), this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new CardDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new MyArticalAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                ArticalEntity obj = entities.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("artical", obj);
                Intent intent = new Intent(MyArticalActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        presenter.requestMyArtical(SharedHelper.getSharedHelper().getAccount());
    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public void setPerformanceData(ArrayList<ArticalEntity> entities) {
        this.entities = entities;
        adapter.updateSourceData(this.entities);
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
