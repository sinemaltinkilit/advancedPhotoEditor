package com.simealapps.simealape.canvastextview;

import android.content.ClipData.Item;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simealapps.simealape.R;

public class WriteTextFontAdapter extends ArrayAdapter<Item> {
    public Context context;
    public String[] fontPathList;
    public int layoutResourceId;
    public Typeface[] typeFaceArray;

    public WriteTextFontAdapter(Context context2, int layoutResourceId2, String[] data) {
        super(context2, layoutResourceId2);
        this.layoutResourceId = layoutResourceId2;
        this.context = context2;
        this.fontPathList = data;
        int length = this.fontPathList.length;
        this.typeFaceArray = new Typeface[length];
        for (int i = 0; i < length; i++) {
          //  this.typeFaceArray[i] = FontCache.get(context2, this.fontPathList[i]);
            this.typeFaceArray[i] = Typeface.createFromAsset(context2.getAssets(), this.fontPathList[i]);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.row_grid, parent, false);
        }
        ((TextView) row.findViewById(R.id.item_text)).setTypeface(this.typeFaceArray[position]);
        return row;
    }

    public int getCount() {
        return this.fontPathList.length;
    }
}
