package com.maple.rimaproject.activites;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maple.rimaproject.ChatActivity;
import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.LoginActivity;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.GetFeaures;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.adapters.CustomSwipeAdapter;
import com.maple.rimaproject.adapters.FeatureAdabter;
import com.maple.rimaproject.adapters.InfinitePagerAdapter;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;
import com.maple.rimaproject.model.featureApi;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    ItemAdapter itemAdapter;
    ArrayList<Project> projectsList = new ArrayList<>();
    List<Project> detailsOfProject = new ArrayList<>();
    SliderLayout sliderLayout;
    ArrayList<String> arr = new ArrayList<>();
    String image;
    int newID;
    TagGroup sizeBuilding;
    CustomTextView typeBuilding;
    String size;
    String size2;
    String size3;
    String type2;
    String type;
    String details;
    String status;
    String referenceId;
    String price;
    String plan;
    String location;

//    CustomTextView projectDetails;
    CustomTextView Location;
    CardView LocationCard;
    CustomTextView statusText;
    CustomTextView priceText;
    String Latu, longt;
    double latu2, longt2;
    String area;
    CustomTextView AreaText;
    CustomTextView PlanText;
    List<featureApi> allFeature = new ArrayList<>();
    List<Project.Slider> sliders = new ArrayList<>();
    int PLACE_PICKER_REQUEST = 1;
    //List<String> allFeatures=new ArrayList<>();
    String all;
    String[] allFeatures;
    FeatureAdabter adapter;
    PagerAdapter adapterr;
    ViewPager viewPageAndroidDetails;
    RecyclerView features;
    SharedPreference sharedPreference;
    List<featureApi> featuresList = new ArrayList<>();
    List<String> featureNames = new ArrayList<>();
    List<String> featureids = new ArrayList<>();
    List<String> featureimgs = new ArrayList<>();
    List<String> parts = new ArrayList<>();
    String format_Price_TRY;
    String format_Price_USD;


    ExpandableTextView expTv1;



    LinearLayout btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        sizeBuilding = findViewById(R.id.size);
        typeBuilding = findViewById(R.id.type);
//        projectDetails = findViewById(R.id.projectdetails);
        AreaText = findViewById(R.id.area);
        PlanText = findViewById(R.id.plan);
        Location = findViewById(R.id.location);
        LocationCard = findViewById(R.id.cardlocation);
        btnChat = findViewById(R.id.btnChat);
        expTv1 = (ExpandableTextView) findViewById(R.id.expand_text_view);
//        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
//            @Override
//            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
//                Toast.makeText(ProjectDetailsActivity.this, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
//            }
//        });
// IMPORTANT - call setText on the ExpandableTextView to set the text content to display


        viewPageAndroidDetails = findViewById(R.id.viewPageAndroidDetails);
        feature();
        features = findViewById(R.id.recycfeatures);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //sliderLayout = findViewById(R.id.imageSlider);
