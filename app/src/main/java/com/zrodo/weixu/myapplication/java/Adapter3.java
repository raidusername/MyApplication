package com.zrodo.weixu.myapplication.java;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zrodo.weixu.myapplication.Main4Activity;
import com.zrodo.weixu.myapplication.R;

import java.util.List;

/**
 * Created by td on 2018/4/25.
 */

public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHold> {
private List<Bean> Beanlist;

    public Adapter3(List<Bean> beanlist) {
        Beanlist = beanlist;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout2,parent,false);
       final ViewHold viewHold=new ViewHold(view);
        viewHold.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int postion=viewHold.getAdapterPosition();
                Bean bean=Beanlist.get(postion);
                Toast.makeText(view.getContext(), bean.getName(),Toast.LENGTH_LONG).show();
            }
        });
        viewHold.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int postion=viewHold.getAdapterPosition();
                if(postion==0){
                    Intent intent=new Intent(view.getContext(), Main4Activity.class);
                    view.getContext().startActivity(intent);

                }else{
                    Bean bean=Beanlist.get(postion);
                    Toast.makeText(view.getContext(), bean.getImgid(),Toast.LENGTH_LONG).show();
                }

            }
        });
        return viewHold;
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        Bean bean=Beanlist.get(position);
        holder.imageView.setImageResource(bean.getImgid());
        holder.textView.setText(bean.getName());


    }

    @Override
    public int getItemCount() {
        return Beanlist.size();
    }

    static class ViewHold extends RecyclerView.ViewHolder {
        View listView;
        ImageView imageView;
        TextView textView;

        public ViewHold(View itemView) {
            super(itemView);
            listView=itemView;
            imageView = (ImageView) itemView.findViewById(R.id.list_button);
            textView = (TextView) itemView.findViewById(R.id.list_text);
        }
    }


}
