package com.xiangmu.lzx.activitys;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiangmu.lzx.CostomAdapter.MyGridViewAadapter;
import com.xiangmu.lzx.CostomAdapter.SearchProductinfoAdapter;
import com.xiangmu.lzx.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.lzx.CostomProgressDialog.SimpleArcDialog;
import com.xiangmu.lzx.Modle.ProductArticler;
import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshBase;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshListView;
import com.xiangmu.lzx.utils.CommonUtil;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.MySqlitehelper;
import com.xiangmu.lzx.utils.ServerURL;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_adapter;
import com.xiangmu.lzx.utils.XinWen_productinfo;
import com.xiangmu.lzx.utils.XinWenproductinfoJson;
import com.xiangmu.lzx.utils.XutilsGetData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import com.google.android.gms.plus.model.people.Person;

//import com.xiangmu.lzx.CostomAdapter.SearchEditResultAdapter;

public class ProductinfoListEditActivity extends AppCompatActivity implements OnDismissListener, OnItemClickListener {
//public class ProductinfoListEditActivity extends AppCompatActivity implements MyItemClickListener, MyItemLongClickListener, OnDismissListener, OnItemClickListener {
    // Content View Elements
    private ImageButton back;
    private TextView noHotWords;
    private TextView searchjiekuo;
    //  private ImageView clear_history;
    private LinearLayout layoutsearchResult;
    //  private RelativeLayout layout_sousuoHis;
    private PullToRefreshListView lv_searchResult;
    private SearchView search_view;
    //    private GridView houtWord_gridview;
//    private GridView gv_searchHistory;
    private MyGridViewAadapter gridViewAadapter;
    private List<String> list = new ArrayList<>();
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    private String tuijian;//推荐热词
    private String keywords;//搜索关键字
    private SearchProductinfoAdapter searchProductinfoAdapter;
    private MySqlitehelper mySqlitehelper;
    private SQLiteDatabase writableDatabase;
    private List<XinWen_productinfo.T18908805728Entity.AdsEntity> listads;//字段listads
    private List<XinWen_productinfo.T18908805728Entity> toutiao_list=new ArrayList<>();
    private XinWen_productinfo.T18908805728Entity bean;
    private XinWenURL xinWenURL = new XinWenURL();
    private String url = null;
    private SimpleArcDialog mDialog;
    private AlertView mAlertView1, mAlertView2;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private Boolean isPause = false;
    private int id = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
 //   private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
    // */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfosearchedit);

        bindViews();
        url = xinWenURL.getZuixin(501);//最新url
        mySqlitehelper = new MySqlitehelper(this);
        writableDatabase = mySqlitehelper.getWritableDatabase();
        mDialog = new SimpleArcDialog(this);

        initSearchNews(url,true);
        // inintAdapter();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    //    client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void bindViews() {
        back = (ImageButton) findViewById(R.id.back);

        noHotWords = (TextView) findViewById(R.id.noHotWords);
        layoutsearchResult = (LinearLayout) findViewById(R.id.searchResult);//搜索结果布局

        //    lv_searchResult = (RecyclerView) findViewById(R.id.lv_searchResult);//搜索结果
        lv_searchResult = (PullToRefreshListView) findViewById(R.id.lv_searchResult);//搜索结果
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//
//        lv_searchResult.setLayoutManager(manager);
//        lv_searchResult.setItemAnimator(new DefaultItemAnimator());

        searchjiekuo = (TextView) findViewById(R.id.lv_searchjiekuo);
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setSubmitButtonEnabled(true);//是否显示确认搜索按钮
        search_view.setIconified(false);//设置搜索框默认展开
        search_view.onActionViewExpanded();//表示在内容为空时不显示取消的x按钮，内容不为空时显示.

