package com.example.broadcasttest.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"my Broadcast",Toast.LENGTH_LONG).show();
        abortBroadcast();//有序广播-截断广播
    }
}
