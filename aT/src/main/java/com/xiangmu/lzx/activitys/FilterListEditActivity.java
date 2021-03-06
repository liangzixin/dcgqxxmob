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
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiangmu.lzx.CostomAdapter.MyGridViewAadapter;
import com.xiangmu.lzx.CostomAdapter.SearchFilterAdapter;
import com.xiangmu.lzx.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.lzx.CostomProgressDialog.SimpleArcDialog;

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


    public class FilterListEditActivity extends AppCompatActivity implements MyItemClickListener,MyItemLongClickListener,OnDismissListener,OnItemClickListener {

    private ImageButton back;
    private TextView noHotWords;
    private TextView searchjiekuo,filteradd;

    private LinearLayout layoutsearchResult;

    private RecyclerView lv_searchResult;
    private SearchView search_view;

    private MyGridViewAadapter gridViewAadapter;
    private List<String> list = new ArrayList<>();
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    private String tuijian;//推荐热词
    private String keywords;//搜索关键字
    private SearchFilterAdapter searchFilterAdapter;
    private MySqlitehelper mySqlitehelper;
    private SQLiteDatabase writableDatabase;
    private List<XinWen_productinfo.T18908805728Entity.AdsEntity> listads;//字段listads
    private List<XinWen_productinfo.FilterEntity> filter_list = new ArrayList<>();
    private XinWen_productinfo.FilterEntity bean;
    private XinWenURL xinWenURL = new XinWenURL();
    private String url = null;
    private SimpleArcDialog mDialog;
    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private Boolean isPause=false;
    private int id=0;
    private  int page=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterlistedit);
        bindViews();
        url = xinWenURL.getFilter(page);//最新url
        mySqlitehelper = new MySqlitehelper(this);
        writableDatabase = mySqlitehelper.getWritableDatabase();
        mDialog = new SimpleArcDialog(this);

        initSearchNews(url);

    //    client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void bindViews() {
        back = (ImageButton) findViewById(R.id.back);
        filteradd= (TextView) findViewById(R.id.result_add);
        noHotWords = (TextView) findViewById(R.id.noHotWords);
        layoutsearchResult = (LinearLayout) findViewById(R.id.searchResult);//搜索结果布局

        lv_searchResult = (RecyclerView) findViewById(R.id.lv_searchResult);//搜索结果
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        lv_searchResult.setLayoutManager(manager);
        lv_searchResult.setItemAnimator(new DefaultItemAnimator());
        searchjiekuo = (TextView) findViewById(R.id.lv_searchjiekuo);

        search_view = (SearchView) findViewById(R.id.search_view);
        search_view.setSubmitButtonEnabled(true);//是否显示确认搜索按钮
        search_view.setIconified(false);//设置搜索框默认展开

        search_view.onActionViewExpanded();//表示在内容为空时不显示取消的x按钮，内容不为空时显示.

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

    }


    //初始化各监听事件
    private void inintClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           FilterListEditActivity.this.finish();
           //    finish();
            }
        });
        filteradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentzhibo = new Intent(FilterListEditActivity.this, FilterAddActivity.class);
            //    intentzhibo.putExtra("FilterEntity", bean);

                startActivity(intentzhibo);

             //   this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);


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
//                initSearchNews(ServerURL.searchUrl1 + keywords + ServerURL.searchUrl2);//执行新闻搜索请求
                initSearchNews(ServerURL.filterUrl + keywords);//执行新闻搜索请求
                //添加数据
                ContentValues contentValues = new ContentValues();
