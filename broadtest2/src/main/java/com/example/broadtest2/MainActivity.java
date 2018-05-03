package com.example.broadtest2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceive localReceive;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_localbroad);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadtest2.LocalReceive");
        localReceive = new LocalReceive();
        localBroadcastManager.registerReceiver(localReceive, intentFilter);//注册本地广播
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送本地广播
                final Intent intent = new Intent("com.example.broadtest2.LocalReceive");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }
}
