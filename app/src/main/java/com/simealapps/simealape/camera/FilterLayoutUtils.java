package com.simealapps.simealape.camera;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seu.magicfilter.display.MagicDisplay;

import java.util.ArrayList;
import java.util.List;

import com.simealapps.simealape.R;


public class FilterLayoutUtils {
    private OnClickListener btn_Favourite_listener = new OnClickListener() {
        public void onClick(View view) {
            if (FilterLayoutUtils.this.position != 0 && ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(FilterLayoutUtils.this.position)).getFilterType() != -1) {
                int filterType = ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(FilterLayoutUtils.this.position)).getFilterType();
                if (((FilterInfo) FilterLayoutUtils.this.filterInfos.get(FilterLayoutUtils.this.position)).isFavourite()) {
                    int i = 0;
                    ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(FilterLayoutUtils.this.position)).setFavourite(false);
                    FilterLayoutUtils.this.mAdapter.notifyItemChanged(FilterLayoutUtils.this.position);
                    while (true) {
                        if (i >= FilterLayoutUtils.this.favouriteFilterInfos.size()) {
                            break;
                        } else if (filterType == ((FilterInfo) FilterLayoutUtils.this.favouriteFilterInfos.get(i)).getFilterType()) {
                            FilterLayoutUtils.this.favouriteFilterInfos.remove(i);
                            int i2 = i + 1;
                            FilterLayoutUtils.this.filterInfos.remove(i2);
                            FilterLayoutUtils.this.mAdapter.notifyItemRemoved(i2);
                            FilterLayoutUtils.this.mAdapter.setLastSelected(FilterLayoutUtils.this.mAdapter.getLastSelected() - 1);
                            break;
                        } else {
                            i++;
                        }
                    }
                    FilterLayoutUtils.this.position = FilterLayoutUtils.this.position - 1;
                    FilterLayoutUtils.this.mAdapter.notifyItemRangeChanged(i + 1, (FilterLayoutUtils.this.filterInfos.size() - i) - 1);
                } else {
                    ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(FilterLayoutUtils.this.position)).setFavourite(true);
                    FilterLayoutUtils.this.mAdapter.notifyItemChanged(FilterLayoutUtils.this.position);
                    FilterInfo filterInfo = new FilterInfo();
                    filterInfo.setFilterType(filterType);
                    filterInfo.setSelected(true);
                    filterInfo.setFavourite(true);
                    FilterLayoutUtils.this.filterInfos.add(FilterLayoutUtils.this.favouriteFilterInfos.size() + 1, filterInfo);
                    FilterLayoutUtils.this.position = FilterLayoutUtils.this.position + 1;
                    FilterLayoutUtils.this.mAdapter.notifyItemInserted(FilterLayoutUtils.this.favouriteFilterInfos.size() + 1);
                    FilterLayoutUtils.this.mAdapter.notifyItemRangeChanged(FilterLayoutUtils.this.favouriteFilterInfos.size() + 1, (FilterLayoutUtils.this.filterInfos.size() - FilterLayoutUtils.this.favouriteFilterInfos.size()) - 1);
                    FilterLayoutUtils.this.favouriteFilterInfos.add(filterInfo);
                    FilterLayoutUtils.this.mAdapter.setLastSelected(FilterLayoutUtils.this.mAdapter.getLastSelected() + 1);
                }
                FilterLayoutUtils.this.saveFavourite();
            }
        }
    };

    public List<FilterInfo> favouriteFilterInfos;

    public List<FilterInfo> filterInfos;

    public FilterAdapter mAdapter;
    private Context mContext;

    public int mFilterType = 0;

    public MagicDisplay mMagicDisplay;
    private FilterAdapter.onFilterChangeListener onFilterChangeListener = new FilterAdapter.onFilterChangeListener() {
        public void onFilterChanged(int i, int i2) {
            int filterType = ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(i2)).getFilterType();
            FilterLayoutUtils.this.position = i2;
            FilterLayoutUtils.this.mMagicDisplay.setFilter(i);
            FilterLayoutUtils.this.mFilterType = i;
            if (i2 <= FilterLayoutUtils.this.favouriteFilterInfos.size()) {
                for (int size = FilterLayoutUtils.this.favouriteFilterInfos.size() + 2; size < FilterLayoutUtils.this.filterInfos.size(); size++) {
                    if (((FilterInfo) FilterLayoutUtils.this.filterInfos.get(size)).getFilterType() == filterType) {
                        ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(size)).setSelected(true);
                        FilterLayoutUtils.this.mAdapter.setLastSelected(size);
                        FilterLayoutUtils.this.position = size;
                        FilterLayoutUtils.this.mAdapter.notifyItemChanged(size);
                    } else if (((FilterInfo) FilterLayoutUtils.this.filterInfos.get(size)).isSelected()) {
                        ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(size)).setSelected(false);
                        FilterLayoutUtils.this.mAdapter.notifyItemChanged(size);
                    }
                }
            }
            for (int i3 = 1; i3 < FilterLayoutUtils.this.favouriteFilterInfos.size() + 1; i3++) {
                if (((FilterInfo) FilterLayoutUtils.this.filterInfos.get(i3)).getFilterType() == filterType) {
                    ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(i3)).setSelected(true);
                    FilterLayoutUtils.this.mAdapter.notifyItemChanged(i3);
                } else if (((FilterInfo) FilterLayoutUtils.this.filterInfos.get(i3)).isSelected()) {
                    ((FilterInfo) FilterLayoutUtils.this.filterInfos.get(i3)).setSelected(false);
                    FilterLayoutUtils.this.mAdapter.notifyItemChanged(i3);
                }
            }
        }
    };

    public int position;

    public FilterLayoutUtils(Context context, MagicDisplay magicDisplay) {
        this.mContext = context;
        this.mMagicDisplay = magicDisplay;
    }

    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        RecyclerView recyclerView = (RecyclerView) ((Activity) this.mContext).findViewById(R.id.filter_listView);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.mAdapter = new FilterAdapter(this.mContext);
        recyclerView.setAdapter(this.mAdapter);
        initFilterInfos();
        this.mAdapter.setFilterInfos(this.filterInfos);
        this.mAdapter.setOnFilterChangeListener(this.onFilterChangeListener);
    }

    public void init(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.filter_listView);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.mAdapter = new FilterAdapter(this.mContext);
        recyclerView.setAdapter(this.mAdapter);
        initFilterInfos();
        this.mAdapter.setFilterInfos(this.filterInfos);
        this.mAdapter.setOnFilterChangeListener(this.onFilterChangeListener);
    }

    private void initFilterInfos() {
        this.filterInfos = new ArrayList();
        FilterInfo filterInfo = new FilterInfo();
        filterInfo.setFilterType(0);
        filterInfo.setSelected(true);
        this.filterInfos.add(filterInfo);
        loadFavourite();
        for (int i = 0; i < this.favouriteFilterInfos.size(); i++) {
            FilterInfo filterInfo2 = new FilterInfo();
            filterInfo2.setFilterType(((FilterInfo) this.favouriteFilterInfos.get(i)).getFilterType());
            filterInfo2.setFavourite(true);
            this.filterInfos.add(filterInfo2);
        }
        FilterInfo filterInfo3 = new FilterInfo();
        filterInfo3.setFilterType(-1);
        this.filterInfos.add(filterInfo3);
        for (int i2 = 1; i2 < 41; i2++) {
            FilterInfo filterInfo4 = new FilterInfo();
            int i3 = i2 + 0;
            filterInfo4.setFilterType(i3);
            int i4 = 0;
            while (true) {
                if (i4 >= this.favouriteFilterInfos.size()) {
                    break;
                } else if (i3 == ((FilterInfo) this.favouriteFilterInfos.get(i4)).getFilterType()) {
                    filterInfo4.setFavourite(true);
                    break;
                } else {
                    i4++;
                }
            }
            this.filterInfos.add(filterInfo4);
        }
    }

    private void loadFavourite() {
        this.favouriteFilterInfos = new ArrayList();
        int i = 0;
        String[] split = ((Activity) this.mContext).getSharedPreferences("favourite_filter", 0).getString("favourite_filter_list", "").split(",");
        while (i < split.length && split[i] != "") {
            FilterInfo filterInfo = new FilterInfo();
            filterInfo.setFilterType(Integer.parseInt(split[i]));
            filterInfo.setFavourite(true);
            this.favouriteFilterInfos.add(filterInfo);
            i++;
        }
    }


    public void saveFavourite() {
        Editor edit = ((Activity) this.mContext).getSharedPreferences("favourite_filter", 0).edit();
        edit.remove("favourite_filter_list");
        edit.commit();
        String str = "";
        for (int i = 0; i < this.favouriteFilterInfos.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(((FilterInfo) this.favouriteFilterInfos.get(i)).getFilterType());
            sb.append(",");
            str = sb.toString();
        }
        edit.putString("favourite_filter_list", str);
        edit.commit();
    }

    public int getFilterType() {
        return this.mFilterType;
    }
}
