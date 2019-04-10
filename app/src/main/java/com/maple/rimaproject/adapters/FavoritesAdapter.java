package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.app.Dialog;
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

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.maple.rimaproject.R;
import com.maple.rimaproject.model.Favorite;
import com.maple.rimaproject.model.Product;
import com.maple.rimaproject.model.Project;

import java.util.List;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.SingleItemRowHolder> {

    private List<Project> ordersList;
    private Activity mContext;
    private boolean isHomeFragment = false;
    SharedPreference sharedPreference;

//    private SweetAlertDialog sweetAlertDialog;


    public FavoritesAdapter(Activity context, List<Project> ordersList, boolean isHomeFragment) {
        this.ordersList = ordersList;
        this.mContext = context;
        this.isHomeFragment = isHomeFragment;
        sharedPreference=new SharedPreference();
    }

    @Override
    public FavoritesAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favorite, null);
        FavoritesAdapter.SingleItemRowHolder mh = new FavoritesAdapter.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final FavoritesAdapter.SingleItemRowHolder holder, final int i) {


        holder.likeButton.setLiked(true);
//        if (checkFavoriteItem(ordersList.get(i))) {
//            holder.likeButton.setLiked(true);
//        } else {
//            holder.likeButton.setLiked(false);
//        }

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
//                sharedPreference.addFavorite(mContext, ordersList.get(i));
                Toast.makeText(mContext, "liked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                sharedPreference.removeFavorite(mContext, ordersList.get(i));
                remove(ordersList
                        .get(i));
                Toast.makeText(mContext, "un like", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean checkFavoriteItem(Project checkProject) {
        boolean check = false;
        List<Project> favorites = sharedPreference.getFavorites(mContext);
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

    public void remove(Project project) {
        ordersList.remove(project);
        notifyDataSetChanged();
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
        protected TextView tvNumber, tvAddress, tvMobile, tvPrice, tvNote;
        protected Button btnDelivered, btnNotDelivered;
        protected LinearLayout cardlinear;
        LikeButton likeButton;

        public SingleItemRowHolder(View view) {
            super(view);
            likeButton = view.findViewById(R.id.favorite);
//            tvNumber = view.findViewById(R.id.tv_no);
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
