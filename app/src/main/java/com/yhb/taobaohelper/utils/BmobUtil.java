package com.yhb.taobaohelper.utils;

import android.util.Log;

import com.yhb.taobaohelper.TokenHelper;
import com.yhb.taobaohelper.model.CookieModel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by smk on 2017/11/28.
 */

public class BmobUtil {

    public static void saveCookie(final String val) {
        refreshCookie(new MyCallBack() {
            @Override
            public void execute(String cookie, String objectId) {
                CookieModel cookieModel = new CookieModel();
                cookieModel.setCookie(val);
                cookieModel.setSuccess(true);
                cookieModel.setObjectId(objectId);
                cookieModel.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            TokenHelper.setCookie(val);
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
    }

    public static void refreshCookie() {
        refreshCookie(new MyCallBack() {
            @Override
            public void execute(String cookie, String objectId) {
                TokenHelper.setCookie(cookie);
            }
        });
    }

    private static void refreshCookie(final MyCallBack callBack) {
        BmobQuery<CookieModel> bmobQuery = new BmobQuery<CookieModel>();
        bmobQuery.addWhereEqualTo("type", "token");
        bmobQuery.setLimit(1);
        bmobQuery.findObjects(new FindListener<CookieModel>() {
            @Override
            public void done(List<CookieModel> list, BmobException e) {
                if (e == null) {
                    if (callBack != null) {
                        callBack.execute(list.get(0).getCookie(), list.get(0).getObjectId());
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });


    }

    public interface MyCallBack {
        void execute(String cookie, String objectId);
    }
}


