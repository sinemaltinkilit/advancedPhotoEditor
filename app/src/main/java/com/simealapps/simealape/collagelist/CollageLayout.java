package com.simealapps.simealape.collagelist;

import java.util.ArrayList;
import java.util.List;

public class CollageLayout {
    public int[][] exceptionIndexForShapes = null;
    boolean isScalable = false;
    public List<MaskPair> maskPairList = new ArrayList();
    int porterDuffClearBorderIntex = -1;
    public List shapeList;
    public boolean useLine = true;

    public CollageLayout() {
    }

    public CollageLayout(List list) {
        this.shapeList = list;
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIntex;
    }

    public int[] getexceptionIndex(int i) {
        if (this.exceptionIndexForShapes == null || i >= this.exceptionIndexForShapes.length || i < 0) {
            return null;
        }
        return this.exceptionIndexForShapes[i];
    }

    public void setClearIndex(int i) {
        if (i >= 0 && i < this.shapeList.size()) {
            this.porterDuffClearBorderIntex = i;
        }
    }
}
