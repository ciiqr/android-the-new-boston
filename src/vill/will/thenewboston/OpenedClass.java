package vill.will.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
		RadioGroup.OnCheckedChangeListener
{

	/* Declarations */
	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, sendData;

	/* Methods */

	public OpenedClass()
	{
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Initialize Layout
		setContentView(R.layout.activity_send_data);

		// Initialize References
		initializeReferences();

		// Get Starting Intents Extras
		Bundle gotBasket = getIntent().getExtras();
		// Prevent Errors
		if (gotBasket != null)
		{
			// Get String From Basket
			gotBread = gotBasket.getString("hungerKey");
	
			// Set TextView Label
			question.setText(gotBread);
		}
	}

	private void initializeReferences()
	{
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.btnReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);

		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// Return Intent
		Intent returnIntent = new Intent();
		// Return Bundle
		Bundle returnBundle = new Bundle();
		
		// Set Data to Return
		returnBundle.putString("answer", sendData);
		
		// Add Bundle to Intent
		returnIntent.putExtras(returnBundle);
		
		// Set Result Intent
		setResult(RESULT_OK, returnIntent);
		
		// Close Activity
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		// Determine Which button was Changed & Set Data Accordingly
		switch (checkedId)
		{
		case R.id.radCrazy:
			sendData = "Probably right!";
			break;
		case R.id.radSexy:
			sendData = "Definitely right!";
			break;
		case R.id.radBoth:
			sendData = "Spot On!";
			break;

		default:
			break;
		}

		// Display What is going to be returned
		test.setText(sendData);
	}
}
