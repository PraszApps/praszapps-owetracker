package com.praszapps.owetracker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.praszapps.owetracker.R;
import com.praszapps.owetracker.util.Utils;

public class AboutActivity extends RootActivity {
	EditText feedbackText;
	RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		feedbackText = (EditText) findViewById(R.id.editTextFeedback);
		ratingBar = (RatingBar) findViewById(R.id.ratingBarApprate);
		Button sendFeedback = (Button) findViewById(R.id.buttonSend);
		sendFeedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (feedbackText.getText().toString().trim().equals("")) {
					Utils.showToast(AboutActivity.this,
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
						Utils.showToast(AboutActivity.this,
								"There are no email clients installed",
								Toast.LENGTH_SHORT);
					}
				}

			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}

}
