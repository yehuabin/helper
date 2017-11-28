package com.yhb.taobaohelper.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.yhb.taobaohelper.model.ProductListModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
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

        Gson gson = new Gson();
        final List<ProductListModel.DataBean.PageListBean> datas = gson.fromJson(json, ProductListModel.class).getData().getPageList();
        List<BmobObject> list = new ArrayList<BmobObject>();
        for (int i = 0; i < 100; i++) {
            ProductListModel.DataBean.PageListBean bean = datas.get(i);

            list.add(ModelUtil.getProductModel(bean, category, sortType));
            if ((i + 1) % 50 == 0) {
                //第二种方式：v3.5.0开始提供
                new BmobBatch().insertBatch(list).doBatch(new QueryListListener<BatchResult>() {

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
                list.clear();
            }

        }

    }
}
