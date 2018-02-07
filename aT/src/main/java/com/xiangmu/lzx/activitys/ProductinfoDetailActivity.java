package com.xiangmu.lzx.activitys;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.xiangmu.lzx.Modle.Photo;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.holder.PhotoHolder;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ProductinfoDetailActivity extends AppCompatActivity implements OnItemSelectedListener{
    Context context =ProductinfoDetailActivity.this;
    private ProgressDialog progressDialog;
    private XinWen_productinfo.CustomerEntity customerEntity;

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
    @InjectView(R.id.customer_id) TextView customer_id;
    @InjectView(R.id.customer_name)   MaterialEditText customer_name;
    @InjectView(R.id.customer_password) TextView password;
    @InjectView(R.id.customer_sfzh) MaterialEditText sfzh;
    @InjectView(R.id.backl)    ImageButton backl;
    @InjectView(R.id.result_addl) TextView addl;
    @InjectView(R.id.customer_realname) TextView realname;
    @InjectView(R.id.customer_address) TextView address;
    @InjectView(R.id.customer_email) TextView email;
    @InjectView(R.id.customer_mobile) TextView mobile;
    @InjectView(R.id.customer_registerdate) TextView registerdate;
    @InjectView(R.id.customer_starttime) TextView starttime;
    @InjectView(R.id.customer_logintime) TextView logintime;
    @InjectView(R.id.recyclerViewlzx)
    RecyclerView recyclerViewlzx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerdetail);

        //  EventBus.getDefault().register(this);

        ButterKnife.inject(this);

        //获得绑定参数
        Intent intent = getIntent();
        customerEntity= (XinWen_productinfo.CustomerEntity) intent.getSerializableExtra("CustomerEntity");
        assert recyclerViewlzx != null;
        recyclerViewlzx.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterlzx = new MultiTypeAdapter(this);
        adapterlzx.registerViewType(Photo.class, PhotoHolder.class);
        recyclerViewlzx.setAdapter(adapterlzx);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("数据加载中  请稍后...");
        progressDialog.show();

        showView(customerEntity);

        progressDialog.dismiss();
        inintClick();
    }
    //初始化各监听事件
    private void inintClick() {
        backl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductinfoDetailActivity.this.finish();
                //    finish();
            }
        });
        addl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(this, "单击了添加按钮", Toast.LENGTH_SHORT).show();
                //      new AlertDialog.Builder(FilterListEditActivity.this).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
                url=xinWenURL.getRepareCustomerMob();
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
            params.addQueryStringParameter("customer.id",customer_id.getText().toString());
            params.addQueryStringParameter("customer.username",customer_name.getText().toString());
            params.addQueryStringParameter("customer.password",password.getText().toString());
            params.addQueryStringParameter("customer.sfzh",sfzh.getText().toString());
            params.addQueryStringParameter("customer.realname",realname.getText().toString());

            params.addQueryStringParameter("customer.address",address.getText().toString());
            params.addQueryStringParameter("customer.email",email.getText().toString());
            params.addQueryStringParameter("customer.mobile",mobile.getText().toString());
            params.addQueryStringParameter("customer.registerdate",registerdate.getText().toString());
            params.addQueryStringParameter("customer.logintime",logintime.getText().toString());


            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    if (responseInfo.result != null) {
                        Toast.makeText(ProductinfoDetailActivity.this, "注册用户信息修改成功！", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ProductinfoDetailActivity.this, "注册用户信息修改失败！", Toast.LENGTH_SHORT).show();
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
////         showView(customer);
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
     * @param customerEntity 职工的图片
     * @param customerEntity 职工的对象
     * */
    public void showView(XinWen_productinfo.CustomerEntity customerEntity)
    {


        customer_id.setText(customerEntity.getId()+"");
        customer_name.setText(customerEntity.getUsername());
        password.setText(customerEntity.getPassword());
        sfzh.setText(customerEntity.getSfzh());

        realname.setText(customerEntity.getRealname());
//        address.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//        address.setGravity(Gravity.TOP);
        address.setText(customerEntity.getAddress());
//        address.setSingleLine(false);
//
//        address.setHorizontallyScrolling(false);
        email.setText(customerEntity.getEmail());
        mobile.setText(customerEntity.getMobile());
        registerdate.setText(customerEntity.getRegisterdate());
        starttime.setText(customerEntity.getStarttime());
        logintime.setText(customerEntity.getLogindate());
        if(!customerEntity.getHeadimg().equals("")) {
            Photo photo = new Photo();
            photo.path=customerEntity.getHeadimg();
            photo.photoId =1;
            photo.description ="头像";
            adapterlzx.add(photo);
        }
    }

    //    /**
//     * 数据加载完之后消除Loding对话框
//     * */
//    private Handler myHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            progressDialog.dismiss(); //消除Loding对话框
//            showView(customerEntity);
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
//      //  url=HttpUtil.BASE_URL+"customer!queryTxxxId.action";
//        params = new RequestParams();
//        params.addQueryStringParameter("id",id+"");
//
//        otherquery=true;
//     //   mThreadmy();
//    }
//
//



}
