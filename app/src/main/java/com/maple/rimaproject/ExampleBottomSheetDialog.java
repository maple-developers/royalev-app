package com.maple.rimaproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;


import com.maple.rimaproject.adapters.CustomAndroidGridViewAdapter;

import java.util.ArrayList;



public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    ArrayList arrayList;

    public static String[] gridViewStrings = {
            "mohamed",
            "mohamed",
            "mohamed",
            "mohamed",
            "mohamed",
            "mohamed mohamed",
            "mohamed",
            "mohamed",
            "mohamed",

    };
    public static int[] gridViewImages = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };

    private BottomSheetListener mListener;
    LinearLayout allCountent;
    boolean isgone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buttomsheetcontent, container, false);
        ImageView more=v.findViewById(R.id.seemore);

        Button button1 = v.findViewById(R.id.btn_search);
        allCountent=v.findViewById(R.id.linearr2);
        allCountent.setVisibility(View.GONE);
        gridView = (GridView) v.findViewById(R.id.grid);
        gridView.setAdapter(new CustomAndroidGridViewAdapter(getActivity(), gridViewStrings, gridViewImages));


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isgone==true) {
                    isgone = false;
                    allCountent.setVisibility(View.GONE);
                }
                else {
                    isgone = true;
                    allCountent.setVisibility(View.VISIBLE);


                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 1 clicked");
                dismiss();
            }
        });


        return v;
    }


    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BottomSheetListener) {
            mListener = (BottomSheetListener) context;
        } else {
            Log.e("onAttach", "onAttach: "+"ERROR" );
        }

    }
}