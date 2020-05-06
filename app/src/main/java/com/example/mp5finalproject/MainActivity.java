package com.example.mp5finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The starting screen of the application. This is mostly for visual purposes
 * as a title screen.
 *
 * I've learned how to go about creating a drawing application by checking out paint app videos
 * by these YouTubers:
 * https://www.youtube.com/user/wangrui23
 * https://www.youtube.com/user/dukeacem
 * https://www.youtube.com/channel/UCWiZBp2dn3Apk67E6SLZOqA
 *
 * Creating a save state function was trickier than it looked, as I didn't look at how others did it.
 */
public class MainActivity extends AppCompatActivity {
    private Button startButton;

    /**
     * Clicking the button changes the screen to the LoadState activity screen.
     * @param savedInstanceState contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
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

    /**
     * Changes the current screen to the LoadState activity screen.
     */
    public void openloadstate() {
        Intent intent = new Intent(this, LoadState.class);
        startActivity(intent);
    }
}
