package com.yhb.taobaohelper.utils;

import com.yhb.taobaohelper.model.ProductModel;

import java.util.List;

public interface SearchCallback {
    void response(List<ProductModel> data, boolean isOK);
}
