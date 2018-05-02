package com.zrodo.weixu.fragmenttest.java;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zrodo.weixu.fragmenttest.R;

public class BaseActivity extends AppCompatActivity{
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.right_fragment,fragment);
        fragmentTransaction.addToBackStack(null);//用于描述返回湛的状态
        fragmentTransaction.commit();
    }
}
