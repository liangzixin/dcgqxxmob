package com.xiangmu.lzx.activitys;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.google.android.gms.appindexing.AppIndex;
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
import com.xiangmu.lzx.decoration.MyDecoration;
import com.xiangmu.lzx.listener.MyItemClickListener;
import com.xiangmu.lzx.listener.MyItemLongClickListener;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.xiangmu.lzx.CostomAdapter.SearchEditResultAdapter;

//public class ProductinfoListEditActivity extends AppCompatActivity {
    public class ProductinfoListEditActivity extends AppCompatActivity implements MyItemClickListener,MyItemLongClickListener,OnDismissListener,OnItemClickListener {
    // Content View Elements
    private ImageButton back;
    private TextView noHotWords;
    private TextView searchjiekuo;
    //  private ImageView clear_history;
    private LinearLayout layoutsearchResult;
    //  private RelativeLayout layout_sousuoHis;
    private RecyclerView lv_searchResult;
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
    private List<XinWen_productinfo.T18908805728Entity> toutiao_list = new ArrayList<>();
    private XinWen_productinfo.T18908805728Entity bean;
    private XinWenURL xinWenURL = new XinWenURL();
    private String url = null;
    private SimpleArcDialog mDialog;
    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private Boolean isPause=false;
    private int id=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfosearchedit);

        bindViews();
        url = xinWenURL.getZuixin(501);//最新url
        mySqlitehelper = new MySqlitehelper(this);
        writableDatabase = mySqlitehelper.getWritableDatabase();
        mDialog = new SimpleArcDialog(this);
        //     queryDB();//查询数据
        //  progressDialog = new CustomProgressDialog(this,"数据正在请求中比...", R.anim.donghua_frame);
        initSearchNews(url);
        // getData(3,url);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void bindViews() {
        back = (ImageButton) findViewById(R.id.back);
        //clear_history = (ImageView) findViewById(R.id.clear_history);
        noHotWords = (TextView) findViewById(R.id.noHotWords);
        layoutsearchResult = (LinearLayout) findViewById(R.id.searchResult);//搜索结果布局
        //   layout_sousuoHis = (RelativeLayout) findViewById(R.id.layout_sousuoHis);//搜索历史布局
        lv_searchResult = (RecyclerView) findViewById(R.id.lv_searchResult);//搜索结果
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        lv_searchResult.setLayoutManager(manager);
        lv_searchResult.setItemAnimator(new DefaultItemAnimator());
        searchjiekuo = (TextView) findViewById(R.id.lv_searchjiekuo);
//        houtWord_gridview = (GridView) findViewById(R.id.houtWord_gridview);//热词推荐
//        gv_searchHistory = (GridView) findViewById(R.id.gv_searchHistory);//搜索历史
        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setSubmitButtonEnabled(true);//是否显示确认搜索按钮
        search_view.setIconified(false);//设置搜索框默认展开
//        android:imeOptions="actionSearch" 设置点击输入法自动匹配是确认,下一条...
//        app:defaultQueryHint="请输入关键字..."  设置输入框展开默认显示文字
        search_view.onActionViewExpanded();//表示在内容为空时不显示取消的x按钮，内容不为空时显示.

        //     inintHotWordsData();//加载热词推荐数据
     inintClick();
    }

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
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
//        gv_searchHistory.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//        gv_searchHistory.setColumnWidth(itemWidth); // 设置列表项宽
//        gv_searchHistory.setHorizontalSpacing(5); // 设置列表项水平间距
//        gv_searchHistory.setStretchMode(GridView.NO_STRETCH);
//        gv_searchHistory.setNumColumns(size); // 设置列数量=列表集合数
//        if (historyList.size()>0){
//            adapterHistory = new MyGridViewAadapter(historyList,this);
//            gv_searchHistory.setAdapter(adapterHistory);
//    //        layout_sousuoHis.setVisibility(View.VISIBLE);
//        }
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

//        //搜索历史记录item监听
//        gv_searchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String word = adapterHistory.getList().get(i);
//                Toast.makeText(ProductinfoListEditActivity.this, word, Toast.LENGTH_SHORT).show();
//                search_view.setQuery(word, false);
//                initSearchNews(ServerURL.searchUrl3 + word);//执行新闻搜索请求
//            }
//        });
//
//        //清空搜索历史
//        clear_history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ProductinfoListEditActivity.this,"正在清空搜索历史...",Toast.LENGTH_SHORT).show();
//                layout_sousuoHis.setVisibility(View.GONE);
//
//                int delete = writableDatabase.delete("searchHistory", null, null);
//                if (delete >= 1){
//                    Toast.makeText(ProductinfoListEditActivity.this,"搜索历史清空完成...",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        //热词推荐
//        houtWord_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                tuijian = gridViewAadapter.getList().get(i);
//                keywords = tuijian;
//                search_view.setQuery(tuijian, true);
//            }
//        });


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
//                initSearchNews(ServerURL.searchUrl1 + keywords + ServerURL.searchUrl2);//执行新闻搜索请求
                initSearchNews(ServerURL.searchUrl3 + keywords);//执行新闻搜索请求
                //添加数据
                ContentValues contentValues = new ContentValues();
