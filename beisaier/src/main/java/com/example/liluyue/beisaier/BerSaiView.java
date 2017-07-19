package com.example.liluyue.beisaier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by liluyue on 2017/7/19.
 */

public class BerSaiView extends View {

    public static final int RADIUS = 20;
    private Paint bezierPaint;
    private Paint subLinePaint;
    private Paint anchorPaint;
    private Paint controlPaint;
    private Point controlPoint = new Point();
    private Point controlPoint2 = new Point();
    private Point[] anchorPoint = new Point[2];
    private Path bezierPath;
    private Path subPath;
    private Paint controlPaint2;

    public BerSaiView(Context context) {
        super(context);
        init();
    }

    public BerSaiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
private void init(){
    controlPaint = new Paint();
    controlPaint.setAntiAlias(true);
    controlPaint.setDither(true);
    controlPaint.setStyle(Paint.Style.STROKE);
    controlPaint.setStrokeWidth(10);
    anchorPaint = new Paint(controlPaint);
    anchorPaint.setColor(Color.GRAY);
    controlPaint.setColor(Color.BLUE);
    subLinePaint = new Paint(controlPaint);
    subLinePaint.setColor(Color.GREEN);
    bezierPaint = new Paint(controlPaint);
    bezierPaint.setColor(Color.RED);
    controlPaint2 =new Paint(controlPaint);
    controlPaint2.setStrokeWidth(1);
    controlPaint2.setColor(Color.LTGRAY);

}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        anchorPoint[0]=new Point();
        anchorPoint[1]=new Point();
        anchorPoint[0].set(w/4,h/2);
        anchorPoint[1].set(w/4*3,h/2);
        controlPoint.set(w/2,h/4);
        controlPoint2.set(w/2,h/4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(anchorPoint[0].x,anchorPoint[0].y, RADIUS,anchorPaint);
        canvas.drawCircle(anchorPoint[0].x,anchorPoint[0].y, RADIUS,controlPaint2);
        canvas.drawCircle(anchorPoint[1].x,anchorPoint[1].y,RADIUS,anchorPaint);
        canvas.drawCircle(controlPoint.x,controlPoint.y,RADIUS,controlPaint);
        canvas.drawCircle(controlPoint2.x,controlPoint2.y,RADIUS,controlPaint2);
        if (bezierPath==null) {
            bezierPath = new Path();
        }else {
            bezierPath.reset();
        }
        bezierPath.moveTo(anchorPoint[0].x,anchorPoint[0].y);
        bezierPath.cubicTo(controlPoint.x,controlPoint.y,controlPoint2.x,controlPoint2.y,anchorPoint[1].x,anchorPoint[1].y);
        canvas.drawPath(bezierPath,bezierPaint);
        if (subPath==null) {
            subPath =new Path();
        }else {
            subPath.reset();
        }
        subPath.moveTo(anchorPoint[0].x,anchorPoint[0].y);
        subPath.lineTo(controlPoint.x,controlPoint.y);
        subPath.lineTo(controlPoint2.x,controlPoint2.y);
        subPath.lineTo(anchorPoint[1].x,anchorPoint[1].y);
        canvas.drawPath(subPath,subLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int actionIndex = MotionEventCompat.getActionIndex(event);
        switch (MotionEventCompat.getActionMasked(event)){
            case  MotionEvent.ACTION_DOWN:
            case  MotionEvent.ACTION_MOVE:
            case  MotionEvent.ACTION_POINTER_DOWN:
                update(event);
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void update(MotionEvent event) {
        if (event.getPointerCount()>1) {
            controlPoint2.set((int)event.getX(1),(int)event.getY(1));
        }
        controlPoint.set((int)event.getX(),(int)event.getY());
        invalidate();
    }
}
