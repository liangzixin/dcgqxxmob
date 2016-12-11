package com.xiangmu.wyxw.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiangmu.wyxw.Modle.Dxfw;
import com.xiangmu.wyxw.Modle.Edu;
import com.xiangmu.wyxw.Modle.Fwcs;
import com.xiangmu.wyxw.Modle.Fzfs;
import com.xiangmu.wyxw.Modle.Gqxx;
import com.xiangmu.wyxw.Modle.ProductCategory;
import com.xiangmu.wyxw.Modle.ProductInfo;
import com.xiangmu.wyxw.Modle.Sex;
import com.xiangmu.wyxw.Modle.Zpnl;
import com.xiangmu.wyxw.Modle.Zpxx;
import com.xiangmu.wyxw.Service.ProductInfoService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class XinWenproductinfoJson {
    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
    public static XinWen_productinfo getdata(String data,int type){
        try {
            XinWen_productinfo toutiao=new XinWen_productinfo();
//            List<ProductInfo> productInfos=null;
//            productInfos =getGson().fromJson(data, new TypeToken<List< XinWen_productinfo.T18908805728Entity>>() {}.getType());
            JSONObject object=new JSONObject(data);
          //  JSONObject object= JSONObject.fromObject(data);
            LogUtils.e("xinwenjsonobject", object + "");
            JSONArray array = null;



            switch (type){
                case XinWen_adapter.rendian:
                    array=object.getJSONArray("T18908805728");//热点
                    break;
                case XinWen_adapter.zuixin:
                    array=object.getJSONArray("T18908805728");//最新

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
                    array=object.getJSONArray("T18908805728");//娱乐T1348648517839
                    break;
                case XinWen_adapter.zaopin:
                    array=object.getJSONArray("T18908805728");//招聘
                    break;
                case XinWen_adapter.qiuzhi:
                    array=object.getJSONArray("T18908805728");//求职
                    break;
                case XinWen_adapter.chushou:
                    array=object.getJSONArray("T18908805728");//出售
                    break;
                case XinWen_adapter.chuzu:
                    array=object.getJSONArray("T18908805728");//出租
                    break;
                case XinWen_adapter.gongqiu:
                    array=object.getJSONArray("T18908805728");//供求
                    break;
                case XinWen_adapter.ershou:
                    array=object.getJSONArray("T18908805728");//二手
                    break;
                case XinWen_adapter.qita:
                    array=object.getJSONArray("T18908805728");//其它
                    break;
                case XinWen_adapter.pumian:
                    array=object.getJSONArray("T18908805728");//铺面
                    break;
                case XinWen_adapter.jiaju:
                    array=object.getJSONArray("T18908805728");//家具
                    break;
            }
            LogUtils.e("xinwenjsonarray----", "" + array);

            List<XinWen_productinfo.T18908805728Entity> list=new ArrayList<>();
            for (int i=0;i<array.length();i++){

//                LogUtils.e("xinwenjsonarray",array+"");

                XinWen_productinfo.T18908805728Entity t18908805728Entity=new XinWen_productinfo.T18908805728Entity();
                JSONObject arrayobj=array.getJSONObject(i);
                if (!arrayobj.isNull("id")){
                    int skipId=arrayobj.getInt("id");
                    t18908805728Entity.setId(skipId);
                };
                if (!arrayobj.isNull("name")){
                   String replyCount=arrayobj.getString("name");
                    t18908805728Entity.setName(replyCount);
                };

                if (!arrayobj.isNull("gsmz")){
                    String replyCount=arrayobj.getString("gsmz");
                    t18908805728Entity.setGsmz(replyCount);
                };
                if (!arrayobj.isNull("gsdz")){
                    String replyCount=arrayobj.getString("gsdz");
                    t18908805728Entity.setGsdz(replyCount);
                };

                if (!arrayobj.isNull("lxr")){
                    String skiptype=arrayobj.getString("lxr");
                    t18908805728Entity.setLxr(skiptype);
                };
                if (!arrayobj.isNull("lxdh")){
                    String lxdh1=arrayobj.getString("lxdh");
                    t18908805728Entity.setLxdh(lxdh1);
                };
                if (!arrayobj.isNull("digest")){
                    String digest1=arrayobj.getString("digest");
                    t18908805728Entity.setDigest(digest1);
                };
                if (!arrayobj.isNull("createTime")){
                    String digest=arrayobj.getString("createTime");
                    t18908805728Entity.setCreateTime(digest);
                };
                if (!arrayobj.isNull("clickcount")){
                    int clickcount1=arrayobj.getInt("clickcount");
                    t18908805728Entity.setClickcount(clickcount1);
                };
                if (!arrayobj.isNull("description")){
                    String description1=arrayobj.getString("description");
                    t18908805728Entity.setDescription(description1);
                };
                if (!arrayobj.isNull("lunbo")){
                   int lunbo=arrayobj.getInt("lunbo");
                    t18908805728Entity.setLunbo(lunbo);
                };
                       if (!arrayobj.isNull("articlers")){
                    JSONArray articlerArray=arrayobj.getJSONArray("articlers");
                    LogUtils.e("xinwenjsonimagetraArray",articlerArray + "");
                    List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> listarticler=new ArrayList<>();
                    for (int j=0;j<articlerArray.length();j++){
                        XinWen_productinfo.T18908805728Entity.ProductArticlerEntity articlerEntity=new XinWen_productinfo.T18908805728Entity.ProductArticlerEntity();

                        JSONObject articlerstra=articlerArray.getJSONObject(j);
                        String articlersra=articlerstra.getString("artreview_content");
                        articlerEntity.setArtreview_content(articlersra);
                        listarticler.add(articlerEntity);
                    }
                    t18908805728Entity.setArticlers(listarticler);
                }
                if (!arrayobj.isNull("uploadFile")){
                    JSONArray uploadFileArray=arrayobj.getJSONArray("uploadFile");
                    LogUtils.e("xinwenjsonuploadFileArray", uploadFileArray + "");
//                    if(t18908805728Entity.getLunbo()==0) {
                        List<XinWen_productinfo.T18908805728Entity.UploadFileEntity> listimagestra = new ArrayList<>();
                        for (int j = 0; j < uploadFileArray.length(); j++) {
                            XinWen_productinfo.T18908805728Entity.UploadFileEntity imgextraEntity = new XinWen_productinfo.T18908805728Entity.UploadFileEntity();

                            JSONObject imagestra = uploadFileArray.getJSONObject(j);
                            String imagesra = imagestra.getString("path");
                            imgextraEntity.setTitle(t18908805728Entity.getName());
                            //   int id=imagestra.getInt("id");
                            imgextraEntity.setPath(imagesra);
                            //     imgextraEntity.setId(id);
                            listimagestra.add(imgextraEntity);
                        }

                        t18908805728Entity.setUploadFile(listimagestra);
//                    }else{
//                        List<XinWen_productinfo.T18908805728Entity.AdsEntity> adsEntityList= new ArrayList<>();
//                        for (int j = 0; j < uploadFileArray.length(); j++) {
//                            XinWen_productinfo.T18908805728Entity.AdsEntity adsEntity = new XinWen_productinfo.T18908805728Entity.AdsEntity();
//
//                            JSONObject imagestra = uploadFileArray.getJSONObject(j);
//                            adsEntity.setImgsrc( imagestra.getString("path"));
//                            adsEntity.setSubtitle("aaaaaaaaa" + j);
//                            adsEntity.setTag("photoset");
//                            adsEntity.setTitle(t18908805728Entity.getName());
//                            adsEntity.setUrl("cccccccc");
//
//                            adsEntityList.add(adsEntity);
//                        }
//
//                        t18908805728Entity.setAds(adsEntityList);
//                    }
                }
                if (!arrayobj.isNull("articlers")){
                    JSONArray articlersArray=arrayobj.getJSONArray("articlers");
                    LogUtils.e("xinwenjsonuploadFileArray",articlersArray + "");
                    List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> listArticlers=new ArrayList<>();
                    for (int j=0;j<articlersArray.length();j++){
                        XinWen_productinfo.T18908805728Entity.ProductArticlerEntity articlerEntity=new XinWen_productinfo.T18908805728Entity.ProductArticlerEntity();

                        JSONObject articler=articlersArray.getJSONObject(j);
                        String artreview_authorid=articler.getString("artreview_authorid");
                        String artreview_time=articler.getString("artreview_time");
                        String artreview_content=articler.getString("artreview_content");
                        //   int id=imagestra.getInt("id");
                        articlerEntity.setArtreview_authorid(artreview_authorid);
                        articlerEntity.setArtreview_time(artreview_time);
                        articlerEntity.setArtreview_content(artreview_content);
                        //     imgextraEntity.setId(id);
                        listArticlers.add(articlerEntity);
                    }
                    t18908805728Entity.setProductArticler(listArticlers);
                }
                if (!arrayobj.isNull("zpxx")){
                    JSONObject zpxx0=arrayobj.getJSONObject("zpxx");
                    Zpxx zpxx=new Zpxx();
                    String edu=zpxx0.getString("edurequest");
                    String sex=zpxx0.getString("sexrequest");
                    String nl=zpxx0.getString("zpnlrequest");
                    String gzdx=zpxx0.getString("gzdx");
                    String sxcy=zpxx0.getString("sxcy");
                  if(zpxx0.getString("qjnl")!=null)  {
                      int qjnl=zpxx0.getInt("qjnl");
                      zpxx.setQjnl(qjnl);
                  }
                    zpxx.setEdurequest(Enum.valueOf(Edu.class, edu));
                    zpxx.setSexrequest(Enum.valueOf(Sex.class, sex));
                    zpxx.setZpnlrequest(Enum.valueOf(Zpnl.class, nl));
                    zpxx.setGzdx(Enum.valueOf(Dxfw.class,gzdx));
                    zpxx.setSxcy(sxcy);

                    t18908805728Entity.setZpxx(zpxx);

                }
                if (!arrayobj.isNull("fwcs")){
                    JSONObject fwcs0=arrayobj.getJSONObject("fwcs");
                    Fwcs fwcs=new Fwcs();
                    int  fwcj=fwcs0.getInt("fwcj");
                    int  fwlj=fwcs0.getInt("fwlj");
                    int  fws=fwcs0.getInt("fws");
                    int  fwt=fwcs0.getInt("fwt");
                    int  fww=fwcs0.getInt("fww");
                    int fwzf=fwcs0.getInt("fwzf");
                    double  fwzj=fwcs0.getDouble("fwzj");
                    int  fwzs=fwcs0.getInt("fwzs");
                    String fzfsrequest=fwcs0.getString("fzfsrequest");
                    double jzmj=fwcs0.getDouble("jzmj");
                   fwcs.setFwcj(fwcj);
                    fwcs.setFwlj(fwlj);
                    fwcs.setFws(fws);
                    fwcs.setFwt(fwt);
                    fwcs.setFww(fww);
                    fwcs.setFwzf(fwzf);
                    fwcs.setFwzs(fwzs);
                    fwcs.setFwzj((float)fwzj);
                    fwcs.setJzmj((float)jzmj);
                    fwcs.setFzfsrequest(Enum.valueOf(Fzfs.class, fzfsrequest));
                    t18908805728Entity.setFwcs(fwcs);
                }
                if (!arrayobj.isNull("gqxx")){
                    JSONObject gqxx0=arrayobj.getJSONObject("gqxx");
                    Gqxx gqxx=new Gqxx();
                    int  gqsl=gqxx0.getInt("gqsl");
                  gqxx.setGqsl(gqsl);
                    t18908805728Entity.setGqxx(gqxx);
                }
                if (!arrayobj.isNull("category")){
                    JSONObject category0=arrayobj.getJSONObject("category");
                    ProductCategory category=new  ProductCategory();
                    int categoryid=category0.getInt("id");
                    String name=category0.getString("name");
                    category.setId(categoryid);
                    category.setName(name);
                    t18908805728Entity.setProductcategory(category);

                }
//                if (!arrayobj.isNull("priority")){
//                    int  priority=arrayobj.getInt("priority");
//                    t18908805728Entity.setPriority(priority);
//                }
//                if (!arrayobj.isNull("imgsrc")){
//                    String  imgsrc=arrayobj.getString("imgsrc");
//                    t18908805728Entity.setImgsrc(imgsrc);
//                }
//                if (!arrayobj.isNull("url")){
//                    String  url=arrayobj.getString("url");
//                    t18908805728Entity.setUrl(url);
//                }

//                LogUtils.e("xinwenjsont18908805728Entity", i + "======" + t18908805728Entity + "");
//                if (!arrayobj.isNull("imgextra")){
//                    JSONArray imagetraArray=arrayobj.getJSONArray("imgextra");
//                    LogUtils.e("xinwenjsonimagetraArray", imagetraArray + "");
//                    List<XinWen_productinfo.T18908805728Entity.ImgextraEntity> listimagestra=new ArrayList<>();
//                    for (int j=0;j<imagetraArray.length();j++){
//                        XinWen_productinfo.T18908805728Entity.ImgextraEntity imgextraEntity=new XinWen_productinfo.T18908805728Entity.ImgextraEntity();
//
//                        JSONObject imagestra=imagetraArray.getJSONObject(j);
//                        String imagesra=imagestra.getString("imgsrc");
//                        imgextraEntity.setImgsrc(imagesra);
//                        listimagestra.add(imgextraEntity);
//                    }
//                    t18908805728Entity.setImgextra(listimagestra);
//                }
//
//
//                if (!arrayobj.isNull("ads")){
//                    JSONArray adsArray=arrayobj.getJSONArray("ads");
//                    List<XinWen_productinfo.T18908805728Entity.AdsEntity> adsEntities=new ArrayList<>();
//                    for (int k=0;k<adsArray.length();k++){
//                        LogUtils.e("xinwenjsonads", "xinwenadsentity");
//                        XinWen_productinfo.T18908805728Entity.AdsEntity ads=new XinWen_productinfo.T18908805728Entity.AdsEntity();
//                        JSONObject adsobj=adsArray.getJSONObject(k);
//                        String adstitle=adsobj.getString("title");
//                        String adsurl=adsobj.getString("url");
//                        LogUtils.e("xinwenjsonadsurl", "" + adsurl);
//                        String adstag=adsobj.getString("tag");
//                        String adsimgsrc=adsobj.getString("imgsrc");
//
//                        ads.setUrl(adsurl);
//                        ads.setImgsrc(adsimgsrc);
//                        ads.setTitle(adstitle);
//                        ads.setTag(adstag);
//                        adsEntities.add(ads);
//                    }
//                    t18908805728Entity.setAds(adsEntities);
//                }

                list.add(t18908805728Entity);
            }
            toutiao.setT18908805728(list);
            LogUtils.e("xinwenjson", "========" + list.get(0).getName());
            for (int i=0;i<toutiao.getT18908805728().size();i++){
                LogUtils.e("xinwenjson", i + "========" + toutiao.getT18908805728().get(i).getName());
            }
            LogUtils.e("xinwenjson", "========" + toutiao.getT18908805728());
            return toutiao;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e("xinwenjson", "=======================================================");
        return null;
    }
}
