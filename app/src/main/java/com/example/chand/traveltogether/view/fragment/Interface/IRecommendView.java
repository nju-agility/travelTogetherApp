package com.example.chand.traveltogether.view.fragment.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;

import java.util.ArrayList;

public interface IRecommendView {
    void setPerformanceData(ArrayList<ActivityEntity> entities);
    void showCardView();
    void stopRefreshing();
    void showError(String s);
}
