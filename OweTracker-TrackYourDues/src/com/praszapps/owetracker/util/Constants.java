package com.praszapps.owetracker.util;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.application.OweTrackerApplication;

public class Constants {

	public static final String OWES_YOU = OweTrackerApplication.getContext().getResources().getString(R.string.owes_me);
	public static final String YOU_OWE = OweTrackerApplication.getContext().getResources().getString(R.string.i_owe);
	public static final String NO_DUES = OweTrackerApplication.getContext().getResources().getString(R.string.no_dues);
	public static final String TOTAL_RECORDS = OweTrackerApplication.getContext().getResources().getString(R.string.total_records);
	public static final String LAST_UPDATE = OweTrackerApplication.getContext().getResources().getString(R.string.last_update);
	public static final String BUNDLE_EXTRA_CURRENCY = "currency";
	public static final String BUNDLE_EXTRA_FRIENDID = "friendId";
	public static final long SPLASH_SCREEN_TIMEOUT = 2000;
	public static final String MODE_ADD = "add";
	public static final String MODE_EDIT = "edit";
	public static final String SHARED_PREFERENCES_NAME = "owetracker_prefs";
	public static final String IS_ADDING_FIRST_DUE = "adding_first_due";
	public static final String IS_OPENING_ABOUT_FIRST_TIME = "opening_about_first_time";
	public static final String PREFS_SHOW_RATING_DIALOG = "rating_dialog";
	public static final String DATE_FORMAT = "dd-MMM-yyyy";
	public static final String EMAIL_ID_FEEDBACK = "developer.praszapps@gmail.com";
	public static final String EMAIL_SUBJ_FEEDBACK = "OweTracker - Feedback";
	public static final String PACKAGE_NAME = "com.praszapps.owetracker";
	public static final String LAUNCH_COUNT = "launch_count";
	public final static int DAYS_UNTIL_PROMPT = 3;
    public final static int LAUNCHES_UNTIL_PROMPT = 15;
    public static final String DONT_SHOW_AGAIN = "dontshowagain";
    public static final String DATE_FIRST_LAUNCH = "date_firstlaunch";
}
