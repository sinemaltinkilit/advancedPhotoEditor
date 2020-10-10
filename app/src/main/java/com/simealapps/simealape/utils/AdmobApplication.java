package com.simealapps.simealape.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.simealapps.simealape.R;


public class AdmobApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }


    public AdView google_AdView;
    public InterstitialAd google_InterstitialAd;


    // Initialize the Mobile Ads SDK.

    public void initializeAdsSdk() {
        //Initialize the Google Mobile Ads SDK
        MobileAds.initialize(this, getResources().getString(R.string.ADMOB_APP_ID));
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
    }


    public void createGoogleBannerAd(final RelativeLayout bannerAdContainer) {
        google_AdView = new AdView(this);
        google_AdView.setAdSize(AdSize.SMART_BANNER);
        google_AdView.setAdUnitId(getResources().getString(R.string.banner_google));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        google_AdView.loadAd(adRequest);
        bannerAdContainer.addView(google_AdView);
        google_AdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                //  Log.e("AdmobApplication_google", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                //  Log.e("AdmobApplication_google", "onAdFailedToLoad");
                createGoogleBannerAd(bannerAdContainer);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                //  Log.e("AdmobApplication_google", "onAdOpened");
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                //  Log.e("AdmobApplication_google", "onAdClicked");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                //  Log.e("AdmobApplication_google", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //  Log.e("AdmobApplication_google", "onAdClosed");
            }
        });
    }


    public void createGooglerRctangleAd(RelativeLayout bannerAdContainer) {
        google_AdView = new AdView(this);
        google_AdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        google_AdView.setAdUnitId(getResources().getString(R.string.banner_google));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        google_AdView.loadAd(adRequest);
        bannerAdContainer.addView(google_AdView);
        google_AdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // Log.e("AdmobApplication_google","onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                // Log.e("AdmobApplication_google","onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e("AdmobApplication_google", "onAdOpened");
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                // Log.e("AdmobApplication_google","onAdClicked");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                // Log.e("AdmobApplication_google","onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Log.e("AdmobApplication_google","onAdClosed");
            }
        });
    }
/*
    public void createGoogleInterstitialAd() {
        google_InterstitialAd = new InterstitialAd(this);
        google_InterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_google));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("6E17361580F10AAC61C6E3AB16B8A443")
                .build();
        google_InterstitialAd.loadAd(adRequest);
        google_InterstitialAd.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("AdmobApplication2", "onAdLoaded()");
                if (google_InterstitialAd.isLoaded()) {
                    google_InterstitialAd.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //  Log.e("AdmobApplication2", "onAdFailedToLoad() with error code: " + errorCode);
            }

            @Override
            public void onAdClosed() {
                //  Log.e("AdmobApplication2", "onAdClosed");

            }

            @Override
            public void onAdOpened() {
                //  Log.e("AdmobApplication2", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                //  Log.e("AdmobApplication2", "onAdLeftApplication");
            }

            @Override
            public void onAdClicked() {
                //  Log.e("AdmobApplication2", "onAdClicked");
            }
        });
    }
*/

    public void onPause() {
        if (google_AdView != null) {
            google_AdView.pause();
        }
        if (google_InterstitialAd !=null){
            google_InterstitialAd.setAdListener(null);
        }


    }


    public void onResume() {

        if (google_AdView != null) {
            google_AdView.resume();
        }
    }


    public void onDestroy() {
        if (google_AdView != null) {
            google_AdView.destroy();
        }
        if (google_InterstitialAd !=null){
            google_InterstitialAd.setAdListener(null);
        }


    }

}

