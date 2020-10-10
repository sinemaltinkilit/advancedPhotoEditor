package com.simealapps.simealape.camera;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.simealapps.simealape.R;


public class CollageSelectionActionAdapter extends RecyclerView.Adapter<CollageSelectionActionAdapter.MyViewHolder> {
    private final Activity context;
    int height;
    private int[] list;


    public CollageSelectionActionAdapter(Activity activity, int[] numArr) {
        this.context = activity;
        this.list = numArr;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collage_selection, parent, false);

        return new CollageSelectionActionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // holder.image.setImageDrawable(context.getDrawable(list[position]));
        Glide.with(context).load(list[position]).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout base;
        public ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            base = (LinearLayout) itemView.findViewById(R.id.base);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
