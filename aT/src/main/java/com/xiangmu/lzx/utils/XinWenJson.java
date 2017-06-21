package com.xiangmu.lzx.utils;

import com.xiangmu.lzx.Modle.ProductInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class XinWenJson {
    public static XinWen_toutiao1 getdata(String data, int type){
        try {
            XinWen_toutiao1 toutiao=new XinWen_toutiao1();
            JSONObject object=new JSONObject(data);
            LogUtils.e("xinwenjsonobject", object + "");
            JSONArray array = null;
            List<ProductInfo> productInfos=null;
            switch (type){
                case XinWen_adapter.rendian:
                    array=object.getJSONArray("T1429173762551");//热点
                    break;
                case XinWen_adapter.zuixin:
                array=object.getJSONArray("T1348647853363");//最新

//                    try{
//                        //获取餐桌列表数据
//                        ProductInfoService productInfoService = new  ProductInfoService();
//
//                        productInfos= productInfoService.QueryAllProductInfo(1,20);
//
//                    } catch (Exception e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                    break;
                case XinWen_adapter.yule:
                    array=object.getJSONArray("T1348648517839");//娱乐T1348648517839
                    break;
                case XinWen_adapter.zaopin:
                    array=object.getJSONArray("T1348649079062");//招聘
                    break;
                case XinWen_adapter.qiuzhi:
                    array=object.getJSONArray("T1348648756099");//求职
                    break;
                case XinWen_adapter.chushou:
                    array=object.getJSONArray("T1348649580692");//出售
                    break;
                case XinWen_adapter.chuzu:
                    array=object.getJSONArray("T1348650593803");//出租
                    break;
                case XinWen_adapter.lishi:
                    array=object.getJSONArray("T1368497029546");//供求
                    break;
                case XinWen_adapter.ershou:
                    array=object.getJSONArray("T1356600029035");//二手
                    break;
                case XinWen_adapter.qita:
                    array=object.getJSONArray("T1348648141035");//其它
                    break;
                case XinWen_adapter.pumian:
                    array=object.getJSONArray("T1348654151579");//铺面
                    break;
                case XinWen_adapter.jiaju:
                    array=object.getJSONArray("T1348654151579");//家具
                    break;
            }
            LogUtils.e("xinwenjsonarray----", "" + array);

            List<XinWen_toutiao1.T1348647853363Entity> list=new ArrayList<>();
            for (int i=0;i<array.length();i++){

//                LogUtils.e("xinwenjsonarray",array+"");

                XinWen_toutiao1.T1348647853363Entity t1348647853363Entity=new XinWen_toutiao1.T1348647853363Entity();
                JSONObject arrayobj=array.getJSONObject(i);
                if (!arrayobj.isNull("skipID")){
                    String skipId=arrayobj.getString("skipID");
                    t1348647853363Entity.setSkipID(skipId);
                };
                if (!arrayobj.isNull("replyCount")){
                    int replyCount=arrayobj.getInt("replyCount");
                    t1348647853363Entity.setReplyCount(replyCount);
                };
                if (!arrayobj.isNull("skipType")){
                    String skiptype=arrayobj.getString("skipType");
                    t1348647853363Entity.setSkipType(skiptype);
                };
                if (!arrayobj.isNull("title")){
                    String title=arrayobj.getString("title");
                    t1348647853363Entity.setTitle(title);
                };
                if (!arrayobj.isNull("digest")){
                    String digest=arrayobj.getString("digest");
                    t1348647853363Entity.setDigest(digest);
                }
                if (!arrayobj.isNull("priority")){
                    int  priority=arrayobj.getInt("priority");
                    t1348647853363Entity.setPriority(priority);
                }
                if (!arrayobj.isNull("imgsrc")){
                    String  imgsrc=arrayobj.getString("imgsrc");
                    t1348647853363Entity.setImgsrc(imgsrc);
                }
                if (!arrayobj.isNull("url")){
                    String  url=arrayobj.getString("url");
                    t1348647853363Entity.setUrl(url);
                }

//                LogUtils.e("xinwenjsont1348647853363Entity", i + "======" + t1348647853363Entity + "");
                if (!arrayobj.isNull("imgextra")){
                    JSONArray imagetraArray=arrayobj.getJSONArray("imgextra");
                    LogUtils.e("xinwenjsonimagetraArray", imagetraArray + "");
                    List<XinWen_toutiao1.T1348647853363Entity.ImgextraEntity> listimagestra=new ArrayList<>();
                    for (int j=0;j<imagetraArray.length();j++){
                        XinWen_toutiao1.T1348647853363Entity.ImgextraEntity imgextraEntity=new XinWen_toutiao1.T1348647853363Entity.ImgextraEntity();

                        JSONObject imagestra=imagetraArray.getJSONObject(j);
                        String imagesra=imagestra.getString("imgsrc");
                        imgextraEntity.setImgsrc(imagesra);
                        listimagestra.add(imgextraEntity);
                    }
                    t1348647853363Entity.setImgextra(listimagestra);
                }


                if (!arrayobj.isNull("ads")){
                    JSONArray adsArray=arrayobj.getJSONArray("ads");
                    List<XinWen_toutiao1.T1348647853363Entity.AdsEntity> adsEntities=new ArrayList<>();
                    for (int k=0;k<adsArray.length();k++){
                        LogUtils.e("xinwenjsonads", "xinwenadsentity");
                        XinWen_toutiao1.T1348647853363Entity.AdsEntity ads=new XinWen_toutiao1.T1348647853363Entity.AdsEntity();
                        JSONObject adsobj=adsArray.getJSONObject(k);
                        String adstitle=adsobj.getString("title");
                        String adsurl=adsobj.getString("url");
                        LogUtils.e("xinwenjsonadsurl", "" + adsurl);
                        String adstag=adsobj.getString("tag");
                        String adsimgsrc=adsobj.getString("imgsrc");

                        ads.setUrl(adsurl);
                        ads.setImgsrc(adsimgsrc);
                        ads.setTitle(adstitle);
                        ads.setTag(adstag);
                        adsEntities.add(ads);
                    }
                    t1348647853363Entity.setAds(adsEntities);
                }

                list.add(t1348647853363Entity);
            }
            toutiao.setT1348647853363(list);
            LogUtils.e("xinwenjson", "========" + list.get(0).getTitle());
            for (int i=0;i<toutiao.getT1348647853363().size();i++){
                LogUtils.e("xinwenjson", i + "========" + toutiao.getT1348647853363().get(i).getTitle());
            }
            LogUtils.e("xinwenjson", "========" + toutiao.getT1348647853363());
            return toutiao;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e("xinwenjson", "=======================================================");
        return null;
    }
}