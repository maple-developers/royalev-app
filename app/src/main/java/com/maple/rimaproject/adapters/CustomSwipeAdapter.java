package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.activites.ProjectDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hisham Snaimeh on 8/14/2017.
 */


public class CustomSwipeAdapter extends PagerAdapter {

    // khalid

    Activity activity;
    List<Project> projects;
    LayoutInflater inflater;


    public CustomSwipeAdapter(Activity a, List<Project> projects){
        activity = a;
        this.projects = projects;
    }
    @Override
    public int getCount() {
        return projects.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_image, container, false);

        List<Project.Slider> images = projects.get(position).getSliders();
        ImageView imageView;
        imageView = (ImageView) view.findViewById(R.id.imgsliderrow);

//        imageView.setImageAlpha(80);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height = dis.heightPixels;
        int width = dis.widthPixels;
        imageView.setMinimumHeight(height);
        imageView.setMinimumWidth(width);

        Picasso.get()
                .load(images.get(0).getPhotoPath())
                .resize(600,400)
                .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent is = new Intent(activity, ProjectDetailsActivity.class);
                    is.putExtra("id",projects.get(position).getId());
                    is.putExtra("referenceId",projects.get(position).getReferenceId());
                    is.putExtra("type",projects.get(position).getTypes());
                    is.putExtra("size",projects.get(position).getSizes());
                    is.putExtra("lat",projects.get(position).getLatitude());
                    is.putExtra("longi",projects.get(position).getLongitude());
                    is.putExtra("info",projects.get(position).getDetails());
                    is.putExtra("slider", (ArrayList<Project.Slider>) projects.get(position).getSliders());
                    is.putExtra("price",projects.get(position).getPricesFrom());
                    is.putExtra("plan1",projects.get(position).getPlan1());
                    is.putExtra("plan2",projects.get(position).getPlan2());
                    is.putExtra("area",projects.get(position).getArea());
                    for (int j=0;j<projects.get(position).getSliders().size();j++){

                        Log.e("ssslllss", "onClick: "+projects.get(position).getSliders().get(j).getPhotoPath() );
                    }
                    is.putExtra("features",projects.get(position).getFeatures());
                    is.putExtra("location",projects.get(position).getLocation());
                    is.putExtra("status",projects.get(position).getStatus());
                    activity.startActivity(is);
            }
        });


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View)object);
    }

//    public void open_image(int p){
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View v = inflater.inflate(R.layout.dialog_slider_pager, null);
//
//        ImageView close = (ImageView) v.findViewById(R.id.close_dialog);
//        ViewPager imageSlideFull = (ViewPager) v.findViewById(R.id.imageSlideFull);
//
//        PagerAdapter adapter2 = new InfinitePagerAdapter(new CustomSwipeAdapter2(activity, projects.get(p).getSliders()));
//        imageSlideFull.setAdapter(adapter2);
//        imageSlideFull.setCurrentItem(p);
//        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
//
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setContentView(v);
//        dialog.show();
//        Window window_register = dialog.getWindow();
//        window_register.setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
//
//    }
}



//private int [] img_resources={R.drawable.mac,R.drawable.mac};
//    private Context ctx;
//    private LayoutInflater layoutInflater;
//
//
//
//    CustomSwipeAdapter(Context ctx)
//    {
//        this.ctx=ctx;
//    }
//
//
//    @Override
//    public int getCount() {
//        return img_resources.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object obj) {
//        return (view==(LinearLayout)obj);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
//        ImageView img=(ImageView) item_view.findViewById(R.id.imageView1);
//        img.setImageResource(img_resources[position]);
//
//        container.addView(item_view);
//
//
//        return item_view;
//    }
//
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((LinearLayout)object);
//
//
//        super.destroyItem(container, position, object);
//    }

