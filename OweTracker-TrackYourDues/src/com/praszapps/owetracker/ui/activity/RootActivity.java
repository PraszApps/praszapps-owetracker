package com.praszapps.owetracker.ui.activity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.database.DatabaseHelper;
import com.praszapps.owetracker.util.Constants;


/**
 * 
 * This is the root-most activity in the application which has instances
 * of databases and preferences for use across the applications
 * @author Prasannajeet Pani
 * @version 1.0
 *
 */
public class RootActivity extends ActionBarActivity {

	public  DatabaseHelper dbHelper;
	public SQLiteDatabase database;
	public static SharedPreferences owetrackerPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Create a new database instance
		dbHelper = new DatabaseHelper(this);
		database = dbHelper.getWritableDatabase();
		
		// Creating a SharedPreferences instance
		owetrackerPrefs = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		
		// Creating a ActionBar instance
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
	

}
