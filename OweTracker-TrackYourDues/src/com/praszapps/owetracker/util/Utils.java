/**
 * Author - Prasannajeet Pani, Copyright reserved
 */


package com.praszapps.owetracker.util;

import java.util.UUID;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.application.OweTrackerApplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

	/**
	 * This is the interface to be used for Positive, Negative and Neutral buttons of the AlertDialog
	 * @author Prasannajeet Pani
	 *
	 */
	public static interface DialogResponse {
		void onPositive();
		void onNegative();
		void onNeutral();
	}
	
	/**
	 * Sets all the parameters and displays the AlertDialog 
	 * @param context - Context of the activity
	 * @param title - Title of the AlertDialog, can be null
	 * @param message - The message that needs to be displayed in the AlertDialog
	 * @param icon - An icon that needs to be shown in the AlertDialog, can be null
	 * @param isCancelable - Whether the AlertDialog is cancellable or not
	 * @param positiveString - Message to be shown in the positive response, can be null
	 * @param negativeString - Message to be shown in the negative response, can be null
	 * @param neutralString -- Message to be shown in the neutral response, can be null
	 * @param response - Instance of the DialogResponse interface
	 */
	public static void showAlertDialog(Context context, String title, String message, Drawable icon, Boolean isCancelable,
			String positiveString, String negativeString, String neutralString, final DialogResponse response){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		
		if(title != null) {
			alert.setTitle(title);
		}
		
		alert.setMessage(message);
		
		if(icon != null) {
			alert.setIcon(icon);
		}
		
		if(isCancelable) {
			alert.setCancelable(true);
		} else {
			alert.setCancelable(false);
		}
		
		if(positiveString != null) {
			alert.setPositiveButton(positiveString, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					response.onPositive();
				}
			});
		}
		
		if(negativeString != null) {
			alert.setNegativeButton(negativeString, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					response.onNegative();
				}
			});
		}
		
		if(neutralString != null) {
			alert.setNeutralButton(neutralString, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					response.onNeutral();
				}
			});
		}
		alert.show();
	}
	
	/**
	 * Method to invoke display of Toast message
	 * @param context - Context where it is to be invoked
	 * @param text - Text of the toast
	 * @param displayTime - Toast.LENGTH_LONG or Toast.LENGHT_SHORT
	 */
	public static void showToast(Context context, String text, int displayTime) {
		if(displayTime == Toast.LENGTH_LONG || displayTime == Toast.LENGTH_SHORT) {
			
			Toast toast = Toast.makeText(context, text, displayTime);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		
	}
		
	/**
	 * This method is used to display Logs in the application
	 * @param tag - The tag of the log message
	 * @param msg  - The message to be shown
	 * @param level - The log level (ERROR, VERBOSE etc.)
	 */
	public static void showLog(String tag, String msg, int level) {
		switch(level) {
		
		case Log.ERROR:
			Log.e(tag, msg);
			break;
		case Log.VERBOSE:
			Log.v(tag, msg);
			break;
		case Log.INFO:
			Log.i(tag, msg);
			break;
		case Log.DEBUG:
			Log.d(tag, msg);
			break;
		default:
			Log.v(tag, msg);
			break;
		}
	}
	
	/**
	 * Generated a random Unique ID
	 * @return The generated random Unique string
	 */
	public static String generateUniqueID()
	{
		String uid = UUID.randomUUID().toString().toUpperCase();
		return uid;
	}
	
	/**
	 * While adding/updating a friend, this method is invoked to store the appropriate value of
	 * the user's display currency as per the item selected in the dropdown
	 * @param arrayItem -- The dropdown item selected by user to select currency
	 * @return - The actual currency value that is stored in the database
	 */
	public static String getCurrencyFromArrayItem(String arrayItem) {
		
		if(arrayItem.equals(OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_rupee))) {
			
			if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			    // Rupee symbol replaced by INR for below ICS version
					return "INR ";
				} else {
					// Store Rupee symbol
					return OweTrackerApplication.getContext().getResources().getString(R.string.currency_rupee);
				}
			
		} else if(arrayItem.equals(OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_dollar))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.currency_dollar);
		} else if(arrayItem.equals(OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_yen))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.currency_yen);
		} else if(arrayItem.equals(OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_pound))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.currency_pound);
		} else if(arrayItem.equals(OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_euro))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.currency_euro);
		} else {
			return null;
		}
	}
	
	public static void goToGooglePlayPage(Context ctx) {
		try {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW, 
            		Uri.parse("market://details?id="+Constants.PACKAGE_NAME)));
        } catch (android.content.ActivityNotFoundException anfe) {
        	ctx.startActivity(new Intent(Intent.ACTION_VIEW, 
            		Uri.parse("http://play.google.com/store/apps/details?id="
            +Constants.PACKAGE_NAME)));
        }
	}
	
	/**
	 * During updating a friend this method is invoked to show the appropriate dropdown item as per selected
	 * currency
	 * @param currency - The currency symbol stored in the database
	 * @return - Dropdown list item to be selected
	 */
	public static String getArrayItemFromCurrency(String currency) {
		
		if(currency.equals(OweTrackerApplication.getContext().getResources().getString(R.string.currency_rupee)) || currency.equals("INR ")) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_rupee);
		} else if(currency.equals(OweTrackerApplication.getContext().getResources().getString(R.string.currency_dollar))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_dollar);
		} else if(currency.equals(OweTrackerApplication.getContext().getResources().getString(R.string.currency_yen))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_yen);
		} else if(currency.equals(OweTrackerApplication.getContext().getResources().getString(R.string.currency_pound))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_pound);
		} else if(currency.equals(OweTrackerApplication.getContext().getResources().getString(R.string.currency_euro))) {
			return OweTrackerApplication.getContext().getResources().getString(R.string.array_currency_item_euro);
		} else {
			return null;
		}
	}
	
}
