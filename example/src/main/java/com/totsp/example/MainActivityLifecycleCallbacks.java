package com.totsp.example;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.recorder_listener.RecorderListener;

/**
 * Created by ccollins on 4/28/15.
 */
public class MainActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        Log.d(Constants.TAG, "MainActivityLifecycleCallbacks onCreate");

        // inject recorder listener in every activity
        RecorderListener.injectTouchListeners(activity);

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
