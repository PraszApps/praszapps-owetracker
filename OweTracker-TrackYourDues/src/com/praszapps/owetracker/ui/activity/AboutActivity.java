package com.praszapps.owetracker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.adapter.AboutSectionsPagerAdapter;
import com.praszapps.owetracker.util.Constants;
import com.praszapps.owetracker.util.Utils;

/**
 * 
 * This activity displays the About Screen of the application, it uses view pager between About and Contact tabs
 * @author Prasannajeet Pani
 * @version 1.0
 *
 */
public class AboutActivity extends RootActivity implements android.support.v7.app.ActionBar.TabListener {
	
	AboutSectionsPagerAdapter aboutAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Setting the layout
		setContentView(R.layout.activity_about);
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Adapter for the view pager that will be used for tabs
		aboutAdapter = new AboutSectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(aboutAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
							actionBar.setSelectedNavigationItem(position);
						
					}

				});

		for (int i = 0; i < aboutAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(aboutAdapter.getPageTitle(i)).setTabListener(this));
		}
		
		if(RootActivity.owetrackerPrefs.getBoolean(Constants.IS_OPENING_ABOUT_FIRST_TIME, true)) {
			Utils.showToast(this, getResources().getString(R.string.toast_msg_swipe), Toast.LENGTH_LONG);
			RootActivity.owetrackerPrefs.edit().putBoolean(Constants.IS_OPENING_ABOUT_FIRST_TIME, false).commit();
		}

	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.about_menu, menu);
		return true;
	}




	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			
		}
		
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
			
		case R.id.item_share:
			Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, 
            		getResources().getString(R.string.share_intent_text)+ " " +
            		"https://play.google.com/store/apps/details?id=com.praszapps.owetracker");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
            		getResources().getString(R.string.share_intent_subj));
            intent.putExtra(android.content.Intent.EXTRA_TITLE, 
            		getResources().getString(R.string.share_intent_subj));
            startActivity(Intent.createChooser(intent, "Share"));
			break;
			
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {}

}
