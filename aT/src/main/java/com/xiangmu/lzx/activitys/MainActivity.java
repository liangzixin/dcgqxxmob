package com.xiangmu.lzx.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xiangmu.lzx.BroadCastReceiver.MyReceiver;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.conent_frament.AddFrament;
import com.xiangmu.lzx.conent_frament.ReDianFrament;
import com.xiangmu.lzx.conent_frament.SheZhiFrament;
import com.xiangmu.lzx.conent_frament.ShiTingFrament;
import com.xiangmu.lzx.conent_frament.XinWenFrament;
import com.xiangmu.lzx.conent_frament.YueDuFrament;
import com.xiangmu.lzx.utils.CommonUtil;
import com.xiangmu.lzx.viewpager.ContentViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ContentViewPager contentViewPager;
    private RadioGroup contentradiogroup;
    private MyReceiver myReceiver;

    final int RESULT_CODE=101;
    final int REQUEST_CODE=1;
    private String id ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);}
        checkNetState();//检查网络状态
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);

        Intent intent =getIntent();
       if(intent.getStringExtra("fragid")!=null) id =intent.getStringExtra("fragid");

        initdata();//填充数据
        initview();//填充布局



    }


    private List<Fragment> content_list = null;

//        @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);  //这个super可不能落下，否则可能回调不了
//            contentViewPager.notifyDataSetChanged();
//            contentViewPager.getItem(4);
////            contentradiogroup.check(R.id.rb_shezhi);
//            contentViewPager.setCurrentItem(4);
// 当otherActivity中返回数据的时候，会响应此方法
// requestCode和resultCode必须与请求startActivityForResult()和返回setResult()的时候传入的值一致。
//        System.out.println("requestCode="+requestCode);
//        System.out.println("resultCode="+resultCode);
//        System.out.println(" ProductinfoAddActivity.RESULT_CODE="+ ProductinfoAddActivity.RESULT_CODE);
////        if (requestCode == 1 && resultCode == ProductinfoAddActivity.RESULT_CODE) {
////        if (resultCode == ProductinfoAddActivity.RESULT_CODE) {
//            initdata();//填充数据
//            initview();//填充布局
//            Bundle bundle = data.getExtras();
//        String strResult = bundle.getString("result");
//        Log.i(TAG, "onActivityResult: " + strResult);
//        Toast.makeText(MainActivity.this, requestCode, Toast.LENGTH_LONG).show();
//        }
//    }
    private void initdata() {
        content_list = new ArrayList<>();
        content_list.add(new XinWenFrament());
        content_list.add(new ReDianFrament());
        content_list.add(new ShiTingFrament());
        content_list.add(new AddFrament());
        content_list.add(new SheZhiFrament());
    }

    private void initview() {
        if (content_list == null) {
            return;
        }
        contentViewPager = (ContentViewPager) findViewById(R.id.content_viewpager);
        contentradiogroup = (RadioGroup) findViewById(R.id.content_radiogroup);
        //预加载一页
        contentViewPager.setOffscreenPageLimit(2);
        contentViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return content_list.get(i);
            }

            @Override
            public int getCount() {
                return content_list.size();
            }

        });
        contentradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_xinwen:
                        contentViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_redian:
                        contentViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_shiting:
                        contentViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_yuedu:
                        contentViewPager.setCurrentItem(3);

                        break;
                    case R.id.rb_shezhi:
                        contentViewPager.setCurrentItem(4);
                        break;
                }
            }
        });

      //  然后根据这个id跳转即可
        if(id.equals("lzx")){
            contentradiogroup.check(R.id.rb_shezhi);
        }else{
            contentradiogroup.check(R.id.rb_xinwen);
        }
    }


    /**
     * 检查网络是否连接
     */
    private void checkNetState() {
        if (!CommonUtil.isNetWork(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("网络状态提醒");
            builder.setMessage("当前网络不可用，是否打开网络设置???");
            builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (android.os.Build.VERSION.SDK_INT > 10) {
                        startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                    } else {
                        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    }
                }
            });
            builder.create().show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private long timeMillis;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timeMillis = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    protected void onResume() {
//    supper.on
//    }

}
