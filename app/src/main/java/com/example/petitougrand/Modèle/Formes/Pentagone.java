package com.example.petitougrand.Modèle.Formes;

public class Pentagone extends Forme_basic {

    static float formeCoords[] = {
             0.0f,  0.0f, 0.0f,   // milieu
             0.0f,  1.0f, 0.0f,   // top
            -1.0f,  0.2f, 0.0f,   // top left
            -0.6f, -1.0f, 0.0f,   // bottom left
             1.0f,  0.2f, 0.0f,   // top right
             0.6f, -1.0f, 0.0f }; // bottom right

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 1.00000000f, 0.00000000f, 0.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2, 0, 2, 3, 0, 3, 5, 0, 5, 4, 0, 4, 1};
    static float echelleRelative = 1.2f;

    public Pentagone(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}
