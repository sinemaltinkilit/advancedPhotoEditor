package com.simealapps.simealape.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

import com.simealapps.simealape.R;
import com.simealapps.simealape.utils.Parameter;

public class FullEffectFragment extends Fragment {
    private static final String TAG = FullEffectFragment.class.getName();
    Activity activity;
    FullBitmapReady bitmapReadyListener;
    Context context;
    Bitmap currentBitmap;
    EffectFragment effectFragment;
    View header;
    ImageView imageView;
    Bitmap sourceBitmap;

    public interface FullBitmapReady {
        void onBitmapReady(Bitmap bitmap, Parameter parameter);

        void onCancel();
    }

    public interface HideHeaderListener {
        void hide(boolean z);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        View fragmentView = inflater.inflate(R.layout.full_fragment_effect, container, false);
        this.imageView = (ImageView) fragmentView.findViewById(R.id.imageView1);
        this.header = fragmentView.findViewById(R.id.full_fragment_apply_filter_header);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("imageView is null ");
        if (this.imageView == null) {
            z = true;
        } else {
            z = false;
        }
        Log.e(str, stringBuilder.append(z).toString());
        addFragment();
        return fragmentView;
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.imageView.setImageBitmap(this.sourceBitmap);
    }

    public void setBitmapWithParameter(Bitmap btm, Parameter parameter) {
        this.sourceBitmap = btm;
        if (this.imageView != null) {
            this.imageView.setImageBitmap(btm);
        }
        if (this.effectFragment != null) {
            if (parameter == null || this.effectFragment.parameterGlobal == null || parameter.getId() != this.effectFragment.parameterGlobal.f2077id) {
                this.effectFragment.setBitmapAndResetBlur(this.sourceBitmap);
            } else {
                this.effectFragment.setBitmap(this.sourceBitmap);
            }
            if (parameter != null) {
                this.effectFragment.setParameters(parameter);
            }
        }
    }


    public void addFragment() {
        if (this.effectFragment == null) {
            this.effectFragment = (EffectFragment) getChildFragmentManager().findFragmentByTag("MY_FRAGMENT");
            if (this.effectFragment == null) {
                this.effectFragment = new EffectFragment();
                Log.e(TAG, "EffectFragment == null");
                this.effectFragment.setArguments(getArguments());
                getChildFragmentManager().beginTransaction().add(R.id.fragment_container, this.effectFragment, "MY_FRAGMENT").commit();
            }
            getChildFragmentManager().beginTransaction().show(this.effectFragment).commit();
            this.effectFragment.setBitmapReadyListener(new EffectFragment.BitmapReady() {
                public void onBitmapReady(Bitmap bitmap) {
                    FullEffectFragment.this.imageView.setImageBitmap(bitmap);
                    FullEffectFragment.this.currentBitmap = bitmap;
                }
            });
            this.effectFragment.setHideHeaderListener(new HideHeaderListener() {
                public void hide(boolean show) {
                    if (show) {
                        FullEffectFragment.this.header.setVisibility(View.VISIBLE);
                    } else {
                        FullEffectFragment.this.header.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    public void setFullBitmapReadyListener(FullBitmapReady listener) {
        this.bitmapReadyListener = listener;
    }

    public void myClickHandler(View view) {
        if (view.getId() == R.id.button_apply_filter) {
            if (this.currentBitmap == null) {
                this.effectFragment.resetParametersWithoutChange();
                this.bitmapReadyListener.onCancel();
                return;
            }
            Parameter parameter = new Parameter(this.effectFragment.parameterGlobal);
            this.effectFragment.resetParametersWithoutChange();
            this.bitmapReadyListener.onBitmapReady(this.currentBitmap, parameter);
        } else if (view.getId() == R.id.button_cancel_filter) {
            this.effectFragment.resetParametersWithoutChange();
            this.bitmapReadyListener.onCancel();
        } else {
            if (this.header == null) {
                this.header = getView().findViewById(R.id.full_fragment_apply_filter_header);
            }
            int id = view.getId();
            if (id == R.id.buttonCancel || id == R.id.buttonOk || id == R.id.buttonReset) {
                this.header.setVisibility(View.VISIBLE);
            } else {
                this.header.setVisibility(View.INVISIBLE);
            }
            this.effectFragment.myClickHandler(id);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }
}
