package com.zrodo.weixu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends BaseAvtivitty {
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        textView2 = (TextView) findViewById(R.id.text2);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        textView2.setText(data);

    }
}
