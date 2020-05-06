package com.example.mp5finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Creates the canvas drawing area and tool functionality.
 */
public class Brush extends View {
    private ArrayList<MainBrush> paths = new ArrayList<>();
    private Path path;
    private Canvas canva;
    private Paint brush;
    private Paint paintscreen = new Paint(Paint.DITHER_FLAG);
    private int brushsize;
    private Bitmap bitmap;
    private int currentcolor;
    private float setx;
    private float sety;

    public void setPaths(ArrayList<MainBrush> setPaths) {
        paths = setPaths;
    }

    public Brush(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush = new Paint();
        brush.setAntiAlias(true);
        brush.setStrokeWidth(15);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setColor(Color.BLACK);
    }

    public void create(DisplayMetrics display) {
        int width = display.widthPixels;
        int height = display.heightPixels;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canva = new Canvas(bitmap);
        currentcolor = Color.BLACK;
        brushsize = 15;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canva = new Canvas(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
    }

    private void start(float x, float y) {
        path = new Path();
        MainBrush draw = new MainBrush(currentcolor, brushsize, path);
        paths.add(draw);
        path.reset();
        path.moveTo(x, y);
        setx = x;
        sety = y;

    }

    private void move(float x, float y) {
        float getx = Math.abs(x - setx);
        float gety = Math.abs(y - sety);
        if (getx >= 5 || gety >= 5) {
            path.quadTo(setx, sety, (x + setx) / 2, (y + sety) / 2);
            setx = x;
            sety = y;
        }
    }

    private void upmove(float x, float y) {
        path.lineTo(setx, sety);
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
            case MotionEvent.ACTION_DOWN: start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP: upmove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE: move(x, y);
                invalidate();
                break;
        }
        return true;
    }

    public int getPathSize() {
        return paths.size();
    }

    public ArrayList<MainBrush> setPaths() {
        return paths;
    }

    public void brushtypeTool() {
        sizeTool();
        invalidate();
    }

    public void colorTool(int r, int g, int b) {
        brush.setColor(Color.rgb(r, g, b));
        currentcolor = Color.rgb(r, g, b);
        invalidate();
    }

    public void setBrushSize(int size) {
        brushsize = size;
    }

    public void sizeTool() {
        brush.setStrokeWidth(brushsize);
        invalidate();
    }
    public void eraserTool() {
        brush.setColor(Color.WHITE);
        invalidate();
    }

    public void clearTool() {
        paths.clear();
        invalidate();
    }

    /**
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        for (MainBrush paint : paths) {
            brush.setColor(paint.color);
            brush.setStrokeWidth(paint.width);
            brush.setMaskFilter(null);
            canvas.drawPath(paint.setpath, brush);
        }
        canvas.drawBitmap(bitmap, 0, 0, paintscreen);
        canvas.restore();
    }
}
