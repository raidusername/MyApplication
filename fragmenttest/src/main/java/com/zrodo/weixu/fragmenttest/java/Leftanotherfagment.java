package com.zrodo.weixu.fragmenttest.java;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrodo.weixu.fragmenttest.R;

/**
 * Created by lenovo on 2018/5/2.
 */

public class Leftanotherfagment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.left_another_layout,container,false);
        return view;
    }
}
