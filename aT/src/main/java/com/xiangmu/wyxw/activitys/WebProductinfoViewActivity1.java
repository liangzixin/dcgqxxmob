package com.xiangmu.wyxw.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.xiangmu.wyxw.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.wyxw.Modle.UploadFile;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Setting_Utils.ShareUtils;
import com.xiangmu.wyxw.jieping.ScreenShot;
import com.xiangmu.wyxw.utils.DateTime;
import com.xiangmu.wyxw.utils.LogUtils;
import com.xiangmu.wyxw.utils.MySqlOpenHelper;
import com.xiangmu.wyxw.utils.XinWenXiData;
import com.xiangmu.wyxw.utils.XutilsGetData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WebProductinfoViewActivity1 extends AppCompatActivity {
    private XinWenXiData xinWenXiData;
    private List<UploadFile> potolist;
    private ListView listView;
  private  ImageView[] toutiaop_imgsrc=null;
    private List list;


//  private WebView webView;
//  WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinwen_xi_putong_productinfo_frament);
        Intent intent = getIntent();
        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
        potolist=(List<UploadFile>)getIntent().getSerializableExtra("potolist");
        initDate();
        initview();
//      ZiTiScale.zitiStyle(WebProductinfoViewActivity.this, webView);
        LogUtils.e("------------>", "==onCreate==");
    }
    ImageButton fenxiang;

    private void initview() {
        final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        TextView xinwen_duotu_title=(TextView) findViewById(R.id.xinwen_duotu_title);
        TextView xinwen_duotu_content=(TextView) findViewById(R.id.xinwen_duotu_content);
        TextView xinwen_duotu_date=(TextView) findViewById(R.id.xinwen_duotu_date);

       //toutiaop_imgsrc=(ImageView) findViewById(R.id.toutiaop_imgsrc);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList();
        for (int i = 0; i <((List<UploadFile>)potolist.get(0)).size(); i++) {
            list.add("aaa" + i);
        }
  listView.setAdapter(new MyListAdapter());

        ImageButton imageback = null;
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回
        TextView duotu_gentie = null;
        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) findViewById(R.id.xinwen_xi_text_webview);
        xinwen_duotu_title.setText(xinWenXiData.getTitle());
        xinwen_duotu_content.setText(xinWenXiData.getXinwentext());
        xinwen_duotu_date.setText(xinWenXiData.getCreateDate().substring(0,10));
      //  toutiaop_imgsrc.setText()
        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
        fenxiang = (ImageButton) findViewById(R.id.xinwen_xi_fenxiang);
        getdata(url,toutiaop_imgsrc);//获得数据
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
                startActivity(new Intent(WebProductinfoViewActivity1.this,GenTieActivity.class));
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
                ShareUtils.shareContent(WebProductinfoViewActivity1.this, xinwentitle, url);

            }
        });
    }
    //获得数据
  //  private List<XinWenXi.PhotosObj> photoslist;
    private XutilsGetData xutilsGetData = new XutilsGetData();
    public void getdata(String url,ImageView[] toutiaop_imgsrc) {
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
//      webView.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript(例如：alert())
        final CustomProgressDialog progress=new CustomProgressDialog(this,"正在加载中.....",R.anim.donghua_frame);
        progress.show();
        List<UploadFile> potolist0=null;
        potolist0=(List<UploadFile>)potolist.get(0);
//  //     XutilsGetData.xUtilsImageiv(toutiaop_imgsrc, "http://www.dcgqxx.com/upload/"+potolist0.get(0).getPath(),null,false);
//////        webView.loadUrl(url);
    BitmapUtils bitmapUtils = new BitmapUtils(this);
//        for(int i=0;i<potolist.size();i++) {
//            bitmapUtils.display(toutiaop_imgsrc[i], "http://www.dcgqxx.com/upload/" + potolist0.get(0).getPath());
//        }
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
//        if (CommonUtil.isNetWork(WebProductinfoViewActivity.this)){//根据url保存数据
//            xutilsGetData.xUtilsHttp(this, url, new XutilsGetData.CallBackHttp() {
//                @Override
//                public void handleData(String data) {
//                    LogUtils.e("xinwenactivitydata", data + "");
//
//                    getshowData(data);
//                }
//            },true);
//        }else {
//            String data = xutilsGetData.getData(this, url, null);
//            //判断本地数据是否存在  如果没有网络请求
//            if (data != null) {
//                getshowData(data);
//            }
//        }
    }
    private void getshowData(String data) {
        int lanmuType = xinWenXiData.getLanMuType();//获得是那条栏目  可能会有不同的字段
//        photoslist = XinWenXi.getdata(data, this, lanmuType);
//        String gentie = photoslist.get(0).getGentieUrl();//获得跟帖的URL
//        LogUtils.e("showdata", "xinwencontent "+ "           " + photoslist.get(0).getPhotosList().get(0).getText());


//        imagePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                //设置滑动改变内容
//                xinwencontent.setText(photoslist.get(0).getPhotosList().get(position).getText());
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
//        imagePager.setAdapter(imagePagerAdapter);
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

                Toast.makeText(WebProductinfoViewActivity1.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(WebProductinfoViewActivity1.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(WebProductinfoViewActivity1.this, PictureActivity.class);
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
//                ZiTiScale.zitiStyle2(WebProductinfoViewActivity.this, webView);
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
    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size() == 0 ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(WebProductinfoViewActivity1.this, R.layout.item_productinfo_imeg, null);
            }
//            textView = (TextView) convertView.findViewById(R.id.tv_one);
            System.out.println("list.size="+list.size());
            final View finalConvertView = convertView;
//            ImageView imageView=(ImageView) findViewById(R.id.toutiaop_imgsrc);
//            ImageView imageView = (ImageView) convertView.findViewById(R.id.zan);
//          imageView.setBackground(getResources().getDrawable(R.drawable.biz_news_list_other_segments_support));
//            List<UploadFile> potolist0=null;
          TextView   textView = (TextView) convertView.findViewById(R.id.tv_one);
            textView.setText("东川这办");
//            potolist0=(List<UploadFile>)potolist.get(position);
            //     XutilsGetData.xUtilsImageiv(toutiaop_imgsrc, "http://www.dcgqxx.com/upload/"+potolist0.get(0).getPath(),null,false);
////        webView.loadUrl(url);
//            BitmapUtils bitmapUtils = new BitmapUtils(this);
//            bitmapUtils.display(toutiaop_imgsrc, "http://www.dcgqxx.com/upload/"+potolist0.get(0).getPath());
         //   imageView.setEnabled(true);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    RelativeLayout relativeLayout = (RelativeLayout) v.getParent();
//                    LinearLayout linearLayout = (LinearLayout) relativeLayout.getParent();
//                    TextView zannum = (TextView) linearLayout.findViewById(R.id.zannum);
//                    zannum.setText((Integer.parseInt(zannum.getText().toString()) + 1) + "");
//                    textView = (TextView) relativeLayout.findViewById(R.id.tv_one);
//                    Log.e("aa","---------"+textView);
//                    Toast.makeText(GenTieActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//                    textView.setVisibility(View.VISIBLE);
//                    v.setBackground(getResources().getDrawable(R.drawable.biz_news_list_other_segments_support_done));
//                    v.setEnabled(false);
//                    textView.startAnimation(animation);
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            textView.setVisibility(View.GONE);
//                        }
//                    }, 500);
//                }
//            });
            LinearLayout linearparent = (LinearLayout) convertView.findViewById(R.id.parentlinea);
            linearparent.removeAllViews();
//            if (position == 3 | position == 8 | position == 13 | position == 17 | position == 28) {
//                switch (position) {
//                    case 3:
//                        //到时候可以根据 字段判断具体添加几条回复
//                        for (int i = 0; i < 2; i++) {
//                            View view = View.inflate(GenTieActivity.this, R.layout.gentie_huifu_item, null);
//                            if (i == 0) {
//                                TextView textView = (TextView) view.findViewById(R.id.in_info);
//                                textView.setText("吐槽者....死!!!!!!!!!!!");
//                            }
//                            TextView num = (TextView) view.findViewById(R.id.item_num);
//                            num.setText("" + (i + 1));
//                            linearparent.addView(view);
//                        }
//                        break;
//                    case 8:
//                        for (int i = 0; i < 5; i++) {
//                            View view = View.inflate(GenTieActivity.this, R.layout.gentie_huifu_item, null);
//                            if (i == 0) {
//                                TextView textView = (TextView) view.findViewById(R.id.in_info);
//                                textView.setText("吐槽者....死!!!!!!!!!!!");
//                            }
//                            TextView num = (TextView) view.findViewById(R.id.item_num);
//                            num.setText("" + (i + 1));
//                            linearparent.addView(view);
//                        }
//                        break;
//                    case 13:
//                        for (int i = 0; i < 2; i++) {
//                            View view = View.inflate(GenTieActivity.this, R.layout.gentie_huifu_item, null);
//                            if (i == 0) {
//                                TextView textView = (TextView) view.findViewById(R.id.in_info);
//                                textView.setText("吐槽者....死!!!!!!!!!!!");
//                            }
//                            TextView num = (TextView) view.findViewById(R.id.item_num);
//                            num.setText("" + (i + 1));
//                            linearparent.addView(view);
//                        }
//                        break;
//                    case 17:
//                        for (int i = 0; i < 9; i++) {
//                            View view = View.inflate(GenTieActivity.this, R.layout.gentie_huifu_item, null);
//                            if (i == 0) {
//                                TextView textView = (TextView) view.findViewById(R.id.in_info);
//                                textView.setText("吐槽者....死!!!!!!!!!!!");
//                            }
//                            TextView num = (TextView) view.findViewById(R.id.item_num);
//                            num.setText("" + (i + 1));
//                            linearparent.addView(view);
//                        }
//                        break;
//                    case 28:
//                        for (int i = 0; i < 2; i++) {
//                            View view = View.inflate(GenTieActivity.this, R.layout.gentie_huifu_item, null);
//                            if (i == 0) {
//                                TextView textView = (TextView) view.findViewById(R.id.in_info);
//                                textView.setText("吐槽者....死!!!!!!!!!!!");
//                            }
//                            TextView num = (TextView) view.findViewById(R.id.item_num);
//                            num.setText("" + (i + 1));
//                            linearparent.addView(view);
//                        }
//                        break;
//
//                }
//            }
            return convertView;
        }
    }
    ;

}
