package com.yhb.taobaohelper.utils;

import com.yhb.taobaohelper.model.ProductListModel;
import com.yhb.taobaohelper.model.ProductModel;

/**
 * Created by smk on 2017/11/28.
 */

public class ModelUtil {
    public static ProductModel getProductModel(ProductListModel.DataBean.PageListBean bean,
                                               String category, int sortType){
        ProductModel model=new ProductModel();
        model.setTitle(bean.getTitle());
        model.setAuctionId(bean.getAuctionId());
        model.setAuctionUrl(bean.getAuctionUrl());
        model.setBiz30day(bean.getBiz30day());
        model.setCouponAmount(bean.getCouponAmount());
        model.setCouponLeftCount(bean.getCouponLeftCount());
        model.setCouponStartFee(bean.getCouponStartFee());
        model.setCouponTotalCount(bean.getCouponTotalCount());
        model.setPictUrl(bean.getPictUrl());
        model.setRlRate(bean.getRlRate());
        model.setShopTitle(bean.getShopTitle());
        model.setTkRate(bean.getTkRate());
        model.setUserType(bean.getUserType());
        model.setZkPrice(bean.getZkPrice());
        model.setCategory(category);
        model.setSortType(sortType);
        return model;
    }
}
