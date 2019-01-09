package com.example.chand.traveltogether.view.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.presenter.Interface.IRegisterPresenter;
import com.example.chand.traveltogether.presenter.RegisterPresenter;
import com.example.chand.traveltogether.view.Interface.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity implements IRegisterView {
    Unbinder unbinder;
    IRegisterPresenter iRegisterPresenter;

    @BindView(R.id.register_account)
    EditText accountEditor;
    @BindView(R.id.register_pwd)
    EditText pwdEditor;
    @BindView(R.id.register_name)
    EditText nameEditor;
    @BindView(R.id.submit_register)
    Button submit;

    @Override
    protected void initialData() {
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
        iRegisterPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public void setActivityResult(int i) {
        setResult(i);
        finish();
    }

    @Override
    public void showResult(String i) {
        Toast.makeText(this,i,Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.submit_register)
    public void doSubmitRegister(View view){
        String account = accountEditor.getText().toString();
        String pwd = pwdEditor.getText().toString();
        String name = nameEditor.getText().toString();
        iRegisterPresenter.submitRegister(account,name,pwd);
    }
}
