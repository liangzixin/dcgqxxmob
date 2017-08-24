package com.xiangmu.lzx.utils;

import com.xiangmu.lzx.Modle.ProductInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class XinWenURL {
    public int stratPage = 1;
    List<ProductInfo> productInfos=null;
    String Count=HttpUtil.BASE_URL+"count!updateCount1.action";
    int page = getStratPage();

    public String getGetDateCount() {
        return getDateCount;
    }

    public void setGetDateCount(String getDateCount) {
        this.getDateCount = getDateCount;
    }

    String getDateCount=HttpUtil.BASE_URL+"count!getDateCount.action";
    String clickcount=HttpUtil.BASE_URL+"product!UpdateClickcount.action?ID=";

    public String getClickdel() {
        return clickdel;
    }

    public void setClickdel(String clickdel) {
        this.clickdel = clickdel;
    }

    String clickdel=HttpUtil.BASE_URL+"product!Clickdel.action?id=";

    public String getRepareFilterMob() {
        return repareFilterMob;
    }

    public void setRepareFilterMob(String repareFilterMob) {
        this.repareFilterMob = repareFilterMob;
    }

    String repareFilterMob=HttpUtil.BASE_URL+"filter!repareMob.action?id=";

    String savearticler=HttpUtil.BASE_URL+"product!savearticlermob.action?productid=";
    String addcustmer=HttpUtil.BASE_URL+"login!addcustmermob.action?openid=";
    public String getAddcustmer() {
        return addcustmer;
    }

    public void setAddcustmer(String addcustmer) {
        this.addcustmer = addcustmer;
    }



    public String getSaveproductinfo() {
        return saveproductinfo;
    }

    public void setSaveproductinfo(String saveproductinfo) {
        this.saveproductinfo = saveproductinfo;
    }

    String saveproductinfo=HttpUtil.BASE_URL+"product!saveproductinfomob.action";

    //热点
   // String redian="http://c.3g.163.com/nc/article/list/T1429173762551/0-20.html";

  String redian = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=0&pageNo=0";
 String zuixin = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=0&pageNo=0";//最新

    String filter=HttpUtil.BASE_URL+"filter!QueryAllFilter.action?pageNo="+page;//过滤词语
    //  String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + stratPage + "-" + (stratPage + 20) + ".html";//头条
    String zaopin = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=1&pageNo=0";//招聘
  //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
    //String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
    String qiuzhi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=2&pageNo=0";//求职
    //String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
    String chushou = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=3&pageNo=0";//出售
    //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
    String chuzu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=4&pageNo=0";//出租
   // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
    String gongqiu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=5&pageNo=0";//供求
    //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
    String ershou = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=6&pageNo=0";//二手
    //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
    String qita = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=7&pageNo=0";//其它
  //  String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
    String pumian = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=8&pageNo=0";//铺面
    //String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
    String jiaju = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=9&pageNo=0";//家具
    //String youxi = "http://c.m.163.com/nc/article/list/T1348654151579/" + stratPage + "-" + (stratPage + 20) + ".html";//游戏
    String youxi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=10&pageNo=0";//游戏
    //String yingshi = "http://c.m.163.com/nc/article/list/T1348648650048/" + stratPage + "-" + (stratPage + 20) + ".html";//影视
    String yingshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=11&pageNo=0";//影视
    //String luntan = "http://c.m.163.com/nc/article/list/T1419386592923/" + stratPage + "-" + (stratPage + 20) + ".html";//论坛
    String luntan = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=12&pageNo=0";//论坛


    public String getSavearticler() {
        return savearticler;
    }

    public void setSavearticler(String savearticler) {
        this.savearticler = savearticler;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
    public String getClickcount() {
        return clickcount;
    }

    public void setClickcount(String clickcount) {
        this.clickcount = clickcount;
    }
    public int getStratPage() {
        return stratPage;
    }
    public void setStratPage(int stratPage) {
        this.stratPage = stratPage;
    }

    public String getRedian() {
        return redian;
    }

    public String getZuixin(int categoryid) {

        String zuixin=HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid="+categoryid+"&pageNo="+page;//最新
        return zuixin;
    }
    public String getFilter(int page) {
        return filter;
    }

    public String getZuixin0() {
        int page = getStratPage();
        //String toutiao = "http://c.m.163.com/nc/article/headline/T1348647853363/" + page + "-" + (page + 20) + ".html";//头条

        String zuixin0 = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=0&lunbo0=true&pageNo="+page;//最新
        return zuixin0;
    }
    public String getZaopin() {
        int page = getStratPage();
      //  String yule = "http://c.m.163.com/nc/article/list/T1348648517839/" + stratPage + "-" + (stratPage + 20) + ".html";//娱乐
      //  String yule = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  zaopin= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=1&pageNo="+page;//招聘
        return zaopin;
    }

    public String getQiuzhi() {
        int page = getStratPage();
       // String tiyu = "http://c.m.163.com/nc/article/list/T1348649079062/" + stratPage + "-" + (stratPage + 20) + ".html";//体育
      //  String tiyu = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  qiuzhi= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=2&pageNo="+page;//求职
        return qiuzhi;
    }

    public String getChushou() {
        int page = getStratPage();
       // String chaijing = "http://c.m.163.com/nc/article/list/T1348648756099/" + stratPage + "-" + (stratPage + 20) + ".html";//财经
        //String chaijing = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  chushou = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=3&pageNo="+page;//出售
        return chushou;
    }

    public String getChuzu() {
        int page = getStratPage();
        //String keji = "http://c.m.163.com/nc/article/list/T1348649580692/" + stratPage + "-" + (stratPage + 20) + ".html";//科技
      //  String keji = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  chuzu= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=4&pageNo="+page;//出租
        return chuzu;
    }

    public String getGongqiu() {
        int page = getStratPage();
       // String qiche = "http://c.m.163.com/nc/article/list/T1348654060988/" + stratPage + "-" + (stratPage + 20) + ".html";//汽车
       // String qiche = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  gongqiu= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=5&pageNo="+page;//供求
        return gongqiu;
    }

    public String getErshou() {
        int page = getStratPage();
        //String shishang = "http://c.m.163.com/nc/article/list/T1348650593803/" + stratPage + "-" + (stratPage + 20) + ".html";//时尚
      //  String shishang = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  ershou= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=6&pageNo="+page;//二手
        return ershou;
    }

    public String getQita() {
        int page = getStratPage();
        //String junshi = "http://c.m.163.com/nc/article/list/T1348648141035/" + stratPage + "-" + (stratPage + 20) + ".html";//军事
     //   String junshi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  qita= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=7&pageNo="+page;//其它
        return qita;
    }

    public String getPumian() {
        int page = getStratPage();
       // String lishi = "http://c.m.163.com/nc/article/list/T1368497029546/" + stratPage + "-" + (stratPage + 20) + ".html";//历史
        //String lishi = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  pumian= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=8&pageNo="+page;//铺面
        return pumian;
    }

    public String getJiaju() {
        int page = getStratPage();
       // String caipiao = "http://c.m.163.com/nc/article/list/T1356600029035/" + stratPage + "-" + (stratPage + 20) + ".html";//彩票
      //  String caipiao = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0";
        String  jiaju= HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?categoryid=9&pageNo="+page;//家具
        return jiaju;
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
