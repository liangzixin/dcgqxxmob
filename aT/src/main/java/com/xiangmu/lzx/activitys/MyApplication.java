package com.xiangmu.lzx.activitys;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.multidex.MultiDexApplication;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiangmu.lzx.listener.GlideImageLoader;
import com.xiangmu.lzx.listener.GlidePauseOnScrollListener;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

//import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2015/11/15.
 */
public class MyApplication extends MultiDexApplication {
    private static final String NAME = "MyApplication";
    public static MediaPlayer mPlayer;
    private  boolean searchDB0=false;
    private static Context ctx;
    @Override
    public void onCreate() {
        super.onCreate();
       // setSearchDB0(false);
        ctx = getApplicationContext();
        Config.DEBUG = true;
        UMShareAPI.get(this);
        ThemeConfig theme = new ThemeConfig.Builder().build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)  // 设置相机
                .setEnableEdit(false)  // 设置是否可编辑
                .setEnableCrop(true)  // 设置是否可裁剪
                .setEnableRotate(true) // 设置是否可旋转
                .setCropSquare(true)
                .setEnablePreview(true)  // 设置是否可预览
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new GlideImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new GlidePauseOnScrollListener(false, true))
                .setNoAnimcation(true)  // 设置是否显示动画
                .build();
        GalleryFinal.init(coreConfig);
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

//  Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
{
    //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
 // PlatformConfig.setWeixin("wxd2069cc9808ef318", "5ae42904c7ca2bad8f5ffdb70ad098f0");
    PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
    //微信AppSecret：5ae42904c7ca2bad8f5ffdb70ad098f0
    //微信应用签名：a6f7664f6969ed22d7c3abe9c6c51cf7
    //   PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
    //豆瓣RENREN平台目前只能在服务器端配置
    //新浪微博
  //   PlatformConfig.setSinaWeibo("3365617265", "ecfbad353322b9101a18036eaa1a40193");
    //PlatformConfig.setSinaWeibo("3002552310", "a6f7664f6969ed22d7c3abe9c6c51cf7","http://sns.whalecloud.com/sina2/callback");
    PlatformConfig.setSinaWeibo("3002552310", "e4364ceed9f8383518e2cfd30e43dd9a","http://sns.whalecloud.com/sina2/callback");
    //易信
    PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
    PlatformConfig.setQQZone("1105789263", "Z72WYfitxSU5HyVO");
//        PlatformConfig.setQQZone("1105898466", "X0wJFplWXPjV0tOQ");
    PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
    PlatformConfig.setAlipay("2015111700822536");
    PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
    PlatformConfig.setPinterest("1439206");
    PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
    PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
    PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
    PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");


}

}

