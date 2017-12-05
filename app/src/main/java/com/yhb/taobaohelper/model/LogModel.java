package com.yhb.taobaohelper.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/4.
 */

public class LogModel extends BmobObject {
    public String module;
    public String action;
    public String creator;
    public String remark;
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
