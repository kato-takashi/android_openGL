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

        renderMain(gl);
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

    //描画を行う部分の記述するメソッドを追加
    public void renderMain(GL10 gl){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                float brightness = (i+j)%2;
                GraphicUtil.drawRectangle(gl,
                        (float) i * 0.4f - 0.8f,
                        (float) j * 0.4f - 0.8f,
                        0.4f, 0.4f,
                        brightness, brightness, brightness, 1.0f);
            }

        }
    }

}
