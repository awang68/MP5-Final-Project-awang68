package com.example.mp5finalproject;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Brush tool function.
 */
public class Brush extends View {
    private Path path = new Path();
    private Canvas canvas = new Canvas();
    private Paint brush = new Paint();
    private int currentColor;


    public Brush(Context context) {
        super(context, null);
    }

    public Brush(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush.setAntiAlias(true);
        brush.setStrokeWidth(10);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setColor(Color.BLACK);
    }

    @Override
    public boolean onTouchEvent(MotionEvent press) {
        float x = press.getX();
        float y = press.getY();

        switch (press.getAction()) {
            case MotionEvent.ACTION_DOWN: path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE: path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP: canvas.drawPath(path, brush);
                break;
            default: return false;
        }
        invalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, brush);
    }

    public void setColor(int color) {
        brush.setColor(color);
    }

    public Path getPath() {
        return path;
    }

    public void setSize(int size) {
        brush.setStrokeWidth(size);
    }
}
