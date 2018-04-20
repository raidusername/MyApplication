package com.zrodo.weixu.myapplication.java;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zrodo.weixu.myapplication.R;

import java.util.List;

/**
 * Created by td on 2018/4/18.
 */

public class BaseAdapter extends ArrayAdapter<Beanadapter> {

    private int viewResourceId;

    public BaseAdapter(Context context, int resource) {
        super(context, resource);
        this.viewResourceId = resource;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Beanadapter baseAdapter=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(viewResourceId,parent,false);
        ImageButton imageButton=(ImageButton) view.findViewById(R.id.list_button);
        TextView textView=(TextView) view.findViewById(R.id.list_text);
        imageButton.setImageResource(baseAdapter.getImgid());
        textView.setText(baseAdapter.getName());
        return view;
    }
}
