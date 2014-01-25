package com.coursera.tstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.widget.TextView;

public class StatsActivity extends Activity implements OnClickListener {

	public static String username = "twitter_username";

	public HashMap<String, Integer> tweetCount;
	public TreeMap<String, Integer> sortedTweetMap;
	public Twitter twitter;
	public static List<String> exceptions;
	public TextView t;
	public EditText usernameInput;
	public ProgressDialog progressDialog;
	public ListView wordList;
	public ArrayAdapter<String> adapter;
	public Button computeStats;
	public Context context;
	public int getWordsResults = 0;
	public static final int TWITTER_FEED_PAGE_MAX = 100;

	private static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		context = this;
		t = (TextView) findViewById(R.id.username);
		usernameInput = (EditText) findViewById(R.id.username_input);
		computeStats = (Button) findViewById(R.id.submit_button);
		computeStats.setOnClickListener(this);
		wordList = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>());
		// adapter = new ArrayAdapter<String>(this, R.layout.tweet_count_row,new
		// ArrayList<String>());
		wordList.setAdapter(adapter);

		sharedPreferences = getSharedPreferences(
				OAuthConfiguration.PREFERENCE_NAME, MODE_PRIVATE);

		String accessToken = sharedPreferences.getString(
				OAuthConfiguration.PREF_KEY_TOKEN, "");
		String accessSecret = sharedPreferences.getString(
				OAuthConfiguration.PREF_KEY_SECRET, "");

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(OAuthConfiguration.CONSUMER_KEY)
				.setOAuthConsumerSecret(OAuthConfiguration.CONSUMER_SECRET)
				.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(accessSecret);

		TwitterFactory factory = new TwitterFactory(cb.build());
		twitter = factory.getInstance();

		tweetCount = new HashMap<String, Integer>();
		ValueComparator bvc = new ValueComparator(tweetCount);
		sortedTweetMap = new TreeMap<String, Integer>(bvc);

		exceptions = new ArrayList<String>();

		try {
			loadExceptions("exceptions.txt");
		} catch (IOException e) {
			Log.e("TWEET", "File not found: " + e);
		}
	}

	@Override
	protected void onDestroy() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			computeStats.setEnabled(true);
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.stats, menu);
	    return true;
	}
	
	public void loadExceptions(String filename) throws IOException {
		InputStream inputreader = getAssets().open(filename);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputreader));

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			exceptions.add(line);
		}
		bufferedReader.close();
	}

	@Override
	public void onClick(final View v) {

		tweetCount.clear();
		sortedTweetMap.clear();
		adapter.clear();
		adapter.notifyDataSetChanged();

		if (usernameInput.getText().length() > 0) {
			username = usernameInput.getText().toString();
		}

		// v.setEnabled(false);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			@Override
			protected void onPreExecute() {
				progressDialog = new ProgressDialog(context);
				progressDialog.setTitle("Gathering data from Twitter...");
				progressDialog.setMessage("Please wait.");
				progressDialog.setCancelable(false);
				progressDialog.setIndeterminate(true);
				progressDialog.show();
			}

			@Override
			protected Void doInBackground(Void... arg0) {
				try {
					getWordsResults = computeStats();
				} catch (TwitterException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				if (progressDialog != null) {
					progressDialog.dismiss();
					computeStats.setEnabled(true);
				}
				if (getWordsResults == -1) {
					t.setText("@" + username
							+ "'s most commonly used key words in last "
							+ TWITTER_FEED_PAGE_MAX + " tweets: ");
					getTopWords(5);
				} else {
					showErrorDialog(v, getWordsResults);
					t.setText("");
				}
				usernameInput.setText(""); // reset input
			}

		};
		task.execute((Void[]) null);

	}

	public int computeStats() throws TwitterException {
		try {
			Paging paging = new Paging(1, TWITTER_FEED_PAGE_MAX);
			List<Status> statuses = twitter.getUserTimeline(username, paging);

			for (Status status : statuses) {
				String tweet = status.getText();
				String[] words = StringUtils.split(tweet.toLowerCase());
				for (String s : words) {
					if (!s.equals("rt") && !s.startsWith("@")
							&& !s.startsWith("http")) {
						s = s.replaceAll("[^A-Z a-z']", "");
						if (tweetCount.containsKey(s)) {
							int value = tweetCount.get(s) + 1;
							tweetCount.put(s, value);
						} else {
							tweetCount.put(s, 1);
						}
					}
				}
			}

		} catch (TwitterException e) {
			Log.e("TSTATS", "ERROR: " + e.getMessage());
			// showErrorDialog(v, e.getStatusCode());
			return e.getStatusCode();
		}

		return -1;
	}

	public void getTopWords(int limit) {
		sortedTweetMap.putAll(tweetCount);
		int count = 0;
		for (Entry<String, Integer> entry : sortedTweetMap.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (value > 1 && !exceptions.contains(key)) {
				adapter.add(key + ": " + value);
				count++;
			}
			if (count >= limit) {
				adapter.notifyDataSetChanged();
				return;
			}
		}
	}

	public void showErrorDialog(View view, int errorCode) {
		AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		builder.setTitle("Error Code " + errorCode);

		// See link for more info:
		// https://dev.twitter.com/docs/error-codes-responses
		switch (errorCode) {
		case 404:
			builder.setMessage("Sorry, that username does not exist.");
			break;
		case 401:
			builder.setMessage("This user's profile is private.");
			break;
		case 502:
			builder.setMessage("Twitter is down or being upgraded.");
			break;
		case 503:
			builder.setMessage("Twitter is currently overloaded with requests.");
			break;
		case 504:
			builder.setMessage("Gateway timeout. Please try again later.");
			break;
		default:
			builder.setMessage("Please try again later.");
			break;
		}

		// User closed out of dialog
		builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

}

// comparator so that TreeMap sorts based on value instead of key
class ValueComparator implements Comparator<String> {

	Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
