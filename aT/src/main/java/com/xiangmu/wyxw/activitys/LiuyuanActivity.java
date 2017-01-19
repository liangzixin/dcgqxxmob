package com.xiangmu.wyxw.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.wyxw.Modle.ProductArticler;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.jieping.ScreenShot;
import com.xiangmu.wyxw.utils.DateTime;
import com.xiangmu.wyxw.utils.XinWenXiData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LiuyuanActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private android.view.animation.Animation animation;
//    private List list;
    private View mNightView = null;
    private WindowManager mWindowManager;
    private XinWenXiData xinWenXiData;
    private TextView customer;
    private TextView content;
    private TextView liuyuandate;
    private ImageButton imagebacklzx ;
    private List<ProductArticler> liuyuanlist;
    private DateTime dateTime=new DateTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        liuyuanlist = (List<ProductArticler>) intent.getSerializableExtra("liuyuanlist");
//
        if (mNightView == null) {
            mNightView = new TextView(this);
            mNightView.setBackgroundColor(0x75000000);
        }
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        setContentView(R.layout.activity_liuyuan);
        animation= AnimationUtils.loadAnimation(this,R.anim.nn);
        listView = (ListView) findViewById(R.id.listView);
        initview();
//        for (int i = 0; i < 30; i++) {
//            liuyuanlist.add("aaa" + i);
//        }
        listView.setAdapter(new MyListAdapter());
    }
    private void initview() {

        imagebacklzx = (ImageButton) findViewById(R.id.xinwen_xi_backlzx);//返回
        imagebacklzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpopuwindow(view);
            }
        });
    }
    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return liuyuanlist.size() == 0 ? 0 : liuyuanlist.size();
        }

        @Override
        public Object getItem(int position) {
            return liuyuanlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(LiuyuanActivity.this, R.layout.gentie_listview_item, null);
            }
            customer= (TextView) convertView.findViewById(R.id.customer);
            content= (TextView) convertView.findViewById(R.id.content);
            liuyuandate= (TextView) convertView.findViewById(R.id.liuyuandate);
            final View finalConvertView = convertView;
            ImageView imageView = (ImageView) convertView.findViewById(R.id.zan);
            imageView.setBackground(getResources().getDrawable(R.drawable.biz_news_list_other_segments_support));
            imageView.setEnabled(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout relativeLayout = (RelativeLayout) v.getParent();
                    LinearLayout linearLayout = (LinearLayout) relativeLayout.getParent();
                    TextView zannum = (TextView) linearLayout.findViewById(R.id.zannum);
                    zannum.setText((Integer.parseInt(zannum.getText().toString()) + 1) + "");
                    textView = (TextView) relativeLayout.findViewById(R.id.tv_one);
                    Log.e("aa","---------"+textView);
                    Toast.makeText(LiuyuanActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                    textView.setVisibility(View.VISIBLE);
                    v.setBackground(getResources().getDrawable(R.drawable.biz_news_list_other_segments_support_done));
                    v.setEnabled(false);
                    textView.startAnimation(animation);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            textView.setVisibility(View.GONE);
                        }
                    }, 500);
                }
            });
            LinearLayout linearparent = (LinearLayout) convertView.findViewById(R.id.parentlinea);
            linearparent.removeAllViews();
            customer.setText(liuyuanlist.get(position).getArtreview_authorid());
            content.setText(liuyuanlist.get(position).getArtreview_content());
           liuyuandate.setText(dateTime.getYmd(liuyuanlist.get(position).getArtreview_time()));
//            if (position == 3 | position == 8 | position == 13 | position == 17 | position == 28) {
//                switch (position) {
//                    case 3:
//                        //到时候可以根据 字段判断具体添加几条回复
//                        for (int i = 0; i < 2; i++) {
//                            View view = View.inflate(LiuyuanActivity.this, R.layout.gentie_huifu_item, null);
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
//                            View view = View.inflate(LiuyuanActivity.this, R.layout.gentie_huifu_item, null);
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
//                            View view = View.inflate(LiuyuanActivity.this, R.layout.gentie_huifu_item, null);
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
//                            View view = View.inflate(LiuyuanActivity.this, R.layout.gentie_huifu_item, null);
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
//                            View view = View.inflate(LiuyuanActivity.this, R.layout.gentie_huifu_item, null);
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

                Toast.makeText(LiuyuanActivity.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(LiuyuanActivity.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(LiuyuanActivity.this, PictureActivity.class);
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
                // ZiTiScale.zitiStyle2(LiuyuanActivity.this, view);
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
}
