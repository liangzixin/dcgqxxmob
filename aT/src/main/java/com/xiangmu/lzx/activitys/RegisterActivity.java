package com.xiangmu.lzx.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
//import android.os.Handler;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiangmu.lzx.Modle.Shezhi;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.xutils.x;



public class RegisterActivity extends Activity implements View.OnClickListener{
    private int countSeconds = 60;//倒计时秒数
    private EditText mobile_login, yanzhengma,username,pwd1,pwd2;
    private Button getyanzhengma1, login_btn,reset_btn;
    ImageView loginimage_back;
    LinearLayout line;
    private Context mContext;
    private String usersuccess;
    private ProgressDialog loginProgress;
    static Boolean used=false;
    public  String  yanzhengma0="";
    public static final int MSG_REGISTER_RESULT = 0;
    public static final int MSG_RECHECK_RESULT = 1;
    private static Gson gson = new Gson();

    private Handler mCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what) {
                case 0:
                    if (countSeconds > 0) {
                        --countSeconds;
                //   loginProgress.dismiss();
                        getyanzhengma1.setText("(" + countSeconds + ")后获取验证码");
                        mCountHandler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        countSeconds = 60;
                        getyanzhengma1.setText("请重新获取验证码");
                        yanzhengma0="";
                    }
                    break;
                case 1:
                    //   loginProgress.dismiss();
                    JSONObject json = (JSONObject) msg.obj;
                    hanleCreateAccountResult(json);
                    break;
            }

        }
    };
    private String userinfomsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
        initData();
    }
    private void initView() {
        username= (EditText) findViewById(R.id.username);
        mobile_login = (EditText) findViewById(R.id.mobile_login);
        getyanzhengma1 = (Button) findViewById(R.id.getyanzhengma1);
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        pwd1= (EditText) findViewById(R.id.pwd1);
        pwd2= (EditText) findViewById(R.id.pwd2);
        login_btn = (Button) findViewById(R.id.login_btn);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        loginimage_back = (ImageView) findViewById(R.id.loginimage_back);
        line= (LinearLayout) findViewById(R.id.line2);
    }
    private void initEvent() {
        getyanzhengma1.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        reset_btn.setOnClickListener(this);
        loginimage_back.setOnClickListener(this);
        line.setOnClickListener(this);
//            mobile_login.addTextChangedListener(new TextWatcher() {
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    // 输入的内容变化的监听
//                    Log.e("输入过程中执行该方法", "文字变化");
//                }
//
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count,
//                                              int after) {
//                    // 输入前的监听
//                    Log.e("输入前确认执行该方法", "开始输入");
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    // 输入后的监听
//                    Log.e("输入结束执行该方法", "输入结束");
//                    Toast.makeText(RegisterActivity.this, "输入结束执行该方法", Toast.LENGTH_SHORT).show();
//                }
//            });
    }
    private void initData() {
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getyanzhengma1:
                if (countSeconds == 60) {
                    String mobile = mobile_login.getText().toString();
                    Log.e("tag", "mobile==" + mobile);
                    getMobiile(mobile);
                } else {
                    Toast.makeText(RegisterActivity.this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_btn:
                handleCreateAccount();
                break;
            case R.id.loginimage_back:
                finish();
                overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
            default:
                break;
        }
    }
    //获取信息进行登录
    public void login() {
        String mobile = mobile_login.getText().toString().trim();
        String verifyCode = yanzhengma.getText().toString().trim();
        RequestParams params = new RequestParams("这里换成你的请求登录的接口");
        //     x.http().post(params, new Handler.Callback.ProgressCallback<String>() {
        x.http().post(params, new Callback.CommonCallback<String>() {
            //                @Override
//                public void onWaiting() {
//                }
//                @Override
//                public void onStarted() {
//                }
//                @Override
//                public void onLoading(long total, long current, boolean isDownloading) {
//                }
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    Log.e("tag", "登陆的result=" + jsonObject);
                    String success = jsonObject.optString("success");
                    String data = jsonObject.optString("data");
                    String msg=jsonObject.optString("msg");
                    if ("true".equals(success)) {
                        Log.e("tag","登陆的data="+data);
                        JSONObject json = new JSONObject(data);
//                            token = json.optString("token");
//                            userId = json.optString("userId");
//                            //我这里按照我的要求写的，你们也可以适当改动
//                            //获取用户信息的状态
//                            getUserInfo();
                    }else{
                        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
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
            Toast.makeText(this, "电话号码校验失败，已经注册过！", Toast.LENGTH_LONG).show();
            return;
        }

        if(result == 2) {
            Toast.makeText(this, "数据库操作异常！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 3) {
            Toast.makeText(this, "注册成功！", Toast.LENGTH_LONG).show();
//            String result = responseInfo.result;
//            msg.obj=result;
            String userName="";
            String profile_image_url ="";
            String jinbi ="";
            String customerid="";
            String shezhi="";
            String openid="";
            List<Shezhi> listshezhi=new ArrayList<Shezhi>();
            try {
           ///     JSONObject myobject = new JSONObject(result);
                JSONObject myobject= json.getJSONObject("customer");
                userName= myobject.getString("username");
                profile_image_url = myobject.getString("imageurl");
                jinbi = myobject.getString("jinbi");
                customerid= myobject.getString("id");
                shezhi=myobject.getString("shezhi");
                openid=myobject.getString("openid");

                if (!myobject.isNull("shezhi")){
                    JSONArray shezhiArray=myobject.getJSONArray("shezhi");
                    for (int j=0;j<shezhiArray.length();j++){
                        JSONObject shezhi0=shezhiArray.getJSONObject(j);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject =shezhiArray.getJSONObject(j);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(jsonObject != null){
                            Shezhi tempAccount = gson.fromJson(jsonObject.toString(),Shezhi.class);
                            listshezhi.add(tempAccount);
                        }
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            String shezhi0= gson.toJson(listshezhi);

            getSharedPreferences("useInfo",Context.MODE_PRIVATE).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).putString("openid",openid).commit();

            finish();



            overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
        }
        if(result == 4) {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 5) {
            Toast.makeText(this, "验证码获取失败！", Toast.LENGTH_LONG).show();
            return;
        }
//        if(result ==6) {
//
//            try {
//                JSONObject shortmessage = json.getJSONObject("shortmessage");
//                yanzhengma0=shortmessage.getString("captcha");
//                // shortmessageEntity =(XinWen_productinfo.ShortmessageEntity)json.getJSONObject("shortmessage")
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(this, "验证码获取成功！", Toast.LENGTH_LONG).show();
//            return;
//        }
        if(result ==7) {
            Toast.makeText(this, "该手机号码今天发送验证码过多！", Toast.LENGTH_LONG).show();
            return;
        }
        if(result == 0) {
//            Toast.makeText(this, "电话号码校验正确！", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
            requestVerifyCode();
//            return;
        }

    };
    //注册前校验数据
    private void handleCreateAccount() {
        boolean isUsernameValid = checkUsername();
        if(!isUsernameValid) {
            Toast.makeText(this, "用户名不正确，请重新输入", Toast.LENGTH_LONG).show();
            return;
        }
        int pwdResult = checkPassword();
        if(pwdResult == 1) {
            Toast.makeText(this, "两次输入的密码不一致，请确认！", Toast.LENGTH_LONG).show();
            return;
        }
        if (pwdResult == 2) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(mobile_login.getText().toString())) {
            Toast.makeText(this, "请输入电话号码！", Toast.LENGTH_LONG).show();
            return;
        }
        int yanzhengmaResult = checkYanzhengma();
        if(yanzhengmaResult == 1) {
            Toast.makeText(this, "验证码错误，请确认！", Toast.LENGTH_LONG).show();
            return;
        }
        if (yanzhengmaResult == 2) {
            Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }

        createAccount();
    }
    //获取验证码信息，判断是否有手机号码
    private void getMobiile(String mobile) {
        if ("".equals(mobile)) {
            Log.e("tag", "mobile=" + mobile);
            // new AlertDialog.Builder(mContext).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
            Toast.makeText(this, "手机号码不能为空！", Toast.LENGTH_LONG).show();
        } else if (isMobileNO(mobile) == false) {
            //  new AlertDialog.Builder(mContext).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
            Toast.makeText(this, "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
        }else {

            isMobileUsed(mobile);
        }
        // new AlertDialog.Builder(mContext).setTitle("提示").setMessage("该手机号码已经注册过").setCancelable(true).show();

//            else {
//                Log.e("tag", "输入了正确的手机号");
//                requestVerifyCode(mobile);
//
//            }
    }
    //获取验证码信息，进行验证码请求
    private void requestVerifyCode() {
        RequestParams requestParams = new RequestParams("http://192.168.16.101:8086/dcgqxx/customerAction!smsMob.action");
        requestParams.addBodyParameter("mobile",mobile_login.getText().toString());
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
                    int result_code;
                    result_code= json.getInt("result_code");


                    if(result_code==6) {
                        sendMessage(MSG_REGISTER_RESULT, json);
                        JSONObject shortmessage = json.getJSONObject("shortmessage");
                        yanzhengma0=shortmessage.getString("captcha");
                        Toast.makeText(RegisterActivity.this, "验证码获取成功！", Toast.LENGTH_LONG).show();
                    }else{
                            sendMessage(MSG_RECHECK_RESULT, json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //  Toast.makeText(RegisterActivity.this, "错误", Toast.LENGTH_SHORT).show();
                yanzhengma0="";
                ex.printStackTrace();
            }
            @Override
            public void onCancelled(CancelledException cex) {
                //  Toast.makeText(RegisterActivity.this, "错误1", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFinished() {
                //  Toast.makeText(RegisterActivity.this, "错误2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //使用正则表达式判断电话号码
    public static boolean isMobileNO(String tel) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(tel);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    //判断用户名是否为空
    private boolean checkUsername() {
        String username0 = username.getText().toString();
        if(TextUtils.isEmpty(username0)) {
            return false;
        }
        return true;
    }
    //判断两次密码是否一致
    private int checkPassword() {
        /*
         * return value:
         * 0 password valid
         * 1 password not equal 2 inputs
         * 2 password empty
         * */
        String pwd10 = pwd1.getText().toString();
        String pwd20 = pwd2.getText().toString();
        if(!pwd10.equals(pwd20)) {
            return 1;
        } else if(TextUtils.isEmpty(pwd10)) {
            return 2;
        } else {
            return 0;
        }
    }
    //判断输入的验证码是否一致
    private int checkYanzhengma() {
        /*
         * return value:
         * 0 password valid
         * 1 password not equal 2 inputs
         * 2 password empty
         * */
        String yanzhengma00=yanzhengma.getText().toString();

        if(!yanzhengma00.equals(yanzhengma0)) {
            return 1;
        } else if(TextUtils.isEmpty(yanzhengma00)) {
            return 2;
        } else {
            return 0;
        }
    }
    //使用后台校验电话号码是否注册过
    private void  isMobileUsed(final String mobile) {
//        loginProgress = new ProgressDialog(this);
//        loginProgress.setMessage("正在校验手机号...");
//        loginProgress.show();
        RequestParams requestParams = new RequestParams("http://192.168.16.101:8086/dcgqxx/customerAction!checkuserMobile.action");
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

//                    Log.e("tag", "获取验证码==" );
//                    if (result.equals("true")) {
//                        used=true;
//                        loginProgress.dismiss();
//
//                   Toast.makeText(RegisterActivity.this,"手机号已经注册过", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                        requestVerifyCode(mobile);
//                    }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(RegisterActivity.this,"数据库操作异常", Toast.LENGTH_SHORT).show();
//
//                Log.e("tag", "手机号已经注册过0" );
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
    //注册类
    private void  createAccount() {
        loginProgress = new ProgressDialog(this);
        loginProgress.setMessage("正注册...");
        loginProgress.show();
        RequestParams requestParams = new RequestParams("http://192.168.16.101:8086/dcgqxx/customerAction!saveMob.action");
        requestParams.addBodyParameter("mobile",mobile_login.getText().toString());
        requestParams.addBodyParameter("username",username.getText().toString());
        requestParams.addBodyParameter("password",pwd1.getText().toString());
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
                loginProgress.dismiss();
                try {
                    JSONObject json = new JSONObject(result);
                    sendMessage(MSG_RECHECK_RESULT, json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loginProgress.dismiss();
                Toast.makeText(RegisterActivity.this,"数据库操作异常", Toast.LENGTH_SHORT).show();

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
    //获取验证码信息,进行计时操作
    private void startCountBack() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getyanzhengma1.setText(countSeconds + "");
                mCountHandler.sendEmptyMessage(0);
            }
        });
    }

    private void sendMessage(int what, Object obj) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        mCountHandler.sendMessage(msg);
    }
    private void handleReset() {
        username.setText("");
        mobile_login.setText("");
        pwd1.setText("");
        pwd2.setText("");
        yanzhengma.setText("");
        yanzhengma0="";
    }
}
