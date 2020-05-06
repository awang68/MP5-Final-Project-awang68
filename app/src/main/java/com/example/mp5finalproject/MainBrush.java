package com.example.mp5finalproject;

import android.graphics.Path;

/**
 * The settings of the brush tool.
 */
public class MainBrush {
    int color;
    int width;
    Path setpath;

    /**
     * Settings are configured by the Brush class for each path created.
     * @param color the current color.
     * @param width the current stroke width.
     * @param path the current path.
     */
    MainBrush(int color, int width, Path path) {
        this.color = color;
        this.width = width;
        this.setpath = path;
    }
}
