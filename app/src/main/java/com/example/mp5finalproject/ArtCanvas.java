package com.example.mp5finalproject;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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
    private TextView sizeText;
    private int sizeValue = 15;
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

        /**
         * Changes the brush size using a seek bar (size 1 - 120).
         */
        sizeText = findViewById(R.id.sizeTxt);
        sizeBar = findViewById(R.id.sizeBar);
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sizeValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                brushTool.setBrushSize(sizeValue);
                brushTool.sizeTool();
                sizeText.setText(String.valueOf(sizeValue));
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                brushTool.setBrushSize(sizeValue);
                brushTool.sizeTool();
                sizeText.setText(String.valueOf(sizeValue));
            }
        });

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
                if (sizeBar.getVisibility() == View.GONE && sizeText.getVisibility() == View.GONE) {
                    sizeBar.setVisibility(View.VISIBLE);
                    sizeText.setVisibility(View.VISIBLE);
                } else if (sizeBar.getVisibility() == View.VISIBLE && sizeText.getVisibility() == View.VISIBLE) {
                    sizeBar.setVisibility(View.GONE);
                    sizeText.setVisibility(View.GONE);
                }
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
