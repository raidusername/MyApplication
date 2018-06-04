package com.example.servicebestnum;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

public class MyDownLoadService extends Service {
    private DownTask downTask;
    private String Downloadurl;
    private DownLoadLister downLoadLister = new DownLoadLister() {
        @Override
        public void onProgress(int progress) {
            //用来显示进度
            getnotificationManager().notify(1, getnotification("Downloading...", progress, null, false));
        }

        @Override
        public void onSuccess() {
            downTask = null;
            //下载成功通知前台服务关闭，并创建一个下载成功的通知,点击成功的通知安装apk
            stopForeground(true);
            //apk 的路径
            String fileName = Downloadurl.substring(Downloadurl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            File file = new File(directory + fileName);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //设置intent的数据类型是应用程序application
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            //为这个新apk开启一个新的activity栈
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //开始安装
           //startActivity(intent);
            //关闭旧版本的应用程序的进程
           // android.os.Process.killProcess(android.os.Process.myPid());
           PendingIntent pi = PendingIntent.getActivity(MyDownLoadService.this, 0, intent, 0);
            //file.delete();



           getnotificationManager().notify(1, getnotification("下载成功,点击安装", -1, pi, true));
            Toast.makeText(MyDownLoadService.this, "DownLoad Successful", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onFiled() {
            downTask = null;
            //下载成功通知前台服务关闭，并创建一个下载失败的通知
            stopForeground(true);
            Intent intent = new Intent(MyDownLoadService.this,MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(MyDownLoadService.this, 1, intent, 0);
            getnotificationManager().notify(1, getnotification("下载失败，请重新下载", -1, pi, true));
            Toast.makeText(MyDownLoadService.this, "DownLoad Failed", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPaused() {
            downTask = null;

            Toast.makeText(MyDownLoadService.this, "DownLoad Paused", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCanceled() {
            downTask = null;
            stopForeground(true);
            Toast.makeText(MyDownLoadService.this, "DownLoad Canceled", Toast.LENGTH_LONG).show();

        }
    };

    public MyDownLoadService() {
    }

    private DownLoadBind downLoadBind = new DownLoadBind();

    @Override
    public IBinder onBind(Intent intent) {
        return downLoadBind;
    }

    private NotificationManager getnotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getnotification(String title, int progress, @Nullable PendingIntent pi, boolean b) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentIntent(pi)
                .setAutoCancel(b);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }

        return builder.build();
    }

    class DownLoadBind extends Binder {
        public void startDownLoad(String url) {
            if (downTask == null) {
                Downloadurl = url;
                downTask = new DownTask(downLoadLister);
                downTask.execute(Downloadurl);
                startForeground(1, getnotification("DownLoad...", 0, null, false));
                Toast.makeText(MyDownLoadService.this, "DownLoad。。。", Toast.LENGTH_LONG).show();


            }

        }

        public void pauseDownload() {
            if (downTask != null) {
                downTask.pausedown();
            }
        }

        public void canceledDownLoad() {
            if (downTask != null) {
                downTask.canceleddown();
            } else {
                if (Downloadurl != null) {
                    //取消下载是需要文件删除
                    String fileName = Downloadurl.substring(Downloadurl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getnotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(MyDownLoadService.this, "DownLoad canceled", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
