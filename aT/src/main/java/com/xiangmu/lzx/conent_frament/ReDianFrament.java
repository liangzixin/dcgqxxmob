package com.xiangmu.lzx.conent_frament;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

//import com.xiangmu.wyxw.CostomAdapter.XinWenBaseAdapter;
import com.xiangmu.lzx.CostomAdapter.XinWenproductinfoBaseAdapter;

import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.activitys.WebProductinfoViewActivity;
import com.xiangmu.lzx.activitys.WebViewActivity;
import com.xiangmu.lzx.activitys.XinWenXiActivity;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshBase;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshListView;
import com.xiangmu.lzx.utils.CommonUtil;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXi;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_adapter;
import com.xiangmu.lzx.utils.XinWen_productinfo;
import com.xiangmu.lzx.utils.XinWenproductinfoJson;
import com.xiangmu.lzx.utils.XutilsGetData;
import com.xiangmu.lzx.viewpager.TouTiaoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class ReDianFrament extends Fragment {
    public  String url;
    private XinWenproductinfoBaseAdapter toutiao_adapter;
    private int daohangtype;
    private XinWenXiData xinWenXi;
    private   ArrayList<UploadFile> potolist;
    private  List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> liuyuenlist;
    private boolean search=false;
    List<XinWen_productinfo.T18908805728Entity.UploadFileEntity> listads;//字段listads
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daohangtype = XinWen_adapter.getXinWenType("热点");//获得栏目的类型
        url = new XinWenURL().getRedian();
    }

    View contentview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            if (contentview == null) {
                contentview = initview(inflater);
            }
            return contentview;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private PullToRefreshListView toutiao_lv;
    LinearLayout titlebar;
    TextView bartextView;

    //初始化控件
    private View initview(LayoutInflater inflater) {
        final View view = inflater.inflate(R.layout.redian_view, null, false);
        titlebar= (LinearLayout) view.findViewById(R.id.redian_title_bar);
        toutiao_lv = (PullToRefreshListView) view.findViewById(R.id.xinwen_toutiao_lv);
        bartextView=(TextView)view.findViewById(R.id.txt_title);

//        toutiao_adapter = new XinWenBaseAdapter(getActivity(), toutiao_list);
//        toutiao_lv.getRefreshableView().setAdapter(toutiao_adapter);
        getdata(url, true,search);

        toutiao_lv.setPullLoadEnabled(false);  //上拉加载，屏蔽
//        toutiao_lv.setPullLoadEnabled(true);
        toutiao_lv.setScrollLoadEnabled(false); //设置滚动加载可用
        //设置上拉下拉的监听事件
        toutiao_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isScroll=false;//设置为不可以滚动获得控件高度
                //下拉刷新，重新获取数据，填充listview
                getdata(url, true,search);//刷新数据
                String stringDate = CommonUtil.getStringDate();// 下拉刷新时获取当前的刷新时间
                toutiao_lv.setLastUpdatedLabel(stringDate);//将时间添加到刷新的表头
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });

        return view;
    }

    private XutilsGetData xutilsGetData = new XutilsGetData();

    //网络请求获得数据 refresh   true为刷新数据  false为加载数据  存储根据url保存数据
    public void getdata(String url, final boolean refresh, final boolean search) {

        if (CommonUtil.isNetWork(getActivity())){
            //然后网络请求刷新数据
            xutilsGetData.xUtilsHttp(getActivity(), url, new XutilsGetData.CallBackHttp() {
                @Override
                public void handleData(String data) {
                    LogUtils.e("xinwenactivity==data==", data + "");
                    getshowdata(data, refresh,search);

                }
            },true);
        }else {
            String data = xutilsGetData.getData(getActivity(), url, null);
            //判断本地数据是否存在  如果没有网络请求
            if (data != null) {
                getshowdata(data, refresh,search);
            }
        }

    }

    List<XinWen_productinfo.T18908805728Entity> toutiao_list = new ArrayList<>();//原始数据
    List<XinWen_productinfo.T18908805728Entity> toutiao_list2 = new ArrayList<>();//多图
    List<XinWen_productinfo.T18908805728Entity> toutiao_list0 = new ArrayList<>();//普通
    boolean isrefresh=true;
    TextView footview;
    View headview;
    int height;
    boolean isScroll;
    List<XinWenXi.PhotosObj> potolist0 = new ArrayList<>();
    // 显示数据
   public void getshowdata(String data, boolean refresh,boolean search) {

        toutiao_list.clear();
        XinWen_productinfo toutiao_object = XinWenproductinfoJson.getdata(data, daohangtype);//传入类型和数据
        LogUtils.e("toutiao_object", "" + toutiao_object);
        toutiao_list.addAll(toutiao_object.getT18908805728());

        if (footview == null) {
            footview = new TextView(getActivity());

             footview.setText("亲,到最后了.....");

            footview.setTextSize(20);
            footview.setWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth());
            footview.setGravity(Gravity.CENTER);
            toutiao_lv.getRefreshableView().addFooterView(footview);
        }
       if (search){
           bartextView.setText("搜索结果");
           if (headview!= null)  toutiao_lv.getRefreshableView().removeHeaderView(headview);

       }else {
           bartextView.setText("24小时热点");

           if (headview == null) {
               headview = View.inflate(getActivity(), R.layout.redian_listviewhead, null);
               //获得控件的宽高
               int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
               int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
               headview.measure(w, h);
               height = headview.getMeasuredHeight();
               int width = headview.getMeasuredWidth();

               toutiao_lv.getRefreshableView().addHeaderView(headview);
           }else {
               headview.setVisibility(View.VISIBLE);
           }
       }
        //点击listview调用的方法
        toutiao_lv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0||(position == toutiao_list.size()-1)){
                    return;
                }
                frament2activity(position);//跳转轮播详细页面
            }
        });
       View viewlunbo = null;
       if (isrefresh&&showLunbo()!=null) {

           viewlunbo = showLunbo();//刷新轮播
//            toutiao_lv.getRefreshableView().addHeaderView(showLunbo());//增加轮播
           isrefresh=false;
       }
        toutiao_adapter = new XinWenproductinfoBaseAdapter(getActivity(), toutiao_list);
        toutiao_lv.getRefreshableView().setAdapter(toutiao_adapter);
        toutiao_adapter.setToutiao_list(toutiao_list);//填充并刷新数据
        //设置listview的滑动监听
        toutiao_lv.getRefreshableView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }

        });
        isScroll=true;
        toutiao_lv.onPullDownRefreshComplete();//隐藏下拉头
    }

    private void frament2activity(int position) {
        int pos;
        xinWenXi =new XinWenXiData();
        XinWen_productinfo.T18908805728Entity toutiao_listview= new XinWen_productinfo.T18908805728Entity();//获取记录
        if (listads.size()==0) {//判断有没有轮播图如果没有就不添加轮播布局  布局就和原来一样
            pos = position;
            toutiao_listview=toutiao_list.get(pos);
        } else {
          //  pos = position - 1;//有轮播图的时候点listview第二条才是数据list集合中的第一条
            toutiao_listview=toutiao_list.get(position);
        }
        LogUtils.e("xinwenadapter", "postion==" + position);
        //    int bujutype = XinWen_adapter.getType(toutiao_list.get(pos).getId());
        int bujutype =0;
        LogUtils.e("xinwenadapter", "type==" + bujutype);

        //传入详细页面的数据
        potolist =new ArrayList<>();
        liuyuenlist =new ArrayList<>();
        //List<PhotoImage> potolist2 = new ArrayList<>();

        xinWenXi.setId(toutiao_listview.getId());
        xinWenXi.setBujuType(bujutype);
        xinWenXi.setLanMuType(daohangtype);
        xinWenXi.setReplaycount(toutiao_listview.getArticlers().size());//跟帖数量
        xinWenXi.setTitle(toutiao_listview.getName());//标题
        xinWenXi.setXinwentext(toutiao_listview.getDescription());//内容
        xinWenXi.setCreateDate(toutiao_listview.getCreateTime());//日期
        xinWenXi.setGsmz(toutiao_listview.getGsmz());
        xinWenXi.setGsdz(toutiao_listview.getGsdz());
        xinWenXi.setLxr(toutiao_listview.getLxr());
        xinWenXi.setLxdh(toutiao_listview.getLxdh());
        xinWenXi.setZpxx(toutiao_listview.getZpxx());
        xinWenXi.setFwcs(toutiao_listview.getFwcs());
        xinWenXi.setGqxx(toutiao_listview.getGqxx());
        xinWenXi.setProductCategory(toutiao_listview.getProductcategory());
//xinWenXi.setUploadFiles(toutiao_listview.getUploadFile());
        for(int i=0;i<toutiao_listview.getUploadFile().size();i++){
            UploadFile uploadFile=new UploadFile();

            uploadFile.setPath(toutiao_listview.getUploadFile().get(i).getPath());
            potolist.add(uploadFile);
        }
        xinWenXi.setUploadFileList(potolist);
        for(int i=0;i<toutiao_listview.getProductArticler().size();i++){
//            ProductArticler productArticler=new ProductArticler();
//            productArticler.setArtreview_authorid(toutiao_listview.getProductArticler().get(i).getArtreview_authorid());
//            productArticler.setArtreview_time(toutiao_listview.getProductArticler().get(i).getArtreview_time());
//            productArticler.setArtreview_content(toutiao_listview.getProductArticler().get(i).getArtreview_content());
//            productArticler.setCustomer(toutiao_listview.getProductArticler().get(i).getCustomer());
            liuyuenlist.add(toutiao_listview.getProductArticler().get(i));
        }
        xinWenXi.setProductArticlerList(liuyuenlist);
//        List<XinWen_productinfo.T18908805728Entity.UploadFileEntity> potolist0 =new ArrayList<>();
//        potolist0=(List<XinWen_productinfo.T18908805728Entity.UploadFileEntity>)toutiao_listview.getUploadFile();
//        List<XinWenXiImage.PhotosObj> potolist1=new ArrayList<>();
//        if(potolist!=null) {
//            potolist1= XinWenXiImage.getdata(potolist,getActivity());
//        }
//        for(int i=0;i<potolist1.get(pos).getPhotosList().size();i++){
//            PhotoImage photo=new PhotoImage();
//
//           photo.setImagePicture(potolist1.get(pos).getPhotosList().get(i).getImage());
//            //   photo.setImagePicture(null);
//            potolist2.add(photo);
//        }
        //根据类型选择跳转的详细页面
        switch (bujutype) {
            case XinWen_adapter.TYPE_putong:
            case XinWen_adapter.TYPE_zhuanti:
            case XinWen_adapter.TYPE_zhibo:
                LogUtils.e("xinwenadapter", "TYPE_zhibo==" + bujutype);
                //  String urlzhibo = toutiao_listview.getUrl();
                // String urlzhibo = toutiao_listview.getName();
                String urlzhibo ="http://www.dcgqxx.com/product/product_select.html;jsessionid=BC7ECA17265523CB85B11424B39DA43A?id=28904";
//                xinWenXi.setUrl(urlzhibo);//详细页面url
                //跳转到详细页
                Intent intentzhibo = new Intent(getActivity(), WebProductinfoViewActivity.class);
                intentzhibo.putExtra("xinwendata", xinWenXi);
                //   intentzhibo.putExtra("potolist", potolist);
                //   intentzhibo.putExtra("xinwendata", new Gson().toJson(xinWenXi));
//                Bundle bundle = new Bundle();

//须定义一个list用于在budnle中传递需要传递的ArrayList<Object>,这个是必须要的
//                bundle.putStringArray("potolist", potolist);
//                intent.putExtras(bundle);
//                ArrayList bundlelist = new ArrayList();
//                ArrayList bundlelist1 = new ArrayList();
//                bundlelist.add(potolist);
//                bundlelist1.add(liuyuenlist);
//                bundle.putParcelableArrayList("potolist",bundlelist);
//                bundle.putParcelableArrayList("liuyuanlist",bundlelist1);
//                intentzhibo.putExtras(bundle);
//                intentzhibo.putExtra("bundle", bundle);
                startActivity(intentzhibo);
                getActivity().overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);
                break;
            case XinWen_adapter.type_duotu:
                LogUtils.e("xinwenadapter", "type_duotu==" + bujutype);
                //   String urlduotuRight = toutiao_listview.getId();
                String urlduotuRight = toutiao_listview.getName();
                String urlRighBefor = urlduotuRight.substring(urlduotuRight.lastIndexOf("|") - 4);
                String urlRight = urlRighBefor.replaceAll("\\|", "/");
                String urlduotu = "http://c.3g.163.com/photo/api/set/" + urlRight + ".json";
                //0096|81994    http://c.3g.163.com/photo/api/set/0096/82126.json
//                xinWenXi.setUrl(urlduotu);//详细页面url
                //跳转到详细页
                Intent intentduotu = new Intent(getActivity(), XinWenXiActivity.class);
//                intentduotu.putExtra("xinwendata", xinWenXi);

                startActivity(intentduotu);
                break;
        }

    }
    //轮播显示的方法
    private List<Lunbo> lunboList = new ArrayList<>();
    private View lunboView;
    private LinearLayout linearLayouticon;
    private ImageView beforicon;//轮播上一个icon
    private TouTiaoViewPager lunbo_viewPager;
    private LinearLayout pagerLayout;
    String xiangxiUrl;//跳转详细页面的url
    int size =0;
    private View showLunbo() {
        lunboList.clear();
        listads = new ArrayList<>();
        if(toutiao_list2.size()>0){
            for(int i=0;i<toutiao_list2.size();i++){
                listads.add(toutiao_list2.get(i).getUploadFile().get(0));
            }
        }

        if(listads.size()==0){
            return null;
        }
        size = listads.size();
        LogUtils.e("size==",size+"");
        lunboView = View.inflate(getActivity(), R.layout.xinwen_toutiao_lunbo, null);
        //如果只有一个图片则不轮播
        if (size == 1) {
            TextView title = (TextView) lunboView.findViewById(R.id.toutiao_lunboyitu_title);
            title.setText(listads.get(0).getTitle());
            ImageView lunbo_yitu = (ImageView) lunboView.findViewById(R.id.daohang_lunbo_yitu);
            lunbo_yitu.setVisibility(View.VISIBLE);
            xiangxiUrl ="aaa";
            XutilsGetData.xUtilsImageiv(lunbo_yitu, "http://www.dcgqxx.com/upload/"+listads.get(0).getPath(), getActivity(),false);
            lunbo_yitu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2015/11/14
                    //一个图片的轮播跳转的页面
                    framentlunbo2activity(0);
                }
            });
            return lunboView;
        }
        linearLayouticon = (LinearLayout) lunboView.findViewById(R.id.toutitao_lunbo_ll);
        //轮播icon以及把数据添加到lunboList
        for (int i = 0; i < size; i++) {
            //开辟一个宽和高的空间放入icon
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView icon = new ImageView(getActivity());
            System.out.println("设置icon宽高"+params);
            icon.setLayoutParams(params);//设置icon宽高
            icon.setImageResource(R.mipmap.toutiao_lunbo_icon);
            linearLayouticon.addView(icon);//添加到布局
            ImageView image = new ImageView(getActivity());
            image.setTag(i);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    framentlunbo2activity((Integer) view.getTag());
                }
            });
