package com.simealapps.simealape.utils;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.InterstitialAd;


import com.simealapps.simealape.activities.CreateCollageActivity;
import com.simealapps.simealape.fragments.SelectImageFragment;


public class CollageHelper {
    protected static final String TAG = "CollageHelper";

    public static SelectImageFragment getGalleryFragment(FragmentActivity activity) {
        return (SelectImageFragment) activity.getSupportFragmentManager().findFragmentByTag("myFragmentTag");
    }

    public static SelectImageFragment addGalleryFragment(FragmentActivity activity, int gallery_fragment_container, InterstitialAd interstitial, boolean showInter, View view) {
        FragmentManager fm = activity.getSupportFragmentManager();
        SelectImageFragment galleryFragment = (SelectImageFragment) fm.findFragmentByTag("myFragmentTag");
        if (galleryFragment == null) {
            SelectImageFragment galleryFragment2 = new SelectImageFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(gallery_fragment_container, galleryFragment2, "myFragmentTag");
            ft.commitAllowingStateLoss();
            galleryFragment2.setGalleryListener(createGalleryListener(activity, galleryFragment2, interstitial, showInter, view));
            activity.findViewById(gallery_fragment_container).bringToFront();
            return galleryFragment2;
        }
        activity.getSupportFragmentManager().beginTransaction().show(galleryFragment).commitAllowingStateLoss();
        return galleryFragment;
    }

    public static SelectImageFragment.GalleryListener createGalleryListener(final FragmentActivity activity, final SelectImageFragment galleryFragment, InterstitialAd interstitial, boolean showInter, final View view) {
        return new SelectImageFragment.GalleryListener() {
            public void onGalleryCancel() {
                if (!(view == null || view.getVisibility() == 0)) {
                    view.setVisibility(View.VISIBLE);
                }
                activity.getSupportFragmentManager().beginTransaction().hide(galleryFragment).commitAllowingStateLoss();
            }

            public void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean x, boolean y) {
                if (!(view == null || view.getVisibility() == 0)) {
                    view.setVisibility(View.VISIBLE);
                }
                Intent localIntent = new Intent(activity, CreateCollageActivity.class);
                localIntent.putExtra("photo_id_list", jArr);
                localIntent.putExtra("photo_orientation_list", iArr);
                localIntent.putExtra("is_scrap_book", x);
                localIntent.putExtra("is_shape", y);
                activity.startActivity(localIntent);
            }

            public void onGalleryOkImageArrayRemoveFragment(long[] jArt, int[] iArr, boolean x, boolean y) {
            }

            public void onGalleryOkSingleImage(long j, int i, boolean x, boolean y) {
            }
        };
    }
}
