package com.example.broadcasttest.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeRecriver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            Toast.makeText(context,"网络可获取",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"网络不可获取",Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(context,"networkchange",Toast.LENGTH_LONG).show();

    }
}
