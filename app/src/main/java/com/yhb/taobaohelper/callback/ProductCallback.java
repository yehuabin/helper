package com.yhb.taobaohelper.callback;

import com.yhb.taobaohelper.model.ProductListModel;

/**
 * Created by smk on 2018/3/26.
 */

public interface ProductCallback {
    void callback(ProductListModel.DataBean.PageListBean product, String err);
}
