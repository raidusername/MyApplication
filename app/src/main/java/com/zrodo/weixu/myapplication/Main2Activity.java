package com.zrodo.weixu.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zrodo.weixu.myapplication.java.BaseAvtivitty;

public class Main2Activity extends BaseAvtivitty {
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }


        textView2 = (TextView) findViewById(R.id.text2);
        Intent intent = getIntent();
        String data = intent.getStringExtra("param1");
        textView2.setText(data);

    }
    public static void Actiononstart(Context context,String data1){
        Intent intent=new Intent(context,Main2Activity.class);
        intent.putExtra("param1",data1);
        context.startActivity(intent);

    }
}