        lv_searchResult.setPullLoadEnabled(false);  //上拉加载，屏蔽
        lv_searchResult.setScrollLoadEnabled(true); //设置滚动加载可用
        //设置上拉下拉的监听事件
        lv_searchResult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新，重新获取数据，填充listview
                xinWenURL.setStratPage(xinWenURL.getStratPage() - 1);
                url = xinWenURL.getZuixin(501);//最新url
                getData(url,true);//刷新数据
                Toast.makeText(ProductinfoListEditActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                String stringDate = CommonUtil.getStringDate();// 下拉刷新时获取当前的刷新时间
                lv_searchResult.setLastUpdatedLabel(stringDate);//将时间添加到刷新的表头
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                xinWenURL.setStratPage(xinWenURL.getStratPage()+1);
                //默认选择头条栏目
                //    String urlfen =geturl();//分页url;
                //   LogUtils.e("toutiao", "url:" + urlfen);
                // 上拉加载
                url = xinWenURL.getZuixin(501);//最新url
                Toast.makeText(ProductinfoListEditActivity.this, "上拉加载"+xinWenURL.getStratPage(), Toast.LENGTH_SHORT).show();
                  getData(url, false);//加载数据


            }
        });

        inintClick();
    }

    //    private void inintAdapter() {
//
//
//
//        layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
//
//        searchProductinfoAdapter= new SearchProductinfoAdapter(toutiao_list,this);
//
//
//        lv_searchResult.setAdapter(searchProductinfoAdapter);
//        RecyclerView.ItemDecoration decoration = new MyDecoration(this);
//        this.lv_searchResult.addItemDecoration(decoration);
//        this.searchProductinfoAdapter.setOnItemClickListener(this);
//        this.searchProductinfoAdapter.setOnItemLongClickListener(this);
//    }
    MyGridViewAadapter adapterHistory = null;

    //查询数据库
    private void queryDB() {
        String searchWord = null;
        List<String> historyList = new ArrayList<>();
        Cursor cursor = writableDatabase.query("searchHistory", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            searchWord = cursor.getString(cursor.getColumnIndex("searchWord"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            LogUtils.e("--->searchHistory", searchWord + "  " + url);
            historyList.add(searchWord);
        }
        cursor.close();
        //设置gv_searchHistory属性
        int size = historyList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

    }


    //初始化各监听事件
    private void inintClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductinfoListEditActivity.this.finish();
                //    finish();
            }
        });


        search_view.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        //搜索框文本变化监听
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keywords = query;

                initSearchNews(ServerURL.searchUrl3 + keywords,true);//执行新闻搜索请求
                //添加数据
                ContentValues contentValues = new ContentValues();

                contentValues.put("url", ServerURL.searchUrl3 + keywords);
                contentValues.put("searchWord", keywords);
                writableDatabase.insert("searchHistory", null, contentValues);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    layoutsearchResult.setVisibility(View.GONE);//隐藏搜索结果布局
                    queryDB();
                }
                return true;
            }
        });

    }

    //初始化新闻搜索数据请求
    CustomProgressDialog progressDialog;

    private void initSearchNews(String url,final boolean refresh) {

        if (!CommonUtil.isNetWork(this)) {//无网络读缓存
            if (keywords != null) {
                LogUtils.e("---", url);
                String searchResult = SharedPreferencesUtil.getData(this, url, "");
                if (!TextUtils.isEmpty(searchResult)) {
                    getshowdata( searchResult,true);
                }
            }
        } else {

            getData(url,refresh);

            mDialog.show();

        }
    }

    //初始化热词推荐数据
    private void inintHotWordsData() {
        noHotWords.setVisibility(View.VISIBLE);
        if (!CommonUtil.isNetWork(this)) {
            String result = SharedPreferencesUtil.getData(this, ServerURL.tuiJianWord, "");
            if (!TextUtils.isEmpty(result)) {
                getshowdata(result,true);
            }
        } else {
            getData(ServerURL.tuiJianWord,true);//如果无缓存再去请求网络
        }
    }
    private XutilsGetData xutilsGetData = new XutilsGetData();
    //网络请求获得数据 refresh   true为刷新数据  false为加载数据  存储根据url保存数据
    public void getData( String url, final boolean refresh) {
        System.out.println(url);
        if (CommonUtil.isNetWork(this)){
            //然后网络请求刷新数据
            xutilsGetData.xUtilsHttp(this, url, new XutilsGetData.CallBackHttp() {
                @Override
                public void handleData(String data) {
                    LogUtils.e("xinwenactivity==data==", data + "");
                    getshowdata(data, refresh);

                }
            },true);
        }else {
            String data = xutilsGetData.getData(this, url, null);
            //判断本地数据是否存在  如果没有网络请求
            if (data != null) {
                getshowdata(data, refresh);
            }
        }
    }
    // 显示数据  或者分页加载数据
    private void getshowdata(String data, boolean refresh) {
     //   toutiao_list.clear();
        if (refresh) {

            toutiao_list.clear();
        }

        XinWen_productinfo toutiao_object = XinWenproductinfoJson.getdata(data, 2);//传入类型和数据
        toutiao_list.addAll(toutiao_object.getT18908805728());
        Collections.sort(toutiao_list, new Comparator<XinWen_productinfo.T18908805728Entity>() {
            public int compare(XinWen_productinfo.T18908805728Entity arg0, XinWen_productinfo.T18908805728Entity arg1) {
                return arg1.getId().compareTo(arg0.getId());
            }
        });
        layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
        if ( searchProductinfoAdapter  == null) {//在数据之后adapter之前增加轮播才会不anr
            searchProductinfoAdapter = new SearchProductinfoAdapter(toutiao_list, this);

            lv_searchResult.getRefreshableView().setAdapter(searchProductinfoAdapter);
        }
        searchProductinfoAdapter.setOnItemClickListener(new SearchProductinfoAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                onItemClick1( view, postion);
            }
//
//
        });
        searchProductinfoAdapter.setToutiao_list(toutiao_list);//填充并刷新数据
        lv_searchResult.onPullDownRefreshComplete();//隐藏下拉头
        lv_searchResult.onPullUpRefreshComplete();//隐藏上拉头
        mDialog.dismiss();



