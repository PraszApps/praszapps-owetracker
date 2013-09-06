package com.praszapps.owetracker.application;

import android.app.Application;
import android.content.Context;

/**
 * Need the context of the app, hence this class is used
 * @author Prasannajeet Pani
 *
 */
public class OweTrackerApplication extends Application {

	private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
	
}
