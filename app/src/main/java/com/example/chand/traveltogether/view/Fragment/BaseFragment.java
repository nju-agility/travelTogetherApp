package com.example.chand.traveltogether.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;
    int layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initalData();

        View rootView = inflater.inflate(layout, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        inflateView();
        return rootView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destoryData();
        unbinder.unbind();
    }

    public abstract void initalData();

    public abstract void destoryData();

    public abstract void inflateView();

    public void setLayout(int layout) {
        this.layout = layout;
    }
}
