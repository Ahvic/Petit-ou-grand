package Formes;

public class Croix extends Forme_basic {

    static float formeCoords[] = {
             0.0f,  0.0f, 0.0f,   // 0
            -0.4f,  1.0f, 0.0f,   // 1
            -0.4f,  0.5f, 0.0f,   // 2
            -0.8f,  0.5f, 0.0f,   // 3
            -0.8f, -0.5f, 0.0f,   // 4
            -0.4f, -0.5f, 0.0f,   // 5
            -0.4f, -1.0f, 0.0f,   // 6
             0.4f, -1.0f, 0.0f,   // 7
             0.4f, -0.5f, 0.0f,   // 8
             0.8f, -0.5f, 0.0f,   // 9
             0.8f,  0.5f, 0.0f,   // 10
             0.4f,  0.5f, 0.0f,   // 11
             0.4f,  1.0f, 0.0f};  // 12

    // Set color with red, green, blue and alpha (opacity) values
    static float color[] = { 1.00000000f, 0.00000000f, 0.20000000f, 1.0f };
    static private short drawOrder[] = { 0, 1, 2, 0, 2, 3, 0, 3, 4, 0, 4, 5, 0, 5, 6, 0, 6, 7, 0, 7, 8, 0, 8, 9, 0, 9, 10, 0, 10, 11, 0, 11, 12, 0, 12, 1 };

    public Croix(){
        super(formeCoords, drawOrder, color);
    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);
    }
}