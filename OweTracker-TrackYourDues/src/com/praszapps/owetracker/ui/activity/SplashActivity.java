package com.praszapps.owetracker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.util.Constants;

public class SplashActivity extends RootActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Setting up the UI
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_splash);		
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
