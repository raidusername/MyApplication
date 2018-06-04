package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServiceIntent extends IntentService {

    public MyServiceIntent() {
        super("MyServiceIntent");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前的线程
        Log.d("MyServiceIntent", "thread id is "+Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyServiceIntent", "onDestroy executed ");
    }
}
