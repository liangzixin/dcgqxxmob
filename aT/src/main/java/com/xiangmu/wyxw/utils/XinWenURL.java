package com.xiangmu.wyxw.utils;

import com.xiangmu.wyxw.Modle.ProductInfo;
import com.xiangmu.wyxw.Service.ProductInfoService;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class XinWenURL {
    public int stratPage = 0;
    List<ProductInfo> productInfos=null;
    //热点
   // String redian="http://c.3g.163.com/nc/article/list/T1429173762551/0-20.html";

  String redian = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=0&pageNo=0";
 String toutiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=0&pageNo=0";
    //  String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + stratPage + "-" + (stratPage + 20) + ".html";//头条
    String yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=1&pageNo=0";
  //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
    //String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
    String tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=2&pageNo=0";
    //String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
    String chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=3&pageNo=0";
    //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
    String keji = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=4&pageNo=0";
   // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
    String qiche = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=5&pageNo=0";
    //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
    String shishang = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=6&pageNo=0";
    //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
    String junshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=7&pageNo=0";
  //  String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
    String lishi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=8&pageNo=0";
    //String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
    String caipiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=9&pageNo=0";
    //String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" + stratPage + "-" + (stratPage + 20) + ".html";//游戏
    String youxi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=10&pageNo=0";
    //String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" + stratPage + "-" + (stratPage + 20) + ".html";//影视
    String yingshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=11&pageNo=0";
    //String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" + stratPage + "-" + (stratPage + 20) + ".html";//论坛
    String luntan = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=12&pageNo=0";
    public int getStratPage() {
        return stratPage;
    }
    public void setStratPage(int stratPage) {
        this.stratPage = stratPage;
    }

    public String getRedian() {
        return redian;
    }

    public String getToutiao() {
        int page = getStratPage();
  //String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + page + "-" + (page + 20) + ".html";//头条
        String toutiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=0&pageNo="+page;
        return toutiao;
    }

    public String getYule() {
        int page = getStratPage();
      //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
      //  String yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=1&pageNo="+page;
        return yule;
    }

    public String getTiyu() {
        int page = getStratPage();
       // String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
      //  String tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=2&pageNo="+page;
        return tiyu;
    }

    public String getChaijing() {
        int page = getStratPage();
       // String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
        //String chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=3&pageNo="+page;
        return chaijing;
    }

    public String getKeji() {
        int page = getStratPage();
        //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
      //  String keji = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  keji= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=4&pageNo="+page;
        return keji;
    }

    public String getQiche() {
        int page = getStratPage();
       // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
       // String qiche = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  qiche= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=5&pageNo="+page;
        return qiche;
    }

    public String getShishang() {
        int page = getStratPage();
        //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
      //  String shishang = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  shishang= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=6&pageNo="+page;
        return shishang;
    }

    public String getJunshi() {
        int page = getStratPage();
        //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
     //   String junshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  junshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=7&pageNo="+page;
        return junshi;
    }

    public String getLishi() {
        int page = getStratPage();
       // String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
        //String lishi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  lishi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=8&pageNo="+page;
        return lishi;
    }

    public String getCaipiao() {
        int page = getStratPage();
       // String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
      //  String caipiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  caipiao= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=9&pageNo="+page;
        return caipiao;
    }

    public String getYouxi() {
        int page = getStratPage();
       // String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" + stratPage + "-" + (stratPage + 20) + ".html";//游戏
      //  String youxi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  youshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=10&pageNo="+page;
        return youxi;
    }

    public String getYingshi() {
        int page = getStratPage();
      //  String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" + stratPage + "-" + (stratPage + 20) + ".html";//影视
       // String yingshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  yingshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=11&pageNo="+page;
        return yingshi;
    }

    public String getLuntan() {
        int page = getStratPage();
       // String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" + stratPage + "-" + (stratPage + 20) + ".html";//论坛
       // String luntan = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  luntan= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=12&pageNo="+page;
        return luntan;
    }
}
