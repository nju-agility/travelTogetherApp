package com.example.chand.traveltogether.view.Activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.view.Interface.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.forget_account)
    TextView forget;
    @BindView(R.id.submit_login)
    Button submit_login;
    @BindView(R.id.submit_register)
    Button submit_register;

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ButterKnife.bind(this);
    }



    @Override
    protected void initialData() {

    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        bind.unbind();
    }

}
