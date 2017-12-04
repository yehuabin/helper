package com.yhb.taobaohelper.model;

/**
 * Created by smk on 2017/11/27.
 */

public class CouponModel {

    /**
     * data : {"taoToken":"￥xvBe0ibOr0D￥","couponShortLinkUrl":"https://s.click.taobao.com/9Mn7NXw","qrCodeUrl":"//gqrcode.alicdn.com/img?type=hv&text=https%3A%2F%2Fs.click.taobao.com%2FaMp7NXw%3Faf%3D3&h=300&w=300","clickUrl":"https://s.click.taobao.com/t?e=m%3D2%26s%3DlWRWdznQlPQcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67rutMZav%2BLXJrumJQoe%2FxcPQVah9g6GuJMoi3ECVYC3Lxa5D0FMyVQJxhUHVAhYQhJXafG7jH8khMocVbYVY6Hby%2Flc8YI1OgajpVvc8kwJHEkWL7%2FScOBGiZ%2BQMlGz6FQ%3D%3D","couponLinkTaoToken":"￥ezJF0ibOpJm￥","couponLink":"https://uland.taobao.com/coupon/edetail?e=Lbc0dA9rKmma2P%2BN2ppgBymr%2B4btKdhvNW3%2Fwnz9ib9nrS0CGkLuxYqhn4kjh1Ue1DjtY8F0NPev3miAUqTz%2FMKUixUTTLeu0WMS0ZKY%2BzmLVIDjuHwzlw%3D%3D&pid=mm_50593678_39748344_148716480&af=1","type":"auction","shortLinkUrl":"https://s.click.taobao.com/aMp7NXw"}
     * info : {"message":null,"ok":true}
     * ok : true
     * invalidKey : null
     */

    private DataBean data;
    private InfoBean info;
    private boolean ok;
    private Object invalidKey;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getInvalidKey() {
        return invalidKey;
    }

    public void setInvalidKey(Object invalidKey) {
        this.invalidKey = invalidKey;
    }

    public static class DataBean {
        /**
         * taoToken : ￥xvBe0ibOr0D￥
         * couponShortLinkUrl : https://s.click.taobao.com/9Mn7NXw
         * qrCodeUrl : //gqrcode.alicdn.com/img?type=hv&text=https%3A%2F%2Fs.click.taobao.com%2FaMp7NXw%3Faf%3D3&h=300&w=300
         * clickUrl : https://s.click.taobao.com/t?e=m%3D2%26s%3DlWRWdznQlPQcQipKwQzePOeEDrYVVa64K7Vc7tFgwiHjf2vlNIV67rutMZav%2BLXJrumJQoe%2FxcPQVah9g6GuJMoi3ECVYC3Lxa5D0FMyVQJxhUHVAhYQhJXafG7jH8khMocVbYVY6Hby%2Flc8YI1OgajpVvc8kwJHEkWL7%2FScOBGiZ%2BQMlGz6FQ%3D%3D
         * couponLinkTaoToken : ￥ezJF0ibOpJm￥
         * couponLink : https://uland.taobao.com/coupon/edetail?e=Lbc0dA9rKmma2P%2BN2ppgBymr%2B4btKdhvNW3%2Fwnz9ib9nrS0CGkLuxYqhn4kjh1Ue1DjtY8F0NPev3miAUqTz%2FMKUixUTTLeu0WMS0ZKY%2BzmLVIDjuHwzlw%3D%3D&pid=mm_50593678_39748344_148716480&af=1
         * type : auction
         * shortLinkUrl : https://s.click.taobao.com/aMp7NXw
         */

        private String taoToken;
        private String couponShortLinkUrl;
        private String qrCodeUrl;
        private String clickUrl;
        private String couponLinkTaoToken;
        private String couponLink;
        private String type;
        private String shortLinkUrl;

        public String getTaoToken() {
            return taoToken;
        }

        public void setTaoToken(String taoToken) {
            this.taoToken = taoToken;
        }

        public String getCouponShortLinkUrl() {
            return couponShortLinkUrl;
        }

        public void setCouponShortLinkUrl(String couponShortLinkUrl) {
            this.couponShortLinkUrl = couponShortLinkUrl;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShortLinkUrl() {
            return shortLinkUrl;
        }

        public void setShortLinkUrl(String shortLinkUrl) {
            this.shortLinkUrl = shortLinkUrl;
        }
    }

    public static class InfoBean {
        /**
         * message : null
         * ok : true
         */

        private Object message;
        private boolean ok;

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }
    }
}
