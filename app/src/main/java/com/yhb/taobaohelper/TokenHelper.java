package com.yhb.taobaohelper;

import android.util.Log;

import com.yhb.taobaohelper.utils.BmobUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by smk on 2017/11/24.
 */

public class TokenHelper {
    private static final String TAG = "TokenHelper";
    private static  String GLOABL_COOKIE = "";
    public static void generateCoupon(String auctionId){
        OkHttpClient okHttpClient = getClient();

        Request request = new Request.Builder().url("http://pub.alimama.com/common/code/getAuctionCode.json?auctionid="+auctionId+"&adzoneid=148716480&siteid=39748344&scenes=1").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                if (response.request().url().host().equals("pub.alimama.com")){
                    Log.d(TAG, "onResponse: success");
                }
                else {
                    Log.d(TAG, "onResponse: fail");
                }
                Log.d(TAG, "onResponse: "+str);
            }
        });
    }

    public static void isLogin(Callback callback){
        OkHttpClient okHttpClient = getClient();
        Request request = new Request.Builder().url("http://pub.alimama.com/common/getUnionPubContextInfo.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    private static OkHttpClient getClient(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", "cookie2="+GLOABL_COOKIE)
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();
        return okHttpClient;
    }

    public static String getCookie(){
       return GLOABL_COOKIE;
    }
    public static void setCookie(String val){
        GLOABL_COOKIE=val;
    }

    public static void saveCookie(String val){
        BmobUtil.saveCookie(val);
    }
    public static void refreshCookie(){
        BmobUtil.refreshCookie();
    }
    public static String getLoginUrl(){
        return "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true";
    }
}
