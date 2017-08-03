package com.xiangmu.lzx.activitys;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;
import com.xiangmu.lzx.Modle.Shezhi;
import com.xiangmu.lzx.R;
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
import java.util.Map;
import java.util.Set;

//import com.umeng.socialize.controller.UMServiceFactory;
//import com.umeng.socialize.controller.UMSocialService;
//import com.umeng.socialize.controller.listener.SocializeListeners;
//import com.umeng.socialize.exception.SocializeException;
//import com.umeng.socialize.sso.SinaSsoHandler;
//import com.umeng.socialize.sso.UMQQSsoHandler;
//import com.umeng.socialize.sso.UMSsoHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
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
    private  UMShareAPI mShareAPI;
     private MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app =new MyApplication();
        // Config.REDIRECT_URL = "http://sns.whalecloud.com";
        sp = getSharedPreferences("kk", Context.MODE_PRIVATE);
        mShareAPI = UMShareAPI.get(this);
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
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.qq_login://QQ登录

                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.xinlang_login://新浪登陆
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;

            case R.id.login_button://登陆按钮
                String zhanghao = login_zhanghao.getText().toString().trim();
                String password = login_password.getText().toString().trim();
                if (Utils.isnumber(zhanghao)) {
                    login1(zhanghao, password);
                } else {
                    Toast.makeText(this, "亲 ~,别闹,你账号输错了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void login1(String zhanghao,String password) {
        ThreadPoolUtils.execute(new HttpPostThread(this, zhanghao, password, hand));
    }
    private void login(String zhanghao,String password) {
//        ThreadPoolUtils.execute(new HttpPostThread(this, zhanghao, password, hand));
        httpUtils = new HttpUtils();
      url= HttpUtil.BASE_URL+"login!logonmob.action?username="+zhanghao+"&password="+password;//最新
        handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.result != null) {
//                    SharedPreferencesUtil.saveData(LoginActivity.this, url, responseInfo.result);
//                    paserData(1, responseInfo.result);
                    Toast.makeText(LoginActivity.this, "登陆成功,恭喜你回家!!!", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out);
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
//                    SearchDB.removeDb(getSharedPreferences("useInfo", Context.MODE_PRIVATE));
                //    app = (MyApplication) getApplication(); //获得我们的应用程序MyApplication
                 //   app.setSearchDB0(true);


         //        PreferenceManager.getDefaultSharedPreferences(app.getCtx());
                 //   app.getCtx().getSharedPreferences("useInfo", Context.MODE_MULTI_PROCESS);
             //   app.getCtx().getSharedPreferences("useInfo", app.getCtx().MODE_MULTI_PROCESS).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid).putString("shezhi",shezhi0).commit();
                    getSharedPreferences("useInfo",Context.MODE_PRIVATE).edit().putString("userName", userName).putString("pic_path",profile_image_url).putString("jinbi",jinbi).putString("customerid",customerid+"").putString("shezhi",shezhi0).putString("openid",openid).commit();
//                    finish();
//                    SharedPreferences sharedPreferences = getSharedPreferences("useInfo", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
//                    editor.putString("userName",userName);
//                    editor.putString("pic_path",profile_image_url);
//                    editor.commit();//提交修改
//                    app.setSearchDB0(true);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);

                    finish();
//            msg.obj=profile_image_url;
//            SheZhiFrament.handle.handleMessage(msg);
//                                   Intent intent= new Intent();
//                                    intent.setClass(LoginActivity.this,MainActivity.class);
//                                    intent.putExtra("fragid","lzx");
//                                    startActivity(intent);
//                    finish();
//                    startActivityForResult(intent,4);

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

    private UMAuthListener umAuthListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            // Toast.makeText(MainActivity.this, "Authorize succeed", Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Set<String> set = data.keySet();
            SharedPreferences.Editor edit = sp.edit();
            String userName ="";
            String profile_image_url ="";
            String opid="";
            for (String string : set) {

                                // 设置头像
                                if (string.equals("profile_image_url")) {
                                    profile_image_url = data.get(string);
                                    Log.i("-------image",profile_image_url);
                                }
                                // 设置昵称
                         if (platform== SHARE_MEDIA.QQ) {

                             if (string.equals("openid")) {
                                 opid = data.get(string);
                             }
                         }else if  (platform== SHARE_MEDIA.SINA) {
                             if (string.equals("id")) {
                                 opid = data.get(string);
                             }
                         }else if  (platform== SHARE_MEDIA.WEIXIN) {
                             if (string.equals("unionid")) {
                                 opid = data.get(string);
                             }
                         }
                               // 设置昵称
                              if (string.equals("screen_name")) {
                                userName= data.get(string);
                              }
                            }

                                    addcustmer(opid,userName,profile_image_url);
       //     finish();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
           Toast.makeText(LoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
           // Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    //    Toast.makeText(getApplicationContext(), "Authorize succeed1111", Toast.LENGTH_SHORT).show();
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        UMShareAPI.get(this).release();
//    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        UMShareAPI.get(this).onSaveInstanceState(outState);
//    }

}

