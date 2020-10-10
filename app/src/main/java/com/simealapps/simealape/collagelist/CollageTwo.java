package com.simealapps.simealape.collagelist;

import android.graphics.PointF;

import java.util.ArrayList;

import com.simealapps.simealape.R;

public class CollageTwo extends Collage {
    public static int shapeCount = 2;

    public CollageTwo(int i, int j) {
        this.collageLayoutList = new ArrayList();
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.5f * ((float) i), ((float) j) * 1.0f), new PointF(0.5f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj.add(new PointF[]{new PointF(0.5f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.5f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        ArrayList obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.5f * ((float) j)), new PointF(((float) i) * 1.0f, 0.5f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.5f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.5f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj2));
        ArrayList obj3 = new ArrayList();
        obj3.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(0.6f * ((float) i), ((float) j) * 1.0f), new PointF(0.6f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj3.add(new PointF[]{new PointF(0.4f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.4f * ((float) i), ((float) j) * 1.0f)});
        CollageLayout obj4 = new CollageLayout(obj3);
        obj4.maskPairList.add(new MaskPair(0, R.drawable.mask_heart));
        obj4.maskPairList.add(new MaskPair(1, R.drawable.mask_heart));
        obj4.setClearIndex(1);
        this.collageLayoutList.add(obj4);
        ArrayList obj5 = new ArrayList();
        obj5.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj5.add(new PointF[]{new PointF(0.13f * ((float) i), 0.13f * ((float) j)), new PointF(0.13f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.13f * ((float) j))});
        CollageLayout obj6 = new CollageLayout(obj5);
        obj6.maskPairList.add(new MaskPair(1, R.drawable.mask_heart));
        obj6.setClearIndex(1);
        this.collageLayoutList.add(obj6);
        ArrayList obj7 = new ArrayList();
        obj7.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj7.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj7));
        ArrayList obj8 = new ArrayList();
        obj8.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj8.add(new PointF[]{new PointF(0.13f * ((float) i), 0.13f * ((float) j)), new PointF(0.13f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.13f * ((float) j))});
        CollageLayout obj9 = new CollageLayout(obj8);
        obj9.maskPairList.add(new MaskPair(1, R.drawable.mask_cloud));
        obj9.setClearIndex(1);
        this.collageLayoutList.add(obj9);
        ArrayList obj10 = new ArrayList();
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.5833f * ((float) j)), new PointF(0.1667f * ((float) i), 0.41667f * ((float) j)), new PointF(0.333f * ((float) i), 0.5833f * ((float) j)), new PointF(0.5f * ((float) i), 0.41667f * ((float) j)), new PointF(0.6667f * ((float) i), 0.5833f * ((float) j)), new PointF(0.8333f * ((float) i), 0.41667f * ((float) j)), new PointF((float) (i * 1), 0.5833f * ((float) j)), new PointF((float) (i * 1), (float) (j * 1))});
        obj10.add(new PointF[]{new PointF((float) (i * 0), 0.5833f * ((float) j)), new PointF((float) (i * 0), (float) (j * 0)), new PointF((float) (i * 1), (float) (j * 0)), new PointF((float) (i * 1), 0.5833f * ((float) j)), new PointF(0.8333f * ((float) i), 0.41667f * ((float) j)), new PointF(0.6667f * ((float) i), 0.5833f * ((float) j)), new PointF(0.5f * ((float) i), 0.41667f * ((float) j)), new PointF(0.333f * ((float) i), 0.5833f * ((float) j)), new PointF(0.1667f * ((float) i), 0.41667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj10));
        ArrayList obj11 = new ArrayList();
        obj11.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj11.add(new PointF[]{new PointF(0.13f * ((float) i), 0.13f * ((float) j)), new PointF(0.13f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.87f * ((float) j)), new PointF(0.87f * ((float) i), 0.13f * ((float) j))});
        CollageLayout obj12 = new CollageLayout(obj11);
        obj12.maskPairList.add(new MaskPair(1, R.drawable.mask_hexagon));
        obj12.setClearIndex(1);
        this.collageLayoutList.add(obj12);
        ArrayList obj13 = new ArrayList();
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.5f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.5f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj13));
        ArrayList obj14 = new ArrayList();
        obj14.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj14.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj14));
        ArrayList obj15 = new ArrayList();
        obj15.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj15.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj15));
        ArrayList obj16 = new ArrayList();
        obj16.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj16.add(new PointF[]{new PointF(0.6f * ((float) i), 0.6f * ((float) j)), new PointF(0.6f * ((float) i), 0.9333333f * ((float) j)), new PointF(0.9333333f * ((float) i), 0.9333333f * ((float) j)), new PointF(0.9333333f * ((float) i), 0.6f * ((float) j))});
        CollageLayout obj17 = new CollageLayout(obj16);
        obj17.setClearIndex(1);
        this.collageLayoutList.add(obj17);
        ArrayList obj18 = new ArrayList();
        obj18.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj18.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj18));
        ArrayList obj19 = new ArrayList();
        obj19.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj19.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj19));
        ArrayList obj20 = new ArrayList();
        obj20.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj20.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj20));
        ArrayList obj21 = new ArrayList();
        obj21.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj21.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj21));
    }
}
