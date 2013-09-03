/**
 * Author - Prasannajeet Pani, Copyright reserved
 */


package com.praszapps.owetracker.util;

import java.util.UUID;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Utils {

	public static interface DialogResponse {
		void onPositive();
		void onNegative();
		void onNeutral();
	}
	
	public static void showAlertDialog(Context context, String title, String message, Drawable icon, Boolean isCancelable,
			String positiveString, String negativeString, String neutralString, final DialogResponse response){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle(title);
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
	
	public static void showToast(Context context, String text, int displayTime) {
		if(displayTime == Toast.LENGTH_LONG || displayTime == Toast.LENGTH_SHORT) {
			
			Toast toast = Toast.makeText(context, text, displayTime);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		
	}
		
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
	
	public static String generateUniqueID()
	{
		String uid = UUID.randomUUID().toString().toUpperCase();
		return uid;
	}
	
}
