package com.maple.rimaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.adapters.SharedPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectResultActivity extends AppCompatActivity {

    List<Project> allProject = new ArrayList<>();
    ArrayList<Project> arr = new ArrayList<>();

    @BindView(R.id.rv_projects)
    RecyclerView rvProjects;

    SharedPreference sharedPreference;

    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_result);

        ButterKnife.bind(this);
        sharedPreference = new SharedPreference("projects");
        Bundle extras = getIntent().getExtras();
        allProject= (List<Project>) extras.getSerializable("projects_result");

        for (int i=0; i < allProject.size(); i++){
            Log.e("fkdsjgh", allProject.get(i).getArea());
        }
//        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new ItemAdapter(this, allProject, false);
        rvProjects.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();

    }
}
