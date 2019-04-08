package com.maple.rimaproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

public class SplashScreen extends AppCompatActivity {


    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);


//        videoView = (VideoView) findViewById(R.id.videoView);

//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
//        videoView.setVideoURI(video);
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
////                startNextActivity();
//                Toast.makeText(SplashScreen.this, "finish", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        videoView.start();


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        videoView = (VideoView) findViewById(R.id.videoView);
        try{
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                    + R.raw.splash);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
//                    jump();
//                    Toast.makeText(SplashScreen.this, "finish", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SplashScreen.this, NavigationActivity.class);
                    startActivity(i);
                    finish();
                }

            });
            videoView.start();
        } catch(Exception ex) {
//            jump();
        }

    }
}
