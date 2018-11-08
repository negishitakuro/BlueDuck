package com.t_negi.www.blueduck;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class MyApplication extends Application {
    private static final String CONSUMER_KEY = "pdxBbLGYgBfM57FttZsj5lM6V";
    private static final String CONSUMER_SECRET = "u1v17HdJgufGRYOq9T8zoLHcYKiU4QiLQhVTeq71R2Ni2LiqRO";

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }
}
