package com.yhb.taobaohelper.utils;

/**
 * Created by Administrator on 2017/11/26.
 */

public class UrlUtil {
    public static String getSearchUrl(int page,String keyword){
        return "http://pub.alimama.com/items/search.json?toPage="+page+"&queryType=2&perPageSize=20&q="+keyword;
    }
    public static String getSearchUrl(String keyword){
        return getSearchUrl(1,keyword);
    }
    public static String getSearchUrl(String channel,int page,String shopTag,int sortType,String query) {
        String url = String.format("http://pub.alimama.com/items/channel/nzjh.json?channel=%s&perPageSize=100&toPage=%s&shopTag=%s&sortType=%s"
                        ,channel,page,shopTag,sortType);
        if (query.length()>0){
            url+="&"+query;
        }
      //  url+="&"+"t=1511835198144&_tb_token_=f1ee8e55ef93d&pvid=21_61.164.128.186_5516_1511835198066";
        return url;
    }
}
