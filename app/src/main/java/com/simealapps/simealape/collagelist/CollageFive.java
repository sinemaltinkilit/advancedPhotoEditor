package com.simealapps.simealape.collagelist;

import android.graphics.PointF;

import java.util.ArrayList;

import com.simealapps.simealape.R;

public class CollageFive extends Collage {
    public static int shapeCount = 5;

    public CollageFive(int i, int j) {
        this.collageLayoutList = new ArrayList();
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.7083333f * ((float) j)), new PointF(0.7083333f * ((float) i), 0.7083333f * ((float) j)), new PointF(0.7083333f * ((float) i), ((float) j) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.2916667f * ((float) i), ((float) j) * 0.0f), new PointF(0.2916667f * ((float) i), 0.7083333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.7083333f * ((float) j))});
        obj.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.2916667f * ((float) j)), new PointF(0.2916667f * ((float) i), 0.2916667f * ((float) j)), new PointF(0.2916667f * ((float) i), ((float) j) * 0.0f)});
        obj.add(new PointF[]{new PointF(0.7083333f * ((float) i), ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.2916667f * ((float) j)), new PointF(0.7083333f * ((float) i), 0.2916667f * ((float) j))});
        obj.add(new PointF[]{new PointF(0.2916667f * ((float) i), 0.7083333f * ((float) j)), new PointF(0.2916667f * ((float) i), 0.2916667f * ((float) j)), new PointF(0.7083333f * ((float) i), 0.2916667f * ((float) j)), new PointF(0.7083333f * ((float) i), 0.7083333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj));
        ArrayList obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.2f * ((float) i), ((float) j) * 0.5f), new PointF(0.3888917f * ((float) i), 0.3888917f * ((float) j)), new PointF(((float) i) * 0.5f, 0.2f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(0.8f * ((float) i), ((float) j) * 0.5f), new PointF(0.6111109f * ((float) i), 0.3888917f * ((float) j)), new PointF(((float) i) * 0.5f, 0.2f * ((float) j))});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.2f * ((float) i), ((float) j) * 0.5f), new PointF(0.3888917f * ((float) i), 0.6110833f * ((float) j)), new PointF(((float) i) * 0.5f, 0.8f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, 0.8f * ((float) j)), new PointF(0.6111084f * ((float) i), 0.6111084f * ((float) j)), new PointF(0.8f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.8f * ((float) j)), new PointF(0.3888917f * ((float) i), 0.6110833f * ((float) j)), new PointF(0.2f * ((float) i), ((float) j) * 0.5f), new PointF(0.3888917f * ((float) i), 0.3888917f * ((float) j)), new PointF(((float) i) * 0.5f, 0.2f * ((float) j)), new PointF(0.6111109f * ((float) i), 0.3888917f * ((float) j)), new PointF(0.8f * ((float) i), ((float) j) * 0.5f), new PointF(0.6111084f * ((float) i), 0.6111084f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj2));
        ArrayList obj3 = new ArrayList();
        obj3.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj3.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj3.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj3.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j))});
        obj3.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj3));
        ArrayList obj4 = new ArrayList();
        obj4.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj4.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj4.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj4.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj4.add(new PointF[]{new PointF(0.25f * ((float) i), 0.25f * ((float) j)), new PointF(0.25f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.25f * ((float) j))});
        CollageLayout obj5 = new CollageLayout(obj4);
        obj5.setClearIndex(4);
        this.collageLayoutList.add(obj5);
        ArrayList obj6 = new ArrayList();
        obj6.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj6.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj6));
        ArrayList obj7 = new ArrayList();
        obj7.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj7.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj7.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj7.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj7.add(new PointF[]{new PointF(0.2f * ((float) i), 0.2f * ((float) j)), new PointF(0.2f * ((float) i), 0.8f * ((float) j)), new PointF(0.8f * ((float) i), 0.8f * ((float) j)), new PointF(0.8f * ((float) i), 0.2f * ((float) j))});
        CollageLayout obj8 = new CollageLayout(obj7);
        obj8.maskPairList.add(new MaskPair(4, R.drawable.mask_heart));
        obj8.setClearIndex(4);
        this.collageLayoutList.add(obj8);
        ArrayList obj9 = new ArrayList();
        obj9.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.25f * ((float) j)), new PointF(0.25f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, 0.75f * ((float) j)), new PointF(0.75f * ((float) i), ((float) j) * 0.5f)});
        obj9.add(new PointF[]{new PointF(0.25f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, 0.25f * ((float) j)), new PointF(0.25f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, 0.25f * ((float) j))});
        obj9.add(new PointF[]{new PointF(0.75f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.25f * ((float) j)), new PointF(0.75f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, 0.25f * ((float) j))});
        obj9.add(new PointF[]{new PointF(0.25f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, 0.75f * ((float) j)), new PointF(0.25f * ((float) i), ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, 0.75f * ((float) j))});
        obj9.add(new PointF[]{new PointF(0.75f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, 0.75f * ((float) j)), new PointF(0.75f * ((float) i), ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.75f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj9));
        ArrayList obj10 = new ArrayList();
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj10.add(new PointF[]{new PointF(0.25f * ((float) i), 0.25f * ((float) j)), new PointF(0.25f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.25f * ((float) j))});
        CollageLayout obj11 = new CollageLayout(obj10);
        obj11.maskPairList.add(new MaskPair(4, R.drawable.mask_circle));
        obj11.setClearIndex(4);
        this.collageLayoutList.add(obj11);
        ArrayList obj12 = new ArrayList();
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj12.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j))});
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j))});
        obj12.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj12));
        ArrayList obj13 = new ArrayList();
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j))});
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j))});
        obj13.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj13));
        ArrayList obj14 = new ArrayList();
        obj14.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj14.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj14.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j))});
        obj14.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj14.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj14));
        ArrayList obj15 = new ArrayList();
        obj15.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j))});
        obj15.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj15.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j))});
        obj15.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj15.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj15));
        ArrayList obj16 = new ArrayList();
        obj16.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 1.0f), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j))});
        obj16.add(new PointF[]{new PointF(0.5833333f * ((float) i), ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(0.5833333f * ((float) i), 0.6666667f * ((float) j))});
        obj16.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.5833333f * ((float) i), 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j))});
        obj16.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.4166667f * ((float) i), ((float) j) * 0.0f), new PointF(0.4166667f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j))});
        obj16.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.4166667f * ((float) i), ((float) j) * 0.0f), new PointF(0.4166667f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj16));
        ArrayList obj17 = new ArrayList();
        obj17.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj17.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        obj17.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f)});
        obj17.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj17.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj17));
        ArrayList obj18 = new ArrayList();
        obj18.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, 0.4166667f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.4166667f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj18.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), 0.4166667f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.4166667f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj18.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), 0.4166667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.4166667f * ((float) j))});
        obj18.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), 0.4166667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.4166667f * ((float) j))});
        obj18.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.4166667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.4166667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj18));
        ArrayList obj19 = new ArrayList();
        obj19.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 1.0f)});
        obj19.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), 0.25f * ((float) j)), new PointF(((float) i) * 1.0f, 0.25f * ((float) j))});
        obj19.add(new PointF[]{new PointF(0.5833333f * ((float) i), 0.25f * ((float) j)), new PointF(0.5833333f * ((float) i), ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, 0.25f * ((float) j))});
        obj19.add(new PointF[]{new PointF(0.5833333f * ((float) i), ((float) j) * 0.5f), new PointF(0.5833333f * ((float) i), 0.75f * ((float) j)), new PointF(((float) i) * 1.0f, 0.75f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f)});
        obj19.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.75f * ((float) j)), new PointF(0.5833333f * ((float) i), 0.75f * ((float) j)), new PointF(0.5833333f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj19));
        ArrayList obj20 = new ArrayList();
        obj20.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j))});
        obj20.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj20.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj20.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j))});
        obj20.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj20));
        ArrayList obj21 = new ArrayList();
        obj21.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj21.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj21.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j))});
        obj21.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj21.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj21));
        ArrayList obj22 = new ArrayList();
        obj22.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(0.4166667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.4166667f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j))});
        obj22.add(new PointF[]{new PointF(0.4166667f * ((float) i), 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(0.4166667f * ((float) i), 0.3333333f * ((float) j))});
        obj22.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj22.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j))});
        obj22.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj22));
        ArrayList obj23 = new ArrayList();
        obj23.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j))});
        obj23.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), ((float) j) * 0.0f), new PointF(0.5833333f * ((float) i), 0.3333333f * ((float) j))});
        obj23.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(0.4166667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.4166667f * ((float) i), ((float) j) * 1.0f)});
        obj23.add(new PointF[]{new PointF(0.4166667f * ((float) i), 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.4166667f * ((float) i), ((float) j) * 1.0f)});
        obj23.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj23));
        ArrayList obj24 = new ArrayList();
        obj24.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj24.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj24.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j))});
        obj24.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj24.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj24));
    }
}