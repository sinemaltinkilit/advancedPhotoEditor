package com.simealapps.simealape.activities;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seu.magicfilter.display.MagicCameraDisplay;
import com.seu.magicfilter.utils.SaveTask.onPictureSaveListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.simealapps.simealape.R;
import com.simealapps.simealape.camera.CollageSelectionActionAdapter;
import com.simealapps.simealape.camera.FilterLayoutUtils;
import com.simealapps.simealape.utils.RecyclerTouchListener;
import com.simealapps.simealape.utils.mp4u;


public class BeautyCamera extends Activity implements OnTouchListener {

    public static int[] imageType = {R.drawable.aspect_1, R.drawable.aspect_2, R.drawable.aspect_3, R.drawable.collage_cat_1,
            R.drawable.collage_cat_2, R.drawable.collage_cat_3, R.drawable.collage_cat_4,R.drawable.collage_cat_5,
            R.drawable.collage_cat_6, R.drawable.collage_cat_7, R.drawable.collage_cat_9, R.drawable.collage_cat_8,
            R.drawable.collage_cat_10};
    public static int _is_save_on_touch = 0;
    public static int android_camera_running = 0;
    public static ImageView collage_indication = null;
    public static int flash_on_off = 0;
    static int flip_enable = 0;
    public static boolean front_camera = true;
    static int image_click_count;
    static int timer_state;
    ImageView _click_image;
    ImageView _filter;
    ImageView _flash;
    ImageView flip_btn;

