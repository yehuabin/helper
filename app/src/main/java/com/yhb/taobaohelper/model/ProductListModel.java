package com.yhb.taobaohelper.model;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ProductListModel {


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

        private HeadBean head;
        private ConditionBean condition;
        private PaginatorBean paginator;
        private Object extraInfo;
        private List<PageListBean> pageList;
        private List<NavigatorBean> navigator;

        public HeadBean getHead() {
            return head;
        }

        public void setHead(HeadBean head) {
            this.head = head;
        }

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public Object getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(Object extraInfo) {
            this.extraInfo = extraInfo;
        }

        public List<PageListBean> getPageList() {
            return pageList;
        }

        public void setPageList(List<PageListBean> pageList) {
            this.pageList = pageList;
        }

        public List<NavigatorBean> getNavigator() {
            return navigator;
        }

        public void setNavigator(List<NavigatorBean> navigator) {
            this.navigator = navigator;
        }

        public static class HeadBean {


            private String version;
            private String status;
            private int pageNo;
            private int pageSize;
            private Object searchUrl;
            private String pvid;
            private Object errmsg;
            private Object fromcache;
            private int processtime;
            private int ha3time;
            private int docsfound;
            private int docsreturn;
            private Object responseTxt;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public Object getSearchUrl() {
                return searchUrl;
            }

            public void setSearchUrl(Object searchUrl) {
                this.searchUrl = searchUrl;
            }

            public String getPvid() {
                return pvid;
            }

            public void setPvid(String pvid) {
                this.pvid = pvid;
            }

            public Object getErrmsg() {
                return errmsg;
            }

            public void setErrmsg(Object errmsg) {
                this.errmsg = errmsg;
            }

            public Object getFromcache() {
                return fromcache;
            }

            public void setFromcache(Object fromcache) {
                this.fromcache = fromcache;
            }

            public int getProcesstime() {
                return processtime;
            }

            public void setProcesstime(int processtime) {
                this.processtime = processtime;
            }

            public int getHa3time() {
                return ha3time;
            }

            public void setHa3time(int ha3time) {
                this.ha3time = ha3time;
            }

            public int getDocsfound() {
                return docsfound;
            }

            public void setDocsfound(int docsfound) {
                this.docsfound = docsfound;
            }

            public int getDocsreturn() {
                return docsreturn;
            }

            public void setDocsreturn(int docsreturn) {
                this.docsreturn = docsreturn;
            }

            public Object getResponseTxt() {
                return responseTxt;
            }

            public void setResponseTxt(Object responseTxt) {
                this.responseTxt = responseTxt;
            }
        }

        public static class ConditionBean {
            /**
             * userType : null
             * queryType : 2
             * sortType : null
             * loc : null
             * includeDxjh : null
             * auctionTag : null
             * startDsr : null
             * freeShipment : null
             * startTkRate : null
             * endTkRate : null
             * startTkTotalSales : null
             * startPrice : null
             * endPrice : null
             * startRatesum : null
             * endRatesum : null
             * startQuantity : null
             * startBiz30day : null
             * startPayUv30 : null
             * hPayRate30 : null
             * hGoodRate : null
             * jhs : null
             * lRfdRate : null
             * startSpay30 : null
             * hSellerGoodrat : null
             * hSpayRate30 : null
             * hasUmpBonus : null
             * isBizActivity : null
             * subOeRule : null
             * auctionTagRaw : null
             * startRlRate : null
             * shopTag : [{"value":"yxjh","desc":"包含营销计划"}]
             * npxType : null
             * picQuality : null
             * selectedNavigator : null
             * typeTagName : null
             */

            private Object userType;
            private int queryType;
            private Object sortType;
            private Object loc;
            private Object includeDxjh;
            private Object auctionTag;
            private Object startDsr;
            private Object freeShipment;
            private Object startTkRate;
            private Object endTkRate;
            private Object startTkTotalSales;
            private Object startPrice;
            private Object endPrice;
            private Object startRatesum;
            private Object endRatesum;
            private Object startQuantity;
            private Object startBiz30day;
            private Object startPayUv30;
            private Object hPayRate30;
            private Object hGoodRate;
            private Object jhs;
            private Object lRfdRate;
            private Object startSpay30;
            private Object hSellerGoodrat;
            private Object hSpayRate30;
            private Object hasUmpBonus;
            private Object isBizActivity;
            private Object subOeRule;
            private Object auctionTagRaw;
            private Object startRlRate;
            private Object npxType;
            private Object picQuality;
            private Object selectedNavigator;
            private Object typeTagName;
            private List<ShopTagBean> shopTag;

            public Object getUserType() {
                return userType;
            }

            public void setUserType(Object userType) {
                this.userType = userType;
            }

            public int getQueryType() {
                return queryType;
            }

            public void setQueryType(int queryType) {
                this.queryType = queryType;
            }

            public Object getSortType() {
                return sortType;
            }

            public void setSortType(Object sortType) {
                this.sortType = sortType;
            }

            public Object getLoc() {
                return loc;
            }

            public void setLoc(Object loc) {
                this.loc = loc;
            }

            public Object getIncludeDxjh() {
                return includeDxjh;
            }

            public void setIncludeDxjh(Object includeDxjh) {
                this.includeDxjh = includeDxjh;
            }

            public Object getAuctionTag() {
                return auctionTag;
            }

            public void setAuctionTag(Object auctionTag) {
                this.auctionTag = auctionTag;
            }

            public Object getStartDsr() {
                return startDsr;
            }

            public void setStartDsr(Object startDsr) {
                this.startDsr = startDsr;
            }

            public Object getFreeShipment() {
                return freeShipment;
            }

            public void setFreeShipment(Object freeShipment) {
                this.freeShipment = freeShipment;
            }

            public Object getStartTkRate() {
                return startTkRate;
            }

            public void setStartTkRate(Object startTkRate) {
                this.startTkRate = startTkRate;
            }

            public Object getEndTkRate() {
                return endTkRate;
            }

            public void setEndTkRate(Object endTkRate) {
                this.endTkRate = endTkRate;
            }

            public Object getStartTkTotalSales() {
                return startTkTotalSales;
            }

            public void setStartTkTotalSales(Object startTkTotalSales) {
                this.startTkTotalSales = startTkTotalSales;
            }

            public Object getStartPrice() {
                return startPrice;
            }

            public void setStartPrice(Object startPrice) {
                this.startPrice = startPrice;
            }

            public Object getEndPrice() {
                return endPrice;
            }

            public void setEndPrice(Object endPrice) {
                this.endPrice = endPrice;
            }

            public Object getStartRatesum() {
                return startRatesum;
            }

            public void setStartRatesum(Object startRatesum) {
                this.startRatesum = startRatesum;
            }

            public Object getEndRatesum() {
                return endRatesum;
            }

            public void setEndRatesum(Object endRatesum) {
                this.endRatesum = endRatesum;
            }

            public Object getStartQuantity() {
                return startQuantity;
            }

            public void setStartQuantity(Object startQuantity) {
                this.startQuantity = startQuantity;
            }

            public Object getStartBiz30day() {
                return startBiz30day;
            }

            public void setStartBiz30day(Object startBiz30day) {
                this.startBiz30day = startBiz30day;
            }

            public Object getStartPayUv30() {
                return startPayUv30;
            }

            public void setStartPayUv30(Object startPayUv30) {
                this.startPayUv30 = startPayUv30;
            }

            public Object getHPayRate30() {
                return hPayRate30;
            }

            public void setHPayRate30(Object hPayRate30) {
                this.hPayRate30 = hPayRate30;
            }

            public Object getHGoodRate() {
                return hGoodRate;
            }

            public void setHGoodRate(Object hGoodRate) {
                this.hGoodRate = hGoodRate;
            }

            public Object getJhs() {
                return jhs;
            }

            public void setJhs(Object jhs) {
                this.jhs = jhs;
            }

            public Object getLRfdRate() {
                return lRfdRate;
            }

            public void setLRfdRate(Object lRfdRate) {
                this.lRfdRate = lRfdRate;
            }

            public Object getStartSpay30() {
                return startSpay30;
            }

            public void setStartSpay30(Object startSpay30) {
                this.startSpay30 = startSpay30;
            }

            public Object getHSellerGoodrat() {
                return hSellerGoodrat;
            }

            public void setHSellerGoodrat(Object hSellerGoodrat) {
                this.hSellerGoodrat = hSellerGoodrat;
            }

            public Object getHSpayRate30() {
                return hSpayRate30;
            }

            public void setHSpayRate30(Object hSpayRate30) {
                this.hSpayRate30 = hSpayRate30;
            }

            public Object getHasUmpBonus() {
                return hasUmpBonus;
            }

            public void setHasUmpBonus(Object hasUmpBonus) {
                this.hasUmpBonus = hasUmpBonus;
            }

            public Object getIsBizActivity() {
                return isBizActivity;
            }

            public void setIsBizActivity(Object isBizActivity) {
                this.isBizActivity = isBizActivity;
            }

            public Object getSubOeRule() {
                return subOeRule;
            }

            public void setSubOeRule(Object subOeRule) {
                this.subOeRule = subOeRule;
            }

            public Object getAuctionTagRaw() {
                return auctionTagRaw;
            }

            public void setAuctionTagRaw(Object auctionTagRaw) {
                this.auctionTagRaw = auctionTagRaw;
            }

            public Object getStartRlRate() {
                return startRlRate;
            }

            public void setStartRlRate(Object startRlRate) {
                this.startRlRate = startRlRate;
            }

            public Object getNpxType() {
                return npxType;
            }

            public void setNpxType(Object npxType) {
                this.npxType = npxType;
            }

            public Object getPicQuality() {
                return picQuality;
            }

            public void setPicQuality(Object picQuality) {
                this.picQuality = picQuality;
            }

            public Object getSelectedNavigator() {
                return selectedNavigator;
            }

            public void setSelectedNavigator(Object selectedNavigator) {
                this.selectedNavigator = selectedNavigator;
            }

            public Object getTypeTagName() {
                return typeTagName;
            }

            public void setTypeTagName(Object typeTagName) {
                this.typeTagName = typeTagName;
            }

            public List<ShopTagBean> getShopTag() {
                return shopTag;
            }

            public void setShopTag(List<ShopTagBean> shopTag) {
                this.shopTag = shopTag;
            }

            public static class ShopTagBean {
                /**
                 * value : yxjh
                 * desc : 包含营销计划
                 */

                private String value;
                private String desc;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }

        public static class PaginatorBean {
            /**
             * length : 50
             * offset : 0
             * page : 1
             * beginIndex : 1
             * endIndex : 50
             * items : 3160391
             * pages : 63208
             * itemsPerPage : 50
             * firstPage : 1
             * lastPage : 63208
             * previousPage : 1
             * nextPage : 2
             * slider : [1,2,3,4,5,6,7]
             */

            private int length;
            private int offset;
            private int page;
            private int beginIndex;
            private int endIndex;
            private int items;
            private int pages;
            private int itemsPerPage;
            private int firstPage;
            private int lastPage;
            private int previousPage;
            private int nextPage;
            private List<Integer> slider;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getBeginIndex() {
                return beginIndex;
            }

            public void setBeginIndex(int beginIndex) {
                this.beginIndex = beginIndex;
            }

            public int getEndIndex() {
                return endIndex;
            }

            public void setEndIndex(int endIndex) {
                this.endIndex = endIndex;
            }

            public int getItems() {
                return items;
            }

            public void setItems(int items) {
                this.items = items;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getItemsPerPage() {
                return itemsPerPage;
            }

            public void setItemsPerPage(int itemsPerPage) {
                this.itemsPerPage = itemsPerPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getPreviousPage() {
                return previousPage;
            }

            public void setPreviousPage(int previousPage) {
                this.previousPage = previousPage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public List<Integer> getSlider() {
                return slider;
            }

            public void setSlider(List<Integer> slider) {
                this.slider = slider;
            }
        }

        public static class PageListBean {


            private String rootCatId;
            private String leafCatId;
            private String eventCreatorId;
            private Object debugInfo;
            private String rootCatScore;
            private String nick;
            private String userType;
            private String title;
            private String sellerId;
            private String shopTitle;
            private String pictUrl;
            private String auctionId;
            private Object tkMktStatus;
            private int biz30day;
            private float tkRate;
            private String includeDxjh;
            private String reservePrice;
            private String tkCommFee;
            private String totalFee;
            private String totalNum;
            private float zkPrice;
            private String auctionTag;
            private String auctionUrl;
            private String rlRate;
            private Object hasRecommended;
            private Object hasSame;
            private Object tk3rdRate;
            private String sameItemPid;
            private Object couponActivityId;
            private String couponTotalCount;
            private String couponLeftCount;
            private String couponLink;
            private String couponLinkTaoToken;
            private float couponAmount;
            private String dayLeft;
            private Object couponShortLink;
            private String couponInfo;
            private float couponStartFee;
            private String couponEffectiveStartTime;
            private String couponEffectiveEndTime;
            private Object eventRate;
            private Object hasUmpBonus;
            private Object isBizActivity;
            private Object umpBonus;
            private Object rootCategoryName;
            private Object couponOriLink;
            private Object userTypeName;


            public String getRootCatId() {
                return rootCatId;
            }

            public void setRootCatId(String rootCatId) {
                this.rootCatId = rootCatId;
            }

            public String getLeafCatId() {
                return leafCatId;
            }

            public void setLeafCatId(String leafCatId) {
                this.leafCatId = leafCatId;
            }

            public String getEventCreatorId() {
                return eventCreatorId;
            }

            public void setEventCreatorId(String eventCreatorId) {
                this.eventCreatorId = eventCreatorId;
            }

            public Object getDebugInfo() {
                return debugInfo;
            }

            public void setDebugInfo(Object debugInfo) {
                this.debugInfo = debugInfo;
            }

            public String getRootCatScore() {
                return rootCatScore;
            }

            public void setRootCatScore(String rootCatScore) {
                this.rootCatScore = rootCatScore;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
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

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
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

            public Object getTkMktStatus() {
                return tkMktStatus;
            }

            public void setTkMktStatus(Object tkMktStatus) {
                this.tkMktStatus = tkMktStatus;
            }

            public int getBiz30day() {
                return biz30day;
            }

            public void setBiz30day(int biz30day) {
                this.biz30day = biz30day;
            }

            public float getTkRate() {
                return tkRate;
            }

            public void setTkRate(float tkRate) {
                this.tkRate = tkRate;
            }

            public String getIncludeDxjh() {
                return includeDxjh;
            }

            public void setIncludeDxjh(String includeDxjh) {
                this.includeDxjh = includeDxjh;
            }

            public String getReservePrice() {
                return reservePrice;
            }

            public void setReservePrice(String reservePrice) {
                this.reservePrice = reservePrice;
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

            public String getAuctionTag() {
                return auctionTag;
            }

            public void setAuctionTag(String auctionTag) {
                this.auctionTag = auctionTag;
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

            public Object getHasRecommended() {
                return hasRecommended;
            }

            public void setHasRecommended(Object hasRecommended) {
                this.hasRecommended = hasRecommended;
            }

            public Object getHasSame() {
                return hasSame;
            }

            public void setHasSame(Object hasSame) {
                this.hasSame = hasSame;
            }

            public Object getTk3rdRate() {
                return tk3rdRate;
            }

            public void setTk3rdRate(Object tk3rdRate) {
                this.tk3rdRate = tk3rdRate;
            }

            public String getSameItemPid() {
                return sameItemPid;
            }

            public void setSameItemPid(String sameItemPid) {
                this.sameItemPid = sameItemPid;
            }

            public Object getCouponActivityId() {
                return couponActivityId;
            }

            public void setCouponActivityId(Object couponActivityId) {
                this.couponActivityId = couponActivityId;
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

            public String getCouponLink() {
                return couponLink;
            }

            public void setCouponLink(String couponLink) {
                this.couponLink = couponLink;
            }

            public String getCouponLinkTaoToken() {
                return couponLinkTaoToken;
            }

            public void setCouponLinkTaoToken(String couponLinkTaoToken) {
                this.couponLinkTaoToken = couponLinkTaoToken;
            }

            public float getCouponAmount() {
                return couponAmount;
            }

            public void setCouponAmount(float couponAmount) {
                this.couponAmount = couponAmount;
            }

            public String getDayLeft() {
                return dayLeft;
            }

            public void setDayLeft(String dayLeft) {
                this.dayLeft = dayLeft;
            }

            public Object getCouponShortLink() {
                return couponShortLink;
            }

            public void setCouponShortLink(Object couponShortLink) {
                this.couponShortLink = couponShortLink;
            }

            public String getCouponInfo() {
                return couponInfo;
            }

            public void setCouponInfo(String couponInfo) {
                this.couponInfo = couponInfo;
            }

            public float getCouponStartFee() {
                return couponStartFee;
            }

            public void setCouponStartFee(float couponStartFee) {
                this.couponStartFee = couponStartFee;
            }

            public String getCouponEffectiveStartTime() {
                return couponEffectiveStartTime;
            }

            public void setCouponEffectiveStartTime(String couponEffectiveStartTime) {
                this.couponEffectiveStartTime = couponEffectiveStartTime;
            }

            public String getCouponEffectiveEndTime() {
                return couponEffectiveEndTime;
            }

            public void setCouponEffectiveEndTime(String couponEffectiveEndTime) {
                this.couponEffectiveEndTime = couponEffectiveEndTime;
            }

            public Object getEventRate() {
                return eventRate;
            }

            public void setEventRate(Object eventRate) {
                this.eventRate = eventRate;
            }

            public Object getHasUmpBonus() {
                return hasUmpBonus;
            }

            public void setHasUmpBonus(Object hasUmpBonus) {
                this.hasUmpBonus = hasUmpBonus;
            }

            public Object getIsBizActivity() {
                return isBizActivity;
            }

            public void setIsBizActivity(Object isBizActivity) {
                this.isBizActivity = isBizActivity;
            }

            public Object getUmpBonus() {
                return umpBonus;
            }

            public void setUmpBonus(Object umpBonus) {
                this.umpBonus = umpBonus;
            }

            public Object getRootCategoryName() {
                return rootCategoryName;
            }

            public void setRootCategoryName(Object rootCategoryName) {
                this.rootCategoryName = rootCategoryName;
            }

            public Object getCouponOriLink() {
                return couponOriLink;
            }

            public void setCouponOriLink(Object couponOriLink) {
                this.couponOriLink = couponOriLink;
            }

            public Object getUserTypeName() {
                return userTypeName;
            }

            public void setUserTypeName(Object userTypeName) {
                this.userTypeName = userTypeName;
            }


        }

        public static class NavigatorBean {
            /**
             * name : 女装
             * id : 50102996
             * type : category
             * level : 1
             * count : 2496415
             * flag : qp_commend
             * subIds : [{"name":"T恤","id":50103041,"type":"category","level":1,"count":"346145","flag":"qp_commend","subIds":null},{"name":"大码女装","id":50103042,"type":"category","level":1,"count":"258331","flag":"qp_commend","subIds":null},{"name":"针织衫","id":50103047,"type":"category","level":1,"count":"176033","flag":"qp_commend","subIds":null},{"name":"连衣裙","id":50103032,"type":"category","level":1,"count":"172146","flag":"qp_commend","subIds":null},{"name":"卫衣/绒衫","id":50103043,"type":"category","level":1,"count":"151445","flag":"qp_commend","subIds":null},{"name":"牛仔裤","id":50103038,"type":"category","level":1,"count":"145948","flag":"qp_commend","subIds":null},{"name":"休闲裤","id":50103029,"type":"category","level":1,"count":"138045","flag":"qp_commend","subIds":null},{"name":"衬衫","id":50103018,"type":"category","level":1,"count":"124714","flag":"qp_commend","subIds":null},{"name":"小背心/小吊带","id":50103044,"type":"category","level":1,"count":"106917","flag":"qp_commend","subIds":null},{"name":"中老年服装","id":50103048,"type":"category","level":1,"count":"100939","flag":"qp_commend","subIds":null},{"name":"毛呢外套","id":50103036,"type":"category","level":1,"count":"94139","flag":"qp_commend","subIds":null},{"name":"蕾丝衫/雪纺衫","id":50103033,"type":"category","level":1,"count":"88030","flag":"qp_commend","subIds":null},{"name":"毛衣","id":50103035,"type":"category","level":1,"count":"84793","flag":"qp_commend","subIds":null},{"name":"棉衣/棉服","id":50103037,"type":"category","level":1,"count":"75299","flag":"qp_commend","subIds":null},{"name":"休闲套装","id":50103027,"type":"category","level":1,"count":"61063","flag":"qp_commend","subIds":null},{"name":"短外套","id":50103019,"type":"category","level":1,"count":"57226","flag":"qp_commend","subIds":null},{"name":"半身裙","id":50103017,"type":"category","level":1,"count":"53273","flag":"qp_commend","subIds":null},{"name":"羽绒服","id":50103046,"type":"category","level":1,"count":"38957","flag":"qp_commend","subIds":null},{"name":"风衣","id":50103020,"type":"category","level":1,"count":"27369","flag":"qp_commend","subIds":null}]
             */

            private String name;
            private int id;
            private String type;
            private int level;
            private String count;
            private String flag;
            private List<SubIdsBean> subIds;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public List<SubIdsBean> getSubIds() {
                return subIds;
            }

            public void setSubIds(List<SubIdsBean> subIds) {
                this.subIds = subIds;
            }

            public static class SubIdsBean {
                /**
                 * name : T恤
                 * id : 50103041
                 * type : category
                 * level : 1
                 * count : 346145
                 * flag : qp_commend
                 * subIds : null
                 */

                private String name;
                private int id;
                private String type;
                private int level;
                private String count;
                private String flag;
                private Object subIds;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public Object getSubIds() {
                    return subIds;
                }

                public void setSubIds(Object subIds) {
                    this.subIds = subIds;
                }
            }
        }
    }

    public static class InfoBean {
        /**
         * message : null
         * pvid : 10_115.234.19.224_439_1511268083319
         * ok : true
         */

        private Object message;
        private String pvid;
        private boolean ok;

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }

        public String getPvid() {
            return pvid;
        }

        public void setPvid(String pvid) {
            this.pvid = pvid;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }
    }
}
