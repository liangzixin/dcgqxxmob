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
import android.widget.EditText;
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


public class SmsMessageDetailActivity extends AppCompatActivity implements OnItemSelectedListener{
    Context context =SmsMessageDetailActivity.this;
    private ProgressDialog progressDialog;
    private XinWen_productinfo.ShortmessageEntity SmsEntity;

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
    @InjectView(R.id.sms_id) TextView sms_id;
    @InjectView(R.id.sms_mobile)    EditText sms_mobile;
    @InjectView(R.id.sms_sendtime) TextView sms_sendtime;
    @InjectView(R.id.sms_captcha) EditText sms_captcha;
    @InjectView(R.id.backl)    ImageButton backl;
//    @InjectView(R.id.result_addl) TextView addl;
    @InjectView(R.id.sms_smstype) TextView sms_smstype;

//    RecyclerView recyclerViewlzx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsmessagedetail);
        ButterKnife.inject(this);

        //获得绑定参数
        Intent intent = getIntent();
        SmsEntity= (XinWen_productinfo.ShortmessageEntity) intent.getSerializableExtra("SmsEntity");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("数据加载中  请稍后...");
        progressDialog.show();

        showView(SmsEntity);

        progressDialog.dismiss();
       inintClick();
    }
    //初始化各监听事件
    private void inintClick() {
        backl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsMessageDetailActivity.this.finish();
                //    finish();
            }
        });

    }


    @Override
    protected void onDestroy() {
   //     EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    private void updateState(int scrollState) {
        String stateName = "Undefined";
    }
    /**
     * 显示视图
     * @param SmsEntity 职工的图片
     * @param SmsEntity 职工的对象
     * */
    public void showView(XinWen_productinfo.ShortmessageEntity SmsEntity)
    {
        sms_id.setText(SmsEntity.getId()+"");
        sms_mobile.setText(SmsEntity.getMobile());
        sms_sendtime.setText(SmsEntity.getSendtime());
       sms_captcha.setText(SmsEntity.getCaptcha());
       sms_smstype.setText(SmsEntity.getSmstype());

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
//        bmmc.setText("你的血型是："+m[arg2]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
