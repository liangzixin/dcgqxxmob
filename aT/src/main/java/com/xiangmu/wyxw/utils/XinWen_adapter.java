package com.xiangmu.wyxw.utils;

/**
 * Created by Administrator on 2015/11/13.
 */
public class XinWen_adapter {
    public final static int TYPE_putong = 0;
    public final static int type_duotu = 1;
    public final static int TYPE_zhuanti = 2;
    public final static int TYPE_zhibo = 3;

    //判断item的类型
    public static int getType(String skipType) {
        if (skipType == null) {
            return TYPE_putong;
        }
        if (skipType.equals("photoset")) {
            return type_duotu;
        } else if (skipType.equals("special")) {
            return TYPE_zhuanti;
        } else if (skipType.equals("live")) {
            return TYPE_zhibo;
        }
        return TYPE_putong;
    }
    public final static int zuixin = 0;
    public final static int zaopin = 1;
    public final static int qiuzhi= 2;
    public final static int chushou= 3;
    public final static int chuzu= 4;
    public final static int gongqiu= 5;
    public final static int ershou= 6;
    public final static int qita= 7;
    public final static int pumian= 8;
    public final static int jiaju= 9;
    public final static int rendian= 100;
    public final static int yule= 102;
    public final static int lishi= 103;
    public static int getXinWenType(String daoHangtitle){
        if (daoHangtitle.equals("热点")){
            return rendian;
        }else if (daoHangtitle.equals("最新")){
            return zuixin;
        }else if (daoHangtitle.equals("招聘")){
            return zaopin;
        }else if (daoHangtitle.equals("求职")){
            return qiuzhi;
        }else if (daoHangtitle.equals("出售")){
            return chushou;
        }else if (daoHangtitle.equals("出租")){
            return chuzu;
        } else if (daoHangtitle.equals("供求")){
            return gongqiu;
        } else if (daoHangtitle.equals("二手")){
            return ershou;
        } else if (daoHangtitle.equals("其它")){
            return qita;
        } else if (daoHangtitle.equals("铺面")){
            return pumian;
        } else if (daoHangtitle.equals("家具")){
            return jiaju;
        }
        // TODO: 2015/11/14
        return zuixin;
    }
}
