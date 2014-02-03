package com.praszapps.owetracker.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.util.Constants;
import com.praszapps.owetracker.util.Utils;

/**
 * 
 * This fragment displayed the Contact form in the Contact tab of the About activity
 * @author Prasannajeet Pani
 *
 */
public class ContactFragment extends Fragment {

	private LayoutInflater layoutInflater;
	private EditText feedbackText;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the view
		View v = inflater.inflate(R.layout.fragment_contact, container, false);
		layoutInflater = getLayoutInflater(savedInstanceState);
		// Initialise the views of the contact form
		feedbackText = (EditText) v.findViewById(R.id.editTextFeedback);
		Button sendFeedback = (Button) v.findViewById(R.id.buttonSend);
		sendFeedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (feedbackText.getText().toString().trim().equals("")) {
					Utils.showToast(getActivity(),
							"Please enter feedback", Toast.LENGTH_SHORT, layoutInflater);
				} else {
					
					// Fire the email intent
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL,
							new String[] { Constants.EMAIL_ID_FEEDBACK });
					i.putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJ_FEEDBACK);
					i.putExtra(Intent.EXTRA_TEXT,
							feedbackText.getText().toString());
					try {
						startActivity(Intent.createChooser(i, "Send Mail"));
					} catch (android.content.ActivityNotFoundException ex) {
						Utils.showToast(getActivity(),
								"There are no email clients installed",
								Toast.LENGTH_SHORT, layoutInflater);
					}
				}

			}
		});
		return v;
	}

}
