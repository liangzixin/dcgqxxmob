package com.xiangmu.lzx.activitys;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.jieping.ScreenShot;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXi;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_productinfo;
import com.xiangmu.lzx.utils.XutilsGetData;

import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class XinWenXiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinwen_xi_duotu_frament);
        button = (ImageButton) findViewById(R.id.subbtn);
        edit = (EditText) findViewById(R.id.edit);
        Intent intent = getIntent();
        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
        liuyuanlist=xinWenXiData.getProductArticlerList();

        initDate();
        initview();
        button.setOnClickListener(c);
    }

    private TextView xinwencontent;
    private ViewPager imagePager;
    private XinWenXiData xinWenXiData;
    private TextView duotu_gentie;
    private TextView duotu_count;
    private  ImageButton button;
    private EditText edit;
    private List<XinWen_productinfo.T18908805728Entity.ProductArticlerEntity> liuyuanlist;
    private XinWenURL xinWenURL=new XinWenURL();
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    ImageButton caidan;
    ImageButton fenxiang;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    /**
     * 提交的监听器
     */
    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = edit.getText().toString();
            if (msg.equals("") || msg == null) {
                new AlertDialog.Builder(XinWenXiActivity.this).setMessage("不能为空").setPositiveButton("确定", null).show();
                return;
            }else{

                XinWen_productinfo.T18908805728Entity.ProductArticlerEntity productArticler=new XinWen_productinfo.T18908805728Entity.ProductArticlerEntity();


                productArticler.setArtreview_rootid(xinWenXiData.getId());
                productArticler.setArtreview_content(msg);
                DateTime dateTime =new DateTime();
                productArticler.setArtreview_time(dateTime.getDateFormatter());
                productArticler.setArtreview_authorid(1+"");
                if(liuyuanlist.size()>0) {
                    liuyuanlist.add(0, productArticler);
                }else{
                    liuyuanlist.add(productArticler);

                }
                UpArticlerFunction();
//                 NotifyFunction();
            }
//            if(liuyuanlist.size()>0) {
//                adapter.add(mockLiuyuan(0));
//            }
            edit.setText("");
            edit.setFocusable(false);
            Toast.makeText(XinWenXiActivity.this, "留言成功！", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * 提交留言
     */
    public void UpArticlerFunction() {
        String url=xinWenURL.getSavearticler()+xinWenXiData.getId()+"&msg="+edit.getText().toString();
        UpData(url);
    }
    private void UpData(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.result != null) {
                        SharedPreferencesUtil.saveData(XinWenXiActivity.this, url, responseInfo.result);
//                                new AlertDialog.Builder(WebProductinfoViewActivity.this).setMessage("留言成功！").setPositiveButton("确定", null).show();
//                                edit.setText("");

                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(XinWenXiActivity.this, "留言请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private void initview() {
        final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        final String url0 ="http://www.dcgqxx.com/product/product_select.html?id="+xinWenXiData.getId();
        ImageButton imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);
        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);
        fenxiang = (ImageButton) findViewById(R.id.xinwen_xi_fenxiang);
        imagePager = (ViewPager) findViewById(R.id.xinwenxi_viewpager);
        xinwencontent = (TextView) findViewById(R.id.xinwenxi_content);
        duotu_count = (TextView) findViewById(R.id.xinwen_xi_count);
        final TextView title = (TextView) findViewById(R.id.xinwen_xi_title);

        title.setText(xinwentitle);
        int replaycount = xinWenXiData.getReplaycount();
        if (replaycount == 0) {
            duotu_gentie.setText("");
            duotu_gentie.setBackgroundColor(Color.parseColor("#ff000000"));
        }
        duotu_gentie.setText(liuyuanlist.size()+ "跟帖");
        getdata();
        //点击finish
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //// TODO: 2015/11/14        //点击进入跟帖 详细页面  完成
        duotu_gentie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xinWenXiData.setProductArticlerList(liuyuanlist);
                Intent intent = new Intent(XinWenXiActivity.this,LiuyuanActivity.class);
                intent.putExtra("liuyuanlist", (Serializable)liuyuanlist);
                startActivity(intent);

            }
        });
        //// TODO: 2015/11/14         //点击打开扩展 详细页面
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpopuwindow(view);//打开菜单栏
            }
        });
        mShareListener = new CustomShareListener(this);
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ShareUtils.shareContent(WebProductinfoViewActivity.this, xinwentitle, url);
//                       ShareUtils.shareQQZore(WebProductinfoViewActivity.this, xinwentitle, url);
                new ShareAction(XinWenXiActivity.this)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA)
                        //     .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                        //  .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                                if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
