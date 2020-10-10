package com.simealapps.simealape.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import androidx.recyclerview.widget.RecyclerView;

import com.simealapps.simealape.R;

@SuppressLint({"InflateParams"})
public class CollageImageAdapter extends MyRecylceAdapterBase<CollageImageAdapter.ViewHolder> implements OnClickListener {
    private Context context;
    private static final String TAG = "Adapter";
    int colorDefault;
    int colorSelected;
    CurrentCollageIndexChangedListener currentIndexlistener;
    public int[] iconList;
    boolean isPattern = false;
    PatternResIdChangedListener patternResIdListener;
    RecyclerView recylceView;
    View selectedListItem;
    public int selectedPosition;
    boolean setSelectedView = true;

    public interface CurrentCollageIndexChangedListener {
        void onIndexChanged(int i);
    }

    public interface PatternResIdChangedListener {
        void onPatternResIdChanged(int i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private static final String TAG = "ViewHolder";
        public ImageView imageView;
        private int item;


        public void setItem(int items) {
            this.item = items;
            this.imageView.setImageResource(items);
          /////  Glide.with(context).asDrawable().load(context.getDrawable(items)).into(imageView);
        }

        public ViewHolder(Context context,View itemLayoutView, boolean isPattern) {
            super(itemLayoutView);
            this.context=context;
            this.imageView = (ImageView) itemLayoutView.findViewById(R.id.image_view_collage_icon);
            if (isPattern) {
                this.imageView.setScaleType(ScaleType.CENTER_CROP);
            }
        }
    }

    public CollageImageAdapter(Context context, int[] fruitsData, CurrentCollageIndexChangedListener l, int cDefault, int cSelected, boolean isPattern2, boolean setSelectedView2) {
       this.context=context;
        this.iconList = fruitsData;
        this.currentIndexlistener = l;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern2;
        this.setSelectedView = setSelectedView2;
    }

    public CollageImageAdapter(int[] fruitsData, int cDefault, int cSelected, boolean isPattern2, boolean setSelectedView2) {
        this.iconList = fruitsData;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern2;
        this.setSelectedView = setSelectedView2;
    }

    public void setData(int[] fruitsData) {
        this.iconList = fruitsData;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder( context,itemLayoutView, this.isPattern);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItem(this.iconList[position]);
        if (this.selectedPosition == position) {
            viewHolder.itemView.setBackgroundColor(this.colorSelected);
        } else {
            viewHolder.itemView.setBackgroundColor(this.colorDefault);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView2) {
        this.recylceView = recylceView2;
    }

    public void setSelectedPositinVoid() {
        this.selectedListItem = null;
        this.selectedPosition = -1;
    }

    public void onClick(View view) {
        int position = this.recylceView.getChildPosition(view);
        RecyclerView.ViewHolder oldViewHolder = this.recylceView.findViewHolderForPosition(this.selectedPosition);
        if (oldViewHolder != null) {
            View oldView = oldViewHolder.itemView;
            if (oldView != null) {
                oldView.setBackgroundColor(this.colorDefault);
            }
        }
        if (this.selectedListItem != null) {
            Log.d(TAG, "selectedListItem " + position);
        }
        if (this.isPattern) {
            this.currentIndexlistener.onIndexChanged(this.iconList[position]);
        } else {
            this.currentIndexlistener.onIndexChanged(position);
        }
        if (this.setSelectedView) {
            this.selectedPosition = position;
            view.setBackgroundColor(this.colorSelected);
            this.selectedListItem = view;
        }
    }

    public int getItemCount() {
        return this.iconList.length;
    }
}
