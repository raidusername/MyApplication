package com.zrodo.weixu.myapplication.java;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by td on 2018/4/18.
 */

public class BaseAdapter extends ArrayAdapter {


    public BaseAdapter(Context context, int resource) {
        super(context, resource);
    }

    public BaseAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }


    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
