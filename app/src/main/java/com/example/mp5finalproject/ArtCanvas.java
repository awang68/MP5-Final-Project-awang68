package com.example.mp5finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Main art project area where drawings are created.
 * This will have some art tools, mainly brush, erase, color, size tools.
 */
public class ArtCanvas extends AppCompatActivity {
    /**
     * Buttons displayed on the canvas screen UI.
     */
    private Button brushButton;
    private Button colorButton;
    private Button sizeButton;
    private Button eraserButton;
    private Button clearButton;
    private Button backButton;

    /**
     * Seek bars displayed on the canvas screen UI, normally invisible.
     */
    private SeekBar sizeBar;
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;

    /**
     * Text boxes displayed along with the seek bars to indicate their corresponding values.
     */
    private TextView sizeText;
    private TextView redText;
    private TextView greenText;
    private TextView blueText;

    /**
     * Default brush size value;
     */
    private int sizeValue = 15;

    /**
     * Default RGB values.
     */
    private int redValue = 255;
    private int greenValue = 255;
    private int blueValue = 255;

    private Brush brushTool;
    private LoadState loadState;
    private int pathSize = 0;

    /**
     * Creates the tool buttons within the canvas UI.
     * @param savedInstanceState contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_canvas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        // Setups the canvas drawing area.
        brushTool = findViewById(R.id.canvasArea);
        brushTool.create(display);

        /**
         * Controls the red RGB value using a seek bar.
         * Updates the current color after a value is selected.
         */
        redBar = findViewById(R.id.redBar);
        redText = findViewById(R.id.redTxt);
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                redText.setText("R:" + redValue);
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                redText.setText("R:" + redValue);
                brushTool.colorTool(redValue, greenValue, blueValue);
            }
        });

        /**
         * Controls the green RGB value using a seek bar.
         * Updates the current color after a value is selected.
         */
        greenBar = findViewById(R.id.greenBar);
        greenText = findViewById(R.id.greenTxt);
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                greenText.setText("G:" + greenValue);
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                greenText.setText("G:" + greenValue);
                brushTool.colorTool(redValue, greenValue, blueValue);
            }
        });

        /**
         * Controls the blue RGB value using a seek bar.
         * Updates the current color after a value is selected.
         */
        blueBar = findViewById(R.id.blueBar);
        blueText = findViewById(R.id.blueTxt);
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                blueText.setText("B:" + blueValue);
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                blueText.setText("B:" + blueValue);
                brushTool.colorTool(redValue, greenValue, blueValue);
            }
        });

        /**
         * Modifies the brush size, between values 1 to 120 using a seek bar.
         * A value is shown above the seek bar to indicate the current value.
         */
        sizeText = findViewById(R.id.sizeTxt);
        sizeBar = findViewById(R.id.sizeBar);
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sizeValue = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                brushButton.setText("Brush");
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

        /**
         * Main brush type that toggles between "Brush" mode and "Bucket" mode.
         * Brush is the default stroke setting.
         * Bucket fills the canvas with a single solid color using a extremely large brush size.
         * This only fills up the entire canvas screen rather than in between areas separated by strokes.
         */
        brushButton = findViewById(R.id.brushBtn);
        brushButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brushButton.getText().equals("Brush")) {
                    brushButton.setText("Bucket");
                    brushTool.setBrushSize(5000);
                } else if (brushButton.getText().equals("Bucket")) {
                    brushButton.setText("Brush");
                    brushTool.setBrushSize(15);
                }
                brushTool.brushtypeTool();
            }
        }));

        /**
         * Changes the brush color using RGB values (1-255).
         * Clicking on the color button toggles the visibility of the color seek bars.
         */
        colorButton = findViewById(R.id.colorBtn);
        colorButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (redBar.getVisibility() == View.GONE && redText.getVisibility() == View.GONE &&
                        greenBar.getVisibility() == View.GONE && greenText.getVisibility() == View.GONE &&
                        blueBar.getVisibility() == View.GONE && blueText.getVisibility() == View.GONE) {
                    redBar.setVisibility(View.VISIBLE);
                    greenBar.setVisibility(View.VISIBLE);
                    blueBar.setVisibility(View.VISIBLE);
                    redText.setVisibility(View.VISIBLE);
                    greenText.setVisibility(View.VISIBLE);
                    blueText.setVisibility(View.VISIBLE);
                } else if (redBar.getVisibility() == View.VISIBLE && redText.getVisibility() == View.VISIBLE &&
                        greenBar.getVisibility() == View.VISIBLE && greenText.getVisibility() == View.VISIBLE &&
                        blueBar.getVisibility() == View.VISIBLE && blueText.getVisibility() == View.VISIBLE) {
                    redBar.setVisibility(View.GONE);
                    greenBar.setVisibility(View.GONE);
                    blueBar.setVisibility(View.GONE);
                    redText.setVisibility(View.GONE);
                    greenText.setVisibility(View.GONE);
                    blueText.setVisibility(View.GONE);
                }
            }
        }));

        /**
         * Modifies the brush size, between values 1 to 120.
         * Clicking on the size button toggles the visibility of the size seek bar.
         */
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

        /**
         * Turns the brush into an eraser to clear out strokes.
         */
        eraserButton = findViewById(R.id.eraserBtn);
        eraserButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.eraserTool();
            }
        }));

        /**
         * Clears the entire canvas currently worked on.
         */
        clearButton = findViewById(R.id.clearBtn);
        clearButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brushTool.clearTool();
            }
        }));

        /**
         * Returns the user to the LoadState activity screen.
         * If the user made any changes to the current canvas, they are prompted to save before changing screens.
         * This works by taking the array list "Paths" as a save state.
         */
        backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brushTool.getPathSize() > pathSize) {
                    backButton.setText("Save");
                    pathSize = brushTool.getPathSize();
                } else if (backButton.getText().equals("Save")) {
                    loadState.setSaveState(brushTool.setPaths());
                    backButton.setText("Back");
                } else if (backButton.getText().equals("Back")) {
                    openloadstate();
                }
            }
        }));
    }

    /**
     * Changes the current screen to the LoadState activity screen.
     */
    public void openloadstate() {
        Intent intent = new Intent(this, LoadState.class);
        startActivity(intent);
    }
}
