package com.zrodo.weixu.myapplication.java;

/**
 * Created by td on 2018/4/19.
 */

public class Beanadapter {
    private  int imgid;
    private String name;

    public Beanadapter(int imgid, String name) {
        this.imgid = imgid;
        this.name = name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
