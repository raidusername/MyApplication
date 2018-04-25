package com.zrodo.weixu.myapplication.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zrodo.weixu.myapplication.java.ActivityCollector;

/**
 * Created by td on 2018/4/17.
 */

public class BaseAtivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d("BaseActivity", getClass().getSimpleName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BaseActivity", "remove" + getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }
}
