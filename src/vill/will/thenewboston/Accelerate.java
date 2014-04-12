package vill.will.thenewboston;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener
{
	MyBringBackSurface ourSurfaceView;
	float x, y, sensorX, sensorY;
	Bitmap shape;
	SensorManager sensorManager;
	Sensor accelSensor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		ourSurfaceView = new MyBringBackSurface(this);
		setContentView(ourSurfaceView);

		// Get Sensor Service
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// Get Sensor List
		List<Sensor> sensorList = sensorManager
				.getSensorList(Sensor.TYPE_ACCELEROMETER);
		// Check for Accelerometer Sensor
		if (sensorList.size() != 0)
		{
			accelSensor = sensorList.get(0);
			sensorManager.registerListener(this, accelSensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		// Shape
		shape = BitmapFactory.decodeResource(getResources(),
				R.drawable.green_shape);

		x = y = sensorX = sensorY = 0;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// Get Sensor Values
		sensorX = event.values[0];
		sensorY = event.values[1];
	}

	@Override
	protected void onPause()
	{
		// Super
		super.onPause();

		// Pause View
		ourSurfaceView.pause();

		// Stop Accelerometer Updates
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume()
	{
		// Super
		super.onResume();

		// Resume View
		ourSurfaceView.resume();

		// Resume Accelerometer Updates (Only if we have a sensor)
		if (accelSensor != null)
			sensorManager.registerListener(this, accelSensor,
					SensorManager.SENSOR_DELAY_NORMAL);
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable
	{
		/* Declarations */
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public MyBringBackSurface(Context context)
		{
			// Super
			super(context);

			// Get Holder
			ourHolder = getHolder();

			// Create Thread
			ourThread = new Thread(this);

			// Start Thread
			ourThread.start();
		}

		public void pause()
		{
			isRunning = false;

			while (true)
			{
				// Try to Join Thread
				try
				{
					ourThread.join();
				} catch (InterruptedException e)
				{
					// Print Exception
					e.printStackTrace();
				}

				// Once Here Thread is Done
				break;
			}

			// Discard Thread
			ourThread = null;
		}

		public void resume()
		{
			isRunning = true;

			// Create Thread
			ourThread = new Thread(this);

			// Start Thread
			ourThread.start();
		}

		@Override
		public void run()
		{
			//
			while (isRunning)
			{
				// Skip Until Valid
				if (!ourHolder.getSurface().isValid())
					continue;

				// Get Locked Canvas
				Canvas canvas = ourHolder.lockCanvas();

				// Draw Background
				canvas.drawRGB(2, 2, 150);

				//
				float centerX = canvas.getWidth() / 2;
				float centerY = canvas.getHeight() / 2;

				// Draw Bitmap
				canvas.drawBitmap(shape, centerX + (-sensorX) * 40, centerY
						+ (sensorY) * 40, null);

				// UnLock Canvas
				ourHolder.unlockCanvasAndPost(canvas);

			}
		}

	}

}
