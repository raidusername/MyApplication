package com.example.broadcastdownline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.broadcastdownline.java.BaseAtivity;

public class MainActivity extends BaseAtivity {
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.downline);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("offline", "onClick: 强制离线");
                Intent intent=new Intent("com.example.broadcastdownline.BroadcastDownline");
                sendBroadcast(intent);
            }
        });
    }
}
