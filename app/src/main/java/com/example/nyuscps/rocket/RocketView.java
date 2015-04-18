package com.example.nyuscps.rocket;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class RocketView extends View {
    Paint paint = new Paint();
    private float radius;
    private float x;
    private boolean first = true;


    public RocketView(Context context) {
        super(context);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);	//vs. STROKE
    }

    public RocketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);	//vs. STROKE
    }

    public RocketView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);	//vs. STROKE
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("myTag", "onLayout " + left + " " + top + " " + right + " " + bottom);
        radius = getHeight() / 10f;
        x = radius;
    }

    private class RollTask extends AsyncTask<Void, Float, Void> {

        //This method is executed by the second thread.


        //It gets its arguments from the execute method of GetReadableDatabaseTask.
        //Its return value is passed as an argument to onPostExecute.

        @Override
        protected Void doInBackground(Void... v) {
            Log.d("myTag", "doInBackground, x = " + x);

            while(x < getWidth() - radius) {
                publishProgress(x);
                ++x;
                try {
                    Thread.sleep(40L);   //milliseconds
                } catch (InterruptedException interruptedException) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            invalidate();
        }

        //This method is executed by the UI thread when doInBackground has finished.
        //Its argument is the return value ofDoInBackground.

        @Override
        protected void onPostExecute(Void v) {
            //buildCompleted();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);	//background
        canvas.drawCircle(x, getHeight() - radius, radius, paint);

        if (first) {
            first = false;
            RollTask rollTask = new RollTask();
            rollTask.execute();
        }
    }
}
