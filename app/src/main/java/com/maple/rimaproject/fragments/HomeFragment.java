package com.maple.rimaproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.HomeActivity;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Datum;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.adapters.CustomSwipeAdapter;
import com.maple.rimaproject.adapters.InfinitePagerAdapter;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

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
//        GetUser service = RetrofitClient.getClient("https://reqres.in/api/").create(GetUser.class);
//
//        /** Call the method with parameter in the interface to get the notice data*/
//
//
//        Call<Datum.Example> call = service.createUser2();
//
//        /**Log the URL called*/
//        Log.e("URL Called", call.request().url() + "");
//
//        call.enqueue(new Callback<Datum.Example>() {
//            @Override
//            public void onResponse(Call<Datum.Example> call, Response<Datum.Example> response) {
//                //   Toasty.success(getApplicationContext(), "login", Toasty.LENGTH_SHORT, true).show();
//                Log.e("ResponseLogIn", "onResponse: " + response.toString());
//                ArrayList<String> arr = new ArrayList<>();
//                Dataa = response.body().data;
//                for (i = 0; i < Dataa.size(); i++) {
//                    image = response.body().data.get(i).avatar;
//                    // setSliderViews(image, Dataa);
//                    arr.add(image);
//                    Log.e("image", "image: " + image);
//                }
//                adapter = new InfinitePagerAdapter(new CustomSwipeAdapter(getActivity(), arr));
////                viewPageAndroidDetails.setAlpha(0.3F);
//                viewPageAndroidDetails.setAdapter(adapter);
//                // save USERNAME ,PASSWORD AND ID IN SHAREDPREF (USERNAME AND PASS TO CHECK IF USER IS LOGIN OR NOT )
//
//
////                      Toast.makeText(LogInActivity.this, id, Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Datum.Example> call, Throwable t) {
////                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
//                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
//                Toast.makeText(getActivity(), "nooooo", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getActivity());

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



            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }

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
