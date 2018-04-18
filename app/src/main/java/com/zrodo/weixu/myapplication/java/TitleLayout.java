package com.zrodo.weixu.myapplication.java;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zrodo.weixu.myapplication.R;

/**
 * Created by td on 2018/4/18.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener{
    public  Context context;
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        ImageButton imageButton1=(ImageButton) findViewById(R.id.imageButton);
        ImageButton imageButton2=(ImageButton) findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.imageButton:
              Toast.makeText(context,"fanhui",Toast.LENGTH_LONG).show();
              break;
          case R.id.imageButton2:
              Toast.makeText(context,"不知道是",Toast.LENGTH_LONG).show();
              break;
      }

    }
}
