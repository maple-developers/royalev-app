package com.maple.rimaproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.reflect.TypeToken;
import com.maple.rimaproject.Retrofit.Datum;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.cache.CacheManager;
import com.maple.rimaproject.cache.CacheUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;



public class SplashScreen extends AppCompatActivity {

    String image;
    List Dataa;
    int i;
    List<Datum> arr=new ArrayList<>();
    VideoView videoView;

    private CacheManager cacheManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);

        cacheManager = new CacheManager(this);
        initFromCache(); //load data from cache if exist.

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
            public void onResponse(Call<List<Datum>> call, retrofit2.Response<List<Datum>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                arr=response.body();
                Log.e("werwerwer", String.valueOf(arr.size()));
                List<Datum> entries = new ArrayList<Datum>();
                for (int i=0;i<arr.size();i++){

                    Log.e("ResponseLogIn", "onResponse: " + arr.get(i).getArea());


                    entries.add(arr.get(i));

                }


                LruCache<String,String> test = new LruCache<>(10);
                test.put("khalid","aldaboubi");
//                CacheUtils<String, List<Datum>> cache = new LruCache<>();
//                if (cache.get("projects") == null){
//                    cache.put("projects", arr);
//                }





                try {
                    CacheManager.writeObject(SplashScreen.this, "sesoo", entries);

                }catch (Exception e){
                    e.printStackTrace();
                }

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


    private void initFromCache() {

        Type type = new TypeToken<ArrayList<Datum>>() {}.getType();
        ArrayList<Datum> itemLatest = (ArrayList<Datum>) cacheManager.readJson(type, CacheUtils.LATEST);
//        if (itemLatest != null)
//            latestAdapter.replaceWith(itemLatest);
    }

}
