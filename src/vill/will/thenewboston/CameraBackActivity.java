package vill.will.thenewboston;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CameraBackActivity extends Activity implements
		View.OnClickListener
{
	/* Declarations */
	ImageButton ibTakePicture;
	Button btnSetWallpaper;
	ImageView ivReturnedPicture;
	Intent captureImageIntent;
	final static int CAMERA_DATA = 0;
	Bitmap imageData;

	/* Methods */
	public CameraBackActivity()
	{
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Setup with Layout
		setContentView(R.layout.activity_camera_back);

		// Initialize Views & Event Handlers
		initialize();
		
		// Initialize BitMap (with Splash Screen Image)
		InputStream iSInitialImageData = getResources().openRawResource(R.drawable.splash_background_crop);
		imageData = BitmapFactory.decodeStream(iSInitialImageData);
	}

	private void initialize()
	{
		// Retrieve References
		ivReturnedPicture = (ImageView) findViewById(R.id.ivReturnedPicture);
		ibTakePicture = (ImageButton) findViewById(R.id.ibTakePicture);
		btnSetWallpaper = (Button) findViewById(R.id.btnSetWallpaper);

		// Setup Event Handlers
		btnSetWallpaper.setOnClickListener(this);
		ibTakePicture.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// Determine Which Button Was Clicked
		switch (v.getId())
		{
		case R.id.btnSetWallpaper:
			try
			{
				WallpaperManager wallpaperManager = WallpaperManager
						.getInstance(this);
				// Drawable wallpaperDrawable = wallpaperManager.getDrawable();
				wallpaperManager.setBitmap(imageData);

			} catch (IOException e)
			{
				e.printStackTrace();
			}
			break;

		case R.id.ibTakePicture:
			// Create Camera Capture Intent
			captureImageIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			// Display Activity
			startActivityForResult(captureImageIntent, CAMERA_DATA);
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
			if (requestCode == CAMERA_DATA)
			{
				// Get Info From Sending Activity
				Bundle extras = data.getExtras();

				// Get Image Data From Sending Activity's Info
				imageData = (Bitmap) extras.get("data");

				// Set Image
				ivReturnedPicture.setImageBitmap(imageData);
			}
			else
				System.out.println("Recieved Unknown Activity Result: " + resultCode);
		}
	}

}
