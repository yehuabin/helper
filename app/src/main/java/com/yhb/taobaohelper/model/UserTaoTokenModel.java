package com.yhb.taobaohelper.model;

/**
 * Created by smk on 2018/3/26.
 */

public class UserTaoTokenModel {
    private String _id;
    private String auctionId;
    private boolean isReply;
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }


}
