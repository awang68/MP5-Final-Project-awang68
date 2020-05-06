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

    /**
     * Sets up the main brush tool settings.
     * @param context context of the current application state.
     * @param attrs Attributes for the custom view.
     */
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

    /**
     * Sets up the dimensions of the canvas based on the screen layout.
     * @param display Provides the general display screen information.
     */
    public void create(DisplayMetrics display) {
        int width = display.widthPixels;
        int height = display.heightPixels;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canva = new Canvas(bitmap);
        currentcolor = Color.BLACK;
        brushsize = 15;
    }

    /**
     * Updates the view layout size when it is changed.
     * @param w the current width of the View.
     * @param h the current height of the View.
     * @param oldw the old width of the View.
     * @param oldh the old height of the View.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canva = new Canvas(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
    }

    /**
     * Starts path creation from the initial starting position.
     * Applies the current brush settings to the path.
     * @param x the current x position.
     * @param y the current y position.
     */
    private void start(float x, float y) {
        path = new Path();
        MainBrush draw = new MainBrush(currentcolor, brushsize, path);
        paths.add(draw);
        path.reset();
        path.moveTo(x, y);
        setx = x;
        sety = y;

    }

    /**
     * Creates paths while the position is constantly updated by motion movement.
     * @param x the current x position.
     * @param y the current y position.
     */
    private void move(float x, float y) {
        float getx = Math.abs(x - setx);
        float gety = Math.abs(y - sety);
        if (getx >= 5 || gety >= 5) {
            path.quadTo(setx, sety, (x + setx) / 2, (y + sety) / 2);
            setx = x;
            sety = y;
        }
    }

    /**
     * Creates a path to the last updated position.
     * @param x the current x position.
     * @param y the current y position.
     */
    private void upmove(float x, float y) {
        path.lineTo(setx, sety);
    }

    /**
     * Monitors the behavior of the brush movement by handling touch screen motion events.
     * @param press Reports movements made (from either a mouse controller or a finger motion).
     * @return returns true if the event was handled.
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

    /**
     * Gets the current Paths arraylist size.
     * @return
     */
    public int getPathSize() {
        return paths.size();
    }

    /**
     * Sets the Paths arraylist to the selected LoadState canvas file Paths arraylist.
     * @param setPaths Paths arraylist from a saved canvas.
     */
    public void setPaths(ArrayList<MainBrush> setPaths) {
        paths = setPaths;
    }

    /**
     * Gets the current Paths arraylist.
     * @return
     */
    public ArrayList<MainBrush> getPaths() {
        return paths;
    }

    /**
     * Sets the brush size according to "Brush" or "Bucket" when the Brush Button is toggled.
     */
    public void brushtypeTool() {
        sizeTool();
        invalidate();
    }

    /**
     * Sets the brush color using RGB values.
     * @param r red RGB value.
     * @param g green RGB value.
     * @param b blue RGB value.
     */
    public void colorTool(int r, int g, int b) {
        brush.setColor(Color.rgb(r, g, b));
        currentcolor = Color.rgb(r, g, b);
        invalidate();
    }

    /**
     * Sets the brush size using a size value obtained from ArtCanvas class.
     * @param size
     */
    public void setBrushSize(int size) {
        brushsize = size;
    }

    /**
     * Sets the brush size.
     */
    public void sizeTool() {
        brush.setStrokeWidth(brushsize);
        invalidate();
    }

    /**
     * Turns the brush into an eraser by setting the brush color to white.
     */
    public void eraserTool() {
        if (currentcolor == Color.WHITE) {
            currentcolor = Color.BLACK;
        } else {
            currentcolor = Color.WHITE;
        }
        invalidate();
    }

    /**
     * Clears the entire canvas by clearing the Paths arraylist.
     */
    public void clearTool() {
        paths.clear();
        invalidate();
    }

    /**
     * Implements the drawing functionality with support from the MainBrush class.
     * @param canvas Settings of the current canvas.
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
