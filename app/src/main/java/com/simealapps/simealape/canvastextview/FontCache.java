package com.simealapps.simealape.canvastextview;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontCache {
    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    public static Typeface get(Context c, String name) {
        Typeface typeface;
        synchronized (cache) {
            if (name != null) {
                if (!(name.length() == 0 || name.compareTo("") == 0)) {
                    if (!cache.containsKey(name)) {
                        cache.put(name, Typeface.createFromAsset(c.getAssets(), name));
                    }
                    typeface = (Typeface) cache.get(name);
                }
            }
            typeface = null;
        }
        return typeface;
    }
}
