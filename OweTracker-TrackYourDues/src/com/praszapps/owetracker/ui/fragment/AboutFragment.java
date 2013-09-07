package com.praszapps.owetracker.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praszapps.owetracker.R;

/**
 * This fragment displayed the About text in the About tab of the About activity
 * @author Prasannajeet Pani
 *
 */
public class AboutFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		// Inflate the layout
		View v = inflater.inflate(R.layout.fragment_about, container, false);
		return v;
	}

}
