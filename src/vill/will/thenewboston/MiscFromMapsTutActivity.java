package vill.will.thenewboston;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MiscFromMapsTutActivity extends Activity implements
		OnClickListener, android.content.DialogInterface.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Content View (Layout)
		setContentView(R.layout.activity_misc_maps);

		// References
		Button btnMiscAlertDialog = (Button) findViewById(R.id.btnMiscAlertDialog);

		// Event Handlers
		btnMiscAlertDialog.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		// 
		switch (v.getId())
		{
		case R.id.btnMiscAlertDialog:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			
			alert.setTitle("Pick an Option");
			alert.setMessage("Just Do it!");
//			Button btn1 = new Button(this);
			alert.setButton(1, "1", this);
			
			alert.show();
			
			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		// Log Which Button Was Pressed
		Log.d("TNB.MISC", "Button " + which);
	}
}
