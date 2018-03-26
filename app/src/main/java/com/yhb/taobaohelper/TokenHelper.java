package com.yhb.taobaohelper;

import android.util.Log;

import com.google.gson.Gson;
import com.yhb.taobaohelper.callback.ProductCallback;
import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.utils.BmobUtil;
import com.yhb.taobaohelper.utils.MYHUtil;

import java.io.IOException;
import java.util.List;

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
    static OkHttpClient okHttpClient = getClient();
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
    public static void getProduct(String auctionId, final ProductCallback productCallback){


        Request request = new Request.Builder().url("http://pub.alimama.com/items/search.json?q=http://item.taobao.com/item.htm?id=" + auctionId).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                final List<ProductListModel.DataBean.PageListBean> datas = gson.fromJson(str, ProductListModel.class).getData().getPageList();
                ProductListModel.DataBean.PageListBean product=null;
                String err=null;

                if(datas!=null&&datas.size()>0){
                    product=datas.get(0);
                }

                productCallback.callback(product,err);
            }
        });
    }
    public static void isLogin(Callback callback){

        Request request = new Request.Builder().url("http://pub.alimama.com/common/getUnionPubContextInfo.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    static String adzoneid="173234312";
    static String siteid="41284538";
    public static void generateTaoToken(ProductListModel.DataBean.PageListBean pro,Callback callback){
         String url = String.format("/common/code/getAuctionCode.json?auctionid=%s&adzoneid=%s&siteid=%s&scenes=1", pro.getAuctionId(), adzoneid, siteid);
        float eventRate=0;
        if(pro.getEventRate()!=null){
            eventRate=Float.parseFloat(pro.getEventRate().toString());
        }

        if (eventRate > pro.getTkRate()) {
           url = String.format("/common/code/getAuctionCode.json?auctionid=%s&adzoneid=%s&siteid=%s&scenes=3&channel=tk_qqhd", pro.getAuctionId(), adzoneid, siteid);//高佣金
         }

        OkHttpClient okHttpClient = getClient();
        Request request = new Request.Builder().url("http://pub.alimama.com"+url).build();
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


    public static void saveCookie(String cookie,String token,String cookies){
        MYHUtil.saveCookie(cookie,token,cookies);
        BmobUtil.saveCookie(cookie);
    }
    public static void refreshCookie(){
        BmobUtil.refreshCookie();
    }
    public static String getLoginUrl(){
        return "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true";
    }
}
