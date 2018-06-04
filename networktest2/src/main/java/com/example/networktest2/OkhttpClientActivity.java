package com.example.networktest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.networktest2.utils.Sendrequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpClientActivity extends AppCompatActivity implements View.OnClickListener {
    private Button request;
    private TextView showresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = (Button) findViewById(R.id.request);
        showresponse = (TextView) findViewById(R.id.response);
        request.setOnClickListener(this);


    }

    public void sendrequestwithokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String add = "http://www.zrodo.com:8080/njsyDetectList/doLoginForCYY.do";
                RequestBody body = new FormBody.Builder()
                        .add("username", "zrdqc")
                        .add("password", "cpb123")
                        .add("areaVersionCode", "nanjingyanshi")
                        .build();

                try {
                    //获取响应数据
                    String responses = Sendrequest.sendrequetwithokhttp(add, body);
                    //json 解析
                    //JSONOBJECT(responses);
                    //使用gson 解析json
                    gsonwithjson(responses);

                    showresponse(responses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request:
                sendrequestwithokhttp();

                break;
        }
    }

    public void showresponse(final String repsonse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行ui操作将结果显示到界面上
                showresponse.setText(repsonse);
            }
        });

    }


    public void gsonwithjson(String response) {

        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(response, LoginBean.class);
        Log.d("truename", loginBean.getResult().getTruename());
        Log.d("deptLevel", loginBean.getResult().getDeptLevel());

    }

    public void JSONOBJECT(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObject2 = jsonObject.getJSONObject("result");
            String deptLevel = jsonObject2.getString("deptLevel");
            String truename = jsonObject2.getString("truename");
            Log.d("truename", truename);
            Log.d("deptLevel", deptLevel);

        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

}
