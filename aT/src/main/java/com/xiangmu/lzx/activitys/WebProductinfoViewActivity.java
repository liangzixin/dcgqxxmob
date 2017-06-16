package com.xiangmu.lzx.activitys;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.xiangmu.lzx.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.lzx.Modle.Article;
import com.xiangmu.lzx.Modle.Customer;
import com.xiangmu.lzx.Modle.Liuyuan;
import com.xiangmu.lzx.Modle.Photo;
import com.xiangmu.lzx.Modle.ProductArticler;
import com.xiangmu.lzx.Modle.Shezhi;
import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.SearchDB;
import com.xiangmu.lzx.holder.ArticleHolder;
import com.xiangmu.lzx.holder.PhotoHolder;
import com.xiangmu.lzx.holder.ProductArticleHolder;
import com.xiangmu.lzx.jieping.ScreenShot;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XutilsGetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WebProductinfoViewActivity extends AppCompatActivity {
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<ProductArticler> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    ImageButton fenxiang;
    private  ImageButton button;
    private EditText edit;
    private RecyclerView recyclerView;
    private int customerid=1;
    private boolean login0=false;
    MultiTypeAdapter adapter;
    private static Gson gson = new Gson();
    private String username;
    private String pic_path;
    //  private MyApplication app;
    // MultiTypeAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo_image);
        button = (ImageButton) findViewById(R.id.subbtn);
        edit = (EditText) findViewById(R.id.edit);
        Intent intent = getIntent();
        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
        potolist=xinWenXiData.getUploadFileList();
        liuyuanlist=xinWenXiData.getProductArticlerList();
       // Context context = getItemView().getContext();
//        potolist=(List<UploadFile>)getIntent().getSerializableExtra("potolist");
//        liuyuanlist=(List<ProductArticler>)getIntent().getSerializableExtra("liuyuanlist");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //app = (MyApplication) getApplication(); //获得我们的应用程序MyApplication
        System.out.println("登录者:"+SearchDB.createDb(getApplication(), "userName"));
     if(SearchDB.createDb(getApplication(), "userName")!=null&&!SearchDB.createDb(getApplication(), "userName").equals("")) {
            username = SearchDB.createDb(getApplication(), "userName");
            customerid = Integer.parseInt(SearchDB.createDb(getApplication(), "customerid"));
            pic_path = SearchDB.createDb(getApplication(), "pic_path");
        }
        initDate();
        initview();
        assert recyclerView != null;

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //  MultiTypeAdapter adapter = new MultiTypeAdapter(this);
        adapter = new MultiTypeAdapter(this);
        //  adapter1 = new MultiTypeAdapter(this);
        // 注册两种 ViewType，对应两种数据类型（必须在设置到 RecyclerView 上之前注册！）
        adapter.registerViewType(Photo.class, PhotoHolder.class);
        adapter.registerViewType(Article.class, ArticleHolder.class);
        adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);

        recyclerView.setAdapter(adapter);
        // recyclerView.setAdapter(adapter1);
        adapter.add(mockArticle(0));

        for (int i = 0; i <potolist.size(); i++) {

            adapter.add(mockPhoto(i));
        }
        for (int i = 0; i <liuyuanlist.size(); i++) {

            adapter.add(mockLiuyuan(i));
        }
        button.setOnClickListener(c);
    }
    private void initview() {
       // final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        //  final String url ="http://www.dcgqxx.com/product/product_select.html?id=29547";
     final String url ="http://www.dcgqxx.com/product/product_select.html?id="+xinWenXiData.getId();
        System.out.println(url);
     //   final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        final String xinwentitle =xinWenXiData.getTitle();
        ImageButton imageback = null;
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回
        TextView duotu_gentie = null;
        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) findViewById(R.id.xinwen_xi_text_webview);
        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
        fenxiang = (ImageButton) findViewById(R.id.xinwen_xi_fenxiang);
        // getdata(url);//获得数据
        //点击finish
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //点击进入跟帖 详细页面
        //// TODO: 2015/11/14 点击进入跟帖 详细页面 完成
        duotu_gentie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WebProductinfoViewActivity.this,GenTieActivity.class));
            }
        });
        //点击打开扩展 详细页面
        //// TODO: 2015/11/14
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpopuwindow(view);
            }
        });

        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       //         ShareUtils.shareContent(WebProductinfoViewActivity.this, xinwentitle, url);
                //       ShareUtils.shareQQZore(WebProductinfoViewActivity.this, xinwentitle, url);
            }
        });
    }

    public void getdata(String url) {
        LogUtils.e("putongframenturl", "==" + url);

//        settings = webView.getSettings();
//        settings.setDomStorageEnabled(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setBlockNetworkImage(true);
//        settings.setSupportZoom(true);  //支持缩放
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
//        settings.setNeedInitialFocus(true);//当webview调用requestFocus时为webview设置节点
//        settings.setBuiltInZoomControls(true);
//        settings.setUseWideViewPort(true);
//        settings.setAppCacheEnabled(true);//是否使用缓存
//        settings.setTextSize(WebSettings.TextSize.NORMAL);
//        webView.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript(例如：alert())
        final CustomProgressDialog progress=new CustomProgressDialog(this,"正在加载中.....",R.anim.donghua_frame);
        progress.show();
//        webView.loadUrl(url);
        //设置打开页面的客户端WebViewClient,如果不设置,则调用系统默认浏览器打开地址
        // 当点击超链地址后不会新打开浏览器来访问，而是始终在本app中浏览页面
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                LogUtils.e("putongframenturl", "==" + url);
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                progress.dismiss();
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient(){
//            public void onRequestFocus(WebView view) {
//                super.onRequestFocus(view);
//                view.requestFocus();
//
//            }
//        });


//        String data = XutilsGetData.getData(getActivity(), url, null);
//        //判断本地数据是否存在  如果没有网络请求
//        if (data != null) {
//            getshowdata(data);
//        } else {
//            XutilsGetData.xUtilsHttp(getActivity(), url, url, new XutilsGetData.CallBackHttp() {
//                @Override
//                public void handleData(String data) {
//                    getshowdata(data);
//                }
//            });
//        }
    }
    /**
     * 提交的监听器
     */
    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = edit.getText().toString();
            if (msg.equals("") || msg == null) {
                new AlertDialog.Builder(WebProductinfoViewActivity.this).setMessage("不能为空").setPositiveButton("确定", null).show();
                return;
            }else{

                ProductArticler productArticler=new ProductArticler();
                productArticler.setArtreview_rootid(xinWenXiData.getId());
                productArticler.setArtreview_content(msg);
                DateTime dateTime =new DateTime();
                productArticler.setArtreview_time(dateTime.getDateFormatter());
                productArticler.setArtreview_authorid(customerid+"");
                Customer customer=new Customer();
                customer.setUsername(username);
                customer.setImageurl(pic_path);
                customer.setId(customerid);
                productArticler.setCustomer(customer);
                if(liuyuanlist.size()>0) {
                    liuyuanlist.add(0, productArticler);
                }else{
                    liuyuanlist.add(productArticler);

                }
                if(username==null) {
                  //  Intent intent2 = new Intent(WebProductinfoViewActivity.this, LoginActivity.class);getActivity()
                    Intent intent2 = new Intent(WebProductinfoViewActivity.this, LoginActivity.class);
                    startActivityForResult(intent2, 1000);
//                    SheZhiFrament.handle.sendEmptyMessage(1);
////                    finish();
                    //  getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }else{
                    UpArticlerFunction();
                    if(liuyuanlist.size()>0) {
                        adapter.add(mockLiuyuan(0));
                        edit.setText("");
                        edit.setFocusable(false);
                        Toast.makeText(WebProductinfoViewActivity.this, "留言成功！", Toast.LENGTH_SHORT).show();
                        adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
                    }
//                 NotifyFunction();
                }

            }

        }
    };
    /**
     * 提交留言
     */
    public void UpArticlerFunction() {
        int customerid0=0;

        String url=xinWenURL.getSavearticler()+xinWenXiData.getId()+"&msg="+edit.getText().toString()+"&customerid="+customerid;
        UpData(url);
    }
    /**
     * 通知数据发生改变
     */
    public void NotifyFunction() {
//        for (int i = 0; i <liuyuanlist.size(); i++) {

        adapter.add(mockLiuyuan(0));
        return;
//        }
//        recyclerView.deferNotifyDataSetChanged();
//      /  recyclerView.notifyDataSetChanged();
//        recyclerView.getChildItemId(item_p)
//      recyclerView.setAdapter(new SaveAdapter(WebProductinfoViewActivity.this,liuyuanlist));
        //  recyclerView.setAdapter(new SaveAdapter(WebProductinfoViewActivity.this,liuyuanlist));
        //  MultiTypeAdapter adapter = new MultiTypeAdapter(this);
        // adapter = new MultiTypeAdapter(WebProductinfoViewActivity.this);
        //  adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
        // for (int i = 0;i <((List<ProductArticler>)liuyuanlist.get(0)).size(); i++) {

//      adapter.add(mockLiuyuan(0));

        // adapter.add(mockLiuyuan(i));
        // }
    }
    //popuwindow设置
    private void getpopuwindow(View v) {
        final PopupWindow popu = new PopupWindow((int) (getWindowManager().getDefaultDisplay().getWidth() / 2.5), getWindowManager().getDefaultDisplay().getHeight() / 2);
        View view = View.inflate(this, R.layout.popwindow_detial, null);
        Button shouchang = (Button) view.findViewById(R.id.bt_save);
        Button jieping = (Button) view.findViewById(R.id.jieping);
        Button ziti = (Button) view.findViewById(R.id.ziti);
        Button yejian = (Button) view.findViewById(R.id.bt_yejian);
        //收藏按钮
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17
                int customerid0=0;
                int shezhitype0=2;


                if(username!=null){
                    if(SearchDB.createDb(getApplication(), "customerid")!=null)   customerid= Integer.parseInt(SearchDB.createDb(getApplication(), "customerid"));
                //    customerid0=Integer.parseInt(customerid);
//                    Toast.makeText(WebProductinfoViewActivity.this, "已登录...", Toast.LENGTH_SHORT).show();
                    String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId()+"&customerid="+customerid+"&shezhitype="+shezhitype0;
                    UpDataCollent(clickcount);
                }else{

                    Toast.makeText(WebProductinfoViewActivity.this, "还没有登录...", Toast.LENGTH_SHORT).show();
//                    SheZhiFrament.handle.sendEmptyMessage(1);
//                    finish();
                    Intent intent9 = new Intent(WebProductinfoViewActivity.this, LoginActivity.class);
                    startActivity(intent9);
              //    this.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }



            }
        });
        //截屏按钮
        jieping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17

                Toast.makeText(WebProductinfoViewActivity.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(WebProductinfoViewActivity.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(WebProductinfoViewActivity.this, PictureActivity.class);
                intent.putExtra("path", s);
                startActivity(intent);

            }
        });
        //改变字体按钮
        ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17
                // ZiTiScale.zitiStyle2(WebProductinfoViewActivity.this, view);
            }
        });
        //夜间模式按钮
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17
            }
        });
        popu.setContentView(view);
        popu.setFocusable(true);
        popu.setBackgroundDrawable(new ColorDrawable(0));
        popu.showAsDropDown(v, 0, 0);
    }


    public void initDate() {
        String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        int replaycount = xinWenXiData.getReplaycount();//获得跟帖数目  //收藏用
        int customerid0=0;
        int shezhitype0=1;
        if(username!=null); if(SearchDB.createDb(getApplication(), "customerid")!=null)   customerid=Integer.parseInt(SearchDB.createDb(getApplication(), "customerid"));

        String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId()+"&customerid="+customerid+"&shezhitype="+shezhitype0;
        String clickcount0=xinWenURL.getCount();
        //String data = xutilsGetData.getData(WebProductinfoViewActivity.this, clickcount, null);
        // String data = SharedPreferencesUtil.getData(this, clickcount, "");
        UpData(clickcount);
//        user_name = SearchDB.createDb(this, "userName");
//        if(!user_name.equals(""))
//        UpData(clickcount0);
        //   UpCount(clickcount0);
        System.out.println("clickcount="+clickcount );
        Log.e("aa", "******xinwentitle*******" + xinwentitle);
        //拿到当前日期
        String date = DateTime.getDate();
        MySqlOpenHelper mySqlOpenHelper = new MySqlOpenHelper(this);
        SQLiteDatabase writableDatabase = mySqlOpenHelper.getWritableDatabase();
        //查询数据库  当前日期 有无存储过 本页的标题
        Cursor cursor = writableDatabase.query("read_date", null, "date =?",
                new String[]{date}, null, null, null, null);
        //有没有当天的数据
        if (cursor.getCount() > 0) {
            ArrayList<String> biaoti = new ArrayList<>();//声明一个集合,用来存放遍历出来的标题
            while (cursor.moveToNext()) {   //遍历  拿到当天的 所有存储的标题
                String cursorString = cursor.getString(cursor.getColumnIndex("title"));
                biaoti.add(cursorString);
            }
            //当天数据中没有 本页的标题
            if (!biaoti.contains(xinwentitle)) {
                ContentValues values = new ContentValues();
                values.put("date", date + "");
                values.put("url", url + "");
                values.put("title", xinwentitle + "");
                values.put("num", 2);
                values.put("replaycount", replaycount + "");
//            values.put("url",url);存储详情页的地址  在 阅读记录里取出来
                writableDatabase.insert("read_date", null, values);
            }
        } else {
            ContentValues values = new ContentValues();
            values.put("date", date + "");
            values.put("url", url + "");
            values.put("title", xinwentitle + "");
            values.put("num", 2 + "");
            values.put("replaycount", replaycount + "");
            writableDatabase.insert("read_date", null, values);
        }
        //关闭
        cursor.close();
        writableDatabase.close();
    }
    public Article mockArticle(int seed) {
        Article article = new Article();
//        article.title = getResources().getStringArray(R.array.mock_title)[seed % 4];
//        article.content = getResources().getStringArray(R.array.mock_content)[seed % 4];
        article.title =xinWenXiData.getTitle();
        article.content =xinWenXiData.getXinwentext();
        article.dat =xinWenXiData.getCreateDate().substring(0,10);
        article.gsmz=xinWenXiData.getGsmz();
        article.gsdz=xinWenXiData.getGsdz();
        article.lxr=xinWenXiData.getLxr();
        article.lxdh=xinWenXiData.getLxdh();

        if(xinWenXiData.getZpxx()!=null) article.zpxx=xinWenXiData.getZpxx();
        if(xinWenXiData.getFwcs()!=null) article.fwcs=xinWenXiData.getFwcs();
        if(xinWenXiData.getGqxx()!=null) article.gqxx=xinWenXiData.getGqxx();
        article.productCategory=xinWenXiData.getProductCategory();
        return article;
    }
    public Liuyuan mockLiuyuan(int seed) {
        Liuyuan liuyuan= new Liuyuan();

        liuyuan.liuyuan_content=((ProductArticler)liuyuanlist.get(seed)).getArtreview_content();
        liuyuan.liuyuan_id=((ProductArticler)liuyuanlist.get(seed)).getArtreview_authorid();
        liuyuan.liuyuan_date=((ProductArticler)liuyuanlist.get(seed)).getArtreview_time();
        liuyuan.liuyuan_name=((ProductArticler)liuyuanlist.get(seed)).getCustomer().getUsername();
        liuyuan.liuyuan_imag=((ProductArticler)liuyuanlist.get(seed)).getCustomer().getImageurl();
        return liuyuan;
    }
    public Photo mockPhoto(int seed) {
        Photo photo = new Photo();
        photo.path=potolist.get(seed).getPath();
//        photo.photoId = new int[]{
//                R.drawable.img_sample1,
//                R.drawable.img_sample2,
//                R.drawable.img_sample3,
//                R.drawable.img_sample4
//        }[seed % 4];
        //  photo.description = getResources().getStringArray(R.array.mock_img_desc)[seed % 4];
        photo.photoId = 0;

        //  XutilsGetData.xUtilsImageiv(photo.imagePicture, "http://www.dcgqxx.com/upload/"+potolist.get(0).getPath(),View().getContext(),false);
        // XutilsGetData.xUtilsImageiv(photo.imagePicture, "http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg",this,true);
//        BitmapUtils bitmapUtils = new BitmapUtils(this);
//        bitmapUtils.display(photo.imagePicture,"http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg");
        photo.description =xinWenXiData.getTitle();
        return photo;
    }

    private void UpData(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (!responseInfo.result.equals("true")) {
                        login0=true;
                        SharedPreferencesUtil.saveData(WebProductinfoViewActivity.this, url, responseInfo.result);
                        String result = responseInfo.result;
                        String userName="";
                        String profile_image_url ="";
                        String jinbi ="";
//                                customerid="";
                        String shezhi="";
                        List<Shezhi> listshezhi=new ArrayList<Shezhi>();
                        try {
                            JSONObject myobject = new JSONObject(result);
                            userName= myobject.getString("username");
                            profile_image_url = myobject.getString("imageurl");
                            jinbi = myobject.getString("jinbi");
                            customerid= Integer.parseInt(myobject.getString("id"));
                            shezhi=myobject.getString("shezhi");

                            if (!myobject.isNull("shezhi")){
                                JSONArray shezhiArray=myobject.getJSONArray("shezhi");
                                for (int j=0;j<shezhiArray.length();j++){
                                    JSONObject shezhi0=shezhiArray.getJSONObject(j);
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject =shezhiArray.getJSONObject(j);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if(jsonObject != null){
                                        Shezhi tempAccount = gson.fromJson(jsonObject.toString(),Shezhi.class);
                                        listshezhi.add(tempAccount);
                                    }
                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String shezhi0= gson.toJson(listshezhi);
                        //     app.setSearchDB0(true);
//                                SearchDB.removeDb(getSharedPreferences("useInfo", Context.MODE_PRIVATE));
                        getSharedPreferences("useInfo", Context.MODE_PRIVATE).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).commit();

                    }

                }


                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(WebProductinfoViewActivity.this, "留言请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private void UpDataCollent(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    Toast.makeText(WebProductinfoViewActivity.this, "收藏请求成功!!!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(WebProductinfoViewActivity.this, "收藏请求失败!!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public Gson getGson() {
        return gson;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);  //这个super可不能落下，否则可能回调不了

        switch(requestCode){
            case 1000:
                username = SearchDB.createDb(this, "userName");
                customerid = Integer.parseInt(SearchDB.createDb(this, "customerid"));
                pic_path=SearchDB.createDb(this, "pic_path");

//                Log.d("TAG", "收到返回值了收到了了子了了了了了了子了了了了了了了"+ RESULT_OK);
//                if(resultCode == getActivity().RESULT_OK) {
//                    returnshezhi();
//                }
                break;
            case 1:
//                if(resultCode == getActivity().RESULT_OK){
//                    Log.d("TAG", "收到返回值了收到了了子了了了了了了子了了了了了了了");
//                }
                break;
        }
    }
//    @Override
//    public void onResume() {
//        //...更新View
//        super.onResume();
//        Toast.makeText(WebProductinfoViewActivity.this, "返回详细页面!!!", Toast.LENGTH_SHORT).show();
//    }
//    private void UpCount(final String url) {
//        if (!url.equals("")) {
//            httpUtils = new HttpUtils();
//
//            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    if (responseInfo.result != null) {
//                        SharedPreferencesUtil.saveData(WebProductinfoViewActivity.this, url, responseInfo.result);
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(WebProductinfoViewActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }

}
