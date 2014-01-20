package com.coursera.webpageapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class JabberwockyActivity extends Activity {

	WebView myWebView;
	MediaPlayer song;
	boolean picLoaded = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jabberwocky);
		myWebView = (WebView) findViewById(R.id.webView1);
		myWebView.getSettings().setBuiltInZoomControls(true);
		myWebView.loadUrl("file:///android_asset/jabberwocky.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jabberwocky, menu);
		return true;
	}

	@Override
	protected void onResume() {
		song = MediaPlayer.create(this, R.raw.song);
		song.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		song.stop();
		song.release();
		super.onPause();
	}

	public void openWikipedia(View v) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse("http://en.wikipedia.org/wiki/Jabberwocky"));
		startActivity(i);
	}

	public void togglePic(View v) {
		if (!picLoaded) {
			myWebView.loadUrl("file:///android_asset/jabberwocky.jpg");
			picLoaded = true;
		} else {
			myWebView.loadUrl("file:///android_asset/jabberwocky.html");
			picLoaded = false;
		}

	}

}
