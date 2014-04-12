package vill.will.thenewboston;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefsActivity extends Activity implements OnClickListener
{
	/* Declarations */
	EditText etSharedData;
	TextView tvDataResults;
	SharedPreferences sharedPrefsData;
	public static final String PREFS_FILE_NAME = "MySharedString"; // Think of like a folder(or a dictionary), there are strings below which are the keys 

	/* Methods */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.activity_shared_preferences);

		// Setup References
		initialize();
		
		// Get Prefs
		sharedPrefsData = getSharedPreferences(PREFS_FILE_NAME, 0);
	}

	private void initialize()
	{
		// Refereces
		Button save = (Button) findViewById(R.id.btnSave);
		Button load = (Button) findViewById(R.id.btnLoad);
		etSharedData = (EditText) findViewById(R.id.etSharedPrefs);
		tvDataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

		save.setOnClickListener(this);
		load.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.btnSave:
			// Store ET Text
			String stringData = etSharedData.getText().toString();
			
			// Retrieve Editor 
			SharedPreferences.Editor editor = sharedPrefsData.edit();
			
			// Save stringData to 'FirstSharedString'
			editor.putString("FirstSharedString", stringData);
			
			// Commit Changes
			editor.commit();
			
			break;
		case R.id.btnLoad:
			// Re-Get Prefs
			sharedPrefsData = getSharedPreferences(PREFS_FILE_NAME, 0);
			
			// Try to Retrieve Data
			String dataReturned = sharedPrefsData.getString("FirstSharedString", "Couldn't Load Data");
			
			// Display Returned Data
			tvDataResults.setText(dataReturned);
			break;

		default:
			break;
		}
	}

}// SharedPrefsActivity
