package com.yhb.taobaohelper.utils;

import android.util.Log;

import com.yhb.taobaohelper.TokenHelper;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by smk on 2017/11/24.
 */

public class TaoBaoHelper {
    private static final String TAG = "TaoBaoHelper";
    public static boolean IS_OK = true;
    private static String searchCookie = "uvXpEdq45TUCAXrkmWIyIO3q";

    public static void generateCoupon(String auctionId, Callback callback) {
        OkHttpClient okHttpClient = getClient("cookie2", TokenHelper.getCookie());

        Request request = new Request.Builder().url("http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=" + auctionId + "&adzoneid=148716480&siteid=39748344&scenes=1").build();

        okHttpClient.newCall(request).enqueue(callback);
    }


    public static void seach(String url, Callback callback) {
        OkHttpClient okHttpClient = getClient("cna", searchCookie);

        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(callback);
    }


    public static void seachNZJH(int sortType) {
        seachInternal("nzjh", sortType);
    }

    public static void seachMuYing(int sortType) {
        seachInternal("muying", sortType);
    }
    public static void seachNanz(int sortType) {
        seachInternal("nanz", sortType);
    }
    private static void seachInternal(final String channel, final int sortType) {

        try {
            String query = "";
            if (sortType == 3 || sortType == 4) {
                query = "startBiz30day=10000";//价格排序销量一万以上
            }
            int sleep=120000;
            //女装尖货 默认排序
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 1, "", sortType, query), new insertCallBack(channel, sortType));

            Thread.sleep(sleep);
            Log.d("bmob", "index:1");
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 2, "", sortType, query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:2");
            //天猫
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 1, "", sortType, "userType=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:3");
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 2, "", sortType, "userType=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:4");

            //店铺优惠券
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 1, "dpyhq", sortType, "dpyhq=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:5");
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 2, "dpyhq", sortType, "dpyhq=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:6");

            //天猫 店铺优惠券
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 1, "dpyhq", sortType, "userType=1&dpyhq=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:7");
            TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, 2, "dpyhq", sortType, "userType=1&dpyhq=1&" + query), new insertCallBack(channel, sortType));
            Thread.sleep(sleep);
            Log.d("bmob", "index:over");

        } catch (Exception e) {

        }


    }

    public static void isLogin(Callback callback) {
        OkHttpClient okHttpClient = getClient("cookie2", TokenHelper.getCookie());
        Request request = new Request.Builder().url("http://pub.alimama.com/common/getUnionPubContextInfo.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    private static OkHttpClient getClient(final String cookieKey, final String cookieVal) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", cookieKey + "=" + cookieVal + ";cookie2=" + TokenHelper.getCookie())
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();
        return okHttpClient;
    }


    public static String getLoginUrl() {
        return "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true";
    }
}
