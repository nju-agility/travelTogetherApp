package com.example.chand.traveltogether.view.Activity;

import android.app.ActionBar;
import android.view.MenuItem;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.SharedHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonCenterActivity extends BaseActivity {
    Unbinder unbinder;
    @Override
    protected void initialData() {
        SharedHelper.getSharedHelper().setBool("isNewAccount", false);
        setContentView(R.layout.activity_personcenter);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            {
                finish();
                return true;
            }
            default:{
                return true;
            }
        }
    }
}
