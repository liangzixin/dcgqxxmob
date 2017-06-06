package com.xiangmu.lzx.activitys;

import android.app.Application;
import android.media.MediaPlayer;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

//import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2015/11/15.
 */
public class MyApplication extends Application {
    private static final String NAME = "MyApplication";
    public static MediaPlayer mPlayer;
    private  boolean searchDB0;
    @Override
    public void onCreate() {
        super.onCreate();
        setSearchDB0(false);

        Config.DEBUG = true;
        UMShareAPI.get(this);
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

//  Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
{
    //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
    PlatformConfig.setWeixin("wx867555fdf410fafd", "d0e072a549171cfbb22c96b4c58d23cf");
    //豆瓣RENREN平台目前只能在服务器端配置
    //新浪微博
    //  PlatformConfig.setSinaWeibo("3002552310", "e4364ceed9f8383518e2cfd30e43dd9a");
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