//            XutilsGetData.xUtilsImageiv(image, toutiao_list.get(0).getAds().get(i).getImgsrc(), getActivity(),false);
//            xiangxiUrl = toutiao_list.get(0).getAds().get(i).getUrl();
//            lunboList.add(new Lunbo(toutiao_list.get(0).getAds().get(i).getTitle(), xiangxiUrl, image));
            XutilsGetData.xUtilsImageiv(image, "http://www.dcgqxx.com/upload/"+listads.get(i).getPath(), getActivity(),false);
            xiangxiUrl =listads.get(i).getPath();
            lunboList.add(new Lunbo(listads.get(i).getTitle(), xiangxiUrl, image));
        }
        //设置第一个图片的标题
        final TextView title = (TextView) lunboView.findViewById(R.id.toutiao_lunbo_title);
        title.setText(lunboList.get(0).getTitle());

        //设置最后一个的icon图片控件
        beforicon = (ImageView) linearLayouticon.getChildAt(size - 1);

//        lunbo_viewPager = (TouTiaoViewPager) lunboView.findViewById(R.id.toutiao_lunbo_viewpager);
        pagerLayout = (LinearLayout) lunboView.findViewById(R.id.view_pager_content);
        lunbo_viewPager=new TouTiaoViewPager(getActivity(),null);
        lunbo_viewPager.setVisibility(View.VISIBLE);
        lunbo_viewPager.setOffscreenPageLimit(0);
        //获取屏幕像素相关信息

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int wpx= dm.widthPixels;
        int hpx=dm.heightPixels;
