package com.example.servicebestnum;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYRE_SUCCESS = 1;
    public static final int TYRE_FAILED = 2;
    public static final int TYRE_PAUSED = 3;
    public static final int TYRE_CANCELED = 4;
    private DownLoadLister downLoadLister;
    private boolean isPaused = false;
    private boolean isCanceled = false;
    private int lastProgress;

    public DownTask(DownLoadLister downLoadLister) {
        this.downLoadLister = downLoadLister;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            downLoadLister.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYRE_SUCCESS:
                downLoadLister.onSuccess();
                break;
            case TYRE_FAILED:
                downLoadLister.onFiled();
                break;
            case TYRE_PAUSED:
                downLoadLister.onPaused();
                break;
            case TYRE_CANCELED:
                downLoadLister.onCanceled();
                break;
        }
    }

    public void pausedown() {
        isPaused = true;
    }

    public void canceleddown() {
        isCanceled = true;
    }

    @Override
    protected Integer doInBackground(String... strings) {

        InputStream is = null;
        RandomAccessFile savedfile = null;
        File file = null;
        try {
            long downloadFiledLength = 0;//记录下载文件的长度
            String downloadUrl = strings[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            Log.d("address", directory+fileName);
            if (file.exists()) {
                downloadFiledLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYRE_FAILED;
            } else if (contentLength == downloadFiledLength) {
//已下载字符数和文件字符数相等  下载完成
                return TYRE_SUCCESS;

            }
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，指定重那个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadFiledLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedfile = new RandomAccessFile(file, "rw");
                savedfile.seek(downloadFiledLength);//跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYRE_CANCELED;
                    } else if (isPaused) {
                        return TYRE_PAUSED;
                    } else {
                        total += len;
                        savedfile.write(b, 0, len);
                        //计算已经下载的百分比
                        int proress = (int) ((total + downloadFiledLength) * 100 / contentLength);
                        publishProgress(proress);
                    }
                }
                response.body().close();
                return TYRE_SUCCESS;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedfile != null) {
                    savedfile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return TYRE_FAILED;
    }

    private long getContentLength(String DownloadUrl) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DownloadUrl)
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            Long contentlength = response.body().contentLength();
            response.close();
            return contentlength;

        }
        return 0;

    }
}
