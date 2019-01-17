package com.example.chand.traveltogether.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chand.traveltogether.R;
import com.example.chand.traveltogether.view.activity.SearchResultActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {
    @BindView(R.id.topic_1)
    ImageView topic_1;
    @BindView(R.id.topic_2)
    ImageView topic_2;
    @BindView(R.id.topic_3)
    ImageView topic_3;
    @BindView(R.id.topic_4)
    ImageView topic_4;

    @Override
    public void initalData() {
        setLayout(R.layout.fragment_search);

    }

    @Override
    public void destoryData() {

    }

    @Override
    public void inflateView() {
        Glide.with(getContext()).load(R.drawable.topic_1).into(topic_1);
        Glide.with(getContext()).load(R.drawable.topic_2).into(topic_2);
        Glide.with(getContext()).load(R.drawable.topic_3).into(topic_3);
        Glide.with(getContext()).load(R.drawable.topic_4).into(topic_4);
    }

    @OnClick({R.id.topic_1, R.id.topic_2, R.id.topic_3, R.id.topic_4})
    public void search(View view) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        String theme;
        switch (view.getId()) {
            case R.id.topic_1: {
                theme = "运动";
                break;
            }
            case R.id.topic_2: {
                theme = "旅行";
                break;
            }
            case R.id.topic_3: {
                theme = "音乐";
                break;
            }
            case R.id.topic_4: {
                theme = "创意";
                break;
            }
            default:
                theme = "运动";
        }
        intent.putExtra("theme", theme);
        startActivity(intent);
//    }

    }
}
