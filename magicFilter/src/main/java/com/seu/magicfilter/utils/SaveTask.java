package com.seu.magicfilter.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;

public class SaveTask extends AsyncTask<Bitmap, Integer, Bitmap>{

	private onPictureSaveListener mListener;
	private Context mContext;
	private Bitmap mFile;
	private Boolean front_camera;

	public SaveTask(Context context, Bitmap bmp, onPictureSaveListener listener){
		this.mContext = context;
		this.mListener = listener;
		this.mFile = bmp;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	protected void onPostExecute(final Bitmap result) {
		Log.e("STATUS","POST EXECUTION IN SAVETASK");
		mListener.onSaved(mFile);

	}
	@Override
	protected Bitmap doInBackground(Bitmap... params) {

		mFile = params[0];
		return mFile;
	}

	public interface onPictureSaveListener{
		void onSaved(Bitmap bitmap);
	}

}
