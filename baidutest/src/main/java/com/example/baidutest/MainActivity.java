package com.example.baidutest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public TextView text_position;
    public LocationClient mlocationClient;
    private MapView baidumap;
    private BaiduMap baiduMapview;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.registerLocationListener(new MyLocaltion());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        baidumap = (MapView) findViewById(R.id.baidumap);
        text_position = (TextView) findViewById(R.id.test_position);
        baiduMapview = baidumap.getMap();
        baiduMapview.setMyLocationEnabled(true);
        List<String> permmissionlist = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permmissionlist.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permmissionlist.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permmissionlist.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permmissionlist.isEmpty()) {
            String[] permission = permmissionlist.toArray(new String[permmissionlist.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permission, 1);

        } else {
            requestLocaltion();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        baidumap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baidumap.onPause();
    }

    private void requestLocaltion() {
        initlocaltion();
        mlocationClient.start();
    }

    private void navigateTo(BDLocation location) {
        if (isFirst) {
            LatLng l = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(l);
            baiduMapview.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMapview.animateMapStatus(update);
            isFirst = false;
        }
        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData locationData=builder.build();
        baiduMapview.setMyLocationData(locationData);
    }

    public void initlocaltion() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);


        option.setScanSpan(1000);
        //根据定位显示中文位置，必须开启
        option.setIsNeedAddress(true);


        mlocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stop();
        baidumap.onDestroy();
        baiduMapview.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocaltion();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocaltion implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //定位当前位置
            if (bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }


            StringBuffer cPosition = new StringBuffer();
            cPosition.append("纬度：").append(bdLocation.getAltitude()).append("\n");
            cPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
            cPosition.append("国家:").append(bdLocation.getCountry()).append("\n");
            cPosition.append("省:").append(bdLocation.getProvince()).append("\n");
            cPosition.append("市:").append(bdLocation.getCity()).append("\n");
            cPosition.append("区:").append(bdLocation.getDistrict()).append("\n");
            cPosition.append("街道:").append(bdLocation.getStreet()).append("\n");
            cPosition.append("定位方式:");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                cPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                cPosition.append("AGPS");
            } else {
                cPosition.append("未知");
            }
            text_position.setText(cPosition);
        }


    }
}