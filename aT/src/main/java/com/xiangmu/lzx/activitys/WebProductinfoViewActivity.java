package com.xiangmu.lzx.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;

import com.xiangmu.lzx.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.lzx.Modle.Article;
import com.xiangmu.lzx.Modle.Customer;
import com.xiangmu.lzx.Modle.Liuyuan;
import com.xiangmu.lzx.Modle.Photo;

import com.xiangmu.lzx.Modle.Shezhi;
import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.SearchDB;
import com.xiangmu.lzx.Setting_Utils.ShareUtils;
import com.xiangmu.lzx.holder.ArticleHolder;
import com.xiangmu.lzx.holder.PhotoHolder;
import com.xiangmu.lzx.holder.ProductArticleHolder;
import com.xiangmu.lzx.jieping.ScreenShot;
import com.xiangmu.lzx.utils.ConstantsLzx;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;
import com.xiangmu.lzx.utils.ThreadManager;
import com.xiangmu.lzx.utils.Utils;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_productinfo;
import com.xiangmu.lzx.utils.XutilsGetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.ImageLoader;

import static com.xiangmu.lzx.Setting_Utils.TouXiangCache.readStream;

//import com.nostra13.universalimageloader.core.ImageLoader;

public class WebProductinfoViewActivity extends AppCompatActivity implements WbShareCallback {
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    ImageButton fenxiang;
    private  ImageButton button;
    private EditText edit;
    private RecyclerView recyclerView;
    private int customerid=0;
    private boolean login0=false;
    MultiTypeAdapter adapter;
    private static Gson gson = new Gson();
    private String username;
    private String username0;
    private String pic_path;
//    private UMImage imageurl;
    private String  shezhi;
      private MyApplication app;
    private  String xinwentitle="";
    private String url ="",url0="";
    private Bundle params;
    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
    private int mExtarFlag = 0x00;
    private WbShareHandler shareHandler;
    private int mShareType = SHARE_CLIENT;
    public static final String KEY_SHARE_TYPE = "key_share_type";
    public static final int SHARE_CLIENT = 1;
    public static final int SHARE_ALL_IN_ONE = 2;
    public static    Bitmap bitmap = null;
    int flag = 0;
    private static final int THUMB_SIZE = 150;
    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    public static IWXAPI api;
    //    private UMShareListener mShareListener;
  //  private ShareAction mShareAction;
    // MultiTypeAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, ConstantsLzx.APP_ID);
        // 将该app注册到微信
        api.registerApp(ConstantsLzx.APP_ID);
        setContentView(R.layout.activity_productinfo_image);
        button = (ImageButton) findViewById(R.id.subbtn);
        edit = (EditText) findViewById(R.id.edit);
        app =(MyApplication)getApplication();
        Intent intent = getIntent();
        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
        potolist=xinWenXiData.getUploadFileList();
        liuyuanlist=xinWenXiData.getProductArticlerList();
       // Context context = getItemView().getContext();
//        potolist=(List<UploadFile>)getIntent().getSerializableExtra("potolist");
//        liuyuanlist=(List<ProductArticler>)getIntent().getSerializableExtra("liuyuanlist");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //app = (MyApplication) app.getCtx(); //获得我们的应用程序MyApplication
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
        if(potolist.size()>0){
           url0="http://www.dcgqxx.com/upload/"+potolist.get(0).getPath();
        }else{
          url0="http://www.dcgqxx.com/css/images/dc.gif";
        }

