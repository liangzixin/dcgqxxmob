package com.xiangmu.wyxw.utils;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/18.
 */
public class DateTime {
    public static String getDate(){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//获得年份
        int month = calendar.get(Calendar.MONTH)+1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取天数
        return year + "年" + month + "月" + day + "日" ;
    }
    public static String getTime() {
        Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return hour + "时" + minute + "分" + second + "秒";
    }
    public static String getDate_Time(){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//获得年份
        int month = calendar.get(Calendar.MONTH)+1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取天数
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year + "年" + month + "月" + day + "日"
                +hour + "时" + minute + "分" + second + "秒";
    }
    public void initDate(){

    };
 //  @Test
   public String getMonthday(  String str){
       // 需要解析的日期字符串
 //    String str = "2015-09-27 12:15:31";
       String dateStr = "";
// 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
// mm，小写，表示分钟，ss表示秒
       //SimpleDateFormat format = new SimpleDateFormat("dd/yy");
     //  DecimalFormat format1 = new DecimalFormat("00");

           // 用parse方法，可能会异常，所以要try-catch
     //     Date date = format.parse(str);
           // 获取日期实例
        //   Calendar calendar = Calendar.getInstance();
           // 将日历设置为指定的时间
        //   calendar.setTime(date);
           // 获取年
         //  int year = calendar.get(Calendar.YEAR);
           // 这里要注意，月份是从0开始。
         //  int month = calendar.get(Calendar.MONTH);
           // 获取天
      //     int day = calendar.get(Calendar.DAY_OF_MONTH);
           dateStr=str.substring(8,10)+"/"+str.substring(5,7);


    return  dateStr;
   } ;
}
