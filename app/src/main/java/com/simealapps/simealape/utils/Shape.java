package com.simealapps.simealape.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;

import com.simealapps.simealape.collagelist.Collage;

public class Shape {
    public static final int MATRIX_MODE_MOVE_DOWN = 13;
    public static final int MATRIX_MODE_MOVE_LEFT = 10;
    public static final int MATRIX_MODE_MOVE_RIGHT = 11;
    public static final int MATRIX_MODE_MOVE_UP = 12;
    public static final int MATRIX_MODE_ROTATE_POSITIVE = 7;
    public static final int MATRIX_MODE_ZOOM_IN = 8;
    public static final int MATRIX_MODE_ZOOM_OUT = 9;
    public static final int MESSAGE_DEFAULT = 0;
    public static final int MESSAGE_MAX_BOTTOM = 6;
    public static final int MESSAGE_MAX_LEFT = 3;
    public static final int MESSAGE_MAX_RIGHT = 4;
    public static final int MESSAGE_MAX_TOP = 5;
    public static final int MESSAGE_MAX_ZOOM = 1;
    public static final int MESSAGE_MIN_ZOOM = 2;
    private static final String TAG = "Shape";
    static final int[] scrapBookRotation = {13, -13, -7, -12, 11, 8, -9, 10, 9};
    public final int SHAPE_MODE_MASK = 3;
    public final int SHAPE_MODE_POINT = 1;
    public final int SHAPE_MODE_RECT = 2;
    private Bitmap bitmap;
    int bitmapHeight;
    public Matrix bitmapMatrix;
    RectF bitmapRect = new RectF();
    int bitmapWidth;
    Paint borderPaint = new Paint(1);
    int borderStrokeWidth = 6;
    public RectF bounds;
    Bitmap btmDelete;
    Bitmap btmScale;
    PointF centerOriginal = new PointF();
    Paint dashPaint = new Paint();
    Path dashPathHorizontal;
    Path dashPathVertical;
    int delW = 0;
    float deleteWidthHalf = 0.0f;


    float f2082dx = 0.0f;


    float f2083dy = 0.0f;
    int[] exceptionIndex;
    float[] f506f = new float[2];
    float[] f507p = new float[2];
    public RectF f508r = new RectF();
    Paint iconMaskPaint;
    Paint iconPaint;
    Xfermode iconXferMode;
    Matrix inverse = new Matrix();
    boolean isScrapBook;
    private Bitmap maskBitmap = null;
    private Matrix maskMatrix = new Matrix();
    Paint maskPaint;
    float maxScale = 1.0f;
    float minScale = 1.0f;
    NinePatchDrawable npd;
    int npdPadding = 16;
    int offsetX = 0;
    int offsetY = 0;
    RectF originalBounds;
    Path originalPath;
    private Paint paintPath;
    Paint paintScrap = new Paint(2);
    private Paint paintTransparent;
    Paint paintXferMode;
    Path path;
    Matrix pathMatrix;
    PointF[] points;
    float[] pts = new float[2];
    public Region region;
    Matrix removeBitmapMatrix;
    Matrix scaleBitmapMatrix;
    float scaleDown = 0.95f;
    float scaleUp = 1.05f;
    float scrapBookPadding = 25.0f;
    int screenWidth;
    int shapeMode;
    RectF sourceRect;
    final float tempRadius = 60.0f;
    RectF tempRect = new RectF();
    final float tempScrapBookPadding = 25.0f;
    float tempTouchStrokeWidth = 8.0f;
    Paint touchPaint = new Paint(1);
    RectF touchRect;
    float touchStrokeWidth = this.tempTouchStrokeWidth;
    Matrix transparentMaskMatrix = new Matrix();
    float[] values = new float[9];

    public Shape(PointF[] points2, Bitmap b, int[] exceptionIndex2, int offsetX2, int offsetY2, boolean isScrapBook2, int index, boolean isDelete, Bitmap del, Bitmap scl, int screenWidth2) {
        this.points = points2;
        this.offsetX = offsetX2;
        this.offsetY = offsetY2;
        this.btmDelete = del;
        this.btmScale = scl;
        this.screenWidth = screenWidth2;
        this.isScrapBook = isScrapBook2;
        createPathFromPoints();
        this.path.offset((float) offsetX2, (float) offsetY2);
        this.exceptionIndex = exceptionIndex2;
        this.bitmap = b;
        this.bitmapWidth = this.bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 1;
        init(isScrapBook2, index, false, 0, 0);
    }

