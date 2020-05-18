package com.example.petitougrand;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.example.petitougrand.Controler.Controleur;
import com.example.petitougrand.Vue.MyGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private MyGLSurfaceView gLView;
    private Controleur controleur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        gLView = new MyGLSurfaceView(this);
        setContentView(gLView);

        controleur = new Controleur(gLView.getRenderer());
    }
}
