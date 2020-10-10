package com.simealapps.simealape.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import com.simealapps.simealape.R;
import com.simealapps.simealape.adapter.MyGridAdapter;
import com.simealapps.simealape.utils.AdmobApplication;
import com.simealapps.simealape.utils.AlbumItem;
import com.simealapps.simealape.utils.GalleryImageUtility;
import com.simealapps.simealape.utils.GridViewItem;

public class SelectImageFragment extends Fragment implements OnItemClickListener {
    private AdmobApplication admobApplication;
    private RelativeLayout bannerAdContainer;
    public static int MAX_COLLAGE = 9;
    public static int MAX_SCRAPBOOK = 9;
    private static final String TAG = "GalleryActivity";
    int COLLAGE_IMAGE_LIMIT_MAX = 9;
    int COLLAGE_IMAGE_LIMIT_MIN = 0;
    Activity activity;
    MyGridAdapter adapter;
    List<AlbumItem> albumList;
    boolean collageSingleMode = false;
    Context context;
    TextView deleteAllTv;
    LinearLayout footer;
    GalleryListener galleryListener;
    Parcelable gridState;
    GridView gridView;
    TextView headerText;
    ImageView imageBack;
    boolean isOnBucket = true;
    public boolean isScrapBook = false;
    boolean isShape = false;
    TextView maxTv;
    TextView nextTv;
    OnClickListener onClickListener = new OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.imageBack) {
                SelectImageFragment.this.backtrace();
            }
            if (id == R.id.imageView_delete) {
                View parent = (View) view.getParent();
                if (parent != null && parent.getParent() != null) {
                    int location = ((ViewGroup) parent.getParent()).indexOfChild(parent);
                    SelectImageFragment.this.footer.removeView(parent);
                    SelectImageFragment.this.deleteAllTv.setText("(" + SelectImageFragment.this.footer.getChildCount() + ")");
                    long imageid = ((Long) SelectImageFragment.this.selectedImageIdList.remove(location)).longValue();
                    SelectImageFragment.this.selectedImageOrientationList.remove(location);
                    Point index = SelectImageFragment.this.findItemById(imageid);
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((AlbumItem) SelectImageFragment.this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((AlbumItem) SelectImageFragment.this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((AlbumItem) SelectImageFragment.this.albumList.get(index.x)).gridItems == SelectImageFragment.this.adapter.items && SelectImageFragment.this.gridView.getFirstVisiblePosition() <= index.y && index.y <= SelectImageFragment.this.gridView.getLastVisiblePosition() && SelectImageFragment.this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) SelectImageFragment.this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            text.setText("" + value);
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            if (!(id != R.id.gallery_delete_all || SelectImageFragment.this.footer == null || SelectImageFragment.this.footer.getChildCount() == 0)) {
                SelectImageFragment.this.removeAllTv.setVisibility(View.VISIBLE);
                SelectImageFragment.this.maxTv.setVisibility(View.INVISIBLE);
                SelectImageFragment.this.deleteAllTv.setVisibility(View.INVISIBLE);
                SelectImageFragment.this.removeAllTv.startAnimation(SelectImageFragment.this.slideInLeft);
            }
            if (id == R.id.gallery_remove_all) {
                SelectImageFragment.this.remoAll();
            }
            if (id == R.id.gallery_next) {
                SelectImageFragment.this.photosSelectFinished();
            }
        }
    };
    TextView removeAllTv;
    int selectedBucketId;
    List<Long> selectedImageIdList = new ArrayList();
    List<Integer> selectedImageOrientationList = new ArrayList();
    Animation slideInLeft;

    public interface GalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkSingleImage(long j, int i, boolean z, boolean z2);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_select_image, container, false);
        loadAds(fragmentView);
        LinearLayout linearAdsBanner = (LinearLayout) fragmentView.findViewById(R.id.linearAds);
        this.footer = (LinearLayout) fragmentView.findViewById(R.id.selected_image_linear);
        this.headerText = (TextView) fragmentView.findViewById(R.id.textView_header);
        this.imageBack = (ImageView) fragmentView.findViewById(R.id.imageBack);
        this.imageBack.setOnClickListener(this.onClickListener);
        this.maxTv = (TextView) fragmentView.findViewById(R.id.gallery_max);
        this.nextTv = (TextView) fragmentView.findViewById(R.id.gallery_next);
        this.nextTv.setOnClickListener(this.onClickListener);
        this.maxTv.setText(String.format(getString(R.string.gallery_lib_max), new Object[]{Integer.valueOf(getLimitMax())}));
        this.deleteAllTv = (TextView) fragmentView.findViewById(R.id.gallery_delete_all);
        this.removeAllTv = (TextView) fragmentView.findViewById(R.id.gallery_remove_all);
        this.slideInLeft = AnimationUtils.loadAnimation(this.context, R.anim.slide_in_left);
        this.deleteAllTv.setOnClickListener(this.onClickListener);
        this.removeAllTv.setOnClickListener(this.onClickListener);
        this.deleteAllTv.setText("(" + this.footer.getChildCount() + ")");
        return fragmentView;
    }
    private void loadAds(View view) {

        bannerAdContainer = (RelativeLayout)view.findViewById(R.id.bannerAdContainer);
        admobApplication = (AdmobApplication) activity.getApplication();
        admobApplication.initializeAdsSdk();
        admobApplication.createGoogleBannerAd(bannerAdContainer);
    }
    public void setIsShape(boolean isShape2) {
        this.isShape = isShape2;
    }


    public void remoAll() {
        if (this.footer != null) {
            this.footer.removeAllViews();
            if (this.selectedImageIdList != null && this.selectedImageIdList.size() > 0) {
                for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                    Point index = findItemById(((Long) this.selectedImageIdList.get(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((AlbumItem) this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((AlbumItem) this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((AlbumItem) this.albumList.get(index.x)).gridItems == this.adapter.items && this.gridView.getFirstVisiblePosition() <= index.y && index.y <= this.gridView.getLastVisiblePosition() && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            text.setText("" + value);
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
            if (this.selectedImageIdList != null) {
                this.selectedImageIdList.clear();
            }
            this.selectedImageOrientationList.clear();
            this.deleteAllTv.setText("(" + this.footer.getChildCount() + ")");
            getView().findViewById(R.id.gallery_remove_all).setVisibility(View.INVISIBLE);
            getView().findViewById(R.id.gallery_max).setVisibility(View.VISIBLE);
            this.deleteAllTv.setVisibility(View.VISIBLE);
        }
    }

    public int getLimitMax() {
        return this.COLLAGE_IMAGE_LIMIT_MAX;
    }

    public void setGalleryListener(GalleryListener l) {
        this.galleryListener = l;
    }



    public void setLimitMax(int max) {
        this.COLLAGE_IMAGE_LIMIT_MAX = max;
        if (this.maxTv != null) {
            this.maxTv.setText(String.format(getString(R.string.gallery_lib_max), new Object[]{Integer.valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}));
        }
    }

    public void onResume() {
        super.onResume();

        if (this.gridView != null) {
            try {
                this.gridState = this.gridView.onSaveInstanceState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logGalleryFolders();
        updateListForSelection();
        setGridAdapter();
        if (!this.isOnBucket && this.albumList != null && this.selectedBucketId >= 0 && this.selectedBucketId < this.albumList.size()) {
            this.adapter.items = ((AlbumItem) this.albumList.get(this.selectedBucketId)).gridItems;
            if (this.gridView != null) {
                this.gridView.post(new Runnable() {
                    public void run() {
                        if (SelectImageFragment.this.gridState != null) {
                            Log.d(SelectImageFragment.TAG, "trying to restore listview state..");
                            SelectImageFragment.this.gridView.onRestoreInstanceState(SelectImageFragment.this.gridState);
                        }
                    }
                });
            }
        }
        this.adapter.notifyDataSetChanged();
    }


    public void updateListForSelection() {
        if (this.selectedImageIdList != null && !this.selectedImageIdList.isEmpty()) {
            for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                Point localPoint = findItemById(((Long) this.selectedImageIdList.get(i)).longValue());
                if (localPoint != null) {
                    GridViewItem localGridViewItem = (GridViewItem) ((AlbumItem) this.albumList.get(localPoint.x)).gridItems.get(localPoint.y);
                    localGridViewItem.selectedItemCount++;
                }
            }
        }
    }

    public void setCollageSingleMode(boolean mode) {
        this.collageSingleMode = mode;
        if (mode) {
            if (this.selectedImageIdList != null) {
                for (int i = this.selectedImageIdList.size() - 1; i >= 0; i--) {
                    Point index = findItemById(((Long) this.selectedImageIdList.remove(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((AlbumItem) this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((AlbumItem) this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((AlbumItem) this.albumList.get(index.x)).gridItems == this.adapter.items && this.gridView.getFirstVisiblePosition() <= index.y && index.y <= this.gridView.getLastVisiblePosition() && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            text.setText(value);
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
            if (this.selectedImageOrientationList != null) {
                this.selectedImageOrientationList.clear();
            }
            if (this.footer != null) {
                this.footer.removeAllViews();
            }
            if (this.deleteAllTv != null) {
                this.deleteAllTv.setText("(0)");
            }
        }
    }

    public void onPause() {

        super.onPause();
    }

    public void onDestroy() {

        super.onDestroy();
    }

    public void onDestroyView() {

        super.onDestroyView();
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logGalleryFolders();
        setGridAdapter();
    }

    private void setGridAdapter() {
        this.gridView = (GridView) getView().findViewById(R.id.gridView);
        this.adapter = new MyGridAdapter(this.context, ((AlbumItem) this.albumList.get(this.albumList.size() - 1)).gridItems, this.gridView);
        this.gridView.setAdapter(this.adapter);
        this.gridView.setOnItemClickListener(this);
    }

    private List<GridViewItem> createGridItemsOnClick(int position) {
        List<GridViewItem> items = new ArrayList<>();
        AlbumItem album = (AlbumItem) this.albumList.get(position);
        List<Long> imageIdList = album.imageIdList;
        List<Integer> orientList = album.orientationList;
        for (int i = 0; i < imageIdList.size(); i++) {
            items.add(new GridViewItem(this.activity, "", "", false, ((Long) imageIdList.get(i)).longValue(), ((Integer) orientList.get(i)).intValue()));
        }
        return items;
    }

    private boolean logGalleryFolders() {
        this.albumList = new ArrayList();
        ArrayList arrayList = new ArrayList();
        Cursor cur = this.context.getApplicationContext().getContentResolver().
                query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "bucket_display_name", "bucket_id", "_id", "orientation"}
                        , null, null, "date_modified DESC");


        if (cur == null || !cur.moveToFirst()) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < this.albumList.size(); i++) {
                arrayList2.add(new GridViewItem(this.activity, ((AlbumItem) this.albumList.get(i)).name, "" + ((AlbumItem) this.albumList.get(i)).imageIdList.size(), true, ((AlbumItem) this.albumList.get(i)).imageIdForThumb, ((Integer) ((AlbumItem) this.albumList.get(i)).orientationList.get(0)).intValue()));
            }
            this.albumList.add(new AlbumItem());
            ((AlbumItem) this.albumList.get(this.albumList.size() - 1)).gridItems = arrayList2;
            for (int i2 = 0; i2 < this.albumList.size() - 1; i2++) {
                ((AlbumItem) this.albumList.get(i2)).gridItems = createGridItemsOnClick(i2);
            }
            return true;
        }
        int bucketColumn = cur.getColumnIndex("bucket_display_name");
        int bucketId = cur.getColumnIndex("bucket_id");
        int imageId = cur.getColumnIndex("_id");
        int orientationColumnIndex = cur.getColumnIndex("orientation");
        do {
            AlbumItem album = new AlbumItem();
            int id = cur.getInt(bucketId);
            album.f2076ID = id;
            if (arrayList.contains(Integer.valueOf(id))) {
                AlbumItem albumFromList = (AlbumItem) this.albumList.get(arrayList.indexOf(Integer.valueOf(album.f2076ID)));
                albumFromList.imageIdList.add(Long.valueOf(cur.getLong(imageId)));
                albumFromList.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
            } else {
                String bucket = cur.getString(bucketColumn);
                arrayList.add(Integer.valueOf(id));
                album.name = bucket;
                album.imageIdForThumb = cur.getLong(imageId);
                album.imageIdList.add(Long.valueOf(album.imageIdForThumb));
                this.albumList.add(album);
                album.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
            }
        } while (cur.moveToNext());
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < this.albumList.size(); i3++) {
            arrayList3.add(new GridViewItem(this.activity, ((AlbumItem) this.albumList.get(i3)).name, "" + ((AlbumItem) this.albumList.get(i3)).imageIdList.size(), true, ((AlbumItem) this.albumList.get(i3)).imageIdForThumb, ((Integer) ((AlbumItem) this.albumList.get(i3)).orientationList.get(0)).intValue()));
        }
        this.albumList.add(new AlbumItem());
        ((AlbumItem) this.albumList.get(this.albumList.size() - 1)).gridItems = arrayList3;
        for (int i4 = 0; i4 < this.albumList.size() - 1; i4++) {
            ((AlbumItem) this.albumList.get(i4)).gridItems = createGridItemsOnClick(i4);
        }
        return true;
    }

    public boolean onBackPressed() {
        return backtrace();
    }


    public boolean backtrace() {
        if (this.isOnBucket) {
            if (this.galleryListener != null) {
                this.galleryListener.onGalleryCancel();
            }
            return true;
        }
        this.gridView.setNumColumns(2);
        this.adapter.items = ((AlbumItem) this.albumList.get(this.albumList.size() - 1)).gridItems;
        this.adapter.notifyDataSetChanged();
        this.gridView.smoothScrollToPosition(0);
        this.isOnBucket = true;
        this.headerText.setText(getString(R.string.gallery_select_an_album));
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View arg1, int location, long arg3) {
        if (this.isOnBucket) {
            this.gridView.setNumColumns(3);
            this.adapter.items = ((AlbumItem) this.albumList.get(location)).gridItems;
            this.adapter.notifyDataSetChanged();
            this.gridView.smoothScrollToPosition(0);
            this.isOnBucket = false;
            this.selectedBucketId = location;
            this.headerText.setText(((AlbumItem) this.albumList.get(location)).name);
        } else if (this.footer.getChildCount() >= this.COLLAGE_IMAGE_LIMIT_MAX) {
            Toast msg = Toast.makeText(this.context, String.format(getString(R.string.gallery_no_more), new Object[]{Integer.valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}), 1);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        } else {
            View retval = LayoutInflater.from(this.context).inflate(R.layout.footer_item, null);
            retval.findViewById(R.id.imageView_delete).setOnClickListener(this.onClickListener);
            ImageView im = (ImageView) retval.findViewById(R.id.imageView);
            if (this.selectedBucketId >= 0 && this.selectedBucketId < this.albumList.size() && location >= 0 && location < ((AlbumItem) this.albumList.get(this.selectedBucketId)).imageIdList.size()) {
                long id = ((Long) ((AlbumItem) this.albumList.get(this.selectedBucketId)).imageIdList.get(location)).longValue();
                this.selectedImageIdList.add(Long.valueOf(id));
                this.selectedImageOrientationList.add(((AlbumItem) this.albumList.get(this.selectedBucketId)).orientationList.get(location));
                Bitmap bm = GalleryImageUtility.getThumbnailBitmap(this.context, id, ((Integer) ((AlbumItem) this.albumList.get(this.selectedBucketId)).orientationList.get(location)).intValue());
                if (bm != null) {
                    im.setImageBitmap(bm);
                }
                this.footer.addView(retval);
                this.deleteAllTv.setText("(" + this.footer.getChildCount() + ")");
                GridViewItem gridViewItem = (GridViewItem) this.adapter.items.get(location);
                gridViewItem.selectedItemCount++;
                TextView text = (TextView) arg1.findViewById(R.id.textViewSelectedItemCount);
                text.setText("" + ((GridViewItem) this.adapter.items.get(location)).selectedItemCount);
                if (text.getVisibility() == 4) {
                    text.setVisibility(View.VISIBLE);
                }
                if (this.collageSingleMode) {
                    photosSelectFinished();
                    this.collageSingleMode = false;
                }
            }
        }
    }

    public Point findItemById(long id) {
        for (int i = 0; i < this.albumList.size() - 1; i++) {
            List<GridViewItem> list = ((AlbumItem) this.albumList.get(i)).gridItems;
            for (int j = 0; j < list.size(); j++) {
                if (( list.get(j)).imageIdForThumb == id) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public int getLimitMin() {
        return this.COLLAGE_IMAGE_LIMIT_MIN;
    }


    public void photosSelectFinished() {
        int size = this.selectedImageIdList.size();
        if (size <= this.COLLAGE_IMAGE_LIMIT_MIN) {
            Toast msg = Toast.makeText(this.context, String.format(getString(R.string.gallery_select_one), new Object[]{Integer.valueOf(getLimitMin() + 1)}), 1);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
            return;
        }
        long[] arrr = new long[size];
        for (int i = 0; i < size; i++) {
            arrr[i] = ((Long) this.selectedImageIdList.get(i)).longValue();
        }
        int[] orientationArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            orientationArr[i2] = (this.selectedImageOrientationList.get(i2)).intValue();
        }
        if (this.galleryListener != null) {
            this.galleryListener.onGalleryOkImageArray(arrr, orientationArr, this.isScrapBook, this.isShape);
            return;
        }
        try {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
