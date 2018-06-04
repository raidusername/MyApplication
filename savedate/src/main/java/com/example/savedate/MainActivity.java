package com.example.savedate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText editText;
private Button button,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.edt_save);
        button=(Button) findViewById(R.id.btn_save);
        button2=(Button) findViewById(R.id.Shareperferennce_save);
        button3=(Button) findViewById(R.id.Shareperferennce_get);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  str=HandleDate.loaddate(MainActivity.this,"SaveFiledate");
                if (!TextUtils.isEmpty(str)){
                    editText.setText(str);
                    editText.setSelection(str.length());

                }

            }
        });
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String str=editText.getText().toString();
        HandleDate.save(this,str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Shareperferennce_save:
                SharedPreferences.Editor editor=getSharedPreferences("data",0).edit();
                editor.putInt("age",20);
                editor.putString("name","raid");
                editor.apply();
                Intent intent=new Intent(this,DBSave.class);
                startActivity(intent);
                break;
            case R.id.Shareperferennce_get:
                SharedPreferences sharedPreference=getSharedPreferences("data",0);
                String name=sharedPreference.getString("name","");
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                break;
        }

    }
}
