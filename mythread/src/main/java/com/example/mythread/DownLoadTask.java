package com.example.mythread;

import android.os.AsyncTask;

public class DownLoadTask extends AsyncTask<Void,Integer,Boolean>{


    /**
     *会在后台任务开始执行之前调用，用于进行一些界面的初始化操作
     * */
    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


/**
 * 该方法中的所有代码都会在子线程中运行，这边处理耗时任务，一旦完成可以通过return语句来将任务的执行结果返回
 *如果AsyncTask第三个泛型指定Void，将可以不返回任何结果值，
 * 注意：这个方法不可以镜像UI 操作，如果需要更新UI，可以调用publicProgress(Progress..)方法完成
 * */
    @Override
    protected Boolean doInBackground(Void... voids) {

        return null;
    }


}
