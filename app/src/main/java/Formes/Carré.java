package Formes;

public class Carré extends Forme_basic {

    static float formeCoords[] = {
            -1.0f,  1.0f, 0.0f,   // top left
            -1.0f, -1.0f, 0.0f,   // bottom left
             1.0f, -1.0f, 0.0f,   // bottom right
             1.0f,  1.0f, 0.0f }; // top right}

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 0.00000000f, 1.00000000f, 0.00000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2, 0, 2, 3 };

    public Carré(){
        super(formeCoords, drawOrder, color);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}
