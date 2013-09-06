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

/**
 * The Adapter class of the Friend type object list view
 * @author Prasannajeet Pani
 *
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

	// Declaring variables
	LayoutInflater inflater;
	Context mContext;
	int layoutResourceId;
	ArrayList<Friend> friendData = null;

	
	/**
	 * Constructor of the FriendAdapter
	 * @param mContext - The context of the application
	 * @param layoutResourceId - The layout resource ID
	 * @param data - Arraylist of Friend type to be passed for processing
	 */
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
		
		// Show the appropriate indicator depending on the type of transaction done
		if (friend.getOweAmount() < 0) {
			status.setImageResource(R.drawable.bluesquare);
		} else if(friend.getOweAmount() > 0) {
			status.setImageResource(R.drawable.redsquare);
		} else if(friend.getOweAmount() == 0) {
			status.setImageResource(R.drawable.greensquare);
		}
		textViewFriendName.setText(friend.getName());
		textViewOweSummary.setText(friend.toString());
		return listItem;
	}

}
