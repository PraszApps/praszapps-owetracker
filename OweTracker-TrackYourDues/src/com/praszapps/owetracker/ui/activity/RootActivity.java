package com.praszapps.owetracker.ui.activity;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.analytics.tracking.android.EasyTracker;
import com.praszapps.owetracker.R;
import com.praszapps.owetracker.database.DatabaseHelper;

public class RootActivity extends FragmentActivity {

	protected SharedPreferences settings = null;	
	public  DatabaseHelper dbHelper;
	public SQLiteDatabase database;
	public static int DEVICE_WIDTH_IN_PIXELS;
	public static int DEVICE_HEIGHT_IN_PIXELS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(this);
		database = dbHelper.getWritableDatabase();
		ActionBar aBar = getActionBar();
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