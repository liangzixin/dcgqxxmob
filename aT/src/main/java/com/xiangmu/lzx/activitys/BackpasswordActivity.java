package com.xiangmu.lzx.activitys;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.Md5Utils;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.Utils;
import com.xiangmu.lzx.utils.XinWenURL;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class BackpasswordActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView loginimage_back;
    EditText number;

    private Context mContext;
    private EditText yanzhengma;
    private Button getyanzhengma1,login_button;
    public  String  yanzhengma0="";
    private int countSeconds = 60;//倒计时秒数
    public static final int MSG_REGISTER_RESULT = 0;
    public static final int MSG_RECHECK_RESULT = 1;
    private ProgressDialog loginProgress;
    private XinWenURL xinWenURL=new XinWenURL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpassword);
        mContext = this;
        initView();
    }
    private Handler mCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what) {
                case 0:
                    if (countSeconds > 0) {
                        --countSeconds;
                        //      loginProgress.dismiss();
                        getyanzhengma1.setText("(" + countSeconds + ")后获取验证码");
                        mCountHandler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        countSeconds = 60;
                        getyanzhengma1.setText("请重新获取验证码");

                    }
                    break;
                case 1:
//                        loginProgress.dismiss();
                    JSONObject json = (JSONObject) msg.obj;
                    hanleCreateAccountResult(json);

            }

        }
    };
    private void initView() {
        loginimage_back = (ImageView) findViewById(R.id.loginimage_back);//返回按钮
        number = (EditText) findViewById(R.id.number);//要更改的手机号

        getyanzhengma1 = (Button) findViewById(R.id.getyanzhengma1);
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        loginimage_back.setOnClickListener(this);

        getyanzhengma1.setOnClickListener(this);
        login_button = (Button) findViewById(R.id.login_button);//获取密码按钮
        login_button.setOnClickListener(this);
        yanzhengma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                String s2 = s.toString().trim();
                if (!"".equals(s2)) {
                    login_button.setEnabled(true);
                    Drawable drawable = getResources().getDrawable(R.drawable.login_button_a);
                    login_button.setBackground(drawable);
                } else {
                    login_button.setEnabled(false);
                    login_button.setBackground(getResources().getDrawable(R.drawable.biankuang));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginimage_back:
                finish();
                overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                break;
            case R.id.login_button:
                String yanzhengma1 =yanzhengma.getText().toString().trim();
                if (!yanzhengma1.equals(yanzhengma0)) {

                    Toast.makeText(this, "验证码不正确,请重新输入...", Toast.LENGTH_SHORT).show();
                    return;
                }
                getBackPassword();
                break;
            case R.id.getyanzhengma1:
                String numbers = number.getText().toString().trim();
                if (!Utils.isPhone(numbers)) {

                    Toast.makeText(this, "手机号格式不正确,请重新输入...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (countSeconds != 60)
            {
                Toast.makeText(BackpasswordActivity.this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
                return;
                }
                isMobileUsed(numbers);
               break;

        }
    }
    //获取验证码信息，判断是否有手机号码
    private void getMobiile(String mobile) {
        if ("".equals(mobile)) {
            Log.e("tag", "mobile=" + mobile);
            // new AlertDialog.Builder(mContext).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
            Toast.makeText(this, "手机号码不能为空！", Toast.LENGTH_LONG).show();
        } else if (Utils.isPhone(mobile) == false) {
            //  new AlertDialog.Builder(mContext).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
            Toast.makeText(this, "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
        }else {

            isMobileUsed(mobile);
        }

    }
    //根据返回值做如下判断
    private void hanleCreateAccountResult(JSONObject json) {
        /*
         *   result_code:
         * 0  电话号码校验成功，没有注册过
         * 1  电话号码校验失败，已经注册过
         * 2  数据库操作异常
         * 3  注册成功
         * 4  注册失败
         * */
        int result;
        try {
            result = json.getInt("result_code");
        } catch (JSONException e) {
            Toast.makeText(this, "没有获取到网络的响应！", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }

        if(result == 1) {
           // Toast.makeText(this, "电话号码校验失败，已经注册过！", Toast.LENGTH_LONG).show();
            requestVerifyCoe();
            return;
        }

        if(result == 2) {
            Toast.makeText(this, "数据库操作异常！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 3) {
            Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 4) {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 5) {
            Toast.makeText(this, "验证码获取失败！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result ==6) {
            Toast.makeText(this, "验证码获取成功！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 0) {
            //     Toast.makeText(this, "电话号码校验正确！", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
            requestVerifyCoe();
            //  return;
        }

    };
    //获取验证码信息，进行验证码请求
    private void requestVerifyCoe(){
        String url=xinWenURL.getSmsMob();
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("mobile",number.getText().toString());
        x.http().post(requestParams, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {
            }
            @Override
            public void onStarted() {
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
            }
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject2 = new JSONObject(result);
                    Log.e("tag", "jsonObject2" + jsonObject2);
                     JSONObject shortmessage =jsonObject2.getJSONObject("shortmessage");
                    String captcha = shortmessage.getString("captcha");
                    String verifyCode = shortmessage.getString("smstype");
                    Log.e("tag", "获取验证码==" + verifyCode);
                    if ("6".equals(verifyCode)) {
                        yanzhengma0=captcha;

                        Toast.makeText(BackpasswordActivity.this, "获取验证码成功!", Toast.LENGTH_SHORT).show();
                        startCountBack();//这里是用来进行请求参数的
                    } else if("5".equals(verifyCode)){

                        Toast.makeText(BackpasswordActivity.this, "获取验证码失败!", Toast.LENGTH_SHORT).show();
                    }else if("7".equals(verifyCode)){

                        Toast.makeText(BackpasswordActivity.this, "该手机号码今天发送验证码过多!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    try {
//                    JSONObject json = new JSONObject(result);
//                    sendMessage(MSG_REGISTER_RESULT, json);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(BackpasswordActivity.this, "获取验证码错误", Toast.LENGTH_SHORT).show();
                yanzhengma0="";
                ex.printStackTrace();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                //  Toast.makeText(MainActivity.this, "错误1", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                //  Toast.makeText(MainActivity.this, "错误2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //获取验证码信息，进行验证码请求
    private void getBackPassword(){
        String url=xinWenURL.getGetbackpasswordMob();
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("mobile",number.getText().toString());
        x.http().post(requestParams, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {
            }
            @Override
            public void onStarted() {
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
            }
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject2 = new JSONObject(result);
                    Log.e("tag", "jsonObject2" + jsonObject2);
                    JSONObject shortmessage =jsonObject2.getJSONObject("shortmessage");
                    String captcha = shortmessage.getString("captcha");
                    String verifyCode = shortmessage.getString("smstype");
                    Log.e("tag", "取回密码==" + verifyCode);
                    if ("6".equals(verifyCode)) {
                        yanzhengma0=captcha;

                        Toast.makeText(BackpasswordActivity.this, "取回密码成功!", Toast.LENGTH_SHORT).show();
                        login_button.setText("取回密码成功");
                        login_button.setEnabled(false);
                        login_button.setBackground(getResources().getDrawable(R.drawable.biankuang));
                    } else if("5".equals(verifyCode)){

                        Toast.makeText(BackpasswordActivity.this, "取回密码失败!", Toast.LENGTH_SHORT).show();
                    }else if("7".equals(verifyCode)){

                        Toast.makeText(BackpasswordActivity.this, "该手机号码今天取回密码次数过多!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(BackpasswordActivity.this, "取回密码错误", Toast.LENGTH_SHORT).show();
           //     yanzhengma0="";
                ex.printStackTrace();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                //  Toast.makeText(MainActivity.this, "错误1", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                //  Toast.makeText(MainActivity.this, "错误2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //获取验证码信息,进行计时操作
    private void startCountBack() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getyanzhengma1.setText(countSeconds + "");
                yanzhengma.setFocusableInTouchMode(true);
                yanzhengma.requestFocus();

                mCountHandler.sendEmptyMessage(0);
            }
        });
    }
    //使用后台校验电话号码是否注册过
    private void  isMobileUsed(final String mobile) {
        String url=xinWenURL.getCheckuserMobile();
//        loginProgress = new ProgressDialog(this);
//        loginProgress.setMessage("正在校验手机号...");
//        loginProgress.show();
        RequestParams requestParams = new RequestParams(url);
//        RequestParams requestParams = new RequestParams("http://192.168.16.101:8086/dcgqxx/customerAction!checkuserMobile.action");
        requestParams.addBodyParameter("mobile",mobile);
        x.http().post(requestParams, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {
            }
            @Override
            public void onStarted() {
            }
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
            }
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    sendMessage(MSG_RECHECK_RESULT, json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(MainActivity.this,"数据库操作异常", Toast.LENGTH_SHORT).show();
//
              Log.e("tag", "手机号已经注册过0" );
                ex.printStackTrace();
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished()
            {
                //  loginProgress.dismiss();

            }
        });
        ;

    }
    private void sendMessage(int what, Object obj) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        mCountHandler.sendMessage(msg);
    }
}