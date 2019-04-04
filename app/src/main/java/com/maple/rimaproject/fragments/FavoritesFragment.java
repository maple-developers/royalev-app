package com.maple.rimaproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.FavoritesAdapter;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.model.Favorite;
import com.maple.rimaproject.model.Project;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    FavoritesAdapter itemAdapter;
    ArrayList<Favorite> favoritesList = new ArrayList<>();


    private OnFragmentInteractionListener mListener;

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

        fillFavorites();
        rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new FavoritesAdapter(getActivity(), favoritesList,false);

        rvFavorites.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
        return view;
    }

    public void fillFavorites() {
        for (int i=0; i < 16; i++){
            Favorite favorite = new Favorite();
            favorite.setId(1);
            favorite.setName("khalid");
            favorite.setName("aldaboubi");
            favoritesList.add(favorite);
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
