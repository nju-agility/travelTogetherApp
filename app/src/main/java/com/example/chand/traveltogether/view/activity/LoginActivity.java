package com.example.chand.traveltogether.view.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.presenter.presenternterface.ILoginPresenter;
import com.example.chand.traveltogether.presenter.LoginPresenter;
import com.example.chand.traveltogether.view.viewinterface.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


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
    public void doSubmit(View view) {
        String account = accountEditor.getText().toString();
        String up = pwdEditor.getText().toString();

        presenter.submitLogin(account, up);
    }

    @OnClick(R.id.submit_register)
    public void doRegister(View view) {
        presenter.submitRegister();
    }

    @OnClick(R.id.forget_account)
    public void doForgetPwd(View view) {
        showNormalDialog();
    }


    private void showNormalDialog() {
//        final AlertDialog.Builder normalDialog =
//                new AlertDialog.Builder(LoginActivity.this);
//        normalDialog.setIcon(R.drawable.x);
//        normalDialog.setTitle("该功能暂未开放");
//        normalDialog.setMessage("由于后台服务器没有设置邮箱，该功能暂未开放！");
//        normalDialog.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("user want to reset the account");
//                    }
//                });
//        normalDialog.show();
        Intent intent = new Intent(LoginActivity.this,ResetActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResult(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void callStartActivity(String s) {
        switch (s) {
            case "Main": {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Register": {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
        }
    }

    @Override
    public Context getContext() {
        return this.getContext();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == 1) {
                    String acc = SharedHelper.getSharedHelper().getStr("account", "null");
                    String up = SharedHelper.getSharedHelper().getStr("password", "null");
                    if (!acc.equals("null") && !up.equals("null")) {
                        presenter.submitLogin(acc, up);
                    }
                }
        }
    }
}
