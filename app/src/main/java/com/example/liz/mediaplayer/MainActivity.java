package com.example.liz.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    TextView timeText;
    private int startTime = 0;
    private int finalTime = 0;
    android.os.Handler handler = new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.tiktok);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        timeText = (TextView)findViewById(R.id.timeText);
    }
    public void play(View view){
        mediaPlayer.start();
        finalTime = mediaPlayer.getDuration();
        seekBar.setMax(finalTime);
        handler.postDelayed(UpdateSongTime,100);
    }
    public void pause(View view){
        mediaPlayer.pause();
    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            int minute = (int)TimeUnit.MILLISECONDS.toMinutes((long) startTime);
            int second = (int)(TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(minute));
            timeText.setText(String.format("%02d : %02d",minute,second));
            seekBar.setProgress(startTime);
            handler.postDelayed(this, 100);
        }
    };
}
