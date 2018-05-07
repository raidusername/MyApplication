package com.example.broadcastdownline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.broadcastdownline.java.ActivityCollector;

public class BroadcastDownline extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d("offline", "11111");

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        Log.d("offline", "11222111");
        builder.setTitle("WARING");
        builder.setMessage("YOU ARE FORCED TO BE OFFLINE,PLEASE TRY TO LOGIN AGANIN.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAllActivity();
                Intent intent1=new Intent(context,LoginActivity.class);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
