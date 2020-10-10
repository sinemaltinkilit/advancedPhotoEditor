package com.simealapps.simealape.collagelist;

import android.graphics.PointF;

import java.util.ArrayList;

import com.simealapps.simealape.R;

public class CollageOne extends Collage {
    public static int shapeCount = 1;

    public CollageOne(int i, int j) {
        this.collageLayoutList = new ArrayList();
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        ArrayList obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj3 = new CollageLayout(obj2);
        obj3.maskPairList.add(new MaskPair(0, R.drawable.mask_butterfly));
        this.collageLayoutList.add(obj3);
        ArrayList obj4 = new ArrayList();
        obj4.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj5 = new CollageLayout(obj4);
        obj5.maskPairList.add(new MaskPair(0, R.drawable.mask_cloud));
        this.collageLayoutList.add(obj5);
        ArrayList obj6 = new ArrayList();
        obj6.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj7 = new CollageLayout(obj6);
        obj7.maskPairList.add(new MaskPair(0, R.drawable.mask_clover));
        this.collageLayoutList.add(obj7);
        ArrayList obj8 = new ArrayList();
        obj8.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj9 = new CollageLayout(obj8);
        obj9.maskPairList.add(new MaskPair(0, R.drawable.mask_leaf));
        this.collageLayoutList.add(obj9);
        ArrayList obj10 = new ArrayList();
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj11 = new CollageLayout(obj10);
        obj11.maskPairList.add(new MaskPair(0, R.drawable.mask_left_foot));
        this.collageLayoutList.add(obj11);
        ArrayList obj12 = new ArrayList();
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj13 = new CollageLayout(obj12);
        obj13.maskPairList.add(new MaskPair(0, R.drawable.mask_diamond));
        this.collageLayoutList.add(obj13);
        ArrayList obj14 = new ArrayList();
        obj14.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj15 = new CollageLayout(obj14);
        obj15.maskPairList.add(new MaskPair(0, R.drawable.mask_hexagon));
        this.collageLayoutList.add(obj15);
        ArrayList obj16 = new ArrayList();
        obj16.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj17 = new CollageLayout(obj16);
        obj17.maskPairList.add(new MaskPair(0, R.drawable.mask_heart));
        this.collageLayoutList.add(obj17);
        ArrayList obj18 = new ArrayList();
        obj18.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj19 = new CollageLayout(obj18);
        obj19.maskPairList.add(new MaskPair(0, R.drawable.mask_paw));
        this.collageLayoutList.add(obj19);
        ArrayList obj20 = new ArrayList();
        obj20.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj21 = new CollageLayout(obj20);
        obj21.maskPairList.add(new MaskPair(0, R.drawable.mask_circle));
        this.collageLayoutList.add(obj21);
        ArrayList obj22 = new ArrayList();
        obj22.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        CollageLayout obj23 = new CollageLayout(obj22);
        obj23.maskPairList.add(new MaskPair(0, R.drawable.mask_twitter));
        this.collageLayoutList.add(obj23);
    }
}
