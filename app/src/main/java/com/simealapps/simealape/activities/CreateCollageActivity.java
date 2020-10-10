package com.simealapps.simealape.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.NinePatchDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commit451.nativestackblur.NativeStackBlur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.simealapps.simealape.utils.AdmobApplication;
import com.simealapps.simealape.R;
import com.simealapps.simealape.adapter.CollageImageAdapter;
import com.simealapps.simealape.adapter.ColorPickerAdapter;
import com.simealapps.simealape.adapter.MyRecylceAdapterBase;
import com.simealapps.simealape.canvastextview.ApplyTextInterface;
import com.simealapps.simealape.canvastextview.CustomRelativeLayout;
import com.simealapps.simealape.canvastextview.SingleTapInterface;
import com.simealapps.simealape.canvastextview.TextDataItem;
import com.simealapps.simealape.collagelist.Collage;
import com.simealapps.simealape.collagelist.CollageLayout;
import com.simealapps.simealape.collagelist.MaskPair;
import com.simealapps.simealape.fragments.FullEffectFragment;
import com.simealapps.simealape.fragments.WriteTextFragment;
import com.simealapps.simealape.image.ImageBlurNormal;
import com.simealapps.simealape.utils.LibUtility;
import com.simealapps.simealape.utils.Parameter;
import com.simealapps.simealape.utils.RotationGestureDetector;
import com.simealapps.simealape.utils.Shape;
import com.simealapps.simealape.utils.ShapeLayout;
import com.simealapps.simealape.utils.Utility;
import com.simealapps.simealape.utils.Utils;

public class CreateCollageActivity extends FragmentActivity {
    public static final int INDEX_COLLAGE = 0;
    public static final int INDEX_COLLAGE_BACKGROUND = 1;
    public static final int INDEX_COLLAGE_BLUR = 4;
    public static final int INDEX_COLLAGE_INVISIBLE_VIEW = 5;
    public static final int INDEX_COLLAGE_RATIO = 3;
    public static final int INDEX_COLLAGE_SPACE = 2;
    public static final int TAB_SIZE = 6;
    private static final String TAG = "CreateCollageActivity";
    private static final float UPPER_SIZE_FOR_LOAD = 1500.0f;
    int RATIO_BUTTON_SIZE = 11;


    Bitmap[] bitmapList;
    Bitmap btmDelete;
    Bitmap btmScale;
    CustomRelativeLayout canvasText;
    CollageImageAdapter collageAdapter;
    CollageView collageView;
    LinearLayout colorContainer;
    ViewGroup contextFooter;
    WriteTextFragment.FontChoosedListener fontChoosedListener = new WriteTextFragment.FontChoosedListener() {
        public void onOk(TextDataItem textData) {
            if (canvasText == null) {
                addCanvasTextView();
            }
            canvasText.addTextView(textData);
            getSupportFragmentManager().beginTransaction().remove(fontFragment).commit();
            Log.e(CreateCollageActivity.TAG, "onOK called");
        }
    };
    WriteTextFragment fontFragment;
    FullEffectFragment fullEffectFragment;
    int height;

    boolean isScrapBook = false;
    LinearLayout linearAdsBanner;


