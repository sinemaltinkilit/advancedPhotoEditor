package com.simealapps.simealape.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Parameter implements Serializable, Parcelable {
    public static final Creator<Parameter> CREATOR = new Creator<Parameter>() {
        public Parameter createFromParcel(Parcel parcel) {
            return new Parameter(parcel);
        }

        public Parameter[] newArray(int i) {
            return new Parameter[i];
        }
    };
    public static final int seek_bar_brightness = 0;
    public static final int seek_bar_saturation = 3;
    public static final int seek_bar_sharpen = 5;
    public static final int seek_bar_temperature = 2;
    public static final int seek_bar_tint = 4;
    private static final long serialVersionUID = -3588782317514910667L;
    public static AtomicInteger uniqueId = new AtomicInteger();
    public int blur = 0;
    public int brightness;
    public int contrast;
    public float highlight = 0.0f;


    public int f2077id;
    public int saturation;
    public int seekBarMode = 0;
    public int selectedBorderIndex;
    public int selectedFilterIndex;
    public int selectedOverlayIndex;
    public int selectedTextureIndex;
    public float shadow = 0.0f;
    public float sharpen = 0.0f;
    public int temperature;
    public int tint;

    public Parameter copy() {
        return new Parameter(this);
    }

    public int getId() {
        return this.f2077id;
    }

    public void setId(int d) {
        this.f2077id = d;
    }

    public boolean isParameterReallyChanged(Parameter p) {
        if (!(this.contrast == p.contrast && this.brightness == p.brightness && this.saturation == p.saturation && this.temperature == p.temperature && this.tint == p.tint && this.sharpen == p.sharpen && this.blur == p.blur && this.highlight == p.highlight && this.shadow == p.shadow && this.selectedBorderIndex == p.selectedBorderIndex && this.selectedFilterIndex == p.selectedFilterIndex && this.selectedOverlayIndex == p.selectedOverlayIndex && this.selectedTextureIndex != p.selectedTextureIndex)) {
        }
        return true;
    }

    public Parameter(Parameter p) {
        set(p);
    }

    public Parameter() {
        reset();
        this.f2077id = uniqueId.getAndIncrement();
        this.seekBarMode = 0;
    }

    public void set(Parameter p) {
        this.brightness = p.brightness;
        this.temperature = p.temperature;
        this.contrast = p.contrast;
        this.saturation = p.saturation;
        this.tint = p.tint;
        this.selectedTextureIndex = p.selectedTextureIndex;
        this.selectedBorderIndex = p.selectedBorderIndex;
        this.selectedOverlayIndex = p.selectedOverlayIndex;
        this.selectedFilterIndex = p.selectedFilterIndex;
        this.sharpen = p.sharpen;
        this.blur = p.blur;
        this.highlight = p.highlight;
        this.shadow = p.shadow;
        this.seekBarMode = p.seekBarMode;
        this.f2077id = p.f2077id;
    }

    public void reset() {
        this.brightness = 0;
        this.contrast = 0;
        this.temperature = 0;
        this.saturation = 50;
        this.tint = 0;
        this.selectedTextureIndex = 0;
        this.selectedBorderIndex = 0;
        this.selectedOverlayIndex = 0;
        this.selectedFilterIndex = 0;
        this.sharpen = 0.0f;
        this.blur = 0;
        this.highlight = 0.0f;
        this.shadow = 0.0f;
    }

    public void setTemperature(int progress) {
        this.temperature = (progress - 50) * 2;
    }

    public void setContrast(int progress) {
        this.contrast = (progress - 50) * 2;
    }

    public void setBrightness(int progress) {
        int value = progress - 50;
        if (value < 0) {
            this.brightness = value * 3;
        } else {
            this.brightness = value * 5;
        }
    }

    public int getContrastProgress() {
        return (this.contrast / 2) + 50;
    }

    public int getBrightProgress() {
        if (this.brightness < 0) {
            return (this.brightness / 3) + 50;
        }
        return (this.brightness / 5) + 50;
    }

    public int getTemperatureProgress() {
        return (this.temperature / 2) + 50;
    }

    public void setSaturation(int progress) {
        this.saturation = progress;
    }

    public void setTint(int value) {
        this.tint = value - 50;
    }

    public int getTintProgressValue() {
        return this.tint + 50;
    }

    public void setSharpen(int value) {
        this.sharpen = ((float) value) / 100.0f;
    }

    public int getSharpenValue() {
        return (int) (this.sharpen * 100.0f);
    }

    public void setBlur(int value) {
        float radius = ((float) value) / 4.0f;
        if (radius > 25.0f) {
            radius = 25.0f;
        }
        this.blur = (int) radius;
    }

    public int getBlurValue() {
        return this.blur * 4;
    }

    public void setHighlight(int value) {
        this.highlight = ((float) (value - 50)) / 255.0f;
    }

    public int getHighlightValue() {
        return (int) ((this.highlight * 255.0f) + 50.0f);
    }

    public void setShadow(int value) {
        this.shadow = ((float) (value - 50)) / 255.0f;
    }

    public int getShadowValue() {
        return (int) ((this.shadow * 255.0f) + 50.0f);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(this.brightness);
        oos.writeInt(this.contrast);
        oos.writeInt(this.temperature);
        oos.writeInt(this.saturation);
        oos.writeInt(this.tint);
        oos.writeInt(this.selectedTextureIndex);
        oos.writeInt(this.selectedBorderIndex);
        oos.writeInt(this.selectedOverlayIndex);
        oos.writeInt(this.selectedFilterIndex);
        oos.writeInt(this.seekBarMode);
        oos.writeFloat(this.sharpen);
        oos.writeInt(this.blur);
        oos.writeFloat(this.highlight);
        oos.writeFloat(this.shadow);
        oos.writeInt(this.f2077id);
    }

    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException {
        ois.defaultReadObject();
        this.brightness = ois.readInt();
        this.contrast = ois.readInt();
        this.temperature = ois.readInt();
        this.saturation = ois.readInt();
        this.tint = ois.readInt();
        this.selectedTextureIndex = ois.readInt();
        this.selectedBorderIndex = ois.readInt();
        this.selectedOverlayIndex = ois.readInt();
        this.selectedFilterIndex = ois.readInt();
        this.seekBarMode = ois.readInt();
        try {
            this.sharpen = ois.readFloat();
            this.blur = ois.readInt();
            this.highlight = ois.readFloat();
            this.shadow = ois.readFloat();
            this.f2077id = ois.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Parameter(Parcel in) {
        this.brightness = in.readInt();
        this.contrast = in.readInt();
        this.temperature = in.readInt();
        this.saturation = in.readInt();
        this.tint = in.readInt();
        this.selectedTextureIndex = in.readInt();
        this.selectedBorderIndex = in.readInt();
        this.selectedOverlayIndex = in.readInt();
        this.selectedFilterIndex = in.readInt();
        this.seekBarMode = in.readInt();
        this.sharpen = in.readFloat();
        this.blur = in.readInt();
        this.highlight = in.readFloat();
        this.shadow = in.readFloat();
        this.f2077id = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int arg1) {
        out.writeInt(this.brightness);
        out.writeInt(this.contrast);
        out.writeInt(this.temperature);
        out.writeInt(this.saturation);
        out.writeInt(this.tint);
        out.writeInt(this.selectedTextureIndex);
        out.writeInt(this.selectedBorderIndex);
        out.writeInt(this.selectedOverlayIndex);
        out.writeInt(this.selectedFilterIndex);
        out.writeInt(this.seekBarMode);
        out.writeFloat(this.sharpen);
        out.writeInt(this.blur);
        out.writeFloat(this.highlight);
        out.writeFloat(this.shadow);
        out.writeInt(this.f2077id);
    }
}
