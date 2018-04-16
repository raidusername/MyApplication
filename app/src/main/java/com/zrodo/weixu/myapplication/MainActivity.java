package com.zrodo.weixu.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_TAKE_PHOTO_CODE = 1;
    public static final int REQUST_TAKE_PHOTTO_CODE2 = 2;
    private Button btnone;
    private ListView list_item;
    private String photoPath;
    private ImageView imageView;
    public final static String SAVED_IMAGE_PATH1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pic";//+"/pic";
    // /storage/emulated/0/Pictures
    public final static String SAVED_IMAGE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();//.getAbsolutePath()+"/pic";//+"/pic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击第一次", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymain, menu);
        return true;
    }

    /*
    * 初始化
    * **/
    public void init() {
        btnone = (Button) findViewById(R.id.btnone);
        list_item = (ListView) findViewById(R.id.list_item);
        imageView = (ImageView) findViewById(R.id.img);
    }

    /*
        * 接收调用相机后返回的数据
        * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            File photoFile = new File(photoPath);
            if (photoFile.exists()) {
                //通过图片地址将图片加载到bitmap里面
                Bitmap bm = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                //将拍摄的照片显示到界面上
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bm);
            } else {
                Toast.makeText(MainActivity.this, "图片文件不存在", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUST_TAKE_PHOTTO_CODE2 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bm = (Bitmap) bundle.get("data");
                if (bm != null) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bm);
                }
            } else {
                Toast.makeText(MainActivity.this, "没有压缩的图片数据", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.additem:
                Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                //显示跳转
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

                break;
            case R.id.removeitem:
                //获取SD卡安装状态
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {

                    //设置图片保存路径
                    photoPath = SAVED_IMAGE_PATH + "/" + System.currentTimeMillis() + ".png";
                    Log.i("photo", photoPath);

                    File imageDir = new File(photoPath);
                    if (!imageDir.exists()) {
                        try {
                            //根据一个 文件地址生成一个新的文件用来存照片
                            imageDir.createNewFile();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_LONG).show();
                    //隐式跳转
                    Intent intent1 = new Intent();
                    intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    //根据路径实例化图片文件
                    File photoFile = new File(photoPath);
                    //设置拍照后图片保存到文件中
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    //启动拍照activity并获取返回数据
                    startActivityForResult(intent1, REQUEST_TAKE_PHOTO_CODE);
                    //finish();
                } else {
                    Toast.makeText(MainActivity.this, "SD卡未插入", Toast.LENGTH_SHORT).show();
                }


                break;

        }
        return true;
    }
}