//                contentValues.put("url",ServerURL.searchUrl1 + keywords + ServerURL.searchUrl2);
                contentValues.put("url", ServerURL.filterUrl + keywords);
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

    private void initSearchNews(String url) {

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
                                SharedPreferencesUtil.saveData(FilterListEditActivity.this, url, responseInfo.result);
                                paserData(1, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(FilterListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 2:
                   httpUtils.configCurrentHttpCacheExpiry(1000 * 10); //设置超时时间   10s
                    handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (responseInfo.result != null) {
                                SharedPreferencesUtil.saveData(FilterListEditActivity.this, url, responseInfo.result);
                                paserData(2, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(FilterListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
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
                                SharedPreferencesUtil.saveData(FilterListEditActivity.this, url, responseInfo.result);
                                paserData(3, responseInfo.result);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            mDialog.dismiss();
                            Toast.makeText(FilterListEditActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
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
                filter_list = new ArrayList<>();
                XinWen_productinfo toutiao_object = XinWenproductinfoJson.getdataFilter(result);//传入类型和数据
                filter_list.addAll(toutiao_object.getListfilterEntity());
//                SearchBean searchBean = new Gson().fromJson(result, SearchBean.class);
           //     System.out.println("标题:" + filter_list.get(0).getFilters());
//                LogUtils.e("---", searchBean.doc.result.get(0).name);
                searchjiekuo.setText("共: " + toutiao_object.getTotalRecords() + " 条");
                //       layout_sousuoHis.setVisibility(View.GONE);//隐藏搜索历史
                //    progressDialog.dismiss();

                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局
     //    searchResultAdapter = new SearchResultAdapter(searchBean.doc.result, this);
//       searchEditResultAdapter = new SearchEditResultAdapter(filter_list,this);
                searchFilterAdapter= new SearchFilterAdapter(filter_list);
                //   lv_searchResult.getRefreshableView().setAdapter(searchEditResultAdapter);

                lv_searchResult.setAdapter(searchFilterAdapter);
                RecyclerView.ItemDecoration decoration = new MyDecoration(this);
                this.lv_searchResult.addItemDecoration(decoration);
                this.searchFilterAdapter.setOnItemClickListener(this);
                this.searchFilterAdapter.setOnItemLongClickListener(this);
                mDialog.dismiss();
                break;
            case 3:
                filter_list = new ArrayList<>();
                XinWen_productinfo toutiao_object1 = XinWenproductinfoJson.getdataFilter(result);//传入类型和数据
                filter_list.addAll(toutiao_object1.getListfilterEntity());
//                SearchBean searchBean = new Gson().fromJson(result, SearchBean.class);
                System.out.println("标题:" + filter_list.get(0).getFilters());
//                LogUtils.e("---", searchBean.doc.result.get(0).name);
                searchjiekuo.setText("共: " + toutiao_object1.getTotalRecords() + " 条");
                //       layout_sousuoHis.setVisibility(View.GONE);//隐藏搜索历史
                //     progressDialog.dismiss();
         //       mDialog.dismiss();
                layoutsearchResult.setVisibility(View.VISIBLE);//显示搜索结果布局

                searchFilterAdapter= new SearchFilterAdapter(filter_list);
                lv_searchResult.setAdapter(searchFilterAdapter);

//                getData(2, url);
//                //   progressDialog.show();
//                mDialog.show();
                mDialog.dismiss();
                break;
        }
    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   Toast.makeText(FilterListEditActivity.this, "onDestroy()", Toast.LENGTH_SHORT).show();
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
    //   Toast.makeText(FilterListEditActivity.this, " onPause()", Toast.LENGTH_SHORT).show();
        isPause = true; //记录页面已经被暂停
    }

    @Override
    protected void onResume() {
        super.onResume();

     ///    Toast.makeText(FilterListEditActivity.this, " onResume()", Toast.LENGTH_SHORT).show();
        if (isPause){ //判断是否暂停
            isPause = false;
            initSearchNews(url);
       }

    }
    //跳转详细页面方法
    @SuppressLint("NewApi")
    private void frament2activity(int position, List<XinWen_productinfo.FilterEntity> filter_list) {
        int pos;
        if (listads == null) {//判断有没有轮播图如果没有就不添加轮播布局  布局就和原来一样
            pos = position;
        } else {
            pos = position - 1;//有轮播图的时候点listview第二条才是数据list集合中的第一条
        }
        LogUtils.e("xinwenadapter", "postion==" + pos);


        //传入详细页面的数据
        ArrayList<UploadFile> potolist = new ArrayList<>();
        List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> liuyuenlist = new ArrayList<>();
        //List<PhotoImage> potolist2 = new ArrayList<>();
        XinWenXiData xinWenXi = new XinWenXiData();
        xinWenXi.setId(filter_list.get(pos).getId());
        int bujutype = 0;
      // bujutype = filter_list.get(pos).getLanmu();
        xinWenXi.setBujuType(bujutype);
//        xinWenXi.setLanMuType(daohangtype);
        xinWenXi.setLanMuType(1);

        xinWenXi.setProductArticlerList(liuyuenlist);

        //根据类型选择跳转的详细页面
        switch (bujutype) {
            case XinWen_adapter.TYPE_putong:
            case XinWen_adapter.TYPE_zhuanti:
            case XinWen_adapter.TYPE_zhibo:
                LogUtils.e("xinwenadapter", "TYPE_zhibo==" + bujutype);

                xinWenXi.setUrl("bbbbb");//详细页面url
                //跳转到详细页
          //      Intent intentzhibo = new Intent(ProductinfoListEditActivity.this, WebProductinfoViewActivity.class);
                Intent intentzhibo = new Intent(this, WebProductinfoViewActivity.class);
                intentzhibo.putExtra("xinwendata", xinWenXi);

                startActivity(intentzhibo);
             //   ProductinfoListEditActivity.this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);
                this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);
                break;
            case XinWen_adapter.type_duotu:

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
        bean=new  XinWen_productinfo.FilterEntity();

        bean = filter_list.get(postion);
        id=bean.getId();
        switch (view.getId()) {
            case R.id.result_title://详细信息

                frament2activity(postion,filter_list);
                break;

            case R.id.result_replace://修改
                Intent intentzhibo = new Intent(this, FilterDetailActivity.class);
                intentzhibo.putExtra("FilterEntity", bean);

                startActivity(intentzhibo);

                this.overridePendingTransition(R.anim.xinwen_inactivity, R.anim.xinwen_inactivity);

                break;

            case R.id.result_delete://删除
                mAlertView = new AlertView("删除",bean.getFilters(), "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
                mAlertView.show();

                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {
        bean=new  XinWen_productinfo.FilterEntity();
        bean = filter_list.get(postion);
        if(bean != null){
            Toast.makeText(this, "LongClick "+bean.getFilters(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemClick(Object o,int position) {
      //  closeKeyboard();

        if(position==0) {
           // Toast.makeText(this, "点击了第确定按键", Toast.LENGTH_SHORT).show();
            String clickdel=xinWenURL.getFilterdel()+bean.getId();
           DelData(clickdel);
          //  searchFilterAdapter.notifyDataSetChanged();
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
                        Toast.makeText(FilterListEditActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                        isPause = true;
                        onResume();
                    }

                }


                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(FilterListEditActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
