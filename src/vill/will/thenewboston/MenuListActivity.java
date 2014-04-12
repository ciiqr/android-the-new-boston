package vill.will.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuListActivity extends ListActivity
{
	/* Declarations */

	private String[] classes =
	{ "CounterActivity", "CommandEntry", "EmailActivity", "CameraBackActivity",
			"DataActivity", "GFX", "GFXSurface", "SoundStuff",
			"SliderActivity", "TabsActivity", "SimpleBrowserActivity",
			"Flipper", "InternalData", "ExternalData", "SQLiteExample",
			"Accelerate", "MiscFromMapsTutActivity", "HTTPExample",
			"GLExample", "VoiceActivity", "TextToSpeechActivity",
			"StatusBarNotifications", "VolumeSeekBarActivity" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Hook up List to String Array (classes)
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, classes));

		//

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// Super
		super.onListItemClick(l, v, position, id);

		try
		{
			// Class of New Activity
			Class<?> activityClass = Class.forName("vill.will.thenewboston."
					+ classes[position]);

			// Setup Activity Switch Intent
			Intent activityToSwitchTo = new Intent(this, activityClass);

			// Switch to Activity
			startActivity(activityToSwitchTo);

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Super
		// return super.onCreateOptionsMenu(menu);

		// Get a Menu Inflater
		MenuInflater menuInflater = getMenuInflater();

		// Initialize Menu with cool_menu file
		menuInflater.inflate(R.menu.cool_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		boolean resultValue;
		// Super
		// return super.onOptionsItemSelected(item);

		switch (item.getItemId())
		{
		case R.id.aboutUsMenuItem:
			// Create Intent
			Intent displayAboutIntent = new Intent(
					"vill.will.thenewboston.ABOUT");

			// Display About Activity
			startActivity(displayAboutIntent);

			resultValue = true;
			break;

		case R.id.preferencesMenuItem:
			// Create Intent
			Intent displayPrefsIntent = new Intent(
					"vill.will.thenewboston.PREFS");

			// Display About Activity
			startActivity(displayPrefsIntent);

			resultValue = true;
			break;

		case R.id.exitMenuItem:
			// Close Application
			finish();

			resultValue = true;
			break;

		default:
			resultValue = false;
			break;
		}

		return resultValue;
	}

}
