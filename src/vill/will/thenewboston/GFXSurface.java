package vill.will.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener
{
	MyBringBackSurface ourSurfaceView;
	float x = 0, y = 0, startX = 0, startY = 0, endX = 0, endY = 0;
	float dX = 0, dY = 0, aniX = 0, aniY = 0, scaledX = 0, scaledY = 0;
	Bitmap bitmap, plus;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Create View
		ourSurfaceView = new MyBringBackSurface(this);

		// Add Touch Listener
		ourSurfaceView.setOnTouchListener(this);

		// Setup Bitmaps
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.green_shape);
		plus = BitmapFactory.decodeResource(getResources(),
				R.drawable.purple_shape);

		// Set Content View
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();

		// Pause View
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();

		// Resume View
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		// Sleep to set Frame Rate
		try
		{
			Thread.sleep(50);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get Coordinates
		x = event.getX();
		y = event.getY();

		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();

			// Reset Values
			dX = dY = aniX = aniY = scaledX = scaledY = endX = endY = 0;

			break;

		case MotionEvent.ACTION_UP:
			endX = event.getX();
			endY = event.getY();

			dX = endX - startX;
			dY = endY - startY;

			// Scale
			scaledX = dX / 30;
			scaledY = dY / 30;

			// Reset Values (So it can be performed again)
			x = 0;
			y = 0;

			break;

		default:
			break;
		}

		// Return false & it only calls once, true and it's continuous
		return true;
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

				if (x != 0 && y != 0)
				{
					canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y
							- (bitmap.getHeight() / 2), null);
				}

				if (startX != 0 && startY != 0)
				{
					canvas.drawBitmap(plus, startX - (plus.getWidth() / 2),
							startY - (plus.getHeight() / 2), null);
				}

				if (endX != 0 && endY != 0)
				{
					canvas.drawBitmap(bitmap, endX - (bitmap.getWidth() / 2)
							- aniX, endY - (bitmap.getHeight() / 2) - aniY, null);
					canvas.drawBitmap(plus, endX - (plus.getWidth() / 2), endY
							- (plus.getHeight() / 2), null);
				}
				aniX += scaledX;
				aniY += scaledY;

				// UnLock Canvas
				ourHolder.unlockCanvasAndPost(canvas);

			}
		}

	}

}
