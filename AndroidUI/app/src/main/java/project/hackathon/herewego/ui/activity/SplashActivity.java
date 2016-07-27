package project.hackathon.herewego.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.HWGSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        HWGSharedPreferences.init(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        final Intent mainIntent;
        if(currentAccessToken == null) {
            mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            /* Create an Intent that will start the Menu-Activity. */

                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
                if(currentAccessToken == null)
                    overridePendingTransition(0,0);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
