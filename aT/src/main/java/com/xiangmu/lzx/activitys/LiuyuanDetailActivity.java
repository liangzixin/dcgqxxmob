package com.xiangmu.lzx.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.MaterialEditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.litao.android.lib.entity.PhotoEntry;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LiuyuanDetailActivity extends AppCompatActivity implements OnItemSelectedListener{
    Context context =LiuyuanDetailActivity.this;
    private ProgressDialog progressDialog;
    private XinWen_productinfo.ProductArticlerEntity liuyuanEntity;

    private Handler testHandler;

    private List<PhotoEntry> mSelectedPhotos=new ArrayList<PhotoEntry>();
    private String filepath;
    private List<String> imgstmppath=new ArrayList<String>();
    private List<File> list=new ArrayList<>();

    private  Boolean otherquery=false;
    private XinWenXiData xinWenXiData;
private HttpHandler<String> handler;
    private HttpUtils httpUtils= new HttpUtils();
    private    String url=null;
    private RequestParams params;

    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private android.support.v7.widget.StaggeredGridLayoutManager StaggeredGridLayoutManager;
    private XinWenURL xinWenURL = new XinWenURL();
    MultiTypeAdapter adapterlzx;
    @InjectView(R.id.liuyuan_id) TextView liuyuan_id;
    @InjectView(R.id.liuyuan_content)   MaterialEditText liuyuan_content;
    @InjectView(R.id.liuyuan_time) TextView liuyuan_time;
    @InjectView(R.id.liuyuan_customer) MaterialEditText liuyuan_customer;
    @InjectView(R.id.backl)    ImageButton backl;
    @InjectView(R.id.result_addl) TextView addl;
    @InjectView(R.id.liuyuan_productinfo) TextView liuyuan_productinfo;

//    RecyclerView recyclerViewlzx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyuandetail);

    //  EventBus.getDefault().register(this);

        ButterKnife.inject(this);

        //获得绑定参数
        Intent intent = getIntent();
        liuyuanEntity= (XinWen_productinfo.ProductArticlerEntity) intent.getSerializableExtra("LiuyuanEntity");
//        assert recyclerViewlzx != null;
//        recyclerViewlzx.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapterlzx = new MultiTypeAdapter(this);
//        adapterlzx.registerViewType(Photo.class, PhotoHolder.class);
//        recyclerViewlzx.setAdapter(adapterlzx);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("数据加载中  请稍后...");
        progressDialog.show();

        showView(liuyuanEntity);

        progressDialog.dismiss();
       inintClick();
    }
    //初始化各监听事件
    private void inintClick() {
        backl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiuyuanDetailActivity.this.finish();
                //    finish();
            }
        });
        addl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(this, "单击了添加按钮", Toast.LENGTH_SHORT).show();
          //      new AlertDialog.Builder(FilterListEditActivity.this).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
             url=xinWenURL.getRepareProductArticlerMob();
                SaveData(url);
            }
        });
    }
//    private void mThreadmy() {
//
//        handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//
//                if (responseInfo.result != null) {
//                    progressDialog.dismiss();
//                    JSONObject myobject =null;
//                    String listArray=null;
//
//                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                progressDialog.dismiss();
//              //  Toast.makeText(TxxxDetailActivity.this, "数据加载失败！！！", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
private void SaveData(final String url){

    if (!url.equals("")) {
        httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
      params.addQueryStringParameter("productArticler.id",liuyuan_id.getText().toString());
        params.addQueryStringParameter("productArticler.artreview_content",liuyuan_content.getText().toString());
        params.addQueryStringParameter("productArticler.artreview_time",liuyuan_time.getText().toString());



        handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                if (responseInfo.result != null) {
                    Toast.makeText(LiuyuanDetailActivity.this, "留言信息修改成功！", Toast.LENGTH_SHORT).show();
                  //  PictureUtil.deleteImgTmp(imgstmppath);
//                    Intent intent = new Intent();
//                    intent.setClass(ProductinfoAddActivity.this, MainActivity.class);
//
//                    startActivity(intent);
//                        setResult(RESULT_CODE, intent);
                  //  onCreate(null);
                 finish();

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(LiuyuanDetailActivity.this, "留言信息修改失败！", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

    @Override
    protected void onDestroy() {
   //     EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    private void updateState(int scrollState) {
        String stateName = "Undefined";
//        switch (scrollState) {
//            case SCROLL_STATE_IDLE:
//                stateName = "Idle";
//                break;
//
//            case SCROLL_STATE_DRAGGING:
//                stateName = "Dragging";
//                break;
//
//            case SCROLL_STATE_SETTLING:
//                stateName = "Flinging";
//                break;
//        }

//        tv_state.setText("滑动状态：" + stateName);
    }

//   private Thread mThread = new Thread() {
//      public void run() {
//        Log.d("TAG", "mThread run");
//        Looper.prepare();
//
//        testHandler = new Handler() {
//    public void handleMessage(Message msg) {
//        Log.d("TAG", "worker thread:" + Thread.currentThread().getName());
////					System.out.println("我的线程："+msg.what);
//
//        switch (msg.what) {
//        //handle message here
//        case 1:
//
////            if (isThemeLight()) {
////                img1.setImageResource(R.drawable.ic_phone_grey600_24dp);
////                img2.setImageResource(R.drawable.ic_phone_grey600_24dp);
////                img3.setImageResource(R.drawable.ic_phone_grey600_24dp);
////
////            } else {
////                img1.setImageResource(R.drawable.ic_phone_white_24dp);
////                img2.setImageResource(R.drawable.ic_phone_white_24dp);
////                img3.setImageResource(R.drawable.ic_phone_white_24dp);
////            }
////         showView(liuyuan);
//
//        progressDialog.dismiss();
//        //send message here
//
//        }
//
//        }
//        };
//
//        testHandler.sendEmptyMessage(1);
//        Looper.loop();
//
//        }
//
//        };
    /**
     * 显示视图
     * @param liuyuanEntity 职工的图片
     * @param liuyuanEntity 职工的对象
     * */
    public void showView(XinWen_productinfo.ProductArticlerEntity liuyuanEntity)
    {


        liuyuan_id.setText(liuyuanEntity.getId()+"");
        liuyuan_content.setText(liuyuanEntity.getArtreview_content());
        liuyuan_time.setText(liuyuanEntity.getArtreview_time());
       liuyuan_customer.setText(liuyuanEntity.getCustomer().getUsername());
       liuyuan_productinfo.setText(liuyuanEntity.getProduct().getName());

    }

//    /**
//     * 数据加载完之后消除Loding对话框
//     * */
//    private Handler myHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            progressDialog.dismiss(); //消除Loding对话框
//            showView(liuyuanEntity);
////            rz.se;
//            super.handleMessage(msg);
//        }
//    };
//
//
//
//
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
//        bmmc.setText("你的血型是："+m[arg2]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//    private void doSearching(int id) {
//
//
//      //  url=HttpUtil.BASE_URL+"liuyuan!queryTxxxId.action";
//        params = new RequestParams();
//        params.addQueryStringParameter("id",id+"");
//
//        otherquery=true;
//     //   mThreadmy();
//    }
//
//



}
