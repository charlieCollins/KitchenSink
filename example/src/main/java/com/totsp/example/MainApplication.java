package com.totsp.example;

import android.app.Application;
import android.util.Log;

/**
 * Created by ccollins on 4/28/15.
 */
public class MainApplication extends Application {

    // TODO add ComponentCallbacks for config changes and update accordingly

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "MainApplication onCreate");
        this.registerActivityLifecycleCallbacks(new MainActivityLifecycleCallbacks());
    }
}
