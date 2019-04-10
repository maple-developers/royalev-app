package com.maple.rimaproject.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;
import com.maple.rimaproject.model.Project;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    @BindView(R.id.rv_projects)
    RecyclerView rvProjects;

    SharedPreference sharedPreference;

    ItemAdapter itemAdapter;
    ArrayList<Project> projectsList = new ArrayList<>();
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
        sharedPreference = new SharedPreference();
        fillProjects();
//        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
        rvProjects.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new ItemAdapter(getActivity(), projectsList,false);

        rvProjects.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
//        rv_projects = view.findViewById(R.id.rv_projects);
        return view;
    }

    public void fillProjects() {
        for (int i=0; i < 16; i++){
            Project project = new Project();
            project.setId(i);
            project.setName("project name "+i);
            project.setDetails("details "+i);
            projectsList.add(project);
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
