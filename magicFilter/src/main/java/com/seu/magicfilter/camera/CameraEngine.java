package com.seu.magicfilter.camera;

import java.io.IOException;
import java.security.Policy;
import java.util.List;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;

public class CameraEngine {

	public static int count=0;
	public static Camera mCamera = null;
	public static int mCameraID = 1;
	
	public static Camera getCamera(){
		return mCamera;
	}
	public static boolean openCamera(int mCameraID){
		if(mCamera == null){
			try{
				mCamera = Camera.open(mCameraID);
				setDefaultParameters();
				return true;
			}catch(RuntimeException e){
				return false;
			}
		}
		return false;
	}

	public static void flipCamera(){
		releaseCamera();
		mCameraID = mCameraID == 0 ? 1 : 0;
		openCamera(mCameraID);
	}
	public static void releaseCamera(){
		if(mCamera != null){
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}
	
	public void resumeCamera(){
		openCamera(mCameraID);
	}
	
	public void setParameters(Parameters parameters){
		mCamera.setParameters(parameters);
	}
	
	public Parameters getParameters(){
		if(mCamera != null)
			mCamera.getParameters();
		return null;
	}
	
	private static void setDefaultParameters(){
		Parameters parameters = mCamera.getParameters();
		if (parameters.getSupportedFocusModes().contains(
                Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }

		Size previewSize = getLargePreviewSize();
		parameters.setPreviewSize(previewSize.width,previewSize.height);
		Size pictureSize = getLargePreviewSize();//getLargePictureSize();
		parameters.setPictureSize(pictureSize.width, pictureSize.height);
		mCamera.setParameters(parameters);
	}
	
	public static Size getLargePreviewSize(){
		if(mCamera != null){
			List<Size> sizes = mCamera.getParameters().getSupportedPreviewSizes();

			Size temp = sizes.get(0);
			for(int i=0;i<sizes.size();i++)
			{
//				Log.e("Preview Size = "+ i," : "+sizes.get(i).width+" , "+sizes.get(i).height+" , "+(float)sizes.get(i).width/(float)sizes.get(i).height+" , "+(float)16/(float)9);
			}
			for(int i=0;i<sizes.size();i++)
			{
				Log.e("Preview Size = "+ i," : "+sizes.get(i).width+" , "+sizes.get(i).height+" , "+(float)sizes.get(i).width/(float)sizes.get(i).height+" , "+(float)16/(float)9);
				if((float)(sizes.get(i).width)/(float)(sizes.get(i).height)==(float)16/(float)9)
				{
					temp=sizes.get(i);
					Log.e("Preview Size ","if");
					break;
				}
				else
				{
					Log.e("Preview Size ","else");
				}

			}
			return temp;
		}
		return null;
	}
	
	public static Size getLargePictureSize(){
		if(mCamera != null){
			List<Size> sizes = mCamera.getParameters().getSupportedPictureSizes();
			Size temp = sizes.get(0);

			for(int i=0;i<sizes.size();i++)
			{
				if((float)(sizes.get(i).width)/(float)(sizes.get(i).height)==(float)16/(float)9)
				{
					temp=sizes.get(i);
					Log.e("Preview Size ","if");
					break;
				}
				else
				{
					Log.e("Picture Size ","else");
				}
			}
			return temp;
		}
		return null;
	}
	
	public static Size getPreviewSize(){
			return mCamera.getParameters().getPreviewSize();
	}
	
	public static void startPreview(SurfaceTexture surfaceTexture){
		try {
			mCamera.setPreviewTexture(surfaceTexture);
			mCamera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void startPreview(){
		if(mCamera != null)
			mCamera.startPreview();
	}
	
	public static void stopPreview(){
		mCamera.stopPreview();
	}
	
	public static CameraInfo getCameraInfo(){
		CameraInfo cameraInfo = new CameraInfo();
		Camera.getCameraInfo(mCameraID, cameraInfo);
		return cameraInfo;
	}
	
	public static int getOrientation(){
		CameraInfo cameraInfo = new CameraInfo();
		Camera.getCameraInfo(mCameraID, cameraInfo);
		return cameraInfo.orientation;
	}
	
	public static boolean isFlipHorizontal(){
		return CameraEngine.getCameraInfo().facing == CameraInfo.CAMERA_FACING_FRONT ? true : false;
	}
	
	public static void setRotation(int rotation){
		Parameters params = mCamera.getParameters();
        params.setRotation(rotation);
        mCamera.setParameters(params);
	}
	public static void startFlash(int i) {

		if (mCamera != null) {

			final Parameters params = mCamera.getParameters();

			List<String> flashModes = params.getSupportedFlashModes();

			if (flashModes == null) {
				return;
			} else {

			}
			if (i == 0) {
				params.setFlashMode(Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(params);
				mCamera.startPreview();
			}

			if (i == 1) {

				try {
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					mCamera.setParameters(params);
					mCamera.startPreview();
				}
				catch (Exception e){}
			}
			if (i == 2) {

				try {
					params.setFlashMode(Parameters.FLASH_MODE_AUTO);
					mCamera.setParameters(params);
					mCamera.startPreview();
				}
				catch (Exception e){}
			}
		}
	}


	/*	public static void startFlash(int i)
        {

            if (mCamera != null) {

                final Parameters params = mCamera.getParameters();

                List<String> flashModes = params.getSupportedFlashModes();

                if (flashModes == null) {
                    return;
                } else {

                }
                if (i == 0) {
                    params.setFlashMode(Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(params);
                    mCamera.startPreview();
                }

                String flashMode = params.getFlashMode();

                if (!Parameters.FLASH_MODE_TORCH.equals(flashMode)) {

                    if (flashModes.contains(Parameters.FLASH_MODE_TORCH)) {
                        params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                        mCamera.setParameters(params);
                    } else {
                        // Toast.makeText(this,
                        // "Flash mode (torch) not supported",Toast.LENGTH_LONG).show();

                        params.setFlashMode(Parameters.FLASH_MODE_ON);

                        mCamera.setParameters(params);
                        try {
                            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                                public void onAutoFocus(boolean success, Camera camera) {
                                    count = 1;
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    */
	public static void takePicture(Camera.ShutterCallback shutterCallback, Camera.PictureCallback rawCallback, 
			Camera.PictureCallback jpegCallback){
		mCamera.takePicture(shutterCallback, rawCallback, jpegCallback);
	}
}
