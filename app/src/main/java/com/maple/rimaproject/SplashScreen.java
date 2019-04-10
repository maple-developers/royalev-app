package com.maple.rimaproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.maple.rimaproject.Retrofit.Datum;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.adapters.CustomSwipeAdapter;
import com.maple.rimaproject.adapters.InfinitePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    String image;
    List Dataa;
    int i;
    List<Datum> arr=new ArrayList<>();
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
        GetUser service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetUser.class);

        /** Call the method with parameter in the interface to get the notice data*/


        Call<List<Datum>> call = service.createUser2();

        /**Log the URL called*/
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Datum>>() {
            @Override
            public void onResponse(Call<List<Datum>> call, Response<List<Datum>> response) {
                //   Toasty.success(getApplicationContext(), "login", Toasty.LENGTH_SHORT, true).show();

                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                arr=response.body();
        for (int i=0;i<=arr.size();i++){

            Log.e("ResponseLogIn", "onResponse: " + arr.get(i).getSliders().get(0));

        }


               // ArrayList<String> arr = new ArrayList<>();
               // Dataa = response.body().data;
             //   for (i = 0; i < Dataa.size(); i++) {
                    //image = response.body().data.get(i).avatar;
                    // setSliderViews(image, Dataa);
                   // arr.add(image);
                   // Log.e("image", "image: " + image);
              //  }

//                viewPageAndroidDetails.setAlpha(0.3F);

                // save USERNAME ,PASSWORD AND ID IN SHAREDPREF (USERNAME AND PASS TO CHECK IF USER IS LOGIN OR NOT )


//                      Toast.makeText(LogInActivity.this, id, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<Datum>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });

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
