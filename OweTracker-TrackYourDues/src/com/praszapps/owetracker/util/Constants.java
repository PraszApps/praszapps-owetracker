package com.praszapps.owetracker.util;


import com.praszapps.owetracker.R;
import com.praszapps.owetracker.application.OweTrackerApplication;

public class Constants {

	public static final String OWES_YOU = OweTrackerApplication.getContext().getResources().getString(R.string.owes_me);
	public static final String YOU_OWE = OweTrackerApplication.getContext().getResources().getString(R.string.i_owe);
	public static final String NO_DUES = OweTrackerApplication.getContext().getResources().getString(R.string.no_dues);
	public static final String BUNDLE_EXTRA_CURRENCY = "currency";
	public static final String BUNDLE_EXTRA_FRIENDID = "friendId";
	public static final String CURRENCY_RS = "Rs";
	public static final long SPLASH_SCREEN_TIMEOUT = 2000;
	public static final String MODE_ADD = "add";
	public static final String MODE_EDIT = "edit";
	public static final String GOOGLE_ANALYTICS_TRACKING_ID = "UA-42776545-1";
	public static final String SHARED_PREFERENCES_NAME = "owetracker_prefs";
	public static final String IS_FIRST_INSTALL = "first_install";
	public static final String DATE_FORMAT = "dd-MMM-yyyy";
}
