package vill.will.thenewboston;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.SystemClock;

public class GLRendererEx implements Renderer
{
	private GLCube cube;
	
	public GLRendererEx()
	{
		cube = new GLCube();
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// 
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		
		// Clear Colour
		gl.glClearColor(.8f, 0f, .2f, 1);
		
		// Clear Depth
		gl.glClearDepthf(1f);
		
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// Improve Performance
		gl.glDisable(GL10.GL_DITHER);
		
		// 
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 2, 0); // OMG 10 Parameters!!
		
		
		// Time (For Rotating)
		long time = SystemClock.uptimeMillis() % 1000L;
		
		float angle = .090f * ((int)time);
		
		// Rotate Cube
		gl.glRotatef(angle, 1, 0, 2);
		
		//
		cube.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// 
		gl.glViewport(0, 0, width, height);
		
		float ratio = (float) width / height;
		
		//
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		//
		gl.glLoadIdentity();
		
		// Render Distance
		gl.glFrustumf(-ratio, ratio, -1, 1f, 1, 25);
	}

}
