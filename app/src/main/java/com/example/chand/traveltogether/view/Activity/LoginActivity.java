package com.example.chand.traveltogether.view.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.CodeHelper;
import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.presenter.Interface.ILoginPresenter;
import com.example.chand.traveltogether.presenter.LoginPresenter;
import com.example.chand.traveltogether.view.Interface.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.chand.traveltogether.Utils.SharedHelper.getSharedHelper;

public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.forget_account)
    TextView forget;
    @BindView(R.id.submit_login)
    Button submit_login;
    @BindView(R.id.submit_register)
    Button submit_register;
    @BindView(R.id.login_account)
    EditText accountEditor;
    @BindView(R.id.login_pwd)
    EditText pwdEditor;

    ILoginPresenter presenter;
    Intent intent;

    private Unbinder bind;


    @Override
    protected void initialData() {
        setContentView(R.layout.activity_login);
        bind = ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        bind.unbind();
    }

    @OnClick(R.id.submit_login)
    public void doSubmit(View view){
        String account = accountEditor.getText().toString();
        String pwd = CodeHelper.getCodeHelper().encode(pwdEditor.getText().toString());

        presenter.submitLogin(account,pwd);
    }

    @OnClick(R.id.submit_register)
    public void doRegister(View view){
        presenter.submitRegister();
    }

    @OnClick(R.id.forget_account)
    public void doForgetPwd(View view){

    }

    @Override
    public void showResult(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void callStartActivity(String s) {
        switch (s){
            case "Main":{
                intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Register":{
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
                break;
            }
            case "Forget":{
                intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == 1){
                    String acc = SharedHelper.getSharedHelper().getStr("account", "null");
                    String pwd = SharedHelper.getSharedHelper().getStr("password", "null");
                    if(!acc.equals("null")&&!pwd.equals("null")){
                        presenter.submitLogin(acc,pwd);
                    }
                }
        }
    }
}
