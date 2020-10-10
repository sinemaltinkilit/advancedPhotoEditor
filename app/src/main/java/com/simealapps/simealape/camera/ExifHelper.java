package com.simealapps.simealape.camera;

import android.media.ExifInterface;

import java.io.IOException;
import java.util.HashMap;

public class ExifHelper {
    public static final String[] EXIF_KEYS = new String[]{"FNumber", "DateTime", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "WhiteBalance"};
    HashMap<String, String> f10001a;

    public ExifHelper() {
        reset();
    }

    public String getAttribute(String str) {
        return (String) this.f10001a.get(str);
    }

    public void readExif(String str) throws IOException {
        ExifInterface exifInterface = new ExifInterface(str);
        for (String str2 : EXIF_KEYS) {
            String attribute = exifInterface.getAttribute(str2);
            if (attribute != null) {
                this.f10001a.put(str2, attribute);
            }
        }
    }

    public void reset() {
        this.f10001a = new HashMap();
    }

    public void setAttribute(String str, String str2) {
        this.f10001a.put(str, str2);
    }

    public void writeExif(String str) throws IOException {
        ExifInterface exifInterface = new ExifInterface(str);
        for (String str2 : this.f10001a.keySet()) {
            String str3 = (String) this.f10001a.get(str2);
            if (!(str2 == null || str3 == null)) {
                exifInterface.setAttribute(str2, str3);
            }
        }
        exifInterface.saveAttributes();
    }
}
