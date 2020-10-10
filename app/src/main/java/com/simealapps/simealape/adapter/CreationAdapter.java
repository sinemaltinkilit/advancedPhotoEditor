package com.simealapps.simealape.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.accessibility.AccessibilityEventCompat;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.simealapps.simealape.R;
import com.simealapps.simealape.utils.mp4u;

public class CreationAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Activity dactivity;
    private int imageSize;
    ArrayList<String> imagegallary = new ArrayList();
    SparseBooleanArray mSparseBooleanArray;
    MediaMetadataRetriever metaRetriever;
    View vi;

    static class ViewHolder {
        public FrameLayout frm;
        ImageView imgIcon;

        ViewHolder() {
        }
    }

    public CreationAdapter(Activity dAct, ArrayList<String> dUrl) {
        this.dactivity = dAct;
        this.imagegallary = dUrl;
        inflater = (LayoutInflater) this.dactivity.getSystemService("layout_inflater");
        this.mSparseBooleanArray = new SparseBooleanArray(this.imagegallary.size());
    }

    public int getCount() {
        return this.imagegallary.size();
    }

    public Object getItem(int position) {
        return Integer.valueOf(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row = convertView;
        int width = this.dactivity.getResources().getDisplayMetrics().widthPixels;
        if (row == null) {
            row = LayoutInflater.from(this.dactivity).inflate(R.layout.list_gallary, parent, false);
            holder = new ViewHolder();
            holder.frm = (FrameLayout) row.findViewById(R.id.frm);
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.imgIcon.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(CreationAdapter.this.dactivity, 16973839);
                    DisplayMetrics metrics = new DisplayMetrics();
                    CreationAdapter.this.dactivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                    int height = (int) (((double) metrics.heightPixels) * 1.0d);
                    int width = (int) (((double) metrics.widthPixels) * 1.0d);
                    dialog.requestWindowFeature(1);
                    dialog.getWindow().setFlags(1024, 1024);
                    dialog.setContentView(R.layout.layout_fullscreen_image);
                    dialog.getWindow().setLayout(width, height);
                    dialog.setCanceledOnTouchOutside(true);
                    ImageView imgDelete = (ImageView) dialog.findViewById(R.id.imgDelete);
                    ImageView imgShare = (ImageView) dialog.findViewById(R.id.imgShare);
                    ImageView imgSetAs = (ImageView) dialog.findViewById(R.id.imgSetAs);
                    ((ImageView) dialog.findViewById(R.id.imgDisplay)).setImageURI(Uri.parse((String) mp4u.IMAGEALLARY.get(position)));
                    imgShare.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            File imagepath = new File(mp4u.IMAGEALLARY.get(position));
                            Bitmap bitmap = BitmapFactory.decodeFile(imagepath.toString());



                          try{
                              Intent shareIntent = new Intent("android.intent.action.SEND");
                              shareIntent.setType("image/*");
                              shareIntent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
                              shareIntent.putExtra("android.intent.extra.TEXT", mp4u.app_name + " Create By : " + mp4u.package_name);
                              shareIntent.putExtra("android.intent.extra.STREAM", getImageUri(dactivity,bitmap));
                              dactivity.startActivity(Intent.createChooser(shareIntent, "Share Image using"));
                              dialog.dismiss();


                            //  Intent shareIntent = new Intent();
                            //  shareIntent.setAction(Intent.ACTION_SEND);
                            //
                            //  shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imagegallary.get(position)));
                            //  shareIntent.setType("image/jpeg");
                            //  dactivity.startActivity(Intent.createChooser(shareIntent, "Share Image to Other..."));
                            //  dialog.dismiss();

                          }catch (Exception e){
                              Log.e("Creation Adapter",e.getMessage());
                          }
                        }
                    });
                    imgSetAs.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            setWallpaper("Diversity", (String) CreationAdapter.this.imagegallary.get(position));
                            dialog.dismiss();
                        }
                    });
                    imgDelete.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            final Dialog dial = new Dialog(CreationAdapter.this.dactivity, 16973839);
                            dial.requestWindowFeature(1);
                            dial.setContentView(R.layout.delete_confirmation);
                            dial.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            dial.setCanceledOnTouchOutside(true);
                            ((TextView) dial.findViewById(R.id.delete_yes)).setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    File fD = new File((String) CreationAdapter.this.imagegallary.get(position));
                                    if (fD.exists()) {
                                        fD.delete();
                                    }
                                    CreationAdapter.this.imagegallary.remove(position);
                                    CreationAdapter.this.dactivity.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(String.valueOf(fD)))));
                                    CreationAdapter.this.notifyDataSetChanged();
                                    if (CreationAdapter.this.imagegallary.size() == 0) {
                                        Toast.makeText(CreationAdapter.this.dactivity, "No Image Found..", 1).show();
                                    }
                                    dial.dismiss();
                                    dialog.dismiss();
                                }
                            });
                            ((TextView) dial.findViewById(R.id.delete_no)).setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    dial.dismiss();
                                }
                            });
                            dial.show();
                        }
                    });
                    dialog.show();
                }
            });
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Glide.with(this.dactivity).load((String) this.imagegallary.get(position)).into(holder.imgIcon);
        System.gc();
        return row;
    }

    private void setWallpaper(String diversity, String s) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this.dactivity);
        DisplayMetrics metrics = new DisplayMetrics();
        this.dactivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        try {
            Options options = new Options();
            options.inPreferredConfig = Config.ARGB_8888;
            wallpaperManager.setBitmap(BitmapFactory.decodeFile(s, options));
            wallpaperManager.suggestDesiredDimensions(width / 2, height / 2);
            Toast.makeText(this.dactivity, "Wallpaper Set", 1).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        return Uri.parse(MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, dactivity.getString(R.string.app_name), null));
    }
}
