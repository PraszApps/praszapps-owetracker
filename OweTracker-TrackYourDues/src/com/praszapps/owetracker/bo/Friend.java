package com.praszapps.owetracker.bo;


public class Friend {

	String id;
	String name;
	int oweAmount;
	String summary;
	String currency;
	//Bitmap image;

	// For Sample Data
	public Friend(String name, int oweAmount) {
		super();
		this.name = name;
		this.oweAmount = oweAmount;
	}

	public Friend(String id, String name, int oweAmount, 
			String currency/*, Bitmap image*/) {
		super();
		this.id = id;
		this.name = name;
		this.oweAmount = oweAmount;
		this.currency = currency;
		//this.image = image;
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

	/*public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}*/
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(int oweAmount) {
		this.summary = formatCurrency(currency)+Math.abs(oweAmount);
	}
	
	public String formatCurrency(String currency) {
		if(currency.equals("Rs")) {
			return currency += ". ";
		} else {
			return currency;
		}
	}

	@Override
	public String toString() {
		
		String friendObjToString = null;
		if(oweAmount <= 0) {
			friendObjToString = name+" owes you "+formatCurrency(currency)+Math.abs(oweAmount);
		} else {
			friendObjToString = "You owe "+name+" "+formatCurrency(currency)+Math.abs(oweAmount);
		}
		
		return friendObjToString;
	}
	
}
