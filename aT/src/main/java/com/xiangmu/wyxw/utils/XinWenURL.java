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

  String redian = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=0&intFirst=1&recPerPage=20";
 String toutiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=0&intFirst=1&recPerPage=20";
    //  String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + stratPage + "-" + (stratPage + 20) + ".html";//头条
    String yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=1&intFirst=1&recPerPage=20";
  //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
    //String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
    String tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=2&intFirst=1&recPerPage=20";
    //String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
    String chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=3&intFirst=1&recPerPage=20";
    //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
    String keji = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=4&intFirst=1&recPerPage=20";
   // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
    String qiche = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=5&intFirst=1&recPerPage=20";
    //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
    String shishang = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=6&intFirst=1&recPerPage=20";
    //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
    String junshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=7&intFirst=1&recPerPage=20";
  //  String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
    String lishi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=8&intFirst=1&recPerPage=20";
    //String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
    String caipiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=9&intFirst=1&recPerPage=20";
    //String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" + stratPage + "-" + (stratPage + 20) + ".html";//游戏
    String youxi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=10&intFirst=1&recPerPage=20";
    //String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" + stratPage + "-" + (stratPage + 20) + ".html";//影视
    String yingshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=11&intFirst=1&recPerPage=20";
    //String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" + stratPage + "-" + (stratPage + 20) + ".html";//论坛
    String luntan = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?category=12&intFirst=1&recPerPage=20";
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
        String toutiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=0&intFirst="+page+"&recPerPage="+(page+20);
        return toutiao;
    }

    public String getYule() {
        int page = getStratPage();
      //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
      //  String yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=1&intFirst="+page+"&recPerPage="+(page+20);
        return yule;
    }

    public String getTiyu() {
        int page = getStratPage();
       // String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
      //  String tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=2&intFirst="+page+"&recPerPage="+(page+20);
        return tiyu;
    }

    public String getChaijing() {
        int page = getStratPage();
       // String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
        //String chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=3&intFirst="+page+"&recPerPage="+(page+20);
        return chaijing;
    }

    public String getKeji() {
        int page = getStratPage();
        //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
      //  String keji = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  keji= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=4&intFirst="+page+"&recPerPage="+(page+20);
        return keji;
    }

    public String getQiche() {
        int page = getStratPage();
       // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
       // String qiche = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  qiche= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=5&intFirst="+page+"&recPerPage="+(page+20);
        return qiche;
    }

    public String getShishang() {
        int page = getStratPage();
        //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
      //  String shishang = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  shishang= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=6&intFirst="+page+"&recPerPage="+(page+20);
        return shishang;
    }

    public String getJunshi() {
        int page = getStratPage();
        //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
     //   String junshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  junshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=7&intFirst="+page+"&recPerPage="+(page+20);
        return junshi;
    }

    public String getLishi() {
        int page = getStratPage();
       // String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
        //String lishi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  lishi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=8&intFirst="+page+"&recPerPage="+(page+20);
        return lishi;
    }

    public String getCaipiao() {
        int page = getStratPage();
       // String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
      //  String caipiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  caipiao= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=9&intFirst="+page+"&recPerPage="+(page+20);
        return caipiao;
    }

    public String getYouxi() {
        int page = getStratPage();
       // String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" + stratPage + "-" + (stratPage + 20) + ".html";//游戏
      //  String youxi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  youshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=10&intFirst="+page+"&recPerPage="+(page+20);
        return youxi;
    }

    public String getYingshi() {
        int page = getStratPage();
      //  String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" + stratPage + "-" + (stratPage + 20) + ".html";//影视
       // String yingshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  yingshi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=11&intFirst="+page+"&recPerPage="+(page+20);
        return yingshi;
    }

    public String getLuntan() {
        int page = getStratPage();
       // String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" + stratPage + "-" + (stratPage + 20) + ".html";//论坛
       // String luntan = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst=1&recPerPage=20";
        String  luntan= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=12&intFirst="+page+"&recPerPage="+(page+20);
        return luntan;
    }
}
