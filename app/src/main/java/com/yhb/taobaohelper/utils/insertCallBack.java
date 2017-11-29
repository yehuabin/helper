package com.yhb.taobaohelper.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.model.ProductModel;

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

/**
 * Created by smk on 2017/11/28.
 */
class insertCallBack implements Callback {
    String category;
    int sortType;

    public insertCallBack(String category, int sortType) {
        this.category = category;
        this.sortType = sortType;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String json = response.body().string();
        if (json.indexOf("http://pub.alimama.com/__x5__/query.htm") > 0) {
            Log.d("bmob", "访问被拒绝了: " + "category:" + category + " sortype:" + sortType);
            return;
        }
        Gson gson = new Gson();
        final List<ProductListModel.DataBean.PageListBean> datas = gson.fromJson(json, ProductListModel.class).getData().getPageList();
        List<BmobObject> list = new ArrayList<BmobObject>();
        BmobQuery<ProductModel> query = new BmobQuery<ProductModel>();
        query.addWhereEqualTo("category", category);
        query.setLimit(500);
        //执行查询方法
        query.findObjects(new FindListener<ProductModel>() {
            @Override
            public void done(List<ProductModel> list, BmobException e) {
                if (e == null) {
                    List<BmobObject> insertData = new ArrayList<>();
                    for (int i = 0; i < datas.size(); i++) {
                        ProductListModel.DataBean.PageListBean bean = datas.get(i);

                        ProductModel model = (ModelUtil.getProductModel(bean, category, sortType));
                        if (!list.contains(model)) {
                            insertData.add(model);
                        }
                    }
                    Log.i("bmob", "可添加："+insertData.size());
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
}
