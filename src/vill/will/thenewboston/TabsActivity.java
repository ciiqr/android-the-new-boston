package vill.will.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabsActivity extends Activity implements OnClickListener
{
	TabHost tabHost;
	TextView tvShowResults;
	long startTime = 0, stopTime;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Setup View
		setContentView(R.layout.activity_tabs);

		// Create TabHost
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		// Create Tabs
		TabSpec tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setContent(R.id.tab1);
		tabSpec.setIndicator("StopWatch");
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setContent(R.id.tab2);
		tabSpec.setIndicator("Tab 2");
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag3");
		tabSpec.setContent(R.id.tab3);
		tabSpec.setIndicator("Add a Tab");
		tabHost.addTab(tabSpec);

		// Buttons
		Button btnAddTab = (Button) findViewById(R.id.btnAddTab);
		Button btnStartWatch = (Button) findViewById(R.id.btnStartWatch);
		Button btnStopWatch = (Button) findViewById(R.id.btnStopWatch);

		// Button Click Handlers
		btnAddTab.setOnClickListener(this);
		btnStartWatch.setOnClickListener(this);
		btnStopWatch.setOnClickListener(this);

		// Timer Text View
		tvShowResults = (TextView) findViewById(R.id.tvTimerResult);

	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.btnAddTab:
			int tabNumber = tabHost.getTabWidget().getTabCount() + 1;
			TabSpec tabSpec = tabHost.newTabSpec("tag" + tabNumber);
			tabSpec.setContent(new TabContentFactory()
			{
				@Override
				public View createTabContent(String tag)
				{
					// Create Text View
					TextView textView = new TextView(TabsActivity.this);
					// Set Text
					textView.setText("You've Created a New Tab!");

					// Return View
					return textView;
				}
			});

			tabSpec.setIndicator("Tab " + tabNumber);
			tabHost.addTab(tabSpec);
			break;
		case R.id.btnStartWatch:
			startTime = System.currentTimeMillis();
			break;
		case R.id.btnStopWatch:
			stopTime = System.currentTimeMillis();

			if (startTime != 0)
			{
				long result = stopTime - startTime;
				int msResult = (int) result;
				int sResult = (int) result / 1000;
				int mResult = (int) sResult / 60;

				// @formatter:off
				String resultString = msResult + " ms" + '\n' +
						sResult + " s" + '\n' +
						mResult + " m";
				// @formatter:on
				
				/*
				 * Alt Way to do formatting(in Format 12:22:01) msResult %= 100;
				 * sResult %= 60;
				 */

				tvShowResults.setText(resultString);

				startTime = 0;
			}
			break;

		default:
			break;
		}
	}
}
