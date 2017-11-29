package com.yhb.taobaohelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yhb.taobaohelper.utils.TaoBaoHelper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TokenHelper.refreshCookie();


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TokenService.class);
                startService(intent);
            }
        });

        mViewHolder.get(R.id.btn_cookie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(CookieActivity.class);
            }
        });
        mViewHolder.get(R.id.btn_refreshCookie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TokenHelper.refreshCookie();
            }
        });

        mViewHolder.get(R.id.btn_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] sortType=new int[]{1};//0,3,4,9,1
               // for (int i=0;i<sortType.length;i++){
                   Thread thread=new Thread(new Runnable() {
                       @Override
                       public void run() {
                           TaoBaoHelper.seachNanz(0);
                       }
                   });
                thread.start();
               // }
            }
        });

        mViewHolder.get(R.id.btn_testCookie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TokenHelper.isLogin(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call,final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String str=response.body().string();
                                    if (str.indexOf("yehuabin")>-1){
                                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "登录失败"+TokenHelper.getCookie(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e){

                                }
                            }
                        });
                    }
                });
            }
        });
    }


}
