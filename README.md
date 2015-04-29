#KitchenSink Android (experimental)
-----------------------------------------

Prototype concepts for automatically capturing View and MotionEvent data on Android (and playing it back).

## Goals
* Android library that is easy to incorporate and does not require manually instrumenting existing code
* Capture of view and touch data (with that relationship) in a compact format
* Sync of captured data in an offline capable, batched, network and battery friendly fashion
* Capability to playback captured data on same app

## Example Capture
Currently the `example` app here uses the `recorder-listener` library by including the `ActivityLifecycleCallbacks` that
inject view listeners on every view (not viewgroups, and not using handlers so that we are in front of those and pass events on).

## Example Playback
The `example` androidTest package includes an

## Investigations
* AccessibilityService: works well but must be enabled per device (not feasible)
* GestureOverlay: works and is unobtrusive but requires layout redraw and difficult to capture correlation of views to events
* View Listeners: works and *seems* to perform well, and captures view to event relationship

## TODO
* Composite listeners and cleanup (so that we don't clobber any existing listeners)
* ViewTreeObserver for runtime view changes
* Performance profiling
* Playback overlay to show touch events?

##Example log output
`04-28 11:49:45.637  26520-26520/com.totsp.example D/example﹕ MainApplication onCreate
04-28 11:49:45.637  26520-26520/com.totsp.example D/example﹕ MainActivityLifecycleCallbacks onCreate
...
04-28 11:49:45.757  26520-26520/com.totsp.example D/recorder-listener﹕ global layout
04-28 11:49:45.757  26520-26520/com.totsp.example D/recorder-listener﹕ addOnTouchListener:android.widget.FrameLayout{41c74320 V.E..... ......ID 0,75-1080,1776}
...
04-28 11:49:45.757  26520-26520/com.totsp.example D/recorder-listener﹕ addOnTouchListener:android.widget.TextView{41ca98a8 V.ED.... ......ID 402,59-582,118 #7f090042 app:id/textView3}
04-28 11:49:45.757  26520-26520/com.totsp.example D/recorder-listener﹕ addOnTouchListener:android.widget.TextView{41ca9cd0 V.ED.... ......ID 402,118-582,177 #7f090043 app:id/textView4}
...
04-28 11:51:28.677  26520-26520/com.totsp.example D/onTouch﹕ view:2131296324 event:MotionEvent { action=ACTION_DOWN, id[0]=0, x[0]=168.79926, y[0]=66.83496, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=61521717, downTime=61521717, deviceId=5, source=0x1002 }
04-28 11:51:28.707  26520-26520/com.totsp.example D/onTouch﹕ view:2131296324 event:MotionEvent { action=ACTION_UP, id[0]=0, x[0]=168.79926, y[0]=66.83496, toolType[0]=TOOL_TYPE_FINGER, buttonState=0, metaState=0, flags=0x0, edgeFlags=0x0, pointerCount=1, historySize=0, eventTime=61521745, downTime=61521717, deviceId=5, source=0x1002 }
`


