//package com.maple.rimaproject.adapters;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.AppCompatCheckBox;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.maple.rimaproject.R;
//
//import java.util.HashMap;
//import java.util.List;
//
//public class Typeadapter extends RecyclerView.Adapter<Typeadapter.SingleItemRowHolder> {
//
//
//  private List<String> mData;
//  private HashMap<String, Boolean> mChecked;
//  private Context mContext;
//
//  public Typeadapter() {
//  }
//
//  @NonNull
//  @Override
//  public Typeadapter.SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.check_recyclerview_item, null);
//    Typeadapter.SingleItemRowHolder mh = new Typeadapter.SingleItemRowHolder(v);
//    return mh;
//
//
//  }
//
//  @Override
//  public void onBindViewHolder(@NonNull final Typeadapter.SingleItemRowHolder singleItemRowHolder, int i) {
//    singleItemRowHolder.mText.setText(mData.get(i));
//    singleItemRowHolder.mCheckBox.setOnCheckedChangeListener(null);
//    if(mChecked.containsKey(mData.get(i))) {
//      singleItemRowHolder.mCheckBox.setChecked(mChecked.get(mData.get(i)));
//    }
//    else {
//      singleItemRowHolder.mCheckBox.setChecked(false);
//    }
//    singleItemRowHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//      @Override
//      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        mChecked.put(mData.get(singleItemRowHolder.getAdapterPosition()), isChecked);
//        Toast.makeText(mContext, "Status is: " + isChecked + "", Toast.LENGTH_SHORT).show();
//      }
//    });
//
//  }
//
//  @Override
//  public int getItemCount() {
//    return mData.size();
//  }
//
//  public Typeadapter(Context mContext, List<String> mData) {
//    this.mContext = mContext;
//    this.mData = mData;
//    this.mChecked = new HashMap<>();
//  }
//
//
//
//  public class SingleItemRowHolder extends RecyclerView.ViewHolder {
//
//    private TextView mText;
//    private AppCompatCheckBox mCheckBox;
//
//    public SingleItemRowHolder(View itemView) {
//      super(itemView);
//
//      mText = (TextView) itemView.findViewById(R.id.item_text);
//      mCheckBox = (AppCompatCheckBox) itemView.findViewById(R.id.item_checkbox);
//
//    }
//  }
//
//}
