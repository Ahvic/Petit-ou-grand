package Boutons;

import android.util.Log;

import Formes.Forme_basic;

public class Bouton_Inférieur extends Bouton {

    public Bouton_Inférieur(Forme_basic f, float x, float y, float echelle) {
        super(f, x, y, echelle);
    }

    @Override
    public void Action(float x, float y) {
        if(isTouched(x, y)){
            Log.e("BOUTONS", "bouton inférieur touché");
        }
    }
}
