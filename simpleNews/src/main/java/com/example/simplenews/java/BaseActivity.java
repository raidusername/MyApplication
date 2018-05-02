package com.example.simplenews.java;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.simplenews.NewContentActivity;
import com.example.simplenews.R;


public class BaseActivity extends AppCompatActivity{
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content_layout,fragment);
        fragmentTransaction.addToBackStack(null);//用于描述返回湛的状态
        fragmentTransaction.commit();
    }
    public static void actionStrat(Context context,String title,String content){
        Intent intent=new Intent(context, NewContentActivity.class);
        intent.putExtra("newstitle",title);
        intent.putExtra("newscontent",content);
        context.startActivity(intent);

    }
}
