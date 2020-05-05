package com.example.mp5finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Creates the canvas drawing area and tool functionality.
 */
public class Brush extends View {
    private Path path = new Path();
    private Canvas canva = new Canvas();
    private Paint brush = new Paint();
    private Paint paintcanva = new Paint();
    private int brushsize;
    private Bitmap bitmap;
    private int currentColor;


    public Brush(Context context) {
        super(context, null);
    }

    public Brush(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush.setAntiAlias(true);
        brush.setStrokeWidth(8);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setColor(Color.BLACK);
    }

    /**
     *
     * @param press
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent press) {
        float x = press.getX();
        float y = press.getY();

        switch (press.getAction()) {
            case MotionEvent.ACTION_DOWN: path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE: path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP: canva.drawPath(path, brush);
                break;
            default: return false;
        }
        invalidate();
        return false;
    }

    public void brushcolorTool() {
        brush.setColor(Color.BLACK);
    }

    public void colorTool() {
        brush.setColor(Color.BLUE);
    }

    public void sizeTool() {
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(brushsize);
    }
    public void eraserTool() {
        brush.setColor(Color.WHITE);
    }

    public void clearTool() {
        path.reset();
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, paintcanva);
        canvas.drawPath(path, brush);
    }
}
