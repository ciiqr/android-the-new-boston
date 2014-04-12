package vill.will.thenewboston;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CommandEntry extends Activity implements View.OnClickListener
{
	Button btnResults;
	ToggleButton tBPassword;
	EditText edTxtCommands;
	TextView tVDisplay;

	public CommandEntry() {}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Setup With Layout
		setContentView(R.layout.command_entry);

		// Setup References
		btnResults = (Button) findViewById(R.id.btnResults);
		tBPassword = (ToggleButton) findViewById(R.id.tBPassword);
		edTxtCommands = (EditText) findViewById(R.id.edTxtCommands);
		tVDisplay = (TextView) findViewById(R.id.tVDisplay);

		// Event Handlers
		tBPassword.setOnClickListener(this);
		btnResults.setOnClickListener(this);
		
		
	} // OnCreate
	
	@Override
	public void onClick(View v)
	{
		// Determine Clicked View 
		switch (v.getId())
		{
		case R.id.btnResults:
			// Retrieve Text from 
			String inputCommand = edTxtCommands.getText().toString();
			
			// Display Input
			tVDisplay.setText(inputCommand);
			
			// Gravity
			if 		(inputCommand.equalsIgnoreCase("left"))
				tVDisplay.setGravity(Gravity.LEFT);
			
			else if (inputCommand.equalsIgnoreCase("center"))
				tVDisplay.setGravity(Gravity.CENTER);
			
			else if (inputCommand.equalsIgnoreCase("right"))
				tVDisplay.setGravity(Gravity.RIGHT);
			
			// Colours
			else if (inputCommand.equalsIgnoreCase("blue"))
				tVDisplay.setTextColor(Color.BLUE);
			
			else if (inputCommand.equalsIgnoreCase("what"))
			{
				Random rand = new Random();
				
				// Set Text Properties
				tVDisplay.setText("WHAT!!!!");
				tVDisplay.setTextSize(rand.nextInt(75));
				tVDisplay.setTextColor(Color.rgb(rand.nextInt(256),   //red
												 rand.nextInt(256),   //green
												 rand.nextInt(256))); // blue
				
				switch (rand.nextInt(3))
				{
				case 1:
					tVDisplay.setGravity(Gravity.LEFT);
					break;
					
				case 2:
					tVDisplay.setGravity(Gravity.CENTER);
					break;
					
				case 3:
					tVDisplay.setGravity(Gravity.RIGHT);
					break;

				default:
					break;
				}
			}
			
			// Default (Invalid)
			else
			{
				tVDisplay.setText(R.string.tVDisplay_Text);
				tVDisplay.setGravity(Gravity.CENTER);
			}
			break;
			
		case R.id.tBPassword:
			// Toggle Password
			if (tBPassword.isChecked())
				edTxtCommands.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			else
				edTxtCommands.setInputType(InputType.TYPE_CLASS_TEXT);
			break;

		default:
			break;
		}
	} // onClick
	
} // CommandEntry
