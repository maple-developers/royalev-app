package com.maple.rimaproject.activites;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.GetFeaures;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.SplashScreen;
import com.maple.rimaproject.adapters.CustomSwipeAdapter;
import com.maple.rimaproject.adapters.FeatureAdabter;
import com.maple.rimaproject.adapters.InfinitePagerAdapter;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;
import com.maple.rimaproject.model.Feature;
import com.maple.rimaproject.model.featureApi;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    ItemAdapter itemAdapter;
    ArrayList<Project> projectsList = new ArrayList<>();
    List<Project> detailsOfProject=new ArrayList<>();
    SliderLayout sliderLayout;
    ArrayList<String> arr= new ArrayList<>();
    String image;
    int newID;
    CustomTextView sizeBuilding;
    CustomTextView typeBuilding;
    String size;
    String size2;
    String type2;
    String type;
    String details;
    String status;

    CustomTextView projectDetails;
    CustomTextView statusText;
    String Latu,longt;
    double latu2,longt2;
    String location;
    CustomTextView locationText;
    List<featureApi>allFeature=new ArrayList<>();
    List<Project.Slider> sliders=new ArrayList<>();
    int PLACE_PICKER_REQUEST = 1;
    //List<String> allFeatures=new ArrayList<>();
    String all;
    String[] allFeatures;
    FeatureAdabter adapter;

    RecyclerView features;
    SharedPreference sharedPreference;
    List<featureApi> featuresList=new ArrayList<>();
    List<String> featureNames=new ArrayList<>();
    List<String> featureids=new ArrayList<>();
    List<String> featureimgs=new ArrayList<>();
    List<String> parts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        sizeBuilding=findViewById(R.id.size);
        typeBuilding=findViewById(R.id.type);
        projectDetails=findViewById(R.id.projectdetails);
        locationText=findViewById(R.id.location);

        feature();
        features=findViewById(R.id.recycfeatures);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        ButterKnife.bind(this);

        sharedPreference = new SharedPreference("projects");


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            newID= 0;
        } else {
            newID= extras.getInt("id");
            size=extras.getString("size");
            size2= size.replace("^","");
            type=extras.getString("type");
            type2=type.replace("^","");
            details=extras.getString("info");
            Latu=extras.getString("lat");
            longt=extras.getString("longi");
            status=extras.getString("status");

            sizeBuilding.setText(size2);
            typeBuilding.setText(type2);
            projectDetails.setText(details);
            locationText.setText(location);
            longt2 = Double.parseDouble(longt);
            latu2 = Double.parseDouble(Latu);
            statusText=findViewById(R.id.status);
            statusText.setText(status);
            Log.e("longt2", "longt2: "+latu2+","+longt2 );
            all=extras.getString("features");
            location=extras.getString("location");
//            all.replace("^^","^");



            String array = all.substring(1,all.length()-1);

            String data[]={array};
            List<Project>list=new ArrayList<>();
            Log.e("asdsa", "asdasd: "+array );
            parts =  Arrays.asList(array.split(Pattern.quote("^^")));

            for (int i=0;i<parts.size();i++){


                Log.e("fghgfhdfhg", "fghgfhdfhg: "+ parts.get(i));
            }





            // Sliders
            sliders= (List<Project.Slider>) extras.getSerializable("slider");
            for (int i=0; i < sliders.size(); i++){
                setSliderViews(sliders.get(i).getPhotoPath());
            }


        }
        Integer[]  images = {R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
                R.drawable.ic_secure_shield,
        };

        for(int i=0; i < parts.size(); i++){
            featureApi feature = new featureApi();

            feature.setName(parts.get(i));

            featuresList.add(feature);
        }
        adapter = new FeatureAdabter(ProjectDetailsActivity.this, parts, true);
        features.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(ProjectDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        features.setLayoutManager(horizontalLayoutManager);




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
////                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
//                detailsOfProject=response.body();
//
//
//
//                for (int i=0;i<detailsOfProject.size();i++) {
//                    for (int j = 0; j < detailsOfProject.get(i).getSliders().size(); j++) {
//                        if (detailsOfProject.get(i).getSliders().get(j).getStatus().equalsIgnoreCase("secondary")&&detailsOfProject.get(i).getId()==newID) {
//
//                            image = detailsOfProject.get(i).getSliders().get(j).getPhotoPath();
////                            setSliderViews(image);
//                            Log.e("onResponse", "onResponse: " + image);
//                            arr.add(image);
//
//                            type=detailsOfProject.get(i).getTypes();
//                            details=detailsOfProject.get(i).getDetails();
//                            Latu=detailsOfProject.get(i).getLatitude();
//                            longt=detailsOfProject.get(i).getLongitude();
//
//
//                            Log.e("asdsad", String.valueOf(latu2+","+longt2));
//
//                           type2=type.replace("^","");
////                            adapter = new InfinitePagerAdapter(new CustomSwipeAdapter(this, arr));
//
////                            viewPageAndroidDetails.setAdapter(adapter);
////                    image = response.body().get(i).
//
//                            Log.e("ResponseLogIn", "onResponse: " + detailsOfProject.get(i).getSliders().get(0));
//                        }
//                    }
//                }
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
////                Toast.makeText(this, "Nooooo", Toast.LENGTH_SHORT).show();
//                Toast.makeText(ProjectDetailsActivity.this, "Noooo", Toast.LENGTH_SHORT).show();
//            }
//        });
//        fillProjects();
////        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
//        rvProjects.setLayoutManager(new GridLayoutManager(this,2));
////        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
//        itemAdapter = new ItemAdapter(this, projectsList,false);
//
//        rvProjects.setAdapter(itemAdapter);
//        itemAdapter.notifyDataSetChanged();


    }

    private void setSliderViews(String image) {



        DefaultSliderView sliderView = new DefaultSliderView(this);


        sliderView.setImageUrl(image);


        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//        final int finalI = i;
//            sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" + "Jackdaws love my big sphinx of quartz. " + (i + 1));
//            final int finalI = i;

        sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(SliderView sliderView) {
//                    Toast.makeText(ProjectDetailsActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

            }
        });

        //at last add this view in your layout :
        sliderLayout.addSliderView(sliderView);


    }

    public void fillProjects() {
        for (int i=0; i < 16; i++){
            Project project = new Project();
//            project.setId(1);
//            project.setName("khalid");
//            project.setName("aldaboubi");
            projectsList.add(project);
        }
    }
    public void feature(){
        GetFeaures service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetFeaures.class);
        Call<List<featureApi>> call =  service.getfeature();
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<featureApi>>() {
            @Override
            public void onResponse(Call<List<featureApi>> call, Response<List<featureApi>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allFeature = response.body();
                Log.e("allFeature", "allFeature: "+allFeature );


//                Integer[]  images = {R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                        R.drawable.ic_secure_shield,
//                };


//                for(int i=0; i < allFeature.size(); i++){
//                    featureApi feature = new featureApi();
//                    feature.setId(allFeature.get(i).getId());
//                    feature.setName(allFeature.get(i).getName());
//
//                    featuresList.add(feature);
//                }


                for (int i=0; i < parts.size(); i++){
                    Log.e("fdksjghd", parts.get(i));
                }


//                for(int i=0; i < allFeature.size(); i++){
//                    featureApi feature = new featureApi();
//                    feature.setId(allFeature.get(i).getId());
//                    feature.setName(allFeature.get(i).getName());
//
//                    featuresList.add(feature);
//                }
//                adapter = new FeatureAdabter(ProjectDetailsActivity.this, featuresList, true);
//                features.setAdapter(adapter);
//                adapter.notifyDataSetChanged();

//                for (int i=0; i < parts.length; i++){
//                    if (!featuresList.get(i).getName().equals(parts[i])){
//                        Log.e("ewyhsdjk", featuresList.get(i).getName());
//                        featuresList.remove(i);
//                    }
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

//                viewPageAndroidDetails.setAlpha(0.3F);

            }

            @Override
            public void onFailure(Call<List<featureApi>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());

            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.e("dsfsdf", String.valueOf(latu2+","+longt2));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latu2,longt2);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18.0f));

    }
}
