package com.example.liluyue.toucheventhandle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by liluyue on 2017/2/18.
 */

public class Button1 extends View {
    private static final String TAG = "Button1";
    public Button1(Context context) {
        super(context);
    }

    public Button1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,MainActivity.dispatchTouchEvent+" "+MainActivity.getString(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,MainActivity.onTouchEvent+" "+MainActivity.getString(event));
        return super.onTouchEvent(event)||true;
    }

}
