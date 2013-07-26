package com.praszapps.owetracker.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.util.Constants;
import com.praszapps.owetracker.util.Utils;

public class SplashActivity extends RootActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Setting up the UI
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_splash);
		
		View v = findViewById(R.id.appiconsplash_tablet);	
		
		if(v != null) {
			//We are in tablet
			Utils.showLog(getClass().getSimpleName(), "Its running on tablet", Log.INFO);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			Utils.showLog(getClass().getSimpleName(), "Its running on phone", Log.INFO);
		}
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// Launch the login screen and kill the splash activity to avoid back navigation
            	startActivity(new Intent(SplashActivity.this, MainActivity.class));
        		finish();
			}
		}, Constants.SPLASH_SCREEN_TIMEOUT);
	}

	@Override
	public void onBackPressed() {
		return;
	}
	
	

}
