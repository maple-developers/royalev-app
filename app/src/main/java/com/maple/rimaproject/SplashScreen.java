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

import com.maple.rimaproject.Retrofit.GetSizeSearch;
import com.maple.rimaproject.Retrofit.GetType;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.RetrofitClient;

import com.maple.rimaproject.adapters.SharedPreference;
import com.maple.rimaproject.adapters.SharedPreferenceSize;
import com.maple.rimaproject.adapters.SharedPreferenceType;
import com.maple.rimaproject.model.TypeModel;
import com.maple.rimaproject.model.featureApi;
import com.maple.rimaproject.model.searchSizeApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends AppCompatActivity {

    String image;
    List Dataa;
    int i,j;
    List<Project> arr = new ArrayList<>();
    VideoView videoView;


    SharedPreference sharedPreference;
    SharedPreference sharedPreference2;
    SharedPreferenceSize sharedPreferenceSize;
    SharedPreferenceType sharedPreferenceType;
    List<Project> allProject = new ArrayList<>();
    List<searchSizeApi> allSize = new ArrayList<>();
    List<TypeModel> allType = new ArrayList<>();

    List<featureApi> allFeature = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);


        sharedPreference = new SharedPreference("projects");
        sharedPreference2 = new SharedPreference("features");
        sharedPreferenceSize = new SharedPreferenceSize("Size");
        sharedPreferenceType= new SharedPreferenceType("Type");
        //   videoView = (VideoView) findViewById(R.id.videoView);

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
//        GetUser service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetUser.class);
//
//        /** Call the method with parameter in the interface to get the notice data*/
//
//
//        Call<List<Project>> call = service.createUser2();
//
//        /**Log the URL called*/
//        Log.e("URL Called", call.request().url() + "");
//
//        call.enqueue(new Callback<List<Project>>() {
//            @Override
//            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
//                //   Toasty.success(getApplicationContext(), "login", Toasty.LENGTH_SHORT, true).show();
//
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
//                arr=response.body();
//        for (int i=0;i<arr.size();i++){
//
//            Log.e("ResponseLogIn", "onResponse: " + arr.get(i).getArea());
//
//        }
//
//
//               // ArrayList<String> arr = new ArrayList<>();
//               // Dataa = response.body().data;
//             //   for (i = 0; i < Dataa.size(); i++) {
//                    //image = response.body().data.get(i).avatar;
//                    // setSliderViews(image, Dataa);
//                   // arr.add(image);
//                   // Log.e("image", "image: " + image);
//              //  }
//
////                viewPageAndroidDetails.setAlpha(0.3F);
//
//
//
////
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Project>> call, Throwable t) {
////                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
//                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
//                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
//            }
//        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        videoView = (VideoView) findViewById(R.id.videoView);
        try {
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
        } catch (Exception ex) {
//            jump();
        }

        fillProjects();
        fillsize();
        filltype();
    }


    public void fillProjects() {
        GetUser service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetUser.class);
        Call<List<Project>> call = service.createUser2();
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allProject = response.body();
                List<Project> allProject_cache = sharedPreference.getArrayList(SplashScreen.this);
                if (allProject_cache != null) {
                    for (Project person2 : allProject_cache) {
                        // Loop arrayList1 items
                        boolean found = false;
                        for (Project person1 : allProject) {
                            if (person2.getId() == person1.getId()) {
                                found = true;
                            }
                        }
                        if (!found) {
                            sharedPreference.addArrayList(SplashScreen.this, allProject.get(i));
//                        results.add(person2.id);
                        }
                    }
                } else {
                    for (int i = 0; i < allProject.size(); i++) {
                        sharedPreference.addArrayList(SplashScreen.this, allProject.get(i));
                    }
                }


//
//
//                 ArrayList<String> arr = new ArrayList<>();
//                 Dataa = response.body().data;
//                   for (i = 0; i < Dataa.size(); i++) {
//                image = response.body().data.get(i).avatar;
//                 setSliderViews(image, Dataa);
//                 arr.add(image);
//                 Log.e("image", "image: " + image);
//                  }

