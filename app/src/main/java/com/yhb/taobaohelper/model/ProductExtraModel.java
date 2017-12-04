package com.yhb.taobaohelper.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/3.
 */

public class ProductExtraModel extends BmobObject {
    private String couponLinkTaoToken="";
    private String couponLink="";
    private String auctionId="";

    public String getCouponLinkTaoToken() {
        return couponLinkTaoToken;
    }

    public void setCouponLinkTaoToken(String couponLinkTaoToken) {
        this.couponLinkTaoToken = couponLinkTaoToken;
    }

    public String getCouponLink() {
        return couponLink;
    }

    public void setCouponLink(String couponLink) {
        this.couponLink = couponLink;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
