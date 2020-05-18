package com.example.petitougrand.Modèle.Boutons;

import com.example.petitougrand.Controler.Controleur;

import com.example.petitougrand.Vue.Formes.Forme_basic;

public class Bouton_Pass extends Bouton {

    public Bouton_Pass(Forme_basic f, float x, float y, float echelleX, float echelleY) {
        super(f, x, y, echelleX, echelleY);
    }

    @Override
    public void Action() {
        //Log.e("BOUTONS", "bouton pass touché");
        Controleur.getInstance().ReceiveInput(-2);
    }
}
