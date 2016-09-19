package com.xiangmu.wyxw.Modle;

import java.io.Serializable;

/**
 * Created by admin on 2016-08-17.
 */
public class Upcomment implements Serializable {
    private static final long serialVersionUID = 1L;
    // 编号
    private Integer id;

    private String compath;

    private ProductInfo productInfo;


    public String getCompath() {
        return compath;
    }

    public void setCompath(String compath) {
        this.compath = compath;
    }




    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }


}
