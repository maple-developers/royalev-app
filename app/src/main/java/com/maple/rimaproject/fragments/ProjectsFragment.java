package com.maple.rimaproject.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.adapters.FavoritesAdapter;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int id;
    List<Project> allProject=new ArrayList<>();
    ArrayList<Project> arr=new ArrayList<>();
    private OnFragmentInteractionListener mListener;


    @BindView(R.id.rv_projects)
    RecyclerView rvProjects;

    SharedPreference sharedPreference;

    ItemAdapter itemAdapter;
   List<Project> projectsList = new ArrayList<>();
   String details;
    public ProjectsFragment() {

    }

    public static ProjectsFragment newInstance(String param1, String param2) {
        ProjectsFragment fragment = new ProjectsFragment();
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
        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        ButterKnife.bind(this,view);
        sharedPreference = new SharedPreference("projects");
        allProject = sharedPreference.getArrayList(getContext());

        for (int i=0; i < allProject.size(); i++){
            Log.e("fkdsjgh", allProject.get(i).getArea());
        }
//        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
        rvProjects.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new ItemAdapter(getActivity(), allProject, false);
        rvProjects.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();


//        rv_projects = view.findViewById(R.id.rv_projects);
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