//        if (toutiao_adapter == null) {//在数据之后adapter之前增加轮播才会不anr
//
//            toutiao_adapter = new XinWenproductinfoBaseAdapter(getActivity(), toutiao_list0);
//            toutiao_lv.getRefreshableView().setAdapter(toutiao_adapter);
//        }
//
//        toutiao_adapter.setToutiao_list(toutiao_list0);//填充并刷新数据
//        toutiao_lv.onPullDownRefreshComplete();//隐藏下拉头
//        toutiao_lv.onPullUpRefreshComplete();//隐藏上拉头
    }

//

    ;
//    private void getData(int flag,final  String url,final boolean refresh) {
//        if (!url.equals("")) {
//            httpUtils = new HttpUtils();
//
//            switch (flag) {
//                case 1:
//                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (responseInfo.result != null) {
//                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
//                                paserData(1, responseInfo.result,refresh);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(HttpException e, String s) {
//                            mDialog.dismiss();
//                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    break;
//                case 2:
//              httpUtils.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s
//                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (responseInfo.result != null) {
//                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
//                                paserData(2, responseInfo.result,refresh);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(HttpException e, String s) {
//                            mDialog.dismiss();
//                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    break;
//                case 3:
//                    //       progressDialog = new CustomProgressDialog(this,"数据正在请求中...", R.anim.donghua_frame);
////                    httpUtils.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s
//                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (responseInfo.result != null) {
//                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
//                                paserData(3, responseInfo.result,refresh);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(HttpException e, String s) {
//                            mDialog.dismiss();
//                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    break;
//            }
//        }
//    }

