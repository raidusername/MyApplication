package com.zrodo.weixu.myapplication.java;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrodo.weixu.myapplication.R;

/**
 * Created by td on 2018/4/18.
 */

public class Adapter2 extends ArrayAdapter<Bean> {

    private int viewResourceId;

    public Adapter2(Context context, int resource) {
        super(context, resource);
        this.viewResourceId = resource;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bean baseAdapter=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView ==null){
            view= LayoutInflater.from(getContext()).inflate(viewResourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageButton=(ImageView) view.findViewById(R.id.list_button);
            viewHolder.textView=(TextView) view.findViewById(R.id.list_text);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.imageButton.setImageResource(baseAdapter.getImgid());
        viewHolder.textView.setText(baseAdapter.getName());
        return view;
    }
    class ViewHolder{
        ImageView imageButton;
        TextView textView;
    }
}
