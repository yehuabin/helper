package com.yhb.taobaohelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yhb.taobaohelper.utils.TaoBaoHelper;
import com.yhb.taobaohelper.utils.UrlUtil;
import com.yhb.taobaohelper.utils.insertCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    Spinner spinner;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TokenHelper.refreshCookie();
       List<String> data_list = new ArrayList<String>();
        data_list.add("nzjh");
        data_list.add("nanz");
        data_list.add("muying");
        data_list.add("hch");
        data_list.add("jyj");
        data_list.add("qbb");
        data_list.add("kdc");
        data_list.add("ifs");
        data_list.add("9k9");
        data_list.add("20k");
        data_list.add("tehui");
        data_list.add("diy");

        //适配器
        ArrayAdapter<String> arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner=mViewHolder.get(R.id.spinner);
        spinner.setAdapter(arr_adapter);

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

        mViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_sortType=mViewHolder.get(R.id.et_sortType);
                EditText et_page=mViewHolder.get(R.id.et_page);
                TextView tv_result=mViewHolder.get(R.id.tv_result);
                tv_result.setText("");
                int page=Integer.parseInt(et_page.getText().toString());
                String channel= spinner.getSelectedItem().toString();
                int sortType=Integer.parseInt(et_sortType.getText().toString());
                String query = "";
                if (sortType == 3 || sortType == 4||sortType==1) {
                    query = "startBiz30day=10000";//价格排序销量一万以上
                }

               switch (v.getId()){
                   case R.id.btn_zonghe:
                       TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, page, "", sortType, query), new insertCallBack(channel, sortType,MainActivity.this));

                       break;
                   case R.id.btn_tamll:
                       TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, page, "", sortType, "userType=1&" + query), new insertCallBack(channel, sortType,MainActivity.this));
                       break;
                   case R.id.btn_quan:
                       TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel, page, "dpyhq", sortType, "dpyhq=1&" + query), new insertCallBack(channel, sortType,MainActivity.this));
                       break;
                   case R.id.btn_quanTmall:
                       TaoBaoHelper.seach(UrlUtil.getSearchUrl(channel,page, "dpyhq", sortType, "userType=1&dpyhq=1&" + query), new insertCallBack(channel, sortType,MainActivity.this));
                       break;
                   default:
                       break;
               }
            }
        }, R.id.btn_zonghe, R.id.btn_tamll, R.id.btn_quan, R.id.btn_quanTmall );


//        mViewHolder.get(R.id.btn_collect).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int[] sortType=new int[]{1};//0,3,4,9,1
//               // for (int i=0;i<sortType.length;i++){
//                   Thread thread=new Thread(new Runnable() {
//                       @Override
//                       public void run() {
//                           TaoBaoHelper.seachNZJH(0);
//                       }
//                   });
//                thread.start();
//               // }
//
//            }
//        });

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
