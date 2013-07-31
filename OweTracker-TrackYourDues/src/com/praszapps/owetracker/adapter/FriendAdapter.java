package com.praszapps.owetracker.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.bo.Friend;

public class FriendAdapter extends ArrayAdapter<Friend> {

	// Declaring variables
	LayoutInflater inflater;
	Context mContext;
	int layoutResourceId;
	ArrayList<Friend> friendData = null;

	public FriendAdapter(Context mContext, int layoutResourceId, ArrayList<Friend> data) {

		// Setting constructor
		super(mContext, layoutResourceId, data);
		this.mContext = mContext;
		this.layoutResourceId = layoutResourceId;
		this.friendData = data;

	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View listItem = convertView;
		//Utils.showLog(getClass().getSimpleName(), "getView() starts",Log.VERBOSE);
		// inflate the oweboard_list_item.xml parent
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		listItem = inflater.inflate(layoutResourceId, parent, false);

		// get elements in the layout
		TextView textViewFriendName = (TextView) listItem
				.findViewById(R.id.textViewFriendName);
		TextView textViewOweSummary = (TextView) listItem
				.findViewById(R.id.textViewOweSummary);

		// Set data for each row
		Friend friend = friendData.get(position);
		friend.setSummary(friend.getOweAmount());
		if (friend.getOweAmount() <= 0) {
			listItem.setBackgroundResource(R.color.list_item_green_bg);
		} else {
			listItem.setBackgroundResource(R.color.list_item_red_bg);
		}
		textViewFriendName.setText(friend.getName());
		textViewOweSummary.setText(friend.toString());
		//Utils.showLog(getClass().getSimpleName(), "getView() ends", Log.VERBOSE);
		return listItem;
	}

}
