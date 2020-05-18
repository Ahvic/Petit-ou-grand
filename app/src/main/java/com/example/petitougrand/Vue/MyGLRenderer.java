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

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Modèle.Boutons.Bouton;
import com.example.petitougrand.Modèle.Boutons.Bouton_Egal;
import com.example.petitougrand.Modèle.Boutons.Bouton_Inférieur;
import com.example.petitougrand.Modèle.Boutons.Bouton_Pass;
import com.example.petitougrand.Modèle.Boutons.Bouton_Supérieur;
import com.example.petitougrand.Modèle.Enum.EnumPlace;
import com.example.petitougrand.Modèle.Enum.EnumPlaceRender;
import com.example.petitougrand.Modèle.Enum.EnumType;
import com.example.petitougrand.Vue.Formes.Carré;
import com.example.petitougrand.Vue.Formes.Croix;
import com.example.petitougrand.Vue.Formes.Forme_basic;
import com.example.petitougrand.Vue.Formes.Gemme;
import com.example.petitougrand.Vue.Formes.UI.Egal;
import com.example.petitougrand.Vue.Formes.UI.Inferieur;
import com.example.petitougrand.Vue.Formes.Pentagone;
import com.example.petitougrand.Vue.Formes.Rubis;
import com.example.petitougrand.Vue.Formes.Triangle;
import com.example.petitougrand.Modèle.Objet;
import com.example.petitougrand.Vue.Formes.UI.Superieur;
import com.example.petitougrand.Vue.Formes.UI.Triangle_Indic;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Map<String, Objet> objetsScenes;            //emplacement, instance
    private List<objet_Attente> fileAttente;            //type, emplacement
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
        fileAttente = new ArrayList<>();
        boutonsScenes = new ArrayList<>();

        //Références
        objetsScenes.put("ref_0", new Objet(new Triangle(), EnumPlaceRender.ref_0.position[0], EnumPlaceRender.ref_0.position[1], 0.5f));
        objetsScenes.put("ref_1", new Objet(new Carré(), EnumPlaceRender.ref_1.position[0], EnumPlaceRender.ref_1.position[1], 0.5f));
        objetsScenes.put("ref_2", new Objet(new Pentagone(), EnumPlaceRender.ref_2.position[0], EnumPlaceRender.ref_2.position[1], 0.5f));
        objetsScenes.put("ref_3", new Objet(new Rubis(), EnumPlaceRender.ref_3.position[0], EnumPlaceRender.ref_3.position[1], 0.5f));
        objetsScenes.put("ref_4", new Objet(new Gemme(), EnumPlaceRender.ref_4.position[0], EnumPlaceRender.ref_4.position[1], 0.5f));
        objetsScenes.put("ref_5", new Objet(new Croix(), EnumPlaceRender.ref_5.position[0], EnumPlaceRender.ref_5.position[1], 0.5f));

        //J1, J2 et tas par défaut
        objetsScenes.put("j_1", new Objet(new Carré(), EnumPlaceRender.j_1.position[0], EnumPlaceRender.j_1.position[1]));
        objetsScenes.put("tas", new Objet(new Croix(), EnumPlaceRender.tas.position[0], EnumPlaceRender.tas.position[1]));
        objetsScenes.put("j_2", new Objet(new Triangle(), EnumPlaceRender.j_2.position[0], EnumPlaceRender.j_2.position[1]));

        //Les boutons
        float couleur_inférieur[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
        float couleur_egal[] = { 1.00000000f, 1.00000000f, 0.00000000f, 1.0f };
        float couleur_supérieur[] = { 0.00000000f, 1.00000000f, 0.00000000f, 1.0f };
        float couleur_pass[] = { 0.70000000f, 0.70000000f, 0.70000000f, 1.0f };

        Bouton bouton_inférieur = new Bouton_Inférieur(new Inferieur(), EnumPlaceRender.Inférieur.position[0], EnumPlaceRender.Inférieur.position[1], 1.0f);
        Bouton bouton_egal = new Bouton_Egal(new Egal(), EnumPlaceRender.Egal.position[0], EnumPlaceRender.Egal.position[1], 1.0f);
        Bouton bouton_supérieur = new Bouton_Supérieur(new Superieur(), EnumPlaceRender.Supérieur.position[0], EnumPlaceRender.Supérieur.position[1], 1.0f);
        Bouton bouton_pass = new Bouton_Pass(new Carré(couleur_pass), EnumPlaceRender.Pass.position[0], EnumPlaceRender.Pass.position[1], 2.5f, 0.5f);

        objetsScenes.put("Inférieur", bouton_inférieur);
        objetsScenes.put("Egal", bouton_egal);
        objetsScenes.put("Supérieur", bouton_supérieur);
        objetsScenes.put("Pass", bouton_pass);

        boutonsScenes.add(bouton_inférieur);
        boutonsScenes.add(bouton_egal);
        boutonsScenes.add(bouton_supérieur);
        boutonsScenes.add(bouton_pass);

        //Préviens le controlleur qu'il a fini de s'initialiser
        Controleur.getInstance().LancementModel();
    }

    //Appelé à chaque frame
    public void onDrawFrame(GL10 unused) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setIdentityM(viewMatrix,0);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //Initialisation des objets dans la file d'attente
        for (objet_Attente objet: fileAttente) {
            Forme_basic forme;
            float echelle = 1.0f;

            switch (objet.forme){
                case Triangle: forme = new Triangle();
                    break;
                case Carré: forme = new Carré();
                    break;
                case Pentagone: forme = new Pentagone();
                    break;
                case Rubis: forme = new Rubis();
                    break;
                case Gemme: forme = new Gemme();
                    break;
                case Croix: forme = new Croix();
                    break;
                case Indic: forme = new Triangle_Indic();
                    break;
                default: forme = new Carré();
                    echelle = 0.0f;
                    break;
            }

            Objet obj = new Objet(forme, objet.coords[0], objet.coords[1], echelle);
            objetsScenes.put(objet.nom, obj);
        }

        fileAttente.clear();

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

    //La fonction utilisés à l'extérieur pour donner des ordres au renderer
    //Met a jour la liste d'attente
    public void aDessiner(EnumPlace emplacement, EnumType type){
        float[] place;

        switch (emplacement){
            case j_1: place = EnumPlaceRender.j_1.position;
                break;
            case tas: place = EnumPlaceRender.tas.position;
                break;
            case j_2: place = EnumPlaceRender.j_2.position;
                break;
            case indJ_1: place = EnumPlaceRender.indJ_1.position;
                break;
            case indJ_2: place = EnumPlaceRender.indJ_2.position;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + emplacement);
        }

        objet_Attente a = new objet_Attente(place, type, emplacement.name());

        fileAttente.add(a);
    }

    private class objet_Attente{
        public String nom;
        public float[] coords;
        public EnumType forme;

        public objet_Attente(float[] coords, EnumType forme, String nom){
            this.coords = coords;
            this.forme = forme;
            this.nom = nom;
        }
    }
}
