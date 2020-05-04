package com.example.petitougrand;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import Formes.Carré;
import Formes.Croix;
import Formes.Gemme;
import Formes.Rubis;
import Formes.Triangle;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Map<String, Objet> objetsScenes;

    // Matrices Model/View/Projection
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    //Appelé lors du setup de la l'environnement
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        //Initialise les formes
        objetsScenes = new HashMap<String, Objet>();

        //Les références

        //On crée le haut de la pile et la carte cachée
        Objet obj_1 = new Objet(new Croix(), -0.3f, 0.0f, 0.2f);
        objetsScenes.put("Caché", obj_1);
        Objet obj_2 = new Objet(new Triangle(), -0.0f, 0.0f, 0.05f);
        objetsScenes.put("Réference", obj_2);

        //Les boutons
        Objet obj_3 = new Objet(new Carré(), -0.3f, -0.8f, 0.10f);
        objetsScenes.put("Inférieur", obj_3);
        Objet obj_4 = new Objet(new Carré(), 0.0f, -0.8f, 0.10f);
        objetsScenes.put("Egal", obj_4);
        Objet obj_5 = new Objet(new Carré(), 0.3f, -0.8f, 0.10f);
        objetsScenes.put("Supérieur", obj_5);
    }

    //Appelé à chaque frame
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //Deplacement des objets de la scene
        float[] scratch;

        for (String nom : objetsScenes.keySet()) {
            Objet item = objetsScenes.get(nom);

            //Matrice de déplacement
            scratch = new float[16];
            Matrix.setIdentityM(mModelMatrix, 0);
            Matrix.translateM(mModelMatrix, 0, item.getPosition()[0], item.getPosition()[1], 0);
            Matrix.multiplyMM(scratch, 0, vPMatrix, 0, mModelMatrix, 0);

            //Scale
            Matrix.scaleM(scratch, 0, item.getEchelle()[0], item.getEchelle()[1], 1);

            //Dessin
            item.draw(scratch);
        }
    }

    //Appelé quand la view change aka on tourne le téléphone
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    //Fonction utilitaire pour charger les shaders
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    //Responasble de gérer les inputs
    //Gere mal quand on tourne l'écran
    //L'écriture en dur est loin d'être optimale mais c'est juste une preuve qu'on sait prendre des inputs
    public void onInput(float x, float y) {

        if(y < -7 && y > -9){
            //Log.d("BOUTONS", x + "");

            //Moins
            if(x > -7.0 && x < -4.0){
                Log.d("BOUTONS", " THE MINUS HAVE BEEN PRESSED " + x + " " + y );
            }

            //Egal
            if(x > -1.5 && x < 1.5){
                Log.d("BOUTONS", " EQUALITY HAVE BEEN ACHIEVED " + x + " " + y );
            }

            //Plus
            if(x > 4.0 && x < 7.0){
                Log.d("BOUTONS", " MORE IS THE ONLY WAY " + x + " " + y );
            }
        }
    }
}
