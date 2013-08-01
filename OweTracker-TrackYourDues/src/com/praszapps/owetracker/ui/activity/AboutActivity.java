package com.praszapps.owetracker.ui.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.MenuItem;

import com.praszapps.owetracker.ui.fragment.FragmentsTabAbout;
import com.praszapps.owetracker.ui.fragment.FragmentsTabContact;

public class AboutActivity extends RootActivity {
	private Tab tab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create the actionbar
		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(true);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create first Tab
		tab = actionBar.newTab().setTabListener(new FragmentsTabAbout());
		// Create your own custom icon
		//tab.setIcon(R.drawable.tab1);
		tab.setText("About");
		actionBar.addTab(tab);

		// Create Second Tab
		tab = actionBar.newTab().setTabListener(new FragmentsTabContact());
		// Set Tab Title
		tab.setText("Tab2");
		tab.setText("Contact");
		actionBar.addTab(tab);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}	
	
	
}
