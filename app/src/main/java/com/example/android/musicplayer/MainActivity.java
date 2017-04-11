package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Variables used for the MediaPlayer API
    private MediaPlayer mp;
    private Button play;
    private Button pause;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Create the objects to be used with Listeners below
         */
        play = (Button) findViewById(R.id.play_button);
        pause = (Button) findViewById(R.id.pause_button);
        stop = (Button) findViewById(R.id.stop_button);
        mp = MediaPlayer.create(this, R.raw.song);

        /**
         * Play Listener:
         * a. Displays a toast message that the song started playing
         * b. Starts playing the song
         * c. Disables the play button
         * d. Enables the remaining buttons
         */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mp.start();
                play.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);
            }
        });

        /**
         * Pause Listener:
         * a. Displays a toast message that the song is paused
         * b. Pauses the song
         * c. Disables the pause button
         * d. Enables the remaining buttons
         */
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Song Paused", Toast.LENGTH_SHORT).show();
                mp.pause();
                pause.setEnabled(false);
                play.setEnabled(true);
                stop.setEnabled(true);
            }
        });

        /**
         * Stop Listener:
         * a. Displays a toast message that the player stopped
         * b. Stops the player completely
         * c. Enables all the buttons
         * d. Sets the state of player to prepare() in order to use it again.
         */
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Player stopped", Toast.LENGTH_SHORT).show();
                mp.stop();
                pause.setEnabled(true);
                play.setEnabled(true);
                stop.setEnabled(true);
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * setOnCompletionListener Callback: Testing asynchronous Callbacks!
         */
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "I 'm done!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
