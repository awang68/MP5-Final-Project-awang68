package com.example.mp5finalproject;

import android.graphics.Path;

/**
 * The settings of the brush tool.
 */
public class MainBrush {
    public int color;
    public int width;
    public Path setpath;

    MainBrush(int color, int width, Path path) {
        this.color = color;
        this.width = width;
        this.setpath = path;
    }
}