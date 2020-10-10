package com.simealapps.simealape.camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.simealapps.simealape.R;


public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {
    private Context context;

    public List<FilterInfo> filterInfos;

    public int lastSelected = 0;
    private LayoutInflater mInflater;

    public onFilterChangeListener onFilterChangeListener;


    class FilterHolder extends RecyclerView.ViewHolder {
        FrameLayout filterFavourite;
        TextView filterName;
        FrameLayout filterRoot;
        ImageView thumbImage;
        FrameLayout thumbSelected;
        View thumbSelected_bg;

        public FilterHolder(View view) {
            super(view);
        }
    }


    public interface onFilterChangeListener {
        void onFilterChanged(int i, int i2);
    }

    public FilterAdapter(Context context2) {
        this.context = context2;
        this.mInflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.filterInfos.size();
    }

    public int getItemViewType(int i) {
        return ((FilterInfo) this.filterInfos.get(i)).getFilterType();
    }

    public void onBindViewHolder(FilterHolder filterHolder, final int i) {
        try {
            if (((FilterInfo) this.filterInfos.get(i)).getFilterType() != -1) {
                filterHolder.thumbImage.setImageResource(FilterTypeHelper.FilterType2Thumb(((FilterInfo) this.filterInfos.get(i)).getFilterType()));
                if (((FilterInfo) this.filterInfos.get(i)).isSelected()) {
                    filterHolder.thumbSelected.setVisibility(0);
                } else {
                    filterHolder.thumbSelected.setVisibility(8);
                }
                if (((FilterInfo) this.filterInfos.get(i)).isFavourite()) {
                    if (i != 0) {
                        filterHolder.filterFavourite.setVisibility(0);
                        filterHolder.filterRoot.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (FilterAdapter.this.onFilterChangeListener != null && ((FilterInfo) FilterAdapter.this.filterInfos.get(i)).getFilterType() != -1 && i != FilterAdapter.this.lastSelected && !((FilterInfo) FilterAdapter.this.filterInfos.get(i)).isSelected()) {
                                    ((FilterInfo) FilterAdapter.this.filterInfos.get(FilterAdapter.this.lastSelected)).setSelected(false);
                                    ((FilterInfo) FilterAdapter.this.filterInfos.get(i)).setSelected(true);
                                    FilterAdapter.this.notifyItemChanged(FilterAdapter.this.lastSelected);
                                    FilterAdapter.this.notifyItemChanged(i);
                                    FilterAdapter.this.lastSelected = i;
                                    FilterAdapter.this.onFilterChangeListener.onFilterChanged(((FilterInfo) FilterAdapter.this.filterInfos.get(i)).getFilterType(), i);
                                }
                            }
                        });
                    }
                }
                filterHolder.filterFavourite.setVisibility(8);
                filterHolder.filterRoot.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (FilterAdapter.this.onFilterChangeListener != null && ((FilterInfo) FilterAdapter.this.filterInfos.get(i)).getFilterType() != -1 && i != FilterAdapter.this.lastSelected && !((FilterInfo) FilterAdapter.this.filterInfos.get(i)).isSelected()) {
                            ((FilterInfo) FilterAdapter.this.filterInfos.get(FilterAdapter.this.lastSelected)).setSelected(false);
                            ((FilterInfo) FilterAdapter.this.filterInfos.get(i)).setSelected(true);
                            FilterAdapter.this.notifyItemChanged(FilterAdapter.this.lastSelected);
                            FilterAdapter.this.notifyItemChanged(i);
                            FilterAdapter.this.lastSelected = i;
                            FilterAdapter.this.onFilterChangeListener.onFilterChanged(((FilterInfo) FilterAdapter.this.filterInfos.get(i)).getFilterType(), i);
                        }
                    }
                });
            }
        } catch (Exception e) {
            Context context2 = this.context;
            StringBuilder sb = new StringBuilder();
            sb.append("Exception ");
            sb.append(e);
            Toast.makeText(context2, sb.toString(), 0).show();
        }
    }

    public FilterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == -1) {
            return new FilterHolder(this.mInflater.inflate(R.layout.filter_division_layout, viewGroup, false));
        }
        View inflate = this.mInflater.inflate(R.layout.filter_item_layout, viewGroup, false);
        FilterHolder filterHolder = new FilterHolder(inflate);
        filterHolder.thumbImage = (ImageView) inflate.findViewById(R.id.filter_thumb_image);
        filterHolder.filterName = (TextView) inflate.findViewById(R.id.filter_thumb_name);
        filterHolder.filterRoot = (FrameLayout) inflate.findViewById(R.id.filter_root);
        filterHolder.thumbSelected = (FrameLayout) inflate.findViewById(R.id.filter_thumb_selected);
        filterHolder.filterFavourite = (FrameLayout) inflate.findViewById(R.id.filter_thumb_favorite_layout);
        filterHolder.thumbSelected_bg = inflate.findViewById(R.id.filter_thumb_selected_bg);
        return filterHolder;
    }

    public void setLastSelected(int i) {
        this.lastSelected = i;
    }

    public int getLastSelected() {
        return this.lastSelected;
    }

    public void setFilterInfos(List<FilterInfo> list) {
        this.filterInfos = list;
        notifyDataSetChanged();
    }

    public void setOnFilterChangeListener(onFilterChangeListener onfilterchangelistener) {
        this.onFilterChangeListener = onfilterchangelistener;
    }
}
