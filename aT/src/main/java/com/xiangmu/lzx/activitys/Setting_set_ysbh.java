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
public class Setting_set_ysbh extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = Setting_set_ysbh.class.getSimpleName();
    private ImageView backsetting;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hgz_ysbhset);
        initView();
    }
    private void initView() {
        backsetting = (ImageView) findViewById(R.id.backsetting);
        tv1= (TextView) findViewById(R.id.tv01);
        initVersion();
    }

    private void initVersion() {

          //   tv1.setText(R.string.umeng_update_content);
        tv1.setText(R.string.ysbh);
    }

    private void listener() {
        backsetting.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backsetting:
                Intent intent = new Intent(this, Setting_set_version.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
                break;

        }
    }
}
