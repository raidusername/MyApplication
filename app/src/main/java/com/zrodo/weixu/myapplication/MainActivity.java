package com.zrodo.weixu.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zrodo.weixu.myapplication.java.ActivityCollector;
import com.zrodo.weixu.myapplication.java.BaseAtivity;
import com.zrodo.weixu.myapplication.java.Bitmapphoto;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseAtivity {
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_TAKE_PHOTO_CODE
            );
        }
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_LONG).show();
                ActivityCollector.finishAllActivity();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymain, menu);
        return true;
    }

    /*
    * 初始化绑定控件
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
                //缩略图
                Bitmap bm = Bitmapphoto.bitmap(photoFile);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission", "获取成功");
            } else {
                Log.d("permission", "权限悲剧");
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.additem:
                Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                /**
                 *   //显示跳转
                 Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                 intent.putExtra("data", "这是一个跳转贷数据的测试");
                 startActivity(intent);

                 * */
                Main2Activity.Actiononstart(MainActivity.this, "跳转过来显示listview");

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
                    // Intent intent = new Intent(Intent.ACTION_DIAL);
                    //intent.setData(Uri.parse("tel:10086"));

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