//                contentValues.put("url",ServerURL.searchUrl1 + keywords + ServerURL.searchUrl2);
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

//        lv_searchResult.setPullLoadEnabled(false);
//        lv_searchResult.setPullRefreshEnabled(false);
//        lv_searchResult.setScrollLoadEnabled(false);
//        lv_searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (view.getId()) {
//                    case R.id.result_title://暂不登陆,返回
//                        Toast.makeText(ProductinfoListEditActivity.this, "数据请求标题", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.result_replace://暂不登陆,返回
//                        Toast.makeText(ProductinfoListEditActivity.this, "数据请求修改", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.result_delete://暂不登陆,返回
//                        Toast.makeText(ProductinfoListEditActivity.this, "数据请求删除", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                String docid = searchResultAdapter.getList().get(i).docid;

//                String docid = searchResultAdapter.getList().get(i).getId().toString();
//                Intent intent = new Intent(SearchActivity.this, YueDuDetialActivity.class);
//                intent.putExtra("yueduDetial", docid);
//                startActivity(intent);
//                overridePendingTransition(R.anim.zcdh_set_in, R.anim.zcdh_alpha_out);
//                layoutsearchResult.setVisibility(View.GONE);//隐藏搜索结果布局
//                queryDB();
        //   frament2activity(i,toutiao_list);
