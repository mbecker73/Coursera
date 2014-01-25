package com.coursera.tstats;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private static Twitter twitter;
	private static RequestToken requestToken;
	private static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//load previously saved shared preferences
		sharedPreferences = getSharedPreferences(OAuthConfiguration.PREFERENCE_NAME, MODE_PRIVATE);

		Uri uri = getIntent().getData();
		if (uri != null && uri.toString().startsWith(OAuthConfiguration.CALLBACK_URL)) {
			String verifier = uri.getQueryParameter(OAuthConfiguration.OAUTH_VERIFIER);

			// Save the user oAuth token and secret token in shared preferences
			try {
				AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
				SharedPreferences.Editor editor = sharedPreferences.edit();
		        editor.putString(OAuthConfiguration.PREF_KEY_TOKEN, accessToken.getToken());
		        editor.putString(OAuthConfiguration.PREF_KEY_SECRET,accessToken.getTokenSecret());
		        editor.commit();
		          
			} catch (Exception e) {
				Log.e("TSTATS", e.getMessage());
			}

		}
	}

	// check if user is connected and start appropriate activity
	protected void onResume() {
		super.onResume();

		if (isConnected()) {
			Intent i = new Intent(this, StatsActivity.class);
			startActivity(i);
		} else {
			//login(null);
		}
	}

	// Method to check in shared preferences if the user has already authenticated the app
	private boolean isConnected() {
		if(sharedPreferences.getString(OAuthConfiguration.PREF_KEY_TOKEN, null) != null) {
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// start activity to authenticate the user's twitter account
	public void login(View v) {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(OAuthConfiguration.CONSUMER_KEY)
				.setOAuthConsumerSecret(OAuthConfiguration.CONSUMER_SECRET);
		TwitterFactory factory = new TwitterFactory(cb.build());
		twitter = factory.getInstance();

		try {
			requestToken = twitter
					.getOAuthRequestToken(OAuthConfiguration.CALLBACK_URL);
			Toast.makeText(this, "Redirecting to Twitter authorization..",
					Toast.LENGTH_LONG).show();
			this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse(requestToken.getAuthenticationURL())));
		} catch (TwitterException e) {
			Log.e("TSTATS", "Exception thrown: " + e);
		}
	}
	
	// start stats activity using guest twitter account login credentials
	public void guestLogin(View v){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(OAuthConfiguration.CONSUMER_KEY)
				.setOAuthConsumerSecret(OAuthConfiguration.CONSUMER_SECRET)
				.setOAuthAccessToken(OAuthConfiguration.GUEST_ACCESS_TOKEN)
				.setOAuthAccessTokenSecret(OAuthConfiguration.GUEST_ACCESS_SECRET);
		
		TwitterFactory factory = new TwitterFactory(cb.build());
		twitter = factory.getInstance();
		
		SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(OAuthConfiguration.PREF_KEY_TOKEN, OAuthConfiguration.GUEST_ACCESS_TOKEN);
        editor.putString(OAuthConfiguration.PREF_KEY_SECRET, OAuthConfiguration.GUEST_ACCESS_SECRET);
        editor.commit();
		
		Intent i = new Intent(this, StatsActivity.class);
		startActivity(i);
		
	}

}
