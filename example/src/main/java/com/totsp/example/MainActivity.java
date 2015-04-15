package com.totsp.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

//import com.totsp.touchrecorder.TouchRecorderOverlay;

//TouchDelegate?

public class MainActivity extends ActionBarActivity {

    // TODO reset after config changes
    // TODO currently not tracking layouts/viewgroups, doesn't support drag-n-drop etc on groups

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
    private void addOnTouchListener(View v) {

        Log.d("TAG***", "addOnTouchListener:" + v);

        if (v instanceof ViewGroup) {
            Log.d("TAG***", "found viewgroup:" + v);
            ViewGroup vg = (ViewGroup) v;
            int childCount = vg.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View vi = vg.getChildAt(i);
                addOnTouchListener(vi);
            }
        } else {
            v.setOnTouchListener(createOnTouchListener());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.container, new PlaceholderFragment())
                                       .commit();
        }

        // inject touch recorder
        //injectTouchRecorderView(this);

        Log.d("TAG ************", "MainActivity onCreate");


        //ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        final ViewGroup contentView = (ViewGroup) this.findViewById(android.R.id.content);

        /*
        // easiest way to get VIEW and events is OnTouchListener
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){

                Log.d("onTouch", "view:" + v.getId() + " event:" + event);

                return false;
            }
        });
        */


        // view tree observer can tell focus changes and when views are added, etc

        ViewTreeObserver vto = contentView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("vto layout******", "global layout");
                addOnTouchListener(contentView);
            }
        });
        /*
        vto.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Log.d("GLOBAL focus change******", "old:" + oldFocus.getId() + " new:" + newFocus.getId());
            }
        });
        vto.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                Log.d("WINDOW focus change******", "" + hasFocus);
            }
        });
        */
    }


    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    // ON THE WAY DOWN CONSUMED HERE
    // goes to each view
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        ///Log.d("MA***", "dispatch touch event:" + ev);

        return super.dispatchTouchEvent(ev);
    }

    // ON THE WAY BACK UP, USUALLY NOT USED
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        ///Log.d("MA***", "on touch event:" + ev);

        return super.onTouchEvent(ev);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
