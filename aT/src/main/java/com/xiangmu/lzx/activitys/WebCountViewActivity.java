package com.xiangmu.lzx.activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
//import com.umeng.socialize.ShareAction;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.UMShareListener;
//import com.umeng.socialize.media.UMImage;
import com.xiangmu.lzx.CostomProgressDialog.SimpleArcDialog;
import com.xiangmu.lzx.Modle.DateCount;

import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.SearchDB;
import com.xiangmu.lzx.holder.DateCountHolder;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WebCountViewActivity extends AppCompatActivity {
   // private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
  //  private XutilsGetData xutilsGetData = new XutilsGetData();
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
//    private UMShareListener mShareListener;
//    private ShareAction mShareAction;
    private  DateCount dateCount0;
  //  private   CustomProgressDialog progress;
    private SimpleArcDialog mDialog;
    private ImageButton imageback;
    // MultiTypeAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        button = (ImageButton) findViewById(R.id.subbtn);
        edit = (EditText) findViewById(R.id.edit);
        app =(MyApplication)getApplication();
        dateCount0=new  DateCount ();
         imageback = null;
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

       initDate();

    }





    public void initDate() {
        mDialog = new SimpleArcDialog(this);
        mDialog.show();
//        String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
//        String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        String xinwentitle ="浏览次数";
//        int replaycount = xinWenXiData.getReplaycount();//获得跟帖数目  //收藏用
        int customerid0=0;
        int shezhitype0=1;
//        imageurl = new UMImage(this,"http://www.dcgqxx.com/css/images/dc2.png");

        String getDateCount=xinWenURL.getGetDateCount();

        getDate(getDateCount);

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
                values.put("url", "url" + "");
                values.put("title", xinwentitle + "");
                values.put("num", 2);
                values.put("replaycount", "replaycount" + "");
//            values.put("url",url);存储详情页的地址  在 阅读记录里取出来
                writableDatabase.insert("read_date", null, values);
            }
        } else {
            ContentValues values = new ContentValues();
            values.put("date", date + "");
            values.put("url", "url" + "");
            values.put("title", xinwentitle + "");
            values.put("num", 2 + "");
            values.put("replaycount"," replaycount" + "");
            writableDatabase.insert("read_date", null, values);
        }
        //关闭
        cursor.close();
        writableDatabase.close();
    }
    public void initDate1() {
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MultiTypeAdapter(this);
        adapter.registerViewType(DateCount.class, DateCountHolder.class);
        recyclerView.setAdapter(adapter);
          adapter.add(dateCount0);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDate(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (!responseInfo.result.equals("false")) {
                        login0=true;
                //  mDialog.dismiss();
                        try {
                            JSONObject datecount = new JSONObject(responseInfo.result);

                            dateCount0= gson.fromJson(datecount.toString(),DateCount.class);
                            mDialog.dismiss();
                            initDate1();
                   //         adapter.add(dateCount0);

                       //     Toast.makeText(WebCountViewActivity.this, dateCount0.allcount, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }


                @Override
                public void onFailure(HttpException e, String s) {
                mDialog.dismiss();
                    Toast.makeText(WebCountViewActivity.this, "留言请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    public Gson getGson() {
        return gson;
    }


}
