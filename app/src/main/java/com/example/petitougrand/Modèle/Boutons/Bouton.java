package com.example.petitougrand.Modèle.Boutons;

import com.example.petitougrand.Modèle.Objet;

import com.example.petitougrand.Vue.Formes.Forme_basic;

public abstract class Bouton extends Objet {

    public Bouton(Forme_basic f, float x, float y, float echelle) {
        super(f, x, y, echelle);
    }

    public Bouton(Forme_basic f, float x, float y, float echelleX, float echelleY) {
        super(f, x, y, echelleX, echelleY);
    }

    public boolean isTouched(float x, float y){

        float tailleReeleX = 1.0f * echelle[0];
        float tailleReeleY = 1.0f * echelle[1];

        if(x > position[0] - tailleReeleX && x < position[0]  + tailleReeleX){
            if(y > position[1] - tailleReeleY && y < position[1]  + tailleReeleY){
                return true;
            }
        }

        return false;
    }

    public abstract void Action();
}
