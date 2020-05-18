package com.example.petitougrand.Vue.Formes;

public class Gemme extends Forme_basic {

    static float formeCoords[] = {
            0.0f,  0.0f, 0.0f,   // milieu
            -0.3f,  1.0f, 0.0f,   // NO
            -0.7f,  0.5f, 0.0f,   // NOO
            -0.7f, -0.5f, 0.0f,   // SOO
            -0.3f, -1.0f, 0.0f,   // SO
            0.3f, -1.0f, 0.0f,   // SE
            0.7f, -0.5f, 0.0f,   // SEE
            0.7f,  0.5f, 0.0f,   // NEE
            0.3f,  1.0f, 0.0f};  // NE

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 0.00000000f, 0.75000000f, 1.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2, 0, 2, 3, 0, 3, 4, 0, 4, 5, 0, 5, 6, 0, 6, 7, 0, 7, 8, 0, 8, 1 };
    static float echelleRelative = 1.6f;

    public Gemme(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}