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
import android.widget.RatingBar;
import android.widget.Toast;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.util.Utils;

public class ContactFragment extends Fragment {

	EditText feedbackText;
	RatingBar ratingBar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_contact, container, false);
		feedbackText = (EditText) v.findViewById(R.id.editTextFeedback);
		ratingBar = (RatingBar) v.findViewById(R.id.ratingBarApprate);
		Button sendFeedback = (Button) v.findViewById(R.id.buttonSend);
		sendFeedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (feedbackText.getText().toString().trim().equals("")) {
					Utils.showToast(getActivity(),
							"Please enter feedback", Toast.LENGTH_SHORT);
				} else {
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					i.putExtra(Intent.EXTRA_EMAIL,
							new String[] { "prasannajeet89@gmail.com" });
					i.putExtra(Intent.EXTRA_SUBJECT, "Owetracker - Feedback");
					i.putExtra(Intent.EXTRA_TEXT,
							feedbackText.getText().toString()
									+ "\n\nRating -- " + ratingBar.getRating());
					try {
						startActivity(Intent.createChooser(i, "Send Mail"));
					} catch (android.content.ActivityNotFoundException ex) {
						Utils.showToast(getActivity(),
								"There are no email clients installed",
								Toast.LENGTH_SHORT);
					}
				}

			}
		});
		return v;
	}

}
