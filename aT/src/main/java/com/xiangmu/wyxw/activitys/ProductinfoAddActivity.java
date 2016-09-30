package com.xiangmu.wyxw.activitys;

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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MaterialEditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemSelectedListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.xiangmu.wyxw.CostomAdapter.SaveAdapter;
import com.xiangmu.wyxw.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.wyxw.Modle.Dxfw;
import com.xiangmu.wyxw.Modle.Edu;
import com.xiangmu.wyxw.Modle.Liuyuan;
import com.xiangmu.wyxw.Modle.ProductArticler;
import com.xiangmu.wyxw.Modle.ProductCategory;
import com.xiangmu.wyxw.Modle.Sex;
import com.xiangmu.wyxw.Modle.UploadFile;
import com.xiangmu.wyxw.Modle.Zpnl;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Setting_Utils.ShareUtils;
import com.xiangmu.wyxw.Setting_Utils.ZiTiScale;
import com.xiangmu.wyxw.holder.ArticleHolder;
import com.xiangmu.wyxw.holder.PhotoHolder;
import  com.xiangmu.wyxw.Modle.Article;
import com.xiangmu.wyxw.Modle.Photo;
import com.xiangmu.wyxw.holder.ProductArticleHolder;
import com.xiangmu.wyxw.holder.ProductinfoAddHolder;
import com.xiangmu.wyxw.jieping.ScreenShot;
import com.xiangmu.wyxw.utils.DateTime;
import com.xiangmu.wyxw.utils.LogUtils;
import com.xiangmu.wyxw.utils.MySqlOpenHelper;
import com.xiangmu.wyxw.utils.ServerURL;
import com.xiangmu.wyxw.utils.SharedPreferencesUtil;
import com.xiangmu.wyxw.utils.XinWenURL;
import com.xiangmu.wyxw.utils.XinWenXiData;
import com.xiangmu.wyxw.utils.XutilsGetData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ProductinfoAddActivity extends AppCompatActivity {
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<ProductArticler> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    private static final String[] m={"请选择类别","招聘","求职","出售","出租","供求","二手","其它","铺面","家居"};
    // private static final List msex=new List() { };
    ImageButton fenxiang;
    //
    private Spinner articlerSpinner = null;
    private Spinner spinner_sex = null;
    private Spinner spinner_sex2 = null;
    private Spinner spinner_dxfw= null;
    private Spinner spinner_dxfw2= null;
    private Spinner spinner_nl= null;
    private Spinner spinner_xl= null;
    private Spinner spinner_xl2= null;
    private RecyclerView recyclerView;
    private MaterialEditText productinfo_name;
    private MaterialEditText productinfo_sxcy;

    LinearLayout category1;
    LinearLayout category2;
        @ViewId(R.id.productinfo_content)  MaterialEditText productinfo_content;
 //@ViewId(R.id.productinfo_sxcy) public MaterialEditText productinfo_sxcy;
//    @ViewId(R.id.productinfo_qjnl)  MaterialEditText productinfo_qjnl;
   // @ViewId(R.id.spin_sex2) public MaterialSpinner spin_sex2;
//    @ViewId(R.id.spin_dxfw2) public MaterialSpinner spin_dxfw2;
//    @ViewId(R.id.spin_xl2) public MaterialSpinner spin_xl2;
//        @ViewId(R.id.category3) public LinearLayout category3;
//        @ViewId(R.id.category4) public LinearLayout category4;
    //   MultiTypeAdapter adapter;
    // MultiTypeAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo_add);
//        @ViewId(R.id.category1) public LinearLayout category1;
//        @ViewId(R.id.category2) public LinearLayout category2;
//        @ViewId(R.id.category3) public LinearLayout category3;
//        @ViewId(R.id.category4) public LinearLayout category4;


        productinfo_name= (MaterialEditText) findViewById(R.id.productinfo_name);
        articlerSpinner = (MaterialSpinner) findViewById(R.id.spin_articler);
        spinner_sex = (MaterialSpinner) findViewById(R.id.spin_sex);
        spinner_dxfw= (MaterialSpinner) findViewById(R.id.spin_dxfw);
        spinner_nl= (MaterialSpinner) findViewById(R.id.spin_nl);
        spinner_xl= (MaterialSpinner) findViewById(R.id.spin_xl);
        category1=(LinearLayout) findViewById(R.id.category1);
        category2=(LinearLayout) findViewById(R.id.category2);

        spinner_sex2 = (MaterialSpinner) findViewById(R.id.spin_sex2);
        spinner_dxfw2= (MaterialSpinner) findViewById(R.id.spin_dxfw2);
        spinner_xl2= (MaterialSpinner) findViewById(R.id.spin_xl2);

        List  msex=Sex.getValues();
        List  dxfw= Dxfw.getValues();
        List  nl= Zpnl.getValues();
        List  xl= Edu.getValues();
        ArrayAdapter adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,m);
        articlerSpinner.setAdapter(adapter);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,msex);
        spinner_sex.setAdapter(adapter);
        spinner_sex2.setAdapter(adapter);

        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,dxfw);
        spinner_dxfw.setAdapter(adapter);
        spinner_dxfw2.setAdapter(adapter);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,nl);
        spinner_nl.setAdapter(adapter);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,xl);

        spinner_xl.setAdapter(adapter);
        spinner_xl2.setAdapter(adapter);
        articlerSpinner.setSelection(0, true);
        category1.setVisibility(View.VISIBLE);
        //给Spinner添加事件监听
        articlerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            //当选中某一个数据项时触发该方法
            /*
             * parent接收的是被选择的数据项所属的 Spinner对象，
             * view参数接收的是显示被选择的数据项的TextView对象
             * position接收的是被选择的数据项在适配器中的位置
             * id被选择的数据项的行号
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println(spinner==parent);//true
                //System.out.println(view);
                //String data = adapter.getItem(position);//从适配器中获取被选择的数据项
                //String data = list.get(position);//从集合中获取被选择的数据项
              //  int data =(int)articlerSpinner.getItemAtPosition(position);//从spinner中获取被选择的数据
             //   int data =1;
                switch (position) {
                    case 0:
                    case 1:
                        productinfo_name.setHint("招聘标题");
                        category1.setVisibility(View.VISIBLE);
                        category2.setVisibility(View.GONE);
                        break;
                    case 2:
                     productinfo_name.setHint("求职标题");
                        category1.setVisibility(View.GONE);
                        category2.setVisibility(View.VISIBLE);
                        break;
//                    case 3:
//                        category3.setVisibility(View.VISIBLE);
//                        break;
//                    case 4:
//                        category4.setVisibility(View.VISIBLE);
//                        break;
                }
//                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //      Intent intent = getIntent();
//        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
//        potolist=(List<UploadFile>)getIntent().getSerializableExtra("potolist");
//        liuyuanlist=(List<ProductArticler>)getIntent().getSerializableExtra("liuyuanlist");
//          recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        initDate();
      initview();
        //    assert recyclerView != null;

//          recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


//       MultiTypeAdapter adapter1 = new MultiTypeAdapter(this);
//        adapter = new MultiTypeAdapter(this);
//        //  adapter1 = new MultiTypeAdapter(this);
//        // 注册两种 ViewType，对应两种数据类型（必须在设置到 RecyclerView 上之前注册！）
//        adapter.registerViewType(Photo.class, PhotoHolder.class);
//        adapter1.registerViewType(Article.class, ProductinfoAddHolder.class);
//        adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
//
//        recyclerView.setAdapter(adapter1);
//        recyclerView.setAdapter(adapter1);
//      adapter1.add(mockArticle(0));
//
//        for (int i = 0; i <((List<UploadFile>)potolist.get(0)).size(); i++) {
//
//            adapter.add(mockPhoto(i));
//        }
//        for (int i = 0; i <((List<ProductArticler>)liuyuanlist.get(0)).size(); i++) {
//
//            adapter.add(mockLiuyuan(i));
//        }
        //   button.setOnClickListener(c);
    }
    private void initview() {
//        final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
//        final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用

        ImageButton imageback = null;
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回
        TextView duotu_gentie = null;
        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) findViewById(R.id.xinwen_xi_text_webview);
//        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
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
                startActivity(new Intent(ProductinfoAddActivity.this,GenTieActivity.class));
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
              //  ShareUtils.shareContent(ProductinfoAddActivity.this, xinwentitle, url);
              if(articlerSpinner.getSelectedItemPosition()==0||productinfo_name.equals("")||productinfo_content.equals(""))  {
                  Toast.makeText(ProductinfoAddActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();

              }

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
//    View.OnClickListener c = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String msg = edit.getText().toString();
//            if (msg.equals("") || msg == null) {
//                new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("不能为空").setPositiveButton("确定", null).show();
//                return;
//            }else{
//
//                ProductArticler productArticler=new ProductArticler();
//
//
//                productArticler.setArtreview_rootid(xinWenXiData.getId());
//                productArticler.setArtreview_content(msg);
//                DateTime dateTime =new DateTime();
//                productArticler.setArtreview_time(dateTime.getDateFormatter());
//                productArticler.setArtreview_authorid(1+"");
//                ((List<ProductArticler>)liuyuanlist.get(0)).add(0,productArticler);
//                NotifyFunction();
//            }
//            //  MultiTypeAdapter adapter = new MultiTypeAdapter(ProductinfoAddActivity.this);
//            // adapter.add(mockLiuyuan( liuyuanlist.size()));
//            new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("留言成功！").setPositiveButton("确定", null).show();
//            edit.setText("");
//        }
//    };
    /**
     * 通知数据发生改变
     */
    public void NotifyFunction() {
        //recyclerView.deferNotifyDataSetChanged();
        ///  recyclerView.notifyDataSetChanged();
        //  recyclerView.getChildItemId(item_p)
        // recyclerView.setAdapter(new SaveAdapter(ProductinfoAddActivity.this,liuyuanlist));
        //  recyclerView.setAdapter(new SaveAdapter(ProductinfoAddActivity.this,liuyuanlist));
        //  MultiTypeAdapter adapter = new MultiTypeAdapter(this);
        // adapter = new MultiTypeAdapter(ProductinfoAddActivity.this);
        //  adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
        // for (int i = 0;i <((List<ProductArticler>)liuyuanlist.get(0)).size(); i++) {
//        adapter.add(mockLiuyuan(0));
//        String url=xinWenURL.getSavearticler()+xinWenXiData.getId()+"&msg="+edit.getText().toString();
//        UpData(url);
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

            }
        });
        //截屏按钮
        jieping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17

                Toast.makeText(ProductinfoAddActivity.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(ProductinfoAddActivity.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(ProductinfoAddActivity.this, PictureActivity.class);
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
                // ZiTiScale.zitiStyle2(ProductinfoAddActivity.this, view);
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
        String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId();
        String clickcount0=xinWenURL.getCount();
        //String data = xutilsGetData.getData(ProductinfoAddActivity.this, clickcount, null);
        // String data = SharedPreferencesUtil.getData(this, clickcount, "");
        UpData(clickcount);
        UpData(clickcount0);
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
        XinWenXiData xinWenXiData=new XinWenXiData();
//        article.title = getResources().getStringArray(R.array.mock_title)[seed % 4];
//        article.content = getResources().getStringArray(R.array.mock_content)[seed % 4];
        article.title =xinWenXiData.getTitle();
        article.content =xinWenXiData.getXinwentext();
//        article.dat =xinWenXiData.getCreateDate().substring(0,10);
        article.dat ="aaaa";

        article.gsmz=xinWenXiData.getGsmz();
        article.gsdz=xinWenXiData.getGsdz();
        article.lxr=xinWenXiData.getLxr();
        article.lxdh=xinWenXiData.getLxdh();

        if(xinWenXiData.getZpxx()!=null) article.zpxx=xinWenXiData.getZpxx();
        if(xinWenXiData.getFwcs()!=null) article.fwcs=xinWenXiData.getFwcs();
//        article.productCategory=xinWenXiData.getProductCategory();
        ProductCategory productCategory0=new ProductCategory();
        productCategory0.setId(1);

        article.productCategory=productCategory0;
        return article;

    }
    public Liuyuan mockLiuyuan(int seed) {
        Liuyuan liuyuan= new Liuyuan();

        liuyuan.liuyuan_content=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_content();
        liuyuan.liuyuan_id=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_authorid();
        liuyuan.liuyuan_date=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_time();

        return liuyuan;
    }
    public Photo mockPhoto(int seed) {
        Photo photo = new Photo();
        photo.path=((List<UploadFile>)potolist.get(0)).get(seed).getPath();
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
                    if (responseInfo.result != null) {
                        SharedPreferencesUtil.saveData(ProductinfoAddActivity.this, url, responseInfo.result);


                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(ProductinfoAddActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
//    private void UpCount(final String url) {
//        if (!url.equals("")) {
//            httpUtils = new HttpUtils();
//
//            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    if (responseInfo.result != null) {
//                        SharedPreferencesUtil.saveData(ProductinfoAddActivity.this, url, responseInfo.result);
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(ProductinfoAddActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }
}

