package com.praszapps.owetracker.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
		ImageView status = (ImageView) listItem
				.findViewById(R.id.imageViewStatus);

		// Set data for each row
		Friend friend = friendData.get(position);
		friend.setSummary(friend.getOweAmount());
		if (friend.getOweAmount() <= 0) {
			status.setImageResource(R.drawable.greensquare);
		} else {
			status.setImageResource(R.drawable.redsquare);
		}
		textViewFriendName.setText(friend.getName());
		textViewOweSummary.setText(friend.toString());
		//Utils.showLog(getClass().getSimpleName(), "getView() ends", Log.VERBOSE);
		return listItem;
	}

}
