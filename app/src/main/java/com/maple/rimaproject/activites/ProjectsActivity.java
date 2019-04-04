package com.maple.rimaproject.activites;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.maple.rimaproject.R;
import com.maple.rimaproject.adapters.ItemAdapter;
import com.maple.rimaproject.model.Project;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsActivity extends AppCompatActivity {

    @BindView(R.id.rv_projects)
    RecyclerView rvProjects;

    ItemAdapter itemAdapter;
    ArrayList<Project> projectsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        ButterKnife.bind(this);
        fillProjects();
//        DeliveryOrderModel deliveryOrderModel = (DeliveryOrderModel) responseObject;
        rvProjects.setLayoutManager(new GridLayoutManager(this,2));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);
        itemAdapter = new ItemAdapter(this, projectsList,false);

        rvProjects.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }

    public void fillProjects() {
        for (int i=0; i < 16; i++){
            Project project = new Project();
            project.setId(1);
            project.setName("khalid");
            project.setName("aldaboubi");
            projectsList.add(project);
        }
    }
}
