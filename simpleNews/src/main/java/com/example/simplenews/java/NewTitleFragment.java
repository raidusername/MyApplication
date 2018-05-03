package com.example.simplenews.java;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplenews.R;

import java.util.ArrayList;
import java.util.List;

public class NewTitleFragment extends Fragment {
    private View view;
 private boolean isTWO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_title_listlayout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public List<NewsBean> getNews() {
        List<NewsBean> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            NewsBean newsBean = new NewsBean();
            newsBean.setTitle("this is title " + i);
            newsBean.setContent("this is content " + i);
            list.add(newsBean);
        }
        return list;
    }

        @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content)!=null){
            isTWO=true;
        }else {
            isTWO=false;
        }
    }
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHold> {
        List<NewsBean> list;


        public NewsAdapter(List<NewsBean> list) {
            this.list = list;
        }

        @Override
        public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
            final ViewHold viewHold = new ViewHold(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsBean newsBean = list.get(viewHold.getAdapterPosition());
                    if (isTWO){
                        //如果是双页模式
                        New_Fragment new_fragment=(New_Fragment) getFragmentManager().findFragmentById(R.id.fragment_news_content);
                        new_fragment.refresh(newsBean.getTitle(),newsBean.getContent());

                    }else {
                        //如果是单页模式
                        Log.d("aaaa", newsBean.getTitle());
                        BaseActivity.actionStrat(getActivity(), newsBean.getTitle(), newsBean.getContent());
                    }


                }
            });
            return viewHold;
        }

        @Override
        public void onBindViewHolder(ViewHold holder, int position) {
            NewsBean newsBean = list.get(position);
            holder.title.setText(newsBean.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHold extends RecyclerView.ViewHolder {
            TextView title;


            public ViewHold(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.news_list_title);
            }
        }
    }

}
