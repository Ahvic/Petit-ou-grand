package com.example.petitougrand.Vue.Formes.UI;

import com.example.petitougrand.Vue.Formes.Forme_basic;

public class Inferieur extends Forme_basic {

    static float formeCoords[] = {
            +1.0f,  1.0f, 0.0f,   // 0
            -1.0f,  0.0f, 0.0f,   // 2
            +1.0f, -1.0f, 0.0f,   // 4
    };
    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2};
    static float echelleRelative = 1.0f;

    public Inferieur(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}
