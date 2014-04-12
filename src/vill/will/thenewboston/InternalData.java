package vill.will.thenewboston;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener
{
	EditText etSharedData;
	TextView tvDataResults;
	FileOutputStream fileOutStream;
	static final String FILENAME = "InternalString";

	// FileInputStream fileInStream;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.activity_shared_preferences);

		initialize();
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

		try
		{
			fileOutStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fileOutStream.close(); // Should be in finally ???
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v)
	{
		//
		switch (v.getId())
		{
		case R.id.btnSave:
			// Retrieve Data
			String data = etSharedData.getText().toString();

			try
			{
				// Open File Out Stream (think ofstream in C++)
				fileOutStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
				// Write Data to Stream
				fileOutStream.write(data.getBytes());
				// Close File Stream
				fileOutStream.close();
			}

			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			catch (IOException e)
			{
				e.printStackTrace();
			}

			// Save Data via File
			// File f = new File(FILENAME);
			// fileOutStream = new FileOutputStream(f);
			// // Write Data
			// fileOutStream.close();

			break;
		case R.id.btnLoad:
			new loadSomeStuff().execute(FILENAME);
			break;

		default:
			break;
		}

	} // onClick

	public class loadSomeStuff extends AsyncTask<String, Integer, String>
	{

		ProgressDialog progDialog;

		protected void onPreExecute()
		{
			// Create Progress Dialog
			progDialog = new ProgressDialog(InternalData.this);

			// Set Style
			progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

			progDialog.setMax(100);

			progDialog.show();
		}

		// Update on current progress
		protected void onProgressUpdate(Integer... progress)
		{
			progDialog.incrementProgressBy(progress[0]);
		}

		// Ran in called thread(Main)? (so it should be safe?)
		protected void onPostExecute(String result)
		{
			tvDataResults.setText(result);
		}

		// Possibly the only method to be called on a different thread
		@Override
		protected String doInBackground(String... params)
		{
			for (int i = 0; i < 20; i++)
			{
				publishProgress(i);

				try
				{
					Thread.sleep(88);
				}

				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			progDialog.dismiss();

			String collected = null;
			FileInputStream fis = null;
			try
			{
				fis = openFileInput(FILENAME);

				byte[] dataArray = new byte[fis.available()];

				// Read Data into dataArray
				while (fis.read(dataArray) != -1)
				{
					collected = new String(dataArray);
				}

			}

			catch (IOException e)
			{
				e.printStackTrace();
			}

			finally
			{
				try
				{
					if (fis != null)
						fis.close();

					// Display Read Data
					return collected;
				}

				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			return null;
		}
	} // loadSomeStuff

} // InternalData
