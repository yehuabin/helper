package com.yhb.taobaohelper.utils;

import com.google.gson.Gson;
import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.model.ProductModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.yhb.taobaohelper.TokenHelper.getCookie;

/**
 * Created by smk on 2017/11/24.
 */

public class TaoBaoHelper {
    private static final String TAG = "TaoBaoHelper";

    private static String searchCookie = "uvXpEdq45TUCAXrkmWIyIO3q";



    //private static  String searchCookie = "frKiEpBaJFECAT2kgLpjUqMb";
    public static void generateCoupon(String auctionId,Callback callback){
        OkHttpClient okHttpClient = getClient("cookie2",getCookie());

        Request request = new Request.Builder().url("http://pub.alimama.com/common/code/getAuctionCode.json?auctionid="+auctionId+"&adzoneid=148716480&siteid=39748344&scenes=1").build();

        okHttpClient.newCall(request).enqueue(callback);
    }


    public static void search(String url, Callback callback) {
        OkHttpClient okHttpClient = getClient("cna", searchCookie);

        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(callback);
    }



    private static OkHttpClient getClient(final String cookieKey, final String cookieVal) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        final Request authorized = original.newBuilder()
                                .addHeader("Cookie", cookieKey + "=" + cookieVal + ";cookie2=" + getCookie())
                                .build();

                        return chain.proceed(authorized);
                    }
                })
                .build();
        return okHttpClient;
    }

    public static void search(final String keyword, final SearchCallback searchCallback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://pub.alimama.com/items/search.json?toPage=1&perPageSize=20&q="+keyword).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String json = response.body().string();

                    Gson gson = new Gson();
                    final List<ProductListModel.DataBean.PageListBean> datas = gson.fromJson(json, ProductListModel.class).getData().getPageList();
                    List<ProductModel> result=new ArrayList<ProductModel>();
                    if (datas==null||datas.size()==0){
                        searchCallback.response(result,true);
                        return;
                    }
                    for (int i=0;i<datas.size();i++){
                        result.add(ModelUtil.getProductModel(datas.get(i),"tuijian",0));
                    }
                    searchCallback.response(result,true);
                }
                catch (Exception e){
                    searchCallback.response(null,false);
                }

            }
        });
    }


    public static void searchNvZhuang( final SearchCallback searchCallback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://pub.alimama.com/items/search.json?toPage=1&queryType=2&auctionTag=&perPageSize=50").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String json = response.body().string();

                    Gson gson = new Gson();
                    final List<ProductListModel.DataBean.PageListBean> datas = gson.fromJson(json, ProductListModel.class).getData().getPageList();
                    List<ProductModel> result=new ArrayList<ProductModel>();
                    if (datas==null||datas.size()==0){
                        searchCallback.response(result,true);
                        return;
                    }
                    for (int i=0;i<datas.size();i++){
                        result.add(ModelUtil.getProductModel(datas.get(i),"nzjh",0));
                    }
                    searchCallback.response(result,true);
                }
                catch (Exception e){
                    searchCallback.response(null,false);
                }

            }
        });
    }
}
