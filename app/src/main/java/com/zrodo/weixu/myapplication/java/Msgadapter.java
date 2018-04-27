package com.zrodo.weixu.myapplication.java;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zrodo.weixu.myapplication.R;

import java.util.List;

/**
 * Created by td on 2018/4/26.
 */

public class Msgadapter extends RecyclerView.Adapter<Msgadapter.ViewHold> {
   private List<Msg> list;

    public Msgadapter(List<Msg> list) {
        this.list = list;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msglayout,parent,false);

        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        Msg msg=list.get(position);
        if (msg.getType()==Msg.TYPE_receive){
            holder.linearLayoutleft.setVisibility(View.VISIBLE);
            holder.linearLayoutright.setVisibility(View.GONE);
            holder.textViewleft.setText(msg.getMsgtext());
        }else {
            holder.linearLayoutleft.setVisibility(View.GONE);
            holder.linearLayoutright.setVisibility(View.VISIBLE);
            holder.textViewrihgt.setText(msg.getMsgtext());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHold extends RecyclerView.ViewHolder {
        LinearLayout linearLayoutleft,linearLayoutright;
        TextView textViewleft,textViewrihgt;



        public ViewHold(View itemView) {
            super(itemView);
            linearLayoutleft=(LinearLayout) itemView.findViewById(R.id.LinearLeft);
            linearLayoutright=(LinearLayout) itemView.findViewById(R.id.Linearright);

            textViewleft = (TextView) itemView.findViewById(R.id.text_left);
            textViewrihgt = (TextView) itemView.findViewById(R.id.textRight);
        }
    }
}
