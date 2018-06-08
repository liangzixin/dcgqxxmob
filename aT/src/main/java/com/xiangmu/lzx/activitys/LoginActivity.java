package com.xiangmu.lzx.activitys;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
//import com.xiangmu.lzx.utils.Constants;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xiangmu.lzx.Modle.Shezhi;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.ConstantsLzx;
import com.xiangmu.lzx.utils.HttpPostThread;
import com.xiangmu.lzx.utils.HttpUtil;
import com.xiangmu.lzx.utils.ThreadPoolUtils;
import com.xiangmu.lzx.utils.Utils;
import com.xiangmu.lzx.utils.XinWenURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getName();
    private ImageView loginback;
    private TextView login_password_back,register;
    private Button login_button;
    private LinearLayout weixin_login,qq_login,xinlang_login;
    private EditText login_zhanghao,login_password;
    private HttpHandler<String> handler;
    private HttpUtils httpUtils;
    private String url=null;
    private String opid="";
    private XinWenURL xinWenURL=new XinWenURL();
  //  UMSocialService mController;
    private static Gson gson = new Gson();
    private static   Message msg = new Message();
    private SharedPreferences sp;
 //   private  UMShareAPI mShareAPI;
     private MyApplication app;
    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;
    public static String mAppid;
    private UserInfo mInfo;
    private  String userName="";
    private  String profile_image_url ="";
    private  String openid="";
    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app =new MyApplication();
        mAppid =app.APP_ID;
        // Config.REDIRECT_URL = "http://sns.whalecloud.com";
        sp = getSharedPreferences("kk", Context.MODE_PRIVATE);
      //  mShareAPI = UMShareAPI.get(this);
        mTencent = Tencent.createInstance(mAppid, this);
        WbSdk.install(this,new AuthInfo(this, ConstantsLzx.APP_KEY, ConstantsLzx.REDIRECT_URL, ConstantsLzx.SCOPE));
        // 微博授权功能
        mSsoHandler = new SsoHandler(LoginActivity.this);
        initView();
    }
    private void initView() {
        login_password_back = (TextView) findViewById(R.id.login_password_back);//找回密码
        register = (TextView) findViewById(R.id.register);//注册
        loginback = (ImageView) findViewById(R.id.loginimage_back);//返回
        login_zhanghao = (EditText) findViewById(R.id.login_zhanghao);//账号
        login_password = (EditText) findViewById(R.id.login_password);//密码
        weixin_login = (LinearLayout) findViewById(R.id.weixin_login);//微信登陆
        qq_login = (LinearLayout) findViewById(R.id.qq_login);//QQ登录
        xinlang_login = (LinearLayout) findViewById(R.id.xinlang_login);//新浪登陆
        login_button = (Button) findViewById(R.id.login_button);//登陆按钮

//        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
////        xinlang = (Button) findViewById(R.id.main_btn1);
////        qq = (Button) findViewById(R.id.main_btn2);
////        weixin = (Button) findViewById(R.id.main_btn3);
//        //设置新浪SSO handler
//        mController.getConfig().setSsoHandler(new SinaSsoHandler());
////        getConfig().setSinaCallBackUrl(" 微博开放平台配置的地址");
//        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this, "1105789263",
//                "Z72WYfitxSU5HyVO");
//        qqSsoHandler.addToSocialSDK();


        loginback.setOnClickListener(this);
        weixin_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        xinlang_login.setOnClickListener(this);
        login_button.setOnClickListener(this);
        login_password_back.setOnClickListener(this);
        register.setOnClickListener(this);

        login_zhanghao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                String s1 = login_password.getText().toString().trim();
                String s2 = s.toString().trim();
                if (!"".equals(s1) && !"".equals(s2)) {
                    login_button.setEnabled(true);
                    Drawable drawable = getResources().getDrawable(R.drawable.login_button_a);
                    login_button.setBackground(drawable);
                } else {
                    login_button.setEnabled(false);
                    login_button.setBackground(getResources().getDrawable(R.drawable.biankuang));
                }
            }
        });
        login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void afterTextChanged(Editable s) {
                String s1 = login_zhanghao.getText().toString().trim();
                String s2 = s.toString().trim();
                if (!"".equals(s1) && !"".equals(s2)) {
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
            case R.id.loginimage_back://暂不登陆,返回
                finish();
                overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                break;
            case R.id.login_password_back://找回密码
                startActivity(new Intent(this,BackpasswordActivity.class));
                finish();
                overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out);
                break;
            case R.id.register://注册
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out);
                break;
            case R.id.weixin_login://微信登录
       //         mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.qq_login://QQ登录
                onClickLogin();
          //      mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.xinlang_login://新浪登陆
                mSsoHandler.authorize(new SelfWbAuthListener());
                break;

            case R.id.login_button://登陆按钮
                String zhanghao = login_zhanghao.getText().toString().trim();
                String password = login_password.getText().toString().trim();
