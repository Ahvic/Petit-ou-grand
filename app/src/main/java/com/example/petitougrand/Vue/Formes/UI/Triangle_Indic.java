package com.example.petitougrand.Vue.Formes.UI;

import com.example.petitougrand.Vue.Formes.Forme_basic;

public class Triangle_Indic extends Forme_basic {

    static float formeCoords[] = {
            -1.0f,  0.8f, 0.0f,   // top left
             1.0f,  0.8f, 0.0f,   // top right
             0.0f, -1.0f, 0.0f }; // bottom

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2 };
    static float echelleRelative = 0.8f;

    public Triangle_Indic(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}
