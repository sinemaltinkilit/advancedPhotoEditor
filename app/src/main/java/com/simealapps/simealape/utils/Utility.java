package com.simealapps.simealape.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Debug;
import android.util.Log;

public class Utility {
    private static String TAG = Utility.class.getName();
    private static final float limitDivider = 60.0f;
    private static final float limitDividerGinger = 160.0f;

    public static int maxSizeForDimension(Context context, int count, float upperLimit) {
        float divider = limitDivider;
        if (VERSION.SDK_INT <= 11) {
            upperLimit = 800.0f;
            divider = limitDividerGinger;
        }
        Log.e(TAG, "divider = " + divider);
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / ((double) (((float) count) * divider)));
        if (maxSize <= 0) {
            maxSize = getDefaultLimit(count, upperLimit);
        }
        return Math.min(maxSize, getDefaultLimit(count, upperLimit));
    }

    public static double getLeftSizeOfMemory() {
        return (((double) Runtime.getRuntime().maxMemory()) - (((double) Runtime.getRuntime().totalMemory()) - ((double) Runtime.getRuntime().freeMemory()))) - ((double) Debug.getNativeHeapAllocatedSize());
    }

    public static void logFreeMemory(Context context) {
        Log.e(TAG, "free memory own method = " + (getLeftSizeOfMemory() / 1048576.0d));
    }

    private static int getDefaultLimit(int count, float upperLimit) {
        int limit = (int) (((double) upperLimit) / Math.sqrt((double) count));
        Log.e(TAG, "limit = " + limit);
        return limit;
    }
}
