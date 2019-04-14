package com.maple.rimaproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.firebase.database.annotations.NotNull;
import com.maple.rimaproject.ProjectResultActivity;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.GetAllSearch;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.SplashScreen;
import com.maple.rimaproject.adapters.CustomAndroidGridViewAdapter;
import com.maple.rimaproject.adapters.SharedPreference;
import com.maple.rimaproject.adapters.SharedPreferenceSize;
import com.maple.rimaproject.adapters.SharedPreferenceType;
import com.maple.rimaproject.model.TypeModel;
import com.maple.rimaproject.model.searchSizeApi;
import com.stfalcon.pricerangebar.RangeBarWithChart;
import com.stfalcon.pricerangebar.SeekBarWithChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import me.bendik.simplerangeview.SimpleRangeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static java.sql.DriverManager.println;


public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button search;
    Toolbar toolbar;

    ArrayList<Integer> arrayList = new ArrayList<>();

    List<String> type = new ArrayList<>();
    List<String> size = new ArrayList<>();


    List<Project> SearchProject = new ArrayList<>();
    SharedPreference sharedPreference;
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

    CheckBox check_Size1;
    CheckBox check_Size2;
    CheckBox check_Size3;
    CheckBox check_Size4;
    CheckBox check_Size5;
    CheckBox check_Size6;
    CheckBox check_Size7;
    CheckBox check_Size8;

    CheckBox check_Type1;
    CheckBox check_Type2;
    CheckBox check_Type3;
    CheckBox check_Type4;
    CheckBox check_Type5;

    EditText editStart, editEnd;


    List<searchSizeApi> allSize = new ArrayList<>();
    List<TypeModel> allType = new ArrayList<>();
    List<Project> all_projects = new ArrayList<>();
    List<String> prices = new ArrayList<>();
    String[] current_price_with_curr;
    SharedPreferenceSize sharedPreferenceSize;
    SharedPreferenceType sharedPreferenceType;

    private String mParam1;
    private String mParam2;
    int rangStart;
    int rangEnd;
    String min;
    String max;
    CheckBox security;
    String size_string;
    String type_string;
    String features_string;
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
        sharedPreferenceSize = new SharedPreferenceSize("Size");
        sharedPreferenceType = new SharedPreferenceType("Type");
        sharedPreference = new SharedPreference("projects");


        allSize = sharedPreferenceSize.getArrayList(getContext());
        allType = sharedPreferenceType.getArrayList(getContext());
        all_projects = sharedPreference.getArrayList(getContext());
        for (int i = 0; i < all_projects.size(); i++) {
            current_price_with_curr = all_projects.get(i).getPricesFrom().split(Pattern.quote("_"));
            prices.add(current_price_with_curr[0]);
        }
        Collections.sort(prices);
        min = prices.get(0);
        max = prices.get(prices.size() - 1);

        security = view.findViewById(R.id.check1);
        check_1Security = view.findViewById(R.id.check1);
        check_2swimbool = view.findViewById(R.id.check2);
        check_3childgame = view.findViewById(R.id.check3);
        check_4shower = view.findViewById(R.id.check4);
        check_5parking = view.findViewById(R.id.check5);
        check_6gym = view.findViewById(R.id.check6);
        check_7gardens = view.findViewById(R.id.check7);
        check_8football = view.findViewById(R.id.check8);
        check_9resturant = view.findViewById(R.id.check9);
        check_10market = view.findViewById(R.id.check10);
        check_11cenima = view.findViewById(R.id.check11);
        check_12shopping = view.findViewById(R.id.check12);

        check_Size1 = view.findViewById(R.id.size1);
        check_Size2 = view.findViewById(R.id.size2);
        check_Size3 = view.findViewById(R.id.size3);
        check_Size4 = view.findViewById(R.id.size4);
        check_Size5 = view.findViewById(R.id.size5);
        check_Size6 = view.findViewById(R.id.size6);
        check_Size7 = view.findViewById(R.id.size7);
        check_Size8 = view.findViewById(R.id.size8);


        check_Type1 = view.findViewById(R.id.type1);
        check_Type2 = view.findViewById(R.id.type2);
        check_Type3 = view.findViewById(R.id.type3);
        check_Type4 = view.findViewById(R.id.type4);
        check_Type5 = view.findViewById(R.id.type5);


        check_Size1.setText(allSize.get(0).getName());
        check_Size2.setText(allSize.get(1).getName());
        check_Size3.setText(allSize.get(2).getName());
        check_Size4.setText(allSize.get(3).getName());
        check_Size5.setText(allSize.get(4).getName());
        check_Size6.setText(allSize.get(5).getName());
        check_Size7.setText(allSize.get(6).getName());
        check_Size8.setText(allSize.get(7).getName());

        check_Type1.setText(allType.get(0).getName());
        check_Type2.setText(allType.get(1).getName());
        check_Type3.setText(allType.get(2).getName());
        check_Type4.setText(allType.get(3).getName());
        check_Type5.setText(allType.get(4).getName());


        setTyps(check_Type1, 0);
        setTyps(check_Type2, 1);
        setTyps(check_Type3, 2);
        setTyps(check_Type4, 3);
        setTyps(check_Type5, 4);


        setSize(check_Size1, 0);
        setSize(check_Size2, 1);
        setSize(check_Size3, 2);
        setSize(check_Size4, 3);
        setSize(check_Size5, 4);
        setSize(check_Size6, 5);
        setSize(check_Size7, 6);
        setSize(check_Size8, 7);


        setFilter(check_1Security, 0);
        setFilter(check_2swimbool, 1);
        setFilter(check_3childgame, 2);
        setFilter(check_4shower, 3);
        setFilter(check_5parking, 4);
        setFilter(check_6gym, 5);
        setFilter(check_7gardens, 6);
        setFilter(check_8football, 7);
        setFilter(check_9resturant, 8);
        setFilter(check_10market, 9);
        setFilter(check_11cenima, 10);
        setFilter(check_12shopping, 11);


        search = view.findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(arrayList);

               size_string = size.toString().replace(" ", "");
               type_string = type.toString().replace(" ", "");
               features_string = arrayList.toString().replace(" ", "");

             GetAllSearch service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetAllSearch.class);
                Call<List<Project>> call = service.getAllSearch(size_string, min, max, features_string, type_string);
                Log.e("URL Called", call.request().url() + "");

                call.enqueue(new Callback<List<Project>>() {
                    @Override
                    public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                        SearchProject = response.body();

                        Log.e("ddd", "dddddd: "+SearchProject );
                        if (SearchProject.size() != 0) {
                            Intent i = new Intent(getActivity(), ProjectResultActivity.class);
                            i.putExtra("projects_result", (ArrayList<Project>) SearchProject);
                            getActivity().startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), "not found result", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Project>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                        Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                        Toast.makeText(getContext(), "Nooooo", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        setRangeSeekbar6(view);
        return view;
    }

    public void fillProjects() {

    }

    private void setRangeSeekbar6(View rootView) {

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) rootView.findViewById(R.id.rangeSeekbar6);

        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin6);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax6);

        // set properties
        rangeSeekbar
                .setCornerRadius(10f)
                .setBarColor(Color.parseColor("#303F9F"))
                .setBarHighlightColor(Color.parseColor("#303F9F"))
                .setMinValue(Float.parseFloat(min))
                .setMaxValue(Float.parseFloat(max))
                .setMaxStartValue(Float.parseFloat(max))


                .setSteps(1000)