    public Shape(PointF[] points2, Bitmap b, int[] exceptionIndex2, int offsetX2, int offsetY2, Bitmap mask, boolean isScrapBook2, int index, boolean isDelete, Bitmap del, Bitmap scl, int screenWidth2) {
        this.maskBitmap = mask;
        this.points = points2;
        this.offsetX = offsetX2;
        this.offsetY = offsetY2;
        this.btmDelete = del;
        this.btmScale = scl;
        this.screenWidth = screenWidth2;
        this.isScrapBook = isScrapBook2;
        createPathFromPoints();
        this.path.offset((float) offsetX2, (float) offsetY2);
        this.exceptionIndex = exceptionIndex2;
        this.bitmap = b;
        this.bitmapWidth = this.bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 3;
        init(isScrapBook2, index, false, 0, 0);
    }

    public void changeRatio(PointF[] points2, int[] exceptionIndex2, int offsetX2, int offsetY2, boolean isScrapBook2, int index, int w, int h) {
        this.points = points2;
        this.offsetX = offsetX2;
        this.offsetY = offsetY2;
        createPathFromPoints();
        this.path.offset((float) offsetX2, (float) offsetY2);
        this.exceptionIndex = exceptionIndex2;
        init(isScrapBook2, index, true, w, h);
    }

    public void freeBitmaps() {
        if (this.bitmap != null && !this.bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
            this.maskBitmap = null;
        }
    }

    public void setRadius(CornerPathEffect corEffect) {
        this.paintPath.setPathEffect(corEffect);
        this.paintTransparent.setPathEffect(corEffect);
    }

    public float smallestDistance() {
        float smallestDistance = 1500.0f;
        for (int i = 0; i < this.points.length; i++) {
            for (int j = 0; j < this.points.length; j++) {
                if (i != j) {
                    float distance = Math.abs(this.points[i].x - this.points[j].x) + Math.abs(this.points[i].y - this.points[j].y);
                    if (distance < smallestDistance) {
                        smallestDistance = distance;
                    }
                }
            }
        }
        return smallestDistance;
    }

    public void init(boolean isScrapBook2, int index, boolean isChangeRatio, int w, int h) {
        this.bounds = new RectF();
        this.originalPath = new Path(this.path);
        this.path.computeBounds(this.bounds, true);
        this.originalBounds = new RectF(this.bounds);
        this.paintXferMode = new Paint(1);
        this.paintXferMode.setFilterBitmap(true);
        this.paintXferMode.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        this.paintPath = new Paint(1);
        this.paintPath.setFilterBitmap(true);
        this.maskPaint = new Paint(1);
        this.maskPaint.setFilterBitmap(true);
        this.paintTransparent = new Paint(1);
        this.paintTransparent.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.paintTransparent.setFilterBitmap(true);
        if (isScrapBook2) {
            setScrapBookBitmapPosition(index, isChangeRatio, w, h);
        } else {
            setBitmapPosition();
        }
        this.paintPath.setPathEffect(new CornerPathEffect(3.0f));
        this.pathMatrix = new Matrix();
        this.region = new Region();
        this.region.setPath(this.path, new Region((int) this.bounds.left, (int) this.bounds.top, (int) this.bounds.right, (int) this.bounds.bottom));
        if (isScrapBook2) {
            this.dashPaint.setColor(7829368);
            this.dashPaint.setStyle(Style.STROKE);
            float strokeW = ((float) this.screenWidth) / 120.0f;
            if (strokeW <= 0.0f) {
                strokeW = 5.0f;
            }
            this.dashPaint.setStrokeWidth(strokeW);
            this.dashPaint.setPathEffect(new DashPathEffect(new float[]{strokeW, strokeW}, 0.0f));
            this.dashPathVertical = new Path();
            this.dashPathVertical.moveTo((float) (this.bitmapWidth / 2), (float) ((-this.bitmapHeight) / 5));
            this.dashPathVertical.lineTo((float) (this.bitmapWidth / 2), (float) ((this.bitmapHeight * 6) / 5));
            this.dashPathHorizontal = new Path();
            this.dashPathHorizontal.moveTo((float) ((-this.bitmapWidth) / 5), (float) (this.bitmapHeight / 2));
            this.dashPathHorizontal.lineTo((float) ((this.bitmapWidth * 6) / 5), (float) (this.bitmapHeight / 2));
        }
    }

