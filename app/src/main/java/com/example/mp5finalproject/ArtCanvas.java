package com.example.mp5finalproject;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Main art project area where drawings are created.
 * This will have some art tools, mainly brush, erase, color, size tools.
 */
public class ArtCanvas extends AppCompatActivity {
    private Button brushButton;
    private Button colorButton;
    private Button sizeButton;
    private Button eraserButton;
    private Button clearButton;
    private SeekBar sizeBar;
    private Brush brushTool;
    private Path path = new Path();
    private Paint brush = new Paint();

    /**
     * Creates the tool buttons within the canvas UI.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_canvas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        brushTool = findViewById(R.id.canvasArea);

        sizeBar = findViewById(R.id.sizeBar);

        brushButton = findViewById(R.id.brushBtn);
        brushButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.brushcolorTool();
            }
        }));

        colorButton = findViewById(R.id.colorBtn);
        colorButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.colorTool();
            }
        }));

        sizeButton = findViewById(R.id.sizeBtn);
        sizeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeBar.getVisibility() == View.GONE) {
                    sizeBar.setVisibility(View.VISIBLE);
                } else if (sizeBar.getVisibility() == View.VISIBLE) {
                    sizeBar.setVisibility(View.GONE);
                }
                brushTool.sizeTool();
            }
        }));

        eraserButton = findViewById(R.id.eraserBtn);
        eraserButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.eraserTool();
            }
        }));

        clearButton = findViewById(R.id.clearBtn);
        clearButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.clearTool();
            }
        }));
    }

}
