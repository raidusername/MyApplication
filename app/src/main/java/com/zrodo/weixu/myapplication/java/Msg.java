package com.zrodo.weixu.myapplication.java;

/**
 * Created by td on 2018/4/26.
 */

public class Msg {
    public static final int TYPE_receive=0;
    public static final int TYPE_send=1;
    private String msgtext;
    private int type;

    public Msg(String msgtext, int type) {
        this.msgtext = msgtext;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getMsgtext() {
        return msgtext;
    }
}
