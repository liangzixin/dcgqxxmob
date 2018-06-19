package com.xiangmu.lzx.activitys;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

//import com.umeng.commonsdk.UMConfigure;
//import com.umeng.message.IUmengRegisterCallback;
//import com.umeng.message.MsgConstant;
//import com.umeng.message.PushAgent;
//import com.umeng.message.UTrack;
//import com.umeng.message.UmengMessageHandler;
//import com.umeng.message.UmengNotificationClickHandler;
//import com.umeng.message.entity.UMessage;
//import com.umeng.socialize.Config;
////import com.umeng.socialize.view.Config;
//import com.umeng.socialize.PlatformConfig;
//import com.umeng.socialize.UMShareAPI;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.listener.GlideImageLoader;
import com.xiangmu.lzx.listener.GlidePauseOnScrollListener;
import com.xiangmu.lzx.utils.ConstantsLzx;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

//import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2015/11/15.
 */
//public class MyApplication extends MultiDexApplication {
    public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getName();
    private static final String NAME = "MyApplication";
   // public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    public static MediaPlayer mPlayer;
    private  boolean searchDB0=false;
    private static Context ctx;
    private Handler handler;
    // QQ申请到的合法appId
    public  final String APP_ID="1105789263";
    public static Tencent mTencent;
    private IWXAPI api;
    @Override
    public void onCreate() {
        super.onCreate();
       // setSearchDB0(false);
        ctx = getApplicationContext();
        WbSdk.install(this,new AuthInfo(this, ConstantsLzx.APP_KEY, ConstantsLzx.REDIRECT_URL, ConstantsLzx.SCOPE));
        if (mTencent == null) {
            mTencent = Tencent.createInstance(APP_ID, this);
        }
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, ConstantsLzx.APP_ID, true);
        // 将该app注册到微信
        api.registerApp(ConstantsLzx.APP_ID);
      //  Config.DEBUG = true;
//        UMConfigure.setLogEnabled(true);
//        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
//        UMConfigure.init(this, "59892f08310c9307b60023d0", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
//                "669c30a9584623e70e8cd01b0381dcb4");
        //PushSDK初始化(如使用推送SDK，必须调用此方法)
   //     initUpush();
//   UMShareAPI.get(this);
//        ThemeConfig theme = new ThemeConfig.Builder().build();
//        //配置功能
//        FunctionConfig functionConfig = new FunctionConfig.Builder()
//                .setEnableCamera(true)  // 设置相机
//                .setEnableEdit(false)  // 设置是否可编辑
//                .setEnableCrop(true)  // 设置是否可裁剪
//                .setEnableRotate(true) // 设置是否可旋转
//                .setCropSquare(true)
//                .setEnablePreview(true)  // 设置是否可预览
//                .build();
//        CoreConfig coreConfig = new CoreConfig.Builder(this, new GlideImageLoader(), theme)
//                .setFunctionConfig(functionConfig)
//                .setPauseOnScrollListener(new GlidePauseOnScrollListener(false, true))
//                .setNoAnimcation(true)  // 设置是否显示动画
//                .build();
//        GalleryFinal.init(coreConfig);

//        try {
//            api.handleIntent(getIntent(), this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        x.Ext.init(this);
        //设置是否输出Debug
        x.Ext.setDebug(true);
    }


    public static MediaPlayer getMediaPlayer() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        return mPlayer;
    }

    public static void setMediaPlayerNull() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public boolean isSearchDB0() {
        return searchDB0;
    }

    public void setSearchDB0(boolean searchDB0) {
        this.searchDB0 = searchDB0;
    }
    public static Context getCtx() {
        return ctx;
    }
