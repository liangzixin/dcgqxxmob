package com.xiangmu.lzx.activitys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.MyDate;
import com.xiangmu.lzx.Setting_Utils.SearchDB;


public class Setting_my_Task extends AppCompatActivity implements View.OnClickListener{
    private ImageView backsetting;
    private ImageView duihuan;
    private RelativeLayout fabiao, share, xinshang, read_text, read_news, open_client,open_fwxx,open_filter,open_customer;
    private String openid="";
    private Boolean manager=false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgz_activity_my__task);
        openid= SearchDB.createDb(getApplication(), "openid");
        if(openid.equals("3841D13FC6C1BE0BA0D5CBDE23E5FD23") ){
            manager=true;

        }else{
            manager=false;

        }
        initView();
        String date = MyDate.getDate();

        if (!date.equals("24:00:00")) {
            Drawable drawable = getResources().getDrawable(R.color.ve_place);
//            client_glod.setBackground(drawable);
//            jinbijifen.setBackground(drawable);
//            tv_client.setBackground(drawable);
//            open_client.setBackground(drawable);
//            open_fwxx.setBackground(drawable);
          //  Toast.makeText(this, "登录客户端成功金币+5", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        backsetting = (ImageView) findViewById(R.id.backsetting);
        duihuan = (ImageView) findViewById(R.id.duihuan);
        open_client = (RelativeLayout) findViewById(R.id.open_client);
        open_fwxx= (RelativeLayout) findViewById(R.id.open_fwxx);
        open_filter= (RelativeLayout) findViewById(R.id.open_filter);
        open_customer= (RelativeLayout) findViewById(R.id.open_customer);
        fabiao = (RelativeLayout) findViewById(R.id.fabiao);
        share = (RelativeLayout) findViewById(R.id.share);
        xinshang = (RelativeLayout) findViewById(R.id.xinshang);
        read_text = (RelativeLayout) findViewById(R.id.read_text);
        read_news = (RelativeLayout) findViewById(R.id.read_news);
        backsetting.setOnClickListener(this);
        fabiao.setOnClickListener(this);
        duihuan.setOnClickListener(this);
        share.setOnClickListener(this);
        xinshang.setOnClickListener(this);
        read_text.setOnClickListener(this);
        read_news.setOnClickListener(this);
        open_fwxx.setOnClickListener(this);
        open_client.setOnClickListener(this);
        open_filter.setOnClickListener(this);
        open_customer.setOnClickListener(this);
        if(manager){
            open_client.setVisibility(View.VISIBLE);
            open_fwxx.setVisibility(View.VISIBLE);
            open_filter.setVisibility(View.VISIBLE);
            open_customer.setVisibility(View.VISIBLE);
        }else{
            open_client.setVisibility(View.GONE);
            open_fwxx.setVisibility(View.GONE);
            open_filter.setVisibility(View.GONE);
            open_customer.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backsetting:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
                break;
            case R.id.duihuan:
                Intent intent1 = new Intent(this, Setting_glodmall.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.open_client:
                Intent intent2 = new Intent(this, ProductinfoListEditActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.fabiao:
                Intent intent3 = new Intent(this, Task_ShuoMing.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.share:
                Intent intent4 = new Intent(this, Task_ShuoMing.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.xinshang:
                Intent intent5 = new Intent(this, Task_ShuoMing.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.read_text:
                Intent intent6 = new Intent(this, Task_ShuoMing.class);
                startActivity(intent6);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.read_news:
                Intent intent7 = new Intent(this, Task_ShuoMing.class);
                startActivity(intent7);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.open_fwxx:
                Intent intent21 = new Intent(this, WebCountViewActivity.class);
                startActivity(intent21);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.open_filter:
                Intent intent211 = new Intent(this, FilterListEditActivity.class);
                startActivity(intent211);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.open_customer:
                Intent intent212 = new Intent(this, CustomerListEditActivity.class);
                startActivity(intent212);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }

}
