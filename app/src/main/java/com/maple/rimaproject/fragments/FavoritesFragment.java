package com.maple.rimaproject.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.adapters.FavoritesAdapter;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    ItemAdapter itemAdapter;
    List<Project> favoritesList = new ArrayList<>();
    SharedPreference sharedPreference;

    ArrayList<Project> projectsList = new ArrayList<>();
    public FavoritesFragment() {
        // Required empty public constructor
    }


    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this,view);
        sharedPreference = new SharedPreference("favorites");
        favoritesList = sharedPreference.getArrayList(getActivity());


        if (favoritesList == null) {
           // Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
        } else {

            if (favoritesList.size() == 0) {
               // Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
            }

            if (favoritesList != null) {
                itemAdapter = new ItemAdapter(getActivity(), favoritesList,false);
            }
                rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
                itemAdapter = new ItemAdapter(getActivity(), favoritesList, false);
                rvFavorites.setAdapter(itemAdapter);
                itemAdapter.notifyDataSetChanged();

            }





        return view;
    }

//    public void fillFavorites() {
//        for (int i=0; i < 16; i++){
//            Favorite favorite = new Favorite();
//            favorite.setId(1);
//            favorite.setName("khalid");
//            favorite.setName("aldaboubi");
//            favoritesList.add(favorite);
//        }
//    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
