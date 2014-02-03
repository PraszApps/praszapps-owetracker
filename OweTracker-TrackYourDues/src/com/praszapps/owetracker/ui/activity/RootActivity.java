package com.praszapps.owetracker.ui.activity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.database.DatabaseHelper;
import com.praszapps.owetracker.util.Constants;
import com.praszapps.owetracker.util.Utils;
import com.praszapps.owetracker.util.Utils.DialogResponse;


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
	public static boolean showDialog = false;
	
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

	@Override
	public void onBackPressed() {		
		if (owetrackerPrefs.getBoolean(Constants.DONT_SHOW_AGAIN, false)) {
			//Utils.showToast(this, "Dont show again is true", Toast.LENGTH_SHORT);
			super.onBackPressed();
		} else {
			//Utils.showToast(this, "Dont show again is false", Toast.LENGTH_SHORT);
			long launch_count = owetrackerPrefs.getLong(Constants.LAUNCH_COUNT, 0)+1;
		     owetrackerPrefs.edit().putLong(Constants.LAUNCH_COUNT, launch_count).commit();
		     Long date_firstLaunch = owetrackerPrefs.getLong(Constants.DATE_FIRST_LAUNCH, 0);
		     if (date_firstLaunch == 0) {
		            date_firstLaunch = System.currentTimeMillis();
		            owetrackerPrefs.edit().putLong(Constants.DATE_FIRST_LAUNCH, date_firstLaunch).commit();
		     }
		     
		  // Wait at least n days before opening
		  //Utils.showToast(RootActivity.this, "launch count is --- "+launch_count, Toast.LENGTH_SHORT);
		  if (launch_count >= Constants.LAUNCHES_UNTIL_PROMPT) {
			  //Utils.showToast(this, "It has number of launches needed to prompt", Toast.LENGTH_SHORT);
			  ///Utils.showToast(RootActivity.this, "Difference --- "+(date_firstLaunch - System.currentTimeMillis()), Toast.LENGTH_SHORT);
			  if (System.currentTimeMillis() >= date_firstLaunch + 
					  (Constants.DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
				  Utils.showToast(this, "It will show prompt", Toast.LENGTH_SHORT, getLayoutInflater());
				  Utils.showAlertDialog(RootActivity.this, getResources().getString(R.string.rate_dialog_title),
						  getResources().getString(R.string.rate_dialog_msg), null, false, 
						  getResources().getString(R.string.rate_dialog_now), 
						  getResources().getString(R.string.rate_dialog_never), 
						  getResources().getString(R.string.rate_dialog_later), new DialogResponse() {
					@Override
					public void onPositive() {
						Utils.goToGooglePlayPage(RootActivity.this);
						owetrackerPrefs.edit().putBoolean(Constants.DONT_SHOW_AGAIN, true).commit();
					}
					
					@Override
					public void onNeutral() {
						owetrackerPrefs.edit().putLong(Constants.LAUNCH_COUNT, 0).commit();
						owetrackerPrefs.edit().putLong(Constants.DATE_FIRST_LAUNCH, 
								System.currentTimeMillis()).commit();
					}
					
					@Override
					public void onNegative() {
						owetrackerPrefs.edit().putBoolean(Constants.DONT_SHOW_AGAIN, true).commit();
					}
					
					
				});
			  } else {
				  //Utils.showToast(this, "It still wont show prompt", Toast.LENGTH_SHORT);
				  super.onBackPressed();
			  }
		  } else {
			  //Utils.showToast(this, "It wont show prompt", Toast.LENGTH_SHORT);
			  super.onBackPressed();
		  }
		}				
	
		
		
	}
	
	

}
