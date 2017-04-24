package com.xiangmu.lzx.Setting_Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/11/16.
 */
public class SearchDB {

    public static String createDb(Context context, String Name) {
        String user_name = null;

//        SharedPreferences preferences = context.getSharedPreferences("useInfo", Context.MODE_PRIVATE);
        SharedPreferences preferences = context.getSharedPreferences("useInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
//        String name_email = preferences.getString("userName", null);
        String name0= preferences.getString(Name, null);
        if (name0!= null) {
//            user_name = preferences.getString("userName", "");
            user_name = preferences.getString(Name, "");
//            String name_eml = "m" + user_name + "_1@163.com";
            return user_name;
        }
        return user_name;

    }
    public static String TouXiangDb(Context context, String pic_Path) {
        String pic_Pathload = null;
        SharedPreferences preferences = context.getSharedPreferences("useInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String pic_path = preferences.getString("pic_path", null);
        if (pic_path != null) {
            pic_Pathload = pic_path;
//            String pic_pathload = "storage/sdcard0/" + pic_Pathload;
//            return pic_pathload;
        }
        return pic_Pathload;

    }

    //删除数据
    public static void removeDb(SharedPreferences preferences) {
        preferences.edit().remove("userName").commit();
    }
}
