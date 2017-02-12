package com.xiangmu.lzx.activitys;

import android.app.Application;
import android.media.MediaPlayer;

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

}

