package com.example.simplenews.java;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplenews.R;

public class New_Fragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_content_layout, container, false);

        return view;
    }

    public void refresh(String Title, String conetnt) {
        View visibliity = view.findViewById(R.id.visiblillty);
        visibliity.setVisibility(View.VISIBLE);
        TextView newstitle = (TextView) view.findViewById(R.id.news_title);
        TextView newscontent = (TextView) view.findViewById(R.id.news_content);
        newstitle.setText(Title);
        newscontent.setText(conetnt);

    }
}
