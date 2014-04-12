package vill.will.thenewboston;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx
{
	// Declarations
	
	private float[] vertices =
	{//  @formatter:off
		 0f,  1f,
		 1f, -1f,
		-1f, -1f
	};// @formatter:on

	private FloatBuffer vertBuff;

	private short[] pIndex =
	{ 0, 1, 2 };
	
	private ShortBuffer pBuff;

	// Methods
	
	public GLTriangleEx()
	{
		// Create Byte Buffer
		ByteBuffer byteBuff = ByteBuffer.allocateDirect(vertices.length * 4);

		// Order Byte Buffer by Native Order
		byteBuff.order(ByteOrder.nativeOrder());

		// Get FloatBuffer from Byte Buffer
		vertBuff = byteBuff.asFloatBuffer();

		// Passes Vertices
		vertBuff.put(vertices);

		// Set Initial Position
		vertBuff.position(0);

		
		
		ByteBuffer pbBuffer = ByteBuffer.allocateDirect(pIndex.length * 2);
		
		pbBuffer.order(ByteOrder.nativeOrder());
		
		pBuff = pbBuffer.asShortBuffer();
		
		pBuff.put(pIndex);
		
		pBuff.position(0);
	}
	
	
	public void draw(GL10 gl)
	{
		// Clock Wise...
		gl.glFrontFace(GL10.GL_CW);
		
		//
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// 
		gl.glVertexPointer(2/*2D*/, GL10.GL_FLOAT, 0, vertBuff);
		
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		
		
		//
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}














