package com.zrodo.weixu.myapplication.java;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by td on 2018/4/19.
 * 缩略图
 */

public class Bitmapphoto {

    public static  Bitmap bitmap(File FilePhote){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize = 16 ;
        Bitmap bm = BitmapFactory.decodeFile(FilePhote.getAbsolutePath(),options);
        return bm;

    }
}
