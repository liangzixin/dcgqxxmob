package com.xiangmu.lzx.Modle;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016-08-17.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 用户编号
    private String username="";// 用户名
    private String password="";// 密码
    private String realname="";// 真实姓名
    private String email="";// 邮箱
    private String address="";// 住址
    private String mobile="";// 手机
    private ProductInfo productInfo;
    private String openid="";
    private String screenname="";
    private String imageurl="";
    private int jinbi=0;
    private String registerdate="";
    private String logindate="";
    private String sfzh="";
    private String headimg="";
    private String endtime="";//
    private String starttime="";
    private Set<Shezhi> shezhi= new HashSet<Shezhi>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
    public ProductInfo getProductInfo() {
        return productInfo;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getScreenname() {
        return screenname;
    }
    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public int getJinbi() {
        return jinbi;
    }
    public void setJinbi(int jinbi) {
        this.jinbi = jinbi;
    }
    public String getRegisterdate() {
        return registerdate;
    }
    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate;
    }
    public String getLogindate() {
        return logindate;
    }
    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }
    public Set<Shezhi> getShezhi() {
        return shezhi;
    }
    public void setShezhi(Set<Shezhi> shezhi) {
        this.shezhi = shezhi;
    }
    public String getSfzh() {
        return sfzh;
    }
    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
    public String getHeadimg() {
        return headimg;
    }
    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

}
