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

public class RecycleType extends AppCompatActivity {

    SharedPreference sharedPreference;
    List<Project> allData=new ArrayList<>();
    List<Project> filteredList = new ArrayList<>();
    String type;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_type);
        sharedPreference=new  SharedPreference("projects");
        allData=sharedPreference.getArrayList(this);
        Bundle extras = getIntent().getExtras();
        type=extras.getString("typeofroyal");
        for(int i=0; i < allData.size(); i++){
            if (allData.get(i).getTypes().contains(type)){
                filteredList.add(allData.get(i));
            }
        }

        recyclerView=findViewById(R.id.rec1);


        Log.e("dfgfdg", type);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.divider);

            itemAdapter = new ItemAdapter(this, filteredList, false);
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();



    }
}
