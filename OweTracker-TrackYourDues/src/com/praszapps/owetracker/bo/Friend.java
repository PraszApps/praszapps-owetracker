package com.praszapps.owetracker.bo;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.application.OweTrackerApplication;


/**
 * Class for the friend type of object
 * @author Prasannajeet Pani
 *
 */
public class Friend {

	String id;
	String name;
	int amount;
	int oweAmount;
	String summary;
	String currency;
	//String totalRecords;
	String lastUpdated;

	public Friend(String id, String name, int oweAmount, 
			String currency, String lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.oweAmount = oweAmount;
		this.currency = currency;
		//this.totalRecords = totalRecords;
		this.lastUpdated = lastUpdated;
	}
	
	public Friend() {
		// Default Constructor
		this.currency = "0";
		this.summary = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOweAmount() {
		return oweAmount;
	}

	public void setOweAmount(int oweAmount) {
		this.oweAmount = oweAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(int oweAmount) {
		if(oweAmount < 0) {
			this.summary = OweTrackerApplication.getContext().getResources().getString(R.string.owes_me)+" "+currency+Math.abs(oweAmount);
		} else if (oweAmount > 0) {
			this.summary = OweTrackerApplication.getContext().getResources().getString(R.string.i_owe)+" "+currency+Math.abs(oweAmount);
		} else {
			this.summary = OweTrackerApplication.getContext().getResources().getString(R.string.no_dues);
		}
		
	}
	
	/*public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}*/

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return getName()+": "+getSummary();
	}
	
}
