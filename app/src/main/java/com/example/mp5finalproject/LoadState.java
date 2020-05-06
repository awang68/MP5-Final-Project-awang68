package com.example.mp5finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Contains a gallery of saved creations.
 * A user that clicks on one of the canvas will open the current art project.
 * (Maybe add preview icons to each canvas save file?)
 */
public class LoadState extends AppCompatActivity {
    private Button canvas1;
    private Button canvas2;
    private Button canvas3;
    private Button canvas4;
    private Button canvas5;
    private Button canvas6;
    private ArrayList<MainBrush> save1 = new ArrayList<>();
    private ArrayList<MainBrush> save2 = new ArrayList<>();
    private ArrayList<MainBrush> save3 = new ArrayList<>();
    private ArrayList<MainBrush> save4 = new ArrayList<>();
    private ArrayList<MainBrush> save5 = new ArrayList<>();
    private ArrayList<MainBrush> save6 = new ArrayList<>();
    private int canvasNumber = 0;
    private Brush brushTool;

    public void setSaveState(ArrayList<MainBrush> save) {
        if (save == null || canvasNumber == 0) {
            return;
        } else if (canvasNumber == 1) {
            save1 = save;
        } else if (canvasNumber == 2) {
            save2 = save;
        } else if (canvasNumber == 3) {
            save3 = save;
        } else if (canvasNumber == 4) {
            save4 = save;
        } else if (canvasNumber == 5) {
            save5 = save;
        } else if (canvasNumber == 61) {
            save6 = save;
        }
    }
    /**
     * Creates a page with six buttons, each one corresponding to an art canvas file.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_state);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        canvas1 = findViewById(R.id.artwork1);
        canvas1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 1;
                brushTool.setPaths(save1);
                openartcanvas();
            }
        }));

        canvas2 = findViewById(R.id.artwork2);
        canvas2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 2;
                brushTool.setPaths(save2);
                openartcanvas();
            }
        }));

        canvas3 = findViewById(R.id.artwork3);
        canvas3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 3;
                brushTool.setPaths(save3);
                openartcanvas();
            }
        }));

        canvas4 = findViewById(R.id.artwork4);
        canvas4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 4;
                brushTool.setPaths(save4);
                openartcanvas();
            }
        }));

        canvas5 = findViewById(R.id.artwork5);
        canvas5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 5;
                brushTool.setPaths(save5);
                openartcanvas();
            }
        }));

        canvas6 = findViewById(R.id.artwork6);
        canvas6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasNumber = 6;
                brushTool.setPaths(save6);
                openartcanvas();
            }
        }));
    }

    /**
     * Changes the screen to the ArtCanvas screen.
     */
    public void openartcanvas() {
        Intent intent = new Intent(this, ArtCanvas.class);
        startActivity(intent);
    }

}
