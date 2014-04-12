package vill.will.thenewboston;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class PointlessWidgetConfig extends Activity implements
		android.view.View.OnClickListener
{
	EditText info;
	AppWidgetManager appWidgetManager;
	Context c;
	int appWidgetID;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_widget_config);

		Button btnWidgetConfig = (Button) findViewById(R.id.btnWidgetConfig);
		btnWidgetConfig.setOnClickListener(this);

		
		c = this;
		info = (EditText) findViewById(R.id.etWidgetConfig);

		// Get Widget Info
		// Get Calling Intent
		Intent i = getIntent();

		Bundle extras = i.getExtras();

		if (extras != null)
		{
			appWidgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		else
			finish();
		
		appWidgetManager = AppWidgetManager.getInstance(c);
	}

	@Override
	public void onClick(View v)
	{
		Log.d("TNB", "On Click Widget Config Button");
		
		// Text to Display
		String e = info.getText().toString();
		
		// Get Remote Views
		RemoteViews remoteViews = new RemoteViews(c.getPackageName(), R.layout.widget_pointless);
		// Change Text
		remoteViews.setTextViewText(R.id.tvConfigInput, e);
		
		// This is not normally done so it will not work after a restart of the device or homescreen
		{
			// Create Pending Intent to Launch App (Display Splash)
			Intent intent = new Intent(c, Splash.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);
			
			// Attach Pending Intent to Widget Button
			remoteViews.setOnClickPendingIntent(R.id.btnWidgetOpen, pendingIntent);
		}
		
		// Update Widget
		appWidgetManager.updateAppWidget(appWidgetID, remoteViews);
		
		// Return Result to caller
		Intent result = new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetID);
		setResult(RESULT_OK, result);
		Log.d("TNB", "On Click Widget Config Button End");
		// Close Activity
		finish();
	}

}