//    private void initUpush() {
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        handler = new Handler(getMainLooper());
//
//        //sdk开启通知声音
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
//        // sdk关闭通知声音
//        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//        // 通知声音由服务端控制
//        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);
//
//        // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//        // mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//
//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//
//            /**
//             * 通知的回调方法（通知送达时会回调）
//             */
//            @Override
//            public void dealWithNotificationMessage(Context context, UMessage msg) {
//                //调用super，会展示通知，不调用super，则不展示通知。
//                super.dealWithNotificationMessage(context, msg);
//            }
//
//            /**
//             * 自定义消息的回调方法
//             */
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//
//                handler.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        // 对自定义消息的处理方式，点击或者忽略
//                        boolean isClickOrDismissed = true;
//                        if (isClickOrDismissed) {
//                            //自定义消息的点击统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
//                            //自定义消息的忽略统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            /**
//             * 自定义通知栏样式的回调方法
//             */
//            @Override
//            public Notification getNotification(Context context, UMessage msg) {
//                switch (msg.builder_id) {
//                    case 1:
//                        Notification.Builder builder = new Notification.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
//                                R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
//                                getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView)
//                                .setSmallIcon(getSmallIconId(context, msg))
//                                .setTicker(msg.ticker)
//                                .setAutoCancel(true);
//
//                        return builder.getNotification();
//                    default:
//                        //默认为0，若填写的builder_id并不存在，也使用默认。
//                        return super.getNotification(context, msg);
//                }
//            }
//        };
//        mPushAgent.setMessageHandler(messageHandler);
//
//        /**
//         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
//         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
//         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
//         * */
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//
//            @Override
//            public void launchApp(Context context, UMessage msg) {
//                super.launchApp(context, msg);
//            }
//
//            @Override
//            public void openUrl(Context context, UMessage msg) {
//                super.openUrl(context, msg);
//            }
//
//            @Override
//            public void openActivity(Context context, UMessage msg) {
//                super.openActivity(context, msg);
//            }
//
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//            }
//        };
//        //使用自定义的NotificationHandler
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//
//        //注册推送服务 每次调用register都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                Log.i(TAG, "device token: " + deviceToken);
//                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.i(TAG, "register failed: " + s + " " + s1);
//                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
//            }
//        });
//
//        //使用完全自定义处理
//        //mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);
//
//        //小米通道
//        //MiPushRegistar.register(this, XIAOMI_ID, XIAOMI_KEY);
//        //华为通道
//        //HuaWeiRegister.register(this);
//        //魅族通道
//        //MeizuRegister.register(this, MEIZU_APPID, MEIZU_APPKEY);
//    }

//  Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
{
//    //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
//PlatformConfig.setWeixin("wxd2069cc9808ef318", "a6f7664f6969ed22d7c3abe9c6c51cf7");
//    //   PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//    //微信AppSecret：5ae42904c7ca2bad8f5ffdb70ad098f0
//    //微信应用签名：a6f7664f6969ed22d7c3abe9c6c51cf7
//    //   PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//    //豆瓣RENREN平台目前只能在服务器端配置
//    //新浪微博
//  //   PlatformConfig.setSinaWeibo("3365617265", "ecfbad353322b9101a18036eaa1a40193");
//    //PlatformConfig.setSinaWeibo("3002552310", "a6f7664f6969ed22d7c3abe9c6c51cf7","http://sns.whalecloud.com/sina2/callback");
//   PlatformConfig.setSinaWeibo("3002552310", "e4364ceed9f8383518e2cfd30e43dd9a","http://sns.whalecloud.com/sina2/callback");
//    //   PlatformConfig.setSinaWeibo("3002552310", "167ed7a9a1eb7955bbc776c38afc33d6","http://sns.whalecloud.com");
//    //易信
//    PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//    PlatformConfig.setQQZone("1105789263", "Z72WYfitxSU5HyVO");
////        PlatformConfig.setQQZone("1105898466", "X0wJFplWXPjV0tOQ");
//    PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//    PlatformConfig.setAlipay("2015111700822536");
//    PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//    PlatformConfig.setPinterest("1439206");
//    PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
//    PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
//    PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
//    PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");


}

}

