package com.example.liluyue.wifi_anim;
/**
 * Created by liluyue on 2017/3/9.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;


public class StatelliteAnimView extends View {
    private Context mContext;
    private Paint paint_circle_line;
    private static final String TAG = "StartAnimView";
    public Bitmap satellite1;
    public Bitmap satellite2;
    public Bitmap planet;

    public StatelliteAnimView(Context context) {
        super(context);
        init(context);
    }

    public StatelliteAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint_circle_line = new Paint();
        paint_circle_line.setDither(true);
        paint_circle_line.setAntiAlias(true);
        paint_circle_line.setColor(Color.GRAY);
        paint_circle_line.setStyle(Paint.Style.STROKE);
        paint_circle_line.setStrokeWidth(2);
    }

    int half_width = 0;
    int half_hight = 0;

    public boolean isPause() {
        return pause;
    }

    boolean pause = false;

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        if (!pause) {
            invalidate();
        }
    }

    float radius = 100;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        half_width = w / 2;
        half_hight = h / 2;
    }

    public void setSpeedOfSatellite1(float speed) {
        interpolator1.speed = speed;
    }

    public void setAccelerateOfSatellite1(float accelerate) {
        interpolator1.accelerate = accelerate;
    }

    public void setSpeedOfSatellite2(float speed) {
        interpolator2.speed = speed;
    }
    public void setDegreeOfSatellite1(float degree) {
        interpolator1.base_degree = degree;
    }

    public void setDegreeOfSatellite2(float degree) {
        interpolator2.base_degree = degree;
    }

    public void setAccelerateOfSatellite2(float accelerate) {
        interpolator2.accelerate = accelerate;
    }

    public void setSatellite1(Bitmap satellite1) {
        this.satellite1 = satellite1;
    }

    public void setSatellite2(Bitmap satellite2) {
        this.satellite2 = satellite2;
    }

    public void setPlanet(Bitmap planet) {
        this.planet = planet;
    }

    public void reset() {
        interpolator1.setDegree(interpolator1.base_degree);
        interpolator2.setDegree(interpolator2.base_degree);
        interpolator1.current_speed = interpolator1.speed;
        interpolator2.current_speed = interpolator2.speed;
        pause = false;
        invalidate();
    }

    public void setRadiusOfPathWay(int radius) {
        this.radius = radius;
    }

    SpeedInterpolator interpolator1 = new SpeedInterpolator(0, 1);
    SpeedInterpolator interpolator2 = new SpeedInterpolator(0, 0);

    public class SpeedInterpolator implements Interpolator {
        public SpeedInterpolator(int speed, int accelerate) {
            this.speed = speed;
            this.accelerate = accelerate;
        }

        public float getDegree() {
            return degree;
        }

        float degree;
        float base_degree;
        float speed = 1;
        float current_speed = speed;
        float accelerate = 4;

        public void setBaseDegree(float degree) {
            this.base_degree = degree;
        }


        @Override
        public float getInterpolation(float v) {
            if (v <= 180) {
                current_speed += accelerate;
                v += current_speed;
            } else {
                current_speed = current_speed > speed ? current_speed -= accelerate : speed;
                v += current_speed;
            }
            if (v >= 360) {
                v = 0;
                current_speed = speed;
            }
            degree = v;
            return v;
        }

        public void setDegree(float degree) {
            this.degree = degree;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (satellite1 == null) {
            satellite1 = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
            initSatellite(satellite1);
        }
        if (satellite2 == null) {
            satellite2 = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
            initSatellite(satellite2);
        }
        if (planet == null) {
            planet = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
            initSatellite(planet);
        }
        canvas.drawCircle(half_width, half_hight, radius, paint_circle_line);
        canvas.drawBitmap(planet, half_width - planet.getWidth() / 2, half_hight - planet.getHeight() / 2, null);
        drawSatellite(canvas, interpolator1, satellite1);
        drawSatellite(canvas, interpolator2, satellite2);
        if (pause) {
            return;
        }
        postInvalidateDelayed(166);

    }

    private void drawSatellite(Canvas canvas, SpeedInterpolator speedInterpolator, Bitmap bitmap) {
        canvas.save();
        canvas.translate(half_width, half_hight);
        canvas.rotate(speedInterpolator.getInterpolation(speedInterpolator.getDegree()));
        canvas.drawBitmap(bitmap, -bitmap.getWidth() / 2, -bitmap.getHeight() / 2 - radius, null);
        canvas.restore();
    }

    private void initSatellite(Bitmap bitmap) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
        }
        Paint paint;
        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(20, 20, 20, paint);
    }


}

