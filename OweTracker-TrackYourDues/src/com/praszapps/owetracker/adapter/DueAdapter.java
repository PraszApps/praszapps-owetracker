package com.praszapps.owetracker.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.bo.Due;
import com.praszapps.owetracker.util.Constants;

/**
 * This is the Adapter of the listview of the dues screen of the friend
 * @author Prasannajeet Pani
 *
 */
public class DueAdapter extends ArrayAdapter<Due> {

	//Declaring variables
	LayoutInflater inflater;
	Context mContext;
	int layoutResourceId;
	ArrayList<Due> dueData = null;
	String currency;
	String friendName;

	/**
	 * Constructor of DueAdapter
	 * @param mContext - The context where it is called
	 * @param layoutResourceId - The resource that is called
	 * @param data - The ArrayList of Due type that is to be passed
	 * @param currency - Display currency
	 * @param friendName - Name of the friend whose dues are displayed
	 */
	public DueAdapter(Context mContext, int layoutResourceId, ArrayList<Due> data, String currency, String friendName) {

		//Initializing views
		super(mContext, layoutResourceId, data);
		this.mContext = mContext;
		this.layoutResourceId = layoutResourceId;
		this.dueData = data;
		this.currency = currency;
		this.friendName = friendName;

	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	
	public void updateAdapter(ArrayList<Due> dueList) {
		// Updating adapter
	    this.dueData = dueList;
	    this.notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View listItem = convertView;
		// Inflating the oweboard_details_list_item.xml
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		listItem = inflater.inflate(layoutResourceId, parent, false);
		TextView textViewDate = (TextView) listItem.findViewById(R.id.textViewDate);
		TextView textViewAmtDetails = (TextView) listItem.findViewById(R.id.textViewAmtDetails);
		TextView textViewReason = (TextView) listItem.findViewById(R.id.textViewReason);
		RelativeLayout listParentLayout = (RelativeLayout) listItem
				.findViewById(R.id.layout_list_item_due);
		textViewDate.setText(dueData.get(position).getFormattedDate());
		String summary = null;
		
		// Setting the type of transaction that has been done
		if(dueData.get(position).getAmount() >=0 ) {
			summary = Constants.YOU_OWE+" "+currency+Math.abs(dueData.get(position).getAmount());
		} else {
			
			summary = Constants.OWES_YOU+" "+currency+Math.abs(dueData.get(position).getAmount());
		}
		
		textViewAmtDetails.setText(summary);
		textViewReason.setText(dueData.get(position).getReason());
		dueData.get(position).setCurrency(currency);
		
		// Showing the visual indicator for the type of due
		if (dueData.get(position).getAmount() <= 0) {
			listParentLayout.setBackgroundResource(R.drawable.card_bg_selector_blue);
		} else {
			listParentLayout.setBackgroundResource(R.drawable.card_bg_selector_red);
		}
		return listItem;
	}
	
}
