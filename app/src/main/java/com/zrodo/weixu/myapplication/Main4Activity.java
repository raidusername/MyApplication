package com.zrodo.weixu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.zrodo.weixu.myapplication.java.Msg;
import com.zrodo.weixu.myapplication.java.Msgadapter;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
 private List<Msg> list;
 private Button btn_sub;
 private EditText editText;
 private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        init();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final Msgadapter msgadapter=new Msgadapter(list);
        recyclerView.setAdapter(msgadapter);
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=editText.getText().toString();
                if (!"".equals(msg)){
                    Msg msg1=new Msg(msg,Msg.TYPE_send);
                    list.add(msg1);
                    msgadapter.notifyItemInserted(list.size()-1);//当有新消息的时候刷新listviw
                    recyclerView.scrollToPosition(list.size()-1);//将listview 定位到最后一行
                    editText.setText("");//输入框中清空
                }
            }
        });

    }
    private void init(){
        list= new ArrayList<>();
        btn_sub=(Button) findViewById(R.id.btn_submit);
        editText=(EditText) findViewById(R.id.edit_qq);
        recyclerView=(RecyclerView) findViewById(R.id.recycler4);
        Msg msg1=new Msg("ddddddd",Msg.TYPE_receive);
        Msg msg2=new Msg("qqqqqqqqqqq",Msg.TYPE_send);
        Msg msg3=new Msg("eeeeeee",Msg.TYPE_receive);
        Msg msg4=new Msg("rrrrrrrrr",Msg.TYPE_send);
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);
        list.add(msg4);

    }
}
