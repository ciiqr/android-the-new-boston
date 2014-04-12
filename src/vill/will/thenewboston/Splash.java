package vill.will.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import vill.will.thenewboston.R;

public class Splash extends Activity
{
	// private MediaPlayer songPlayer;

	public Splash()
	{
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// // Play Song
		// songPlayer = MediaPlayer.create(this, R.raw.woodpecker);
		// songPlayer.start();
	}

	@Override
	protected void onResume()
	{
		// Super
		super.onResume();

		// Timer
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(900);

				} catch (InterruptedException e)
				{
					e.printStackTrace();

				} finally
				{
					// Switch to Starting Point
					Intent switchToSartingPoint = new Intent(
							"vill.will.thenewboston.MENU");
					startActivity(switchToSartingPoint);
				}
			}
		};

		// Get Preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		// Get Value (Or passed in default)
		boolean playMusic = prefs.getBoolean("splashScreenMusic", true);

		if (playMusic)
			timer.start();
	}

	@Override
	protected void onPause()
	{
		// Super
		super.onPause();

		// // Stop Song
		// songPlayer.release();

		// Close Activity
		finish();
	}

}// Splash
