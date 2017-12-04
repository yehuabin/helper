package com.yhb.taobaohelper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smk on 2017/11/23.
 */

public class TextUtil {
    public static String clearZero(String num) {
        num = String.format("%.1f", Double.parseDouble(num));
        if (num.indexOf(".") > 0) {
            //正则表达
            num = num.replaceAll("0+?$", "");//去掉后面无用的零
            num = num.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return num;
    }
    public static String clearZero(float num) {
    return clearZero(String.valueOf(num));
    }
    public static String getBiz30day(String num){
        if (num.length()>=5){
            num=num.substring(0,1)+"."+num.substring(1,2)+"万";
        }
        return num;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
