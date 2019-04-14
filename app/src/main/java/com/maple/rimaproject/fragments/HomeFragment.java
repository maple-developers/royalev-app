package com.maple.rimaproject.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.adapters.CustomSwipeAdapter;
import com.maple.rimaproject.adapters.InfinitePagerAdapter;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ViewPager viewPageAndroidDetails;
    String image;
    List Dataa;
    int i;
    List<Project> allDataArray=new ArrayList<>();
    List<Project> slider=new ArrayList<>();
    PagerAdapter adapter;

    ConstraintLayout layout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SliderLayout sliderLayout;
    CustomTextView txtWlc;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtWlc = view.findViewById(R.id.txtWlc);
//        txtWlc.setText(Html.fromHtml("Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
        viewPageAndroidDetails = view.findViewById(R.id.viewPageAndroidDetails);
//        sliderLayout = view.findViewById(R.id.imageSlider);
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
        GetUser service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetUser.class);

        /** Call the method with parameter in the interface to get the notice data*/


        Call<List<Project>> call = service.createUser2();

        /**Log the URL called*/
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                //   Toasty.success(getApplicationContext(), "login", Toasty.LENGTH_SHORT, true).show();

//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allDataArray=response.body();

                ArrayList<String> arr = new ArrayList<>();
                for (int i=0;i<allDataArray.size();i++) {
                    for (int j = 0; j < allDataArray.get(i).getSliders().size(); j++) {
                        if (allDataArray.get(i).getSliders().get(j).getStatus().equalsIgnoreCase("primary")) {

                            image = allDataArray.get(i).getSliders().get(j).getPhotoPath();
                            Log.e("onResponse", "onResponse: " + image);
                            arr.add(image);
                            adapter = new InfinitePagerAdapter(new CustomSwipeAdapter(getActivity(), arr));

                            viewPageAndroidDetails.setAdapter(adapter);
//                    image = response.body().get(i).

                            Log.e("ResponseLogIn", "onResponse: " + allDataArray.get(i).getSliders().get(0));
                        }
                    }
                }
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



//


            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(getContext(), "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });

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
//                allDataArray=response.body();
//                for (int i=0;i < allDataArray.size();i++){
//
//                    Log.e("ResponseLogIn", "onResponse: " + allDataArray.get(i).getArea());
//
//                }
//
//
//                // ArrayList<String> arr = new ArrayList<>();
//                // Dataa = response.body().data;
//                //   for (i = 0; i < Dataa.size(); i++) {
//                //image = response.body().data.get(i).avatar;
//                // setSliderViews(image, Dataa);
//                // arr.add(image);
//                // Log.e("image", "image: " + image);
//                //  }
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
//                Toast.makeText(getContext(), "Nooooo", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
