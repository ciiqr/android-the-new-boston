package vill.will.thenewboston;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class PointlessWidget extends AppWidgetProvider
{
	TextView tvOutput;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		Log.d("TNB", "On Update Widget");
		// Super
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		// Random Number
		Random rand = new Random();
		String randNum = String.valueOf(rand.nextInt(100));

		// Number if ID's
		final int N = appWidgetIds.length;

		// Itterate Each ID
		for (int index = 0; index < N; index++)
		{
			// Local Reference to ID
			int appWidgetID = appWidgetIds[index];

			// Create Remote View w/ Package Name & Widget Layout
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget_pointless);

			// Set Text View Text w/ ID Reference & Text (Random Number)
			remoteViews.setTextViewText(R.id.tvWidgetUpdate, randNum);

			// Update App Widget w/ Widget ID & Remote Views
			appWidgetManager.updateAppWidget(appWidgetID, remoteViews);

			// Debug
			Log.d("TNB", "Random Number: " + randNum);
			Log.d("TNB", "There Are: " + N + " Widget Id's...");
		}

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		Toast.makeText(context, "Good Bye!", Toast.LENGTH_LONG).show();

		// Super
		super.onDeleted(context, appWidgetIds);
	}

}