    public void setBitmap(Bitmap bitmap2, boolean isFilter) {
        this.bitmap = bitmap2;
        this.bitmapWidth = bitmap2.getWidth();
        this.bitmapHeight = bitmap2.getHeight();
        if (!isFilter) {
            setBitmapPosition();
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Bitmap getMaskBitmap() {
        return this.maskBitmap;
    }

    private void setBitmapPosition() {
        float scaleBitmap = getBitmapScale();
        float bitmapY = this.bounds.top - (((((float) this.bitmapHeight) * scaleBitmap) - this.bounds.height()) / 2.0f);
        float bitmapX = this.bounds.left - (((((float) this.bitmapWidth) * scaleBitmap) - this.bounds.width()) / 2.0f);
        this.bitmapMatrix = new Matrix();
        this.bitmapMatrix.reset();
        this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
        this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
        setMaxMinScales(scaleBitmap);
    }

    private float getBitmapScale() {
        float scaleBitmapX = this.bounds.width() / ((float) this.bitmapWidth);
        float scaleBitmapY = this.bounds.height() / ((float) this.bitmapHeight);
        return scaleBitmapX < scaleBitmapY ? scaleBitmapY : scaleBitmapX;
    }


    public void setMaxMinScales(float scaleBitmap) {
        if (this.isScrapBook) {
            this.minScale = scaleBitmap / 2.0f;
        } else {
            this.minScale = scaleBitmap;
        }
        if (this.isScrapBook) {
            this.maxScale = scaleBitmap * 2.0f;
        } else {
            this.maxScale = 4.0f * scaleBitmap;
        }
    }


    public void setMinScales(float scaleBitmap) {
        if (this.isScrapBook) {
            this.minScale = scaleBitmap / 2.0f;
        } else {
            this.minScale = scaleBitmap;
        }
    }

    private void setMaskBitmapPositions() {
        float scaleMaskBitmap;
        float scaleMaskBitmapTr;
        if (this.maskBitmap != null) {
            int maskBitmapWidth = this.maskBitmap.getWidth();
            int maskBitmapHeight = this.maskBitmap.getHeight();
            float scaleMaskBitmapX = this.bounds.width() / ((float) maskBitmapWidth);
            float scaleMaskBitmapY = this.bounds.height() / ((float) maskBitmapHeight);
            if (scaleMaskBitmapX > scaleMaskBitmapY) {
                scaleMaskBitmap = scaleMaskBitmapY;
            } else {
                scaleMaskBitmap = scaleMaskBitmapX;
            }
            float maskBitmapY = this.bounds.top - (((((float) maskBitmapHeight) * scaleMaskBitmap) - this.bounds.height()) / 2.0f);
            float maskBitmapX = this.bounds.left - (((((float) maskBitmapWidth) * scaleMaskBitmap) - this.bounds.width()) / 2.0f);
            this.maskMatrix = new Matrix();
            this.maskMatrix.reset();
            this.maskMatrix.postScale(scaleMaskBitmap, scaleMaskBitmap);
            this.maskMatrix.postTranslate(maskBitmapX, maskBitmapY);
            float scaleMaskBitmapXTr = this.originalBounds.width() / ((float) maskBitmapWidth);
            float scaleMaskBitmapYTr = this.originalBounds.height() / ((float) maskBitmapHeight);
            if (scaleMaskBitmapXTr > scaleMaskBitmapYTr) {
                scaleMaskBitmapTr = scaleMaskBitmapYTr;
            } else {
                scaleMaskBitmapTr = scaleMaskBitmapXTr;
            }
            float maskBitmapYTr = this.originalBounds.top - (((((float) maskBitmapHeight) * scaleMaskBitmapTr) - this.originalBounds.height()) / 2.0f);
            float maskBitmapXTr = this.originalBounds.left - (((((float) maskBitmapWidth) * scaleMaskBitmapTr) - this.originalBounds.width()) / 2.0f);
            this.transparentMaskMatrix = new Matrix();
            this.transparentMaskMatrix.reset();
            this.transparentMaskMatrix.postScale(scaleMaskBitmapTr, scaleMaskBitmapTr);
            this.transparentMaskMatrix.postTranslate(maskBitmapXTr, maskBitmapYTr);
        }
    }

    public void scalePath(float distance, float width, float height) {
        if (this.shapeMode == 1) {
            pathTransform(this.points, this.path, distance, this.originalBounds.centerX(), this.originalBounds.centerY());
        } else if (this.shapeMode == 2) {
            pathTransformFromRect(distance);
        } else {
            float scaleX = (width - (2.0f * distance)) / width;
            float scaleY = (height - (2.0f * distance)) / height;
            this.pathMatrix.reset();
            this.pathMatrix.setScale(scaleX, scaleY, this.originalBounds.centerX(), this.originalBounds.centerY());
            this.originalPath.transform(this.pathMatrix, this.path);
        }
        this.path.computeBounds(this.bounds, true);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
    }


    public void createPathFromPoints() {
        this.path = new Path();
        this.path.setFillType(FillType.EVEN_ODD);
        this.path.moveTo(this.points[0].x, this.points[0].y);
        for (int i = 1; i < this.points.length; i++) {
            this.path.lineTo(this.points[i].x, this.points[i].y);
        }
        this.path.lineTo(this.points[0].x, this.points[0].y);
        this.path.close();
    }


    public void createPathFromRect() {
        this.path = new Path();
        this.path.addRect(this.sourceRect, Direction.CCW);
    }


    public void pathTransform(PointF[] points2, Path path2, float distance, float centerX, float centerY) {
        float centerX2 = centerX - ((float) this.offsetX);
        float centerY2 = centerY - ((float) this.offsetY);
        path2.rewind();
        path2.setFillType(FillType.EVEN_ODD);
        int size = points2.length;
        float[] distanceArray = new float[size];
        for (int i = 0; i < size; i++) {
            distanceArray[i] = distance;
        }
        if (this.exceptionIndex != null) {
            for (int i2 : this.exceptionIndex) {
                distanceArray[i2] = 2.0f * distance;
            }
        }
        path2.moveTo(checkRange(points2[0].x, distanceArray[0], centerX2), checkRange(points2[0].y, distance, centerY2));
        for (int i3 = 1; i3 < size; i3++) {
            path2.lineTo(checkRange(points2[i3].x, distanceArray[i3], centerX2), checkRange(points2[i3].y, distance, centerY2));
        }
        path2.lineTo(checkRange(points2[0].x, distanceArray[0], centerX2), checkRange(points2[0].y, distance, centerY2));
        path2.close();
        path2.offset((float) this.offsetX, (float) this.offsetY);
    }


    public void pathTransformFromRect(float distance) {
        float top = this.sourceRect.top;
        this.tempRect.set(this.sourceRect.left + distance, top + distance, this.sourceRect.right - distance, this.sourceRect.bottom - distance);
        this.path.rewind();
        this.path.addRect(this.tempRect, Direction.CCW);
    }


    public float checkRange(float pointA, float distance, float centerA) {
        if (pointA > centerA) {
            return pointA - distance;
        }
        if (pointA < centerA) {
            return pointA + distance;
        }
        return pointA;
    }

    public void drawShape(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        if (drawPorterClear) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
                canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
            }
            canvas.restoreToCount(j);
        }
        this.f508r.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.f508r);
        int k = canvas.saveLayer(this.f508r, null, 31);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXferMode);
        canvas.restoreToCount(k);
    }

    public void drawShapeForSave(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        if (drawPorterClear) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
                canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
            }
            canvas.restoreToCount(j);
        }
        RectF r = new RectF(0.0f, 0.0f, (float) (this.bitmapWidth + 0), (float) (this.bitmapHeight + 0));
        this.bitmapMatrix.mapRect(r);
        int k = canvas.saveLayer(r, null, 31);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXferMode);
        canvas.restoreToCount(k);
    }

    public void initIcon(int width, int height) {
        this.iconPaint = new Paint(1);
        this.iconPaint.setFilterBitmap(true);
        this.iconPaint.setColor(7829368);
        this.paintXferMode.setColor(7829368);
        scalePath(5.0f, (float) width, (float) height);
        this.iconMaskPaint = new Paint(1);
        this.iconMaskPaint.setFilterBitmap(true);
        this.iconMaskPaint.setColor(7829368);
        this.iconXferMode = new PorterDuffXfermode(Mode.SRC_IN);
        this.iconMaskPaint.setXfermode(this.iconXferMode);
    }


    public void drawShapeIcon(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        setMaskBitmapPositions();
        this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.originalPath.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.maskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        this.transparentMaskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        if (drawPorterClear) {
            if (this.shapeMode == 3) {
                canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
            } else {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            }
            canvas.restoreToCount(j);
        }
        if (this.shapeMode == 3) {
            int i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height, null, 31);
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.iconPaint);
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.iconMaskPaint);
            canvas.restoreToCount(i);
            return;
        }
        canvas.drawPath(this.path, this.iconPaint);
    }


    public void drawShapeIcon2(Canvas canvas, int width, int height) {
        this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.originalPath.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.maskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        this.transparentMaskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        Paint p2 = new Paint();
        if (this.shapeMode == 3) {
            int i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height, null, 31);
            canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, p2);
            p2.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, p2);
            p2.setXfermode(null);
            canvas.restoreToCount(i);
            return;
        }
        canvas.drawPath(this.path, this.iconPaint);
    }

    public void bitmapMatrixScale(float scaleX, float scaleY, float centerX, float centerY) {
        this.bitmapMatrix.postScale(scaleX, scaleY, centerX, centerY);
        checkScaleBoundries();
    }

    public void bitmapMatrixScaleScrapBook(float scaleX, float scaleY) {
        this.f507p[0] = (float) (this.bitmapWidth / 2);
        this.f507p[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(this.f507p);
        this.bitmapMatrix.postScale(scaleX, scaleY, this.f507p[0], this.f507p[1]);
        checkScaleBoundries();
    }

    public void checkScaleBoundries() {
        float scale = getScale();
        if (scale < this.minScale) {
            this.bitmapMatrix.postScale(this.minScale / scale, this.minScale / scale, this.f507p[0], this.f507p[1]);
        }
        if (scale > this.maxScale) {
            this.bitmapMatrix.postScale(this.maxScale / scale, this.maxScale / scale, this.f507p[0], this.f507p[1]);
        }
    }

    public void bitmapMatrixTranslate(float dx, float dy) {
        this.bitmapMatrix.postTranslate(dx, dy);
        if (!this.isScrapBook) {
            checkBoundries();
        }
    }

    public void checkBoundries() {
        this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.bitmapRect);
        float dx = 0.0f;
        float dy = 0.0f;
        if (this.bitmapRect.left > this.bounds.left) {
            dx = this.bounds.left - this.bitmapRect.left;
        }
        if (this.bitmapRect.top > this.bounds.top) {
            dy = this.bounds.top - this.bitmapRect.top;
        }
        if (this.bitmapRect.right < this.bounds.right) {
            dx = this.bounds.right - this.bitmapRect.right;
        }
        if (this.bitmapRect.bottom < this.bounds.bottom) {
            dy = this.bounds.bottom - this.bitmapRect.bottom;
        }
        this.bitmapMatrix.postTranslate(dx, dy);
    }

    public void checkScaleBounds() {
        setMinScales(getBitmapScale());
        checkScaleBoundries();
    }

    public void bitmapMatrixgGetValues(float[] values2) {
        this.bitmapMatrix.getValues(values2);
    }

    public void bitmapMatrixRotate(float angle) {
        this.f507p[0] = (float) (this.bitmapWidth / 2);
        this.f507p[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(this.f507p);
        this.bitmapMatrix.postRotate(angle, this.f507p[0], this.f507p[1]);
    }

    public int setScaleMatrix(int mode) {
        if (this.f2082dx <= 0.5f) {
            this.f2082dx = ((float) this.bitmapWidth) / 100.0f;
        }
        if (this.f2083dy <= 0.5f) {
            this.f2083dy = ((float) this.bitmapHeight) / 100.0f;
        }
        PointF centerOfImage = getCenterOfImage();
        if (mode == 0) {
            setMatrixFit();
        } else if (mode == 1) {
            setBitmapPosition();
        } else if (mode == 3) {
            this.bitmapMatrix.postRotate(-90.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 2) {
            this.bitmapMatrix.postRotate(90.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 4) {
            this.bitmapMatrix.postScale(-1.0f, 1.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 5) {
            this.bitmapMatrix.postScale(1.0f, -1.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 6) {
            this.bitmapMatrix.postRotate(-10.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 7) {
            this.bitmapMatrix.postRotate(10.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 8) {
            if (getScale() >= this.maxScale) {
                return 1;
            }
            this.bitmapMatrix.postScale(this.scaleUp, this.scaleUp, centerOfImage.x, centerOfImage.y);
        } else if (mode == 9) {
            if (getScale() <= this.minScale) {
                return 2;
            }
            this.bitmapMatrix.postScale(this.scaleDown, this.scaleDown, centerOfImage.x, centerOfImage.y);
        } else if (mode == 10) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.right <= this.bounds.right && !this.isScrapBook) {
                return 3;
            }
            this.bitmapMatrix.postTranslate(-this.f2082dx, 0.0f);
        } else if (mode == 11) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.left >= this.bounds.left && !this.isScrapBook) {
                return 4;
            }
            this.bitmapMatrix.postTranslate(this.f2082dx, 0.0f);
        } else if (mode == 12) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.bottom <= this.bounds.bottom && !this.isScrapBook) {
                return 5;
            }
            this.bitmapMatrix.postTranslate(0.0f, -this.f2083dy);
        } else if (mode == 13) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.top >= this.bounds.top && !this.isScrapBook) {
                return 6;
            }
            this.bitmapMatrix.postTranslate(0.0f, this.f2083dy);
        }
        checkScaleBoundries();
        if (!this.isScrapBook) {
            checkBoundries();
        }
        return 0;
    }


    public PointF getCenterOfImage() {
        if (this.centerOriginal == null) {
            this.centerOriginal = new PointF();
        }
        if (this.f506f == null) {
            this.f506f = new float[2];
        }
        float y = ((float) this.bitmapHeight) / 2.0f;
        this.f506f[0] = ((float) this.bitmapWidth) / 2.0f;
        this.f506f[1] = y;
        this.bitmapMatrix.mapPoints(this.f506f);
        this.centerOriginal.set(this.f506f[0], this.f506f[1]);
        return this.centerOriginal;
    }


    public void setMatrixFit() {
        float scaleBitmap = Math.min(this.bounds.width() / ((float) this.bitmapWidth), this.bounds.height() / ((float) this.bitmapHeight));
        if (this.isScrapBook) {
            scaleBitmap *= Collage.scrapBookShapeScale;
        }
        Log.e(TAG, "Collage.scrapBookShapeScale " + Collage.scrapBookShapeScale);
        float bitmapY = this.bounds.top + ((this.bounds.height() - (((float) this.bitmapHeight) * scaleBitmap)) / 2.0f);
        float bitmapX = this.bounds.left + ((this.bounds.width() - (((float) this.bitmapWidth) * scaleBitmap)) / 2.0f);
        this.bitmapMatrix.reset();
        this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
        this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
    }

    private void setScrapBookBitmapPosition(int index, boolean isChangeRatio, int width, int height) {
        if (isChangeRatio) {
            int w = this.bitmapWidth;
            int h = this.bitmapHeight;
            float[] points2 = {0.0f, 0.0f, (float) w, 0.0f, (float) w, (float) h, 0.0f, (float) h};
            this.bitmapMatrix.mapPoints(points2);
            RectF drawArea = new RectF((float) this.offsetX, (float) this.offsetY, (float) (this.offsetX + width), (float) (this.offsetY + height));
            if (!drawArea.contains(points2[0], points2[1])) {
                if (!drawArea.contains(points2[2], points2[3])) {
                    if (!drawArea.contains(points2[4], points2[5])) {
                        if (!drawArea.contains(points2[6], points2[7])) {
                            PointF A = new PointF((float) this.offsetX, (float) this.offsetY);
                            PointF B = new PointF((float) (this.offsetX + width), (float) this.offsetY);
                            PointF P = new PointF();
                            if (points2[1] < ((float) this.offsetY)) {
                                P.set(points2[0], points2[1]);
                                Log.e(TAG, "0  " + distToSegment(P, A, B));
                                P.set(points2[2], points2[3]);
                                Log.e(TAG, "1  " + distToSegment(P, A, B));
                                P.set(points2[4], points2[5]);
                                Log.e(TAG, "2  " + distToSegment(P, A, B));
                                P.set(points2[6], points2[7]);
                                float[] f = {pointToLineDistance(A, B, P), pointToLineDistance(A, B, P), pointToLineDistance(A, B, P), pointToLineDistance(A, B, P)};
                                Log.e(TAG, "3  " + distToSegment(P, A, B));
                                float min = f[0];
                                int minIndex = 0;
                                for (int i = 1; i < 4; i++) {
                                    if (f[i] < min) {
                                        min = f[i];
                                        minIndex = i;
                                    }
                                    Log.e(TAG, "fi  " + f[i]);
                                }
                                this.bitmapMatrix.postTranslate(0.0f, ((float) (this.offsetY + 120)) - points2[(minIndex * 2) + 1]);
                                return;
                            }
                            PointF A2 = new PointF((float) this.offsetX, (float) (this.offsetY + height));
                            PointF B2 = new PointF((float) (this.offsetX + width), (float) (this.offsetY + height));
                            P.set(points2[0], points2[1]);
                            float f2 = A2.x;
                            float f22 = B2.x;
                            Log.e(TAG, "0  " + distToSegment(P, A2, B2));
                            float f23 = P.x;
                            P.set(points2[2], points2[3]);
                            Log.e(TAG, "1  " + distToSegment(P, A2, B2));
                            float f24 = P.x;
                            P.set(points2[4], points2[5]);
                            Log.e(TAG, "2  " + distToSegment(P, A2, B2));
                            float f25 = P.x;
                            P.set(points2[6], points2[7]);
                            float[] f3 = {pointToLineDistance(A2, B2, P), pointToLineDistance(A2, B2, P), pointToLineDistance(A2, B2, P), pointToLineDistance(A2, B2, P)};
                            Log.e(TAG, "3  " + distToSegment(P, A2, B2));
                            float f26 = P.x;
                            float min2 = f3[0];
                            int minIndex2 = 0;
                            Log.e(TAG, "bi  " + f3[0]);
                            for (int i2 = 1; i2 < 4; i2++) {
                                if (f3[i2] < min2) {
                                    min2 = f3[i2];
                                    minIndex2 = i2;
                                }
                                Log.e(TAG, "bi  " + f3[i2]);
                            }
                            Log.e(TAG, "minIndex  " + minIndex2);
                            Log.e(TAG, " points[minIndex*2+1] " + points2[(minIndex2 * 2) + 1]);
                            Log.e(TAG, "translate " + (((float) ((this.offsetY + height) - 120)) - points2[(minIndex2 * 2) + 1]));
                            this.bitmapMatrix.postTranslate(0.0f, ((float) ((this.offsetY + height) - 120)) - points2[(minIndex2 * 2) + 1]);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.bitmapMatrix = new Matrix();
        setMatrixFit();
        float actualScale = getScale();
        setMaxMinScales(actualScale);
        float scale = 1.0f / actualScale;
        this.touchStrokeWidth = this.tempTouchStrokeWidth * scale;
        this.scrapBookPadding = 25.0f * scale;
        this.bitmapMatrix.postRotate((float) scrapBookRotation[index], this.bounds.left + (this.bounds.width() / 2.0f), this.bounds.top + (this.bounds.height() / 2.0f));
        this.touchRect = new RectF(-this.scrapBookPadding, -this.scrapBookPadding, ((float) this.bitmapWidth) + this.scrapBookPadding, ((float) this.bitmapHeight) + this.scrapBookPadding);
        this.touchPaint.setColor(1290417);
        this.touchPaint.setFilterBitmap(true);
        this.touchPaint.setStyle(Style.STROKE);
        this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
        this.borderPaint.setColor(-1);
        this.borderPaint.setStyle(Style.STROKE);
        this.borderPaint.setStrokeWidth((float) this.borderStrokeWidth);
        this.borderPaint.setAntiAlias(true);
    }

    public float pointToLineDistance(PointF A, PointF B, PointF P) {
        return Math.abs(((P.x - A.x) * (B.y - A.y)) - ((P.y - A.y) * (B.x - A.x))) / ((float) Math.sqrt((double) (((B.x - A.x) * (B.x - A.x)) + ((B.y - A.y) * (B.y - A.y)))));
    }


    public float sqr(float x) {
        return x * x;
    }


    public float dist2(PointF v, PointF w) {
        return sqr(v.x - w.x) + sqr(v.y - w.y);
    }


    public float distToSegmentSquared(PointF p, PointF v, PointF w) {
        float l2 = dist2(v, w);
        if (l2 == 0.0f) {
            return dist2(p, v);
        }
        float t = (((p.x - v.x) * (w.x - v.x)) + ((p.y - v.y) * (w.y - v.y))) / l2;
        if (t < 0.0f) {
            return dist2(p, v);
        }
        if (t > 1.0f) {
            return dist2(p, w);
        }
        return dist2(p, new PointF(v.x + ((w.x - v.x) * t), v.y + ((w.y - v.y) * t)));
    }

    public float distToSegment(PointF p, PointF v, PointF w) {
        return (float) Math.sqrt((double) distToSegmentSquared(p, v, w));
    }

    public float[] getMappedCenter() {
        this.pts[0] = (float) (this.bitmapWidth / 2);
        this.pts[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(this.pts, this.pts);
        return this.pts;
    }

    public boolean isScrapBookSelected(float x1, float y1) {
        this.pts[0] = x1;
        this.pts[1] = y1;
        this.inverse.reset();
        boolean invert = this.bitmapMatrix.invert(this.inverse);
        this.inverse.mapPoints(this.pts, this.pts);
        float x = this.pts[0];
        float y = this.pts[1];
        if (x < 0.0f || x > ((float) this.bitmapWidth) || y < 0.0f || y > ((float) this.bitmapHeight)) {
            return false;
        }
        return true;
    }

    public void drawShapeForScrapBook(Canvas canvas, int width, int height, boolean isSelected, boolean isOrthogonal) {
        canvas.save();
        canvas.concat(this.bitmapMatrix);
        this.npd.setBounds((-this.npdPadding) - this.borderStrokeWidth, (-this.npdPadding) - this.borderStrokeWidth, this.bitmapWidth + this.npdPadding + this.borderStrokeWidth, this.bitmapHeight + this.npdPadding + this.borderStrokeWidth);
        this.npd.draw(canvas);
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paintScrap);
        if (isSelected) {
            this.touchStrokeWidth = this.tempTouchStrokeWidth * (1.0f / getScale());
            this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
            canvas.drawRect(this.touchRect, this.touchPaint);
            setDelAndScaleBitmapMatrix();
            if (this.btmDelete != null && !this.btmDelete.isRecycled()) {
                canvas.drawBitmap(this.btmDelete, this.removeBitmapMatrix, this.touchPaint);
            }
            if (this.btmScale != null && !this.btmScale.isRecycled()) {
                canvas.drawBitmap(this.btmScale, this.scaleBitmapMatrix, this.touchPaint);
            }
            if (isOrthogonal) {
                canvas.drawPath(this.dashPathVertical, this.dashPaint);
                canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
            }
        }
        canvas.drawRect((float) ((-this.borderStrokeWidth) / 2), (float) ((-this.borderStrokeWidth) / 2), (float) (this.bitmapWidth + (this.borderStrokeWidth / 2)), (float) (this.bitmapHeight + (this.borderStrokeWidth / 2)), this.borderPaint);
        canvas.restore();
    }


    public void setDelAndScaleBitmapMatrix() {
        if (this.removeBitmapMatrix == null) {
            this.removeBitmapMatrix = new Matrix();
        }
        if (this.scaleBitmapMatrix == null) {
            this.scaleBitmapMatrix = new Matrix();
        }
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        if (this.delW == 0 && this.btmDelete != null) {
            this.delW = this.btmDelete.getWidth();
        }
        if (this.screenWidth <= 0) {
            this.screenWidth = 720;
        }
        float bitmapScale = ((((float) this.screenWidth) / 20.0f) * 2.0f) / ((float) this.delW);
        this.deleteWidthHalf = (((float) this.delW) * bitmapScale) / 1.4f;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate((-this.scrapBookPadding) - ((((float) this.delW) * bitmapScale) / 2.0f), (-this.scrapBookPadding) - ((((float) this.delW) * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate((((float) this.bitmapWidth) + this.scrapBookPadding) - ((((float) this.delW) * bitmapScale) / 2.0f), (((float) this.bitmapHeight) + this.scrapBookPadding) - ((((float) this.delW) * bitmapScale) / 2.0f));
        float scale = getScale();
        this.scaleBitmapMatrix.postScale(1.0f / scale, 1.0f / scale, this.touchRect.right, this.touchRect.bottom);
        this.removeBitmapMatrix.postScale(1.0f / scale, 1.0f / scale, this.touchRect.left, this.touchRect.top);
        if (this.screenWidth > 0) {
            this.tempTouchStrokeWidth = ((float) this.screenWidth) / 120.0f;
        }
    }

    public void initScrapBook(NinePatchDrawable npd2) {
        setNinePatch(npd2);
    }

    public void setNinePatch(NinePatchDrawable npd2) {
        this.npd = npd2;
        this.touchRect.round(new Rect());
    }

    public float getScale() {
        this.bitmapMatrix.getValues(this.values);
        float scalex = this.values[0];
        float skewy = this.values[3];
        float scale = (float) Math.sqrt((double) ((scalex * scalex) + (skewy * skewy)));
        if (scale <= 0.0f) {
            return 1.0f;
        }
        return scale;
    }

    public boolean isInCircle(float x1, float y1) {
        this.pts[0] = x1;
        this.pts[1] = y1;
        this.bitmapMatrix.invert(this.inverse);
        this.inverse.mapPoints(this.pts, this.pts);
        float x = this.pts[0];
        float y = this.pts[1];
        float scale = getScale();
        if (((x - this.touchRect.right) * (x - this.touchRect.right)) + ((y - this.touchRect.bottom) * (y - this.touchRect.bottom)) < (this.deleteWidthHalf * this.deleteWidthHalf) / (scale * scale)) {
            return true;
        }
        return false;
    }

    public boolean isOnCross(float x1, float y1) {
        this.pts[0] = x1;
        this.pts[1] = y1;
        this.bitmapMatrix.invert(this.inverse);
        this.inverse.mapPoints(this.pts, this.pts);
        float x = this.pts[0];
        float y = this.pts[1];
        float scale = getScale();
        if (((x - this.touchRect.left) * (x - this.touchRect.left)) + ((y - this.touchRect.top) * (y - this.touchRect.top)) < (this.deleteWidthHalf * this.deleteWidthHalf) / (scale * scale)) {
            return true;
        }
        return false;
    }

    public void resetDashPaths() {
        if (this.dashPathVertical == null) {
            this.dashPathVertical = new Path();
        }
        this.dashPathVertical.reset();
        this.dashPathVertical.moveTo((float) (this.bitmapWidth / 2), (float) ((-this.bitmapHeight) / 5));
        this.dashPathVertical.lineTo((float) (this.bitmapWidth / 2), (float) ((this.bitmapHeight * 6) / 5));
        if (this.dashPathHorizontal == null) {
            this.dashPathHorizontal = new Path();
        }
        this.dashPathHorizontal.reset();
        this.dashPathHorizontal.moveTo((float) ((-this.bitmapWidth) / 5), (float) (this.bitmapHeight / 2));
        this.dashPathHorizontal.lineTo((float) ((this.bitmapWidth * 6) / 5), (float) (this.bitmapHeight / 2));
    }
}
