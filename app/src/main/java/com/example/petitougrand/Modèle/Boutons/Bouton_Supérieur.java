package com.example.petitougrand.Modèle.Boutons;

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Vue.Formes.Forme_basic;

public class Bouton_Supérieur extends Bouton {

    public Bouton_Supérieur(Forme_basic f, float x, float y, float echelle) {
        super(f, x, y, echelle);
    }

    @Override
    public void Action() {
        //Log.e("BOUTONS", "bouton supérieur touché");
        Controleur.getInstance().ReceiveInput(+1);
    }
}
