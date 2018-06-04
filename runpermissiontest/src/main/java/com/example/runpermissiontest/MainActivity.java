package com.example.runpermissiontest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button call, getCall;
    private ListView content_view;
    private ArrayAdapter<String> adapter;
    private List<String>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = (Button) findViewById(R.id.btn_call);
        getCall = (Button) findViewById(R.id.get_content);

        list=new ArrayList<String>();
        content_view=(ListView) findViewById(R.id.content_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        content_view.setAdapter(adapter);

        call.setOnClickListener(this);
        getCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    Toast.makeText(this,"getContacts1",Toast.LENGTH_LONG).show();
                } else {
                    call();
                    Toast.makeText(this,"getContacts3",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.get_content:

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 2);
                    Toast.makeText(this,"getContacts1",Toast.LENGTH_LONG).show();
                } else {
                    readcontent();
                    Toast.makeText(this,"getContacts2",Toast.LENGTH_LONG).show();
                }

                break;
        }

    }

    public void readcontent() {
        Cursor cursor = null;
        list.clear();
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    String displayname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phonenum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(displayname + "\n" + phonenum);
                }

                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    public void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(MainActivity.this, "you denied the permisson", Toast.LENGTH_LONG).show();
                }
                break;
              case 2:
                  if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     readcontent();
                  } else {
                      Toast.makeText(MainActivity.this, "you denied the permisson", Toast.LENGTH_LONG).show();
                  }
                break;
        }
    }
}
