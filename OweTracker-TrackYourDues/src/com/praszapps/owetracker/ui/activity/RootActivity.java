package com.praszapps.owetracker.ui.activity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.google.analytics.tracking.android.EasyTracker;
import com.praszapps.owetracker.R;
import com.praszapps.owetracker.database.DatabaseHelper;
import com.praszapps.owetracker.util.Constants;

public class RootActivity extends ActionBarActivity {

	protected SharedPreferences settings = null;	
	public  DatabaseHelper dbHelper;
	public SQLiteDatabase database;
	public static SharedPreferences owetrackerPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(this);
		database = dbHelper.getWritableDatabase();
		owetrackerPrefs = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		ActionBar aBar = getSupportActionBar();
		aBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar_bg));
	}

	@Override
	protected void onPause() {
		super.onPause();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!database.isOpen()) {
			database = dbHelper.getWritableDatabase();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(database.isOpen()) {
			database.close();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
	
	

}
