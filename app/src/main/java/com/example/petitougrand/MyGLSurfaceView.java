package com.example.petitougrand;

import android.content.Context;
import android.opengl.GLSurfaceView;
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

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        // la taille de l'écran en pixels
        float screen_x = getWidth();
        float screen_y = getHeight();

        /* Conversion des coordonnées pixel en coordonnées OpenGL
        Attention l'axe x est inversé par rapport à OpenGLSL
        On suppose que l'écran correspond à un carré d'arête 2 centré en 0
         */

        float x_opengl = +20.0f*x/getWidth() - 10.0f;
        float y_opengl = -20.0f*y/getHeight() + 10.0f;

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                renderer.onInput(x_opengl, y_opengl);
                requestRender();
        }

        return true;
    }
}
