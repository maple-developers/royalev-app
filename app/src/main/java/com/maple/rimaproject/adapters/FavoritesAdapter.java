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

import com.maple.rimaproject.R;
import com.maple.rimaproject.model.Favorite;

import java.util.List;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.SingleItemRowHolder> {

    private List<Favorite> ordersList;
    private Activity mContext;
    private boolean isHomeFragment = false;
//    private SweetAlertDialog sweetAlertDialog;


    public FavoritesAdapter(Activity context, List<Favorite> ordersList, boolean isHomeFragment) {
        this.ordersList = ordersList;
        this.mContext = context;
        this.isHomeFragment = isHomeFragment;
    }

    @Override
    public FavoritesAdapter.SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favorite, null);
        FavoritesAdapter.SingleItemRowHolder mh = new FavoritesAdapter.SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final FavoritesAdapter.SingleItemRowHolder holder, final int i) {

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

        public SingleItemRowHolder(View view) {
            super(view);
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
