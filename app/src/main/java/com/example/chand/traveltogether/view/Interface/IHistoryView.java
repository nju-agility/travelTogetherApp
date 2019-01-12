package com.example.chand.traveltogether.view.Interface;

import com.example.chand.traveltogether.model.ActivityEntity;

import java.util.ArrayList;

public interface IHistoryView {
    void setPerformanceData(ArrayList<ActivityEntity> entities);
    void showError(String s);
}
