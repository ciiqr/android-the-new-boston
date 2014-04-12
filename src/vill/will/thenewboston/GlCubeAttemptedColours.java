//package vill.will.thenewboston;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.FloatBuffer;
//import java.nio.ShortBuffer;
//
//import javax.microedition.khronos.opengles.GL10;
//
//public class GLCube
//{
//	// Declarations
//
//	private float[] vertices =
//	{//  @formatter:off
//		  1,  1, -1, // Front
//		  1, -1, -1,
//		 -1, -1, -1,
//		 -1,  1, -1,
//
//		  1,  1,  1, // Back
//		  1, -1,  1,
//		 -1, -1,  1,
//		 -1,  1,  1
//	};// @formatter:on
//	
//	private float[] rgbaVals =
//	{//  @formatter:off
//		  1   , 1,    0, .5f,
//		  .25f, 0, .85f,   1,
//		  1   , 1,    0, .5f,
//		  .50f, .25f, 1,   1,
//		  
//		  0, 0, 1, 1,
//		  0, 1, 0, 1,
//		  1, 0, 0, 1,
//		  1, 0, 1, 1
//	};// @formatter:on
//
//	private FloatBuffer vertBuff, colourBuff;
//
//	private short[] pIndex =
//	{//  @formatter:off
//		3, 4, 0,  0, 4, 1,  3, 0, 1,
//		3, 7, 4,  7, 6, 4,  7, 3, 6,
//		3, 1, 2,  1, 6, 2,  6, 3, 2,
//		1, 4, 5,  5, 6, 1,  6, 5, 4
//	}; // @formatter:on
//
//	private ShortBuffer pBuff;
//
//	// Methods
//
//	public GLCube()
//	{
//		// Create Byte Buffer
//		ByteBuffer byteBuff = ByteBuffer.allocateDirect(vertices.length * 4);
//		// Order Byte Buffer by Native Order
//		byteBuff.order(ByteOrder.nativeOrder());
//		// Get FloatBuffer from Byte Buffer
//		vertBuff = byteBuff.asFloatBuffer();
//		// Passes Vertices
//		vertBuff.put(vertices);
//		// Set Initial Position
//		vertBuff.position(0);
//
//		ByteBuffer pbBuffer = ByteBuffer.allocateDirect(pIndex.length * 2);
//		pbBuffer.order(ByteOrder.nativeOrder());
//		pBuff = pbBuffer.asShortBuffer();
//		pBuff.put(pIndex);
//		pBuff.position(0);
//		
//		
//		
//		ByteBuffer cBuffer = ByteBuffer.allocateDirect(rgbaVals.length * 2);
//		cBuffer.order(ByteOrder.nativeOrder());
//		colourBuff = cBuffer.asFloatBuffer();
//		colourBuff.put(rgbaVals);
//		colourBuff.position(0);
//	}
//
//	public void draw(GL10 gl)
//	{
//		// Clock Wise...
//		gl.glFrontFace(GL10.GL_CW);
//		// Dont Render Back Side
//		gl.glEnable(GL10.GL_CULL_FACE);
//		gl.glCullFace(GL10.GL_BACK);
//		
//		//
//		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//		
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
//		
//		//
//		gl.glVertexPointer(3/*3D*/, GL10.GL_FLOAT, 0, vertBuff);
//		// IDFucking Know Anymore
//		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colourBuff);
//		
//		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length,
//				GL10.GL_UNSIGNED_SHORT, pBuff);
//		
//		
//		// Restore State
//		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
//		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//		//
//		gl.glDisable(GL10.GL_CULL_FACE);
//	}
//
//}
