package com.example.petitougrand;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import Boutons.Bouton;
import Boutons.Bouton_Egal;
import Boutons.Bouton_Inférieur;
import Boutons.Bouton_Supérieur;
import Formes.Carré;
import Formes.Croix;
import Formes.Gemme;
import Formes.Rubis;
import Formes.Triangle;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Map<String, Objet> objetsScenes;
    private List<Bouton> boutonsScenes;

    // Matrices Model/View/Projection
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    //Appelé lors du setup de la l'environnement
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        //Initialise les formes
        objetsScenes = new HashMap<String, Objet>();
        boutonsScenes = new ArrayList<>();

        //Les références

        //On crée le haut de la pile et la carte cachée
        Objet obj_1 = new Objet(new Carré(), 0.0f, 0.0f, 1f);
        objetsScenes.put("Caché", obj_1);
        Objet obj_2 = new Objet(new Carré(), 2.0f, 2.0f, 1f);
        objetsScenes.put("Réference", obj_2);

        //Les boutons
        Bouton obj_3 = new Bouton_Inférieur(new Carré(), -3.5f, -7.0f, 1.0f);
        objetsScenes.put("Inférieur", obj_3);
        boutonsScenes.add(obj_3);
        Bouton obj_4 = new Bouton_Egal(new Carré(), 0.0f, -7.0f, 1.0f);
        objetsScenes.put("Egal", obj_4);
        boutonsScenes.add(obj_4);
        Bouton obj_5 = new Bouton_Supérieur(new Carré(), 3.5f, -7.0f, 1.0f);
        objetsScenes.put("Supérieur", obj_5);
        boutonsScenes.add(obj_5);
    }

    //Appelé à chaque frame
    public void onDrawFrame(GL10 unused) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setIdentityM(viewMatrix,0);

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
        GLES30.glViewport(0, 0, width, height);
        float ratio = (float) width / height;

        Matrix.orthoM(projectionMatrix, 0, -10.0f * ratio, 10.0f * ratio, -10.0f, 10.0f, -1.0f, 1.0f);
    }

    //Fonction utilitaire pour charger les shaders
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES30.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES30.GL_FRAGMENT_SHADER)
        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

    //Responasble de gérer les inputs
    //Gere mal quand on tourne l'écran
    public void onInput(float x, float y) {

        Log.d("BOUTONS", "x: " + x + " y: " + y);

        for (Bouton item: boutonsScenes) {
            item.Action(x, y);
        }
    }
}
