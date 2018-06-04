package com.example.servicebestnum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class InitApkBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {

            comm.rmoveFile("https://updatezrodo.oss-cn-beijing.aliyuncs.com/5app/4tsnmjq/tsncp_xm_v1.5_20171130.apk");
            Toast.makeText(context , "监听到系统广播添加" , Toast.LENGTH_LONG).show();
        }

        if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
            comm.rmoveFile("https://updatezrodo.oss-cn-beijing.aliyuncs.com/5app/4tsnmjq/tsncp_xm_v1.5_20171130.apk");
            Toast.makeText(context , "监听到系统广播移除" , Toast.LENGTH_LONG).show();
            System.out.println("");
        }

        if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            comm.rmoveFile("https://updatezrodo.oss-cn-beijing.aliyuncs.com/5app/4tsnmjq/tsncp_xm_v1.5_20171130.apk");
            Toast.makeText(context , "监听到系统广播替换" , Toast.LENGTH_LONG).show();
        }

    }
}
