package com.yhb.taobaohelper.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by smk on 2017/11/28.
 */

public class CookieModel extends BmobObject {
    private String cookie;
    private String remark;
    private String state;
    private boolean isSuccess=false;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