//    private void paserData(int flag, String result,final boolean refresh) {
//        switch (flag) {
//            case 1:
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    JSONArray jsonArray = jsonObject.getJSONArray("hotWordList");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jo = jsonArray.getJSONObject(i);
//                        String hotWord = jo.getString("title");
//                        list.add(hotWord);
//                    }
//                    gridViewAadapter = new MyGridViewAadapter(list, this);
////                    houtWord_gridview.setAdapter(gridViewAadapter);
//                    noHotWords.setVisibility(View.GONE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 2:
//
//                if (refresh) {
//
//                    toutiao_list.clear();
//                }
//                XinWen_productinfo toutiao_object = XinWenproductinfoJson.getdata(result, 2);//传入类型和数据
//                toutiao_list.addAll(toutiao_object.getT18908805728());
//
//                searchjiekuo.setText("搜索结果: " + toutiao_object.getTotalRecords() + " 条记录");
//
//
//                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
//
//                searchProductinfoAdapter = new SearchProductinfoAdapter(toutiao_list, this);
//
//                lv_searchResult.getRefreshableView().setAdapter(searchProductinfoAdapter);
//
//                searchProductinfoAdapter.setOnItemClickListener(new SearchProductinfoAdapter.MyItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int postion) {
//                        onItemClick1( view, postion);
//                    }
////
////
//                });
//
//                lv_searchResult.onPullDownRefreshComplete();//隐藏下拉头
//                lv_searchResult.onPullUpRefreshComplete();//隐藏上拉头
//                mDialog.dismiss();
//                break;
//            case 3:
//                toutiao_list = new ArrayList<>();
//
//                XinWen_productinfo toutiao_object1 = XinWenproductinfoJson.getdata(result, 0);//传入类型和数据
//                toutiao_list.addAll(toutiao_object1.getT18908805728());
////                SearchBean searchBean = new Gson().fromJson(result, SearchBean.class);
//                System.out.println("标题:" + toutiao_list.get(0).getName());
////                LogUtils.e("---", searchBean.doc.result.get(0).name);
//                searchjiekuo.setText("搜索结果: " + toutiao_object1.getTotalRecords() + " 条记录");
//                //       layout_sousuoHis.setVisibility(View.GONE);//隐藏搜索历史
//                //     progressDialog.dismiss();
//                mDialog.dismiss();
//                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
//
//                searchProductinfoAdapter = new SearchProductinfoAdapter(toutiao_list, this);
//
//
//                //   lv_searchResult.setAdapter(searchProductinfoAdapter);
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpUtils != null) {
            handler.cancel();
        }
        if (writableDatabase != null) {
            writableDatabase.close();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //   Toast.makeText(ProductinfoListEditActivity.this, " onPause()", Toast.LENGTH_SHORT).show();
        isPause = true; //记录页面已经被暂停
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  Toast.makeText(ProductinfoListEditActivity.this, " onResume()", Toast.LENGTH_SHORT).show();

        if (isPause) { //判断是否暂停
            isPause = false;
            initSearchNews(url,true);
//            inintAdapter();
        }

    }

    //跳转详细页面方法
    @SuppressLint("NewApi")
    private void frament2activity(int position, List<XinWen_productinfo.T18908805728Entity> toutiao_list) {
        int pos;
        if (listads == null) {//判断有没有轮播图如果没有就不添加轮播布局  布局就和原来一样
            pos = position;
        } else {
            pos = position - 1;//有轮播图的时候点listview第二条才是数据list集合中的第一条
        }
        LogUtils.e("xinwenadapter", "postion==" + pos);
        //    int bujutype = XinWen_adapter.getType(toutiao_list.get(pos).getId());

//        LogUtils.e("xinwenadapter", "type==" + bujutype);

        //传入详细页面的数据
        ArrayList<UploadFile> potolist = new ArrayList<>();
        List<ProductArticler> liuyuenlist = new ArrayList<>();
        //List<PhotoImage> potolist2 = new ArrayList<>();
        XinWenXiData xinWenXi = new XinWenXiData();
        xinWenXi.setId(toutiao_list.get(pos).getId());
        int bujutype = 0;
        bujutype = toutiao_list.get(pos).getLanmu();
        xinWenXi.setBujuType(bujutype);
//        xinWenXi.setLanMuType(daohangtype);
        xinWenXi.setLanMuType(1);
        xinWenXi.setReplaycount(toutiao_list.get(pos).getArticlers().size());//跟帖数量
        xinWenXi.setTitle(toutiao_list.get(pos).getName());//标题
        xinWenXi.setXinwentext(toutiao_list.get(pos).getDescription());//内容
        xinWenXi.setCreateDate(toutiao_list.get(pos).getCreateTime());//日期
        xinWenXi.setGsmz(toutiao_list.get(pos).getGsmz());
        xinWenXi.setGsdz(toutiao_list.get(pos).getGsdz());
        xinWenXi.setLxr(toutiao_list.get(pos).getLxr());
        xinWenXi.setLxdh(toutiao_list.get(pos).getLxdh());
        xinWenXi.setZpxx(toutiao_list.get(pos).getZpxx());
        xinWenXi.setFwcs(toutiao_list.get(pos).getFwcs());
        xinWenXi.setGqxx(toutiao_list.get(pos).getGqxx());
        xinWenXi.setProductCategory(toutiao_list.get(pos).getProductcategory());
//xinWenXi.setUploadFiles(toutiao_list.get(pos).getUploadFile());
        for (int i = 0; i < toutiao_list.get(pos).getUploadFile().size(); i++) {
            UploadFile uploadFile = new UploadFile();

            uploadFile.setPath(toutiao_list.get(pos).getUploadFile().get(i).getPath());
            potolist.add(uploadFile);
        }
        xinWenXi.setUploadFileList(potolist);
        for (int i = 0; i < toutiao_list.get(pos).getProductArticler().size(); i++) {
            ProductArticler productArticler = new ProductArticler();
            productArticler.setArtreview_authorid(toutiao_list.get(pos).getProductArticler().get(i).getArtreview_authorid());
            productArticler.setArtreview_time(toutiao_list.get(pos).getProductArticler().get(i).getArtreview_time());
            productArticler.setArtreview_content(toutiao_list.get(pos).getProductArticler().get(i).getArtreview_content());
            liuyuenlist.add(productArticler);
        }
        xinWenXi.setProductArticlerList(liuyuenlist);
//        List<XinWen_productinfo.T18908805728Entity.UploadFileEntity> potolist0 =new ArrayList<>();
//        potolist0=(List<XinWen_productinfo.T18908805728Entity.UploadFileEntity>)toutiao_list.get(pos).getUploadFile();
//        List<XinWenXiImage.PhotosObj> potolist1=new ArrayList<>();
//        if(potolist!=null) {
//            potolist1= XinWenXiImage.getdata(potolist,SearchActivity.this);
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
                //  String urlzhibo = toutiao_list.get(pos).getUrl();
                // String urlzhibo = toutiao_list.get(pos).getName();
//                String urlzhibo ="http://www.dcgqxx.com/product/product_select.html;jsessionid=BC7ECA17265523CB85B11424B39DA43A?id=28904";
                xinWenXi.setUrl("bbbbb");//详细页面url
                //跳转到详细页
                //      Intent intentzhibo = new Intent(ProductinfoListEditActivity.this, WebProductinfoViewActivity.class);
                Intent intentzhibo = new Intent(this, WebProductinfoViewActivity.class);
                intentzhibo.putExtra("xinwendata", xinWenXi);

                startActivity(intentzhibo);

                this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);
                break;
            case XinWen_adapter.type_duotu:
