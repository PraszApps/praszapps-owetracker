package com.praszapps.owetracker.bo;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.praszapps.owetracker.util.Constants;


public class Due {

	private String dueId;
	private String friendId;
	private long date;
	private int amount;
	private String reason;
	private String currency;

	public Due(String dueId, String friendId, long date, int amount,
			String reason) {
		super();
		this.dueId = dueId;
		this.friendId = friendId;
		this.date = date;
		this.amount = amount;
		this.reason = reason;
	}

	public Due() {
		// Default Constructor
	}

	public String getDueId() {
		return dueId;
	}

	public void setDueId(String dueId) {
		this.dueId = dueId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	@SuppressLint("SimpleDateFormat")
	public String getFormattedDate() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);
		return dateFormat.format(c.getTimeInMillis());
	}
	
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
