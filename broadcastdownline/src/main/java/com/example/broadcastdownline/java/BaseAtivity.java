package com.example.broadcastdownline.java;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.broadcastdownline.BroadcastDownline;

/**
 * Created by td on 2018/4/17.
 */

public class BaseAtivity extends AppCompatActivity {
    BroadcastDownline broadcastDownline;
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

    @Override
    protected void onResume() {
        super.onResume();
        //注册广播
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.example.broadcastdownline.BroadcastDownline");
        broadcastDownline=new BroadcastDownline();
        registerReceiver(broadcastDownline,filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (broadcastDownline!=null){
            unregisterReceiver(broadcastDownline);
            broadcastDownline=null;
        }

    }
}
