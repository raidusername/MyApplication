package com.example.simplenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simplenews.java.BaseActivity;
import com.example.simplenews.java.New_Fragment;

public class NewContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        String Titie=getIntent().getStringExtra("newstitle");
        String content=getIntent().getStringExtra("newscontent");
        New_Fragment new_fragment=(New_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment_news_content);
        new_fragment.refresh(Titie,content);

    }
}
