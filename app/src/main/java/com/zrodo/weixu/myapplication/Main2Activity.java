package com.zrodo.weixu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zrodo.weixu.myapplication.java.Adapter2;
import com.zrodo.weixu.myapplication.java.BaseAtivity;
import com.zrodo.weixu.myapplication.java.Bean;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends BaseAtivity {
    private TextView textView2;
    private String data1[] = {"苹果", "香蕉", "樱桃", "葡萄", "芒果", "橘子", "水果li", "菠萝", "草莓", "西瓜"};
    private int fruits[]={R.mipmap.apple_pic,R.mipmap.banana_pic,R.mipmap.cherry_pic,R.mipmap.grape_pic,R.mipmap.mango_pic,R.mipmap.orange_pic,R.mipmap.pear_pic,R.mipmap.pineapple_pic,R.mipmap.strawberry_pic,R.mipmap.watermelon_pic};
    private ListView listView;
    private List<Bean> list;

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
        final Intent intent = getIntent();
        list=new ArrayList<>();
        String data = intent.getStringExtra("param1");
        textView2.setText(data);
        inilist();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, data1);
        Adapter2 adapter2 =new Adapter2(Main2Activity.this,R.layout.list_layout);
        adapter2.addAll(list);
        listView.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean bean =list.get(position);
                Intent intent1=new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("name",bean.getName());
                startActivity(intent1);
                Toast.makeText(Main2Activity.this, bean.getName(),Toast.LENGTH_LONG).show();
            }
        });


    }
public void inilist(){

        for (int i=0;i<=9;i++){
            Bean bean =new Bean(fruits[i],data1[i]);

            list.add(bean);

    }
}
    //跳转
    public static void Actiononstart(Context context, String data1) {
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra("param1", data1);
        context.startActivity(intent);

    }


}
