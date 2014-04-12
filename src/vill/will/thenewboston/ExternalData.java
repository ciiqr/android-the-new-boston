package vill.will.thenewboston;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnClickListener,
		OnItemSelectedListener
{
	private TextView canWrite, canRead;
	private String state;
	Boolean canW, canR;
	Spinner spinner;
	String[] paths =
	{ "Music", "Pictures", "Downloads" };
	File path = null;
	File file = null;

	EditText etSaveFile;
	Button btnConfirm, btnSave;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Content View
		setContentView(R.layout.activity_external_data);

		// References
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);

		// Checks the SD Card State
		checkState();

		// Get Spinner
		spinner = (Spinner) findViewById(R.id.spinner1);

		// Create Array Adapter with Paths
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, paths);

		// Set Spinner Adapter
		spinner.setAdapter(adapter);

		// Setup Event Handler
		spinner.setOnItemSelectedListener(this);

		// Button References
		btnConfirm = (Button) findViewById(R.id.btnConfirmSaveAs);
		btnSave = (Button) findViewById(R.id.btnSaveFile);

		// Button Event Handlers
		btnConfirm.setOnClickListener(this);
		btnSave.setOnClickListener(this);
		
		// Edit Text
		etSaveFile = (EditText) findViewById(R.id.etSaveAs);

	}

	private void checkState()
	{
		// Get External State
		state = Environment.getExternalStorageState();
		// Determine State
		if (state.equals(Environment.MEDIA_MOUNTED))
		{
			// Read & Write
			canWrite.setText("true");
			canRead.setText("true");
			canW = true;
			canR = true;
		}

		else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
			// Read Only
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		}

		else
		{
			// CANNOT READ OR WRITE!
			canWrite.setText("false");
			canRead.setText("false");
			canW = false;
			canR = false;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btnConfirmSaveAs:

			btnSave.setVisibility(View.VISIBLE);
			break;

		case R.id.btnSaveFile:
			// Get File Name
			String fileName = etSaveFile.getText().toString();

			// Create File With Selected Path & File Name
			file = new File(path, fileName);

			// Check Sd State
			checkState();

			if (canW && canR)
			{
				// Check if directory & its parents Exist & Create if They Don't
				path.mkdirs();
				
				try
				{
					// Stream Which Will Be Saved (Based On Green Shape
					// Resource)
					InputStream inputStream = getResources().openRawResource(
							R.drawable.green_shape);

					// File Output Stream
					OutputStream outputStream = new FileOutputStream(file);

					// Byte Array Size of Input Stream
					byte[] data = new byte[inputStream.available()];
					
					// Read From Input Stream to Data Array
					inputStream.read(data);
					
					// Write Data From Data Array to File
					outputStream.write(data);
					
					// Close Streams
					inputStream.close();
					outputStream.close();
					
					
					// Toast, Notification (Small Text at the bottom of the screen (Think "Press Back Again to Exit" ))
					Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					// Update Files (So User Can Access Them)
					MediaScannerConnection.scanFile(ExternalData.this,
							new String[]
							{ file.toString() }, null,
							new OnScanCompletedListener()
					{
						@Override
						public void onScanCompleted(String path, Uri uri)
						{
							// 
							Toast.makeText(ExternalData.this, "Scan Compelte", Toast.LENGTH_LONG).show();
						}
					});
					
					
					// Rehide Save Button
					btnSave.setVisibility(View.INVISIBLE);
				}

				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;

		default:
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
	{
		//
		switch (pos)
		{
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;

		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;

		default:
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
	}
}
