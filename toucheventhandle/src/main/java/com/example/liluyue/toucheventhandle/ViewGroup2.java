package com.example.liluyue.toucheventhandle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by liluyue on 2017/2/18.
 */

public class ViewGroup2 extends RelativeLayout{
    public static final String TAG = "ViewGroup2";
    public ViewGroup2(Context context) {
        super(context);
    }

    public ViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG,MainActivity.dispatchTouchEvent+" "+MainActivity.getString(ev)+"\n");
        boolean b =true;
        b= super.dispatchTouchEvent(ev);
        if (i>3){
            getChildAt(0).dispatchTouchEvent(ev);
        }
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG,MainActivity.onTouchEvent+" "+MainActivity.getString(event));
        return true||super.onTouchEvent(event);
    }
   public int i=0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_MOVE){
            i++;
        }
        Log.v(TAG,MainActivity.onInterceptTouchEvent+" "+MainActivity.getString(ev));
        return super.onInterceptTouchEvent(ev)||i>3;
    }

}
