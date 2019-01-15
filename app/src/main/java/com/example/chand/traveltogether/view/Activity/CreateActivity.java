package com.example.chand.traveltogether.view.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.Utils.CodeHelper;
import com.example.chand.traveltogether.Utils.SharedHelper;
import com.example.chand.traveltogether.Utils.Validator;
import com.example.chand.traveltogether.presenter.CreatePresenter;
import com.example.chand.traveltogether.presenter.Interface.ICreatePresenter;
import com.example.chand.traveltogether.view.Interface.ICreateView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateActivity extends BaseActivity implements ICreateView {
    private String type;
    private String start;
    private String account;
    private String title;
    private String end;
    private String details;
    private int price = -1;
    private String location;
    private String city;
    private String url;
    private Unbinder unbinder;
    private ICreatePresenter presenter;
    private MultipartBody.Part file_body;
    private final int REQUEST_CODE_CHOOSE = 998;
    private ArrayList<String> urls;
    @BindView(R.id.create_user_type)
    TextView typeTv;
    @BindView(R.id.create_ac_account)
    TextView accountTv;
    @BindView(R.id.create_ac_city)
    TextView cityTv;
    @BindView(R.id.create_location)
    TextView locationTv;
    @BindView(R.id.create_starttime)
    TextView startTv;
    @BindView(R.id.create_user_stoptime)
    TextView stopTv;
    @BindView(R.id.create_user_cost)
    TextView costTv;
    @BindView(R.id.create_title)
    TextView titleTv;
    @BindView(R.id.create_user_detail)
    TextView detailTv;
    @BindView(R.id.create_ac_img)
    ImageView imgIv;


    @Override
    protected void initialData() {
        setContentView(R.layout.activity_create);
        unbinder = ButterKnife.bind(this);
        presenter = new CreatePresenter(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        accountTv.setText(SharedHelper.getSharedHelper().getAccount());
        account = SharedHelper.getSharedHelper().getAccount();

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
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
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
                Glide.with(this).load(url).into(imgIv);

            }
        }
    }


    @OnClick(R.id.create_type_container)
    public void pickType(View view) {
        final String[] list = {"运动", "旅行", "音乐", "创意"};
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(CreateActivity.this, 0);
        dialog.setIcon(R.drawable.modifyicon).setTitle("选择类别");
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
                CreateActivity.this.type = list[type[0]];
                typeTv.setText(list[type[0]]);
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

    @OnClick(R.id.create_ac_img_container)
    public void chooseImg(View view) {
        MultiImageSelector.create(this)
                .showCamera(false) // show camera or not. true by default
                .count(1) // max select image size, 9 by default. used width #.multi()
                .single() // single mode
                .origin(urls) // original select data set, used width #.multi()
                .start(this, REQUEST_CODE_CHOOSE);
    }

    private AlertDialog.Builder getDialog(String title, String content) {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(CreateActivity.this, 0);
        normalDialog.setIcon(R.drawable.modifyicon).setTitle(title).setMessage(content);
        return normalDialog;
    }

    @OnClick(R.id.create_city_container)
    public void setCity(View view) {
        AlertDialog.Builder dialog = getDialog("请指定地点", "请输入旅行的地点");
        final EditText editText = new EditText(CreateActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateActivity.this.city = m_name;
                    cityTv.setText(m_name);
                } else {
                    Toast.makeText(CreateActivity.this, "输入的地点错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_location_container)
    public void setLocation(View view) {
        AlertDialog.Builder dialog = getDialog("请指定区域", "请输入旅行的区域");
        final EditText editText = new EditText(CreateActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateActivity.this.location = m_name;
                    locationTv.setText(m_name);
                } else {
                    Toast.makeText(CreateActivity.this, "输入的区域错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_cost_container)
    public void setCost(View view) {
        AlertDialog.Builder dialog = getDialog("请输入预估费用", "请输入此次旅行预估的费用");
        final EditText editText = new EditText(CreateActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (Validator.isNum(m_name)) {
                    CreateActivity.this.price = Integer.parseInt(m_name);
                    costTv.setText(m_name);
                } else {
                    Toast.makeText(CreateActivity.this, "输入的格式错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_title_container)
    public void setTitle(View view) {
        AlertDialog.Builder dialog = getDialog("请指定标题", "请输入旅行的标题");
        final EditText editText = new EditText(CreateActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateActivity.this.title = m_name;
                    titleTv.setText(m_name);
                } else {
                    Toast.makeText(CreateActivity.this, "输入的标题错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_detail_container)
    public void setDetails(View view) {
        AlertDialog.Builder dialog = getDialog("请输入旅行详情", "请输入此次旅行的详细介绍");
        final EditText editText = new EditText(CreateActivity.this);
        dialog.setView(editText);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_name = editText.getText().toString();
                if (m_name.length() > 2) {
                    CreateActivity.this.details = m_name;
                    detailTv.setText(m_name);
                } else {
                    Toast.makeText(CreateActivity.this, "输入的区域错误", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.create_starttime_container)
    public void setStart(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(CreateActivity.this, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String b = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                CreateActivity.this.start = b;
                startTv.setText(b);
            }
        }, now.get(Calendar.YEAR), (now.get(Calendar.MONTH) + 1), now.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @OnClick(R.id.create_stoptime_container)
    public void setEnd(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(CreateActivity.this, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String b = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                CreateActivity.this.end = b;
                stopTv.setText(b);
            }
        }, now.get(Calendar.YEAR), (now.get(Calendar.MONTH) + 1), now.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @OnClick(R.id.create_ac_btn)
    public void reqAddActivity(View view) {
        if (isMatch(city) && isMatch(location) && isMatch(type) && isMatch(title) && isMatch(details) && isMatch(start) && isMatch(end) && price != -1 && isMatch(url)) {
            String[] starts = start.split("-");
            String[] ends = end.split("-");
            Calendar now = Calendar.getInstance();
            String[] l = new String[3];
            l[0] = now.get(Calendar.YEAR) + "";
            l[1] = (now.get(Calendar.MONTH) + 1) + "";
            l[2] = now.get(Calendar.DAY_OF_MONTH) + "";
            boolean pass = false;
            for (int i = 1; i <= 2; i++) {
                if (Integer.parseInt(starts[i]) >= Integer.parseInt(ends[i]) && Integer.parseInt(starts[i]) >= Integer.parseInt(l[i])) {
                    pass = true;
                    break;
                }
            }
            if (pass) {

                presenter.reqCreateActivity(account, city, location, title, details, start, end, type, price,file_body);
            } else {
                Toast.makeText(this, "时间信息不对", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "输入的信息不全", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isMatch(String s) {
        if (s == null) {
            return false;
        } else if (s.length() != 0 && !s.equals("未设置")) {
            return true;
        } else {
            return false;
        }
    }
}
