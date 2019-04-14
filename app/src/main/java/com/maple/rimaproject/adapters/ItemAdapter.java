package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.maple.rimaproject.Font.CustomTextView;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.activites.ProjectDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.SingleItemRowHolder> {

    private List<Project> ordersList;
    private Activity mContext;
    private boolean isHomeFragment = false;
    SharedPreference sharedPreference;
//    private SweetAlertDialog sweetAlertDialog;


    public ItemAdapter(Activity context, List<Project> ordersList, boolean isHomeFragment) {
        this.ordersList = ordersList;
        this.mContext = context;
        this.isHomeFragment = isHomeFragment;
        sharedPreference = new SharedPreference("favorites");
    }

    @Override
    public ItemAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_project2, null);
        ItemAdapter.SingleItemRowHolder mh = new ItemAdapter.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.SingleItemRowHolder holder, final int i) {



//        holder.txtTags.setTags(list);
        holder.txtTags.setTags(new String[]{"شقق", "مكاتب", "محلات", "عقارات", "مكاتب"});

        String strOut = ordersList.get(i).getDetails();
        if (strOut.length() > 89){
            String result = strOut.substring(0, 90) + " ... ";
            holder.tvDetails.setText(result);
        } else {
            holder.tvDetails.setText(strOut);
        }

        holder.tvprojectName.setText(ordersList.get(i).getReferenceId());
       Picasso.get()
                .load(ordersList.get(i).getSliders().get(0).getPhotoPath())
                .resize(600,400)
                .into(holder.ImageProject);

        if (checkFavoriteItem(ordersList.get(i))) {
            holder.likeButton.setLiked(true);
            Log.e("ssss","true");
        } else {
            holder.likeButton.setLiked(false);
            Log.e("ssss","fasle");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(mContext, ProjectDetailsActivity.class);

                is.putExtra("id",ordersList.get(i).getId());
                is.putExtra("referenceId",ordersList.get(i).getReferenceId());
                is.putExtra("type",ordersList.get(i).getTypes());
                is.putExtra("size",ordersList.get(i).getSizes());
                is.putExtra("lat",ordersList.get(i).getLatitude());
                is.putExtra("longi",ordersList.get(i).getLongitude());
                is.putExtra("info",ordersList.get(i).getDetails());
                is.putExtra("slider", (ArrayList<Project.Slider>) ordersList.get(i).getSliders());
                is.putExtra("price",ordersList.get(i).getPricesFrom());
                is.putExtra("plan1",ordersList.get(i).getPlan1());
                is.putExtra("area",ordersList.get(i).getArea());
                for (int j=0;j<ordersList.get(i).getSliders().size();j++){

                    Log.e("ssslllss", "onClick: "+ordersList.get(i).getSliders().get(j).getPhotoPath() );
                }
                is.putExtra("features",ordersList.get(i).getFeatures());
                is.putExtra("location",ordersList.get(i).getLocation());
                is.putExtra("status",ordersList.get(i).getStatus());
                mContext.startActivity(is);
            }
        });

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                sharedPreference.addArrayList(mContext, ordersList.get(i));
                Toast.makeText(mContext, "liked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                sharedPreference.removeArrayList(mContext, ordersList.get(i));
                Toast.makeText(mContext, "un like", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean checkFavoriteItem(Project checkProject) {
        boolean check = false;
        List<Project> favorites = sharedPreference.getArrayList(mContext);
        if (favorites != null) {
            for (Project project : favorites) {
                if (project.equals(checkProject)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    @Override
    public int getItemCount() {
        return (null != ordersList ? ordersList.size() : 0);
    }



    public class SingleItemRowHolder extends RecyclerView.ViewHolder{
        protected CustomTextView tvAddress,  tvPrice, tvNote,tvprojectName,tvDetails;
        TagGroup txtTags;
        ImageView ImageProject;
        protected Button btnDelivered, btnNotDelivered;
        protected LinearLayout cardlinear;

        LikeButton likeButton;

        public SingleItemRowHolder(View view) {
            super(view);
            txtTags = view.findViewById(R.id.txtTags);
            likeButton = view.findViewById(R.id.favorite);
            tvprojectName = view.findViewById(R.id.txtProjectName);
            tvDetails = view.findViewById(R.id.txtProjectBrief);
            ImageProject = view.findViewById(R.id.imgProject);
//            tvPrice = view.findViewById(R.id.tv_price);
//            tvNote = view.findViewById(R.id.tv_note);
//            cardlinear = view.findViewById(R.id.cardlinear);
//            btnDelivered = view.findViewById(R.id.btnDelivered);
//            btnNotDelivered = view.findViewById(R.id.btnNotDelivered);
        }
    }

}
