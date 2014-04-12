package vill.will.thenewboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class MyBringBack extends View
{
	/* Declarations */
	Bitmap gBall;
	float changingY;
	Typeface font;

	public MyBringBack(Context context)
	{
		// Super
		super(context);

		// Setup gBall
		gBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.green_shape);
		
		changingY = 0;
		
		font = Typeface.createFromAsset(context.getAssets(), "Quivira.ttf");
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// Super
		super.onDraw(canvas);

		// Set Drawing Colour
		canvas.drawColor(Color.WHITE);
		
		// Font
		Paint textPaint = new Paint();
		textPaint.setARGB(50, 254, 10, 150);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("My Bring Back!!", canvas.getWidth()/2, canvas.getHeight()/2, textPaint);
		
		
		// Draw Bitmap
		canvas.drawBitmap(gBall,
				(canvas.getWidth() / 2 - gBall.getWidth() / 2),
				 changingY, null); //(canvas.getHeight() / 2 - gBall.getHeight() / 2) +
		
		// Change Y
		if (changingY > canvas.getHeight())
			changingY = 0;
		else
			changingY += 10;
		
		// 
		Rect middleRect = new Rect();
		middleRect.set(0, (canvas.getHeight() / 2 + 20) , canvas.getWidth(), (canvas.getHeight() / 2 + 150));
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		
		// Invalidate
		invalidate();
	}

	public MyBringBack(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyBringBack(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

}
