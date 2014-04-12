package vill.will.thenewboston;

import android.app.Activity;
import android.os.Bundle;

public class AboutUs extends Activity
{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Set Convent View From Layout
		setContentView(R.layout.about);
	}

}
