package com.example.petitougrand.Vue;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer renderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        renderer = new MyGLRenderer();
        setRenderer(renderer);

        // On redessine que si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public MyGLRenderer getRenderer(){
        return renderer;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        // la taille de l'écran en pixels
        float screen_x = getWidth();
        float screen_y = getHeight();

        /* Conversion des coordonnées pixel en coordonnées OpenGL
        On applique un ratio pour prendre en compte que l'écran n'est pas un carré
         */

        float ratio = (float) screen_x / screen_y;

        float x_opengl = (+20.0f * x/getWidth() - 10.0f) * ratio;
        float y_opengl = -20.0f * y/getHeight() + 10.0f;

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                renderer.onInput(x_opengl, y_opengl);
                requestRender();
        }

        return true;
    }
}
