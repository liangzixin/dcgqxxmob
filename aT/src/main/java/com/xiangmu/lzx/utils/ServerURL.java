package com.xiangmu.lzx.utils;

/**
 * Created by Administrator on 2015/11/9.
 */
public interface ServerURL {
    //阅读相关
//    String yueDuURL = "http://c.3g.163.com/recommend/getSubDocPic?passport=e4f8256803f484e72150fcfc59e1bd82@tencent.163.com&devId=862949023308336&size=20&version=5.3.6&from=yuedu";
//    String yueDuURLJiaZai = "http://c.3g.163.com/recommend/getSubDocNews?passport=e4f8256803f484e72150fcfc59e1bd82@tencent.163.com&devId=862949023308336&size=20&version=5.3.6&from=yuedu";
    String yueDuURL = "http://c.3g.163.com/recommend/getSubDocPic?passport=&devId=862949023308336&size=10&version=5.3.6&from=yuedu&net=wifi";
    String yueDuURLJiaZai = "http://c.3g.163.com/recommend/getSubDocNews?passport=&devId=862949023308336&size=10&version=5.3.6&from=yuedu&net=wifi";
    String yueDuDetial ="http://c.3g.163.com/nc/article/";//前缀
    String yueDuHouzui = "/full.html";//后缀

    String weatherUrl = "http://api.map.baidu.com/telematics/v3/weather?location=东川&output=json&ak=mXBIDrvTOwwmYaTtN03Lo0j2"; //天气

    //上头条相关
    String TopUrl = "http://c.3g.163.com/nc/userhead/curopTopic/";
    String HuiGuUrl = "http://c.3g.163.com/nc/userhead/optopicList/";
    String HuiGuDetialUrl = "http://c.3g.163.com/nc/userhead/optopic/";//前缀; 格式:http://c.3g.163.com/nc/userhead/optopic/150/0-20.html

    //搜索相关
//    String tuiJianWord = "http://c.3g.163.com/nc/search/hotWord.html";//热词推荐
    String tuiJianWord = HttpUtil.BASE_URL+"tuijianAction!selecttuijian";//二手
    String searchUrl1 = "http://c.3g.163.com/search/comp/MA%3D%3D/30/";
    String searchUrl2 = ".html?deviceId=ODYyOTQ5MDIzMzA4MzM2&version=bmV3c2NsaWVudC41LjMuNi5hbmRyb2lk&channel=VDEzNDg2NDc5MDkxMDc%3D";
    String searchUrl3 = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?pageNo=0&searchWord=";//查询发布信息
    String searchUrl4 = HttpUtil.BASE_URL+"product!QueryAllProductarticler.action?pageNo=0&searchWord=";//查询留言信息
    String searchUrl5 = HttpUtil.BASE_URL+"customerAction!QueryAllCustomer.action?pageNo=0&searchWord=";//查询注册信息
    String filterUrl= HttpUtil.BASE_URL+"filter!QueryAllFilter.action?pageNo=0&searchword=";//查询信息
    //视听相关
    String shiTingUrl = "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-10.html";
}
