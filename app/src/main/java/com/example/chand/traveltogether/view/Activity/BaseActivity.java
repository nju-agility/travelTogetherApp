package com.example.chand.traveltogether.view.Activity;
import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyData();
    }

    protected abstract void initialData();
    protected abstract void saveData();
    protected abstract void destroyData();

}
