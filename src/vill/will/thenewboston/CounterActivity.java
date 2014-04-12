package vill.will.thenewboston;

import java.io.File;

import vill.will.thenewboston.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CounterActivity extends Activity
{
	/* Declarations */
	private int counter = 0;
	private Button btnAdd;
	private Button btnSub;
	private TextView displayText;
	
	/* Methods */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Test Directory API
		testDirectories();
		
		// Setup
		// Views
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnSub = (Button) findViewById(R.id.btnSub);
		displayText = (TextView) findViewById(R.id.displayText);
		btnAdd.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				displayText.setText("Your total is " + (++counter) );
			}
		});
		btnSub.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				displayText.setText("Your total is " + (--counter) );
			}
		});
		
		btnAdd.setOnLongClickListener(new View.OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View view)
			{
				// Reset Text
				displayText.setText(getResources().getString(R.string.displayTextLabel));
				
				// Reset Counter
				counter = 0;
				
				Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				// Vibrate for 500 milliseconds
				try
				{
					vibrator.vibrate(100);
					Thread.sleep(100);
					vibrator.vibrate(100);
					Thread.sleep(100);
					
				} catch (InterruptedException e){
					
					e.printStackTrace();
				}
//				vibrator.vibrate(500);
				
				// Successful
				return true;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void testDirectories()
	{
		File files[] = Environment.getExternalStorageDirectory().listFiles();

		for (File file : files)
		{
			if (file.isDirectory())
				System.out.println("Directory: " + file.getPath());
			else
				System.out.println("     File: " + file.getPath());
		}
	}
}
