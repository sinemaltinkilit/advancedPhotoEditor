package com.simealapps.simealape.utils;

import android.app.Activity;
import android.graphics.Bitmap;

public class GridViewItem {
    Activity context;
    public String count;
    private String folderName;
    public long imageIdForThumb;
    private boolean isDirectory;
    int orientation;
    public int selectedItemCount = 0;

    public GridViewItem(Activity context2, String path, String count2, boolean isDirectory2, long imageId, int orientation2) {
        this.folderName = path;
        this.isDirectory = isDirectory2;
        this.count = count2;
        this.context = context2;
        this.imageIdForThumb = imageId;
        this.orientation = orientation2;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public Bitmap getImage() {
        return GalleryImageUtility.getThumbnailBitmap(this.context, this.imageIdForThumb, this.orientation);
    }
}