//            }
//        });
    }

    //初始化新闻搜索数据请求
    CustomProgressDialog progressDialog;

    private void initSearchNews(String url) {

        //  mDialog.show();
        //   progressDialog = new CustomProgressDialog(this,"数据正在请求中...", R.anim.donghua_frame);
//        mPointProgressBar=(PointProgressBar)findViewById(R.id.pointProgressBar);
//        new Thread(){
//            public void run() {
//                for(int i=0;i<=100;i++){
//                    mPointProgressBar.setCurrentPro(i);
//                    try {
//                        sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            ;
//        }.start();
        if (!CommonUtil.isNetWork(this)) {//无网络读缓存
            if (keywords != null) {
                LogUtils.e("---", url);
                String searchResult = SharedPreferencesUtil.getData(this, url, "");
                if (!TextUtils.isEmpty(searchResult)) {
                    paserData(2, searchResult);
                }
            }
        } else {
            //    if (keywords != null) {
            getData(2, url);
            //   progressDialog.show();
            mDialog.show();
            //   }
        }
    }

    //初始化热词推荐数据
    private void inintHotWordsData() {
        noHotWords.setVisibility(View.VISIBLE);
        if (!CommonUtil.isNetWork(this)) {
            String result = SharedPreferencesUtil.getData(this, ServerURL.tuiJianWord, "");
            if (!TextUtils.isEmpty(result)) {
                paserData(1, result);
            }
        } else {
            getData(1, ServerURL.tuiJianWord);//如果无缓存再去请求网络
        }
    }

    private void getData(int flag, final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            switch (flag) {
                case 1:
                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (responseInfo.result != null) {
                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
                                paserData(1, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 2:
//                    httpUtils.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s
                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (responseInfo.result != null) {
                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
                                paserData(2, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 3:
                    //       progressDialog = new CustomProgressDialog(this,"数据正在请求中...", R.anim.donghua_frame);
//                    httpUtils.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s
                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (responseInfo.result != null) {
                                SharedPreferencesUtil.saveData(ProductinfoListEditActivity.this, url, responseInfo.result);
                                paserData(3, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(ProductinfoListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    }

    private void paserData(int flag, String result) {
        switch (flag) {
            case 1:
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("hotWordList");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        String hotWord = jo.getString("title");
                        list.add(hotWord);
                    }
                    gridViewAadapter = new MyGridViewAadapter(list, this);
//                    houtWord_gridview.setAdapter(gridViewAadapter);
                    noHotWords.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                toutiao_list = new ArrayList<>();
                XinWen_productinfo toutiao_object = XinWenproductinfoJson.getdata(result, 2);//传入类型和数据
                toutiao_list.addAll(toutiao_object.getT18908805728());
//                SearchBean searchBean = new Gson().fromJson(result, SearchBean.class);
                System.out.println("标题:" + toutiao_list.get(0).getName());
//                LogUtils.e("---", searchBean.doc.result.get(0).name);
                searchjiekuo.setText("搜索结果: " + toutiao_object.getTotalRecords() + " 条记录");
                //       layout_sousuoHis.setVisibility(View.GONE);//隐藏搜索历史
                //    progressDialog.dismiss();
                mDialog.dismiss();
                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
//                searchResultAdapter = new SearchResultAdapter(searchBean.doc.result, this);
//       searchEditResultAdapter = new SearchEditResultAdapter(toutiao_list,this);
                searchProductinfoAdapter= new SearchProductinfoAdapter(toutiao_list);
                //   lv_searchResult.getRefreshableView().setAdapter(searchEditResultAdapter);

                lv_searchResult.setAdapter(searchProductinfoAdapter);
                RecyclerView.ItemDecoration decoration = new MyDecoration(this);
                this.lv_searchResult.addItemDecoration(decoration);
                this.searchProductinfoAdapter.setOnItemClickListener(this);
                this.searchProductinfoAdapter.setOnItemLongClickListener(this);

                break;
            case 3:
                toutiao_list = new ArrayList<>();

                XinWen_productinfo toutiao_object1 = XinWenproductinfoJson.getdata(result, 0);//传入类型和数据
                toutiao_list.addAll(toutiao_object1.getT18908805728());
//                SearchBean searchBean = new Gson().fromJson(result, SearchBean.class);
                System.out.println("标题:" + toutiao_list.get(0).getName());
//                LogUtils.e("---", searchBean.doc.result.get(0).name);
                searchjiekuo.setText("搜索结果: " + toutiao_object1.getTotalRecords() + " 条记录");
                //       layout_sousuoHis.setVisibility(View.GONE);//隐藏搜索历史
                //     progressDialog.dismiss();
                mDialog.dismiss();
                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局

                searchProductinfoAdapter= new SearchProductinfoAdapter(toutiao_list);


                lv_searchResult.setAdapter(searchProductinfoAdapter);
                break;
        }
    }

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
     //   Toast.makeText(ProductinfoListEditActivity.this, " onResume()", Toast.LENGTH_SHORT).show();
        if (isPause){ //判断是否暂停
            isPause = false;
          // list = 新数据;
     //     adapter.setList(list); //需要adapter重新设置list的数据
            searchProductinfoAdapter= new SearchProductinfoAdapter(toutiao_list);
            //   lv_searchResult.getRefreshableView().setAdapter(searchEditResultAdapter);

            lv_searchResult.setAdapter(searchProductinfoAdapter);
            searchProductinfoAdapter.notifyDataSetChanged();//刷新
            RecyclerView.ItemDecoration decoration = new MyDecoration(this);
            this.lv_searchResult.addItemDecoration(decoration);
            this.searchProductinfoAdapter.setOnItemClickListener(this);
            this.searchProductinfoAdapter.setOnItemLongClickListener(this);
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
             //   ProductinfoListEditActivity.this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);
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
    @Override
    public void onItemClick(View view, int postion) {
        bean=new  XinWen_productinfo.T18908805728Entity();
        bean = toutiao_list.get(postion);
        id=bean.getId();
        switch (view.getId()) {
            case R.id.result_title://详细信息
             //  	Toast.makeText(this, "LongClick1 标题1", Toast.LENGTH_SHORT).show();
                //	System.out.println("LongClick1 标题");
            //    l = 1;
                frament2activity(postion,toutiao_list);
                break;

            case R.id.result_replace://修改
                	Toast.makeText(this, "LongClick1 标题2", Toast.LENGTH_SHORT).show();
                //	System.out.println("LongClick1 修改");
              //  l = 2;
                break;
            case R.id.result_delete://删除
                mAlertView = new AlertView("删除",bean.getName(), "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
                mAlertView.show();

                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {
        bean=new  XinWen_productinfo.T18908805728Entity();
        bean = toutiao_list.get(postion);
        if(bean != null){
            Toast.makeText(this, "LongClick "+bean.getName(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemClick(Object o,int position) {
      //  closeKeyboard();

        if(position==0) {
           // Toast.makeText(this, "点击了第确定按键", Toast.LENGTH_SHORT).show();
            String clickdel=xinWenURL.getClickdel()+bean.getId();
           DelData(clickdel);
          //  searchProductinfoAdapter.notifyDataSetChanged();
        }else{
          Toast.makeText(this, "此功能未完善", Toast.LENGTH_SHORT).show();

        }
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

                            if (toutiao_list.get(i).getId() ==id) {

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
}