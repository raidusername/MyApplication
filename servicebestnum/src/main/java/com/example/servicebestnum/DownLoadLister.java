package com.example.servicebestnum;

public interface DownLoadLister {
    void onProgress(int progress);//通知下载进度
    void onSuccess();//成功
    void onFiled();//失败
    void onPaused();//暂停
    void onCanceled();//取消
}
