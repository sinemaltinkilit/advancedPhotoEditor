package com.simealapps.simealape.collagelist;

import android.graphics.PointF;

import java.util.ArrayList;

public class CollageScrapBook extends Collage {
    public CollageScrapBook(int i, int j) {
        this.collageLayoutList = new ArrayList();
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        ArrayList obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        ArrayList obj3 = new ArrayList();
        obj3.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj3.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj3));
        ArrayList obj4 = new ArrayList();
        obj4.add(new PointF[]{new PointF(0.2f * ((float) i), 0.6f * ((float) j)), new PointF(0.8f * ((float) i), 0.6f * ((float) j)), new PointF(0.8f * ((float) i), ((float) j) * 0.0f), new PointF(0.2f * ((float) i), ((float) j) * 0.0f)});
        obj4.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, 0.4f * ((float) j)), new PointF(((float) i) * 0.0f, 0.4f * ((float) j))});
        obj4.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.4f * ((float) j)), new PointF(((float) i) * 0.5f, 0.4f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj4));
        ArrayList obj5 = new ArrayList();
        obj5.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj5.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj5.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj5.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj5));
        ArrayList obj6 = new ArrayList();
        obj6.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f)});
        obj6.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f)});
        obj6.add(new PointF[]{new PointF(0.25f * ((float) i), 0.25f * ((float) j)), new PointF(0.25f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.25f * ((float) j))});
        CollageLayout obj7 = new CollageLayout(obj6);
        obj7.setClearIndex(4);
        this.collageLayoutList.add(obj7);
        ArrayList obj8 = new ArrayList();
        obj8.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj8.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj8.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj8.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj8.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj8.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj8));
        ArrayList obj9 = new ArrayList();
        obj9.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj9.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj9.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj9.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj9.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        obj9.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj9));
        ArrayList obj10 = new ArrayList();
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, ((float) j) * 1.0f)});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.5f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.5f, 0.6666667f * ((float) j))});
        obj10.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj10.add(new PointF[]{new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, ((float) j) * 0.0f), new PointF(((float) i) * 0.5f, 0.3333333f * ((float) j))});
        obj10.add(new PointF[]{new PointF(0.25f * ((float) i), 0.25f * ((float) j)), new PointF(0.25f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.25f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj10));
        ArrayList obj11 = new ArrayList();
        obj11.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj11.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 0.5f), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj11.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj11.add(new PointF[]{new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.5f)});
        obj11.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        obj11.add(new PointF[]{new PointF(((float) i) * 1.0f, ((float) j) * 0.5f), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 0.5f)});
        obj11.add(new PointF[]{new PointF(0.25f * ((float) i), 0.25f * ((float) j)), new PointF(0.25f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.75f * ((float) j)), new PointF(0.75f * ((float) i), 0.25f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj11));
        ArrayList obj12 = new ArrayList();
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.4f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.4f * ((float) i), ((float) j) * 0.0f), new PointF(0.4f * ((float) i), 0.4f * ((float) j))});
        obj12.add(new PointF[]{new PointF(0.3f * ((float) i), 0.45f * ((float) j)), new PointF(0.7f * ((float) i), 0.45f * ((float) j)), new PointF(0.7f * ((float) i), ((float) j) * 0.0f), new PointF(0.3f * ((float) i), ((float) j) * 0.0f)});
        obj12.add(new PointF[]{new PointF(0.6f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.4f * ((float) j)), new PointF(0.6f * ((float) i), 0.4f * ((float) j))});
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.7f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3f * ((float) j)), new PointF(0.45f * ((float) i), 0.3f * ((float) j)), new PointF(0.45f * ((float) i), 0.7f * ((float) j))});
        obj12.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6f * ((float) j)), new PointF(0.4f * ((float) i), 0.6f * ((float) j)), new PointF(0.4f * ((float) i), ((float) j) * 1.0f)});
        obj12.add(new PointF[]{new PointF(0.3f * ((float) i), 0.55f * ((float) j)), new PointF(0.7f * ((float) i), 0.55f * ((float) j)), new PointF(0.7f * ((float) i), ((float) j) * 1.0f), new PointF(0.3f * ((float) i), ((float) j) * 1.0f)});
        obj12.add(new PointF[]{new PointF(0.6f * ((float) i), 0.6f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6f * ((float) i), ((float) j) * 1.0f)});
        obj12.add(new PointF[]{new PointF(0.55f * ((float) i), 0.7f * ((float) j)), new PointF(0.55f * ((float) i), 0.3f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3f * ((float) j)), new PointF(((float) i) * 1.0f, 0.7f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj12));
        ArrayList obj13 = new ArrayList();
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 0.0f, ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j))});
        obj13.add(new PointF[]{new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 0.0f)});
        obj13.add(new PointF[]{new PointF(0.6666667f * ((float) i), ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, ((float) j) * 0.0f), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j))});
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 0.0f, 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j))});
        obj13.add(new PointF[]{new PointF(((float) i) * 0.0f, ((float) j) * 1.0f), new PointF(((float) i) * 0.0f, 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f), new PointF(0.3333333f * ((float) i), ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j)), new PointF(((float) i) * 1.0f, ((float) j) * 1.0f), new PointF(0.6666667f * ((float) i), ((float) j) * 1.0f)});
        obj13.add(new PointF[]{new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.3333333f * ((float) j)), new PointF(((float) i) * 1.0f, 0.6666667f * ((float) j))});
        obj13.add(new PointF[]{new PointF(0.6666667f * ((float) i), 0.6666667f * ((float) j)), new PointF(0.6666667f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.3333333f * ((float) j)), new PointF(0.3333333f * ((float) i), 0.6666667f * ((float) j))});
        this.collageLayoutList.add(new CollageLayout(obj13));
    }
}
