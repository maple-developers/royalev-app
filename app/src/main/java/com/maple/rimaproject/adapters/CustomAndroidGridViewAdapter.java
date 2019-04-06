package com.maple.rimaproject.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.rimaproject.R;

/**
 * Created by HP on 5/11/2016.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {
  private Context mContext;
  private final String[] string;
  private final int[] Imageid;

  public CustomAndroidGridViewAdapter(Context c,String[] string,int[] Imageid ) {
    mContext = c;
    this.Imageid = Imageid;
    this.string = string;
  }

  @Override
  public int getCount() {
    return string.length;
  }

  @Override
  public Object getItem(int p) {
    return null;
  }

  @Override
  public long getItemId(int p) {
    return 0;
  }

  @Override
  public View getView(int p, View convertView, ViewGroup parent) {
    View grid;
    LayoutInflater inflater = (LayoutInflater) mContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    if (convertView == null) {

      grid = new View(mContext);
      grid = inflater.inflate(R.layout.gridview_custom_layout, null);
      TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
      CheckBox checkBox = (CheckBox)grid.findViewById(R.id.check);
      textView.setText(string[p]);
      checkBox.setTag(Imageid[p]);
      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked){
            Toast.makeText(mContext, "Cheeckeeed", Toast.LENGTH_SHORT).show();
          }
          else{
            Toast.makeText(mContext, "Not", Toast.LENGTH_SHORT).show();

          }
        }
      });

    } else {
      grid = (View) convertView;
    }

    return grid;
  }
}
