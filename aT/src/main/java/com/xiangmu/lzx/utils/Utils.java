package com.xiangmu.lzx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/18.
 */
public class Utils {


    public static boolean isnumber(String s){
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    };
    public static String IntToChar(int int0){
       String inttoc="  ";
        if(int0!=0){
            inttoc=int0+"";
        }
        return inttoc;
    };
    public static String FloatToChar(float int0){
        String inttoc="  ";
        if(Float.compare(int0,0.0f)!=0){
            inttoc=int0+"";
        }
        return inttoc;
    };
}