package com.seu.magicfilter.display;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.seu.magicfilter.filter.base.gpuimage.GPUImageFilter;
import com.seu.magicfilter.filter.helper.MagicFilterParam;
import com.seu.magicfilter.filter.helper.MagicFilterType;
import com.seu.magicfilter.utils.ICallBack;
import com.seu.magicfilter.utils.MagicSDK;
import com.seu.magicfilter.utils.OpenGLUtils;
import com.seu.magicfilter.utils.SaveTask;
import com.seu.magicfilter.utils.SaveTask.onPictureSaveListener;
import com.seu.magicfilter.utils.TextureRotationUtil;

import java.io.File;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MagicImageDisplay extends MagicDisplay {
	
	private final GPUImageFilter mImageInput;
   
    private final MagicSDK mMagicSDK;
    
    private Bitmap mOriginBitmap,bitmapa;
    
    private boolean mIsSaving = false;
    
    @SuppressLint("HandlerLeak")
	private class MagicSDKHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MagicSDK.MESSAGE_OPERATION_END:
				refreshDisplay();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
    	
    }
    
    public MagicImageDisplay(Context context, GLSurfaceView glSurfaceView){
    	super(context, glSurfaceView);
    	mImageInput = new GPUImageFilter();
    	mMagicSDK = MagicSDK.getInstance();
		mMagicSDK.setMagicSDKHandler(new MagicSDKHandler());
    }      
    
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES20.glDisable(GL10.GL_DITHER);
        GLES20.glClearColor(0,0,0,0);
        GLES20.glEnable(GL10.GL_CULL_FACE);
        GLES20.glEnable(GL10.GL_DEPTH_TEST);	
        MagicFilterParam.initMagicFilterParam(gl);
        mImageInput.init();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
		mSurfaceWidth = width;
		mSurfaceHeight = height;
		adjustImageDisplaySize();
		onFilterChanged();
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		if(mTextureId == OpenGLUtils.NO_TEXTURE)
			mTextureId = OpenGLUtils.loadTexture(mMagicSDK.getBitmap(), OpenGLUtils.NO_TEXTURE);
		if(mFilters == null){
			mImageInput.onDrawFrame(mTextureId, mGLCubeBuffer, mGLTextureBuffer);
		}else{
			mFilters.onDrawFrame(mTextureId, mGLCubeBuffer, mGLTextureBuffer);
		}
	}
	
	public void setImageBitmap(Bitmap bitmap) {
		if (bitmap == null || bitmap.isRecycled())
			return;
		mImageWidth = bitmap.getWidth();
		mImageHeight = bitmap.getHeight();
		mOriginBitmap = bitmap;

		adjustImageDisplaySize();
		mMagicSDK.storeBitmap(mOriginBitmap, false);
		refreshDisplay();
	}
	
	private void refreshDisplay(){
		deleteTextures();
		mGLSurfaceView.requestRender();
	}
	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
	}
	
	public void onDestroy(){
		super.onDestroy();
		if(mMagicSDK != null)
			mMagicSDK.onDestroy();	
	}
	
	private void adjustImageDisplaySize() {
		float ratio1 = (float)mSurfaceWidth / mImageWidth;
        float ratio2 = (float)mSurfaceHeight / mImageHeight;
        float ratioMax = Math.max(ratio1, ratio2);
        int imageWidthNew = Math.round(mImageWidth * ratioMax);
        int imageHeightNew = Math.round(mImageHeight * ratioMax);

        float ratioWidth = imageWidthNew / (float)mSurfaceWidth;
        float ratioHeight = imageHeightNew / (float)mSurfaceHeight;

        float[] cube = new float[]{
        		TextureRotationUtil.CUBE[0] / ratioHeight, TextureRotationUtil.CUBE[1] / ratioWidth,
        		TextureRotationUtil.CUBE[2] / ratioHeight, TextureRotationUtil.CUBE[3] / ratioWidth,
        		TextureRotationUtil.CUBE[4] / ratioHeight, TextureRotationUtil.CUBE[5] / ratioWidth,
        		TextureRotationUtil.CUBE[6] / ratioHeight, TextureRotationUtil.CUBE[7] / ratioWidth,
        };
        mGLCubeBuffer.clear();
        mGLCubeBuffer.put(cube).position(0);
    }
	
	protected void onGetBitmapFromGL(Bitmap bitmap){
		try {
			mOriginBitmap = bitmap;
			if (mIsSaving) {
				mSaveTask.execute(mOriginBitmap);
				mIsSaving = false;
			} else {
				mMagicSDK.storeBitmap(mOriginBitmap, false);
			}
		}
		catch (Exception ex)
		{
			Toast.makeText(mContext, "Exception on OnGetBitmap"+ex, Toast.LENGTH_SHORT).show();
		}
	}


	public void savaImage(Bitmap output, onPictureSaveListener listener){
		mSaveTask = new SaveTask(mContext, output, listener);
		mIsSaving = true;
		try
		{
			if(mFilters != null) {
				Log.e("Filter"," :if");
				getBitmapFromGL(mOriginBitmap, true);
				return;
			}
			else

				Log.e("Filter"," :else");
			onGetBitmapFromGL(mOriginBitmap);

		}
		catch (Exception ex)
		{
			Toast.makeText(mContext, "Ex"+ex, Toast.LENGTH_SHORT).show();

		}
	}

	public void getBitmap1(final ICallBack iCallBack){
		mGLSurfaceView.queueEvent(new Runnable() {
			@Override
			public void run() {
				Bitmap result=mOriginBitmap.copy(mOriginBitmap.getConfig(),true);
				Bitmap photo=result;
				if(mFilters!=null){
					//photo=getBitmapFromGL(photo,true);
				}
				if(photo!=null){
					iCallBack.onComplete(photo);
				}
			}
		});

	}


}