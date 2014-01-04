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
	int amount;
	int oweAmount;
	String summary;
	String currency;
	String totalRecords;
	String lastUpdated;

	public Friend(String id, String name, int oweAmount, 
			String currency, String totalRecords, String lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.oweAmount = oweAmount;
		this.currency = currency;
		this.totalRecords = totalRecords;
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
		this.summary = currency+Math.abs(oweAmount);
	}
	
	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		
		String friendObjToString = null;
		if(oweAmount < 0) {
			friendObjToString = name+" : "+Constants.OWES_YOU+" "+currency+Math.abs(oweAmount);
		} else if(oweAmount > 0) {
			friendObjToString = name+" : "+Constants.YOU_OWE+" "+currency+Math.abs(oweAmount);
		} else if(oweAmount == 0) {
			friendObjToString = name+" : "+Constants.NO_DUES;
		}
		
		return friendObjToString;
	}
	
}
