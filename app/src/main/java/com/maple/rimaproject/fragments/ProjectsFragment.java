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
import com.maple.rimaproject.Retrofit.GetUser;
import com.maple.rimaproject.Retrofit.RetrofitClient;
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
        sharedPreference = new SharedPreference();
        fillProjects();
//        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
        rvProjects.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new ItemAdapter(getActivity(), arr,false);


//        rv_projects = view.findViewById(R.id.rv_projects);
        return view;
    }

    public void fillProjects() {
        GetUser service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetUser.class);

        /** Call the method with parameter in the interface to get the notice data*/


        Call<List<Project>> call = service.createUser2();

        /**Log the URL called*/
        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                //   Toasty.success(getApplicationContext(), "login", Toasty.LENGTH_SHORT, true).show();

//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allProject=response.body();


                for (int i=0;i<allProject.size();i++) {
                    for (int j = 0; j < allProject.get(i).getSliders().size(); j++) {
                        if (allProject.get(i).getSliders().get(j).getStatus().equalsIgnoreCase("primary")) {
                            id=allProject.get(i).getId();
                            details=allProject.get(i).getDetails();
                            Project project = new Project();
                            project.setId(id);
                            project.setReferenceId(allProject.get(i).getReferenceId());
                            project.setDetails(details);
                            project.setSliders(allProject.get(i).getSliders());
                            arr.add(project);
                            rvProjects.setAdapter(itemAdapter);
                            itemAdapter.notifyDataSetChanged();



//                    image = response.body().get(i).

                            Log.e("ResponseLogIn", "onResponse: " + allProject.get(i).getSliders().get(0));
                        }
                    }
                }
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



//


            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("sagtegrfte", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(getContext(), "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });
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
