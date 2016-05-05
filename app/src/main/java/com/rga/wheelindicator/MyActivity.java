package com.rga.wheelindicator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import wheelindicator.rga.com.wheelindicator.R;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Bitmap blur = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_ring_blur).copy(Bitmap.Config.ARGB_8888, true);
        Bitmap color = BitmapFactory.decodeResource(getResources(), R.drawable.daily_goal_color_meter).copy(Bitmap.Config.ARGB_8888, true);;
        Bitmap result = Bitmap.createBitmap(blur, 0, 0, blur.getHeight(), blur.getWidth());

        /*Canvas canvas = new Canvas(color);
        canvas.drawBitmap(color, 0, 0, null);
        final Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        canvas.drawBitmap(blur, 0, 0, paint);
        result.prepareToDraw();

        ImageView ring = (ImageView) findViewById(R.id.image);
        ring.setImageBitmap(blur);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