//        System.out.println("宽度:"+wpx);
//        System.out.println("宽度:"+hpx);
//        //根据屏幕信息设置ViewPager广告容器的宽高
        lunbo_viewPager.setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels, dm.heightPixels * 2 /5));
        pagerLayout.addView(lunbo_viewPager);
//        lunbo_viewPager.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels,
//               LinearLayout.LayoutParams.MATCH_PARENT));
        lunbo_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                int i = position % size;
                title.setText(lunboList.get(i).getTitle());
                ImageView currmIcon = (ImageView) linearLayouticon.getChildAt(i);
                currmIcon.setImageResource(R.mipmap.toutiao_lunbo_icon2);//设置当前的图片
                beforicon.setImageResource(R.mipmap.toutiao_lunbo_icon);//改变上一个图片
                beforicon = currmIcon;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        Lunboadapter lunboadapter = new Lunboadapter();
        lunbo_viewPager.setAdapter(lunboadapter);
        lunbo_viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % lunboList.size());
        if (thread == null) {//如果只有一个图片不需要轮播
            startthreadLunbo();//开启子线程轮播
            thread.start();
        }
        return lunboView;
    }

    private Thread thread;//子线程轮播对象
    private void startthreadLunbo() {
        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            lunbo_viewPager.setCurrentItem(lunbo_viewPager.getCurrentItem() + 1);
        }
    };

    class Lunboadapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                container.addView(lunboList.get(position % lunboList.size()).getImageView());
                return lunboList.get(position % lunboList.size()).getImageView();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    //轮播工具类
    class Lunbo {
        String title;
        String xiangxiurl;
        ImageView imageView;

        public Lunbo(String title, String xiangxiurl, ImageView imageView) {
            this.title = title;
            this.xiangxiurl = xiangxiurl;
            this.imageView = imageView;
        }

        public String getTitle() {
            return title;
        }

        public String getXiangxiurl() {
            return xiangxiurl;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }


    //轮播的详细页面
    private void framentlunbo2activity(int posion){
//        String skiptype=toutiao_list.get(0).getAds().get(posion).getTag();
//
//        int lanmutype=XinWen_adapter.getType(skiptype);

        XinWen_productinfo.T18908805728Entity toutiao_listview= new XinWen_productinfo.T18908805728Entity();//获取记录
        toutiao_listview=toutiao_list.get( posion);

        //传入详细页面的数据
        XinWenXiData xinWenXi = new XinWenXiData();
        xinWenXi.setLanMuType(daohangtype);
//        xinWenXi.setTitle(toutiao_list.get(0).getAds().get(posion).getTitle());//标题
        xinWenXi.setTitle(toutiao_listview.getName());
        //传入详细页面的数据
        potolist =new ArrayList<>();
        liuyuenlist =new ArrayList<>();
        //List<PhotoImage> potolist2 = new ArrayList<>();
        int bujutype=1;
        xinWenXi.setId(toutiao_listview.getId());
        xinWenXi.setBujuType(bujutype);
        xinWenXi.setLanMuType(daohangtype);
        xinWenXi.setReplaycount(toutiao_listview.getArticlers().size());//跟帖数量
        xinWenXi.setTitle(toutiao_listview.getName());//标题
        xinWenXi.setXinwentext(toutiao_listview.getDescription());//内容
        xinWenXi.setCreateDate(toutiao_listview.getCreateTime());//日期
        xinWenXi.setGsmz(toutiao_listview.getGsmz());
        xinWenXi.setGsdz(toutiao_listview.getGsdz());
        xinWenXi.setLxr(toutiao_listview.getLxr());
        xinWenXi.setLxdh(toutiao_listview.getLxdh());
        xinWenXi.setZpxx(toutiao_listview.getZpxx());
        xinWenXi.setFwcs(toutiao_listview.getFwcs());
        xinWenXi.setGqxx(toutiao_listview.getGqxx());
        xinWenXi.setProductCategory(toutiao_listview.getProductcategory());
//xinWenXi.setUploadFiles(toutiao_listview.getUploadFile());
        for(int i=0;i<toutiao_listview.getUploadFile().size();i++){
            UploadFile uploadFile=new UploadFile();

            uploadFile.setPath(toutiao_listview.getUploadFile().get(i).getPath());
            potolist.add(uploadFile);
        }
        xinWenXi.setUploadFileList(potolist);
        for(int i=0;i<toutiao_listview.getProductArticler().size();i++){
//            ProductArticler productArticler=new ProductArticler();
//            productArticler.setArtreview_authorid(toutiao_listview.getProductArticler().get(i).getArtreview_authorid());
//            productArticler.setArtreview_time(toutiao_listview.getProductArticler().get(i).getArtreview_time());
//            productArticler.setArtreview_content(toutiao_listview.getProductArticler().get(i).getArtreview_content());
            liuyuenlist.add(toutiao_listview.getProductArticler().get(i));
        }
        xinWenXi.setProductArticlerList(liuyuenlist);
        int lanmutype=1;
        lanmutype=toutiao_listview.getLanmu();
        //根据类型选择跳转的详细页面
        switch (lanmutype) {
            case XinWen_adapter.TYPE_putong:
            case XinWen_adapter.TYPE_zhuanti:
            case XinWen_adapter.TYPE_zhibo:
//                String urlputong = toutiao_list.get(0).getAds().get(posion).getUrl();
//                LogUtils.e("xinwenactivity==urlputong==", urlputong + "");
                xinWenXi.setUrl("bbbb");//详细页面url
                //跳转到详细页
                Intent intentputong = new Intent(getActivity(), WebProductinfoViewActivity.class);
                intentputong.putExtra("xinwendata", xinWenXi);
                startActivity(intentputong);
                break;
            case XinWen_adapter.type_duotu:

//                String urlduotuRight = toutiao_list.get(0).getAds().get(posion).getUrl();
//                String urlRighBefor = urlduotuRight.substring(urlduotuRight.lastIndexOf("|") - 4);
//                String urlRight = urlRighBefor.replaceAll("\\|", "/");
//                String urlduotu = "http://c.3g.163.com/photo/api/set/" + urlRight + ".json";
                //0096|81994    http://c.3g.163.com/photo/api/set/0003/578045.json
//                xinWenXi.setUrl(urlduotu);//详细页面url
                //跳转到详细页
//                Intent intentduotu = new Intent(getActivity(), XinWenXiActivity.class);
                xinWenXi.setUrl("urlduotu");
                Intent intentduotu = new Intent(getActivity(),XinWenXiActivity.class);
                intentduotu.putExtra("xinwendata", xinWenXi);
                startActivity(intentduotu);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        xutilsGetData.XutilsClose();//关闭网络请求
    }
}

