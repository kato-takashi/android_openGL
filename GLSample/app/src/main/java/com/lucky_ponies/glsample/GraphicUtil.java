package com.lucky_ponies.glsample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by katoTakashi on 16/03/07.
 */
public class GraphicUtil {
    //システム上のメモリ領域を確保するためのメソッド
    public static final FloatBuffer makeFloatBuffer(float[] arr){
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }

    //ポリゴンを描画するメソッド
    public static final void drawSquare(GL10 gl, float r, float g, float b, float a){
        float[] vertices = {
                -0.5f, -0.5f, //頂点1
                0.5f, -0.5f, //頂点2
                -0.5f,  0.5f, //頂点3
                0.5f,  0.5f, //頂点4
        };

        float[] colors = {
                r, g, b, a, // R, G, B, alpha
                r, g, b, a,
                r, g, b, a,
                r, g, b, a,
        };

        FloatBuffer squareVertices = makeFloatBuffer(vertices);
        FloatBuffer squareColors = makeFloatBuffer(colors);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, squareVertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, squareColors);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }

    public static final void drawSquare(GL10 gl){
        drawSquare(gl, 1.0f, 0.0f, 0.0f, 1.0f);
    }

    public static final void drawRectangle(GL10 gl,
                                           float x, float y,
                                           float width, float height,
                                           float r, float g, float b, float a){

        float[] vertices = {
                -0.5f* width+x, -0.5f* height+y, //頂点1
                0.5f* width+x, -0.5f* height+y, //頂点2
                -0.5f* width+x, 0.5f* height+y, //頂点3
                0.5f* width+x, 0.5f* height+y, //頂点4
        };

        float[] colors = {
                r, g, b, a, // R, G, B, alpha
                r, g, b, a,
                r, g, b, a,
                r, g, b, a,
        };

        FloatBuffer squareVertices = makeFloatBuffer(vertices);
        FloatBuffer squareColors = makeFloatBuffer(colors);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, squareVertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, squareColors);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }
}
