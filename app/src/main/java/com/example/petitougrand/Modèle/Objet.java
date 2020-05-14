package com.example.petitougrand.Modèle;

import com.example.petitougrand.Vue.Formes.Forme_basic;

/*
 * Simplifie la déclaration d'un nouvel objet
 * */

public class Objet {

    protected Forme_basic forme;
    protected float[] position;
    protected float[] echelle;

    public Objet(Forme_basic f, float x, float y){
        this.forme = f;

        this.position = new float[2];
        this.position[0] = x;
        this.position[1] = y;

        this.echelle = new float[2];
        this.echelle[0] = f.echelleRelative;
        this.echelle[1] = f.echelleRelative;
    }

    public Objet(Forme_basic f, float x, float y, float echelle){
        this(f, x, y);
        this.echelle[0] = echelle * f.echelleRelative;
        this.echelle[1] = echelle * f.echelleRelative;
    }

    public Objet(Forme_basic f, float x, float y, float echelleX, float echelleY){
        this(f, x, y);
        this.echelle[0] = echelleX * f.echelleRelative;
        this.echelle[1] = echelleY * f.echelleRelative;
    }

    public Forme_basic getForme() {
        return forme;
    }

    public float[] getPosition() {
        return position;
    }

    public float[] getEchelle() {
        return echelle;
    }

    public void draw(float[] mvpMatrix){
        forme.draw(mvpMatrix);
    }
}