package com.simealapps.simealape.utils;

public class ShapeLayout {
    boolean isScalable = false;
    int porterDuffClearBorderIntex = -1;
    public Shape[] shapeArr;

    public ShapeLayout(Shape[] arr) {
        this.shapeArr = arr;
    }

    public void setClearIndex(int index) {
        if (index >= 0 && index < this.shapeArr.length) {
            this.porterDuffClearBorderIntex = index;
        }
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIntex;
    }
}
