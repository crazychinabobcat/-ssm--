package com.lynx.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串

    public static String date2String(Date date,String patt){

        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String formt = sdf.format(date);
        return  formt;

    }

    //字符串转成日期
    public static Date string2Date(String str ,String patt) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(str);
        return  parse;

    }
}
