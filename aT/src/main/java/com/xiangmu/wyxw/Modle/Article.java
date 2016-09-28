package com.xiangmu.wyxw.Modle;

import com.twiceyuan.commonadapter.library.adapter.ViewTypeItem;

import java.util.List;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class Article implements ViewTypeItem {

    public String title;
    public String content;
    public String dat;
    public String gsmz;
    public String gsdz;
    public String lxr;
    public String lxdh;

    public Zpxx zpxx;
    public Fwcs fwcs;
    public ProductCategory productCategory;
    public String[] m={"请选择出差地点","昆明","汤丹","殡仪馆","其它"};
}
