package com.yhb.taobaohelper.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yhb.taobaohelper.TokenHelper;
import com.yhb.taobaohelper.callback.ProductCallback;
import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.model.UserTaoTokenModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by smk on 2017/12/21.
 */

public class MYHUtil {
    private static final String TAG = "MYHUtil";
   public static String host = "m.5imyh.com";
   // public static String host = "17.178.217.26";
    static OkHttpClient okHttpClient = new OkHttpClient();

    public static void saveRepertory(String repertorys, String category, Number sortType) {

        RequestBody requestBodyPost = new FormBody.Builder()
                .add("json", repertorys)
                .add("category", category)
                .add("sortType", String.valueOf(sortType))

                .build();
        Request request = new Request.Builder().url("http://" + host + "/adminApi/repertory").post(requestBodyPost).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static void saveCookie(String cookie, String token, String cookies) {

        RequestBody requestBodyPost = new FormBody.Builder()
                .add("cookie", cookie)
                .add("token", token)
                .add("cookies", cookies)
                .build();
        Request request = new Request.Builder().url("http://" + host + "/adminApi/cookie").post(requestBodyPost).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static void newCall(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    static boolean isHandling = false;

    public static void handleTaoToken() {


        Request request = new Request.Builder().url("http://" + host + "/adminApi/getTaoTokenRequest").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                isHandling = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                if(json.indexOf("502 Bad")>-1){

                    return;
                }
                final Gson gson = new Gson();
                UserTaoTokenModel[] datas = gson.fromJson(json, UserTaoTokenModel[].class);
                for (int i = 0; i < datas.length; i++) {
                    final UserTaoTokenModel item = datas[i];
                    TokenHelper.getProduct(item.getAuctionId(), new ProductCallback() {
                        @Override
                        public void callback(final ProductListModel.DataBean.PageListBean product, String err) {
                            if (err == null) {
                                if (product == null) {
                                    RequestBody requestBodyPost = new FormBody.Builder()
                                            .add("id", item.get_id())
                                            .add("ok", "1")
                                            .add("replyContent", "抱歉小主，该宝贝没有参加返现活动")
                                            .build();
                                    Request request = new Request.Builder().url("http://" + host + "/adminApi/updateTaoToken").post(requestBodyPost).build();
                                    newCall(request);

                                }
                                else {
                                    TokenHelper.generateTaoToken(product, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String body = response.body().string();
                                            if (body.indexOf("!doctype") > -1) {
                                                RequestBody requestBodyPost = new FormBody.Builder()
                                                        .add("id", item.get_id())
                                                        .add("ok", "0")
                                                        .add("title", product.getTitle())
                                                        .add("replyContent", "请求生成淘口令时，发现要重新登录淘宝")
                                                        .build();
                                                Request request = new Request.Builder().url("http://" + host + "/adminApi/updateTaoToken").post(requestBodyPost).build();
                                                newCall(request);
                                            } else {
                                                //调用接口更新内容
                                                JsonObject jsonBody = gson.fromJson(body, JsonObject.class);


                                                boolean ok = jsonBody.get("ok").getAsBoolean();
                                                if (ok) {
                                                    JsonObject dataJson = jsonBody.get("data").getAsJsonObject();
                                                    String taoToken = dataJson.get("taoToken").getAsString();
                                                    if (dataJson.get("couponLinkTaoToken") != null) {
                                                        taoToken = dataJson.get("couponLinkTaoToken").getAsString();
                                                    }


                                                    float price = product.getZkPrice() - product.getCouponAmount();
                                                    float reward=(price * product.getRate() / 100);

                                                    String content = "★" + product.getTitle() + "\n" +
                                                            "★券后价: " + String.format("%.2f", price) + "\n" +
                                                            "★预估奖励: " + String.format("%.2f", reward) + "\n" +
                                                            "★优惠券: " + String.format("%.2f", product.getCouponAmount()) + "\n" +
                                                            taoToken + "\n" +
                                                            "--------------------\n" +
                                                            "★复制信息，打开淘宝APP下单";
                                                    RequestBody requestBodyPost = new FormBody.Builder()
                                                            .add("id", item.get_id())
                                                            .add("ok", "1")
                                                            .add("title", product.getTitle())
                                                            .add("tkRate", String.format("%.2f", product.getRate()) )
                                                            .add("price",String.format("%.2f",price) )
                                                            .add("reward", String.format("%.2f", reward))
                                                            .add("couponAmount", String.format("%.2f", product.getCouponAmount()))
                                                            .add("taoToken", taoToken)
                                                            .add("replyContent", content)
                                                            .build();
                                                    Request request = new Request.Builder().url("http://" + host + "/adminApi/updateTaoToken").post(requestBodyPost).build();
                                                    newCall(request);
                                                }


                                            }
                                        }
                                    });
                                }
                            }

                        }
                    });


                }

            }
        });
    }
}
