package com.biz.util;

/**
 * Created by Administrator on 2016/10/9.
 */
public class StringUtil {

    public static String StringNotNullAndEmpty(String str){
        if(null == str || "".equals(str.trim())){
            return null;
        } else {
            return str.trim();
        }
    }

}