//                .setLeftThumbDrawable(R.drawable.thumb_android)
//                .setLeftThumbHighlightDrawable(R.drawable.ic_launcher_background)
//                .setRightThumbDrawable(R.drawable.thumb_android)
//                .setRightThumbHighlightDrawable(R.drawable.thumb_android_pressed)
                .setDataType(CrystalRangeSeekbar.DataType.INTEGER)
                .apply();

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                Log.e("valueChanged", "valueChanged: " + minValue);
                Log.e("valueChanged", "valueChanged: " + maxValue);


                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    public void setTyps(CheckBox checkbox, final int position) {

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type.add(allType.get(position).getName().replace(" ", "_"));

                } else {
                    for (int i = 0; i < type.size(); i++) {

                        if (type.get(i).equals(allType.get(position).getName())) {
                            type.remove(i);
                        }
                    }

                }


            }
        });


    }

    public void setFilter(CheckBox checkbox, final int position) {

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arrayList.add(position);
                } else {
                    for (int i = 0; i < arrayList.size(); i++) {

                        if (arrayList.get(i) == position) {
                            arrayList.remove(position);
//                            Log.e(TAG, "onCheckedChanged: "+ arrayList.get(position));

                        }
                    }

                }


            }
        });


    }

    public void setSize(CheckBox checkbox, final int position) {

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    size.add(allSize.get(position).getName());

                } else {
                    for (int i = 0; i < size.size(); i++) {

                        if (size.get(i).equals(allSize.get(position).getName())) {
                            size.remove(i);
                        }
                    }

                }


            }
        });


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
