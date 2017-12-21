package com.yhb.taobaohelper.utils;

import android.util.Log;

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
    public static String host="m.5imyh.com";
    public static void addRepertory(String repertorys,String category,Number sortType){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("json", repertorys)
                .add("category", category)
                .add("sortType", String.valueOf(sortType))

                .build();
        Request request = new Request.Builder().url("http://"+host+"/repertoryApi/repertory").post(requestBodyPost).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String json = response.body().string();
                    Log.d(TAG, "onResponse: "+json);

                }
                catch (Exception e){

                }

            }
        });
    }
}
