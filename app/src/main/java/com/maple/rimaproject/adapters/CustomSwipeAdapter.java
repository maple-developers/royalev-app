package com.maple.rimaproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.rimaproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Hisham Snaimeh on 8/14/2017.
 */


public class CustomSwipeAdapter extends PagerAdapter {

    // khalid

    Activity activity;
    ArrayList<String> images;
    LayoutInflater inflater;


    public CustomSwipeAdapter(Activity a, ArrayList<String> i){
        activity = a;
        images = i;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager_image_row, container, false);

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
                .load(images.get(position))
                .resize(600,400)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                open_image(position);
                Toast.makeText(activity, String.valueOf(position), Toast.LENGTH_SHORT).show();
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
//        PagerAdapter adapter2 = new InfinitePagerAdapter(new CustomSwipeAdapter2(activity, images));
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

