package com.example.chand.traveltogether.view.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;

import java.util.ArrayList;

public interface ISearchResultView {
    void setPerformanceData(ArrayList<ActivityEntity> activityEntities);
    void showError(String s);
}
