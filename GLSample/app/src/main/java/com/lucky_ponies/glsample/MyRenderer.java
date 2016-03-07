package com.lucky_ponies.glsample;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by katotakashi on 16/03/07.
 */
public class MyRenderer implements GLSurfaceView.Renderer {
    private int mWidth;
    private int mHeight;


    @Override
    public void onDrawFrame(GL10 gl) {
        //描画処理を記述する
        gl.glViewport(0, 0, mWidth, mHeight);
        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();
        gl.glOrthof(-1.0f, 1.0f, -1.5f, 1.5f, 0.5f, -0.5f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        float[] vertices = {
                -0.5f, -0.5f, //頂点1
                 0.5f, -0.5f, //頂点2
                -0.5f,  0.5f, //頂点3
                 0.5f,  0.5f, //頂点4
        };

        float[] colors = {
//                1.0f, 1.0f, 0.0f, 1.0f, // R, G, B, alpha
//                0.0f, 1.0f, 1.0f, 1.0f,
//                0.0f, 0.0f, 0.0f, 0.0f,
//                1.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 0.0f, 0.0f, 1.0f, // R, G, B, alpha
                0.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f, 1.0f,
        };

        FloatBuffer polygonVertices = makeFloatBuffer(vertices);
        FloatBuffer polygonColors = makeFloatBuffer(colors);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonVertices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, polygonColors);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);



    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //画面生成時の画面向き変更時に呼び出される。初期化処理などを行う

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //画面生成時、画面向き変更時に呼び出される。
        this.mWidth = width;
        this.mHeight = height;
    }

    //システム上のメモリ領域を確保するためのメソッド
    public static final FloatBuffer makeFloatBuffer(float[] arr){
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }

}