//                                    Toast.makeText(WebProductinfoViewActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
//                                } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
//                                    Toast.makeText(WebProductinfoViewActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();
//
//                                } else {
                                UMWeb web = new UMWeb(url0);
                                web.setTitle(xinwentitle);
                                web.setDescription(xinwentitle);
                                web.setThumb(new UMImage(XinWenXiActivity.this, R.drawable.ic_launcher));
                                new ShareAction(XinWenXiActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(mShareListener)
                                        .share();
//                                }
                            }
                        })
                        .open();
            }
        });
    }

    //获得数据
//    private List<XinWenXi.PhotosObj> photoslist;
    private List<UploadFile> photoslist;
    private XutilsGetData xutilsGetData = new XutilsGetData();
//    private XinWenXi xinWenXi= new XinWenXi();

    public void getdata() {//根据url保存数据

        photoslist=xinWenXiData.getUploadFileList();
        photoslist=XinWenXi.getdataview(photoslist,this);
        getshowData();
//        if (CommonUtil.isNetWork(XinWenXiActivity.this)){
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

    private void getshowData() {
//        int lanmuType = xinWenXiData.getLanMuType();//获得是那条栏目  可能会有不同的字段
//        photoslist = XinWenXi.getdata(data, this, lanmuType);
//        String gentie = photoslist.get(0).getGentieUrl();//获得跟帖的URL
//        photoslist=xinWenXiData.getUploadFileList();
//        LogUtils.e("showdata", xinwencontent + "           " + photoslist.get(0).getPhotosList().get(0).getText());


        imagePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置滑动改变内容
//                xinwencontent.setText(photoslist.get(0).getPhotosList().get(position).getText());
                xinwencontent.setText(photoslist.get(position).getPath());
                duotu_count.setText(position + 1 + "/" +photoslist.size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter();
        imagePager.setAdapter(imagePagerAdapter);
    }

    class ImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return photoslist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //对ViewPager页号求模取出View列表中要显示的项

            container.addView(photoslist.get(position).getImageView());

            if (position == 0) {//设置第一次初始化内容
                xinwencontent.setText(photoslist.get(position).getPath());
                duotu_count.setText("1/" + getCount());
            }
            return photoslist.get(position).getImageView();
//            try {
//                container.addView(photoslist.get(position % photoslist.size()).getPath(), 0);
//            }catch(Exception e){
//                //handler something
//            }
//            return mImageViews[position % mImageViews.length];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
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
                Toast.makeText(XinWenXiActivity.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdir();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(XinWenXiActivity.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(XinWenXiActivity.this, PictureActivity.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xutilsGetData.XutilsClose();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);  //这个super可不能落下，否则可能回调不了
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void initDate() {
        String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        int replaycount = xinWenXiData.getReplaycount();//获得跟帖数目  //收藏用
        int lanMuType = xinWenXiData.getLanMuType();//获得跟帖数目  //收藏用
        Log.e("aa", "------xinwentitle------" + xinwentitle);
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
                values.put("num", 3);
                values.put("replaycount", replaycount + "");
                values.put("lanMuType", lanMuType + "");
//            values.put("url",url);存储详情页的地址  在 阅读记录里取出来
                writableDatabase.insert("read_date", null, values);
            }
        } else {
            ContentValues values = new ContentValues();
            values.put("date", date + "");
            values.put("url", url + "");
            values.put("title", xinwentitle + "");
            values.put("num", 3);
            values.put("replaycount", replaycount + "");
            values.put("lanMuType", lanMuType + "");
            writableDatabase.insert("read_date", null, values);
        }
        //关闭
        cursor.close();
        writableDatabase.close();
    }

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<XinWenXiActivity> mActivity;

        private CustomShareListener(XinWenXiActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦!!!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                //    com.umeng.socialize.utils.Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

}