    //    getBitmap();
        for (int j = 0; j <liuyuanlist.size(); j++) {

            adapter.add(mockLiuyuan(j));
        }
        button.setOnClickListener(c);
        mShareType = getIntent().getIntExtra(KEY_SHARE_TYPE, SHARE_CLIENT);
        shareHandler = new WbShareHandler(this);
      shareHandler.registerApp();
     shareHandler.setProgressColor(0xff33b5e5);

    }
    private Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    bitmap = (Bitmap) msg.obj;
               //     iv_icon.setImageBitmap(bitmap); //设置imageView显示的图片
                    break;
                case 1:
                    Toast.makeText(WebProductinfoViewActivity.this, "图片加载失败", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent,this);
    }
    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMessage(boolean hasText, boolean hasImage) {
        sendMultiMessage(hasText, hasImage);
    }
    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage(boolean hasText, boolean hasImage) {


        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
   if (hasText) {
            weiboMessage.textObject = getTextObj();
        }
        if (hasImage) {

            weiboMessage.imageObject = getImageObj();
        }
//        if(multiImageCheckbox.isChecked()){
//            weiboMessage.multiImageObject = getMultiImageObject();
//        }
//        if(videoCheckbox.isChecked()){
//            weiboMessage.videoSourceObject = getVideoObject();
//        }
        shareHandler.shareMessage(weiboMessage, false);

    }
    /**
     * 创建文本消息对象。
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        textObject.title = "xxxx";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }
    /**
     * 创建图片消息对象。
     * @return 图片消息对象。
     */
    public void getBitmap(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = getImageFromNet(url0);
                if (bitmap != null) {
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = bitmap;
                    myhandler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 1;
                    myhandler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(bitmap);
        return imageObject;
    }
    private Bitmap getImageFromNet(String url) {
        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET"); //设置请求方法
            conn.setConnectTimeout(10000); //设置连接服务器超时时间
            conn.setReadTimeout(5000);  //设置读取数据超时时间

            conn.connect(); //开始连接

            int responseCode = conn.getResponseCode(); //得到服务器的响应码
            if (responseCode == 200) {
                //访问成功
                InputStream is = conn.getInputStream(); //获得服务器返回的流数据
               // Bitmap bitmap = BitmapFactory.decodeStream(is); //根据流数据 创建一个bitmap对象
                if (is == null){
                    throw new RuntimeException("stream is null");
                }else{
                    try {
                        byte[] data=readStream(is);
                        if(data!=null){
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    is.close();
                }
                return bitmap;

            } else {
                //访问失败
                Log.d("lyf--", "访问失败===responseCode：" + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); //断开连接
            }
        }
        return null;
    }
    /**
     * 获取分享的文本模板。
     */
    private String getSharedText() {
        int formatId = R.string.weibosdk_demo_share_text_template;
        String format = getString(formatId);
        String text = format;
      //  if (mTextCheckbox.isChecked() || mImageCheckbox.isChecked()) {
            text = "@大屁老师，这是一个很漂亮的小狗，朕甚是喜欢-_-!! #大屁老师#http://weibo.com/p/1005052052202067/home?from=page_100505&mod=TAB&is_all=1#place";
      //  }
        text = xinWenXiData.getDigest()+url;
        return text;
    }

    private void initview() {
        // final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        //  final String url ="http://www.dcgqxx.com/product/product_select.html?id=29547";
       url = "http://www.dcgqxx.com/product/product_select.html?id=" + xinWenXiData.getId();
        System.out.println(url);
        //   final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        xinwentitle = xinWenXiData.getTitle();
        ImageButton imageback = null;
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回
        TextView duotu_gentie = null;
        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) findViewById(R.id.xinwen_xi_text_webview);
//       if(xinWenXiData.getProductArticlerList()!=null) {
        duotu_gentie.setText(xinWenXiData.getProductArticlerList().size() + "");
//       }
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
                startActivity(new Intent(WebProductinfoViewActivity.this, GenTieActivity.class));
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
        //    mShareListener = new CustomShareListener(this);
//        fenxiang.setOnClickListener(new View.OnClickListener() {
//            @Override
//                public void onClick(View view) {
//     //           ShareUtils.shareContent(WebProductinfoViewActivity.this, xinwentitle, url);
////                       ShareUtils.shareQQZore(WebProductinfoViewActivity.this, xinwentitle, url);
//                new ShareAction(WebProductinfoViewActivity.this)
//                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
//                   //     .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
//                      //  .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
//                        .setShareboardclickCallback(new ShareBoardlistener() {
//                            @Override
//                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
////                                if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
////                                    Toast.makeText(WebProductinfoViewActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
////                                } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
////                                    Toast.makeText(WebProductinfoViewActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();
////
////                                } else {
//                                    UMWeb web = new UMWeb(url);
//                                    web.setTitle(xinwentitle);
//                                    web.setDescription(xinwentitle);
//                                    web.setThumb(new UMImage(WebProductinfoViewActivity.this, R.drawable.ic_launcher));
//                                    new ShareAction(WebProductinfoViewActivity.this).withMedia(web)
//                                            .setPlatform(share_media)
//                                            .setCallback(mShareListener)
//                                            .share();
////                                }
//                            }
//                        })
//                       .open();
//            }
//        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
//                String   shareUrl="http://inews.gtimg.com/newsapp_bt/0/876781763/1000";
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, xinwentitle + "\n" + xinWenXiData.getXinwentext() + "\n" +shareUrl);
//                sendIntent.setType("text/plain");
////          sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//微信朋友
////          sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qqfav.widget.QfavJumpActivity");//保存到QQ收藏
////          sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qlink.QlinkShareJumpActivity");//QQ面对面快传
////          sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.qfileJumpActivity");//传给我的电脑
//                sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");//QQ好友或QQ群
////          sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//微信朋友圈，仅支持分享图片
//                //   startActivityForResult(sendIntent, QUN_QUEST);
//                startActivityForResult(sendIntent, 1000);

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
        final CustomProgressDialog progress=new CustomProgressDialog(this,"正在加载中.....",R.drawable.donghua_frame);
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

                XinWen_productinfo.T18908805728Entity.ProductArticlerEntity productArticler=new XinWen_productinfo.T18908805728Entity.ProductArticlerEntity();
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
        int shezhitype0=2;
        String url=xinWenURL.getSavearticler()+xinWenXiData.getId()+"&msg="+edit.getText().toString()+"&customerid="+customerid+"&shezhitype="+shezhitype0;
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
                    if(SearchDB.createDb(app.getCtx(), "customerid")!=null)   customerid= Integer.parseInt(SearchDB.createDb(app.getCtx(), "customerid"));
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
     //   imageurl = new UMImage(this,"http://www.dcgqxx.com/css/images/dc2.png");
     //   imageurl.setThumb(new UMImage(this, R.drawable.thumb));
      //  if(username!=null);
      //  if(SearchDB.createDb(app.getCtx(), "customerid")!=null)   customerid=Integer.parseInt(SearchDB.createDb(app.getCtx(), "customerid"));

        String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId()+"&customerid="+customerid+"&shezhitype="+shezhitype0;
    //    String clickcount0=xinWenURL.getCount();
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

        liuyuan.liuyuan_content=((XinWen_productinfo.T18908805728Entity.ProductArticlerEntity)liuyuanlist.get(seed)).getArtreview_content();
        liuyuan.liuyuan_id=((XinWen_productinfo.T18908805728Entity.ProductArticlerEntity)liuyuanlist.get(seed)).getArtreview_authorid();
        liuyuan.liuyuan_date=((XinWen_productinfo.T18908805728Entity.ProductArticlerEntity)liuyuanlist.get(seed)).getArtreview_time();
        liuyuan.liuyuan_name=((XinWen_productinfo.T18908805728Entity.ProductArticlerEntity)liuyuanlist.get(seed)).getCustomer().getUsername();
        liuyuan.liuyuan_imag=((XinWen_productinfo.T18908805728Entity.ProductArticlerEntity)liuyuanlist.get(seed)).getCustomer().getImageurl();
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
                           app.setSearchDB0(true);
//                                SearchDB.removeDb(getSharedPreferences("useInfo", Context.MODE_PRIVATE));
                  //      getSharedPreferences("useInfo", Context.MODE_MULTI_PROCESS).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).commit();
                        PreferenceManager.getDefaultSharedPreferences(getApplication());
                     //   getApplication().getSharedPreferences("useInfo", Context.MODE_MULTI_PROCESS);
                       getSharedPreferences("useInfo",Context.MODE_PRIVATE).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).commit();
                      //  getApplication().getSharedPreferences("useInfo",Context.MODE_MULTI_PROCESS).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).commit();
