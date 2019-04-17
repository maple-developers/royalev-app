package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.maple.recyclebannar.layoutmanager.BannerLayout;
import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by test on 2017/11/22.
 */


public class WebBannerAdapterDetails extends RecyclerView.Adapter<WebBannerAdapterDetails.MzViewHolder> {
    String url;
    private Activity activity;
    private List<String> urlList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public WebBannerAdapterDetails(Activity activity, List<String> urlList) {
        this.activity = activity;
        this.urlList = urlList;
    }

    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    @Override
    public WebBannerAdapterDetails.MzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MzViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(WebBannerAdapterDetails.MzViewHolder holder, final int position) {
        if (urlList == null || urlList.isEmpty())
            return;
        final int P = position % urlList.size();

      //  List<Project.Slider> images = urlList.get(position).getSliders();
      //  for (int i=0;i<urlList.size();i++){


       // url= urlList.get(P).getSliders().get(i).getPhotoPath();

       // }
        ImageView img = (ImageView) holder.imageView;

        Picasso.get()
                .load(urlList.get(position))
                .resize(600,400)
                .into(img);
       // Glide.with(context).load(url).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    open_image(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (urlList != null) {
           return urlList.size();
        }
       return 0;
    }


    class MzViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MzViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
    public void open_image(int p){
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_slider_pager, null);

        ImageView close = (ImageView) v.findViewById(R.id.close_dialog);
        ViewPager imageSlideFull = (ViewPager) v.findViewById(R.id.imageSlideFull);

        PagerAdapter adapter2 = new InfinitePagerAdapter(new CustomSwipeAdapter2(activity, urlList));
        imageSlideFull.setAdapter(adapter2);
        imageSlideFull.setCurrentItem(p);
        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(v);
        dialog.show();
        Window window_register = dialog.getWindow();
        window_register.setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);

    }

}
