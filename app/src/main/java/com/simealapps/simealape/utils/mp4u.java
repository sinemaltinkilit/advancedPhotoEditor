package com.simealapps.simealape.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import com.simealapps.simealape.R;


public class mp4u {
    public static String path_saved;
    public static int selected_frame = 0;
    public static float screenWidth, screenHeight;
    public static Bitmap backgroundBitmap = null;
    public static Bitmap altered_bitmap = null;
    public static ArrayList<Bitmap> collage_bitmap_array_list = new ArrayList<>();
    public static int camera_or_cameraCollage = 0;
    public static int cameraCollage_cat = 0;
    public static File newDir = new File(Environment.getExternalStorageDirectory() + "/"+ AdmobApplication.getContext().getString(R.string.directory));
    public static File filenew;
    public static Bitmap photoBitmap=null;
    public static ArrayList<String> IMAGEALLARY = new ArrayList();
    public static String app_name = newDir.getName();
    public static String Edit_Folder_name = AdmobApplication.getContext().getString(R.string.directory);
    public static String package_name = "https://play.google.com/store/apps/details?id="+AdmobApplication.getContext().getPackageName();






    public static void rateApplication(Context context)
    {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK|
                Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }


    //.....................More app play store code........
    public static void moreApplication(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://search?q=pub:"+"\""+context.getString(R.string.more_link)+"\""));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void listAllImages(File filepath) {
        File[] files = filepath.listFiles();
        if (files != null) {
            for (int j = files.length - 1; j >= 0; j--) {
                String ss = files[j].toString();
                File check = new File(ss);
                Log.d("" + check.length(), "" + check.length());
                if (check.length() <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                    Log.i("Invalid Image", "Delete Image");
                } else if (check.toString().contains(".jpg") || check.toString().contains(".png") || check.toString().contains(".jpeg")) {
                    IMAGEALLARY.add(ss);
                }
                System.out.println(ss);
            }
            return;
        }
        System.out.println("Empty Folder");
    }


}
