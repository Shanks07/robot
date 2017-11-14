package com.qiyu.robot.Bean;

/**
 * Created by asus on 2017/11/13.
 */

public class User {

    private String id;//用户id

    private String name;//用户名

    private String info;//个性签名

    private String sex;//性别

    private int imgHead;//头像

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getImgHead() {
        return imgHead;
    }

    public void setImgHead(int imgHead) {
        this.imgHead = imgHead;
    }

}
