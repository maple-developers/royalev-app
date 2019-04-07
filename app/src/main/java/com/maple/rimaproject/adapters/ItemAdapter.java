package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.R;
import com.maple.rimaproject.activites.ProjectDetailsActivity;
import com.maple.rimaproject.model.Project;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.SingleItemRowHolder> {

    private List<Project> ordersList;
    private Activity mContext;
    private boolean isHomeFragment = false;
//    private SweetAlertDialog sweetAlertDialog;


    public ItemAdapter(Activity context, List<Project> ordersList, boolean isHomeFragment) {
        this.ordersList = ordersList;
        this.mContext = context;
        this.isHomeFragment = isHomeFragment;
    }

    @Override
    public ItemAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_project2, null);
        ItemAdapter.SingleItemRowHolder mh = new ItemAdapter.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.SingleItemRowHolder holder, final int i) {
        List<String> list = new ArrayList<>();
        list.add("khalid");
        list.add("ali");
//        holder.txtTags.setTags(list);
        holder.txtTags.setTags(new String[]{"شقق", "مكاتب", "محلات", "عقارات", "مكاتب"});

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ProjectDetailsActivity.class);
                mContext.startActivity(i);
            }
        });
    }


    public void dialogOptions(Dialog d, View v){
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(v);
        d.show();
        Window window_register = d.getWindow();
        window_register.setLayout(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public int getItemCount() {
        return (null != ordersList ? ordersList.size() : 0);
    }



    public class SingleItemRowHolder extends RecyclerView.ViewHolder{
        protected CustomTextView tvAddress, tvMobile, tvPrice, tvNote;
        TagGroup txtTags;
        protected Button btnDelivered, btnNotDelivered;
        protected LinearLayout cardlinear;

        public SingleItemRowHolder(View view) {
            super(view);
            txtTags = view.findViewById(R.id.txtTags);
//            tvAddress = view.findViewById(R.id.tv_address);
//            tvAddress = view.findViewById(R.id.tv_address);
//            tvMobile = view.findViewById(R.id.tv_mobile);
//            tvPrice = view.findViewById(R.id.tv_price);
//            tvNote = view.findViewById(R.id.tv_note);
//            cardlinear = view.findViewById(R.id.cardlinear);
//            btnDelivered = view.findViewById(R.id.btnDelivered);
//            btnNotDelivered = view.findViewById(R.id.btnNotDelivered);
        }
    }

}
