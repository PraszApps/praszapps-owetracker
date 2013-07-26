package com.praszapps.owertracker.ui.activity;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.ui.fragment.FriendDueFragment;
import com.praszapps.owetracker.ui.fragment.OweboardFragment;
import com.praszapps.owetracker.ui.fragment.OweboardFragment.OnFriendNameClickListener;
import com.praszapps.owetracker.util.Utils;

public class MainActivity extends RootActivity implements OnFriendNameClickListener{

	public static boolean isSinglePane;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View v = findViewById(R.id.fragment_container);
		
		if(v == null) {
			//Running on tablet
			Utils.showLog(getClass().getSimpleName(), "Its running on tablet", Log.INFO);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			isSinglePane = false;
		} else {
			//Its running on phone
			Utils.showLog(getClass().getSimpleName(), "Its running on phone", Log.INFO);
			isSinglePane = true;
			if (savedInstanceState == null) {
				//Utils.showLog(getClass().getSimpleName(), "You can safely instantiate your fragment", Log.DEBUG);
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				OweboardFragment f1 = new OweboardFragment();
				ft.add(R.id.fragment_container, f1);
				ft.commit();
				//Utils.showLog(getClass().getSimpleName(), "Fragment instantiated safely", Log.DEBUG);

			} else {
				//Utils.showLog(getClass().getSimpleName(), "Activity recreated", Log.DEBUG);
			}
		}

		ActionBar aBar = getActionBar();
		aBar.setBackgroundDrawable(getResources().getDrawable(R.color.actionbar_bg));

	}
	
	

	@Override
	public void OnFriendNameClick(String friendId, String currency) {
		
		if(isSinglePane == true){
			/*
			 * The second fragment not yet loaded. 
			 * Load FriendDueFragment by FragmentTransaction, and pass 
			 * data from current fragment to second fragment via bundle.
			 */
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			FriendDueFragment fragTwo = new FriendDueFragment();
			Bundle args = new Bundle();
			args.putString("friendId", friendId);
			args.putString("currency", currency);
			fragTwo.setArguments(args);
			ft.addToBackStack(null);
			ft.replace(R.id.fragment_container, fragTwo);
			ft.commit();
			
		} else {
			/*
			 * Activity have two fragments. Pass data between fragments
			 * via reference to fragment
			 */
			//get reference to FriendDueFragment
			FriendDueFragment dueFrag = (FriendDueFragment)getSupportFragmentManager().findFragmentById(R.id.due_frag);
			dueFrag.showDetails(friendId, currency);
			
		}
	
	}

}
