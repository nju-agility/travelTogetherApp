package com.example.chand.traveltogether.view.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.utils.CodeHelper;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.utils.Validator;
import com.example.chand.traveltogether.presenter.PersonCenterPresenter;
import com.example.chand.traveltogether.view.viewinterface.IPersonCenterView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonCenterActivity extends BaseActivity implements IPersonCenterView {
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
    @BindView(R.id.center_user_status)
    TextView statusTv;
    @BindView(R.id.center_user_school)
    TextView schoolTv;
    @BindView(R.id.center_user_name)
    TextView nameTv;
    @BindView(R.id.center_user_img)
    ImageView picIv;
    @BindView(R.id.center_status_container)
    ConstraintLayout status_container;
    Unbinder unbinder;

    private PersonCenterPresenter presenter;
    final int REQUEST_CODE_CHOOSE = 100;
    final int REQUEST_CODE_STATUS = 101;
    private ArrayList<String> urls;
    private ArrayList<String> statusUrls;


    @Override
    protected void initialData() {
        setContentView(R.layout.activity_personcenter);
        SharedHelper.getSharedHelper().setBool("isNewAccount", false);

        unbinder = ButterKnife.bind(this);
        presenter = new PersonCenterPresenter(this);
        loadDataFromSharedPreference();


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromSharedPreference();
    }

    public void loadDataFromSharedPreference() {
//        CodeHelper.clear(this);
        accountTv.setText(SharedHelper.getSharedHelper().getStr("account", "账号"));
        ageTv.setText(SharedHelper.getSharedHelper().getInt("age", 18) + "");
        cityTv.setText(SharedHelper.getSharedHelper().getStr("city", "地球村"));
        int gender = SharedHelper.getSharedHelper().getInt("gender", 0);
        if (gender == 1) {
            genderTv.setText("女");
        } else {
            genderTv.setText("男");
        }
        String pwdStr = SharedHelper.getSharedHelper().getStr("password", "******");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < pwdStr.length(); i++) {
            stringBuffer.append("*");
        }
        passwordTv.setText(stringBuffer.toString());
        schoolTv.setText(SharedHelper.getSharedHelper().getStr("school", "南京大学"));
        nameTv.setText(SharedHelper.getSharedHelper().getStr("name", "啊翔"));
        switch (SharedHelper.getSharedHelper().getInt("status", 0)) {
            case -1: {
                status_container.setClickable(true);
                statusTv.setText("验证错误");
                break;
            }
            case 0: {
                status_container.setClickable(true);
                statusTv.setText("未验证");
                break;
            }
            case 1: {
                status_container.setClickable(false);
                statusTv.setText("已上传，待验证");
                break;
            }
            case 2: {
                status_container.setClickable(false);
                statusTv.setText("验证通过");
                break;
            }
        }

        if (SharedHelper.getSharedHelper().getStr("userPic", "/image/Dont't find image!").equals("/image/Dont't find image!")) {
            Glide.with(this).load(R.drawable.testpic).into(picIv);
        } else {
            Glide.with(this).load(getString(R.string.base_url) + SharedHelper.getSharedHelper().getStr("userPic", "")).apply(CodeHelper.getOptions()).into(picIv);
        }

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


    @Override
    public void showReqResult(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//        loadDataFromSharedPreference();
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSharedPreference(String a, String b) {
        SharedHelper.getSharedHelper().setStr(a, b);
        loadDataFromSharedPreference();
    }

    @Override
    public void updateSharedPreference(String a, boolean b) {
        SharedHelper.getSharedHelper().setBool(a, b);
        loadDataFromSharedPreference();
    }

    @Override
    public void updateSharedPreference(String a, int b) {
        SharedHelper.getSharedHelper().setInt(a, b);
        loadDataFromSharedPreference();
    }


    private AlertDialog.Builder getDialog(String title, String content) {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(PersonCenterActivity.this, 0);
        normalDialog.setIcon(R.drawable.modifyicon).setTitle(title).setMessage(content);
        return normalDialog;
    }

    @OnClick(R.id.center_img_container)
    public void changeImg(View view) {
        MultiImageSelector.create(this)
                .showCamera(false) // show camera or not. true by default
                .count(1) // max select image size, 9 by default. used width #.multi()
                .single() // single mode
                .origin(urls) // original select data set, used width #.multi()
                .start(this, REQUEST_CODE_CHOOSE);
    }

    @OnClick(R.id.center_status_container)
    public void changeStatus(View view) {
        MultiImageSelector.create(this)
                .showCamera(false)
                .count(1)
                .single()
                .origin(statusUrls)
                .start(this, REQUEST_CODE_STATUS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            urls = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (urls.size() > 0) {
                String url = urls.get(0).toString();
                File file = new File(url);
                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                // MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);


                presenter.requestUpdateIcon(SharedHelper.getSharedHelper().getAccount(), body);

            }
        }
        if (requestCode == REQUEST_CODE_STATUS && resultCode == RESULT_OK) {
            statusUrls = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (statusUrls.size() > 0) {
                String url = statusUrls.get(0).toString();
                File file = new File(url);
                // 创建 RequestBody，用于封装构建Req1uestBody
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                // MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                presenter.requestUpdateStatus(SharedHelper.getSharedHelper().getAccount(), body);

            }
        }
    }


    @OnClick(R.id.center_name_container)
    public void changeName(View view) {
        AlertDialog.Builder dialog = getDialog("修改用户名", "请输入想要使用的名称(1-15位)");
        final EditText editText = new EditText(PersonCenterActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                System.out.println(SharedHelper.getSharedHelper().getPassword());
                if (Validator.isUsername(m_name)) {
                    presenter.reqUpdateUserTextInfo(m_name, SharedHelper.getSharedHelper().getGender(),
                            SharedHelper.getSharedHelper().getAge(), SharedHelper.getSharedHelper().getCity(),
                            SharedHelper.getSharedHelper().getCode(), SharedHelper.getSharedHelper().getPassword(),
                            SharedHelper.getSharedHelper().getAccount(), SharedHelper.getSharedHelper().getSchool());
                } else {
                    Toast.makeText(PersonCenterActivity.this, "输入的用户名格式错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }

    @OnClick(R.id.center_age_container)
    public void changeAge(View view) {
        AlertDialog.Builder dialog = getDialog("修改年龄", "请输入年龄");
        final EditText editText = new EditText(PersonCenterActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_age = editText.getText().toString();
                if (Validator.isAge(m_age)) {
                    presenter.reqUpdateUserTextInfo(SharedHelper.getSharedHelper().getName(), SharedHelper.getSharedHelper().getGender(),
                            Integer.parseInt(m_age), SharedHelper.getSharedHelper().getCity(),
                            SharedHelper.getSharedHelper().getCode(), SharedHelper.getSharedHelper().getPassword(),
                            SharedHelper.getSharedHelper().getAccount(), SharedHelper.getSharedHelper().getSchool());
                } else {
                    Toast.makeText(PersonCenterActivity.this, "输入的年龄不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }

    @OnClick(R.id.center_city_container)
    public void changeCity(View view) {
        AlertDialog.Builder dialog = getDialog("修改城市", "请输入城市");
        final EditText editText = new EditText(PersonCenterActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_city = editText.getText().toString();
                if (Validator.isChinese(m_city)) {
                    presenter.reqUpdateUserTextInfo(SharedHelper.getSharedHelper().getName(), SharedHelper.getSharedHelper().getGender(),
                            SharedHelper.getSharedHelper().getAge(), m_city,
                            SharedHelper.getSharedHelper().getCode(), SharedHelper.getSharedHelper().getPassword(),
                            SharedHelper.getSharedHelper().getAccount(), SharedHelper.getSharedHelper().getSchool());
                } else {
                    Toast.makeText(PersonCenterActivity.this, "输入的城市", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }

    @OnClick(R.id.center_edu_container)
    public void changeSchool(View view) {
        AlertDialog.Builder dialog = getDialog("修改学校", "请输入学校");
        final EditText editText = new EditText(PersonCenterActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_school = editText.getText().toString();
                if (Validator.isChinese(m_school)) {
                    presenter.reqUpdateUserTextInfo(SharedHelper.getSharedHelper().getName(), SharedHelper.getSharedHelper().getGender(),
                            SharedHelper.getSharedHelper().getAge(), SharedHelper.getSharedHelper().getCity(),
                            SharedHelper.getSharedHelper().getCode(), SharedHelper.getSharedHelper().getPassword(),
                            SharedHelper.getSharedHelper().getAccount(), m_school);
                } else {
                    Toast.makeText(PersonCenterActivity.this, "输入的城市", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }

    @OnClick(R.id.center_password_container)
    public void changePassword(View view) {
        AlertDialog.Builder dialog = getDialog("修改密码", "请输入新密码");
        final EditText editText = new EditText(PersonCenterActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_pwd = editText.getText().toString();
                if (Validator.isUserPass(m_pwd)) {
                    presenter.reqUpdateUserTextInfo(SharedHelper.getSharedHelper().getName(), SharedHelper.getSharedHelper().getGender(),
                            SharedHelper.getSharedHelper().getAge(), SharedHelper.getSharedHelper().getCity(),
                            SharedHelper.getSharedHelper().getCode(), m_pwd,
                            SharedHelper.getSharedHelper().getAccount(), SharedHelper.getSharedHelper().getSchool());
                } else {
                    Toast.makeText(PersonCenterActivity.this, "输入的城市", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }

    @OnClick(R.id.center_gender_container)
    public void changeGender(View view) {
        final String[] list = {"男", "女"};
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(PersonCenterActivity.this, 0);
        dialog.setIcon(R.drawable.modifyicon).setTitle("选择性别");
        final int[] type = {0};
        dialog.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type[0] = which;
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.reqUpdateUserTextInfo(SharedHelper.getSharedHelper().getName(), type[0],
                        SharedHelper.getSharedHelper().getAge(), SharedHelper.getSharedHelper().getCity(),
                        SharedHelper.getSharedHelper().getCode(), SharedHelper.getSharedHelper().getPassword(),
                        SharedHelper.getSharedHelper().getAccount(), SharedHelper.getSharedHelper().getSchool());
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("user cancel the action");
            }
        });
        dialog.show();
    }
}


