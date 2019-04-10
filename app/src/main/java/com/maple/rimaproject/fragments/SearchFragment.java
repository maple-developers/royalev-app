package com.maple.rimaproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.v4.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.Collections;

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
    ArrayList <Integer> arrayList=new ArrayList<>();;
CheckBox check_1Security;
    CheckBox check_2swimbool;
    CheckBox check_3childgame;
    CheckBox check_4shower;
    CheckBox check_5parking;
    CheckBox check_6gym;
    CheckBox check_7gardens;
    CheckBox check_8football;
    CheckBox check_9resturant;
    CheckBox check_10market;
    CheckBox check_11cenima;
    CheckBox check_12shopping;
Button search;


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
int rangStart;
int rangEnd;


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
        check_1Security=view.findViewById(R.id.check1);
        check_2swimbool=view.findViewById(R.id.check2);
        check_3childgame=view.findViewById(R.id.check3);
        check_4shower=view.findViewById(R.id.check4);
        check_5parking=view.findViewById(R.id.check5);
        check_6gym=view.findViewById(R.id.check6);
        check_7gardens=view.findViewById(R.id.check7);
        check_8football=view.findViewById(R.id.check8);
        check_9resturant=view.findViewById(R.id.check9);
        check_10market=view.findViewById(R.id.check10);
        check_11cenima=view.findViewById(R.id.check11);
        check_12shopping=view.findViewById(R.id.check12);
search=view.findViewById(R.id.btn_search);

        check_1Security.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(1);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==1){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_2swimbool.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(2);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==2){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_3childgame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(3);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==3){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_4shower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(4);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==4){
                            arrayList.remove(i);


                        }
                    }

                }
            }
        });
        check_5parking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(5);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==5){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_6gym.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(6);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==6){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_7gardens.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(7);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==7){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_8football.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(8);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==8){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_9resturant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(9);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==9){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_10market.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(10);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==10){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_11cenima.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(11);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==11){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });
        check_12shopping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    arrayList.add(12);
                }else
                {
                    for (int i=0 ; i<arrayList.size();i++){

                        if (arrayList.get(i)==12){
                            arrayList.remove(i);


                        }
                    }

                }

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(arrayList);
                Log.e("arrayList", "arrayList: "+ arrayList+"\n"+"bathroom"+bathroom+"\n"+"living"+living+"\n"+"kitchen"+kitchen+"\n"+"countbad"+countbad +"\n"+"rangStart="+rangStart+"\n"+"rangEnd="+rangEnd);
            }
        });




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




                String start1= String.valueOf(start+"K");
                editStart.setText(start1);
                Log.e("onStartRangeChanged", "onStartRangeChanged: "+start1 );

            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {


                String End1=String.valueOf(end+"K");
                editEnd.setText(End1);
                Log.e("onStartRangeChanged", "onStartRangeChanged: "+End1);

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
