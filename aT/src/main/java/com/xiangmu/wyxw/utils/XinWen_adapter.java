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
    public final static int toutiao = 0;
    public final static int yule = 1;
    public final static int tiyu = 2;
    public final static int caijing= 3;
    public final static int keji= 4;
    public final static int shishang= 5;
    public final static int lishi= 6;
    public final static int caipiao= 7;
    public final static int junshi= 8;
    public final static int youxi= 9;
    public final static int rendian= 100;
    public static int getXinWenType(String daoHangtitle){
        if (daoHangtitle.equals("热点")){
            return rendian;
        }else if (daoHangtitle.equals("最新")){
            return toutiao;
        }else if (daoHangtitle.equals("招聘")){
            return yule;
        }else if (daoHangtitle.equals("求职")){
            return tiyu;
        }else if (daoHangtitle.equals("出售")){
            return caijing;
        }else if (daoHangtitle.equals("出租")){
            return keji;
        } else if (daoHangtitle.equals("供求")){
            return shishang;
        } else if (daoHangtitle.equals("二手")){
            return lishi;
        } else if (daoHangtitle.equals("其它")){
            return caipiao;
        } else if (daoHangtitle.equals("铺面")){
            return junshi;
        } else if (daoHangtitle.equals("家具")){
            return youxi;
        }
        // TODO: 2015/11/14
        return toutiao;
    }
}
