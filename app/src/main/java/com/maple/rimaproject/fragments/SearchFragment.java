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

import static java.sql.DriverManager.println;


public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    ArrayList <Integer> arrayList=new ArrayList<>();

    List<String> type=new ArrayList<>();
    List<String> size=new ArrayList<>();


    List<Project> SearchProject=new ArrayList<>();
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
Button search;
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



List<searchSizeApi> allSize=new ArrayList<>();
    List<TypeModel> allType=new ArrayList<>();
    List<Project> all_projects=new ArrayList<>();
    List<String> prices=new ArrayList<>();

SharedPreferenceSize sharedPreferenceSize;
SharedPreferenceType sharedPreferenceType;
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
    String min;
    String max;

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
        sharedPreferenceSize=new SharedPreferenceSize("Size");
        sharedPreferenceType=new SharedPreferenceType("Type");
        sharedPreference=new SharedPreference("projects");


        allSize=sharedPreferenceSize.getArrayList(getContext());

        for (int i=0;i < allSize.size();i++){

            Log.e("ccccccccc", "ccccccccc: "+allSize.get(i).getName() );
        }
        allType=sharedPreferenceType.getArrayList(getContext());

        for (int i=0;i < allType.size();i++){

            Log.e("allType", "allType: "+allType.get(i).getName() );
        }


        all_projects=sharedPreference.getArrayList(getContext());
        for (int i=0;i<all_projects.size();i++){

            Log.e("prices", ": "+all_projects.get(i).getPricesFrom());

            String[] current_price_with_curr = all_projects.get(i).getPricesFrom().split(Pattern.quote("_"));
            prices.add(current_price_with_curr[0]);
        }

        for (int k = 0; k < prices.size(); k++) {
            Collections.sort(prices);
            Log.e("sdfhsjk", prices.get(k).toString());
        }

        Log.e("sdfhsjksss", prices.get(0).toString());
        Log.e("sdfhsjksss", prices.get(prices.size()-1).toString());

     min=prices.get(0);
    max=prices.get(prices.size()-1);

//        gridView = (GridView) view.findViewById(R.id.grid);
//        gridView.setAdapter(new CustomAndroidGridViewAdapter(getActivity(), gridViewStrings, gridViewImages));


        //initInstances(view);
//        fixed_rangeview = view.findViewById(R.id.fixed_rangeview);
//        rangeBar = view.findViewById(R.id.range);


