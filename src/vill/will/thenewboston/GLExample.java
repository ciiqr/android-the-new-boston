package vill.will.thenewboston;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity
{
	GLSurfaceView glSurface;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Open GL Surface View
		glSurface = new GLSurfaceView(this);
		
		glSurface.setRenderer(new GLRendererEx());
		
		
		// Content View
		setContentView(glSurface);
	}

	@Override
	protected void onPause()
	{
		// Super
		super.onPause();
		
		// Pause Open GL
		glSurface.onPause();
	}

	@Override
	protected void onResume()
	{
		// Super
		super.onResume();
		
		// Resume Open GL
		glSurface.onResume();
	}

	

}
