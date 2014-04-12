package vill.will.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.WindowManager.LayoutParams;

public class GFX extends Activity
{
	/* Declarations */
	MyBringBack outView;
	WakeLock wakeLock;

	/* Methods*/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Initialize Out View
		outView = new MyBringBack(this);
		
		// Set Out View to Content View
		setContentView(outView);
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		
		wakeLock.release();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume()
	{
		// Super
		super.onResume();
		
		// Wake Lock
		PowerManager powerMan = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerMan.newWakeLock(LayoutParams.FLAG_KEEP_SCREEN_ON, "Always Animating");
		// Start
		wakeLock.acquire();
	}

	
	
}
