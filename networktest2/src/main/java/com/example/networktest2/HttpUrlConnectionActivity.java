package com.example.networktest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionActivity extends AppCompatActivity implements View.OnClickListener{
private Button  button;
private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.request);
        textView=(TextView) findViewById(R.id.response);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.request:
                sendRequest();


                break;
        }
    }
    public void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection=null;
                BufferedReader bufferedReader=null;
                try{
                    URL url=new URL("http://www.zrodo.com");
                    urlConnection=(HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream inputStream=urlConnection.getInputStream();
                    //对获取的流进行操作
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer response=new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine())!=null){
                        response.append(line);

                    }
                    showresponse(response.toString());


                }catch (Exception e){
                   e.printStackTrace();
                }finally {
                    if(bufferedReader!=null){
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(urlConnection!=null){
                        urlConnection.disconnect();

                    }
                }


            }
        }).start();
    }

    public void showresponse(final String repsonse){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行ui操作将结果显示到界面上
                textView.setText(repsonse);
            }
        });

    }
}
