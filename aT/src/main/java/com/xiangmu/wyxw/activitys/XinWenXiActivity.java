package com.xiangmu.wyxw.activitys;

import android.content.ContentValues;
import android.content.Context;
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
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.wyxw.Modle.UploadFile;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Setting_Utils.ShareUtils;
import com.xiangmu.wyxw.jieping.ScreenShot;
import com.xiangmu.wyxw.utils.CommonUtil;
import com.xiangmu.wyxw.utils.DateTime;
import com.xiangmu.wyxw.utils.LogUtils;
import com.xiangmu.wyxw.utils.MySqlOpenHelper;
import com.xiangmu.wyxw.utils.XinWenXi;
import com.xiangmu.wyxw.utils.XinWenXiData;
import com.xiangmu.wyxw.utils.XutilsGetData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XinWenXiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xinwen_xi_duotu_frament);
        Intent intent = getIntent();
        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
        initDate();
        initview();
    }

    private TextView xinwencontent;
    private ViewPager imagePager;
    private XinWenXiData xinWenXiData;
    private TextView duotu_gentie;
    private TextView duotu_count;
    ImageButton caidan;
    ImageButton fenxiang;

    private void initview() {
        final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用

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
        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
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
                startActivity(new Intent(XinWenXiActivity.this,GenTieActivity.class));
            }
        });
        //// TODO: 2015/11/14         //点击打开扩展 详细页面
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpopuwindow(view);//打开菜单栏
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtils.shareContent(XinWenXiActivity.this, xinwentitle, url);
            }
        });
    }

    //获得数据
//    private List<XinWenXi.PhotosObj> photoslist;
    private ArrayList<UploadFile> photoslist;
    private XutilsGetData xutilsGetData = new XutilsGetData();
//    private XinWenXi xinWenXi= new XinWenXi();

    public void getdata() {//根据url保存数据

        photoslist=xinWenXiData.getUploadFileList();
        photoslist=XinWenXi.getdataview(photoslist,this);
//        for(int i=0;i<photoslist.size();i++)
//        {
//            if(photoslist.get(i).getImageView()==null){
//                System.out.println("为空");
//            }
//        }
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
     private  int   myposition=0;
    private void getshowData() {
//        int lanmuType = xinWenXiData.getLanMuType();//获得是那条栏目  可能会有不同的字段
//        photoslist = XinWenXi.getdata(data, this, lanmuType);
//        String gentie = photoslist.get(0).getGentieUrl();//获得跟帖的URL
//        photoslist=xinWenXiData.getUploadFileList();
//        LogUtils.e("showdata", xinwencontent + "           " + photoslist.get(0).getPhotosList().get(0).getText());



        imagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置滑动改变内容
//                xinwencontent.setText(photoslist.get(0).getPhotosList().get(position).getText());
//                myposition=position;
                xinwencontent.setText(photoslist.get(position).getPath());
                duotu_count.setText(position +1+"/" +photoslist.size());
//                if(position==photoslist.size()-1){
//                    imagePager.setCurrentItem(1);
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //页面到达最后一页，state==0表示页面动画完成状态
                boolean flag=false;
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag= false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (imagePager.getCurrentItem() == imagePager.getAdapter()
                                .getCount() - 1 && !flag) {
                            Toast.makeText(XinWenXiActivity.this, "已经是最后一页",
                                    Toast.LENGTH_LONG).show();
                        }
                        flag = true;
                        break;
                }
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
//           if(position==getCount()-1) {
//               container.addView(photoslist.get(0).getImageView());
//           }

            if (position== 0) {//设置第一次初始化内容
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
//            ((ViewPager)container).removeView(photoslist.get(position).getImageView());
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

    ;
}
