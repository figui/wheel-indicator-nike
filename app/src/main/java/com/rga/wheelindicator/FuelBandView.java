package com.rga.wheelindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import wheelindicator.rga.com.wheelindicator.R;

class FuelBandView extends View {

    private Bitmap blur;
    private Bitmap color;
    private Typeface font;
    private Integer cap;
    private Integer selectedValue;
    private int count;
    private int increment;

    public FuelBandView(Context context) {
        super(context);
    }

    public FuelBandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blur = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_ring_blur);
        color = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_color_meter);
        font = Typeface.createFromAsset(getResources().getAssets(), "AkzidGrtskProBolCnd.otf");
        count = 0;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FuelbandView, 0, 0);
        cap = a.getInteger(R.styleable.FuelbandView_cap, 3000);
        selectedValue = a.getInteger(R.styleable.FuelbandView_selectedValue, 1780);
        increment = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        checkIncrement();
        count += increment;

        Paint p = new Paint();
        p.setColor(Color.RED);

        Paint numberIndicator = new Paint();
        numberIndicator.setTypeface(font);
        numberIndicator.setTextSize(90);
        numberIndicator.setTextAlign(Paint.Align.CENTER);
        numberIndicator.setColor(Color.WHITE);

        canvas.drawText(String.valueOf(count), (this.getWidth() / 2) , (this.getHeight() / 2), numberIndicator);

        Paint capPaint = new Paint();
        capPaint.setTypeface(font);
        capPaint.setTextSize(50);
        capPaint.setTextAlign(Paint.Align.CENTER);
        capPaint.setColor(0x66ffffff);

        canvas.drawText(String.valueOf(cap), (canvas.getWidth() / 2) , (canvas.getHeight() / 2) + 50, capPaint);

        Shader shader = new BitmapShader(color, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        p.setShader(shader);

        float sweepAngle = count * 350 / cap;

        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        canvas.drawBitmap(blur, 0, 0, p);
        canvas.drawArc(new RectF(canvas.getClipBounds().left, canvas.getClipBounds().top, color.getWidth(), color.getHeight()), 100f, sweepAngle, true, p);

        if(count < selectedValue) {
            invalidate();
        }

    }

    private void checkIncrement() {
        if(count + increment > selectedValue) {
            if(increment / 10 < 10) {
                increment = 1;
            } else {
                increment /=  10;
            }
            checkIncrement();
        }
    }


}
