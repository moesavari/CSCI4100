package com.example.myempire.lab_10;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class PlayMediaActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int count = 0;
    private int duration = 0;
    private int position = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_media);

        mediaPlayer = new MediaPlayer();
        AssetManager assetManager = this.getAssets();
        try {
            Log.i("musicManager", "this is a random text");
            AssetFileDescriptor fileDescriptor = assetManager.openFd("betterdays.mp3");
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            mediaPlayer.selectTrack(count);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        final Button play = (Button)findViewById(R.id.playButton_id);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("musicManager", "the song is playing");

                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                duration = mediaPlayer.getDuration();
                position = mediaPlayer.getCurrentPosition();

            }
        });

        Button pause = (Button)findViewById(R.id.pauseButton_id);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("musicManager", "the song is paused");

                mediaPlayer.pause();
            }
        });
    }
}
