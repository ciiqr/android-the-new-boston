package vill.will.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DataActivity extends Activity implements OnClickListener
{

	/* Declarations */
	Button start, startFor;
	EditText sendET;
	TextView gotAnswer;
	
	final static int CRAZY_SEXY = 1337;

	/* Methods */

	public DataActivity()
	{
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Initialize View With Layout
		setContentView(R.layout.activity_recieve_data);

		// Initialize References
		initializeReferences();
	}

	private void initializeReferences()
	{
		start = (Button) findViewById(R.id.btnSA);
		startFor = (Button) findViewById(R.id.btnSAForResult);
		sendET = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);

		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		//
		switch (v.getId())
		{
		case R.id.btnSA:
			// Retrieve Data to Send
			String bread = sendET.getText().toString();
			
			// Create Bundle
			Bundle basket = new Bundle();
			
			// Add Data to Bundle
			basket.putString("hungerKey", bread);
			
			// Create Intent
			Intent sendDataIntent = new Intent(DataActivity.this, OpenedClass.class);
			
			// Add Bundle to Intent
			sendDataIntent.putExtras(basket);
			
			// Start Intent Activity
			startActivity(sendDataIntent);
			
			break;
			
		case R.id.btnSAForResult:
			// Create Intent
			Intent i = new Intent(DataActivity.this, OpenedClass.class);
			
			// Start Intent Activity
			startActivityForResult(i, CRAZY_SEXY);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// Super
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK)
		{
			switch (requestCode)
			{
			case CRAZY_SEXY:
				// Retrieve Extras Bundle
				Bundle basket = data.getExtras();
				
				System.out.println("Before Get answer with basket: " + basket);
				
				// Retrieve Answer/Result
				String result = basket.getString("answer");
				
				System.out.println("After Get answer with basket: " + basket);
				
				// Display Result
				gotAnswer.setText(result);
				
				break;

			default:
				break;
			}
		}
	}
	
	
	
	
}// DataActivity
