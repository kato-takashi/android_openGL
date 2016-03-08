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

    //円を作成するためのメソッド
    public static final void drawCircle(GL10 gl,
                                        float x, float y,
                                        int divides, float radius,
                                        float r, float g, float b, float a){
        float[] vertices = new float[divides*3*2];
        int vertexId = 0; //頂点配列の様相の番号を記憶しておくための変数
        for(int i=0; i<divides; i++){
            //円周上のi番目の頂点角度(ラジアン)を計算する
            float theta1 = 2.0f / (float)divides * (float) i * (float) Math.PI;
            //円周上のi+1番目の頂点角度(ラジアン)を計算する
            float theta2 = 2.0f / (float)divides * (float) (i+1) * (float) Math.PI;
            //i番目の三角形の0番目の頂点の情報をセットする
            vertices[vertexId++] = x;
            vertices[vertexId++] = y;
            //i番目の三角形の1番目の頂点の情報をセットする(円周上のi番目の頂点)
            vertices[vertexId++] = (float) Math.cos((double)theta1) * radius + x; //x座標
            vertices[vertexId++] = (float) Math.sin((double) theta1) * radius + y; //y座標

            //i番目の三角形の2番目の頂点の情報をセットする(円周上のi+1番目の頂点)
            vertices[vertexId++] = (float) Math.cos((double)theta2) * radius + x; //x座標
            vertices[vertexId++] = (float) Math.sin((double) theta2) * radius + y; //y座標
        }
        FloatBuffer polygonVewrtices = makeFloatBuffer(vertices);
        //ポリゴンの色をして知る
        gl.glColor4f(r, g, b, a);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, polygonVewrtices);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, divides*3);
    }
}
