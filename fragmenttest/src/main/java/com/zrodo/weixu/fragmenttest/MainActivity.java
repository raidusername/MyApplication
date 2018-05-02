package com.zrodo.weixu.fragmenttest;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zrodo.weixu.fragmenttest.java.BaseActivity;
import com.zrodo.weixu.fragmenttest.java.LeftFragment;
import com.zrodo.weixu.fragmenttest.java.Leftanotherfagment;
import com.zrodo.weixu.fragmenttest.java.RightFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
Button button;
int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.left_one);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_one:
                if (i%2==0){
                    replaceFragment(new Leftanotherfagment());
                }else{
                    replaceFragment(new RightFragment());
                }
                i++;
                Log.d("button", "点击数件");

                break;
            default:
                break;
        }

    }

}
