package com.maple.rimaproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.v4.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.annotations.NotNull;
import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.CustomAndroidGridViewAdapter;
import com.stfalcon.pricerangebar.RangeBarWithChart;
import com.stfalcon.pricerangebar.SeekBarWithChart;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import me.bendik.simplerangeview.SimpleRangeView;

import static java.sql.DriverManager.println;


public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    ArrayList arrayList;

    RangeBarWithChart rangeBar;


    public static String[] gridViewStrings = {
            "حراسة و امن 7/24",
            "أحواض سباحة",
            "ملاعب اطفال",
            "مواقف سيارات",
            "صالة رياضية",
            "حمام تركي_ساونا",
            "حدائق وممرات للمشي",
            "ملاعب كرة قجم",
            "مطاعم ومقاهي",
            "محلات تجارية",
            "صالة سينما",
            "مراكز للتسوق",

    };
    public static int[] gridViewImages = {
            R.drawable.ic_secure_shield,
            R.drawable.ic_swimming_pool,
            R.drawable.ic_children_slide,
            R.drawable.ic_parking,
            R.drawable.ic_stationary_bike,
            R.drawable.ic_shower,
            R.drawable.ic_landscape,
            R.drawable.ic_soccer_field,
            R.drawable.ic_dinner_table,
            R.drawable.ic_market,
            R.drawable.ic_cinema,
            R.drawable.ic_shopping_cart
    };
    private String mParam1;
    private String mParam2;


    ImageView plus1, plus2, plus3, plus4, minus1, minus2, minus3, minus4;
    int countbad, bathroom, living, kitchen;
    TextView badRoom, bathRoomText, livingRoom, KitchenRoom;
    CheckBox security;

    SimpleRangeView fixed_rangeview;
    EditText editStart,editEnd;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {

    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        editStart = (EditText) view.findViewById(R.id.edit_start);
        editEnd = (EditText) view.findViewById(R.id.edit_end);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

//        gridView = (GridView) view.findViewById(R.id.grid);
//        gridView.setAdapter(new CustomAndroidGridViewAdapter(getActivity(), gridViewStrings, gridViewImages));


        //initInstances(view);
        fixed_rangeview = view.findViewById(R.id.fixed_rangeview);
//        rangeBar = view.findViewById(R.id.range);


        badRoom = view.findViewById(R.id.badroom);
        bathRoomText = view.findViewById(R.id.bathroom);
        livingRoom = view.findViewById(R.id.livingroom);
        KitchenRoom = view.findViewById(R.id.kitchen);
        plus1 = view.findViewById(R.id.btnPlus1);
        plus2 = view.findViewById(R.id.btnPlus2);
        plus3 = view.findViewById(R.id.btnPlus3);
        plus4 = view.findViewById(R.id.btnPlus4);
        minus1 = view.findViewById(R.id.btnMinus1);
        minus2 = view.findViewById(R.id.btnMinus2);
        minus3 = view.findViewById(R.id.btnMinus3);
        minus4 = view.findViewById(R.id.btnMinus4);
        security=view.findViewById(R.id.check1);


        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countbad++;

                badRoom.setText(String.valueOf(countbad));
            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bathroom++;
                bathRoomText.setText(String.valueOf(bathroom));
            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                living++;
                livingRoom.setText(String.valueOf(living));
            }
        });
        plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kitchen++;
                KitchenRoom.setText(String.valueOf(kitchen));
            }
        });
        minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countbad > 0) {
                    countbad--;

                    badRoom.setText(String.valueOf(countbad));}
            }
        });
        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bathroom > 0) {
                    bathroom--;

                    bathRoomText.setText(String.valueOf(bathroom));}
            }
        });
        minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (living > 0) {
                    living--;

                    livingRoom.setText(String.valueOf(living));}
            }
        });
        minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kitchen > 0) {
                    kitchen--;

                    KitchenRoom.setText(String.valueOf(kitchen));
                }
            }
        });


        fixed_rangeview.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
                editStart.setText(String.valueOf(start));
            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
                editEnd.setText(String.valueOf(end));
            }
        });

//        fixed_rangeview.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
//            @Override
//            public void onRangeChanged(@NotNull SimpleRangeView rangeView, int start, int end) {
//                editRange.setText(String.valueOf(start) + " - " + String.valueOf(end));
//            }
//        });
        return view;
    }
//    private void initInstances(View v) {
//        rootLayoutAndroid = (CoordinatorLayout) v.findViewById(R.id.android_coordinator_layout);
//        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar_android_layout);
//        collapsingToolbarLayoutAndroid.setTitle("Material Grid");
//    }

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
