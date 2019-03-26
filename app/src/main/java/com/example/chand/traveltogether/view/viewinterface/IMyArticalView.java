package com.example.chand.traveltogether.view.viewinterface;

import com.example.chand.traveltogether.model.ArticalEntity;

import java.util.ArrayList;

public interface IMyArticalView {
    void setPerformanceData(ArrayList<ArticalEntity> entities);
    void showError(String s);
}
