package com.example.mythread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button change;
private TextView show;
private Handler handler;
private final int UPDATE_TEXT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change=(Button) findViewById(R.id.change);
        show=(TextView) findViewById(R.id.show);

        change.setOnClickListener(this);
        //异步消息处理
        handler=new Handler(){
          public void handleMessage(Message msg){
              switch (msg.what){
                  case UPDATE_TEXT:
                      show.setText("nice to meet you");
                      break;
              }
          }
        };
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.change:
        new Thread(new Runnable() {
            @Override
            public void run() {
//异步消息处理
//                Message message=new Message();
//                message.what=UPDATE_TEXT;
//                handler.sendMessage(message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        show.setText("nice to meet you to");
                    }
                });

            }
        }).start();


        break;
}
    }
}