//                LogUtils.e("xinwenadapter", "type_duotu==" + bujutype);
//                //   String urlduotuRight = toutiao_list.get(pos).getId();
//                String urlduotuRight = toutiao_list.get(pos).getName();
//                String urlRighBefor = urlduotuRight.substring(urlduotuRight.lastIndexOf("|") - 4);
//                String urlRight = urlRighBefor.replaceAll("\\|", "/");
//                String urlduotu = "http://c.3g.163.com/photo/api/set/" + urlRight + ".json";
                //0096|81994    http://c.3g.163.com/photo/api/set/0096/82126.json
                xinWenXi.setUrl("aaaa");//详细页面url
                //跳转到详细页
                //    Intent intentduotu = new Intent(ProductinfoListEditActivity.this, XinWenXiActivity.class);
                Intent intentduotu = new Intent(this, XinWenXiActivity.class);
                intentduotu.putExtra("xinwendata", xinWenXi);

                startActivity(intentduotu);
                break;
        }

    }

    /**
     * Item click
     */
  //  @Override
    public void onItemClick1(View view, int postion) {
        bean = new XinWen_productinfo.T18908805728Entity();
        bean = toutiao_list.get(postion);
        id = bean.getId();
        switch (view.getId()) {
            case R.id.result_title://详细信息
                //  	Toast.makeText(this, "LongClick1 标题1", Toast.LENGTH_SHORT).show();
                //	System.out.println("LongClick1 标题");
                //    l = 1;
                frament2activity(postion, toutiao_list);
                break;

            case R.id.result_replace://修改
                Toast.makeText(this, "LongClick1 标题2", Toast.LENGTH_SHORT).show();
                //	System.out.println("LongClick1 修改");
                //  l = 2;
                break;
            case R.id.result_delete://删除
                mAlertView1 = new AlertView("删除", bean.getName(), "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
                mAlertView1.show();
                break;
            case R.id.result_agree://审核
                mAlertView2 = new AlertView("审核", bean.getName(), "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
                mAlertView2.show();
                break;
        }

    }

//    @Override
//    public void onItemLongClick(View view, int postion) {
//        bean = new XinWen_productinfo.T18908805728Entity();
//        bean = toutiao_list.get(postion);
//        if (bean != null) {
//            Toast.makeText(this, "LongClick " + bean.getName(), Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onItemClick(Object o, int position) {
        //  closeKeyboard();
        AlertView mAlertView0 = (AlertView) o;
        if (mAlertView1 == mAlertView0) {
            String clickdel = xinWenURL.getClickdel() + bean.getId();
            DelData(clickdel);
        } else if (mAlertView2 == mAlertView0) {
            String agree = xinWenURL.getClickagree() + bean.getId();
            AgreeData(agree);
        }
//        if(position==0) {
//           // Toast.makeText(this, "点击了第确定按键", Toast.LENGTH_SHORT).show();
//
//          //  searchProductinfoAdapter.notifyDataSetChanged();
//        }else{
//          Toast.makeText(this, "此功能未完善", Toast.LENGTH_SHORT).show();
//
//        }
    }

    @Override
    public void onDismiss(Object o) {
        //
        //    closeKeyboard();
//        Toast.makeText(this, "消失了", Toast.LENGTH_SHORT).show();
    }

    private void DelData(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.result.equals("true")) {
                        Toast.makeText(ProductinfoListEditActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < toutiao_list.size(); i++) {

                            if (toutiao_list.get(i).getId() == id) {

                                toutiao_list.remove(i);

                                i--;

                            }
                        }

                        isPause = true;
                        onResume();
                    }

                }


                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(ProductinfoListEditActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void AgreeData(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.result.equals("true")) {
                        Toast.makeText(ProductinfoListEditActivity.this, "审核成功", Toast.LENGTH_SHORT).show();
                        isPause = true;
                        onResume();
                    }

                }


                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(ProductinfoListEditActivity.this, "审核失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
