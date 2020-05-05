package com.example.mp5finalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

/**
 * Contains a gallery of saved creations.
 * A user that clicks on one of the canvas will open the current art project.
 * (Maybe add preview icons to each canvas save file?)
 */
public class LoadState extends AppCompatActivity {
    private Button loadButton;
    private ToggleButton canvas1;
    private ToggleButton canvas2;
    private ToggleButton canvas3;
    private ToggleButton canvas4;
    private ToggleButton canvas5;
    private ToggleButton canvas6;


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
                openartcanvas();
            }
        }));

        canvas2 = findViewById(R.id.artwork2);
        canvas2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openartcanvas();
            }
        }));

        canvas3 = findViewById(R.id.artwork3);
        canvas3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openartcanvas();
            }
        }));

        canvas4 = findViewById(R.id.artwork4);
        canvas4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openartcanvas();
            }
        }));

        canvas5 = findViewById(R.id.artwork5);
        canvas5.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openartcanvas();
            }
        }));

        canvas6 = findViewById(R.id.artwork6);
        canvas6.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openartcanvas();
            }
        }));
    }

    public void openartcanvas() {
        Intent intent = new Intent(this, ArtCanvas.class);
        startActivity(intent);
    }

}
