package com.web.yx.common.util;

import java.util.regex.Pattern;

/**
 * Created by T006113 on 2015/6/8.
 */
public class StringUtils {

    public static final String REG_MOBILE = "^((1(7[0-9])\\d{8}$)|(1(3[0-9])\\d{8}$)|(1(4[0-9])\\d{8}$)|(1(5[0-9])\\d{8}$)|(1(8[0-9])\\d{8}$))";

    public static boolean isNotEmpty(String source){
        return source!=null&&!source.trim().equals("");
    }

    /**判断是否满足某正则式*/
    public static boolean isPhone(String phone,String reg){
        Pattern p = Pattern.compile(reg);
        return p.matcher(phone).matches();
    }

    /***
     * 判断字符串为空
     * @param source
     * @return
     */
    public static boolean isEmpty(String source){
        return source==null||source.trim().equals("");
    }


}
