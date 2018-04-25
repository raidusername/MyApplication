package com.zrodo.weixu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.zrodo.weixu.myapplication.java.Adapter3;
import com.zrodo.weixu.myapplication.java.Bean;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String data1[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "11", "12", "123"};
    private List<Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView = (RecyclerView) findViewById(R.id.recyclelist);
        list=new ArrayList<>();
        inilist();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter3 adapter3 = new Adapter3(list);
        recyclerView.setAdapter(adapter3);


    }

    public void inilist() {

        for (int i = 0; i <= 12; i++) {
            Bean bean = new Bean(R.mipmap.ic_launcher, data1[i]);
            Log.d("Bean", bean.getName());

            list.add(bean);
            Log.d("BEAnlist", list.size()+"");

        }
    }
}
