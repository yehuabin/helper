package com.yhb.taobaohelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yhb.taobaohelper.model.ProductModel;
import com.yhb.taobaohelper.utils.MYHUtil;
import com.yhb.taobaohelper.utils.SearchCallback;
import com.yhb.taobaohelper.utils.TaoBaoHelper;
import com.yhb.taobaohelper.utils.UrlUtil;
import com.yhb.taobaohelper.utils.insertCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    Spinner spinner;
    TextView tv_btnclick;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_btnclick = mViewHolder.get(R.id.tv_btnclick);

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
        mViewHolder.setText(MYHUtil.host, R.id.et_myhUrl);
        //适配器
        ArrayAdapter<String> arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner = mViewHolder.get(R.id.spinner);
        spinner.setAdapter(arr_adapter);

        final Button button = (Button) findViewById(R.id.button);
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
        mViewHolder.get(R.id.btn_addTuiJian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TuijianActivity.class);
            }
        });
        final EditText et_myhUrl=mViewHolder.get(R.id.et_myhUrl);
        mViewHolder.get(R.id.btn_searchNZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MYHUtil.host=et_myhUrl.getText().toString();
               TaoBaoHelper.searchNvZhuang(new SearchCallback() {
                   @Override
                   public void response(final List<ProductModel> datas, boolean isOK) {
                       if (isOK){
                           BmobQuery<ProductModel> query = new BmobQuery<ProductModel>();
                           query.addWhereEqualTo("category", "nzjh");
                           query.setLimit(500);
                           //执行查询方法
                           query.findObjects(new FindListener<ProductModel>() {
                               @Override
                               public void done(List<ProductModel> list, BmobException e) {

                                   if (e == null) {
                                       List<BmobObject> insertData = new ArrayList<>();
                                       for (int i = datas.size()-1; i >=0; i--) {
                                           ProductModel model = datas.get(i);

                                           //佣金大于2块钱的商品才添加
                                           if (!list.contains(model)&&(model.getZkPrice()*model.getTkRate()/100)>2) {
                                               insertData.add(model);
                                           }
                                       }

                                       Log.i("bmob", "可添加：" + insertData.size());
                                       if (insertData.size() > 0) {
                                           new BmobBatch().insertBatch(insertData).doBatch(new QueryListListener<BatchResult>() {

                                               @Override
                                               public void done(List<BatchResult> o, BmobException e) {
                                                   if (e == null) {

                                                       for (int i = 0; i < o.size(); i++) {
                                                           BatchResult result = o.get(i);
                                                           BmobException ex = result.getError();
                                                           if (ex == null) {

                                                               // Log.d("bmob", "第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                                                           } else {
                                                               Log.d("bmob", "第" + i + "个数据批量添加失败：" + ex.getMessage() + "," + ex.getErrorCode());
                                                           }
                                                       }

                                                   } else {
                                                       Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                                   }
                                               }
                                           });
                                       }

                                   } else {

                                       Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                   }


                               }
                           });
                       }
                       toastLong("添加结束");
                   }
               });
            }
        });
        mViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_btnclick.setText(((Button)v).getText().toString());
                EditText et_sortType = mViewHolder.get(R.id.et_sortType);
                EditText et_page = mViewHolder.get(R.id.et_page);
                EditText et_xiaoliang = mViewHolder.get(R.id.et_xiaoliang);
                TextView tv_result = mViewHolder.get(R.id.tv_result);
                tv_result.setText("");
                int page = Integer.parseInt(et_page.getText().toString());
                String channel = spinner.getSelectedItem().toString();
                int sortType = Integer.parseInt(et_sortType.getText().toString());
                String query = "";
                if (sortType !=0) {
                    query = "startBiz30day="+et_xiaoliang.getText();//价格排序销量一万以上
                }

                switch (v.getId()) {
                    case R.id.btn_zonghe:
                        TaoBaoHelper.search(UrlUtil.getSearchUrl(channel, page, "", sortType, query), new insertCallBack(channel, sortType, MainActivity.this));

                        break;
                    case R.id.btn_tamll:
                        TaoBaoHelper.search(UrlUtil.getSearchUrl(channel, page, "b2c", sortType, "userType=1&" + query), new insertCallBack(channel, sortType, MainActivity.this));
                        break;
                    case R.id.btn_quan:
                        TaoBaoHelper.search(UrlUtil.getSearchUrl(channel, page, "dpyhq", sortType, "dpyhq=1&" + query), new insertCallBack(channel, sortType, MainActivity.this));
                        break;
                    case R.id.btn_quanTmall:
                        TaoBaoHelper.search(UrlUtil.getSearchUrl(channel, page, "b2c,dpyhq", sortType, "userType=1&dpyhq=1&" + query), new insertCallBack(channel, sortType, MainActivity.this));
                        break;
                    default:
                        break;
                }
            }
        }, R.id.btn_zonghe, R.id.btn_tamll, R.id.btn_quan, R.id.btn_quanTmall);

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
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String str = response.body().string();
                                    if (str.indexOf("yehuabin") > -1) {
                                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "登录失败" + TokenHelper.getCookie(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {

                                }
                            }
                        });
                    }
                });
            }
        });
    }


}
