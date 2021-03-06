package com.yhb.taobaohelper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yhb.taobaohelper.callback.ProductCallback;
import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.utils.MYHUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by smk on 2017/11/24.
 */

public class TokenHelper {
    private static final String TAG = "TokenHelper";
    private static String GLOABL_COOKIE = "";
    private static String GLOABL_COOKIES = "";
    public static String GLOABL_TOKEN = "";
    static OkHttpClient okHttpClient = getClient();

    public static void generateCoupon(String auctionId) {
        OkHttpClient okHttpClient = getClient();

        Request request = new Request.Builder().url("http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=" + auctionId + "&adzoneid=148716480&siteid=39748344&scenes=1").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                if (response.request().url().host().equals("pub.alimama.com")) {
                    Log.d(TAG, "onResponse: success");
                } else {
                    Log.d(TAG, "onResponse: fail");
                }
                Log.d(TAG, "onResponse: " + str);
            }
        });
    }

    public static void getProduct(String auctionId, final ProductCallback productCallback) {


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
                ProductListModel.DataBean.PageListBean product = null;
                String err = null;

                if (datas != null && datas.size() > 0) {
                    product = datas.get(0);
                    applyCamp(product);
                }

                productCallback.callback(product, err);
            }
        });
    }

    public static void isLogin(Callback callback) {

        Request request = new Request.Builder().url("http://pub.alimama.com/common/getUnionPubContextInfo.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    static String adzoneid = "173234312";
    static String siteid = "41284538";

    public static void generateTaoToken(ProductListModel.DataBean.PageListBean pro, Callback callback) {
        String url = String.format("/common/code/getAuctionCode.json?auctionid=%s&adzoneid=%s&siteid=%s&scenes=1", pro.getAuctionId(), adzoneid, siteid);
        float eventRate = 0;
        if (pro.getEventRate() != null) {
            eventRate = Float.parseFloat(pro.getEventRate().toString());
        }

        if (eventRate > pro.getTkRate()) {
            url = String.format("/common/code/getAuctionCode.json?auctionid=%s&adzoneid=%s&siteid=%s&scenes=3&channel=tk_qqhd", pro.getAuctionId(), adzoneid, siteid);//高佣金
        }

        OkHttpClient okHttpClient = getClient();
        Request request = new Request.Builder().url("http://pub.alimama.com" + url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void applyCamp(final ProductListModel.DataBean.PageListBean product) {
       final Gson gson = new Gson();
        if (product.getTkSpecialCampaignIdRateMap() != null) {
            JsonObject obj =gson.fromJson( product.getTkSpecialCampaignIdRateMap().toString(),JsonObject.class);
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry : entries) {
                float rate = entry.getValue().getAsFloat();
                if (rate > product.getRate()) {
                    //定向计划较高,获取定向详细

                    final OkHttpClient okHttpClient = getClient();
                    Request request = new Request.Builder().url("http://pub.alimama.com/pubauc/getCommonCampaignByItemId.json?itemId=" + product.getAuctionId()).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String body = response.body().string();
                            if (body.indexOf("阿里妈妈") > -1) {
                                return;
                            }

                            JsonObject obj=gson.fromJson(body,JsonObject.class);
                            JsonElement data= obj.get("data");
                            JsonObject maxRateItem=null;
                            float commissionRate=product.getRate();
                            if(data!=null){
                               JsonArray array= data.getAsJsonArray();
                                for (int i=0;i<array.size();i++){
                                   JsonObject jihua= array.get(i).getAsJsonObject();
                                   float jihuaRate= jihua.get("commissionRate").getAsFloat();
                                    if (jihuaRate > commissionRate &&  jihua.get("manualAudit").getAsInt() == 0) {
                                        commissionRate = jihuaRate;
                                        maxRateItem = jihua;
                                    }
                                }

                                if(maxRateItem!=null&&!maxRateItem.get("Exist").getAsBoolean()){
                                    RequestBody requestBodyPost = new FormBody.Builder()
                                            .add("campId", maxRateItem.get("CampaignID").getAsString())
                                            .add("applyreason", "职业淘客申请长期合作,qq:253922578")
                                            .add("_tb_token_", GLOABL_TOKEN)
                                            .add("keeperid",maxRateItem.get("ShopKeeperID").getAsString())
                                            .build();
                                    Request request = new Request.Builder().url("http://pub.alimama.com//pubauc/applyForCommonCampaign.json").post(requestBodyPost).build();
                                    okHttpClient.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            Log.d(TAG, "申请定向: "+response.body().string());
                                        }
                                    });
                                }
                            }
                        }
                    });

                    break;
                }

            }
        }
    }


    private static OkHttpClient getClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3427.400 QQBrowser/9.6.12513.400")
                                .addHeader("Cookie", "cookie2=" + GLOABL_COOKIES)
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();
        return okHttpClient;
    }

    public static String getCookie() {
        return GLOABL_COOKIE;
    }

    public static void setCookie(String val) {
        GLOABL_COOKIE = val;
    }


    public static void saveCookie(String cookie, String token, String cookies) {
        MYHUtil.saveCookie(cookie, token, cookies);
        GLOABL_COOKIE=cookie;
        GLOABL_TOKEN=token;
        GLOABL_COOKIES=cookies;
       // BmobUtil.saveCookie(cookie);
    }

    public static void refreshCookie() {
        Request request = new Request.Builder().url("http://"+MYHUtil.host+"/adminApi/getCookie").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                JsonObject jsonObject=gson.fromJson(response.body().string(),JsonObject.class);
                GLOABL_COOKIE=jsonObject.get("cookie").getAsString();
                GLOABL_TOKEN=jsonObject.get("token").getAsString();
                GLOABL_COOKIES=jsonObject.get("cookies").getAsString();
            }
        });


    }

    public static String getLoginUrl() {
        return "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http://pub.alimama.com/common/code/getAuctionCode.json?auctionid=556095016857&adzoneid=148716480&siteid=39748344&scenes=1&full_redirect=true&disableQuickLogin=true";
    }
}
