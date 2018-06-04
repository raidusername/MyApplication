package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button btn_start,btn_end,btnstart_bind,btnend_bind,start_intent;
private MyService.DownloadBinder downloadBinder;
private ServiceConnection connection=new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        downloadBinder=(MyService.DownloadBinder)service;
        downloadBinder.startDown();
        downloadBinder.getProgress();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start=(Button) findViewById(R.id.startservice);
        btn_end=(Button) findViewById(R.id.endservice);
        btnstart_bind=(Button) findViewById(R.id.start_Bind);
        btnend_bind=(Button) findViewById(R.id.end_Bind);
        start_intent=(Button) findViewById(R.id.star_intent);
        btn_start.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        btnstart_bind.setOnClickListener(this);
        btnend_bind.setOnClickListener(this);
        start_intent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startservice:
                Intent intent=new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.endservice:
                Intent intent1=new Intent(this,MyService.class);
               stopService(intent1);
                break;
            case R.id.start_Bind:
                Intent intent2=new Intent(this,MyService.class);
                bindService(intent2,connection,BIND_AUTO_CREATE);


                break;
            case R.id.end_Bind:
                unbindService(connection);
                break;
            case R.id.star_intent:
                Log.d("MainActivity", "thread id is "+Thread.currentThread().getId());
                Intent intent3=new Intent(this,MyServiceIntent.class);
                startService(intent3);
                break;
        }
    }
}
