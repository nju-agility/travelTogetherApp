package com.example.chand.traveltogether.view.Activity;

import android.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.SharedHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonCenterActivity extends BaseActivity {
    @BindView(R.id.center_user_account)
    TextView accountTv;
    @BindView(R.id.center_user_age)
    TextView ageTv;
    @BindView(R.id.center_user_city)
    TextView cityTv;
    @BindView(R.id.center_user_gender)
    TextView genderTv;
    @BindView(R.id.center_user_password)
    TextView passwordTv;
    @BindView(R.id.center_user_score)
    TextView scoreTv;
    @BindView(R.id.center_user_school)
    TextView schoolTv;
    @BindView(R.id.center_user_name)
    TextView nameTv;
    Unbinder unbinder;

    @Override
    protected void initialData() {
        SharedHelper.getSharedHelper().setBool("isNewAccount", false);
        setContentView(R.layout.activity_personcenter);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        unbinder = ButterKnife.bind(this);
        loadDataFromSharedPreference();
    }

    public void loadDataFromSharedPreference() {
        accountTv.setText(SharedHelper.getSharedHelper().getStr("account", "账号"));
        ageTv.setText(SharedHelper.getSharedHelper().getInt("age", 18));
        cityTv.setText(SharedHelper.getSharedHelper().getStr("city", "地球村"));
        genderTv.setText(SharedHelper.getSharedHelper().getStr("gender", "男"));
        String pwdStr = SharedHelper.getSharedHelper().getStr("password", "******");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < pwdStr.length(); i++) {
            stringBuffer.append("*");
        }
        passwordTv.setText(stringBuffer.toString());
        schoolTv.setText(SharedHelper.getSharedHelper().getStr("school", "南京大学"));
        nameTv.setText(SharedHelper.getSharedHelper().getStr("name", "垃圾翔"));
        scoreTv.setText(SharedHelper.getSharedHelper().getInt("score", 100));
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
