package com.simealapps.simealape.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import com.simealapps.simealape.utils.AdmobApplication;
import com.simealapps.simealape.R;
import com.simealapps.simealape.fragments.SelectImageFragment;
import com.simealapps.simealape.utils.mp4u;
import com.simealapps.simealape.utils.CollageHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int camera_support_state = 1;
    int PERMISSION_COLLAGE_EDITOR = 11;
    SelectImageFragment galleryFragment;
    int PERMISSION_SINGLE_EDITOR = 22;

    private ImageView camera, edit, collage, myCreation, rate, more;
    private AdmobApplication admobApplication;
    private RelativeLayout bannerAdContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        permissionCheck();
        loadAds();

        edit = (ImageView) findViewById(R.id.edit);
        camera = (ImageView) findViewById(R.id.camera);
        collage = (ImageView) findViewById(R.id.collage);
        myCreation = (ImageView) findViewById(R.id.mycreation);
        rate = (ImageView) findViewById(R.id.rate);
        more = (ImageView) findViewById(R.id.more);
        edit.setOnClickListener(this);
        camera.setOnClickListener(this);
        collage.setOnClickListener(this);
        myCreation.setOnClickListener(this);
        rate.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit:
                if (Build.VERSION.SDK_INT < 23) {
                    openCollage(true,  false);
                } else if (checkAndRequestSinglePermissions()) {
                    openCollage(true,  false);
                }
                break;
            case R.id.camera:
                Intent intent1 = new Intent(MainActivity.this, BeautyCamera.class);
                startActivity(intent1);
                break;
            case R.id.collage:
                if (Build.VERSION.SDK_INT < 23) {
                    openCollage(false, false);
                } else if (checkAndRequestCollagePermissions()) {
                    openCollage(false, false);
                }
                break;
            case R.id.mycreation:
                Intent intent = new Intent(MainActivity.this, MyCreationActivity.class);
                startActivity(intent);
                break;
            case R.id.rate:
                mp4u.rateApplication(MainActivity.this);
                break;
            case R.id.more:
                mp4u.moreApplication(MainActivity.this);
                break;
        }
    }

    public void openCollage(boolean isblur,  boolean isShape) {
        this.galleryFragment = CollageHelper.addGalleryFragment(this, R.id.gallery_fragment_container, null, true, null);
        this.galleryFragment.setCollageSingleMode(isblur);
        this.galleryFragment.setIsShape(isShape);

    }

    private boolean checkAndRequestSinglePermissions() {
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        int storagePermission = ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (storagePermission != 0) {
            listPermissionsNeeded.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add("android.permission.CAMERA");
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_SINGLE_EDITOR);
        return false;
    }


    private boolean checkAndRequestCollagePermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, str);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, str2);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, str3);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_COLLAGE_EDITOR);
        return false;
    }




    private void loadAds() {

        bannerAdContainer = (RelativeLayout) findViewById(R.id.bannerAdContainer);
        admobApplication = (AdmobApplication) getApplication();
        admobApplication.initializeAdsSdk();
        admobApplication.createGooglerRctangleAd(bannerAdContainer);
        //admobApplication.createGoogleInterstitialAd();


    }
    private void permissionCheck() {


        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    private void showSettingsDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    private void exitto() {
        try {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Exit !!!")
                    .setMessage("Do you really want to Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                            homeIntent.addCategory(Intent.CATEGORY_HOME);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(homeIntent);

                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        } catch (Exception e) {


        }


    }

    @Override
    public void onBackPressed() {

        SelectImageFragment localGalleryFragment = CollageHelper.getGalleryFragment(this);
        if (localGalleryFragment == null || !localGalleryFragment.isVisible()) {
            exitto();
        } else {
            localGalleryFragment.onBackPressed();
        }


    }

    @Override
    protected void onPause() {
        admobApplication.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        admobApplication.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        admobApplication.onDestroy();
        super.onDestroy();
    }
}
