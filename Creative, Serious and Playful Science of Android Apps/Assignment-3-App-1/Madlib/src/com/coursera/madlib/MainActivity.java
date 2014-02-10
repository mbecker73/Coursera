package com.coursera.madlib;

import java.util.Random;

import com.coursera.madlib.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = "MadLib";

	EditText nameInput, nounInput, verbInput, adjInput, numberInput, colorInput;
	TextView story;
	Button resetButton, submitButton, shareButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize the form inputs and buttons
		nameInput = (EditText) findViewById(R.id.enterName);
		nounInput = (EditText) findViewById(R.id.enterNoun);
		verbInput = (EditText) findViewById(R.id.enterVerb);
		adjInput = (EditText) findViewById(R.id.enterAdj);
		numberInput = (EditText) findViewById(R.id.enterNumber);
		story = (TextView) findViewById(R.id.storyText);
		resetButton = (Button) findViewById(R.id.resetButton);
		submitButton = (Button) findViewById(R.id.submitButton);
		shareButton = (Button) findViewById(R.id.shareButton);
		// hide the share button until the story is created
		shareButton.setVisibility(View.GONE);

		// Reset button listener to clear the form and the current story
		OnClickListener resetListener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				nameInput.setText("");
				nounInput.setText("");
				verbInput.setText("");
				adjInput.setText("");
				numberInput.setText("");
				story.setText("");
				// hide the share button
				shareButton.setVisibility(View.GONE);
			}
		};
		resetButton.setOnClickListener(resetListener);

		// Submit button listener to create the story based on user input
		OnClickListener submitListener = new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// check that the form is complete and return if not
				if (checkEmptyForm() == -1) {
					return;
				}
				// Create a random number for use in the story
				Random generator = new Random();
				int numDownloads = (generator.nextInt(10) + 1)
						* Integer.valueOf(numberInput.getText().toString()
								.trim());

				// Create story by combining sentences with user input
				StringBuilder sb = new StringBuilder();
				sb.append("One day, ")
						.append(nameInput.getText().toString().trim())
						.append(" decided to code an Android application about ");
				sb.append(nounInput.getText().toString().trim())
						.append(". The app was going to teach the user how to use ")
						.append(nounInput.getText().toString().trim())
						.append(" to ");
				sb.append(verbInput.getText().toString().trim()).append(".");
				sb.append(" After spending about ")
						.append(numberInput.getText().toString().trim())
						.append(" hours of ")
						.append(adjInput.getText().toString().trim())
						.append(" programming, the app was finally finished. ");
				sb.append(nameInput.getText().toString().trim())
						.append(" submitted it to the Google Play Store and it was downloaded ")
						.append(numDownloads).append(" times!");
				story.setText(sb.toString());
				// Make the share button visible once the story is generated
				shareButton.setVisibility(View.VISIBLE);
			}

			// Method to check if the form is completely filled out
			// If a field is missing, alerts the user which field is missing
			// input and exits without generating the story
			private int checkEmptyForm() {
				if (isEmpty(nameInput)) {
					Toast.makeText(getApplicationContext(),
							"You did not enter a name", Toast.LENGTH_SHORT)
							.show();
					Log.d(TAG, "User did not enter a name..");
					nameInput.requestFocus();
					return -1;
				} else if (isEmpty(nounInput)) {
					Toast.makeText(getApplicationContext(),
							"You did not enter a noun", Toast.LENGTH_SHORT)
							.show();
					Log.d(TAG, "User did not enter a noun..");
					nounInput.requestFocus();
					return -1;
				} else if (isEmpty(verbInput)) {
					Toast.makeText(getApplicationContext(),
							"You did not enter a verb", Toast.LENGTH_SHORT)
							.show();
					Log.d(TAG, "User did not enter a verb..");
					verbInput.requestFocus();
					return -1;
				} else if (isEmpty(adjInput)) {
					Toast.makeText(getApplicationContext(),
							"You did not enter an adjective",
							Toast.LENGTH_SHORT).show();
					Log.d(TAG, "User did not enter an adjective..");
					adjInput.requestFocus();
					return -1;
				} else if (isEmpty(numberInput)) {
					Toast.makeText(getApplicationContext(),
							"You did not enter a number", Toast.LENGTH_SHORT)
							.show();
					Log.d(TAG, "User did not enter a number..");
					numberInput.requestFocus();
					return -1;
				}
				return 0;
			}

			// Helper method to check if an Edit Text field is empty or not
			private boolean isEmpty(EditText e) {
				return e.getText().toString().trim().length() == 0;
			}
		};
		submitButton.setOnClickListener(submitListener);

		// Share button listener to share generated story via SMS 
		OnClickListener shareListener = new OnClickListener() {
			@Override
			public void onClick(View v) {

				// Start intent to send the story in a text message
				Intent intent = new Intent(Intent.ACTION_VIEW);
				// Leave the phone number empty for the user to fill in
				intent.setData(Uri.fromParts("sms", "", null));
				intent.putExtra("sms_body", story.getText().toString());

				try {
					startActivity(intent);
					// if the intent fails to start show a Toast message to the user explaining the failure
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getApplicationContext(),
							"Text message failed to be sent", Toast.LENGTH_SHORT)
							.show();
					Log.e(TAG, "Text message failed to be sent: ", e);
				}

			}

		};
		shareButton.setOnClickListener(shareListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
