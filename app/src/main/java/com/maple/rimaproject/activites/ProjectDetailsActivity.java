package com.maple.rimaproject.activites;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.model.Project;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    ItemAdapter itemAdapter;
    ArrayList<Project> projectsList = new ArrayList<>();

    SliderLayout sliderLayout;




    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

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
        setSliderViews();
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        animalNames.add("Hotel");
        ButterKnife.bind(this);
//        fillProjects();
////        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
//        rvProjects.setLayoutManager(new GridLayoutManager(this,2));
////        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
//        itemAdapter = new ItemAdapter(this, projectsList,false);
//
//        rvProjects.setAdapter(itemAdapter);
//        itemAdapter.notifyDataSetChanged();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("http://preview.freethemescloud.com/dominno-preview/dominno/images/slider/1.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("https://revolution.themepunch.com/wp-content/uploads/revslider/real_estate_header/housebg1.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("http://allmumbaiproperty.com/public/images/slider-new.jpg");
                    break;
                case 3:
                    sliderView.setImageUrl("http://www.raftarworld.com/uploads/Slider6.jpg");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" + "Jackdaws love my big sphinx of quartz. " + (i + 1));
            final int finalI = i;

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(ProjectDetailsActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }

    }

    public void fillProjects() {
        for (int i=0; i < 16; i++){
            Project project = new Project();
            project.setId(1);
            project.setName("khalid");
            project.setName("aldaboubi");
            projectsList.add(project);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(31.9787559,35.8661521);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18.0f));

    }
}