//        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
//        sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        ButterKnife.bind(this);

        sharedPreference = new SharedPreference("projects");


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            newID = 0;
        } else {

            newID = extras.getInt("id");
            size = extras.getString("size");

            Log.e("asd", "sub: " + size2);
            size2 = size.replace("^^", "&");
            Log.e("asd", "rep: " + size2);

            type = extras.getString("type");
            type2 = type.replace("^", "");
            details = extras.getString("info");
            Latu = extras.getString("lat");
            longt = extras.getString("longi");
            status = extras.getString("status");
            plan = extras.getString("plan1");
            price = extras.getString("price");
            area = extras.getString("area");
            location = extras.getString("location");




                Location.setText(location);


            String allPrice[] = price.split(Pattern.quote("^"));

            String TRY_price = allPrice[0];
            String USD_price = allPrice[1];
            String TYE[] = TRY_price.split("_");
            Log.e("TYE", "TYE: " + TYE[0]);
            String USD[] = USD_price.split("_");
            Log.e("TYE", "TYE: " + USD[0]);

            NumberFormat formatter = new DecimalFormat("#,###");
            double myNumber = Double.parseDouble(TYE[0]);
            double myNumber2 = Double.parseDouble(USD[0]);
            String formattedTRY = formatter.format(myNumber);
            String formattedUSD = formatter.format(myNumber2);


            //format_Price_TRY = (String.format("%,d" ,953430)).replace(' ', ',');
            //format_Price_USD = (String.format("%,d" ,165082)).replace(' ', ',');
            Log.e("format_Price_USD", "format_Price_USD: " + formattedUSD);
            Log.e("format_Price_TRY", "format_Price_TRY: " + formattedTRY);

            //  String[] allPricse=price.split(" ");


//          for (int j=0;j<allPrice.length;++j){
//
//
////             price = (String.format("%,d", price));
//
//          }
//            Log.e("dsadzxc", "dsadzxc: "+s );
            size3 = size2.substring(1, size2.length() - 1);
            String[] sizes = size3.split("&");
            Log.e("asd", "asd: " + size3);
            sizeBuilding.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            sizeBuilding.setTags(sizes);

            if (!type2.equals("")) {


                typeBuilding.setText(type2);
            } else {
                typeBuilding.setText("غير محدد");
            }

            if (!details.equals("")) {


//                projectDetails.setText(details);
                expTv1.setText(details);
                AreaText.setText(area);
                longt2 = Double.parseDouble(longt);
                latu2 = Double.parseDouble(Latu);
                statusText = findViewById(R.id.status);
                priceText = findViewById(R.id.price);
                priceText.setText(formattedTRY + " " + TYE[1] + "\n" + formattedUSD + " " + USD[1]);
                if (!status.equals("")) {
                    statusText.setText(status);
                } else {
                    statusText.setText("غير محدد");
                }
                if (!plan.equals("")) {
                    PlanText.setText(plan);
                } else {
                    priceText.setText("غير محدد");
                }
                Log.e("longt2", "longt2: " + latu2 + "," + longt2);
                all = extras.getString("features");

//            all.replace("^^","^");


                String array = all.substring(1, all.length() - 1);

                String data[] = {array};
                List<Project> list = new ArrayList<>();
                Log.e("asdsa", "asdasd: " + array);
                parts = Arrays.asList(array.split(Pattern.quote("^^")));


        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectDetailsActivity.this, LoginActivity.class);
                i.putExtra("project_name", referenceId);
                startActivity(i);
            }
        });


                // Sliders
                arr = new ArrayList<>();
                sliders = (List<Project.Slider>) extras.getSerializable("slider");
                for (int i = 0; i < sliders.size(); i++) {
                    //setSliderViews(sliders.get(i).getPhotoPath());
//                String image=;

                    image = sliders.get(i).getPhotoPath();
                    Log.e("Imgaessssda", "onCreate: " + image);
                    arr.add(image);

                }
                adapterr = new InfinitePagerAdapter(new CustomSwipeAdapter(this, arr));

                viewPageAndroidDetails.setAdapter(adapterr);


            }
            Integer[] images = {R.drawable.ic_secure_shield,
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

            for (int i = 0; i < parts.size(); i++) {
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
    }
        private void setSliderViews (String image){


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

        public void fillProjects () {
            for (int i = 0; i < 16; i++) {
                Project project = new Project();
//            project.setId(1);
//            project.setName("khalid");
//            project.setName("aldaboubi");
                projectsList.add(project);
            }
        }

        public void feature () {
            GetFeaures service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetFeaures.class);
            Call<List<featureApi>> call = service.getfeature();
            Log.e("URL Called", call.request().url() + "");

            call.enqueue(new Callback<List<featureApi>>() {
                @Override
                public void onResponse(Call<List<featureApi>> call, Response<List<featureApi>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                    allFeature = response.body();
                    Log.e("allFeature", "allFeature: " + allFeature);


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


                    for (int i = 0; i < parts.size(); i++) {
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
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;

            Log.e("dsfsdf", String.valueOf(latu2 + "," + longt2));
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(latu2, longt2);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18.0f));

        }

}