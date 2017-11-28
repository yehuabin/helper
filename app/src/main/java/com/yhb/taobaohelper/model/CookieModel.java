package com.yhb.taobaohelper.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by smk on 2017/11/28.
 */

public class CookieModel extends BmobObject {
    private String cookie;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
