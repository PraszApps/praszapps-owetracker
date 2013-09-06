package com.praszapps.owetracker.bo;

import com.praszapps.owetracker.util.Constants;

/**
 * Class for the friend type of object
 * @author Prasannajeet Pani
 *
 */
public class Friend {

	String id;
	String name;
	int oweAmount;
	String summary;
	String currency;

	// For Sample Data
	public Friend(String name, int oweAmount) {
		super();
		this.name = name;
		this.oweAmount = oweAmount;
	}

	public Friend(String id, String name, int oweAmount, 
			String currency) {
		super();
		this.id = id;
		this.name = name;
		this.oweAmount = oweAmount;
		this.currency = currency;
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
		this.summary = currency+Math.abs(oweAmount);
	}

	@Override
	public String toString() {
		
		String friendObjToString = null;
		if(oweAmount < 0) {
			friendObjToString = Constants.OWES_YOU+" "+currency+Math.abs(oweAmount);
		} else if(oweAmount > 0) {
			friendObjToString = Constants.YOU_OWE+" "+currency+Math.abs(oweAmount);
		} else if(oweAmount == 0) {
			friendObjToString = Constants.NO_DUES;
		}
		
		return friendObjToString;
	}
	
}
