package com.example.mp5finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The starting screen of the application.
 */
public class MainActivity extends AppCompatActivity {
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startBtn);
        startButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloadstate();
            }
        }));
    }

    public void openloadstate() {
        Intent intent = new Intent(this, LoadState.class);
        startActivity(intent);
    }
}