//                if (Utils.isnumber(zhanghao)) {
                 //   login1(zhanghao, password);
                if (!zhanghao.trim().equals("")) {
                    login(zhanghao, password);
                } else {
                    Toast.makeText(this, "亲 ~,别闹,你账号输错了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            android.util.Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                android.util.Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            updateUserInfo();
        //    updateLoginButton();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        android.util.Log.d(TAG, "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            android.util.Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
       //     updateLoginButton();
        }
    };
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Utils.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                Utils.showResultDialog(LoginActivity.this, "返回为空", "登录失败");
                return;
            }
       //     Utils.showResultDialog(LoginActivity.this, response.toString(), "登录成功");
            // 有奖分享处理
            //handlePrizeShare()
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Utils.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            Utils.dismissDialog();
        }

        @Override
        public void onCancel() {
            Utils.toastMessage(LoginActivity.this, "onCancel: ");
            Utils.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    private void updateUserInfo() {

        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {

                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;

                    mHandler.sendMessage(msg);
//                    new Thread(){
//
//                        @Override
//                        public void run() {
//                            JSONObject json = (JSONObject)response;
//                            if(json.has("figureurl")){
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Utils.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        }
//
//                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);


        } else {
//            mUserInfo.setText("");
//            mUserInfo.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                 //                   JSONObject json = (JSONObject)response;
                    try {
                    userName=response.getString("nickname");
//                     openid=response.getString("openid");
                        openid=mTencent.getOpenId();
                        profile_image_url=response.getString("figureurl");
                    } catch (JSONException e) {
                        e.printStackTrace();
                   }
                addcustmer(openid,userName, profile_image_url);
//                if (response.has("nickname")) {
//                    try {
//                //        Toast.makeText(LoginActivity.this, "返回!!!", Toast.LENGTH_SHORT).show();
////                        mUserInfo.setVisibility(android.view.View.VISIBLE);
//                       mUserInfo.setText(response.getString("nickname"));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
            }else if(msg.what == 1){
                Bitmap bitmap = (Bitmap)msg.obj;
//                mUserLogo.setImageBitmap(bitmap);
//                mUserLogo.setVisibility(android.view.View.VISIBLE);
            }
        }

    };
    private void login1(String zhanghao,String password) {
        ThreadPoolUtils.execute(new HttpPostThread(this, zhanghao, password, hand));
    }
    private void login(String zhanghao,String password) {
//        ThreadPoolUtils.execute(new HttpPostThread(this, zhanghao, password, hand));
        httpUtils = new HttpUtils();
      url= HttpUtil.BASE_URL+"customerAction!logonmob.action?zhanghao="+zhanghao+"&password="+password;//最新
        handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (!responseInfo.result.equals("null")) {

                    Toast.makeText(LoginActivity.this, "登陆成功,恭喜你回家!!!", Toast.LENGTH_SHORT).show();
                    String result = responseInfo.result;
                    msg.obj=result;
                    String userName="";
                    String profile_image_url ="";
                    String jinbi ="";
                    String customerid="";
                    String shezhi="";
                    String openid="";
                    List<Shezhi> listshezhi=new ArrayList<Shezhi>();
                    try {
                        JSONObject myobject = new JSONObject(result);
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
                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码不正确!请重新输入!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(LoginActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                boolean b= (boolean) msg.obj;
                if (b) {
                    Toast.makeText(LoginActivity.this, "登陆成功,恭喜你回家!!!", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
                } else {
                    Toast.makeText(LoginActivity.this,"账号或密码不正确!请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        /**使用SSO授权必须添加如下代码 */
////        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
////        if(ssoHandler != null){
////            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
////        }
//    }
    /**
     * 提交用户
     */
    public void addcustmer(String openid,String screenname,String imageurl) {
        String url=xinWenURL.getAddcustmer()+openid+"&screenname="+screenname+"&imageurl="+imageurl;
        UpData(url);
    }
    private void UpData(final String url) {
        if (!url.equals("")) {
            httpUtils = new HttpUtils();

            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    String result = responseInfo.result;
                    msg.obj=result;
               //     String userName="";
                //    String profile_image_url ="";
                    String jinbi ="";
                    String customerid="";
                    String shezhi="";
                //    String openid="";
                    List<Shezhi> listshezhi=new ArrayList<Shezhi>();
                    try {
                        JSONObject myobject = new JSONObject(result);
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


                }

                @Override
                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(WebProductinfoViewActivity.this, "留言请求失败", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    public  Gson getGson() {
        return gson;
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
           LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    mAccessToken = token;
//                    if (mAccessToken.isSessionValid()) {
//                        // 显示 Token
//                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
//                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                        Toast.makeText(LoginActivity.this,
                                R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
//                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(LoginActivity.this,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(LoginActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

