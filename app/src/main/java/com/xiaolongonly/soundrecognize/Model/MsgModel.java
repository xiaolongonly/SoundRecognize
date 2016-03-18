package com.xiaolongonly.soundrecognize.Model;

/**
 * Created by GuoXiaolong on 2016/3/18.
 */
public class MsgModel {
    private int state;//这个是用来标识是用户发送的还是返回的文本数据为了适配一个ListView做的蹩脚设计
    private String message;//转换过来的文本
    private float time;//声音文件时长
    private String filePathString; //声音文件路径

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFilePathString() {
        return filePathString;
    }

    public void setFilePathString(String filePathString) {
        this.filePathString = filePathString;
    }
}
