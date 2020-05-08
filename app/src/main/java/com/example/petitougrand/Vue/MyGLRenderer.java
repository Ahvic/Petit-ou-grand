package com.example.petitougrand.Vue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.petitougrand.Modèle.Boutons.Bouton;
import com.example.petitougrand.Modèle.Boutons.Bouton_Egal;
import com.example.petitougrand.Modèle.Boutons.Bouton_Inférieur;
import com.example.petitougrand.Modèle.Boutons.Bouton_Supérieur;
import com.example.petitougrand.Modèle.Formes.Carré;
import com.example.petitougrand.Modèle.Formes.Croix;
import com.example.petitougrand.Modèle.Objet;

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
        Objet obj_1 = new Objet(new Carré(), 0.0f, 0.0f);
        objetsScenes.put("Caché", obj_1);
        Objet obj_2 = new Objet(new Croix(), 2.0f, 2.0f);
        objetsScenes.put("Réference", obj_2);

        //Les boutons
        float couleur_inférieur[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
        float couleur_egal[] = { 1.00000000f, 1.00000000f, 0.00000000f, 1.0f };
        float couleur_supérieur[] = { 0.00000000f, 1.00000000f, 0.00000000f, 1.0f };

        Bouton bouton_inférieur = new Bouton_Inférieur(new Carré(couleur_inférieur), -3.5f, -7.0f, 1.0f);
        Bouton bouton_egal = new Bouton_Egal(new Carré(couleur_egal), 0.0f, -7.0f, 1.0f);
        Bouton bouton_supérieur = new Bouton_Supérieur(new Carré(couleur_supérieur), 3.5f, -7.0f, 1.0f);

        objetsScenes.put("Inférieur", bouton_inférieur);
        objetsScenes.put("Egal", bouton_egal);
        objetsScenes.put("Supérieur", bouton_supérieur);

        boutonsScenes.add(bouton_inférieur);
        boutonsScenes.add(bouton_egal);
        boutonsScenes.add(bouton_supérieur);
    }

    private boolean input = false;

    //Appelé à chaque frame
    public void onDrawFrame(GL10 unused) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setIdentityM(viewMatrix,0);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        if(input){
            Objet obj_3 = new Objet(new Croix(), -2.0f, 2.0f);
            objetsScenes.put("Réference", obj_3);
            input = false;
        }

        //Deplacement des objets de la scene
        float[] scratch;

        for (String nom : objetsScenes.keySet()) {
            Log.e("SPAWN", "nb objet: " + objetsScenes.size());

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

        //Log.d("BOUTONS", "x: " + x + " y: " + y);

        //for (Bouton item: boutonsScenes) {
        //    item.Action(x, y);
        //}

        Log.e("SPAWN", "ici");

        input = true;
    }
}
