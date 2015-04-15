package com.totsp.example;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ccollins on 4/14/15.
 */
public class TouchViewGroup extends ViewGroup {

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d("TV***", "motion event:" + ev);

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void notifySubtreeAccessibilityStateChanged(View child, View source, int changeType) {
        super.notifySubtreeAccessibilityStateChanged(child, source, changeType);

        Log.d("TV***", "view state change:" + child.toString());

    }
}
