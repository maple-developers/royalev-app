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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toolbar;

import com.google.firebase.database.annotations.NotNull;
import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.CustomAndroidGridViewAdapter;

import java.util.ArrayList;

import me.bendik.simplerangeview.SimpleRangeView;


public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    ArrayList arrayList;
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

        gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new CustomAndroidGridViewAdapter(getActivity(), gridViewStrings, gridViewImages));

        //initInstances(view);
        fixed_rangeview = view.findViewById(R.id.fixed_rangeview);


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
