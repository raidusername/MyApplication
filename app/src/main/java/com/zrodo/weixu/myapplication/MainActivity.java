package com.zrodo.weixu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button btnone;
private ListView list_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnone=(Button) findViewById(R.id.btnone);
        list_item=(ListView) findViewById(R.id.list_item);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击第一次",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymain,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case R.id.additem:
            Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_LONG).show();
            break;
        case R.id.removeitem:
            Toast.makeText(MainActivity.this,"退出",Toast.LENGTH_LONG).show();
            finish();
            break;

        }
        return true;
    }
}
