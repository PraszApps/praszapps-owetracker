package com.praszapps.owetracker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.praszapps.owetracker.ui.fragment.AboutFragment;
import com.praszapps.owetracker.ui.fragment.ContactFragment;



public class AboutSectionsPagerAdapter extends FragmentPagerAdapter {

        public AboutSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
        	switch(i) {
        	case 0:
        		return new AboutFragment();
        	case 1:
        		return new ContactFragment();
        	default:
        		return null;
        	}
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	
        	switch(position) {
        	case 0:
        		return "About";
        	case 1:
        		return "Contact";
        	default:
        		return "Error";
        	}
        }
    }