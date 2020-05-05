package Boutons;

import android.util.Log;

import Formes.Forme_basic;

public class Bouton_Egal extends Bouton {

    public Bouton_Egal(Forme_basic f, float x, float y, float echelle) {
        super(f, x, y, echelle);
    }

    @Override
    public void Action(float x, float y) {
        if(isTouched(x, y)){
            Log.e("BOUTONS", "bouton égal touché");
        }
    }
}
