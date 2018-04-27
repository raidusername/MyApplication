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
    private String data1[] = {"苹果", "香蕉", "樱桃", "葡萄", "芒果", "橘子", "水果li", "菠萝", "草莓", "西瓜"};
   private int fruits[]={R.mipmap.apple_pic,R.mipmap.banana_pic,R.mipmap.cherry_pic,R.mipmap.grape_pic,R.mipmap.mango_pic,R.mipmap.orange_pic,R.mipmap.pear_pic,R.mipmap.pineapple_pic,R.mipmap.strawberry_pic,R.mipmap.watermelon_pic};
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

        for (int i = 0; i <= 9; i++) {
            Bean bean = new Bean(fruits[i], data1[i]);
            Log.d("Bean", bean.getName());

            list.add(bean);
            Log.d("BEAnlist", list.size()+"");

        }
    }
}
