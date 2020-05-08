package com.example.petitougrand.Modèle.Formes;

public class Rubis extends Forme_basic {

    static float formeCoords[] = {
             0.0f,  0.0f, 0.0f,   // milieu
             0.0f,  1.0f, 0.0f,   // top
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
             0.0f, -1.0f, 0.0f,   // bottom
             0.5f, -0.5f, 0.0f,   // bottom right
             0.5f,  0.5f, 0.0f};  // top right

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 1.00000000f, 0.50000000f, 0.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2, 0, 2, 3, 0, 3, 4, 0, 4, 5, 0, 5, 6, 0, 6, 1};
    static float echelleRelative = 1.4f;

    public Rubis(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}