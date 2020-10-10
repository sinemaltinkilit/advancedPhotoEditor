package com.simealapps.simealape.canvastextview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Typeface;

import androidx.core.internal.view.SupportMenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TextDataItem implements Serializable {
    public static final int defBgAlpha = 255;
    public static final int defBgColor = 0;
    public static final String defaultMessage = "Preview Text";
    private static final long serialVersionUID = 3789254181897332363L;
    public CustomMatrix canvasMatrix;
    private String fontPath;
    public CustomMatrix imageSaveMatrix;
    public boolean isTypeFaceSet;
    public String message;
    public CustomPaint textPaint;
    public float textSize;
    public float xPos;
    public float yPos;

    public void setTextFont(String path, Context context) {
        this.fontPath = path;
        if (this.fontPath != null) {
           // Typeface typeFace = FontCache.get(context, this.fontPath);

            Typeface typeFace= Typeface.createFromAsset(context.getAssets(),fontPath);
            if (typeFace != null) {
                this.textPaint.setTypeface(typeFace);
            }
            this.isTypeFaceSet = true;
        }
    }

    public String getFontPath() {
        return this.fontPath;
    }

    public TextDataItem(float textSize2) {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.canvasMatrix = new CustomMatrix();
        this.textPaint = new CustomPaint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.textSize = textSize2;
        this.textPaint.setTextSize(textSize2);
        this.fontPath = null;
    }

    public TextDataItem() {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.canvasMatrix = new CustomMatrix();
        this.textPaint = new CustomPaint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(-1);
        this.textSize = 60.0f;
        this.textPaint.setTextSize(this.textSize);
        this.fontPath = null;
    }

    public TextDataItem(TextDataItem src) {
        this.message = defaultMessage;
        this.isTypeFaceSet = false;
        this.canvasMatrix = new CustomMatrix(src.canvasMatrix);
        this.textPaint = new CustomPaint(src.textPaint);
        this.textPaint.setAntiAlias(true);
        this.message = new String(src.message);
        this.textSize = src.textSize;
        this.xPos = src.xPos;
        this.yPos = src.yPos;
        if (src.imageSaveMatrix != null) {
            this.imageSaveMatrix = new CustomMatrix(src.imageSaveMatrix);
        }
        if (src.fontPath != null) {
            this.fontPath = src.fontPath;
        }
    }

    public void setImageSaveMatrix(Matrix mtr) {
        CustomMatrix inverse = new CustomMatrix(mtr);
        mtr.invert(inverse);
        inverse.preConcat(this.canvasMatrix);
        this.imageSaveMatrix = inverse;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeFloat(this.xPos);
        oos.writeFloat(this.yPos);
        oos.writeFloat(this.textSize);
        oos.writeObject(this.textPaint);
        oos.writeObject(this.message);
        oos.writeObject(this.canvasMatrix);
        oos.writeObject(this.imageSaveMatrix);
        oos.writeObject(this.fontPath);
    }

    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException {
        ois.defaultReadObject();
        this.xPos = ois.readFloat();
        this.yPos = ois.readFloat();
        this.textSize = ois.readFloat();
        this.textPaint = (CustomPaint) ois.readObject();
        this.message = (String) ois.readObject();
        this.canvasMatrix = (CustomMatrix) ois.readObject();
        this.imageSaveMatrix = (CustomMatrix) ois.readObject();
        try {
            this.fontPath = (String) ois.readObject();
        } catch (Exception e) {
            this.fontPath = null;
        }
        this.textPaint.setAntiAlias(true);
        this.isTypeFaceSet = false;
    }
}
