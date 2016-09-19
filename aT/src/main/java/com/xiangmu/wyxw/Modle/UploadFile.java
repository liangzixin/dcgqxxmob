package com.xiangmu.wyxw.Modle;

import java.io.Serializable;

/**
 * Created by admin on 2016-08-17.
 */
public class UploadFile implements Serializable {
    private static final long serialVersionUID = 1L;
    // 编号
    private Integer id;
    private Integer productid;
    private Double wid;
    private Double hig;
    private int rand;

    // 文件路径
    private String path;
    private ProductInfo productinfo;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setProductid(Integer productid) {
        this.productid = productid;
    }
    public Integer getProductid() {
        return productid;
    }
    public void setProductinfo(ProductInfo productinfo) {
        this.productinfo = productinfo;
    }
    public ProductInfo getProductinfo() {
        return productinfo;
    }
    public Double getWid() {
        return wid;
    }
    public void setWid(Double wid) {
        this.wid = wid;
    }
    public Double getHig() {
        return hig;
    }
    public void setHig(Double hig) {
        this.hig = hig;
    }
    public int getRand() {
        return rand;
    }
    public void setRand(int rand) {
        this.rand = rand;
    }


}
