package com.totsp.example;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;


@LargeTest
 public class HelloWorldEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {


    // example Button press MotionEvent
    /*

    D/onTouch ( 4810): view:2131296324 event:MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=89.0, y[0]=59.0, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=42080773, downTime=42080773, deviceId=0, source=0x1002 }
D/onTouch ( 4810): view:2131296324 event:MotionEvent { action=ACTION_UP, id[0]=0, x[0]=89.0, y[0]=59.0, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=42080933, downTime=42080773, deviceId=0, source=0x1002 }

     */


    public HelloWorldEspressoTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getActivity();
    }

    public void testHelloWorldFound() {
        Espresso.onView(ViewMatchers.withText("Hello world!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public void testMotionEventStuff() {
        ViewInteraction vi = Espresso.onView(ViewMatchers.withId(2131296324));
        Log.d("test", "view interaction:" + vi);
        vi.perform(ViewActions.click());
        SystemClock.sleep(1000);
        vi.perform(ViewActions.click());
        SystemClock.sleep(1000);
        vi.perform(ViewActions.click());
        SystemClock.sleep(1000);
        vi.perform(ViewActions.click());

                //.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }


}



