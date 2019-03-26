package com.example.chand.traveltogether.view.fragment.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;
import com.example.chand.traveltogether.model.ArticalEntity;

import java.util.ArrayList;

public interface IArticalView {
    void setPerformanceData(ArrayList<ArticalEntity> entities);
    void showCardView();
    void stopRefreshing();
    void showError(String s);
}
