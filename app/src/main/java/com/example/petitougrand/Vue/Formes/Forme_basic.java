package com.example.petitougrand.Vue.Formes;

import android.opengl.GLES30;

import com.example.petitougrand.Vue.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class Forme_basic {

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

    private int vPMatrixHandle;
    static final int COORDS_PER_VERTEX = 3;
    float formeCoords[] = {};

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
    private short drawOrder[] = {};
    public float echelleRelative;
    private final int mProgram;

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    public Forme_basic(float[] formeCoords, short[] drawOrder, float[] color, float echelleRelative) {

        this.formeCoords = formeCoords;
        this.color = color;
        this.drawOrder = drawOrder;
        this.echelleRelative = echelleRelative;

        // Initialise le buffer des vertex
        ByteBuffer bb = ByteBuffer.allocateDirect(formeCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(formeCoords);
        vertexBuffer.position(0);

        // Initialise le buffer des indexs
        ByteBuffer dlb = ByteBuffer.allocateDirect(drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        // create empty OpenGL ES Program
        mProgram = GLES30.glCreateProgram();

        // Attache le vertexShader
        int vertexShader = MyGLRenderer.loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        GLES30.glAttachShader(mProgram, vertexShader);

        // Attache le fragmentShader
        int fragmentShader = MyGLRenderer.loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);
        GLES30.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES30.glLinkProgram(mProgram);
    }

    private int positionHandle;
    private int colorHandle;
    private final int vertexCount = formeCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // le pas entre 2 sommets : 4 bytes per vertex

    public void draw(float[] mvpMatrix) {
        GLES30.glUseProgram(mProgram);

        // Récupère la position du shader
        positionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition");
        GLES30.glEnableVertexAttribArray(positionHandle);
        GLES30.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES30.GL_FLOAT, false, vertexStride, vertexBuffer);

        /// Recupère la couleur du shader et l'applique
        colorHandle = GLES30.glGetUniformLocation(mProgram, "vColor");
        GLES30.glUniform4fv(colorHandle, 1, color, 0);

        // Récupère et applique la transformation
        vPMatrixHandle = GLES30.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES30.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);

        // Set color for drawing the triangle
        GLES30.glUniform4fv(colorHandle, 1, color, 0);

        // Dessine le carré
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, drawOrder.length, GLES30.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(positionHandle);
    }
}
