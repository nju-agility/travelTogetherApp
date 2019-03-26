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
import com.example.chand.traveltogether.presenter.CreateArticalPresenter;
import com.example.chand.traveltogether.presenter.presenternterface.ICreateArticalPresenter;
import com.example.chand.traveltogether.utils.CodeHelper;
import com.example.chand.traveltogether.utils.SharedHelper;
import com.example.chand.traveltogether.utils.Validator;
import com.example.chand.traveltogether.view.viewinterface.ICreateArticalView;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateArticalActivity extends BaseActivity implements ICreateArticalView {
    Unbinder unbinder;
    ICreateArticalPresenter presenter;
    @BindView(R.id.create_artical_ac_img_container)
    ConstraintLayout imgContainer;
    @BindView(R.id.create_artical_ac_img)
    ImageView img;
    @BindView(R.id.create_artical_ac_account_container)
    ConstraintLayout accountContainer;
    @BindView(R.id.create_artical_ac_account)
    TextView account;
    @BindView(R.id.create_artical_city_container)
    ConstraintLayout cityContainer;
    @BindView(R.id.create_artical_ac_city)
    TextView city;
    @BindView(R.id.create_artical_location_container)
    ConstraintLayout locationContainer;
    @BindView(R.id.create_artical_location)
    TextView location;
    @BindView(R.id.create_artical_title_container)
    ConstraintLayout titleContainer;
    @BindView(R.id.create_artical_title)
    TextView title;
    @BindView(R.id.create_artical_detail_container)
    ConstraintLayout detailContainer;
    @BindView(R.id.create_artical_user_detail)
    TextView detail;

    private final int REQUEST_CODE_CHOOSE_IMG = 1009;
    private ArrayList<String> urls = new ArrayList<>();
    private MultipartBody.Part file_body;
    private String url;

    @Override
    protected void initialData() {
        setContentView(R.layout.activity_create_artical);
        ButterKnife.bind(this);
        presenter = new CreateArticalPresenter(this);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        account.setText(SharedHelper.getSharedHelper().getAccount());
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

    private AlertDialog.Builder getDialog(String title, String content) {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(CreateArticalActivity.this, 0);
        normalDialog.setIcon(R.drawable.modifyicon).setTitle(title).setMessage(content);
        return normalDialog;
    }

    @Override
    protected void saveData() {

    }

    @Override
    protected void destroyData() {
        unbinder.unbind();
    }

    @Override
    public void requestSuccess() {
        Toast.makeText(this, "游记创建成功!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void requestError() {
        Toast.makeText(this, "游记创建失败!请重试!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_IMG && resultCode == RESULT_OK) {
            urls = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (urls.size() > 0) {
                url = urls.get(0).toString();
                File file = new File(url);
                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                // MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                file_body = body;
                CodeHelper.clear(this);
                Glide.with(this).load(url).into(img);

            }
        }
    }

    @OnClick(R.id.create_artical_ac_img_container)
    public void chooseImg(View view) {
        MultiImageSelector.create(this)
                .showCamera(false) // show camera or not. true by default
                .count(1) // max select image size, 9 by default. used width #.multi()
                .single() // single mode
                .origin(urls) // original select data set, used width #.multi()
                .start(this, REQUEST_CODE_CHOOSE_IMG);
    }

    @OnClick(R.id.create_artical_city_container)
    public void setCity(View view) {
        AlertDialog.Builder dialog = getDialog("请指定地点", "请输入旅行的地点");
        final EditText editText = new EditText(CreateArticalActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateArticalActivity.this.city.setText(m_name);
                } else {
                    Toast.makeText(CreateArticalActivity.this, "输入的地点错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_artical_location_container)
    public void setLocation(View view) {
        AlertDialog.Builder dialog = getDialog("请指定区域", "请输入旅行的区域");
        final EditText editText = new EditText(CreateArticalActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateArticalActivity.this.location.setText(m_name);
                } else {
                    Toast.makeText(CreateArticalActivity.this, "输入的区域错误", Toast.LENGTH_SHORT).show();
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


    @OnClick(R.id.create_artical_title_container)
    public void setTitle(View view) {
        AlertDialog.Builder dialog = getDialog("请指定标题", "请输入旅行的标题");
        final EditText editText = new EditText(CreateArticalActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateArticalActivity.this.title.setText(m_name);
                } else {
                    Toast.makeText(CreateArticalActivity.this, "输入的标题错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_artical_detail_container)
    public void setDetails(View view) {
        AlertDialog.Builder dialog = getDialog("请输入旅行详情", "请输入此次旅行的详细介绍");
        final EditText editText = new EditText(CreateArticalActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateArticalActivity.this.detail.setText(m_name);
                } else {
                    Toast.makeText(CreateArticalActivity.this, "输入的区域错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_artical_ac_btn)
    public void requestCreateArtical(View view) {
        if (url.length() > 0 && city.getText().toString().length() > 0 && location.getText().toString().length() > 0
                && title.getText().toString().length() > 0 && detail.getText().toString().length() > 0) {
            presenter.requestCreateArtical(account.getText().toString(), city.getText().toString(), location.getText().toString(),
                    title.getText().toString(), detail.getText().toString(), DateFormat.getDateInstance().format(new Date()), file_body);
        }
    }
}
