package com.simealapps.simealape.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class ImageBlurNormal {
    int lastBlurRadius = -1;
    Matrix matrixBlur = new Matrix();
    Paint paintBlur = new Paint(2);

    public static Bitmap createCroppedBitmap(Bitmap bitmap, int i, int j, int k, int l, boolean flag) {
        Bitmap bitmap1 = Bitmap.createBitmap(k, l, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        paint.setFilterBitmap(flag);
        canvas.drawBitmap(bitmap, (float) (-i), (float) (-j), paint);
        return bitmap1;
    }
}
