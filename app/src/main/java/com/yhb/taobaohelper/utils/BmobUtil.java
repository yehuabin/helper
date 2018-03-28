package com.yhb.taobaohelper.utils;

import android.util.Log;

import com.yhb.taobaohelper.TokenHelper;
import com.yhb.taobaohelper.model.CookieModel;
import com.yhb.taobaohelper.model.LogModel;
import com.yhb.taobaohelper.model.ProductExtraModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
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

    public static void sendSms(){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = format.format(new Date());
        BmobSMS.requestSMS("13695700114", "淘宝登录失效", sendTime, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//
                    Log.i("bmob","短信发送成功，短信id："+integer);//用于查询本次短信发送详情
                }else{
                    Log.i("bmob","errorCode = "+ex.getErrorCode()+",errorMsg = "+ex.getLocalizedMessage());
                }
            }
        });
    }

    public static void updateProductLink(ProductExtraModel productExtraModel) {
        productExtraModel.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e!=null){
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    public static void saveLog(LogModel logModel){
        if (logModel==null){
            return;
        }
        logModel.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e!=null){
                    Log.d("bmob", e.getMessage());
                }
            }
        });
    }
}


