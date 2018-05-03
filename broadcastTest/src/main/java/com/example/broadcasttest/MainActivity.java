package com.example.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.broadcasttest.java.NetworkChangeRecriver;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeRecriver networkChangeReveiver;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        button = (Button) findViewById(R.id.bRecriver);


        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReveiver = new NetworkChangeRecriver();
        registerReceiver(networkChangeReveiver, intentFilter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.java.MyBroadCast");
                // sendBroadcast(intent);//发送无序广播
                sendOrderedBroadcast(intent, null);//发送有序广播


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReveiver);
    }
}
