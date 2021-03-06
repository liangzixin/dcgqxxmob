package com.xiangmu.lzx.Modle;

import java.io.Serializable;

/**
 * Created by admin on 2016-08-17.
 */
public class ProductArticlerl implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 类别编号
    private Integer artreview_rootid;// 类别名称
    private String artreview_content;

    public String getArtreview_authorid() {
        return artreview_authorid;
    }

    public void setArtreview_authorid(String artreview_authorid) {
        this.artreview_authorid = artreview_authorid;
    }

    private String artreview_authorid;

    public String getArtreview_time() {
        return artreview_time;
    }

    public void setArtreview_time(String artreview_time) {
        this.artreview_time = artreview_time;
    }

    private String artreview_time;
    //private Set<ProductCategory> children;// 子产品类别
    //private ProductCategory parent;// 父类别
    //private Set<ProductInfo> products = new TreeSet<ProductInfo>();// 包含商品
    private ProductInfo product;
    private Customer customer;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    /*
    public Set<ProductInfo> getProducts() {
        return products;
    }
    public void setProducts(Set<ProductInfo> products) {
        this.products = products;
    }
    */
    public void setArtreview_rootid(Integer artreview_rootid) {
        this.artreview_rootid = artreview_rootid;
    }
    public Integer getArtreview_rootid() {
        return artreview_rootid;
    }
    public void setArtreview_content(String artreview_content) {
        this.artreview_content = artreview_content;
    }
    public String getArtreview_content() {
        return artreview_content;
    }


    public ProductInfo getProduct() {
        return product;
    }
    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
    }

}
