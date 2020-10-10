package com.simealapps.simealape.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import com.simealapps.simealape.utils.AdmobApplication;
import com.simealapps.simealape.R;
import com.simealapps.simealape.adapter.CreationAdapter;
import com.simealapps.simealape.camera.ExifHelper;
import com.simealapps.simealape.utils.mp4u;


public class MyCreationActivity extends Activity {




    private ImageView bback;
    private CreationAdapter galleryAdapter;
    private GridView lstList;
    private ImageView noImage;

    ExifHelper exif = new ExifHelper();

    private AdmobApplication admobApplication;
    private RelativeLayout bannerAdContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



       loadAds();



    }
    private void loadAds() {
        bannerAdContainer = (RelativeLayout) findViewById(R.id.bannerAdContainer);
        admobApplication = (AdmobApplication) getApplication();
        admobApplication.initializeAdsSdk();
        admobApplication.createGoogleBannerAd(bannerAdContainer);
        //admobApplication.createGoogleInterstitialAd();
    }



    protected void onResume() {
        super.onResume();
        admobApplication.onResume();
        bindView();
    }
    private void bindView() {
        noImage = (ImageView) findViewById(R.id.novideoimg);
        lstList = (GridView) findViewById(R.id.lstList);
        getImages();
        if (mp4u.IMAGEALLARY.size() <= 0) {
            this.noImage.setVisibility(View.VISIBLE);
            lstList.setVisibility(View.GONE);
        } else {
            this.noImage.setVisibility(View.GONE);
            lstList.setVisibility(View.VISIBLE);
        }
        bback = (ImageView) findViewById(R.id.back);
        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Collections.sort(mp4u.IMAGEALLARY);
        Collections.reverse(mp4u.IMAGEALLARY);
        Log.e("list size", String.valueOf(mp4u.IMAGEALLARY.size()));
        this.galleryAdapter = new CreationAdapter(this, mp4u.IMAGEALLARY);
        lstList.setAdapter(galleryAdapter);
    }
    private void getImages() {
        if (Build.VERSION.SDK_INT < 23) {
            fetchImage();
        } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            fetchImage();
        } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 5);
        }
    }
    private void fetchImage() {
        mp4u.IMAGEALLARY.clear();
        mp4u.listAllImages(new File("/mnt/sdcard/" + mp4u.Edit_Folder_name + "/"));
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 5:
                if (grantResults[0] == 0) {
                    fetchImage();
                    return;
                } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 5);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }


  public void saveExif(Uri contentUri) throws IOException {
        String filename = getContentFilename(contentUri);
        if (filename != null) {
            String versionString = null;
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                if (pInfo != null) {
                    versionString = pInfo.versionName;
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
            if (versionString == null) {
                versionString = "0.0";
            }
            this.exif.setAttribute("DateTime", currentTimeAsExif());
            this.exif.setAttribute("Orientation", "0");
            this.exif.setAttribute("Software", getString(R.string.app_name) + versionString + " (Android)");
            this.exif.setAttribute("Description", "Made with MP4u Apps ");
            this.exif.setAttribute("ImageLength", null);
            this.exif.setAttribute("ImageWidth", null);
            this.exif.setAttribute("ImageHeight", null);
            this.exif.writeExif(filename);
        }
    }
    String currentTimeAsExif() {
        return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date());
    }

    String getContentFilename(Uri contentUri) throws IOException {
        if (contentUri == null) {
            return null;
        }
        if ("file".equals(contentUri.getScheme())) {
            return contentUri.getPath();
        }
        Cursor cursor = getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow("_data");
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onPause() {
        admobApplication.onPause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        admobApplication.onDestroy();
        super.onDestroy();
    }
}
