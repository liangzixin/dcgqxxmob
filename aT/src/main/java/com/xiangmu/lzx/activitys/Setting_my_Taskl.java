package com.xiangmu.lzx.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.lzx.CostomAdapter.SortButtonAdapter;
import com.xiangmu.lzx.Modle.ButtonModel;
import com.xiangmu.lzx.R;

import java.util.ArrayList;
import java.util.List;

import fj.mtsortbutton.lib.Interface.ViewControl;
import fj.mtsortbutton.lib.SoreButton;

public class Setting_my_Taskl extends AppCompatActivity implements ViewControl {

    private Context context;
    private SoreButton soreButton;
    private List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytaskl);
        context = this;
        soreButton = (SoreButton) findViewById(R.id.soreButton);

        //设置界面监听
        soreButton.setViewControl(this);
        //添加界面到list
        list = new ArrayList<>();
        list.add(R.layout.viewpager_page);
        list.add(R.layout.viewpager_page);
        list.add(R.layout.viewpager_page_text);

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
                        Toast.makeText(context,"第"+type+"页"+position, Toast.LENGTH_LONG).show();
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
        buttonModel.setName("品质酒店");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_12);
        buttonModel.setName("生活服务");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_13);
        buttonModel.setName("足疗按摩");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_14);
        buttonModel.setName("母婴亲子");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_15);
        buttonModel.setName("结婚");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_16);
        buttonModel.setName("景点");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_17);
        buttonModel.setName("温泉");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_18);
        buttonModel.setName("学习培训");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_19);
        buttonModel.setName("洗浴/汗蒸");
        data.add(buttonModel);
        buttonModel = new ButtonModel();
        buttonModel.setDrawableIcon(R.drawable.icon_20);
        buttonModel.setName("全部分类");
        data.add(buttonModel);
        return data;
    }
}
