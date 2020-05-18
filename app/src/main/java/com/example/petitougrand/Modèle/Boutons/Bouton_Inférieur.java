package com.example.petitougrand.Modèle.Boutons;

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Vue.Formes.Forme_basic;

public class Bouton_Inférieur extends Bouton {

    public Bouton_Inférieur(Forme_basic f, float x, float y, float echelle) {
        super(f, x, y, echelle);
    }

    @Override
    public void Action() {
        //Log.e("BOUTONS", "bouton inférieur touché");
        Controleur.getInstance().ReceiveInput(-1);
    }
}
