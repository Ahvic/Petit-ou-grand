package com.example.petitougrand.Vue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.petitougrand.Modèle.Boutons.Bouton;
import com.example.petitougrand.Modèle.Boutons.Bouton_Egal;
import com.example.petitougrand.Modèle.Boutons.Bouton_Inférieur;
import com.example.petitougrand.Modèle.Boutons.Bouton_Pass;
import com.example.petitougrand.Modèle.Boutons.Bouton_Supérieur;
import com.example.petitougrand.Modèle.Enum.EnumPlace;
import com.example.petitougrand.Modèle.Enum.EnumType;
import com.example.petitougrand.Vue.Formes.Carré;
import com.example.petitougrand.Vue.Formes.Croix;
import com.example.petitougrand.Vue.Formes.Gemme;
import com.example.petitougrand.Vue.Formes.Pentagone;
import com.example.petitougrand.Vue.Formes.Rubis;
import com.example.petitougrand.Vue.Formes.Triangle;
import com.example.petitougrand.Modèle.Objet;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Map<String, Objet> objetsScenes;            //emplacement, instance
    private Map<String, String> fileAttente;            //emplacement, type
    private Map<String, float[]> lstEmpls;     //correspondance nom, coordonées
    private List<Bouton> boutonsScenes;

    // Matrices Model/View/Projection
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    //Appelé lors du setup de la l'environnement
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        //Initialise les listes
        objetsScenes = new HashMap<>();
        fileAttente = new HashMap<>();
        lstEmpls = InitEmplacement();
        boutonsScenes = new ArrayList<>();

        //Références
        objetsScenes.put("ref_0", new Objet(new Triangle(), lstEmpls.get("ref_0")[0], lstEmpls.get("ref_0")[1], 0.5f));
        objetsScenes.put("ref_1", new Objet(new Carré(), lstEmpls.get("ref_1")[0], lstEmpls.get("ref_1")[1], 0.5f));
        objetsScenes.put("ref_2", new Objet(new Pentagone(), lstEmpls.get("ref_2")[0], lstEmpls.get("ref_2")[1], 0.5f));
        objetsScenes.put("ref_3", new Objet(new Rubis(), lstEmpls.get("ref_3")[0], lstEmpls.get("ref_3")[1], 0.5f));
        objetsScenes.put("ref_4", new Objet(new Gemme(), lstEmpls.get("ref_4")[0], lstEmpls.get("ref_4")[1], 0.5f));
        objetsScenes.put("ref_5", new Objet(new Croix(), lstEmpls.get("ref_5")[0], lstEmpls.get("ref_5")[1], 0.5f));

        //J1, J2 et tas par défaut
        objetsScenes.put("j_1", new Objet(new Carré(), lstEmpls.get("j_1")[0], lstEmpls.get("j_1")[1]));
        objetsScenes.put("tas", new Objet(new Croix(), lstEmpls.get("tas")[0], lstEmpls.get("tas")[1]));
        objetsScenes.put("j_2", new Objet(new Triangle(), lstEmpls.get("j_2")[0], lstEmpls.get("j_2")[1]));

        //Les boutons
        float couleur_inférieur[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
        float couleur_egal[] = { 1.00000000f, 1.00000000f, 0.00000000f, 1.0f };
        float couleur_supérieur[] = { 0.00000000f, 1.00000000f, 0.00000000f, 1.0f };
        float couleur_pass[] = { 0.70000000f, 0.70000000f, 0.70000000f, 1.0f };

        Bouton bouton_inférieur = new Bouton_Inférieur(new Carré(couleur_inférieur), lstEmpls.get("Inférieur")[0], lstEmpls.get("Inférieur")[1], 1.0f);
        Bouton bouton_egal = new Bouton_Egal(new Carré(couleur_egal), lstEmpls.get("Egal")[0], lstEmpls.get("Egal")[1], 1.0f);
        Bouton bouton_supérieur = new Bouton_Supérieur(new Carré(couleur_supérieur), lstEmpls.get("Supérieur")[0], lstEmpls.get("Supérieur")[1], 1.0f);
        Bouton bouton_pass = new Bouton_Pass(new Carré(couleur_pass), lstEmpls.get("Pass")[0], lstEmpls.get("Pass")[1], 2.5f, 0.5f);

        objetsScenes.put("Inférieur", bouton_inférieur);
        objetsScenes.put("Egal", bouton_egal);
        objetsScenes.put("Supérieur", bouton_supérieur);
        objetsScenes.put("Pass", bouton_pass);

        boutonsScenes.add(bouton_inférieur);
        boutonsScenes.add(bouton_egal);
        boutonsScenes.add(bouton_supérieur);
        boutonsScenes.add(bouton_pass);
    }

    //Appelé à chaque frame
    public void onDrawFrame(GL10 unused) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setIdentityM(viewMatrix,0);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //Initialisation des objets dans la file d'attente
        for(String emplacement: fileAttente.keySet()) {
            switch (fileAttente.get(emplacement)){
                case "Triangle": objetsScenes.put(emplacement, new Objet(new Triangle(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                     break;
                case "Carré": objetsScenes.put(emplacement, new Objet(new Carré(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                    break;
                case "Pentagone": objetsScenes.put(emplacement, new Objet(new Pentagone(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                    break;
                case "Rubis": objetsScenes.put(emplacement, new Objet(new Rubis(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                    break;
                case "Gemme": objetsScenes.put(emplacement, new Objet(new Gemme(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                    break;
                case "Croix": objetsScenes.put(emplacement, new Objet(new Croix(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1]));
                    break;
                case "Caché": objetsScenes.put(emplacement, new Objet(new Croix(), lstEmpls.get(emplacement)[0], lstEmpls.get(emplacement)[1], 0));
                    break;
                default:
                    objetsScenes.remove(emplacement);
            }

            fileAttente.remove(emplacement);
        }

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

        for(Bouton element: boutonsScenes){
            if(element.isTouched(x, y))
                element.Action();
        }
    }

    public void aDessiner(EnumPlace emplacement, EnumType type){
        String place = "";
        String forme = "";

        switch (emplacement){
            case j_1: place = "j_1";
                break;
            case tas: place = "tas";
                break;
            case j_2: place = "j_2";
                break;
        }

        switch (type){
            case Triangle: forme = "Triangle";
                break;
            case Carré: forme = "Carré";
                break;
            case Pentagone: forme = "Pentagone";
                break;
            case Rubis: forme = "Rubis";
                break;
            case Gemme: forme = "Gemme";
                break;
            case Croix: forme = "Croix";
                break;
            case Caché: forme = "Caché";
                break;
        }

        fileAttente.put(place, forme);
    }

    private HashMap<String, float[]> InitEmplacement(){
        HashMap<String, float[]> resultat = new HashMap<>();

        resultat.put("ref_0", new float[]{-4.0f, 8.0f});
        resultat.put("ref_1", new float[]{-2.7f, 8.0f});
        resultat.put("ref_2", new float[]{-1.4f, 8.0f});
        resultat.put("ref_3", new float[]{-0.0f, 8.0f});
        resultat.put("ref_4", new float[]{+1.3f, 8.0f});
        resultat.put("ref_5", new float[]{+3.0f, 8.0f});

        resultat.put("j_1", new float[]{-3.0f, 0.0f});
        resultat.put("tas", new float[]{-0.0f, 0.0f});
        resultat.put("j_2", new float[]{+3.0f, 0.0f});

        resultat.put("Inférieur", new float[]{-3.5f, -7.0f});
        resultat.put("Egal", new float[]{+0.0f, -7.0f});
        resultat.put("Supérieur", new float[]{+3.5f, -7.0f});
        resultat.put("Pass", new float[]{+0.0f, -9.0f});

        return resultat;
    }
}
