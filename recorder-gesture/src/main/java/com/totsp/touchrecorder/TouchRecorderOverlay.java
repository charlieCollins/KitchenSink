package com.totsp.touchrecorder;

import android.app.Activity;
import android.content.Context;
import android.gesture.GestureOverlayView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class TouchRecorderOverlay extends GestureOverlayView {

    // NOTE this works, but doesn't capture the view ids, just all MotionEvents
    // would have to track view bounds and check which events fall in bounds separately

    // TODO measure performance of overlay
    // TODO validate that multiple gesture overlays work fine together
    // TODO viewtreeobserver in case views are added dynamically (and reset observer)

    /*
    D/CaptureTouch( 1247): me:MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=304.0, y[0]=829.0, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=1932003, downTime=1932003, deviceId=0, source=0x1002 }
    D/CaptureTouch( 1247): me:MotionEvent { action=ACTION_UP, id[0]=0, x[0]=304.0, y[0]=829.0, toolType[0]=TOOL_TYPE_FINGER, buttonState=0,
 metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=1932085, downTime=1932003, deviceId=0, source=0x1002 }

     */

    public static void injectTouchRecorderView(Context context, ViewGroup rootView) {
        //Preconditions.checkNotNull(context);
        //Preconditions.checkNotNull(rootView);

        TouchRecorderOverlay overlay = new TouchRecorderOverlay(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        overlay.setLayoutParams(layoutParams);

        View childZero = rootView.getChildAt(0);

        rootView.removeView(childZero);
        overlay.addView(childZero);
        rootView.addView(overlay, 0);
    }
    
    public static void injectTouchRecorderViewAsRoot(Activity activity) {
        //final ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        //decorview includes action bar -- see hierarchyviewer
        final ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        injectTouchRecorderView(activity, viewGroup);
    }
    
    public TouchRecorderOverlay(Context context) {
        super(context);
        this.setGestureVisible(false);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(Constants.TAG, "me:" + event.toString());
        return super.dispatchTouchEvent(event);
    }
}
