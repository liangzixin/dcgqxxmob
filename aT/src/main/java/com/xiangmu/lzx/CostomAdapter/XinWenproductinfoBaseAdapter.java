package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_adapter;
import com.xiangmu.lzx.utils.XinWen_productinfo;
import com.xiangmu.lzx.utils.XutilsGetData;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class XinWenproductinfoBaseAdapter extends BaseAdapter {
    //头条数据的listview的adapter
    private List<XinWen_productinfo.T18908805728Entity> toutiao_list;
    // private List<XinWen_productinfo.T18908805728Entity.UploadFileEntity> uploadFile_list;
    private Context context;
    private DateTime dateTime = new DateTime();

    public XinWenproductinfoBaseAdapter(Context context, List<XinWen_productinfo.T18908805728Entity> toutiao_list) {
        this.context = context;
        this.toutiao_list = toutiao_list;
    }

    public List<XinWen_productinfo.T18908805728Entity> getToutiao_list() {
        return toutiao_list;
    }

    public void setToutiao_list(List<XinWen_productinfo.T18908805728Entity> toutiao_list) {
        this.toutiao_list = toutiao_list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return toutiao_list.size();
    }

    @Override
    public Object getItem(int i) {
        return toutiao_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    //重写系统方法
    @Override
    public int getItemViewType(int position) {
        //String skipType = toutiao_list.get(position).getProductcategory().getId()+"";
//        String skipType ="0";
//        int type = XinWen_adapter.getType(skipType);
        int type = toutiao_list.get(position).getLanmu();
        return type;
    }

    //重写方法返回4中类型
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XinWenXiData data = new XinWenXiData();//设置跳转详细页面数据类
        int type = getItemViewType(i);
        Viewholder putong = null;
        ViewholderZT zhuanti = null;
        ViewholderZB zhibo = null;
        ViewholderImage duotu = null;
        if (view == null) {
            switch (type) {
                case XinWen_adapter.TYPE_putong:
                    LogUtils.e("skiptype", "TYPE_putong");
                    putong = new Viewholder();
                    view = View.inflate(context, R.layout.xinwen_toutiao_item_putong, null);
                    putong.toutiaop_imagesrc = (ImageView) view.findViewById(R.id.toutiaop_imgsrc);
                    putong.toutiaop_title = (TextView) view.findViewById(R.id.toutiaop_title);
                    putong.toutiaop_replaycount = (TextView) view.findViewById(R.id.toutiaop_replaycount);
                    putong.toutiaop_digest = (TextView) view.findViewById(R.id.toutiaop_digest);
                    putong.toutiaop_createDate = (TextView) view.findViewById(R.id.toutiaop_createDate);
                    putong.toutiaop_articler = (TextView) view.findViewById(R.id.toutiaop_articler);
                    view.setTag(putong);
                    break;
                case XinWen_adapter.TYPE_zhuanti:
                    LogUtils.e("skiptype", "TYPE_zhuanti");
                    zhuanti = new ViewholderZT();
                    view = View.inflate(context, R.layout.xinwen_toutiao_item_zhuanti, null);
                    zhuanti.toutiaozt_imagesrc = (ImageView) view.findViewById(R.id.toutiaozt_imgsrc);
                    zhuanti.toutiaozt_title = (TextView) view.findViewById(R.id.toutiaozt_title);
                    zhuanti.toutiaozt_digest = (TextView) view.findViewById(R.id.toutiaozt_digest);
                    view.setTag(zhuanti);
                    break;
                case XinWen_adapter.TYPE_zhibo:
                    LogUtils.e("skiptype", "TYPE_zhibo");
                    zhibo = new ViewholderZB();
                    view = View.inflate(context, R.layout.xinwen_toutiao_item_zhibo, null);
                    zhibo.toutiaozb_imagesrc = (ImageView) view.findViewById(R.id.toutiaozb_imgsrc);
                    zhibo.toutiaozb_title = (TextView) view.findViewById(R.id.toutiaozb_title);
                    zhibo.toutiaozb_digest = (TextView) view.findViewById(R.id.toutiaozb_digest);
                    view.setTag(zhibo);
                    break;
                case XinWen_adapter.type_duotu:
                    LogUtils.e("skiptype", "type_duotu");
                    duotu = new ViewholderImage();
                    view = View.inflate(context, R.layout.xinwen_toutiao_item_duotu, null);
                    duotu.toutiaodt_replaycount = (TextView) view.findViewById(R.id.toutiaodt_replaycount);
                    duotu.toutiaodt_articler = (TextView) view.findViewById(R.id.toutiaodt_articler);
                    duotu.toutiaodt_createDate = (TextView) view.findViewById(R.id.toutiaodt_createDate);
                    duotu.toutiaodt_title = (TextView) view.findViewById(R.id.toutiaodt_title);
                    duotu.toutiaodt_imagesrc1 = (ImageView) view.findViewById(R.id.toutiaodt_imgsrc1);
                    duotu.toutiaodt_imagesrc2 = (ImageView) view.findViewById(R.id.toutiaodt_imgsrc2);
                    duotu.toutiaodt_imagesrc3 = (ImageView) view.findViewById(R.id.toutiaodt_imgsrc3);
                    view.setTag(duotu);
                    break;
            }
        } else {
            switch (type) {
                case XinWen_adapter.TYPE_putong:
                    LogUtils.e("skiptype", "elseTYPE_putong" + view.getTag());
                    putong = (Viewholder) view.getTag();
                    break;
                case XinWen_adapter.TYPE_zhuanti:
                    zhuanti = (ViewholderZT) view.getTag();
                    break;
                case XinWen_adapter.TYPE_zhibo:
                    zhibo = (ViewholderZB) view.getTag();
                    break;
                case XinWen_adapter.type_duotu:
                    duotu = (ViewholderImage) view.getTag();
                    break;
            }
        }
        switch (type) {
            case XinWen_adapter.TYPE_putong:
                putong.toutiaop_title.setText(toutiao_list.get(i).getName() + "");
                putong.toutiaop_replaycount.setText(toutiao_list.get(i).getClickcount() + "");
                putong.toutiaop_digest.setText(toutiao_list.get(i).getDigest() + "");
                putong.toutiaop_createDate.setText(dateTime.getMonthday(toutiao_list.get(i).getCreateTime()) + "");
                //    System.out.println(toutiao_list.get(i).getName()+toutiao_list.get(i).getArticlers().size()+"");
                putong.toutiaop_articler.setText(toutiao_list.get(i).getArticlers().size() + "");
                //   putong.toutiaop_createDate.setText("05/09"+ "");

                if((toutiao_list.get(i).getUploadFile().size()>0)) {
                    XutilsGetData.xUtilsImageiv(putong.toutiaop_imagesrc, "http://www.dcgqxx.com/upload/" + toutiao_list.get(i).getUploadFile().get(0).getPath(), context, false);
                    //       XutilsGetData.xUtilsImageiv(putong.toutiaop_imagesrc, toutiao_list.get(i).getUploadFile().get(i).getPath(), context,false);
                } else {
                    XutilsGetData.xUtilsImageiv(putong.toutiaop_imagesrc, "http://www.dcgqxx.com/upload/dcgqxxbook.jpg", context, false);

                }
                break;
            case XinWen_adapter.TYPE_zhuanti:
                zhuanti.toutiaozt_title.setText(toutiao_list.get(i).getName() + "");
                zhuanti.toutiaozt_digest.setText(toutiao_list.get(i).getDigest() + "");
                //   XutilsGetData.xUtilsImageiv(zhuanti.toutiaozt_imagesrc, toutiao_list.get(i).getImgsrc(), context,false);
                break;
            case XinWen_adapter.TYPE_zhibo:
                zhibo.toutiaozb_title.setText(toutiao_list.get(i).getName() + "");
                zhibo.toutiaozb_digest.setText(toutiao_list.get(i).getDigest() + "");
                //    XutilsGetData.xUtilsImageiv(zhibo.toutiaozb_imagesrc, toutiao_list.get(i).getImgsrc(), context,false);
                break;
            case XinWen_adapter.type_duotu:
                duotu.toutiaodt_title.setText(toutiao_list.get(i).getName() + "");
                duotu.toutiaodt_articler.setText(toutiao_list.get(i).getProductArticler().size() + "");
//                duotu.toutiaodt_articler.setText("5");
                duotu.toutiaodt_replaycount.setText(toutiao_list.get(i).getClickcount() + "");

                duotu.toutiaodt_createDate.setText(dateTime.getMonthday(toutiao_list.get(i).getCreateTime()) + "");


                if (toutiao_list.get(i).getUploadFile().size() > 0)
                    XutilsGetData.xUtilsImageiv(duotu.toutiaodt_imagesrc1, "http://www.dcgqxx.com/upload/" + toutiao_list.get(i).getUploadFile().get(0).getPath(), context, false);
                if (toutiao_list.get(i).getUploadFile().size() > 1)
                    XutilsGetData.xUtilsImageiv(duotu.toutiaodt_imagesrc2, "http://www.dcgqxx.com/upload/" + toutiao_list.get(i).getUploadFile().get(1).getPath(), context, false);
                if (toutiao_list.get(i).getUploadFile().size() > 2)
                    XutilsGetData.xUtilsImageiv(duotu.toutiaodt_imagesrc3, "http://www.dcgqxx.com/upload/" + toutiao_list.get(i).getUploadFile().get(2).getPath(), context, false);
                //    List<XinWen_productinfo.T18908805728Entity.ImgextraEntity> imagextralist=toutiao_list.get(i).getImgextra();
//                if (imagextralist!=null){
//                    XutilsGetData.xUtilsImageiv(duotu.toutiaodt_imagesrc2, imagextralist.get(0).getImgsrc(), context,false);
//                    XutilsGetData.xUtilsImageiv(duotu.toutiaodt_imagesrc3, imagextralist.get(1).getImgsrc(), context,false);
//                }
                break;
        }
        return view;
    }

    //普通条目布局的viewholder
    public class Viewholder {
        ImageView toutiaop_imagesrc;
        TextView toutiaop_title;
        TextView toutiaop_digest;
        TextView toutiaop_replaycount;
        TextView toutiaop_createDate;
        TextView toutiaop_articler;
    }

    public class ViewholderZT {
        ImageView toutiaozt_imagesrc;
        TextView toutiaozt_title;
        TextView toutiaozt_digest;
    }

    public class ViewholderZB {
        ImageView toutiaozb_imagesrc;
        TextView toutiaozb_title;
        TextView toutiaozb_digest;
    }

    public class ViewholderImage {
        TextView toutiaodt_title;
        TextView toutiaodt_articler;
        ImageView toutiaodt_imagesrc1;
        ImageView toutiaodt_imagesrc2;
        ImageView toutiaodt_imagesrc3;
        TextView toutiaodt_createDate;
        TextView toutiaodt_replaycount;
    }


    }
