package com.yhb.taobaohelper.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by smk on 2017/11/28.
 */

public class ProductModel extends BmobObject {


    private String userType;
    private String title;

    private String shopTitle;
    private String pictUrl;
    private String auctionId;
    private String biz30day;
    private float tkRate;
    private String tkCommFee;
    private String totalFee;
    private String totalNum;
    private float zkPrice;
    private String auctionUrl;
    private String rlRate;
    private String couponTotalCount;
    private String couponLeftCount;
    private float couponAmount;
    private float couponStartFee;
    private String category;

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    private int sortType;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getBiz30day() {
        return biz30day;
    }

    public void setBiz30day(String biz30day) {
        this.biz30day = biz30day;
    }

    public float getTkRate() {
        return tkRate;
    }

    public void setTkRate(float tkRate) {
        this.tkRate = tkRate;
    }

    public String getTkCommFee() {
        return tkCommFee;
    }

    public void setTkCommFee(String tkCommFee) {
        this.tkCommFee = tkCommFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public float getZkPrice() {
        return zkPrice;
    }

    public void setZkPrice(float zkPrice) {
        this.zkPrice = zkPrice;
    }

    public String getAuctionUrl() {
        return auctionUrl;
    }

    public void setAuctionUrl(String auctionUrl) {
        this.auctionUrl = auctionUrl;
    }

    public String getRlRate() {
        return rlRate;
    }

    public void setRlRate(String rlRate) {
        this.rlRate = rlRate;
    }

    public String getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(String couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public String getCouponLeftCount() {
        return couponLeftCount;
    }

    public void setCouponLeftCount(String couponLeftCount) {
        this.couponLeftCount = couponLeftCount;
    }

    public float getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(float couponAmount) {
        this.couponAmount = couponAmount;
    }

    public float getCouponStartFee() {
        return couponStartFee;
    }

    public void setCouponStartFee(float couponStartFee) {
        this.couponStartFee = couponStartFee;
    }



}
