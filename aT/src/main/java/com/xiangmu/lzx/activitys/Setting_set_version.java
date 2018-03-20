package com.xiangmu.lzx.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.SearchDB;
import com.xiangmu.lzx.conent_frament.SheZhiFrament;
import com.xiangmu.lzx.utils.XutilsGetData;

/**
 * Created by Administrator on 2015/11/12.
 */
public class Setting_set_version extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = Setting_set_version.class.getSimpleName();
    private ImageView backsetting,imageView,imageView2,imageView3;
    private TextView tv1;
    private Button quit;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgz_versionset);
        initView();
    }
    private void initView() {
        backsetting = (ImageView) findViewById(R.id.backsetting);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
       tv1= (TextView) findViewById(R.id.tv1);
        quit= (Button) findViewById(R.id.quit);
        listener();
        initVersion();
    }

    private void initVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv1.setText(pInfo.versionName);
            XutilsGetData.xUtilsImageiv(imageView2, "http://www.dcgqxx.com/upload/mffp.gif",this,false);
            XutilsGetData.xUtilsImageiv(imageView3, "http://www.dcgqxx.com/upload/copyRight.gif",this,false);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Cannot get package info", e);
        }
    }

    private void listener() {
        backsetting.setOnClickListener(this);
        tv1.setOnClickListener(this);
        quit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backsetting:
                Intent intent = new Intent(this, Setting_set_page.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
                break;
            case R.id.email:
                String userName = SearchDB.createDb(this, "userName");
                if (userName!=null){
                    tv1.setText(userName);
                }
                break;
            case R.id.quit:
             //   SearchDB.removeDb(getSharedPreferences("hgz", Context.MODE_PRIVATE));
                SearchDB.removeDb(getSharedPreferences("useInfo", Context.MODE_PRIVATE));
                SheZhiFrament.handle.sendEmptyMessage(1);
                finish();
                break;
        }
    }
}
