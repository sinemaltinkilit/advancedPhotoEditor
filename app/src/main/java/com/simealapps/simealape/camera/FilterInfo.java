package com.simealapps.simealape.camera;


public class FilterInfo {
    private boolean bFavourite = false;
    private boolean bSelected = false;
    private int filterType;

    public void setFilterType(int i) {
        this.filterType = i;
    }

    public int getFilterType() {
        return this.filterType;
    }

    public boolean isSelected() {
        return this.bSelected;
    }

    public void setSelected(boolean z) {
        this.bSelected = z;
    }

    public boolean isFavourite() {
        return this.bFavourite;
    }

    public void setFavourite(boolean z) {
        this.bFavourite = z;
    }
}
