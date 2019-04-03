//package com.maple.rimaproject;
//
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//
//import com.smarteist.autoimageslider.IndicatorAnimations;
//import com.smarteist.autoimageslider.SliderAnimations;
//import com.smarteist.autoimageslider.SliderLayout;
//import com.smarteist.autoimageslider.DefaultSliderView;
//import com.smarteist.autoimageslider.SliderView;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, ExampleBottomSheetDialog.BottomSheetListener {
//    SliderLayout sliderLayout;
//    MyRecyclerViewAdapter adapter;
//    private Toolbar mTopToolbar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mTopToolbar =  findViewById(R.id.my_toolbar);
//        setSupportActionBar(mTopToolbar);
//
//        sliderLayout = findViewById(R.id.imageSlider);
//        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
//        sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :
//        setSliderViews();
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Hotel");
//        animalNames.add("Hotel");
//        animalNames.add("Hotel");
//        animalNames.add("Hotel");
//        animalNames.add("Hotel");
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(this, animalNames);
//        adapter.setClickListener(MainActivity.this);
//        recyclerView.setAdapter(adapter);
////        recyclerView.addItemDecoration(new DividerItemDecoration(this,
////                DividerItemDecoration.VERTICAL));
//        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
////        Button b2 = (Button) findViewById(R.id.btn_search);
////        b2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                sliderLayout.setVisibility(View.GONE);
////
////            }
////        });
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_favorite) {
//            ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
//            bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//    }
//    private void setSliderViews() {
//
//        for (int i = 0; i <= 3; i++) {
//
//            DefaultSliderView sliderView = new DefaultSliderView(this);
//
//            switch (i) {
//                case 0:
//                    sliderView.setImageDrawable(R.drawable.ic_launcher_background);
//                    break;
//                case 1:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//                    break;
//                case 2:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//                    break;
//                case 3:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//                    break;
//            }
//
//            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderView.setDescription("The quick brown fox jumps over the lazy dog.\n" +
//                    "Jackdaws love my big sphinx of quartz. " + (i + 1));
//            final int finalI = i;
//
//            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                @Override
//                public void onSliderClick(SliderView sliderView) {
//                    Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//            //at last add this view in your layout :
//            sliderLayout.addSliderView(sliderView);
//        }
//
//    }
//
//    @Override
//    public void onButtonClicked(String text) {
//
//    }
//}