    public RotationGestureDetector mRotationDetector;
    SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_round) {
                if (collageView != null) {
                    collageView.setCornerRadius((float) progress);
                }
            } else if (id == R.id.seekbar_padding) {
                if (collageView != null) {
                    collageView.setPathPadding(collageView.currentCollageIndex, (float) progress);
                }
            } else if (id == R.id.seekbar_size) {
                if (collageView != null) {
                    collageView.setCollageSize(collageView.sizeMatrix, progress);
                }
            } else if (id == R.id.seekbar_collage_blur) {
                float f = ((float) progress) / 4.0f;
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekBar.getId() == R.id.seekbar_collage_blur) {
                float radius = ((float) seekBar.getProgress()) / 4.0f;
                if (radius > 25.0f) {
                    radius = 25.0f;
                }
                if (radius < 0.0f) {
                    radius = 0.0f;
                }
                Log.e(CreateCollageActivity.TAG, "blur radius " + radius);
                collageView.setBlurBitmap((int) radius, false);
            }
        }
    };
    RelativeLayout mainLayout;
    float mulX = 1.0f;
    float mulY = 1.0f;
    NinePatchDrawable npd;
    Parameter[] parameterList;
    ArrayList<MyRecylceAdapterBase> patternAdapterList = new ArrayList<>();
    Button[] ratioButtonArray;
    RecyclerView recyclerViewCollage;
    AlertDialog saveImageAlert;
    SeekBar seekBarPadding;
    SeekBar seekBarRound;
    SeekBar seekbarBlur;
    SeekBar seekbarSize;
    View selectFilterTextView;
    boolean selectImageForAdj = false;
    View selectSwapTextView;
    boolean showText = false;
    public Animation slideLeftIn;

    public Animation slideLeftOut;

    public Animation slideRightIn;

    public Animation slideRightOut;
    boolean swapMode = false;
    View[] tabButtonList;
    ArrayList<TextDataItem> textDataList = new ArrayList<>();
    ViewFlipper viewFlipper;
    int width;


    private AdmobApplication admobApplication;
    private RelativeLayout bannerAdContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        setContentView(R.layout.create_collage_activity);

        Display display = getWindowManager().getDefaultDisplay();
        this.width = display.getWidth();
        this.height = display.getHeight();

        this.linearAdsBanner = (LinearLayout) findViewById(R.id.linearAds);
        loadAds();



        Bundle extras = getIntent().getExtras();
        int arraySize = getCollageSize(extras);
        this.seekBarRound = (SeekBar) findViewById(R.id.seekbar_round);
        this.seekBarRound.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarPadding = (SeekBar) findViewById(R.id.seekbar_padding);
        this.seekBarPadding.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarSize = (SeekBar) findViewById(R.id.seekbar_size);
        this.seekbarSize.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarBlur = (SeekBar) findViewById(R.id.seekbar_collage_blur);
        this.seekbarBlur.setOnSeekBarChangeListener(this.mSeekBarListener);
        RecyclerView recyclerViewColor = (RecyclerView) findViewById(R.id.recyclerViewColor);
        this.recyclerViewCollage = (RecyclerView) findViewById(R.id.recyclerViewCollage);
        int colorDefault = getResources().getColor(R.color.view_flipper_bg_color);
        int colorSelected = getResources().getColor(R.color.footer_button_color_pressed);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        this.recyclerViewCollage.setLayoutManager(layoutManager);
        collageAdapter = new CollageImageAdapter(CreateCollageActivity.this,Collage.collageIconArray[arraySize - 1],
                new CollageImageAdapter.CurrentCollageIndexChangedListener() {
            public void onIndexChanged(int i) {
                collageView.setCurrentCollageIndex(i);
            }
        }, colorDefault, colorSelected, false, true);
        recyclerViewCollage.setAdapter(this.collageAdapter);
        recyclerViewCollage.setItemAnimator(new DefaultItemAnimator());
        viewFlipper = (ViewFlipper) findViewById(R.id.collage_view_flipper);
        viewFlipper.setDisplayedChild(5);
        createAdapterList(colorDefault, colorSelected);
        RecyclerView recyclerViewPattern = (RecyclerView) findViewById(R.id.recyclerViewPattern);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        recyclerViewPattern.setLayoutManager(linearLayoutManager);
        final RecyclerView recyclerView = recyclerViewColor;
        recyclerViewPattern.setAdapter(new CollageImageAdapter(CreateCollageActivity.this,Utils.patternResIdList3,
                new CollageImageAdapter.CurrentCollageIndexChangedListener() {
            public void onIndexChanged(int position) {
                collageView.backgroundMode = 0;
                if (position == 0) {
                    collageView.setPatternPaint(-1);
                    return;
                }
                int newPos = position - 1;
                if (patternAdapterList.get(newPos) != recyclerView.getAdapter()) {
                    recyclerView.setAdapter(patternAdapterList.get(newPos));
                    ((MyRecylceAdapterBase) patternAdapterList.get(newPos)).setSelectedPositinVoid();
                } else {
                    ((MyRecylceAdapterBase) patternAdapterList.get(newPos)).setSelectedPositinVoid();
                    ((MyRecylceAdapterBase) patternAdapterList.get(newPos)).notifyDataSetChanged();
                }
                colorContainer.setVisibility(View.VISIBLE);
            }
        }, colorDefault, colorSelected, false, false));
        recyclerViewPattern.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManagerColor = new LinearLayoutManager(this);
        layoutManagerColor.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewColor.setLayoutManager(layoutManagerColor);
        recyclerViewColor.setAdapter(new ColorPickerAdapter(new CollageImageAdapter.CurrentCollageIndexChangedListener() {
            public void onIndexChanged(int color) {
                collageView.setPatternPaintColor(color);
            }
        }, colorDefault, colorSelected));
        recyclerViewColor.setItemAnimator(new DefaultItemAnimator());
        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.collage_footer);
        horizontalScrollView.bringToFront();
        horizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                horizontalScrollView.scrollTo(horizontalScrollView.getChildAt(0).getMeasuredWidth(), 0);
            }
        }, 50);
        horizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                horizontalScrollView.fullScroll(17);
            }
        }, 600);
        new BitmapWorkerTask().execute(new Bundle[]{extras, savedInstanceState});
    }

    private void loadAds() {

        bannerAdContainer = (RelativeLayout) findViewById(R.id.bannerAdContainer);
        admobApplication = (AdmobApplication) getApplication();
        admobApplication.initializeAdsSdk();
        admobApplication.createGoogleBannerAd(bannerAdContainer);



    }

    class BitmapWorkerTask extends AsyncTask<Bundle, Void, Void> {
        int arraySize;
        Bundle data;
        ProgressDialog progressDialog;
        Bundle savedInstanceState;

        BitmapWorkerTask() {
        }
        public void onPreExecute() {
            this.progressDialog = new ProgressDialog(CreateCollageActivity.this);
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage("loading images!");
            this.progressDialog.show();
        }
        public Void doInBackground(Bundle... params) {
            this.data = params[0];
            this.savedInstanceState = params[1];
            isScrapBook = this.data.getBoolean("is_scrap_book", false);
            long[] selectedImageList = this.data.getLongArray("photo_id_list");
            int[] selectedImageOrientationList = this.data.getIntArray("photo_orientation_list");
            this.arraySize = 0;
            if (selectedImageList == null) {
                String selectedImagePath = this.data.getString("selected_image_path");
                if (selectedImagePath != null) {
                    this.arraySize = 1;
                    bitmapList = new Bitmap[this.arraySize];
                    int maxDivider = this.arraySize;
                    if (maxDivider < 3) {
                        maxDivider = 3;
                    }
                    bitmapList[0] = Utils.decodeFile(selectedImagePath, Utility.maxSizeForDimension(CreateCollageActivity.this, maxDivider, CreateCollageActivity.UPPER_SIZE_FOR_LOAD), isScrapBook);
                }
            } else {
                this.arraySize = selectedImageList.length;
                bitmapList = new Bitmap[this.arraySize];
                int maxDivider2 = this.arraySize;
                if (maxDivider2 < 3) {
                    maxDivider2 = 3;
                }
                int requiredSize = Utility.maxSizeForDimension(CreateCollageActivity.this, maxDivider2, CreateCollageActivity.UPPER_SIZE_FOR_LOAD);
                int loadingImageError = 0;
                for (int i = 0; i < this.arraySize; i++) {
                    Bitmap bitmap = Utils.getScaledBitmapFromId(CreateCollageActivity.this, selectedImageList[i], selectedImageOrientationList[i], requiredSize, isScrapBook);
                    if (bitmap != null) {
                        bitmapList[i] = bitmap;
                    } else {
                        loadingImageError++;
                    }
                }
                if (loadingImageError > 0) {
                    int newSize = this.arraySize - loadingImageError;
                    Bitmap[] arr = new Bitmap[newSize];
                    int j = 0;
                    for (int i2 = 0; i2 < this.arraySize; i2++) {
                        if (bitmapList[i2] != null) {
                            arr[j] = bitmapList[i2];
                            j++;
                        }
                    }
                    this.arraySize = newSize;
                    bitmapList = arr;
                }
            }
            parameterList = new Parameter[this.arraySize];
            for (int i3 = 0; i3 < parameterList.length; i3++) {
                parameterList[i3] = new Parameter();
            }
            return null;
        }


        public void onPostExecute(Void v) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
            }
            if (this.arraySize <= 0) {
                Toast msg = Toast.makeText(CreateCollageActivity.this, "Couldn't load images!", Toast.LENGTH_SHORT);
                msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
                msg.show();
                finish();
                return;
            }
            if (Collage.collageIconArray[bitmapList.length - 1] != collageAdapter.iconList) {
                collageAdapter.setData(Collage.collageIconArray[bitmapList.length - 1]);
                collageAdapter.notifyDataSetChanged();
                Log.e(CreateCollageActivity.TAG, "change collage icons");
            }
            if (isScrapBook) {
                btmDelete = BitmapFactory.decodeResource(getResources(), R.drawable.scrapbook_remove);
                btmScale = BitmapFactory.decodeResource(getResources(), R.drawable.scrapbook_scale);
            }
            if (isScrapBook) {
                npd = (NinePatchDrawable) ContextCompat.getDrawable(CreateCollageActivity.this, R.drawable.shadow_7);
                Log.e(CreateCollageActivity.TAG, "ndp width " + npd.getMinimumHeight());
            }
            collageView = new CollageView(CreateCollageActivity.this, width, height);
            mainLayout = (RelativeLayout) findViewById(R.id.collage_main_layout);
            mainLayout.addView(collageView);
            viewFlipper.bringToFront();
            linearAdsBanner.bringToFront();
            slideLeftIn = AnimationUtils.loadAnimation(CreateCollageActivity.this, R.anim.slide_in_left);
            slideLeftOut = AnimationUtils.loadAnimation(CreateCollageActivity.this, R.anim.slide_out_left);
            slideRightIn = AnimationUtils.loadAnimation(CreateCollageActivity.this, R.anim.slide_in_right);
            slideRightOut = AnimationUtils.loadAnimation(CreateCollageActivity.this, R.anim.slide_out_right);
            addEffectFragment();
            if (this.arraySize == 1) {
                setVisibilityForSingleImage();
            }
            if (isScrapBook) {
                setVisibilityForScrapbook();
            }
            viewFlipper = (ViewFlipper) findViewById(R.id.collage_view_flipper);
            viewFlipper.bringToFront();
            findViewById(R.id.collage_footer).bringToFront();
            findViewById(R.id.collage_header).bringToFront();
            contextFooter = (ViewGroup) findViewById(R.id.collage_context_menu);
            contextFooter.bringToFront();
            selectSwapTextView = findViewById(R.id.select_image_swap);
            selectSwapTextView.bringToFront();
            selectSwapTextView.setVisibility(View.INVISIBLE);
            selectFilterTextView = findViewById(R.id.select_image_filter);
            selectFilterTextView.bringToFront();
            selectFilterTextView.setVisibility(View.INVISIBLE);
        }
    }


    public int getCollageSize(Bundle extras) {
        long[] selectedImageList = extras.getLongArray("photo_id_list");
        if (selectedImageList == null) {
            return 1;
        }
        return selectedImageList.length;
    }

    class CollageView extends View {
        public static final int BACKGROUND_BLUR = 1;
        public static final int BACKGROUND_PATTERN = 0;
        private static final int INVALID_POINTER_ID = 1;
        public static final int PATTERN_SENTINEL = -1;
        static final float RATIO_CONSTANT = 1.25f;
        private static final int UPPER_SIZE_LIMIT = 2048;
        float MIN_ZOOM;
        RectF above;
        int animEpsilon = 20;
        int animHalfTime = ((this.animationLimit / 2) + 1);
        int animSizeSeekbarProgress = 0;
        boolean animate = false;
        int animationCount = 0;
        int animationDurationLimit = 50;
        int animationLimit = 31;
        private Runnable animator;
        int backgroundMode;
        Bitmap blurBitmap;
        ImageBlurNormal blurBuilderNormal;
        int blurRadius = 14;
        RectF blurRectDst;
        Rect blurRectSrc;
        Paint borderPaint;
        RectF bottom;
        RectF bottomLeft;
        RectF bottomRight;
        Paint circlePaint;
        float cornerRadius = 0.0f;
        int currentCollageIndex = 0;
        RectF drawingAreaRect;
        final float epsilon;
        float finalAngle;
        Bitmap frameBitmap;
        int frameDuration = 10;
        RectF frameRect;
        Matrix identityMatrix = new Matrix();
        boolean isInCircle;
        boolean isOnCross;
        RectF left;
        private int mActivePointerId;
        float mLastTouchX;
        float mLastTouchY;
        private ScaleGestureDetector mScaleDetector;
        float mScaleFactor;
        private GestureDetectorCompat mTouchDetector;
        Bitmap[] maskBitmapArray;
        int[] maskResIdList = {R.drawable.mask_butterfly, R.drawable.mask_cloud, R.drawable.mask_clover, R.drawable.mask_leaf, R.drawable.mask_left_foot, R.drawable.mask_diamond, R.drawable.mask_santa, R.drawable.mask_snowman, R.drawable.mask_paw, R.drawable.mask_egg, R.drawable.mask_twitter, R.drawable.mask_circle, R.drawable.mask_hexagon, R.drawable.mask_heart};
        float[] matrixValues;
        boolean move;
        int offsetX;
        int offsetY;
        boolean orthogonal;
        float paddingDistance = 0.0f;
        Paint paint = new Paint();
        Paint paintGray;
        Bitmap patternBitmap;
        Paint patternPaint = new Paint(1);
        int previousIndex;
        float[] pts;
        Rect rectAnim;
        RectF right;
        RotationGestureDetector.OnRotationGestureListener rotateListener;
        Shape scaleShape;
        int screenHeight;
        int screenWidth;
        int shapeIndex = -1;
        List<ShapeLayout> shapeLayoutList = new ArrayList();
        Matrix sizeMatrix = new Matrix();
        Matrix sizeMatrixSaved;
        float sizeScale = 1.0f;
        ArrayList<Float> smallestDistanceList = new ArrayList<>();
        private float startAngle;
        Matrix startMatrix;
        long startTime = System.nanoTime();
        Matrix textMatrix;
        RectF topLeft;
        RectF topRight;
        float[] values;
        float xscale = 1.0f;
        float yscale = 1.0f;
        PointF zoomStart;

        class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
            private static final String DEBUG_TAG = "Gestures";

            MyGestureListener() {
            }

            public boolean onSingleTapConfirmed(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapConfirmed: ");
                return true;
            }

            public boolean onSingleTapUp(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapUp: ");
                if (!CollageView.this.isOnCross) {
                    collageView.selectCurrentShape(event.getX(), event.getY(), true);
                }
                return true;
            }
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            private ScaleListener() {
            }

            public boolean onScale(ScaleGestureDetector detector) {
                if (CollageView.this.shapeIndex >= 0) {
                    CollageView.this.mScaleFactor = detector.getScaleFactor();
                    detector.isInProgress();
                    CollageView.this.mScaleFactor = Math.max(0.1f, Math.min(CollageView.this.mScaleFactor, 5.0f));
                    CollageView.this.scaleShape = ((ShapeLayout) CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.this.shapeIndex];
                    if (isScrapBook) {
                        CollageView.this.scaleShape.bitmapMatrixScaleScrapBook(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor);
                    } else {
                        CollageView.this.scaleShape.bitmapMatrixScale(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor, CollageView.this.scaleShape.bounds.centerX(), CollageView.this.scaleShape.bounds.centerY());
                    }
                    CollageView.this.invalidate();
                    CollageView.this.requestLayout();
                }
                return true;
            }
        }

        @SuppressLint({"NewApi"})
        public CollageView(Context context, int width, int height) {
            super(context);
            this.animator = new Runnable() {
                public void run() {
                    boolean scheduleNewFrame = false;
                    int iter = ((int) (((float) (System.nanoTime() - CollageView.this.startTime)) / 1000000.0f)) / CollageView.this.animationDurationLimit;
                    if (iter <= 0) {
                        iter = 1;
                    }
                    if (CollageView.this.animationCount == 0) {
                        collageView.animationCount++;
                    } else {
                        collageView.animationCount += iter;
                    }
                    CollageView.this.setCollageSize(CollageView.this.sizeMatrix, CollageView.this.animSize(CollageView.this.animationCount));
                    if (CollageView.this.animationCount < CollageView.this.animationLimit) {
                        scheduleNewFrame = true;
                    } else {
                        CollageView.this.animate = false;
                    }
                    if (scheduleNewFrame) {
                        CollageView.this.postDelayed(this, (long) CollageView.this.frameDuration);
                    } else {
                        CollageView.this.sizeMatrix.set(CollageView.this.sizeMatrixSaved);
                    }
                    ((ShapeLayout) CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex)).shapeArr[0].f508r.roundOut(CollageView.this.rectAnim);
                    CollageView.this.invalidate(CollageView.this.rectAnim);
                    CollageView.this.startTime = System.nanoTime();
                }
            };
            this.rectAnim = new Rect();
            this.textMatrix = new Matrix();
            this.blurRectDst = new RectF();
            this.drawingAreaRect = new RectF();
            this.above = new RectF();
            this.left = new RectF();
            this.right = new RectF();
            this.bottom = new RectF();
            this.move = false;
            this.paintGray = new Paint(1);
            this.mActivePointerId = 1;
            this.zoomStart = new PointF();
            this.startMatrix = new Matrix();
            this.startAngle = 0.0f;
            this.MIN_ZOOM = 0.1f;
            this.isInCircle = false;
            this.isOnCross = false;
            this.orthogonal = false;
            this.mScaleFactor = 1.0f;
            this.matrixValues = new float[9];
            this.finalAngle = 0.0f;
            this.epsilon = 4.0f;
            this.rotateListener = new RotationGestureDetector.OnRotationGestureListener() {
                public void OnRotation(RotationGestureDetector rotationGestureDetector) {
                    if (CollageView.this.shapeIndex >= 0) {
                        float angle = rotationGestureDetector.getAngle();
                        CollageView.this.scaleShape = ((ShapeLayout) CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.this.shapeIndex];
                        float rotation = CollageView.this.getMatrixRotation(CollageView.this.scaleShape.bitmapMatrix);
                        if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(CollageView.this.finalAngle - angle) < 4.0f) {
                            CollageView.this.orthogonal = true;
                            return;
                        }
                        if (Math.abs((rotation - CollageView.this.finalAngle) + angle) < 4.0f) {
                            angle = CollageView.this.finalAngle - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(90.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 90.0f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(180.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 180.0f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(-180.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.024902344f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(-90.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.049804688f) - rotation;
                            CollageView.this.orthogonal = true;
                        } else {
                            CollageView.this.orthogonal = false;
                        }
                        CollageView.this.scaleShape.bitmapMatrixRotate(CollageView.this.finalAngle - angle);
                        CollageView.this.finalAngle = angle;
                        CollageView.this.invalidate();
                        CollageView.this.requestLayout();
                    }
                }
            };
            this.values = new float[9];
            this.backgroundMode = 0;
            this.blurRectSrc = new Rect();
            this.maskBitmapArray = new Bitmap[this.maskResIdList.length];
            this.borderPaint = new Paint(1);
            this.borderPaint.setColor(getResources().getColor(R.color.blue));
            this.borderPaint.setStyle(Paint.Style.STROKE);
            this.borderPaint.setStrokeWidth(10.0f);
            this.screenWidth = width;
            this.screenHeight = height;
            this.circlePaint = new Paint();
            this.circlePaint.setColor(SupportMenu.CATEGORY_MASK);
            this.identityMatrix.reset();
            this.topLeft = new RectF((float) (width * 0), (float) (height * 0), 0.5f * ((float) width), 0.5f * ((float) height));
            this.topRight = new RectF(0.5f * ((float) width), 0.0f * ((float) height), 1.0f * ((float) width), 0.5f * ((float) height));
            this.bottomLeft = new RectF((float) (width * 0), 0.5f * ((float) height), 0.5f * ((float) width), 1.0f * ((float) height));
            this.bottomRight = new RectF(0.5f * ((float) width), 0.5f * ((float) height), 1.0f * ((float) width), 1.0f * ((float) height));
            Path pathTopLeft = new Path();
            Path pathTopRight = new Path();
            Path pathBottomLeft = new Path();
            Path pathBottomRight = new Path();
            pathTopLeft.addRect(this.topLeft, Path.Direction.CCW);
            pathTopRight.addRect(this.topRight, Path.Direction.CCW);
            pathBottomLeft.addRect(this.bottomLeft, Path.Direction.CCW);
            pathBottomRight.addRect(this.bottomRight, Path.Direction.CCW);
            this.mTouchDetector = new GestureDetectorCompat(context, new MyGestureListener());
            this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            mRotationDetector = new RotationGestureDetector(this.rotateListener);
            calculateOffset();
            this.patternPaint = new Paint(1);
            this.patternPaint.setColor(-1);
            createShapeList(bitmapList.length, width, height);
            this.paintGray.setColor(-12303292);
        }

        private void calculateOffset() {
            PointF scale = getRatio();
            this.offsetX = (int) ((((float) width) - (scale.x * ((float) width))) / 2.0f);
            this.offsetY = (int) ((((float) height) - (scale.y * ((float) width))) / 2.0f);
        }

        public void setCropBitmap(int left2, int top, int right2, int bottom2) {
            if (this.shapeIndex >= 0) {
                Bitmap sourceBitmap = bitmapList[this.shapeIndex];
                boolean isFilter = sourceBitmap != ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[this.shapeIndex].getBitmap();
                if (isFilter) {
                    doCrop(left2, top, right2, bottom2, sourceBitmap, false, false);
                    doCrop(left2, top, right2, bottom2, ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[this.shapeIndex].getBitmap(), true, true);
                } else {
                    doCrop(left2, top, right2, bottom2, sourceBitmap, false, true);
                }
                if (!(!isFilter || parameterList == null || parameterList[this.shapeIndex] == null)) {
                    parameterList[this.shapeIndex].setId(Parameter.uniqueId.getAndIncrement());
                }
                invalidate();
            }
        }

        public void doCrop(int left2, int top, int right2, int bottom2, Bitmap sourceBitmap, boolean isFilter, boolean last) {
            Bitmap sourceBitmap2;
            Bitmap localCropBtm = sourceBitmap;
            int bitmapWidth = sourceBitmap.getWidth();
            int bitmapHeight = sourceBitmap.getHeight();
            if (right2 > bitmapWidth) {
                right2 = bitmapWidth;
            }
            if (bottom2 > bitmapHeight) {
                bottom2 = bitmapHeight;
            }
            if (right2 - left2 > 0 && bottom2 - top > 0) {
                if (Build.VERSION.SDK_INT < 12) {
                    sourceBitmap2 = ImageBlurNormal.createCroppedBitmap(localCropBtm, left2, top, right2 - left2, bottom2 - top, false);
                } else {
                    sourceBitmap2 = Bitmap.createBitmap(localCropBtm, left2, top, right2 - left2, bottom2 - top);
                }
                if (localCropBtm != sourceBitmap2) {
                    localCropBtm.recycle();
                }
                if (!isFilter) {
                    bitmapList[this.shapeIndex] = sourceBitmap2;
                }
                if (last) {
                    int i = 0;
                    while (this.shapeLayoutList.size() > 0) {
                        ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex].setBitmap(sourceBitmap2, false);
                        if (isScrapBook) {
                            ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex].resetDashPaths();
                        }
                        i += 0;
                    }
                }
            }
        }

        @SuppressLint("WrongThread")
        public String saveBitmap(int width, int height) {
            int btmWidth = (int) (((float) width) * collageView.xscale);
            int btmHeight = (int) (((float) width) * collageView.yscale);
            float btmScale = ((float) Utils.maxSizeForSave(CreateCollageActivity.this, 2048.0f)) / ((float) Math.max(btmWidth, btmHeight));
            int newBtmWidth = (int) (((float) btmWidth) * btmScale);
            int newBtmHeight = (int) (((float) btmHeight) * btmScale);
            if (newBtmWidth <= 0) {
                newBtmWidth = btmWidth;
                Log.e(CreateCollageActivity.TAG, "newBtmWidth");
            }
            if (newBtmHeight <= 0) {
                newBtmHeight = btmHeight;
                Log.e(CreateCollageActivity.TAG, "newBtmHeight");
            }
            Bitmap savedBitmap = Bitmap.createBitmap(newBtmWidth, newBtmHeight, Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(savedBitmap);
            ShapeLayout arr = (ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex);
            Matrix sizeMat = new Matrix();
            sizeMat.reset();
            sizeMat.preScale(btmScale, btmScale);
            bitmapCanvas.setMatrix(sizeMat);
            if (this.backgroundMode == 0) {
                bitmapCanvas.drawRect(0.0f, 0.0f, (float) btmWidth, (float) btmHeight, this.patternPaint);
            }
            if (this.blurBitmap != null && !this.blurBitmap.isRecycled() && this.backgroundMode == 1) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) btmWidth, (float) btmHeight);
                bitmapCanvas.drawBitmap(this.blurBitmap, this.blurRectSrc, rectF, this.paint);
            }
            sizeMat.postScale(this.sizeScale, this.sizeScale, ((float) newBtmWidth) / 2.0f, ((float) newBtmHeight) / 2.0f);
            sizeMat.preTranslate((float) (-this.offsetX), (float) (-this.offsetY));
            bitmapCanvas.setMatrix(sizeMat);
            int q = bitmapCanvas.saveLayer(((float) (-width)) / this.sizeScale, ((float) (-height)) / this.sizeScale, ((float) this.offsetX) + (((float) width) / this.sizeScale), ((float) this.offsetY) + (((float) height) / this.sizeScale), null, Canvas.ALL_SAVE_FLAG);
            for (int i = 0; i < arr.shapeArr.length; i++) {
                boolean drawPorterClear = false;
                if (i == arr.getClearIndex()) {
                    drawPorterClear = true;
                }
                Log.e(CreateCollageActivity.TAG, "drawPorterClear " + drawPorterClear);
                if (isScrapBook) {
                    arr.shapeArr[i].drawShapeForScrapBook(bitmapCanvas, newBtmWidth, newBtmHeight, false, false);
                } else {
                    arr.shapeArr[i].drawShapeForSave(bitmapCanvas, newBtmWidth, newBtmHeight, q, drawPorterClear);
                }
            }
            if (textDataList != null) {
                for (int i2 = 0; i2 < textDataList.size(); i2++) {
                    Matrix mat = new Matrix();
                    mat.set(((TextDataItem) textDataList.get(i2)).imageSaveMatrix);
                    mat.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
                    mat.postScale(btmScale, btmScale);
                    bitmapCanvas.setMatrix(mat);
                    bitmapCanvas.drawText(((TextDataItem) textDataList.get(i2)).message, ((TextDataItem) textDataList.get(i2)).xPos, ((TextDataItem) textDataList.get(i2)).yPos, ((TextDataItem) textDataList.get(i2)).textPaint);
                }
            }
            bitmapCanvas.restoreToCount(q);
            String resultPath = String.valueOf(Environment.getExternalStorageDirectory().toString()) + getString(R.string.directory) + String.valueOf(System.currentTimeMillis()) + ".jpg";
            new File(resultPath).getParentFile().mkdirs();
            try {
                OutputStream fileOutputStream = new FileOutputStream(resultPath);
                savedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            savedBitmap.recycle();
            return resultPath;
        }


        public int getMaskIndex(int resId) {
            for (int i = 0; i < this.maskResIdList.length; i++) {
                if (resId == this.maskResIdList[i]) {
                    return i;
                }
            }
            return -1;
        }

        private void createShapeList(int shapeCount, int width, int height) {
            this.shapeLayoutList.clear();
            this.smallestDistanceList.clear();
            Collage collage = Collage.CreateCollage(shapeCount, width, width, isScrapBook);
            int size = ((CollageLayout) collage.collageLayoutList.get(0)).shapeList.size();
            Log.e(CreateCollageActivity.TAG, "bitmapList.length " + bitmapList.length);
            for (int i = 0; i < collage.collageLayoutList.size(); i++) {
                Shape[] shapeArray = new Shape[size];
                for (int j = 0; j < shapeCount; j++) {
                    boolean masked = false;
                    int resId = 0;
                    if (((CollageLayout) collage.collageLayoutList.get(i)).maskPairList != null && !((CollageLayout) collage.collageLayoutList.get(i)).maskPairList.isEmpty()) {
                        for (MaskPair maskPair : ((CollageLayout) collage.collageLayoutList.get(i)).maskPairList) {
                            if (j == maskPair.index) {
                                masked = true;
                                resId = maskPair.f2074id;
                            }
                        }
                    }
                    if (masked) {
                        Bitmap maskBitmap = null;
                        int maskIndex = getMaskIndex(resId);
                        if (maskIndex >= 0) {
                            if (this.maskBitmapArray == null) {
                                this.maskBitmapArray = new Bitmap[this.maskResIdList.length];
                            }
                            if (this.maskBitmapArray[maskIndex] == null) {
                                this.maskBitmapArray[maskIndex] = loadMaskBitmap2(resId);
                                Log.e(CreateCollageActivity.TAG, "load mask bitmap from factory");
                            } else {
                                Log.e(CreateCollageActivity.TAG, "load mask bitmap from pool");
                            }
                            maskBitmap = this.maskBitmapArray[maskIndex];
                        }
                        shapeArray[j] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i)).shapeList.get(j), bitmapList[j], null, this.offsetX, this.offsetY, maskBitmap, isScrapBook, j, false, btmDelete, btmScale, this.screenWidth);
                        if (isScrapBook) {
                            shapeArray[j].initScrapBook(npd);
                        }
                    } else {
                        shapeArray[j] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i)).shapeList.get(j), bitmapList[j], ((CollageLayout) collage.collageLayoutList.get(i)).getexceptionIndex(j), this.offsetX, this.offsetY, isScrapBook, j, false, btmDelete, btmScale, this.screenWidth);
                        if (isScrapBook) {
                            shapeArray[j].initScrapBook(npd);
                        }
                    }
                }
                this.smallestDistanceList.add(Float.valueOf(smallestDistance(shapeArray)));
                ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                shapeLayout.setClearIndex(((CollageLayout) collage.collageLayoutList.get(i)).getClearIndex());
                this.shapeLayoutList.add(shapeLayout);
            }
            if (isScrapBook) {
                return;
            }
            if (shapeCount != 1) {
                for (int index = 0; index < this.shapeLayoutList.size(); index++) {
                    setPathPadding(index, (float) getResources().getInteger(R.integer.default_space_value));
                    for (int i2 = 0; i2 < ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr.length; i2++) {
                        ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i2].setScaleMatrix(1);
                    }
                }
                setCollageSize(this.sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
            } else if (bitmapList.length == 1) {
                setCollageSize(this.sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
            }
        }


        public int setShapeScaleMatrix(int mode) {
            if (this.shapeIndex < 0) {
                return -1;
            }
            int scaleMatrix = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].setScaleMatrix(mode);
            invalidate();
            return scaleMatrix;
        }


        public void deleteBitmap(int index, int width, int height) {
            Log.e(CreateCollageActivity.TAG, "index" + index);
            Shape[] scrapBookTemp = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr;
            if (index >= 0 && index < ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length) {
                int newSize = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length - 1;
                Bitmap[] currentBitmapListTemp = new Bitmap[newSize];
                Bitmap[] bitmapListTemp = new Bitmap[newSize];
                int j = 0;
                for (int i = 0; i < currentBitmapListTemp.length + 1; i++) {
                    if (i != index) {
                        currentBitmapListTemp[j] = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[i].getBitmap();
                        bitmapListTemp[j] = bitmapList[i];
                        j++;
                    }
                }
                bitmapList[index].recycle();
                ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[index].getBitmap().recycle();
                this.shapeLayoutList.clear();
                this.smallestDistanceList.clear();
                Collage collage = Collage.CreateCollage(newSize, width, width, isScrapBook);
                int size = ((CollageLayout) collage.collageLayoutList.get(0)).shapeList.size();
                bitmapList = bitmapListTemp;
                for (int i2 = 0; i2 < collage.collageLayoutList.size(); i2++) {
                    Shape[] shapeArray = new Shape[size];
                    for (int j2 = 0; j2 < currentBitmapListTemp.length; j2++) {
                        boolean masked = false;
                        int resId = 0;
                        if (((CollageLayout) collage.collageLayoutList.get(i2)).maskPairList != null && !((CollageLayout) collage.collageLayoutList.get(i2)).maskPairList.isEmpty()) {
                            for (MaskPair maskPair : ((CollageLayout) collage.collageLayoutList.get(i2)).maskPairList) {
                                if (j2 == maskPair.index) {
                                    masked = true;
                                    resId = maskPair.f2074id;
                                }
                            }
                        }
                        if (masked) {
                            Bitmap maskBitmap = null;
                            int maskIndez = getMaskIndex(resId);
                            if (maskIndez >= 0) {
                                if (this.maskBitmapArray == null) {
                                    this.maskBitmapArray = new Bitmap[this.maskResIdList.length];
                                }
                                if (this.maskBitmapArray[maskIndez] == null) {
                                    this.maskBitmapArray[maskIndez] = loadMaskBitmap2(resId);
                                    Log.e(CreateCollageActivity.TAG, "load mask bitmap from factory");
                                } else {
                                    Log.e(CreateCollageActivity.TAG, "load mask bitmap from pool");
                                }
                                maskBitmap = this.maskBitmapArray[maskIndez];
                            }
                            shapeArray[j2] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i2)).shapeList.get(j2), currentBitmapListTemp[j2], null, this.offsetX, this.offsetY, maskBitmap, isScrapBook, j2, true, btmDelete, btmScale, this.screenWidth);
                            if (isScrapBook) {
                                shapeArray[j2].initScrapBook(npd);
                            }
                        } else {
                            shapeArray[j2] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i2)).shapeList.get(j2), currentBitmapListTemp[j2], ((CollageLayout) collage.collageLayoutList.get(i2)).getexceptionIndex(j2), this.offsetX, this.offsetY, isScrapBook, j2, true, btmDelete, btmScale, this.screenWidth);
                            if (isScrapBook) {
                                shapeArray[j2].initScrapBook(npd);
                            }
                        }
                    }
                    if (isScrapBook) {
                        for (int k = 0; k < scrapBookTemp.length; k++) {
                            if (k < index) {
                                shapeArray[k].bitmapMatrix.set(scrapBookTemp[k].bitmapMatrix);
                            }
                            if (k > index) {
                                shapeArray[k - 1].bitmapMatrix.set(scrapBookTemp[k].bitmapMatrix);
                            }
                        }
                    }
                    ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                    shapeLayout.setClearIndex(((CollageLayout) collage.collageLayoutList.get(i2)).getClearIndex());
                    this.shapeLayoutList.add(shapeLayout);
                    this.smallestDistanceList.add(Float.valueOf(smallestDistance(shapeArray)));
                }
                this.currentCollageIndex = 0;
                collageAdapter.selectedPosition = 0;
                collageAdapter.setData(Collage.collageIconArray[newSize - 1]);
                collageAdapter.notifyDataSetChanged();
                if (!isScrapBook) {
                    updateShapeListForRatio(width, height);
                }
                unselectShapes();
                for (int i3 = 0; i3 < ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length; i3++) {
                    Log.e(CreateCollageActivity.TAG, "i " + i3 + "is recylced " + ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[i3].getBitmap().isRecycled());
                }
                invalidate();
                if (currentBitmapListTemp.length == 1) {
                    setVisibilityForSingleImage();
                }
                if (newSize == 1) {
                    setPathPadding(0, 0.0f);
                    if (this.sizeScale == 1.0f && !isScrapBook) {
                        setCollageSize(this.sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
                    }
                }
            }
        }


        public Bitmap loadMaskBitmap2(int resId) {
            return convertToAlphaMask(BitmapFactory.decodeResource(getResources(), resId));
        }

        private Bitmap convertToAlphaMask(Bitmap b) {
            Bitmap a = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ALPHA_8);
            new Canvas(a).drawBitmap(b, 0.0f, 0.0f, null);
            b.recycle();
            return a;
        }

        public float smallestDistance(Shape[] shapeArray) {
            float smallestDistance = shapeArray[0].smallestDistance();
            for (Shape smallestDistance2 : shapeArray) {
                float distance = smallestDistance2.smallestDistance();
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                }
            }
            return smallestDistance;
        }


        public void updateShapeListForRatio(int width, int height) {
            int shapeCount = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length;
            PointF scale = getRatio();
            calculateOffset();
            Collage collage = Collage.CreateCollage(shapeCount, (int) (scale.x * ((float) width)), (int) (((float) width) * scale.y), isScrapBook);
            this.smallestDistanceList.clear();
            for (int index = 0; index < this.shapeLayoutList.size(); index++) {
                if (shapeCount == 1) {
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[0].changeRatio((PointF[]) ((CollageLayout) collage.collageLayoutList.get(index)).shapeList.get(0), null, this.offsetX, this.offsetY, isScrapBook, 0, (int) (scale.x * ((float) width)), (int) (((float) width) * scale.y));
                } else {
                    for (int j = 0; j < shapeCount; j++) {
                        ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[j].changeRatio((PointF[]) ((CollageLayout) collage.collageLayoutList.get(index)).shapeList.get(j), null, this.offsetX, this.offsetY, isScrapBook, j, (int) (scale.x * ((float) width)), (int) (((float) width) * scale.y));
                    }
                }
                this.smallestDistanceList.add(Float.valueOf(smallestDistance(((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr)));
                setPathPadding(index, this.paddingDistance);
                if (!isScrapBook) {
                    for (Shape scaleMatrix : ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr) {
                        scaleMatrix.setScaleMatrix(1);
                    }
                }
            }
            setCornerRadius(this.cornerRadius);
            if (this.blurBitmap != null) {
                setBlurRect2((float) this.blurBitmap.getWidth(), (float) this.blurBitmap.getHeight());
            }
            postInvalidate();
        }


        public PointF getRatio() {
            this.yscale = 1.0f;
            this.xscale = 1.0f;
            this.yscale = mulY / mulX;
            if (!isScrapBook && this.yscale > RATIO_CONSTANT) {
                this.xscale = RATIO_CONSTANT / this.yscale;
                this.yscale = RATIO_CONSTANT;
            }
            return new PointF(this.xscale, this.yscale);
        }


        public void updateShapeListForFilterBitmap(Bitmap bitmap) {
            if (this.shapeIndex >= 0) {
                for (int i = 0; i < this.shapeLayoutList.size(); i++) {
                    ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex].setBitmap(bitmap, true);
                }
            }
        }


        public void updateParamList(Parameter p) {
            if (this.shapeIndex >= 0) {
                parameterList[this.shapeIndex] = new Parameter(p);
            }
        }


        public void swapBitmaps(int index1, int index2) {
            Bitmap bitmap1 = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[index1].getBitmap();
            Bitmap bitmap2 = ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr[index2].getBitmap();
            for (int i = 0; i < this.shapeLayoutList.size(); i++) {
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index1].setBitmap(bitmap2, false);
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index2].setBitmap(bitmap1, false);
            }
            Bitmap temp = bitmapList[index1];
            bitmapList[index1] = bitmapList[index2];
            bitmapList[index2] = temp;
            Parameter tempParam = parameterList[index1];
            parameterList[index1] = parameterList[index2];
            parameterList[index2] = tempParam;
            float sd = ((Float) this.smallestDistanceList.get(index1)).floatValue();
            this.smallestDistanceList.set(index1, this.smallestDistanceList.get(index2));
            this.smallestDistanceList.set(index2, Float.valueOf(sd));
            selectSwapTextView.setVisibility(View.INVISIBLE);
            unselectShapes();
        }


        public void setCurrentCollageIndex(int index) {
            this.currentCollageIndex = index;
            if (this.currentCollageIndex >= this.shapeLayoutList.size()) {
                this.currentCollageIndex = 0;
            }
            if (this.currentCollageIndex < 0) {
                this.currentCollageIndex = this.shapeLayoutList.size() - 1;
            }
            setCornerRadius(this.cornerRadius);
            setPathPadding(this.currentCollageIndex, this.paddingDistance);
        }


        public void setCornerRadius(float radius) {
            this.cornerRadius = radius;
            CornerPathEffect corEffect = new CornerPathEffect(radius);
            for (Shape radius2 : ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr) {
                radius2.setRadius(corEffect);
            }
            postInvalidate();
        }


        public void setPathPadding(int index, float distance) {
            this.paddingDistance = distance;
            for (int i = 0; i < ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr.length; i++) {
                ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i].scalePath((((Float) this.smallestDistanceList.get(index)).floatValue() / 250.0f) * distance, (float) this.screenWidth, (float) this.screenWidth);
                if (!isScrapBook) {
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i].checkScaleBounds();
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i].checkBoundries();
                }
            }
            postInvalidate();
        }


        public void setCollageSize(Matrix matrix, int progress) {
            matrix.reset();
            this.sizeScale = calculateSize((float) progress);
            matrix.postScale(this.sizeScale, this.sizeScale, (((float) (this.offsetX + this.offsetX)) + (((float) width) * this.xscale)) / 2.0f, (((float) (this.offsetY + this.offsetY)) + (((float) width) * this.yscale)) / 2.0f);
            invalidate();
        }


        public float calculateSize(float progress) {
            return 1.0f - (progress / 200.0f);
        }


        public int calculateSizeProgress(float size) {
            int progress = 200 - Math.round(200.0f * size);
            if (progress < 0) {
                progress = 0;
            }
            if (progress > 100) {
                return 100;
            }
            return progress;
        }


        public void setPatternPaint(int resId) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
                this.patternPaint.setColor(-1);
            }
            if (resId == -1) {
                this.patternPaint.setShader(null);
                this.patternPaint.setColor(-1);
                postInvalidate();
                return;
            }
            this.patternBitmap = BitmapFactory.decodeResource(getResources(), resId);
            this.patternPaint.setShader(new BitmapShader(this.patternBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            postInvalidate();
        }


        public void setPatternPaintColor(int color) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
            }
            this.patternPaint.setShader(null);
            this.patternPaint.setColor(color);
            postInvalidate();
        }

        public void setFrame(int index) {
            if (this.frameRect == null) {
                this.frameRect = new RectF(0.0f, 0.0f, (float) this.screenWidth, (float) this.screenWidth);
            }
            if (this.frameBitmap != null && !this.frameBitmap.isRecycled()) {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (index != 0) {
                this.frameBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[index]);
                postInvalidate();
            }
        }

        public void startAnimator() {
            if (seekbarSize != null) {
                this.animSizeSeekbarProgress = seekbarSize.getProgress();
            } else {
                this.animSizeSeekbarProgress = 0;
            }
            this.sizeMatrixSaved = new Matrix(this.sizeMatrix);
            this.animationCount = 0;
            this.animate = true;
            removeCallbacks(this.animator);
            postDelayed(this.animator, 150);
        }


        public int animSize(int value) {
            int res;
            if (value < this.animHalfTime) {
                res = value;
            } else {
                res = this.animationLimit - value;
            }
            return this.animSizeSeekbarProgress + Math.round((float) (res * 2));
        }

        public void onDraw(Canvas canvas) {
            int width = getWidth();
            int height = getHeight();
            canvas.save();
            this.drawingAreaRect.set((float) this.offsetX, (float) this.offsetY, ((float) this.offsetX) + (((float) width) * this.xscale), ((float) this.offsetY) + (((float) width) * this.yscale));
            canvas.drawPaint(this.paintGray);
            if (this.backgroundMode == 0) {
                canvas.drawRect(this.drawingAreaRect, this.patternPaint);
            }
            if (this.blurBitmap != null && !this.blurBitmap.isRecycled() && this.backgroundMode == 1) {
                this.blurRectDst.set(this.drawingAreaRect);
                canvas.drawBitmap(this.blurBitmap, this.blurRectSrc, this.blurRectDst, this.paint);
            }
            if (!isScrapBook) {
                canvas.setMatrix(this.sizeMatrix);
            }
            int j = 0;
            if (!isScrapBook || showText) {
                j = canvas.saveLayer(0.0f, 0.0f, ((float) width) / this.sizeScale, ((float) height) / this.sizeScale, null, Canvas.ALL_SAVE_FLAG);
            }
            int i = 0;
            while (i < ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr.length) {
                boolean drawPorterClear = false;
                if (i == ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).getClearIndex()) {
                    drawPorterClear = true;
                }
                if (isScrapBook) {
                    ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i].drawShapeForScrapBook(canvas, width, height, i == this.shapeIndex, this.orthogonal);
                } else {
                    ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i].drawShape(canvas, width, height, j, drawPorterClear);
                }
                i++;
            }
            if (!isScrapBook && this.shapeIndex >= 0 && ((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length > 1) {
                canvas.drawRect(((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bounds, this.borderPaint);
            }
            if (showText) {
                canvas.restoreToCount(j);
                for (int i2 = 0; i2 < textDataList.size(); i2++) {
                    this.textMatrix.set(((TextDataItem) textDataList.get(i2)).imageSaveMatrix);
                    canvas.setMatrix(this.textMatrix);
                    canvas.drawText(((TextDataItem) textDataList.get(i2)).message, ((TextDataItem) textDataList.get(i2)).xPos, ((TextDataItem) textDataList.get(i2)).yPos, ((TextDataItem) textDataList.get(i2)).textPaint);
                    canvas.setMatrix(this.identityMatrix);
                }
            }
            if (this.frameBitmap != null && !this.frameBitmap.isRecycled()) {
                canvas.drawBitmap(this.frameBitmap, null, this.frameRect, this.paint);
            }
            if (isScrapBook) {
                canvas.restore();
                this.above.set(0.0f, 0.0f, (float) canvas.getWidth(), this.drawingAreaRect.top);
                this.left.set(0.0f, this.drawingAreaRect.top, this.drawingAreaRect.left, this.drawingAreaRect.bottom);
                this.right.set(this.drawingAreaRect.right, this.drawingAreaRect.top, (float) canvas.getWidth(), this.drawingAreaRect.bottom);
                this.bottom.set(0.0f, this.drawingAreaRect.bottom, (float) canvas.getWidth(), (float) canvas.getHeight());
                canvas.drawRect(this.above, this.paintGray);
                canvas.drawRect(this.left, this.paintGray);
                canvas.drawRect(this.right, this.paintGray);
                canvas.drawRect(this.bottom, this.paintGray);
            }
        }

        public boolean onTouchEvent(MotionEvent ev) {
            this.mScaleDetector.onTouchEvent(ev);
            this.mTouchDetector.onTouchEvent(ev);
            if (isScrapBook) {
                mRotationDetector.onTouchEvent(ev);
            }
            int action = ev.getAction();
            switch (action & 255) {
                case 0:
                    this.previousIndex = this.shapeIndex;
                    float x = ev.getX();
                    float y = ev.getY();
                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    this.orthogonal = false;
                    this.mActivePointerId = ev.getPointerId(0);
                    if (isScrapBook && this.shapeIndex >= 0) {
                        this.zoomStart.set(x, y);
                        this.pts = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].getMappedCenter();
                        if (this.pts != null) {
                            this.startAngle = -Utils.pointToAngle(x, y, this.pts[0], this.pts[1]);
                        }
                        this.isInCircle = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].isInCircle(x, y);
                        this.isOnCross = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].isOnCross(x, y);

                    } else {
                        selectCurrentShape(x, y, false);

                    }
                    break;
                case 1:
                    this.orthogonal = false;
                    this.mActivePointerId = 1;
                    if (this.isOnCross) {
                        createDeleteDialog();
                    }
                    this.isInCircle = false;
                    this.isOnCross = false;
                    invalidate();
                    break;
                case 2:
                    if (!this.isOnCross) {
                        int pointerIndex = ev.findPointerIndex(this.mActivePointerId);
                        float x2 = ev.getX(pointerIndex);
                        float y2 = ev.getY(pointerIndex);
                        if (this.shapeIndex < 0) {
                            selectCurrentShape(x2, y2, false);
                        }
                        if (this.shapeIndex >= 0) {
                            if (!isScrapBook || !this.isInCircle) {
                                ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrixTranslate(x2 - this.mLastTouchX, y2 - this.mLastTouchY);
                                this.mLastTouchX = x2;
                                this.mLastTouchY = y2;
                                invalidate();
                                break;
                            } else {
                                this.pts = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].getMappedCenter();
                                float currentAngle = -Utils.pointToAngle(x2, y2, this.pts[0], this.pts[1]);
                                Log.d(CreateCollageActivity.TAG, "currentAngle " + Float.toString(currentAngle));
                                float rotation = getMatrixRotation(((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrix);
                                if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(this.startAngle - currentAngle) < 4.0f) {
                                    this.orthogonal = true;
                                } else {
                                    if (Math.abs((rotation - this.startAngle) + currentAngle) < 4.0f) {
                                        currentAngle = this.startAngle - rotation;
                                        this.orthogonal = true;
                                        Log.d(CreateCollageActivity.TAG, "aaaaa " + Float.toString(rotation));
                                    } else if (Math.abs(90.0f - ((rotation - this.startAngle) + currentAngle)) < 4.0f) {
                                        currentAngle = (90.0f + this.startAngle) - rotation;
                                        this.orthogonal = true;
                                        Log.d(CreateCollageActivity.TAG, "bbbbb " + Float.toString(rotation));
                                    } else if (Math.abs(180.0f - ((rotation - this.startAngle) + currentAngle)) < 4.0f) {
                                        currentAngle = (180.0f + this.startAngle) - rotation;
                                        this.orthogonal = true;
                                        Log.d(CreateCollageActivity.TAG, "cccc " + Float.toString(rotation));
                                    } else if (Math.abs(-180.0f - ((rotation - this.startAngle) + currentAngle)) < 4.0f) {
                                        currentAngle = (-180.0f + this.startAngle) - rotation;
                                        this.orthogonal = true;
                                    } else if (Math.abs(-90.0f - ((rotation - this.startAngle) + currentAngle)) < 4.0f) {
                                        currentAngle = (-90.0f + this.startAngle) - rotation;
                                        this.orthogonal = true;
                                        Log.d(CreateCollageActivity.TAG, "dddd " + Float.toString(rotation));
                                    } else {
                                        this.orthogonal = false;
                                    }
                                    ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrixRotate(this.startAngle - currentAngle);
                                    this.startAngle = currentAngle;
                                }
                                float scaley = ((float) Math.sqrt((double) (((x2 - this.pts[0]) * (x2 - this.pts[0])) + ((y2 - this.pts[1]) * (y2 - this.pts[1]))))) / ((float) Math.sqrt((double) (((this.zoomStart.x - this.pts[0]) * (this.zoomStart.x - this.pts[0])) + ((this.zoomStart.y - this.pts[1]) * (this.zoomStart.y - this.pts[1])))));
                                float scale = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].getScale();
                                if (scale >= this.MIN_ZOOM || (scale < this.MIN_ZOOM && scaley > 1.0f)) {
                                    ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrixScaleScrapBook(scaley, scaley);
                                    this.zoomStart.set(x2, y2);
                                }
                                invalidate();
                                return true;
                            }
                        }
                    }
                    break;
                case 3:
                    this.mActivePointerId = 1;
                    this.isInCircle = false;
                    this.isOnCross = false;
                    break;
                case 6:
                    this.finalAngle = 0.0f;
                    int pointerIndex2 = (65280 & action) >> 8;
                    if (ev.getPointerId(pointerIndex2) == this.mActivePointerId) {
                        int newPointerIndex = pointerIndex2 == 0 ? 1 : 0;
                        this.mLastTouchX = ev.getX(newPointerIndex);
                        this.mLastTouchY = ev.getY(newPointerIndex);
                        this.mActivePointerId = ev.getPointerId(newPointerIndex);

                    }
                    break;
            }
            return true;
        }

        private void selectCurrentShapeScrapBook(float x, float y, boolean isSingleTap) {
            int length = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr.length;
            boolean isSelected = false;
            int i = length - 1;
            while (true) {
                if (i < 0) {
                    break;
                } else if (((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i].isScrapBookSelected(x, y)) {
                    this.shapeIndex = i;
                    isSelected = true;
                    break;
                } else {
                    i--;
                }
            }
            if (this.previousIndex == this.shapeIndex && isSingleTap) {
                unselectShapes();
            } else if (!isSelected) {
                unselectShapes();
            } else if (selectImageForAdj) {
                openFilterFragment();
            } else if (this.shapeIndex >= 0 && this.shapeIndex < length) {
                Shape shapeTemp = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex];
                Bitmap btmTemp = bitmapList[this.shapeIndex];
                Parameter prmTemp = parameterList[this.shapeIndex];
                for (int i2 = 0; i2 < length; i2++) {
                    if (i2 >= this.shapeIndex) {
                        if (i2 < length - 1) {
                            ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i2] = ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i2 + 1];
                            bitmapList[i2] = bitmapList[i2 + 1];
                            parameterList[i2] = parameterList[i2 + 1];
                        } else {
                            ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i2] = shapeTemp;
                            bitmapList[i2] = btmTemp;
                            parameterList[i2] = prmTemp;
                        }
                    }
                }
                if (this.previousIndex == this.shapeIndex) {
                    this.previousIndex = length - 1;
                } else if (this.previousIndex > this.shapeIndex) {
                    this.previousIndex--;
                }
                this.shapeIndex = length - 1;
                if (((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length > 0) {
                    contextFooter.setVisibility(View.VISIBLE);
                    setSelectedTab(5);
                }
            }
            if (this.shapeIndex >= 0) {
                ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }


        public void selectCurrentShape(float x, float y, boolean isSingleTap) {
            if (isScrapBook) {
                selectCurrentShapeScrapBook(x, y, isSingleTap);
            } else {
                selectCurrentShapeCollage(x, y, isSingleTap);
            }
        }

        private void selectCurrentShapeCollage(float x, float y, boolean isSingleTap) {
            int swapIndex = this.shapeIndex;
            for (int i = 0; i < ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr.length; i++) {
                if (((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[i].region.contains((int) x, (int) y)) {
                    this.shapeIndex = i;
                }
            }
            if (selectImageForAdj) {
                openFilterFragment();
            } else if (swapMode) {
                Log.e(CreateCollageActivity.TAG, "PRE SWAP");
                if (swapIndex != this.shapeIndex && swapIndex > -1 && this.shapeIndex > -1) {
                    Log.e(CreateCollageActivity.TAG, "SWAP");
                    swapBitmaps(this.shapeIndex, swapIndex);
                    swapMode = false;
                }
            } else if (this.previousIndex == this.shapeIndex && isSingleTap) {
                unselectShapes();
            } else if (((ShapeLayout) this.shapeLayoutList.get(0)).shapeArr.length > 0) {
                contextFooter.setVisibility(View.VISIBLE);
                setSelectedTab(5);
                Log.e(CreateCollageActivity.TAG, "VISIBLE");
            }
            if (this.shapeIndex >= 0) {
                ((ShapeLayout) this.shapeLayoutList.get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }


        public void unselectShapes() {
            contextFooter.setVisibility(View.INVISIBLE);
            this.shapeIndex = -1;
            Log.e(CreateCollageActivity.TAG, "unselectShapes");
            postInvalidate();
        }

        public void openFilterFragment() {
            selectFilterTextView.setVisibility(View.INVISIBLE);
            selectImageForAdj = false;
            if (shapeIndex >= 0) {
                fullEffectFragment.setBitmapWithParameter(bitmapList[this.shapeIndex], parameterList[this.shapeIndex]);
                setVisibilityOfFilterHorizontalListview(true);
            }
        }


        public float getMatrixRotation(Matrix matrix) {
            matrix.getValues(this.values);
            return (float) Math.round(Math.atan2((double) this.values[1], (double) this.values[0]) * 57.29577951308232d);
        }

        public void setBlurBitmap(int radius, boolean cascade) {
            if (this.blurBuilderNormal == null) {
                this.blurBuilderNormal = new ImageBlurNormal();
            }
            if (cascade) {
                this.backgroundMode = 2;
                if (!isScrapBook) {
                    seekbarSize.setProgress(seekbarSize.getMax());
                }
            } else {
                this.backgroundMode = 1;
            }
            this.blurBitmap = NativeStackBlur.process(bitmapList[0].copy(bitmapList[0].getConfig(), true), radius);
            if (this.blurBitmap != null) {
                setBlurRect2((float) this.blurBitmap.getWidth(), (float) this.blurBitmap.getHeight());
            }
            postInvalidate();
        }


        public void setBlurRect2(float btmwidth, float btmheight) {
            float w;
            float h;
            if ((mulY * btmwidth) / mulX < btmheight) {
                w = (float) ((int) btmwidth);
                h = (mulY * btmwidth) / mulX;
            } else {
                w = (((float) ((int) mulX)) * btmheight) / mulY;
                h = (float) ((int) btmheight);
            }
            int l = (int) ((btmwidth - w) / 2.0f);
            int t = (int) ((btmheight - h) / 2.0f);
            this.blurRectSrc.set(l, t, (int) (((float) l) + w), (int) (((float) t) + h));
        }
    }

    public void setVisibilityForSingleImage() {
        findViewById(R.id.seekbar_corner_container).setVisibility(View.GONE);
        findViewById(R.id.seekbar_space_container).setVisibility(View.GONE);
        findViewById(R.id.buttonBlur).setVisibility(View.VISIBLE);
        findViewById(R.id.buttonDelete).setVisibility(View.GONE);
        findViewById(R.id.buttonSwap).setVisibility(View.GONE);
        if (!this.isScrapBook) {
            this.collageView.setCollageSize(this.collageView.sizeMatrix, 45);
            if (this.seekbarSize != null) {
                this.seekbarSize.setProgress(45);
            }
        }
        this.collageView.setBlurBitmap(this.collageView.blurRadius, false);
        if (!this.isScrapBook) {
            setSelectedTab(2);
        }
    }

    public void setSelectedTab(int index) {
        if (this.viewFlipper != null) {
            setTabBg(0);
            int displayedChild = this.viewFlipper.getDisplayedChild();
            if (displayedChild != 1) {
                hideColorContainer();
            }
            if (index == 0) {
                if (displayedChild != 0) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                    this.viewFlipper.setDisplayedChild(0);
                } else {
                    return;
                }
            }
            if (index == 1) {
                setTabBg(1);
                if (displayedChild != 1) {
                    if (displayedChild == 0) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(1);
                } else {
                    return;
                }
            }
            if (index == 4) {
                setTabBg(4);
                if (displayedChild != 4) {
                    if (displayedChild == 0) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(4);
                } else {
                    return;
                }
            }
            if (index == 2) {
                setTabBg(2);
                if (displayedChild != 2) {
                    if (displayedChild == 0 || displayedChild == 1) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(2);
                } else {
                    return;
                }
            }
            if (index == 3) {
                setTabBg(3);
                if (displayedChild != 3) {
                    if (displayedChild == 5) {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    }
                    this.viewFlipper.setDisplayedChild(3);
                } else {
                    return;
                }
            }
            if (index == 5) {
                setTabBg(-1);
                if (displayedChild != 5) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(5);
                }
            }
        }
    }

    private void setTabBg(int index) {
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[6];
            this.tabButtonList[0] = findViewById(R.id.buttonCollageLayout);
            this.tabButtonList[1] = findViewById(R.id.buttonBackground);
            this.tabButtonList[2] = findViewById(R.id.buttonSpace);
            this.tabButtonList[4] = findViewById(R.id.buttonBlur);
            this.tabButtonList[3] = findViewById(R.id.buttonRatio);
            this.tabButtonList[5] = findViewById(R.id.buttonAdjustment);
        }
        for (View backgroundResource : tabButtonList) {
            backgroundResource.setBackgroundResource(R.drawable.collage_footer_button);
        }
        if (index >= 0) {
            this.tabButtonList[index].setBackgroundResource(R.color.footer_button_color_pressed);
        }
    }


    public void setVisibilityOfFilterHorizontalListview(boolean show) {
        if (show && this.fullEffectFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().show(this.fullEffectFragment).commit();
        }
        if (!show && this.fullEffectFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commit();
        }
        findViewById(R.id.collage_effect_fragment_container).bringToFront();
    }

    private void hideColorContainer() {
        if (this.colorContainer == null) {
            this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        }
        this.colorContainer.setVisibility(View.INVISIBLE);
    }


    public void createDeleteDialog() {
        if ((collageView.shapeLayoutList.get(0)).shapeArr.length == 1) {
            Toast msg = Toast.makeText(this, "You can't delete last image!", Toast.LENGTH_SHORT);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();


        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) "Do you want to delete it?").setCancelable(true).setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                collageView.deleteBitmap(collageView.shapeIndex, width, height);
            }
        }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

    public void addCanvasTextView() {
        this.canvasText = new CustomRelativeLayout(this, this.textDataList, this.collageView.identityMatrix, new SingleTapInterface() {
            public void onSingleTap(TextDataItem textData) {
                fontFragment = new WriteTextFragment();
                Bundle arguments = new Bundle();
                arguments.putSerializable("text_data", textData);
                fontFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction().replace(R.id.collage_text_view_fragment_container, fontFragment, "FONT_FRAGMENT").commit();
                Log.e(CreateCollageActivity.TAG, "replace fragment");
                fontFragment.setFontChoosedListener(fontChoosedListener);
            }
        });
        this.canvasText.setApplyTextListener(new ApplyTextInterface() {
            public void onOk(ArrayList<TextDataItem> tdList) {
                Iterator it = tdList.iterator();
                while (it.hasNext()) {
                    ((TextDataItem) it.next()).setImageSaveMatrix(collageView.identityMatrix);
                }
                textDataList = tdList;
                showText = true;
                if (mainLayout == null) {
                    mainLayout = (RelativeLayout) findViewById(R.id.collage_main_layout);
                }
                mainLayout.removeView(canvasText);
                collageView.postInvalidate();
            }

            public void onCancel() {
                showText = true;
                mainLayout.removeView(canvasText);
                collageView.postInvalidate();
            }
        });
        this.showText = false;
        this.collageView.invalidate();
        this.mainLayout.addView(this.canvasText);
        findViewById(R.id.collage_text_view_fragment_container).bringToFront();
        this.fontFragment = new WriteTextFragment();
        this.fontFragment.setArguments(new Bundle());
        getSupportFragmentManager().beginTransaction().add(R.id.collage_text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
        Log.e(TAG, "add fragment");
        this.fontFragment.setFontChoosedListener(this.fontChoosedListener);
    }

    private void createAdapterList(int colorDefault, int colorSelected) {
        this.patternAdapterList.clear();
        this.patternAdapterList.add(new ColorPickerAdapter(new CollageImageAdapter.CurrentCollageIndexChangedListener() {
            public void onIndexChanged(int color) {
                collageView.setPatternPaintColor(color);
            }
        }, colorDefault, colorSelected));


        for (int[] collageImageAdapter : Utils.patternResIdList2) {
            this.patternAdapterList.add(new CollageImageAdapter(CreateCollageActivity.this,collageImageAdapter, new CollageImageAdapter.CurrentCollageIndexChangedListener() {
                public void onIndexChanged(int positionOrColor) {
                    collageView.setPatternPaint(positionOrColor);
                }
            }, colorDefault, colorSelected, true, true));
        }
    }

    public void addEffectFragment() {
        if (this.fullEffectFragment == null) {
            this.fullEffectFragment = (FullEffectFragment) getSupportFragmentManager().findFragmentByTag("FULL_FRAGMENT");
            Log.e(TAG, "addEffectFragment");
            if (this.fullEffectFragment == null) {
                this.fullEffectFragment = new FullEffectFragment();
                Log.e(TAG, "EffectFragment == null");
                this.fullEffectFragment.setArguments(getIntent().getExtras());
                Log.e(TAG, "fullEffectFragment null");
                getSupportFragmentManager().beginTransaction().add(R.id.collage_effect_fragment_container, this.fullEffectFragment, "FULL_FRAGMENT").commitAllowingStateLoss();
            } else {
                Log.e(TAG, "not null null");
                if (this.collageView.shapeIndex >= 0) {
                    this.fullEffectFragment.setBitmapWithParameter(this.bitmapList[this.collageView.shapeIndex], this.parameterList[this.collageView.shapeIndex]);
                }
            }
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commitAllowingStateLoss();
            this.fullEffectFragment.setFullBitmapReadyListener(new FullEffectFragment.FullBitmapReady() {
                public void onBitmapReady(Bitmap bitmap, Parameter parameter) {
                    collageView.updateShapeListForFilterBitmap(bitmap);
                    collageView.updateParamList(parameter);
                    collageView.postInvalidate();
                    getSupportFragmentManager().beginTransaction().hide(fullEffectFragment).commit();
                    collageView.postInvalidate();
                }

                public void onCancel() {
                    setVisibilityOfFilterHorizontalListview(false);
                    collageView.postInvalidate();
                }
            });
            findViewById(R.id.collage_effect_fragment_container).bringToFront();
        }
    }

    public void setVisibilityForScrapbook() {
        findViewById(R.id.buttonCollageLayout).setVisibility(View.GONE);
        findViewById(R.id.buttonSpace).setVisibility(View.GONE);
        findViewById(R.id.buttonSwap).setVisibility(View.GONE);
        findViewById(R.id.buttonFit).setVisibility(View.GONE);
        findViewById(R.id.buttonCenter).setVisibility(View.GONE);
        findViewById(R.id.buttonDelete).setVisibility(View.VISIBLE);
    }


    public void onPause() {

        super.onPause();
    }

    public void onResume() {
        super.onResume();

    }



    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("show_text", this.showText);
        outState.putSerializable("text_data", this.textDataList);
        if (this.fontFragment != null && this.fontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.showText = savedInstanceState.getBoolean("show_text");
        this.textDataList = (ArrayList) savedInstanceState.getSerializable("text_data");
        if (this.textDataList == null) {
            this.textDataList = new ArrayList<>();
        }
        if (this.contextFooter == null) {
            this.contextFooter = (ViewGroup) findViewById(R.id.collage_context_menu);
        }
        if (this.contextFooter != null) {
            this.contextFooter.bringToFront();
        }
    }

    public void onBackPressed() {
        if (this.fontFragment != null && this.fontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        } else if (!this.showText && this.canvasText != null) {
            this.showText = true;
            this.mainLayout.removeView(this.canvasText);
            this.collageView.postInvalidate();
            this.canvasText = null;
            Log.e(TAG, "replace fragment");
        } else if (this.fullEffectFragment != null && this.fullEffectFragment.isVisible()) {
        } else {
            if (this.colorContainer.getVisibility() == View.VISIBLE) {
                hideColorContainer();
            } else if (this.swapMode) {
                this.selectSwapTextView.setVisibility(View.INVISIBLE);
                this.swapMode = false;
            } else if (this.collageView != null && this.collageView.shapeIndex >= 0) {
                this.collageView.unselectShapes();
            } else if (this.selectImageForAdj) {
                this.selectFilterTextView.setVisibility(View.INVISIBLE);
                this.selectImageForAdj = false;
            } else if (this.viewFlipper == null || this.viewFlipper.getDisplayedChild() == 5) {
                backButtonAlertBuilder();
            } else {
                setSelectedTab(5);
            }
        }
    }

    private void backButtonAlertBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage((CharSequence) "Would you like to save image ?").setCancelable(true).setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                new SaveImageTask().execute(new Object[0]);
            }
        }).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNeutralButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(CreateCollageActivity.this,MainActivity.class));
            }
        });
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

    public void myClickHandler(View view) {
        int id = view.getId();
        if (id == R.id.buttonCollageLayout) {
            setSelectedTab(0);
        } else if (id == R.id.buttonRatio) {
            setSelectedTab(3);
        } else if (id == R.id.buttonBlur) {
            this.collageView.setBlurBitmap(this.collageView.blurRadius, false);
            setSelectedTab(4);
            this.collageView.startAnimator();
        } else if (id == R.id.buttonBackground) {
            setSelectedTab(1);
        } else if (id == R.id.buttonSpace) {
            setSelectedTab(2);
        } else if (id == R.id.buttonAdjustment) {
            if (((ShapeLayout) this.collageView.shapeLayoutList.get(0)).shapeArr.length == 1) {
                this.collageView.shapeIndex = 0;
                this.collageView.openFilterFragment();
            } else if (this.collageView.shapeIndex >= 0) {
                this.collageView.openFilterFragment();
                Log.e(TAG, "collageView.shapeIndex>=0 openFilterFragment");
            } else {
                setSelectedTab(5);
                this.selectFilterTextView.setVisibility(View.VISIBLE);
                this.selectImageForAdj = true;
            }
        } else if (id == R.id.buttonSwap) {
            if (( this.collageView.shapeLayoutList.get(this.collageView.currentCollageIndex)).shapeArr.length == 2) {
                this.collageView.swapBitmaps(0, 1);
            } else {
                this.selectSwapTextView.setVisibility(View.VISIBLE);
                this.swapMode = true;
            }
        } else if (id == R.id.buttonDelete) {
            createDeleteDialog();
        } else if (id == R.id.button_collage_context_filter) {
            this.collageView.openFilterFragment();
        } else if (id == R.id.button_save_collage_image) {
            setSelectedTab(5);
            new SaveImageTask().execute(new Object[0]);
        } else if (id == R.id.button_cancel_collage_image) {
            backButtonAlertBuilder();
        } else if (id == R.id.button11) {
            this.mulX = 1.0f;
            this.mulY = 1.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(0);
        } else if (id == R.id.button21) {
            this.mulX = 2.0f;
            this.mulY = 1.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(1);
        } else if (id == R.id.button12) {
            this.mulX = 1.0f;
            this.mulY = 2.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(2);
        } else if (id == R.id.button32) {
            this.mulX = 3.0f;
            this.mulY = 2.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(3);
        } else if (id == R.id.button23) {
            this.mulX = 2.0f;
            this.mulY = 3.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(4);
        } else if (id == R.id.button43) {
            this.mulX = 4.0f;
            this.mulY = 3.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(5);
        } else if (id == R.id.button34) {
            this.mulX = 3.0f;
            this.mulY = 4.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(6);
        } else if (id == R.id.button45) {
            this.mulX = 4.0f;
            this.mulY = 5.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(7);
        } else if (id == R.id.button57) {
            this.mulX = 5.0f;
            this.mulY = 7.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(8);
        } else if (id == R.id.button169) {
            this.mulX = 16.0f;
            this.mulY = 9.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(9);
        } else if (id == R.id.button916) {
            this.mulX = 9.0f;
            this.mulY = 16.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(10);
        } else if (id == R.id.hide_select_image_warning) {
            this.selectSwapTextView.setVisibility(View.INVISIBLE);
            this.swapMode = false;
        } else if (id == R.id.hide_select_image_warning_filter) {
            this.selectFilterTextView.setVisibility(View.INVISIBLE);
            this.selectImageForAdj = false;
        } else if (id == R.id.hide_color_container) {
            hideColorContainer();
        } else if (id == R.id.buttonText) {
            addCanvasTextView();
            clearViewFlipper();
        }
        if (id == R.id.buttonFit) {
            this.collageView.setShapeScaleMatrix(0);
        } else if (id == R.id.buttonCenter) {
            this.collageView.setShapeScaleMatrix(1);
        } else if (id == R.id.button_collage_context_rotate_left) {
            this.collageView.setShapeScaleMatrix(3);
        } else if (id == R.id.button_collage_context_rotate_right) {
            this.collageView.setShapeScaleMatrix(2);
        } else if (id == R.id.button_collage_context_flip_horizontal) {
            this.collageView.setShapeScaleMatrix(4);
        } else if (id == R.id.button_collage_context_flip_vertical) {
            this.collageView.setShapeScaleMatrix(5);
        } else if (id == R.id.button_collage_context_rotate_negative) {
            this.collageView.setShapeScaleMatrix(6);
        } else if (id == R.id.button_collage_context_rotate_positive) {
            this.collageView.setShapeScaleMatrix(7);
        } else if (id == R.id.button_collage_context_zoom_in) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(8));
        } else if (id == R.id.button_collage_context_zoom_out) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(9));
        } else if (id == R.id.button_collage_context_move_left) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(10));
        } else if (id == R.id.button_collage_context_move_right) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(11));
        } else if (id == R.id.button_collage_context_move_up) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(12));
        } else if (id == R.id.button_collage_context_move_down) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(13));
        } else if (this.fullEffectFragment != null && this.fullEffectFragment.isVisible()) {
            this.fullEffectFragment.myClickHandler(view);
        }
    }

    private void setRatioButtonBg(int index) {
        if (this.ratioButtonArray == null) {
            this.ratioButtonArray = new Button[this.RATIO_BUTTON_SIZE];
            this.ratioButtonArray[0] = (Button) findViewById(R.id.button11);
            this.ratioButtonArray[1] = (Button) findViewById(R.id.button21);
            this.ratioButtonArray[2] = (Button) findViewById(R.id.button12);
            this.ratioButtonArray[3] = (Button) findViewById(R.id.button32);
            this.ratioButtonArray[4] = (Button) findViewById(R.id.button23);
            this.ratioButtonArray[5] = (Button) findViewById(R.id.button43);
            this.ratioButtonArray[6] = (Button) findViewById(R.id.button34);
            this.ratioButtonArray[7] = (Button) findViewById(R.id.button45);
            this.ratioButtonArray[8] = (Button) findViewById(R.id.button57);
            this.ratioButtonArray[9] = (Button) findViewById(R.id.button169);
            this.ratioButtonArray[10] = (Button) findViewById(R.id.button916);
        }
        for (int i = 0; i < this.RATIO_BUTTON_SIZE; i++) {
            this.ratioButtonArray[i].setBackgroundResource(R.drawable.selector_collage_ratio_button);
        }
        this.ratioButtonArray[index].setBackgroundResource(R.drawable.collage_ratio_bg_pressed);
    }

    public void toastMatrixMessage(int message) {
        String str = null;
        if (message == 1) {
            str = "You reached maximum zoom!";
        } else if (message == 2) {
            str = "You reached minimum zoom!";
        } else if (message == 6) {
            str = "You reached max bottom!";
        } else if (message == 5) {
            str = "You reached max top!";
        } else if (message == 4) {
            str = "You reached max right!";
        } else if (message == 3) {
            str = "You reached max left!";
        }
        if (str != null) {
            Toast msg = Toast.makeText(this, str, Toast.LENGTH_SHORT);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        }
    }

    public void clearViewFlipper() {
        this.viewFlipper.setDisplayedChild(5);
        setTabBg(-1);
    }

    private final class MyMediaScannerConnectionClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private MediaScannerConnection mConn;
        private String mFilename;
        private String mMimetype;

        MyMediaScannerConnectionClient(Context ctx, File file, String mimetype) {
            this.mFilename = file.getAbsolutePath();
            this.mConn = new MediaScannerConnection(ctx, this);
            this.mConn.connect();
        }

        public void onMediaScannerConnected() {
            this.mConn.scanFile(this.mFilename, this.mMimetype);
        }

        public void onScanCompleted(String path, Uri uri) {
            this.mConn.disconnect();
        }
    }

    private class SaveImageTask extends AsyncTask<Object, Object, Object> {
        ProgressDialog progressDialog;
        String resultPath;

        private SaveImageTask() {
            this.resultPath = null;
        }


        public void onPreExecute() {
            this.progressDialog = new ProgressDialog(CreateCollageActivity.this);
            this.progressDialog.setMessage("Saving image...");
            this.progressDialog.show();
        }


        @SuppressLint("WrongThread")
        public Object doInBackground(Object... arg0) {
            resultPath = collageView.saveBitmap(width, height);
            return resultPath;
        }


        public void onPostExecute(Object result) {
            super.onPostExecute(result);
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.cancel();
            }
            Log.e("path1",result.toString());
            Log.e("path2",resultPath);
            if (this.resultPath != null) {
                Intent intent = new Intent(CreateCollageActivity.this, ShareActivity.class);
                intent.putExtra("path", this.resultPath);
                startActivity(intent);
            }
            new MyMediaScannerConnectionClient(getApplicationContext(), new File(this.resultPath), null);
        }
    }


}