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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.container, new PlaceholderFragment())
                                       .commit();
        }

        Log.d(Constants.TAG, "MainActivity onCreate");
    }


    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    // touch notes
    // activity, view, viewgroup
    // activity dispatches motion events to child views, in reverse order of hierarchy
    // if no views handle the event (either with listener, setOnTouchListener, or with handler, onTouchEvent) it then goes to Activity.onTouchEvent

    // ON THE WAY DOWN DISPATCHED HERE
    // goes to each view
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ///Log.d("MA***", "dispatch touch event:" + ev);
        return super.dispatchTouchEvent(ev);
    }

    // ON THE WAY UP CONSUMED/USED HERE (usually in views)
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
