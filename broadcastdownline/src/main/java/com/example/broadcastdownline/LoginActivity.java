package com.example.broadcastdownline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.broadcastdownline.java.BaseAtivity;

public class LoginActivity extends BaseAtivity implements View.OnClickListener{
    private EditText edtname,edtpwd;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        button.setOnClickListener(this);

    }
    public void init(){
        edtname=(EditText) findViewById(R.id.edt_username);
        edtpwd=(EditText) findViewById(R.id.edt_pwdworld);
        button=(Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String acount=edtname.getText().toString();
                String pwd=edtpwd.getText().toString();
                //如果账号是admin 密码是123456  登录成功
                if (acount.equals("admin")&&pwd.equals("123456")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"账户或者密码无效",Toast.LENGTH_LONG).show();
                }

                break;

        }

    }
}
