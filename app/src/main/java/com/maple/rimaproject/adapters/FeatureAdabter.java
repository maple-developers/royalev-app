package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.like.LikeButton;
import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.activites.ProjectDetailsActivity;
import com.maple.rimaproject.model.Feature;
import com.maple.rimaproject.model.featureApi;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

public class FeatureAdabter extends RecyclerView.Adapter<FeatureAdabter.SingleItemRowHolder> {
    private List<String> ordersList;
    private List<Integer> Image;
    private Activity mContext;
    private boolean isHomeFragment = false;
SharedPreference sharedPreference;
    public FeatureAdabter(Activity context, List<String> ordersList, boolean isHomeFragment) {
        this.isHomeFragment=isHomeFragment;
        this.mContext=context;
       // this.Image=image;
        this.ordersList=ordersList;
        sharedPreference = new SharedPreference("favorites");

    }




    @NonNull
    @Override
    public FeatureAdabter.SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.features_row, null);
        FeatureAdabter.SingleItemRowHolder mh = new FeatureAdabter.SingleItemRowHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemRowHolder holder, int i) {
     //   holder.featureimages.setBackgroundResource(Image.get(i));
        holder.featurename.setText(ordersList.get(i));
        Log.e("asdasdsad", "onBindViewHolder: "+ordersList.get(i) );
//        Log.e("asdasdsad", "onBindViewHolder: "+ordersList.get(i).getImage() );
    }


    @Override
    public int getItemCount() {
        return (null != ordersList ? ordersList.size() : 0);
    }
    public class SingleItemRowHolder extends RecyclerView.ViewHolder{
ImageView featureimages;
TextView featurename;


        public SingleItemRowHolder(View view) {
            super(view);
//featureimages=view.findViewById(R.id.featuresimage);
            featurename=view.findViewById(R.id.featuresname);
//            tvPrice = view.findViewById(R.id.tv_price);
//            tvNote = view.findViewById(R.id.tv_note);
//            cardlinear = view.findViewById(R.id.cardlinear);
//            btnDelivered = view.findViewById(R.id.btnDelivered);
//            btnNotDelivered = view.findViewById(R.id.btnNotDelivered);
        }
    }

}
