package com.example.liluyue.toucheventhandle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String dispatchTouchEvent="dispatchTouchEvent";
    public static final String onTouchEvent="onTouchEvent";
    public static final String onInterceptTouchEvent="onInterceptTouchEvent";
    public static final int HANDLETRUE=1;
    public static final int HANDLEFALSE=2;
    public static final int HANDLESUPER=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1= (Spinner) findViewById(R.id.sp_button1);
        View viewGroup=  findViewById(R.id.vg1);
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.v(ViewGroup2.TAG,"onTouch \t"+getString(motionEvent)+" motionEvent31");
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,dispatchTouchEvent+" "+getString(ev));
        return super.dispatchTouchEvent(ev);

    }

    @Nullable
    public static String getString(MotionEvent ev) {
        String event = null;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                event="ACTION_DOWN";
             break;
            case MotionEvent.ACTION_MOVE:
                event="ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                event="ACTION_UP";
                break;
        }
        return event;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,onTouchEvent+" "+getString(event));
        return super.onTouchEvent(event);
    }
}
