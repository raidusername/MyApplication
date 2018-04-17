package com.zrodo.weixu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        textView2=(TextView) findViewById(R.id.text2);
        Intent intent=getIntent();
        String data=intent.getStringExtra("data");
        textView2.setText(data);
    }
}
