package vill.will.thenewboston;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowserActivity extends Activity implements OnClickListener
{
	EditText etURL;
	WebView wvBrowser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Setup View
		setContentView(R.layout.activity_simple_browser);

		// Get Web View
		wvBrowser = (WebView) findViewById(R.id.webViewBrow);
		// Setup Settings
		wvBrowser.getSettings().setJavaScriptEnabled(true);
		wvBrowser.getSettings().setLoadWithOverviewMode(true);
		wvBrowser.getSettings().setUseWideViewPort(true);
		// Setup Web View Client (Delegate)
		wvBrowser.setWebViewClient(new SmplBrowserWVC());

		// Load Initial URL
		wvBrowser.loadUrl("http://www.mybringback.com");

		// Buttons
		Button btnGo = (Button) findViewById(R.id.btnGo);
		Button btnBrowBack = (Button) findViewById(R.id.btnBrowBack);
		Button btnBrowForward = (Button) findViewById(R.id.btnBrowForward);
		Button btnBrowRefresh = (Button) findViewById(R.id.btnBrowRefresh);
		Button btnBrowHistClear = (Button) findViewById(R.id.btnBrowHistClear);

		// Event Handlers
		btnGo.setOnClickListener(this);
		btnBrowBack.setOnClickListener(this);
		btnBrowForward.setOnClickListener(this);
		btnBrowRefresh.setOnClickListener(this);
		btnBrowHistClear.setOnClickListener(this);

		// URL Edit Text
		etURL = (EditText) findViewById(R.id.etURL);

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btnGo:
			// Get URL
			String url = getURLString();
			// Load URL
			wvBrowser.loadUrl(url);

			// Remove Focus from etURL
			etURL.clearFocus();
			// Dismiss Keyboard
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(etURL.getWindowToken(), 0);
			
			break;

		case R.id.btnBrowBack:
			// Has History to Go Back To
			if (wvBrowser.canGoBack())
				// Go Back
				wvBrowser.goBack();
			break;

		case R.id.btnBrowForward:
			// Has History to Go Forward To
			if (wvBrowser.canGoForward())
				// Go Forward
				wvBrowser.goForward();
			break;

		case R.id.btnBrowRefresh:
			// Reload
			wvBrowser.reload();
			break;

		case R.id.btnBrowHistClear:
			// Clear History
			wvBrowser.clearHistory();
			break;

		default:
			break;
		}
	}

	public String getURLString()
	{
		return etURL.getText().toString();
	}

	public URL getURL() throws MalformedURLException
	{
		return new URL(etURL.getText().toString());
	}

	private class SmplBrowserWVC extends WebViewClient
	{
		@Override
		public void onPageFinished(WebView view, String url)
		{
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			// Set etUrl Text
			etURL.setText(url);
			
			// Super
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			// Load URL with Out Web View
			view.loadUrl(url);

			return true;
			// return super.shouldOverrideUrlLoading(view, url);
		}

	}

}
