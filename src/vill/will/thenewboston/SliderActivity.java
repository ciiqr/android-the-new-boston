package vill.will.thenewboston;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer; // Deprecated (Note: Suggested Resolve is to re-implement based on Android Open Source Project)
import android.widget.SlidingDrawer.OnDrawerOpenListener;

@SuppressWarnings("deprecation") // For SlidingDrawer Widget 
public class SliderActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnDrawerOpenListener
{
	SlidingDrawer slidingDrawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Setup View With Layout
		setContentView(R.layout.activity_sliding);

		// Setup View References
		Button btn1 = (Button) findViewById(R.id.slidebutton1);
		Button btn2 = (Button) findViewById(R.id.slidebutton2);
		Button btn3 = (Button) findViewById(R.id.slidebutton3);
		Button btn4 = (Button) findViewById(R.id.slidebutton4);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);

		CheckBox checkBox = (CheckBox) findViewById(R.id.cbSlidable);
		checkBox.setOnCheckedChangeListener(this);
		
		slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingD);
		slidingDrawer.setOnDrawerOpenListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		//
		if (isChecked)
		{
			slidingDrawer.lock();
		}
		else
		{
			slidingDrawer.unlock();
		}

	}

	@Override
	public void onClick(View v)
	{
		//
		switch (v.getId())
		{
		case R.id.slidebutton1:
			slidingDrawer.open();
			break;
		case R.id.slidebutton2:
			
			break;
		case R.id.slidebutton3:
			slidingDrawer.toggle();
			break;
		case R.id.slidebutton4:
			slidingDrawer.close();
			break;

		default:
			break;
		}

	}

	@Override
	public void onDrawerOpened()
	{
		// PLay Sound
		MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}
}
