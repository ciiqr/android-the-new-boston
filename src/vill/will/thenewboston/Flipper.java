package vill.will.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener
{
	ViewFlipper viewFlipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Content View
		setContentView(R.layout.activity_flipper);
		
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);
		viewFlipper.setOnClickListener(this);
		
		viewFlipper.setFlipInterval(900);
		viewFlipper.startFlipping();
	}

	@Override
	public void onClick(View v)
	{
		// 
		viewFlipper.showNext();
	}

	

}