//                    finish();

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
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1000:
                shezhi= SearchDB.createDb(this, "shezhi");
           app.setSearchDB0(true);
                username = SearchDB.createDb(this, "userName");
                customerid = Integer.parseInt(SearchDB.createDb(this, "customerid"));
                pic_path=SearchDB.createDb(this, "pic_path");
                getSharedPreferences("login", MODE_PRIVATE).edit().putBoolean("login", false).commit();
                username0 = SearchDB.createDb(this, "userName");
                Log.d("TAG", "收到返回值了收到了了子了了了了了了子了了了了了了了"+ username0 );
                Log.d("TAG", "收到返回值了收到了了子了了了了了了子了了了了了了了"+ username );
//                if(resultCode == getActivity().RESULT_OK) {
//                    returnshezhi();
//                }
                break;
            case 1:
//                if(resultCode == getActivity().RESULT_OK){
//                    Log.d("TAG", "收到返回值了收到了了子了了了了了了子了了了了了了了");
//                }
                break;
            case  Constants.REQUEST_QQ_SHARE:
                Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
                break;
        }
    }
    private void showShareDialog() {
  //      Log.d(TAG, "showShareDialog");

        View view = LayoutInflater.from(this).inflate(R.layout.viewliang, null);
        // 设置style 控制默认dialog带来的边距问题
        final Dialog dialog = new Dialog(this, R.style.common_dialog);
        dialog.setContentView(view);
        dialog.show();

        // 监听
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.view_share_pengyou:
                        share2Wx(false);
                   //     finish();
                    case R.id.view_share_weixin:
//                        WXWebpageObject webpage = new WXWebpageObject();
//                        // webpage.webpageUrl = "http://www.qq.com";
//                        webpage.webpageUrl =url;
//                        WXMediaMessage msg = new WXMediaMessage(webpage);
////                        msg.title = "WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
////                        msg.description = "WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
//                        msg.title=xinWenXiData.getTitle();
//                        msg.description=xinWenXiData.getDigest();
////                     Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mr_button_connected_00_light);
//
//                        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
//                        bitmap.recycle();
//                        msg.thumbData = Utils.bmpToByteArray(thumbBmp, true);
//
//                        SendMessageToWX.Req req = new SendMessageToWX.Req();
//                        req.transaction = buildTransaction("webpage");
//                        req.message = msg;
//                        req.scene = mTargetScene;
//                        api.sendReq(req);
                     share2Wx(true);
                  //   finish();
                        break;
                    case R.id.view_share_qq:
                        params = new Bundle();

                       params.putString(QQShare.SHARE_TO_QQ_TITLE,  xinwentitle);
                       params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);
                       params.putString(QQShare.SHARE_TO_QQ_SUMMARY, xinWenXiData.getDigest());
                       if(potolist.size()>0){
                           params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://www.dcgqxx.com/upload/"+potolist.get(0).getPath());
                       }else {
                           params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://www.dcgqxx.com/css/images/dc.gif");
                       }
                        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,R.string.app_name+"");

                        doShareToQQ(params);

                     //   onShare2Weixin();
                        break;
                    case R.id.view_share_qzone:
                        params = new Bundle();
                        params.putString(QQShare.SHARE_TO_QQ_TITLE,xinwentitle);
                        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
                        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, xinWenXiData.getDigest());
                        String imageUrl="";
                        if(potolist.size()>0){
                       imageUrl="http://www.dcgqxx.com/upload/"+potolist.get(0).getPath();
                        }else {
                            imageUrl= "http://www.dcgqxx.com/css/images/dc.gif";
                        }
                        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,imageUrl);
                        params.putString(shareType == QQShare.SHARE_TO_QQ_TYPE_IMAGE ? QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL
                                : QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
                        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,R.string.app_name+"");
                        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
                        // 最后一个二进制位置为1, 其他位不变
                        mExtarFlag |= QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN;
                        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);
                        doShareToQQ(params);
                        break;

                    case R.id.view_share_wbsina:
                        flag = 1;
                      //  sendMessage(true,mImageCheckbox.isChecked());
                        sendMessage(true,true);
                        break;
                    case R.id.share_cancel_btn:
                        // 取消
                        break;

                }

                dialog.dismiss();
            }

        };
        ViewGroup mViewWeixin = (ViewGroup) view.findViewById(R.id.view_share_weixin);
        ViewGroup mViewPengyou = (ViewGroup) view.findViewById(R.id.view_share_pengyou);
        ViewGroup mViewQq = (ViewGroup) view.findViewById(R.id.view_share_qq);
        ViewGroup mViewWbsina= (ViewGroup) view.findViewById(R.id.view_share_wbsina);
        ViewGroup mViewQzone = (ViewGroup) view.findViewById(R.id.view_share_qzone);

        Button mBtnCancel = (Button) view.findViewById(R.id.share_cancel_btn);
        mBtnCancel.setTextColor(getResources().getColor(R.color.black));
        mViewWeixin.setOnClickListener(listener);
        mViewPengyou.setOnClickListener(listener);
        mViewQq.setOnClickListener(listener);
        mViewWbsina.setOnClickListener(listener);
        mViewQzone.setOnClickListener(listener);
        mBtnCancel.setOnClickListener(listener);

        // 设置相关位置，一定要在 show()之后
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);

    }
    private void doShareToQQ(final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null !=app.getCtx()) {
                    app.mTencent.shareToQQ(WebProductinfoViewActivity.this, params, qqShareListener);
                }
            }
        });
    }
    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
         //   if (shareType != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
                Utils.toastMessage(WebProductinfoViewActivity.this, "onCancel: ");
          //  }
        }
        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            String result =response.toString();
            JSONObject myobject;
            int ret=-1;
            try {
            myobject = new JSONObject(result);
            ret=myobject.getInt("ret");
            } catch (JSONException e) {
                e.printStackTrace();
            }

           if(ret==0){
               Utils.toastMessage(WebProductinfoViewActivity.this, "分享成功！");
           }

        }
        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            Utils.toastMessage(WebProductinfoViewActivity.this, "onError: " + e.errorMessage, "e");
        }
    };

    @Override
    public void onWbShareSuccess() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWbShareFail() {
        Toast.makeText(this,
                getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    /**
     * @param isShareFriend true 分享到朋友，false分享到朋友圈
     */
    private void share2Wx(boolean isShareFriend) {
        // 分享到微信
        WXWebpageObject webpage = new WXWebpageObject();
        // webpage.webpageUrl = "http://www.qq.com";
        webpage.webpageUrl =url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
//                        msg.title = "WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title WebPage Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
//                        msg.description = "WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description WebPage Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
        msg.title=xinWenXiData.getTitle();
        msg.description=xinWenXiData.getDigest();
       // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Bitmap bmp = null;
        try {
            bmp = Utils.getImage(url0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);

        //     Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
       // bitmap.recycle();
        msg.thumbData = Utils.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
      //  req.scene = mTargetScene;
        req.scene = isShareFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
     //finish();

    }


}