    ImageView image_ratio_type;
    ImageView _save_on_touch;
    ImageView _timer;
    float aspect_ratio;
    Bitmap bitmap = null;
    LinearLayout complete_layer;
    Context context;
    int effect_index = 0;
    Integer[] element_in_collage = {Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)};
    File file;
    ImageView filte;
    ImageView filter_;
    ImageView filter_swipe_icon;
    LinearLayout first_;
    LinearLayout first_layer;
    LayoutParams first_param;
    ImageView flip;
    ImageView gallery;

    ImageView f3137gi;
    ImageView gif;
    GLSurfaceView glSurfaceView;
    float h_gl;
    float height;
    int[] img_1 = {R.drawable.img_1_1, R.drawable.img_1_2};
    int[] img_10 = {R.drawable.img_10_1, R.drawable.img_10_2, R.drawable.img_10_3, R.drawable.img_10_4, R.drawable.img_10_5, R.drawable.img_10_6, R.drawable.img_10_7, R.drawable.img_10_8, R.drawable.img_10_9};
    int[] img_2 = {R.drawable.img_2_1, R.drawable.img_2_2, R.drawable.img_2_3};
    int[] img_3 = {R.drawable.img_3_1, R.drawable.img_3_2, R.drawable.img_3_3, R.drawable.img_3_4};
    int[] img_4 = {R.drawable.img_4_1, R.drawable.img_4_2, R.drawable.img_4_3, R.drawable.img_4_4, R.drawable.img_4_5, R.drawable.img_4_6};
    int[] img_5 = {R.drawable.img_5_1, R.drawable.img_5_2, R.drawable.img_5_3, R.drawable.img_5_4, R.drawable.img_5_5, R.drawable.img_5_6, R.drawable.img_5_7, R.drawable.img_5_8, R.drawable.img_5_9};
    int[] img_6 = {R.drawable.img_6_1, R.drawable.img_6_2};
    int[] img_7 = {R.drawable.img_7_1, R.drawable.img_7_2, R.drawable.img_7_3};
    int[] img_8 = {R.drawable.img_8_1, R.drawable.img_8_2, R.drawable.img_8_3, R.drawable.img_8_4};
    int[] img_9 = {R.drawable.img_9_1, R.drawable.img_9_2, R.drawable.img_9_3, R.drawable.img_9_4, R.drawable.img_9_5, R.drawable.img_9_6};
    int[] img_source;
    int is_collage_selection_open = 0;
    int is_filter_open = 0;
    int is_gif_open = 0;
    FrameLayout.LayoutParams layer_param;
    LinearLayout layout_hide_bottom;
    LayoutParams layout_hide_bottom_param;
    LinearLayout layout_hide_center_1;
    LayoutParams layout_hide_center_1_param;
    LinearLayout layout_hide_center_2;
    LayoutParams layout_hide_center_2_param;
    LinearLayout layout_hide_left;
    LayoutParams layout_hide_left_param;
    LinearLayout layout_hide_right;
    LayoutParams layout_hide_right_param;
    LinearLayout layout_hide_top;
    LayoutParams layout_hide_top_param;
    float left_;

    public LinearLayout mCollageSelectionLayout;
    public LinearLayout mFilterLayout;
    public MagicCameraDisplay mMagicCameraDisplay;
    LayoutParams params_first_layer;
    LayoutParams params_second_layer;
    LayoutParams params_third_layer;
    ProgressDialog progressDialog;
    ImageView save;
    int save_enable = 1;
    LinearLayout second_;
    LinearLayout second_layer;
    LayoutParams second_param;
    TextView seconds;
    TextView show_click;
    TextView show_time;
    float start_point;
    LinearLayout third_;
    LinearLayout third_layer;
    LayoutParams third_param;
    int time_duration = 0;
    TextView timer_count;
    CardView timer_layout;
    float top_;
    float w_gl;
    float width;

    public void change_aspect_ratio() {
        switch (mp4u.selected_frame) {
            case 0:
                mp4u.selected_frame = 1;
                this.image_ratio_type.setImageResource(R.drawable.aspect_3);
                this.first_param = new LayoutParams((int) this.width, ((int) (this.height - this.width)) / 2);
                this.second_param = new LayoutParams((int) this.width, (int) this.width);
                this.first_.setLayoutParams(this.first_param);
                this.second_.setLayoutParams(this.second_param);
                this.third_.setLayoutParams(this.first_param);
                return;
            case 1:
                this.image_ratio_type.setImageResource(R.drawable.aspect_1);
                mp4u.selected_frame = 2;
                this.first_param = new LayoutParams(0, 0);
                this.second_param = new LayoutParams(0, 0);
                this.third_param = new LayoutParams(0, 0);
                this.first_.setLayoutParams(this.first_param);
                this.second_.setLayoutParams(this.second_param);
                this.third_.setLayoutParams(this.third_param);
                return;
            case 2:
                this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                mp4u.selected_frame = 0;
                this.first_param = new LayoutParams((int) this.width, ((int) (this.height - ((this.width / 3.0f) * 4.0f))) / 2);
                this.second_param = new LayoutParams((int) this.width, (int) ((this.width / 3.0f) * 4.0f));
                this.first_.setLayoutParams(this.first_param);
                this.second_.setLayoutParams(this.second_param);
                this.third_.setLayoutParams(this.first_param);
                return;
            default:
                return;
        }
    }


    public void layout_square() {
        mp4u.selected_frame = 1;
        this.image_ratio_type.setImageResource(R.drawable.aspect_3);
        this.first_param = new LayoutParams((int) this.width, ((int) (this.height - this.width)) / 2);
        this.second_param = new LayoutParams((int) this.width, (int) this.width);
        this.first_.setLayoutParams(this.first_param);
        this.second_.setLayoutParams(this.second_param);
        this.third_.setLayoutParams(this.first_param);
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.beauty_camera);
        mp4u.selected_frame = 2;
        getWindow().setFlags(1024, 1024);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height = (float) displayMetrics.heightPixels;
        this.width = (float) displayMetrics.widthPixels;
        this.filter_swipe_icon = (ImageView) findViewById(R.id.filter_swipe_icon);
        this.filter_swipe_icon.setImageResource(R.drawable.swipe_filter_icon);
        collage_indication = (ImageView) findViewById(R.id.collage_indication);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in_1);
        final Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.zoom_out_1);
        this.filter_swipe_icon.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BeautyCamera.this.filter_swipe_icon.setAnimation(loadAnimation2);
            }
        });
        loadAnimation2.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BeautyCamera.this.filter_swipe_icon.setImageResource(R.drawable.un_selected_image);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(" : ");
        sb.append(MainActivity.camera_support_state);
        Log.e("Camera support state : ", sb.toString());
        if (MainActivity.camera_support_state == 2) {
            this.h_gl = this.height;
            this.w_gl = (this.h_gl / 4.0f) * 3.0f;
        } else {
            this.w_gl = this.width;
            this.h_gl = (this.w_gl / 9.0f) * 16.0f;
        }
        initMagicPreview();
        initFilterLayout();
        this.layout_hide_top = (LinearLayout) findViewById(R.id.layout_hide_top);
        this.layout_hide_bottom = (LinearLayout) findViewById(R.id.layout_hide_bottom);
        this.layout_hide_left = (LinearLayout) findViewById(R.id.layout_hide_left);
        this.layout_hide_right = (LinearLayout) findViewById(R.id.layout_hide_right);
        this.layout_hide_center_1 = (LinearLayout) findViewById(R.id.layout_hide_center_1);
        this.layout_hide_center_2 = (LinearLayout) findViewById(R.id.layout_hide_center_1);
        CollageSelectionActionAdapter collageSelectionActionAdapter = new CollageSelectionActionAdapter(this,
                imageType);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.collage_action_selection);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(collageSelectionActionAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int i) {
                mp4u.collage_bitmap_array_list.clear();
                switch (i) {
                    case 0:
                        collage_indication.setVisibility(View.GONE);
                        mp4u.camera_or_cameraCollage = 0;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_1);
                        mp4u.selected_frame = 2;
                        BeautyCamera.this.first_param = new LayoutParams(0, 0);
                        BeautyCamera.this.second_param = new LayoutParams(0, 0);
                        BeautyCamera.this.third_param = new LayoutParams(0, 0);
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.third_param);
                        return;
                    case 1:
                        collage_indication.setVisibility(View.GONE);
                        mp4u.camera_or_cameraCollage = 0;
                        image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        return;
                    case 2:
                        BeautyCamera.collage_indication.setVisibility(8);
                        mp4u.camera_or_cameraCollage = 0;
                        mp4u.selected_frame = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_3);
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - BeautyCamera.this.width)) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) BeautyCamera.this.width);
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        return;
                    case 3:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_1;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 2;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        return;
                    case 4:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_2;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 3;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        return;
                    case 5:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_3;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        mp4u.cameraCollage_cat = 4;
                        return;
                    case 6:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_4;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        mp4u.cameraCollage_cat = 6;
                        return;
                    case 7:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_5;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.image_ratio_type.setImageResource(R.drawable.aspect_2);
                        mp4u.selected_frame = 0;
                        BeautyCamera.this.first_param = new LayoutParams((int) BeautyCamera.this.width, ((int) (BeautyCamera.this.height - ((BeautyCamera.this.width / 3.0f) * 4.0f))) / 2);
                        BeautyCamera.this.second_param = new LayoutParams((int) BeautyCamera.this.width, (int) ((BeautyCamera.this.width / 3.0f) * 4.0f));
                        BeautyCamera.this.first_.setLayoutParams(BeautyCamera.this.first_param);
                        BeautyCamera.this.second_.setLayoutParams(BeautyCamera.this.second_param);
                        BeautyCamera.this.third_.setLayoutParams(BeautyCamera.this.first_param);
                        mp4u.cameraCollage_cat = 9;
                        return;
                    case 8:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_6;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 2;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    case 9:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_7;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 3;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    case 10:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_8;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 4;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    case 11:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_9;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 6;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    case 12:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_10;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 9;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    case 13:
                        BeautyCamera.this.img_source = BeautyCamera.this.img_10;
                        BeautyCamera.collage_indication.setVisibility(0);
                        BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                        mp4u.cameraCollage_cat = 10;
                        mp4u.camera_or_cameraCollage = 1;
                        BeautyCamera.this.layout_square();
                        return;
                    default:
                        return;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        this.progressDialog = new ProgressDialog(this, 2);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Image saving...");
        this.progressDialog.setCancelable(false);
        float f = (this.height * 20.0f) / 100.0f;
        this.first_ = (LinearLayout) findViewById(R.id.first_);
        this.second_ = (LinearLayout) findViewById(R.id.second_);
        this.third_ = (LinearLayout) findViewById(R.id.third_);
        this.first_param = new LayoutParams(0, 0);
        this.second_param = new LayoutParams(0, 0);
        this.third_param = new LayoutParams(0, 0);
        this.first_.setLayoutParams(this.first_param);
        this.second_.setLayoutParams(this.second_param);
        this.third_.setLayoutParams(this.third_param);
        this.first_param = new LayoutParams((int) this.width, (((int) this.height) * 10) / 100);
        this.second_param = new LayoutParams((int) this.width, (((int) this.height) * 10) / 100);
        this.third_param = new LayoutParams((int) this.width, (((int) this.height) * 10) / 100);
        this.complete_layer = (LinearLayout) findViewById(R.id.complete_layer);
        this.first_layer = (LinearLayout) findViewById(R.id.first_layer);
        this.second_layer = (LinearLayout) findViewById(R.id.second_layer);
        this.third_layer = (LinearLayout) findViewById(R.id.third_layer);
        this.complete_layer.setLayoutParams(new FrameLayout.LayoutParams((int) this.width, (int) this.height));
        this.params_first_layer = new LayoutParams((int) this.width, (((int) this.height) * 10) / 100);
        this.params_second_layer = new LayoutParams((int) this.width, (((int) this.height) * 75) / 100);
        this.params_third_layer = new LayoutParams((int) this.width, (((int) this.height) * 15) / 100);
        this.first_layer.setLayoutParams(this.params_first_layer);
        this.second_layer.setLayoutParams(this.params_second_layer);
        this.third_layer.setLayoutParams(this.params_third_layer);
        this.second_layer.setOnTouchListener(this);
       // this._home = (ImageView) findViewById(R.id.click_home);
        this._save_on_touch = (ImageView) findViewById(R.id.new_image_save);
      //  this._frame_aspect = (ImageView) findViewById(R.id.gallery);
        this._flash = (ImageView) findViewById(R.id.click_flash);
        this.flip_btn = (ImageView) findViewById(R.id.click_flip);
        this._timer = (ImageView) findViewById(R.id.click_timer);
        int i = (int) ((25.0f * f) / 100.0f);
        LayoutParams layoutParams = new LayoutParams(i, i);
       // this._home.setLayoutParams(layoutParams);
        this._save_on_touch.setLayoutParams(layoutParams);
        this._flash.setLayoutParams(layoutParams);
        this.flip_btn.setLayoutParams(layoutParams);
        this._timer.setLayoutParams(layoutParams);

        this._flash.setImageResource(R.drawable.flash_off);
        this._timer.setImageResource(R.drawable.timer_1);

        this.image_ratio_type = (ImageView) findViewById(R.id.image_ratio_type);
        this.image_ratio_type.setImageResource(R.drawable.aspect_1);
        this._click_image = (ImageView) findViewById(R.id._click_image);
        this._filter = (ImageView) findViewById(R.id._filter);
        int i2 = (int) ((30.0f * f) / 100.0f);
        LayoutParams layoutParams2 = new LayoutParams(i2, i2);
        this.image_ratio_type.setLayoutParams(layoutParams2);
        this._filter.setLayoutParams(layoutParams2);
        int i3 = (int) ((f * 75.0f) / 100.0f);
        this._click_image.setLayoutParams(new LayoutParams(i3, i3));
        this.timer_count = (TextView) findViewById(R.id.timer_count);
        this._save_on_touch.setImageResource(R.drawable.save_on_click_off);
        this._save_on_touch.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BeautyCamera._is_save_on_touch == 0) {
                    BeautyCamera._is_save_on_touch = 1;
                    BeautyCamera.this._save_on_touch.setImageResource(R.drawable.save_on_click_on);
                    return;
                }
                BeautyCamera._is_save_on_touch = 0;
                BeautyCamera.this._save_on_touch.setImageResource(R.drawable.save_on_click_off);
            }
        });
        this._flash.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!BeautyCamera.front_camera) {
                    if (BeautyCamera.flash_on_off == 0) {
                        BeautyCamera.flash_on_off = 1;
                        BeautyCamera.this._flash.setImageResource(R.drawable.flash_on);
                    } else if (BeautyCamera.flash_on_off == 1) {
                        BeautyCamera.flash_on_off = 2;
                        BeautyCamera.this._flash.setImageResource(R.drawable.flash_auto);
                    } else if (BeautyCamera.flash_on_off == 2) {
                        BeautyCamera.flash_on_off = 0;
                        BeautyCamera.this._flash.setImageResource(R.drawable.flash_off);
                    }
                    BeautyCamera.this.mMagicCameraDisplay.flash_light(BeautyCamera.flash_on_off);
                    return;
                }
                Toast.makeText(BeautyCamera.this, "Flip camera to use Flash ", 0).show();
            }
        });
        this.flip_btn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                if(flip_enable==0)
                {
                    flip_enable=1;
                    flip_camera();
                }
                else
                {
                    Log.e("flip","Disable");
                }
            }
        });

        this._timer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BeautyCamera.timer_state == 0) {
                    BeautyCamera.this.time_duration = 3000;
                    BeautyCamera.timer_state = 1;
                    BeautyCamera.this._timer.setImageResource(R.drawable.timer_2);
                } else if (BeautyCamera.timer_state == 1) {
                    BeautyCamera.this.time_duration = 5000;
                    BeautyCamera.timer_state = 2;
                    BeautyCamera.this._timer.setImageResource(R.drawable.timer_3);
                } else if (BeautyCamera.timer_state == 2) {
                    BeautyCamera.this.time_duration = 10000;
                    BeautyCamera.timer_state = 3;
                    BeautyCamera.this._timer.setImageResource(R.drawable.timer_4);
                } else if (BeautyCamera.timer_state == 3) {
                    BeautyCamera.this.time_duration = 0;
                    BeautyCamera.timer_state = 0;
                    BeautyCamera.this._timer.setImageResource(R.drawable.timer_1);
                }
            }
        });
        this.image_ratio_type.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BeautyCamera.this.is_filter_open == 1) {
                    BeautyCamera.this.close_filter();
                }
                if (BeautyCamera.this.is_collage_selection_open == 0) {
                    BeautyCamera.this.open_collage_selection();
                } else {
                    BeautyCamera.this.close_collage_selection();
                }
            }
        });
        this._click_image.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BeautyCamera.this.time_duration == 0) {
                    BeautyCamera.this.save_image();
                } else {
                    BeautyCamera.this.initialise_timer();
                }
            }
        });
        this._filter.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BeautyCamera.this.is_collage_selection_open == 1) {
                    BeautyCamera.this.close_collage_selection();
                }
                if (BeautyCamera.this.is_filter_open == 0) {
                    BeautyCamera.this.open_filter();
                } else {
                    BeautyCamera.this.close_filter();
                }
            }
        });
        try {
            open_filter();
        } catch (Exception unused) {
        }

    }

    public void open_collage_selection() {
        this.is_collage_selection_open = 1;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCollageSelectionLayout, "translationY", new float[]{(float) this.mCollageSelectionLayout.getHeight(), 0.0f});
        ofFloat.setDuration(200);
        ofFloat.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                BeautyCamera.this.mCollageSelectionLayout.setVisibility(View.VISIBLE);
            }
        });
        ofFloat.start();
    }

    public void close_collage_selection() {
        this.is_collage_selection_open = 0;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mCollageSelectionLayout, "translationY", new float[]{0.0f, (float) this.mCollageSelectionLayout.getHeight()});
        ofFloat.setDuration(200);
        ofFloat.addListener(new AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                BeautyCamera.this.mCollageSelectionLayout.setVisibility(4);
            }

            public void onAnimationCancel(Animator animator) {
                BeautyCamera.this.mCollageSelectionLayout.setVisibility(4);
            }
        });
        ofFloat.start();
    }

    public void open_filter() {
        this.is_filter_open = 1;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFilterLayout, "translationY", new float[]{(float) this.mFilterLayout.getHeight(), 0.0f});
        ofFloat.setDuration(200);
        ofFloat.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                BeautyCamera.this.mFilterLayout.setVisibility(0);
            }
        });
        ofFloat.start();
    }

    public void close_filter() {
        this.is_filter_open = 0;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFilterLayout, "translationY", new float[]{0.0f, (float) this.mFilterLayout.getHeight()});
        ofFloat.setDuration(200);
        ofFloat.addListener(new AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                BeautyCamera.this.mFilterLayout.setVisibility(4);
            }

            public void onAnimationCancel(Animator animator) {
                BeautyCamera.this.mFilterLayout.setVisibility(4);
            }
        });
        ofFloat.start();
    }

    public void flip_camera() {

        if (front_camera )
        {
            front_camera = false;
        }
        else
        {
            front_camera = true;
        }
        executeAsyncTask();
    }

    public void executeAsyncTask() {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.e("AsyncTask", "onPreExecute");
            }

            @Override
            protected String doInBackground(Void... params) {
                Log.v("AsyncTask", "doInBackground");
                String msg = null;

                mMagicCameraDisplay.flipcamera();
                return msg;
            }
            @Override
            protected void onPostExecute(String msg) {
                Log.v("AsyncTask", "onPostExecute");
                flip_enable=0;
                onResume();

                if(MainActivity.camera_support_state==2)
                {
                    h_gl=(float) height;
                    w_gl=((float)h_gl/4)*3;
                }
                else
                {
                    w_gl=(float) width;
                    h_gl=((float)w_gl/9)*16;
                }

                glSurfaceView.setLayoutParams(new FrameLayout.LayoutParams((int)w_gl,(int)h_gl));
            }
        };

        if(Build.VERSION.SDK_INT >= 11/*HONEYCOMB*/) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            task.execute();
        }
    }

    private void initMagicPreview() {
        try {
            this.glSurfaceView = (GLSurfaceView) findViewById(R.id.glsurfaceview_camera);
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        this.glSurfaceView.setLayoutParams(new FrameLayout.LayoutParams((int) this.w_gl, (int) this.h_gl));
        this.mMagicCameraDisplay = new MagicCameraDisplay(this, this.glSurfaceView);
    }

    private void initFilterLayout() {
        this.mFilterLayout = (LinearLayout) findViewById(R.id.layout_filter);
        this.mCollageSelectionLayout = (LinearLayout) findViewById(R.id.layout_collage_selection);
        new FilterLayoutUtils(this, this.mMagicCameraDisplay).init();
    }
    public void onPause() {
        super.onPause();
        if (this.mMagicCameraDisplay != null) {
            this.mMagicCameraDisplay.onPause();
        }
    }
    public void onResume() {
        super.onResume();
        if (this.mMagicCameraDisplay != null) {
            this.mMagicCameraDisplay.onResume();
        }
        try {
            this.progressDialog.dismiss();
        } catch (Exception unused) {
        }
    }
    public void onDestroy() {
        super.onDestroy();
        if (this.mMagicCameraDisplay != null) {
            this.mMagicCameraDisplay.onDestroy();
        }
    }
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.start_point = motionEvent.getX();
                break;
            case 1:
                if (this.start_point >= motionEvent.getX()) {
                    if (this.start_point - motionEvent.getX() <= 20.0f) {
                        if (_is_save_on_touch == 1) {
                            save_image();
                            break;
                        }
                    } else if (this.effect_index != 52) {
                        this.effect_index++;
                        this.mMagicCameraDisplay.setFilter(this.effect_index);
                        break;
                    } else {
                        this.effect_index = 1;
                        break;
                    }
                } else if (motionEvent.getX() - this.start_point <= 20.0f) {
                    if (_is_save_on_touch == 1) {
                        save_image();
                        break;
                    }
                } else if (this.effect_index != 0) {
                    this.effect_index--;
                    this.mMagicCameraDisplay.setFilter(this.effect_index);
                    break;
                } else {
                    this.effect_index = 51;
                    break;
                }
                break;
        }
        return true;
    }
    public void initialise_timer() {
      CountDownTimer   r0 = new CountDownTimer((long) this.time_duration, 1000) {
            public void onTick(long j) {
                TextView textView = BeautyCamera.this.timer_count;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(j / 1000);
                textView.setText(sb.toString());
            }

            public void onFinish() {
                BeautyCamera.this.save_image();
            }
        };
        r0.start();
    }
    public static Bitmap flip_(Bitmap bitmap2, int i) {
        Matrix matrix = new Matrix();
        if (i == 1) {
            matrix.preScale(1.0f, -1.0f);
        } else if (i != 2) {
            return null;
        } else {
            matrix.preScale(-1.0f, 1.0f);
        }
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }
    public void save_image() {
        try {
            this.timer_count.setText("");
            this.progressDialog.show();
            this.mMagicCameraDisplay.onTakePicture(mp4u.altered_bitmap, new onPictureSaveListener() {
                public void onSaved(Bitmap bitmap) {
                    if (BeautyCamera.front_camera) {
                        bitmap = BeautyCamera.flip_(bitmap, 2);
                    }
                    mp4u.altered_bitmap = bitmap;
                    if (mp4u.altered_bitmap == null) {
                        Log.e("STATUS", "bitmap is null");
                        return;
                    }
                    if (BeautyCamera.this.height / BeautyCamera.this.width == 1.7777778f) {
                        mp4u.altered_bitmap = Bitmap.createBitmap(mp4u.altered_bitmap, 0, 0, mp4u.altered_bitmap.getWidth(), (mp4u.altered_bitmap.getWidth() / 9) * 16);
                    }
                    if (BeautyCamera.this.height / BeautyCamera.this.width == 1.6444445f) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(mp4u.altered_bitmap.getWidth());
                        sb.append(" , ");
                        sb.append((mp4u.altered_bitmap.getWidth() / 45) * 74);
                        Log.e("STATUSN", sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("");
                        sb2.append(mp4u.altered_bitmap.getWidth());
                        sb2.append(" , ");
                        sb2.append(mp4u.altered_bitmap.getHeight());
                        Log.e("STATUSN", sb2.toString());
                        if ((mp4u.altered_bitmap.getWidth() / 45) * 74 > mp4u.altered_bitmap.getHeight()) {
                            mp4u.altered_bitmap = Bitmap.createBitmap(mp4u.altered_bitmap, 0, 0, (mp4u.altered_bitmap.getHeight() / 74) * 45, mp4u.altered_bitmap.getHeight());
                        } else {
                            mp4u.altered_bitmap = Bitmap.createBitmap(mp4u.altered_bitmap, 0, 0, mp4u.altered_bitmap.getWidth(), (mp4u.altered_bitmap.getWidth() / 45) * 74);
                        }
                    }
                    switch (mp4u.selected_frame) {
                        case 0:
                            mp4u.altered_bitmap = Bitmap.createBitmap(mp4u.altered_bitmap, 0, (mp4u.altered_bitmap.getHeight() - (((int) (((float) mp4u.altered_bitmap.getWidth()) / 3.0f)) * 4)) / 2, mp4u.altered_bitmap.getWidth(), ((int) (((float) mp4u.altered_bitmap.getWidth()) / 3.0f)) * 4);
                            break;
                        case 1:
                            mp4u.altered_bitmap = Bitmap.createBitmap(mp4u.altered_bitmap, 0, (mp4u.altered_bitmap.getHeight() - mp4u.altered_bitmap.getWidth()) / 2, mp4u.altered_bitmap.getWidth(), mp4u.altered_bitmap.getWidth());
                            break;
                    }
                    if (mp4u.camera_or_cameraCollage == 0) {
                        BeautyCamera.this.saving_bitmap();
                        return;
                    }
                    mp4u.collage_bitmap_array_list.add(mp4u.altered_bitmap);
                    int size = mp4u.collage_bitmap_array_list.size();
                    if (size == 6) {
                        mp4u.altered_bitmap = BeautyCamera.this.combineImagesSix((Bitmap) mp4u.collage_bitmap_array_list.get(0), (Bitmap) mp4u.collage_bitmap_array_list.get(1), (Bitmap) mp4u.collage_bitmap_array_list.get(2), (Bitmap) mp4u.collage_bitmap_array_list.get(3), (Bitmap) mp4u.collage_bitmap_array_list.get(4), (Bitmap) mp4u.collage_bitmap_array_list.get(5));
                    } else if (size != 9) {
                        switch (size) {
                            case 2:
                                mp4u.altered_bitmap = BeautyCamera.this.combineImagesTwo((Bitmap) mp4u.collage_bitmap_array_list.get(0), (Bitmap) mp4u.collage_bitmap_array_list.get(1));
                                break;
                            case 3:
                                mp4u.altered_bitmap = BeautyCamera.this.combineImagesThree((Bitmap) mp4u.collage_bitmap_array_list.get(0), (Bitmap) mp4u.collage_bitmap_array_list.get(1), (Bitmap) mp4u.collage_bitmap_array_list.get(2));
                                break;
                            case 4:
                                mp4u.altered_bitmap = BeautyCamera.this.combineImagesFour((Bitmap) mp4u.collage_bitmap_array_list.get(0), (Bitmap) mp4u.collage_bitmap_array_list.get(1), (Bitmap) mp4u.collage_bitmap_array_list.get(2), (Bitmap) mp4u.collage_bitmap_array_list.get(3));
                                break;
                        }
                    } else {
                        mp4u.altered_bitmap = BeautyCamera.this.combineImagesNine((Bitmap) mp4u.collage_bitmap_array_list.get(0), (Bitmap) mp4u.collage_bitmap_array_list.get(1), (Bitmap) mp4u.collage_bitmap_array_list.get(2), (Bitmap) mp4u.collage_bitmap_array_list.get(3), (Bitmap) mp4u.collage_bitmap_array_list.get(4), (Bitmap) mp4u.collage_bitmap_array_list.get(5), (Bitmap) mp4u.collage_bitmap_array_list.get(6), (Bitmap) mp4u.collage_bitmap_array_list.get(7), (Bitmap) mp4u.collage_bitmap_array_list.get(8));
                    }
                    if (mp4u.collage_bitmap_array_list.size() == mp4u.cameraCollage_cat) {
                        BeautyCamera.this.saving_bitmap();
                        return;
                    }
                    BeautyCamera.collage_indication.setImageResource(BeautyCamera.this.img_source[mp4u.collage_bitmap_array_list.size()]);
                    BeautyCamera.this.onResume();
                }
            }, null);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(" : ");
            sb.append(e);
            Log.e("EXCEPTION ", sb.toString());
        }
    }
    public Bitmap combineImagesTwo(Bitmap bitmap2, Bitmap bitmap3) {
        int i;
        int i2;
        if (bitmap2.getWidth() > bitmap3.getWidth()) {
            i2 = bitmap2.getWidth() + bitmap3.getWidth();
            i = bitmap2.getHeight();
        } else {
            i2 = bitmap3.getWidth() + bitmap3.getWidth();
            i = bitmap2.getHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) bitmap2.getWidth(), 0.0f, null);
        return createBitmap;
    }
    public Bitmap combineImagesThree(Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth() + bitmap3.getWidth() + bitmap4.getWidth(), Math.max(Math.max(bitmap2.getWidth(), bitmap3.getWidth()), bitmap4.getWidth()), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) bitmap2.getWidth(), 0.0f, null);
        canvas.drawBitmap(bitmap4, (float) (bitmap2.getWidth() + bitmap3.getWidth()), 0.0f, null);
        return createBitmap;
    }

    public Bitmap combineImagesFour(Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth() + bitmap3.getWidth(), bitmap2.getHeight() + bitmap3.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) bitmap2.getWidth(), 0.0f, null);
        canvas.drawBitmap(bitmap4, 0.0f, (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap5, (float) bitmap2.getWidth(), (float) bitmap2.getHeight(), null);
        return createBitmap;
    }

    public Bitmap combineImagesSix(Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Bitmap bitmap7) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth() + bitmap3.getWidth() + bitmap4.getWidth(), bitmap2.getHeight() + bitmap3.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) bitmap2.getWidth(), 0.0f, null);
        canvas.drawBitmap(bitmap4, (float) (bitmap2.getWidth() + bitmap3.getWidth()), 0.0f, null);
        canvas.drawBitmap(bitmap5, 0.0f, (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap6, (float) bitmap2.getWidth(), (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap7, (float) (bitmap2.getWidth() + bitmap3.getWidth()), (float) bitmap2.getHeight(), null);
        return createBitmap;
    }

    public Bitmap combineImagesNine(Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Bitmap bitmap7, Bitmap bitmap8, Bitmap bitmap9, Bitmap bitmap10) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth() + bitmap3.getWidth() + bitmap4.getWidth(), bitmap2.getHeight() + bitmap3.getHeight() + bitmap4.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) bitmap2.getWidth(), 0.0f, null);
        canvas.drawBitmap(bitmap4, (float) (bitmap2.getWidth() + bitmap3.getWidth()), 0.0f, null);
        canvas.drawBitmap(bitmap5, 0.0f, (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap6, (float) bitmap2.getWidth(), (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap7, (float) (bitmap2.getWidth() + bitmap3.getWidth()), (float) bitmap2.getHeight(), null);
        canvas.drawBitmap(bitmap8, 0.0f, (float) (bitmap2.getHeight() + bitmap3.getHeight()), null);
        canvas.drawBitmap(bitmap9, (float) bitmap2.getWidth(), (float) (bitmap2.getHeight() + bitmap3.getHeight()), null);
        canvas.drawBitmap(bitmap10, (float) (bitmap2.getWidth() + bitmap3.getWidth()), (float) (bitmap2.getHeight() + bitmap3.getHeight()), null);
        return createBitmap;
    }

    public void saving_bitmap() {
        new AsyncTask<Void, Void, Void>() {
            String path_text;


            public void onPreExecute() {
                BeautyCamera.collage_indication.setVisibility(8);
                if (!BeautyCamera.this.progressDialog.isShowing()) {
                    BeautyCamera.this.progressDialog.show();
                }
                if (!mp4u.newDir.exists()) {
                    try {
                        mp4u.newDir.mkdirs();
                    } catch (Exception unused) {
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(mp4u.newDir);
                sb.append("/IMG_");
                sb.append(BeautyCamera.this.get_name());
                sb.append(".jpg");
                this.path_text = sb.toString();
              //  mp4u.newDir = this.path_text;
                mp4u.path_saved = this.path_text;
                Log.e("STATUS", "PRE");
            }


            public Void doInBackground(Void... voidArr) {
                Log.e("STATUS", "BACK");
                if (mp4u.newDir.exists()) {
                    BeautyCamera.this.saveBitmap(mp4u.altered_bitmap, new File(this.path_text));
                }
                try {
                    MediaScannerConnection.scanFile(BeautyCamera.this, new String[]{this.path_text.toString()}, null, new OnScanCompletedListener() {
                        public void onScanCompleted(String str, Uri uri) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Scanned ");
                            sb.append(str);
                            sb.append(":");
                            Log.i("ExternalStorage", sb.toString());
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("-> uri=");
                            sb2.append(uri);
                            Log.i("ExternalStorage", sb2.toString());
                        }
                    });
                } catch (Exception unused) {
                }
                return null;
            }


            public void onPostExecute(Void voidR) {
                Log.e("STATUS", "POST");
                BeautyCamera.this.progressDialog.dismiss();
                if (new File(this.path_text).exists()) {
                    Intent intent = new Intent(BeautyCamera.this, ShareActivity.class);
                    intent.putExtra("path", path_text.toString());
                    BeautyCamera.this.startActivity(intent);



                    return;
                }
                BeautyCamera.this.finish();
            }
        }.execute(new Void[0]);
    }

    private Bitmap images_with_logo(Bitmap bitmap2, Bitmap bitmap3) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), bitmap2.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap3, (float) ((bitmap2.getWidth() - bitmap3.getWidth()) - 10), (float) ((bitmap2.getHeight() - bitmap3.getHeight()) - 10), null);
        return createBitmap;
    }

    public String saveBitmap(Bitmap bitmap2, File file2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap2.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public String get_name() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        int i4 = instance.get(10);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(i));
        sb.append(Integer.toString(i2));
        sb.append("_");
        sb.append(Integer.toString(i3));
        sb.append(Integer.toString(i4));
        sb.append(Integer.toString(i5));
        sb.append(Integer.toString(i6));
        return sb.toString();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 25) {
            return true;
        }
        if (i != 24) {
            return super.onKeyDown(i, keyEvent);
        }
        save_image();
        return true;
    }
}