//                viewPageAndroidDetails.setAlpha(0.3F);

            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fillsize() {

        GetSizeSearch service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetSizeSearch.class);
        Call<List<searchSizeApi>> call = service.getSearch();
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<searchSizeApi>>() {
            @Override
            public void onResponse(Call<List<searchSizeApi>> call, Response<List<searchSizeApi>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allSize = response.body();
                for (int i=0;i<allSize.size();i++){


                Log.e("Size", "Size: "+allSize.get(i).getName());

                }
                List<searchSizeApi> allProject_cache = sharedPreferenceSize.getArrayList(SplashScreen.this);
                if (allProject_cache != null) {
                    for (searchSizeApi person2 : allProject_cache) {
                        // Loop arrayList1 items
                        boolean found = false;
                        for (searchSizeApi person1 : allSize) {
                            if (person2.getId() == person1.getId()) {
                                found = true;
                            }
                        }
                        if (!found) {
                            sharedPreferenceSize.addArrayList(SplashScreen.this, allSize.get(j));
//                        results.add(person2.id);
                        }
                    }
                } else {
                    for (int k = 0; k < allSize.size(); k++) {
                        sharedPreferenceSize.addArrayList(SplashScreen.this, allSize.get(k));
                    }
                }


                LruCache<String,String> test = new LruCache<>(10);
                test.put("khalid","aldaboubi");
//                CacheUtils<String, List<Datum>> cache = new LruCache<>();
//                if (cache.get("projects") == null){
//                    cache.put("projects", arr);
//                }


//
//
//                 ArrayList<String> arr = new ArrayList<>();
//                 Dataa = response.body().data;
//                   for (i = 0; i < Dataa.size(); i++) {
//                image = response.body().data.get(i).avatar;
//                 setSliderViews(image, Dataa);
//                 arr.add(image);
//                 Log.e("image", "image: " + image);
//                  }



            }

            @Override
            public void onFailure(Call<List<searchSizeApi>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("SizeError", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });




    }






    public void filltype() {

        GetType service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetType.class);
        Call<List<TypeModel>> call = service.gettype();
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<TypeModel>>() {
            @Override
            public void onResponse(Call<List<TypeModel>> call, Response<List<TypeModel>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allType = response.body();
                for (int i=0;i<allType.size();i++){


                    Log.e("type", "type: "+allType.get(i).getName());

                }
                List<TypeModel> allProject_cache = sharedPreferenceType.getArrayList(SplashScreen.this);
                if (allProject_cache != null) {
                    for (TypeModel person2 : allProject_cache) {
                        // Loop arrayList1 items
                        boolean found = false;
                        for (TypeModel person1 : allType) {
                            if (person2.getId() == person1.getId()) {
                                found = true;
                            }
                        }
                        if (!found) {
                            sharedPreferenceType.addArrayList(SplashScreen.this, allType.get(j));
//                        results.add(person2.id);
                        }
                    }
                } else {
                    for (int k = 0; k < allType.size(); k++) {
                        sharedPreferenceType.addArrayList(SplashScreen.this, allType.get(k));
                    }
                }


//
//
//                 ArrayList<String> arr = new ArrayList<>();
//                 Dataa = response.body().data;
//                   for (i = 0; i < Dataa.size(); i++) {
//                image = response.body().data.get(i).avatar;
//                 setSliderViews(image, Dataa);
//                 arr.add(image);
//                 Log.e("image", "image: " + image);
//                  }

//                viewPageAndroidDetails.setAlpha(0.3F);

            }

            @Override
            public void onFailure(Call<List<TypeModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("SizeError", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });




    }




//    public void feature(){
//        GetFeaures service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetFeaures.class);
//        Call<featureApi> call =  service.getfeature();
//        Log.e("URL Called", call.request().url() + "");
//
//        call.enqueue(new Callback<List<featureApi>>() {
//            @Override
//            public void onResponse(Call<List<featureApi>> call, Response<List<featureApi>> response) {
////                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
//                allFeature = response.body();
//                List<featureApi> allProject_cache = sharedPreference2.getArrayList(SplashScreen.this);
//                if (allProject_cache != null) {
//                    for (featureApi person2 : allProject_cache) {
//                        // Loop arrayList1 items
//                        boolean found = false;
//                        for (featureApi person1 : allFeature) {
//                            if (person2.getId() == person1.getId()) {
//                                found = true;
//                            }
//                        }
//                        if (!found) {
//                            sharedPreference.addArrayList(SplashScreen.this, allFeature.get(i));
////                        results.add(person2.id);
//                        }
//                    }
//                } else {
//                    for (int i = 0; i < allFeature.size(); i++) {
//                        sharedPreference.addArrayList(SplashScreen.this, allFeature.get(i));
//                    }
//                }
//
//
////
////
////                 ArrayList<String> arr = new ArrayList<>();
////                 Dataa = response.body().data;
////                   for (i = 0; i < Dataa.size(); i++) {
////                image = response.body().data.get(i).avatar;
////                 setSliderViews(image, Dataa);
////                 arr.add(image);
////                 Log.e("image", "image: " + image);
////                  }
//
////                viewPageAndroidDetails.setAlpha(0.3F);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<featureApi>> call, Throwable t) {
////                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
//                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
//                Toast.makeText(SplashScreen.this, "Nooooo", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    }


