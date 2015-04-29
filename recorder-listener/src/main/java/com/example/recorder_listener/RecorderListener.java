package com.example.recorder_listener;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by ccollins on 4/28/15.
 */
public class RecorderListener {

    // TODO currently not tracking layouts/viewgroups, doesn't support drag-n-drop etc on groups
    // TODO create a composite listener that keeps track of any EXISTING listeners, and new ones added, etc
    // TODO cleanup added listeners in onPause, re-add them in onCreate
    // TODO profile

    public static void injectTouchListeners(Activity activity) {
        //ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        final ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);

        // view tree observer can tell focus changes and when views are added, etc
        // (and also knows when layout has completed)

        ViewTreeObserver vto = contentView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d(Constants.TAG, "global layout");
                addOnTouchListener(contentView);
            }
        });
    }

    public static void ejectTouchListeners(Activity activity) {
        // TODO every pause should cleanup added composite listeners
    }

    private static View.OnTouchListener createOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("onTouch", "view:" + v.getId() + " event:" + event);
                return false;
            }
        };
    }

    // recursively adds a touch listener to every VIEW (but skips viewgroups)
    private static void addOnTouchListener(View v) {
        Log.d(Constants.TAG, "addOnTouchListener:" + v);
        if (v instanceof ViewGroup) {
            Log.d(Constants.TAG, "found viewgroup:" + v);
            ViewGroup vg = (ViewGroup) v;
            int childCount = vg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View vi = vg.getChildAt(i);
                addOnTouchListener(vi);
            }
        } else {
            // using listener, not handler
            // note that this gets the event FIRST before view.onTouchEvent
            // (not sure what happens yet if there are multiple ontouchlisteners)
            v.setOnTouchListener(createOnTouchListener());
        }
    }

     /*
    public static void injectTouchRecorderView(Activity activity) {
        //Preconditions.checkNotNull(context);
        //Preconditions.checkNotNull(rootView);

        TouchViewGroup touchViewGroup = new TouchViewGroup(activity, null);
        ///int viewId = View.generateViewId();
        ///Log.i(Constants.TAG, "OVERLAY ID:" + viewId);
        ///overlay.setId(viewId);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        touchViewGroup.setLayoutParams(layoutParams);
        touchViewGroup.setAlpha(0);

        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        View childZero = rootView.getChildAt(0);
        rootView.removeView(childZero);
        touchViewGroup.addView(childZero);
        rootView.addView(touchViewGroup, 0);
    }
    */
}
