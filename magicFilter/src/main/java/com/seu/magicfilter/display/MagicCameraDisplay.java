package com.seu.magicfilter.display;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.seu.magicfilter.camera.CameraEngine;
import com.seu.magicfilter.filter.base.MagicCameraInputFilter;
import com.seu.magicfilter.filter.helper.MagicFilterParam;
import com.seu.magicfilter.utils.OpenGLUtils;
import com.seu.magicfilter.utils.Rotation;
import com.seu.magicfilter.utils.SaveTask;
import com.seu.magicfilter.utils.SaveTask.onPictureSaveListener;
import com.seu.magicfilter.utils.TextureRotationUtil;

import java.io.File;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.seu.magicfilter.camera.CameraEngine.mCamera;

/**
 * MagicCameraDisplay is used for camera preview
 */
public class MagicCameraDisplay extends MagicDisplay {
    private static final int s = 1000;
    private final MagicCameraInputFilter mCameraInputFilter;
int height, width;

    private SurfaceTexture mSurfaceTexture;

    public MagicCameraDisplay(Context context, GLSurfaceView glSurfaceView) {
        super(context, glSurfaceView);
        mCameraInputFilter = new MagicCameraInputFilter();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glDisable(GL10.GL_DITHER);
        GLES20.glClearColor(0, 0, 0, 0);
        GLES20.glEnable(GL10.GL_CULL_FACE);
        GLES20.glEnable(GL10.GL_DEPTH_TEST);
        MagicFilterParam.initMagicFilterParam(gl);
        mCameraInputFilter.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        onFilterChanged();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mSurfaceTexture.updateTexImage();
        float[] mtx = new float[16];
        mSurfaceTexture.getTransformMatrix(mtx);
        mCameraInputFilter.setTextureTransformMatrix(mtx);
        if (mFilters == null) {
            mCameraInputFilter.onDrawFrame(mTextureId, mGLCubeBuffer, mGLTextureBuffer);
        } else {
            int textureID = mCameraInputFilter.onDrawToTexture(mTextureId);
            mFilters.onDrawFrame(textureID, mGLCubeBuffer, mGLTextureBuffer);
        }
    }

    private OnFrameAvailableListener mOnFrameAvailableListener = new OnFrameAvailableListener() {

        @Override
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            // TODO Auto-generated method stub
            mGLSurfaceView.requestRender();
        }
    };

    private void setUpCamera() {
        mGLSurfaceView.queueEvent(new Runnable() {

            @Override
            public void run() {
                if (mTextureId == OpenGLUtils.NO_TEXTURE) {
                    mTextureId = OpenGLUtils.getExternalOESTextureID();
                    mSurfaceTexture = new SurfaceTexture(mTextureId);
                    mSurfaceTexture.setOnFrameAvailableListener(mOnFrameAvailableListener);
                }
                Size size = CameraEngine.getPreviewSize();

                int orientation = CameraEngine.getOrientation();
                if (orientation == 90 || orientation == 270) {
                    mImageWidth = size.height;
                    mImageHeight = size.width;
                } else {
                    mImageWidth = size.width;
                    mImageHeight = size.height;
                }

               CameraEngine.setRotation(orientation);
                mCameraInputFilter.onOutputSizeChanged(mImageWidth, mImageHeight);
                CameraEngine.startPreview(mSurfaceTexture);
            }
        });
    }

    public void flipcamera() {

        CameraEngine.flipCamera();
        if (CameraEngine.getCamera() != null) {
            boolean flipHorizontal = CameraEngine.isFlipHorizontal();
            adjustPosition(CameraEngine.getOrientation(), flipHorizontal, !flipHorizontal);
        }
        setUpCamera();
    }

    public static void startFlash(int i)
    {

        if (mCamera != null) {

            final Camera.Parameters params = mCamera.getParameters();

            List<String> flashModes = params.getSupportedFlashModes();

            if (flashModes == null) {
                return;
            } else {

            }
            if (i == 0) {
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(params);
                mCamera.startPreview();
            }

            if (i == 1) {

                try {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    mCamera.setParameters(params);
                    mCamera.startPreview();
                }
                catch (Exception e){}
            }
            if (i == 2) {

                try {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                    mCamera.setParameters(params);
                    mCamera.startPreview();
                }
                catch (Exception e){}
            }
        }
    }

    public void flash_light(int i)
    {
        CameraEngine.startFlash(i);
        setUpCamera();
    }

    protected void onFilterChanged() {
        super.onFilterChanged();
        mCameraInputFilter.onDisplaySizeChanged(mSurfaceWidth, mSurfaceHeight);
        if (mFilters != null)
            mCameraInputFilter.initCameraFrameBuffer(mImageWidth, mImageHeight);
        else
            mCameraInputFilter.destroyFramebuffers();
    }

    public void refresh()
    {
        setUpCamera();
    }

    public void onResume() {

        super.onResume();
        if (CameraEngine.getCamera() == null)
            CameraEngine.openCamera(CameraEngine.mCameraID);
        if (CameraEngine.getCamera() != null) {
            boolean flipHorizontal = CameraEngine.isFlipHorizontal();
            adjustPosition(CameraEngine.getOrientation(), flipHorizontal, !flipHorizontal);
        }
        setUpCamera();
    }
    public void onPause() {
        super.onPause();
        CameraEngine.releaseCamera();
    }
    public void onDestroy() {
        super.onDestroy();
    }
    public void onTakePicture(Bitmap bmp, onPictureSaveListener listener, ShutterCallback shutterCallback) {
        //CameraEngine.setRotation(CameraEngine.getOrientation());
        Log.d("Rotation", "" + CameraEngine.getOrientation());
        mSaveTask = new SaveTask(mContext, bmp, listener);

        CameraEngine.takePicture(shutterCallback, null, mPictureCallback);

    }

    private PictureCallback mPictureCallback = new PictureCallback()
    {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            int width1 = bitmap.getWidth();
            int height1 = bitmap.getHeight();
            Log.e("NATIVE BYTE ","H: "+bitmap.getHeight()+"W: "+bitmap.getWidth());

            if (width1 > height1) {
                if (CameraEngine.getCameraInfo().facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    Matrix matrix = new Matrix();
                    matrix.preRotate(-90);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,  bitmap.getWidth(),bitmap.getHeight(), matrix, true);
                } else {
                    Matrix matrix = new Matrix();
                    matrix.preRotate(90);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                }
            }


            if (mFilters != null) {
                getBitmapFromGL(bitmap, true);
            } else {
                mSaveTask.execute(bitmap);
            }

        }
    };

    protected void onGetBitmapFromGL(Bitmap bitmap) {
        mSaveTask.execute(bitmap);
    }
    private void adjustPosition(int orientation, boolean flipHorizontal, boolean flipVertical) {
        Rotation mRotation = Rotation.fromInt(orientation);
        float[] textureCords = TextureRotationUtil.getRotation(mRotation, flipHorizontal, flipVertical);
        mGLTextureBuffer.clear();
        mGLTextureBuffer.put(textureCords).position(0);
    }
    public Bitmap getExactSizeBitmap(Bitmap bitmap, int width, int height) {

        float ratio = (float) bitmap.getWidth() / (float) bitmap.getHeight();
        if (bitmap.getHeight() > bitmap.getWidth()) {
            width = (int) ((float) height * ratio);
        } else {
            height = (int) ((float) width / ratio);
        }
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }


}
