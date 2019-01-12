package com.example.chand.traveltogether.view.Fragment.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;

import java.util.ArrayList;
import java.util.List;

public interface IRecommendView {
    void setPerformanceData(ArrayList<ActivityEntity> entities);
    void showCardView();
    void stopRefreshing();
    void showError(String s);
}
