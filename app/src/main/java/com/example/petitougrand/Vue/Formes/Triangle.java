package com.example.petitougrand.Vue.Formes;

public class Triangle extends Forme_basic {

    static float formeCoords[] = {
            -1.0f, -1.0f, 0.0f,   // bottom left
             1.0f, -1.0f, 0.0f,   // bottom right
             0.0f,  0.8f, 0.0f }; // top

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 0.00000000f, 0.00000000f, 1.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2 };
    static float echelleRelative = 0.8f;

    public Triangle(){
        super(formeCoords, drawOrder, color, echelleRelative);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}
