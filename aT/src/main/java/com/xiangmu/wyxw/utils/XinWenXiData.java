package com.xiangmu.wyxw.utils;

//import com.xiangmu.wyxw.Modle.UploadFile;

import com.xiangmu.wyxw.Modle.Fwcs;
import com.xiangmu.wyxw.Modle.ProductCategory;
import com.xiangmu.wyxw.Modle.Zpxx;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 * 新闻跳转详细页面的工具类
 */
public class XinWenXiData implements Serializable{

    private int id;
    private int bujuType;

    private int lanMuType;
    private String url;
    private int replaycount;
    private String title;
    private String createDate;
    private String xinwentext;
    private String gsmz;
    private String gsdz;
    private String lxr;
    private String lxdh;
    private Zpxx zpxx;
    private Fwcs fwcs;
    private ProductCategory ProductCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Fwcs getFwcs() {
        return fwcs;
    }

    public void setFwcs(Fwcs fwcs) {
        this.fwcs = fwcs;
    }


    public com.xiangmu.wyxw.Modle.ProductCategory getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(com.xiangmu.wyxw.Modle.ProductCategory productCategory) {
        ProductCategory = productCategory;
    }


    public Zpxx getZpxx() {
        return zpxx;
    }

    public void setZpxx(Zpxx zpxx) {
        this.zpxx = zpxx;
    }


    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }


    public String getGsdz() {
        return gsdz;
    }

    public void setGsdz(String gsdz) {
        this.gsdz = gsdz;
    }


    public String getGsmz() {
        return gsmz;
    }

    public void setGsmz(String gsmz) {
        this.gsmz = gsmz;
    }




    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXinwentext() {
        return xinwentext;
    }

    public void setXinwentext(String xinwentext) {
        this.xinwentext = xinwentext;
    }

    public int getBujuType() {
        return bujuType;
    }

    public void setBujuType(int bujuType) {
        this.bujuType = bujuType;
    }

    public int getLanMuType() {
        return lanMuType;
    }

    public void setLanMuType(int lanMuType) {
        this.lanMuType = lanMuType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getReplaycount() {
        return replaycount;
    }

    public void setReplaycount(int replaycount) {
        this.replaycount = replaycount;
    }
}
