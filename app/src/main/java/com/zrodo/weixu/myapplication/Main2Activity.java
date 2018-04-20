package com.zrodo.weixu.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zrodo.weixu.myapplication.java.BaseAdapter;
import com.zrodo.weixu.myapplication.java.BaseAvtivitty;
import com.zrodo.weixu.myapplication.java.Beanadapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends BaseAvtivitty {
    private TextView textView2;
    private String data1[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "11", "12", "123"};
    private ListView listView;
    private List<Beanadapter> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        textView2 = (TextView) findViewById(R.id.text2);
        listView = (ListView) findViewById(R.id.list_info);
        Intent intent = getIntent();
        list=new ArrayList<>();
        String data = intent.getStringExtra("param1");
        textView2.setText(data);
        inilist();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, data1);
        BaseAdapter baseAdapter=new BaseAdapter(Main2Activity.this,R.layout.list_layout);
        baseAdapter.addAll(list);
        listView.setAdapter(baseAdapter);


    }
public void inilist(){

        for (int i=0;i<=11;i++){
            Beanadapter beanadapter=new Beanadapter(R.mipmap.ic_launcher,data1[i]);

            list.add(beanadapter);

    }
}
    //跳转
    public static void Actiononstart(Context context, String data1) {
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra("param1", data1);
        context.startActivity(intent);

    }
}
