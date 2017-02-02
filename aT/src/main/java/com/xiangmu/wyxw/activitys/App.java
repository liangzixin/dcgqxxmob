package com.xiangmu.wyxw.activitys;

import android.app.Application;
import android.media.MediaPlayer;
import android.util.Config;

//import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2015/11/15.
 */
public class App extends Application {
    public static MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
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

//  Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";

}

