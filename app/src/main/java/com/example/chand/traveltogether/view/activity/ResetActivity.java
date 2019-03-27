package com.example.chand.traveltogether.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.presenter.ResetPresenter;
import com.example.chand.traveltogether.presenter.presenternterface.IResetPresenter;
import com.example.chand.traveltogether.view.viewinterface.IResetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ResetActivity extends BaseActivity implements IResetView {

    Unbinder unbinder;
    IResetPresenter resetPresenter;

    @BindView(R.id.reset_account)
    EditText account;
    @BindView(R.id.reset_name)
    EditText name;
    @BindView(R.id.submit_reset)
    Button button;

    @Override
    protected void initialData() {
        setContentView(R.layout.activity_reset);
        unbinder = ButterKnife.bind(this);
        resetPresenter = new ResetPresenter(this);

    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public void resetOK() {
        Toast.makeText(this,"重置成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void resetError() {
        Toast.makeText(this,"账户或用户名错误",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.submit_reset)
    public void doRequest(View view){
        String account = this.account.getText().toString();
        String name = this.name.getText().toString();
        resetPresenter.doReset(account,name);
    }
}
