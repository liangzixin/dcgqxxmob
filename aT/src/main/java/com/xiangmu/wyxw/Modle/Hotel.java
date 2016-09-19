package com.xiangmu.wyxw.Modle;

import java.io.Serializable;

/**
 * Created by admin on 2016-08-17.
 */
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    // 编号
    private Integer id;
    // 文件路径
    private Integer productid;
    private String gsmz;
    private String gsdz;
    private Float baseprice;// 商品采购价格
    private Float marketprice;// 现在市场价格
    private Float sellprice;// 商城销售价格
    private Float totalPrice;// 总额
    private Room room;

    private ProductInfo productInfo;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGsmz() {
        return gsmz;
    }
    public void setGsmz(String gsmz) {
        this.gsmz = gsmz;
    }
    public String getGsdz() {
        return gsdz;
    }
    public void setGsdz(String gsdz) {
        this.gsdz = gsdz;
    }
    public ProductInfo getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
    public void setBaseprice(Float baseprice) {
        this.baseprice = baseprice;
    }
    public Float getBaseprice() {
        return baseprice;
    }
    public void setMarketprice(Float marketprice) {
        this.marketprice = marketprice;
    }
    public Float getMarketprice() {
        return marketprice;
    }
    public void setSellprice(Float sellprice) {
        this.sellprice = sellprice;
    }
    public Float getSellprice() {
        return sellprice;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Integer getProductid() {
        return productid;
    }
    public void setProductid(Integer productid) {
        this.productid = productid;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
