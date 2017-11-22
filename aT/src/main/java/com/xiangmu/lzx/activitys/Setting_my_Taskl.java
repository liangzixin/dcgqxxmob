package com.xiangmu.lzx.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.lzx.CostomAdapter.SortButtonAdapter;
import com.xiangmu.lzx.Modle.ButtonModel;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.SearchDB;

import java.util.ArrayList;
import java.util.List;

import fj.mtsortbutton.lib.Interface.ViewControl;
import fj.mtsortbutton.lib.SoreButton;

public class Setting_my_Taskl extends AppCompatActivity implements ViewControl {
    private ImageButton back;
    private Context context;
    private SoreButton soreButton;
    private List<Integer> list;
    private String openid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytaskl);
        context = this;
        soreButton = (SoreButton) findViewById(R.id.soreButton);
        back = (ImageButton) findViewById(R.id.back);
        //设置界面监听
        soreButton.setViewControl(this);
        //添加界面到list
        list = new ArrayList<>();
        list.add(R.layout.viewpager_page);
        openid= SearchDB.createDb(getApplication(), "openid");
        if(openid.equals("3841D13FC6C1BE0BA0D5CBDE23E5FD23") ) {
            list.add(R.layout.viewpager_page);
        }
//        list.add(R.layout.viewpager_page_text);

        //控件相关设置
//        soreButton
//                //设置选中和未选中指示器图标
//                .setIndicator(R.drawable.radio1,R.drawable.radio2)
//                //设置指示器半间距px
//                .setDistance(10)
//                //设置view组
//                .setView(list)
//                .init();
        soreButton.setView(list).init();
        inintClick();
    }

    @Override
    public void setView(View view, final int type) {
        switch (type) {
            case 0://第一个界面
                GridView gridView = (GridView) view.findViewById(R.id.gridView);
                SortButtonAdapter adapter = new SortButtonAdapter(this,setData());
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context,"第"+type+"页"+position, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case 1://第二个界面
                GridView gridView2 = (GridView) view.findViewById(R.id.gridView);
                SortButtonAdapter adapter2 = new SortButtonAdapter(this,setData2());
                gridView2.setAdapter(adapter2);
                gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                Toast.makeText(context, "第" + type + "页1" + position, Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(context, "第" + type + "页2" + position, Toast.LENGTH_LONG).show();
                                break;
                            case 4:
                                Intent intent2 = new Intent(Setting_my_Taskl.this,ProductinfoListEditActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                break;
                            case 5:
                                Intent intent21 = new Intent(Setting_my_Taskl.this, WebCountViewActivity.class);
                                startActivity(intent21);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                break;
                            case 6:
                                Intent intent211 = new Intent(Setting_my_Taskl.this, FilterListEditActivity.class);
                                startActivity(intent211);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                break;
                            case 7:
                                Intent intent212 = new Intent(Setting_my_Taskl.this, CustomerListEditActivity.class);
                                startActivity(intent212);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                break;
                            case 8:
                                Intent intent213 = new Intent(Setting_my_Taskl.this, LiuyuanListEditActivity.class);
                                startActivity(intent213);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                               break;
                        }
                    }
                });
                break;
            case 2://第三个界面
                TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                tvTitle.setText("可高度定制，可设置任意layout,并且在回调中获取该layout内的所有控件");
                tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"点击了该文字", Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    private List<ButtonModel> setData(){
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_1);
        buttonModel.setName("美食");
        data.add(buttonModel);

        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_2);
        buttonModel.setName("电影");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_3);
        buttonModel.setName("酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_4);
        buttonModel.setName("休闲娱乐");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_5);
        buttonModel.setName("外卖");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_6);
        buttonModel.setName("机票/火车票");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_7);
        buttonModel.setName("KTV");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_8);
        buttonModel.setName("周边游");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_9);
        buttonModel.setName("丽人");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_10);
        buttonModel.setName("旅游出行");
        data.add(buttonModel);
        return data;
    }

    private List<ButtonModel> setData2(){
        List<ButtonModel> data = new ArrayList<>();
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_11);
        buttonModel.setName("发表跟帖");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_12);
        buttonModel.setName("分享新闻");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_13);
        buttonModel.setName("欣赏广告");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_14);
        buttonModel.setName("本地新闻");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_15);
        buttonModel.setName("信息管理");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_16);
        buttonModel.setName("访问信息");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_17);
        buttonModel.setName("注册管理");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_18);
        buttonModel.setName("过滤词语");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_19);
        buttonModel.setName("留言管理");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_20);
        buttonModel.setName("全部分类");
        data.add(buttonModel);
        return data;
    }
    //初始化各监听事件
    private void inintClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          finish();
            }
        });
    }
}
