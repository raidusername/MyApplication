package com.example.savedate;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HandleDate {
    String str = "Save To Date";


    /***
     * 保存数据到SaveFiledate文件夹下
     */

    public static void save(Context context,String inputdate) {
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;

        try {
            outputStream = context.openFileOutput("SaveFiledate", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(inputdate);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /***
     * 提取文件中的数据
     */
    public static String loaddate(Context context,String filename){
        FileInputStream inputStream=null;
        BufferedReader reader=null;
        StringBuffer buffer= new StringBuffer();
        try {
          inputStream = context.openFileInput(filename);
          reader=new BufferedReader(new InputStreamReader(inputStream));
          String line="";
          while ((line=reader.readLine())!=null){
              buffer.append(line);
          }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