//        badRoom = view.findViewById(R.id.badroom);
//        bathRoomText = view.findViewById(R.id.bathroom);
//        livingRoom = view.findViewById(R.id.livingroom);
//        KitchenRoom = view.findViewById(R.id.kitchen);
//        plus1 = view.findViewById(R.id.btnPlus1);
//        plus2 = view.findViewById(R.id.btnPlus2);
//        plus3 = view.findViewById(R.id.btnPlus3);
//        plus4 = view.findViewById(R.id.btnPlus4);
//        minus1 = view.findViewById(R.id.btnMinus1);
//        minus2 = view.findViewById(R.id.btnMinus2);
//        minus3 = view.findViewById(R.id.btnMinus3);
//        minus4 = view.findViewById(R.id.btnMinus4);
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

        check_Size1=view.findViewById(R.id.size1);
        check_Size2=view.findViewById(R.id.size2);
        check_Size3=view.findViewById(R.id.size3);
        check_Size4=view.findViewById(R.id.size4);
        check_Size5=view.findViewById(R.id.size5);
        check_Size6=view.findViewById(R.id.size6);
        check_Size7=view.findViewById(R.id.size7);
        check_Size8=view.findViewById(R.id.size8);
        check_Type1=view.findViewById(R.id.type1);
        check_Type2=view.findViewById(R.id.type2);
        check_Type3=view.findViewById(R.id.type3);
        check_Type4=view.findViewById(R.id.type4);
        check_Type5=view.findViewById(R.id.type5);




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



        setTyps(check_Type1,0);
        setTyps(check_Type2,1);
        setTyps(check_Type3,2);
        setTyps(check_Type4,3);
        setTyps(check_Type5,4);


        setSize(check_Size1,0);
        setSize(check_Size2,1);
        setSize(check_Size3,2);
        setSize(check_Size4,3);
        setSize(check_Size5,4);
        setSize(check_Size6,5);
        setSize(check_Size7,6);
        setSize(check_Size8,7);




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
                Log.e("arrayList", "arrayList: "+ arrayList+"\n"+rangStart+"\n"+"rangEnd="+rangEnd);

               for (int i=0;i<type.size();i++){

                   Log.e("arrayList2", "arrayList2: "+ type.get(i));

               }
                for (int i=0;i<size.size();i++){

                    Log.e("arrayList2", "arrayList3333: "+ size.get(i));

                }



                Log.e("kjhkjhkjhkjhj", size.toString().replace(" ",""));
                Log.e("kjhkjhkjhkjhj2", type.toString().replace(" ",""));
                Log.e("kjhkjhkjhkjhj3", arrayList.toString().replace(" ",""));
                Log.e("kjhkjhkjhkjhj4", min+max);


                String size_string = size.toString().replace(" ","");
                String type_string = type.toString().replace(" ","");
                String features_string = arrayList.toString().replace(" ","");


                GetAllSearch service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetAllSearch.class);
                Call<List<Project>> call = service.getAllSearch(size_string,min,max,features_string,type_string);
                Log.e("URL Called", call.request().url() + "");

                call.enqueue(new Callback<List<Project>>() {
                    @Override
                    public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                        SearchProject = response.body();

                        for (int i=0;i<SearchProject.size();i++){

                            Log.e("Seaaaacr", "Seaaaacr: "+SearchProject.get(i).getArea() );
                        }


                        if (SearchProject != null){
                            Intent i = new Intent(getActivity(), ProjectResultActivity.class);
                            i.putExtra("projects_result", (ArrayList<Project>) SearchProject);
                            getActivity().startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), "not found result", Toast.LENGTH_SHORT).show();
                        }
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
                    public void onFailure(Call<List<Project>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                        Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                        Toast.makeText(getContext(), "Nooooo", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




//        plus1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                countbad++;
//
//                badRoom.setText(String.valueOf(countbad));
//            }
//        });
//        plus2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bathroom++;
//                bathRoomText.setText(String.valueOf(bathroom));
//            }
//        });
//        plus3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                living++;
//                livingRoom.setText(String.valueOf(living));
//            }
//        });
//        plus4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                kitchen++;
//                KitchenRoom.setText(String.valueOf(kitchen));
//            }
//        });
//        minus1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (countbad > 0) {
//                    countbad--;
//
//                    badRoom.setText(String.valueOf(countbad));}
//            }
//        });
//        minus2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bathroom > 0) {
//                    bathroom--;
//
//                    bathRoomText.setText(String.valueOf(bathroom));}
//            }
//        });
//        minus3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (living > 0) {
//                    living--;
//
//                    livingRoom.setText(String.valueOf(living));}
//            }
//        });
//        minus4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (kitchen > 0) {
//                    kitchen--;
//
//                    KitchenRoom.setText(String.valueOf(kitchen));
//                }
//            }
//        });

        setRangeSeekbar6(view);
        return view;
    }
    public void fillProjects() {

    }

    private void setRangeSeekbar6(View rootView){

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

                Log.e("valueChanged", "valueChanged: "+minValue );
                Log.e("valueChanged", "valueChanged: "+maxValue );


                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
    }

    public void setTyps(CheckBox checkbox, final int position){

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    type.add(allType.get(position).getName().replace(" ","_"));

                }else
                {
                    for (int i=0 ; i<type.size();i++){

                        if (type.get(i).equals(allType.get(position).getName())){
                            type.remove(i);
                        }
                    }

                }



            }
        });


    }
    public void setSize(CheckBox checkbox, final int position){

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    size.add(allSize.get(position).getName());

                }else
                {
                    for (int i=0 ; i<size.size();i++){

                        if (size.get(i).equals(allSize.get(position).getName())){
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
