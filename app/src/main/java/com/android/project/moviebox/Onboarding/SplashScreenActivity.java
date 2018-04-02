package com.android.project.moviebox.Onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.project.moviebox.Constants.AppConstants;
import com.android.project.moviebox.R;
import com.android.project.moviebox.UI.MainActivity;

/**
 * Splash screen before we show the Movies list
 */
public class SplashScreenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, AppConstants.SPLASH_DISPLAY_INTERVAL);
    }
}
